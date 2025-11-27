package views.derivadas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import utils.AppConstants;
import utils.UIComponents;
import views.ViewManager;

public class NivelEvaluacionDerivadasView {
    private ViewManager viewManager;
    private EvaluacionDerivadasView parentView;
    private String nivel;

    public NivelEvaluacionDerivadasView(ViewManager viewManager, EvaluacionDerivadasView parentView, String nivel) {
        this.viewManager = viewManager;
        this.parentView = parentView;
        this.nivel = nivel;
    }

    public VBox createView() {
        VBox contentLayout = new VBox(20);
        contentLayout.setPadding(new Insets(30));
        contentLayout.setAlignment(Pos.TOP_CENTER);
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        // Header
        VBox header = UIComponents.createHeader("Evaluación: Nivel " + nivel, "Responde las siguientes preguntas");

        // Questions Container
        VBox questionsContainer = new VBox(20);
        questionsContainer.setPadding(new Insets(10));

        List<Question> questions = getQuestionsForLevel(nivel);
        for (int i = 0; i < questions.size(); i++) {
            questionsContainer.getChildren().add(createQuestionCard(i + 1, questions.get(i)));
        }

        // Submit Button
        Button submitBtn = UIComponents.createPrimaryButton("Calificar Evaluación");
        submitBtn.setOnAction(e -> showResults(questionsContainer, questions));

        // Back Button
        Button backBtn = UIComponents.createSecondaryButton("⬅ Cancelar y Volver");
        backBtn.setOnAction(e -> {
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(parentView.createView());
        });

        contentLayout.getChildren().addAll(header, questionsContainer, submitBtn, backBtn);

        ScrollPane scrollPane = new ScrollPane(contentLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox mainContainer = new VBox(scrollPane);
        mainContainer.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        return mainContainer;
    }

    private VBox createQuestionCard(int number, Question question) {
        VBox card = new VBox(15);
        card.setPadding(new Insets(20));
        card.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        Text questionText = new Text(number + ". " + question.text);
        questionText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        questionText.setFill(AppConstants.TEXT_COLOR);
        questionText.setWrappingWidth(650);

        VBox optionsBox = new VBox(10);
        ToggleGroup group = new ToggleGroup();

        for (int i = 0; i < question.options.length; i++) {
            RadioButton rb = new RadioButton(question.options[i]);
            rb.setToggleGroup(group);
            rb.setUserData(i); // Store index as user data
            rb.setFont(Font.font("Segoe UI", 14));
            rb.setWrapText(true);
            optionsBox.getChildren().add(rb);
        }

        card.setUserData(group); // Store toggle group in card for easy access
        card.getChildren().addAll(questionText, optionsBox);
        return card;
    }

    private void showResults(VBox container, List<Question> questions) {
        int correct = 0;
        int total = questions.size();

        for (int i = 0; i < total; i++) {
            VBox card = (VBox) container.getChildren().get(i);
            ToggleGroup group = (ToggleGroup) card.getUserData();

            if (group.getSelectedToggle() != null) {
                int selectedIndex = (int) group.getSelectedToggle().getUserData();
                if (selectedIndex == questions.get(i).correctIndex) {
                    correct++;
                    card.setStyle(
                            "-fx-background-color: #e8f5e9; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2); -fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 10;");
                } else {
                    card.setStyle(
                            "-fx-background-color: #ffebee; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2); -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 10;");
                }
            } else {
                card.setStyle(
                        "-fx-background-color: #fff3e0; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2); -fx-border-color: orange; -fx-border-width: 2; -fx-border-radius: 10;");
            }

            // Disable all radio buttons
            VBox optionsBox = (VBox) card.getChildren().get(1);
            optionsBox.getChildren().forEach(node -> node.setDisable(true));
        }

        // Show result alert
        VBox resultBox = new VBox(15);
        resultBox.setAlignment(Pos.CENTER);
        resultBox.setPadding(new Insets(20));
        resultBox.setStyle(
                "-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 5);");

        Text scoreText = new Text("Puntuación: " + correct + "/" + total);
        scoreText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        scoreText.setFill(correct == total ? Color.GREEN : (correct >= total / 2 ? Color.ORANGE : Color.RED));

        Text messageText = new Text(correct == total ? "¡Excelente trabajo!"
                : (correct >= total / 2 ? "¡Bien hecho!" : "Sigue practicando"));
        messageText.setFont(Font.font("Segoe UI", 18));

        Button closeBtn = UIComponents.createPrimaryButton("Finalizar");
        closeBtn.setOnAction(e -> {
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(parentView.createView());
        });

        resultBox.getChildren().addAll(scoreText, messageText, closeBtn);

        // Replace content with result
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(resultBox);
    }

    private static class Question {
        String text;
        String[] options;
        int correctIndex;

        Question(String text, String[] options, int correctIndex) {
            this.text = text;
            this.options = options;
            this.correctIndex = correctIndex;
        }
    }

    private List<Question> getQuestionsForLevel(String nivel) {
        List<Question> questions = new ArrayList<>();

        if (nivel.equals("Introducción")) {
            questions.add(new Question("La derivada de x² es:", new String[] { "2x", "x", "2", "x²" }, 0));
            questions.add(new Question("La derivada de una constante es:",
                    new String[] { "1", "0", "La constante", "x" }, 1));
            questions.add(new Question("La derivada representa:",
                    new String[] { "Área", "Pendiente", "Volumen", "Longitud" }, 1));
        } else if (nivel.equals("Intermedio")) {
            questions.add(
                    new Question("Derivada de sin(x²):", new String[] { "cos(x²)", "2xcos(x²)", "-sin(x²)", "2x" }, 1));
            questions.add(new Question("Derivada de e^(3x):", new String[] { "e^(3x)", "3e^(3x)", "3x", "e^x" }, 1));
            questions.add(new Question("Derivada de ln(x):", new String[] { "1/x", "e^x", "x", "1" }, 0));
        } else { // Avanzado
            questions.add(new Question("Segunda derivada de x³:", new String[] { "3x²", "6x", "6", "0" }, 1));
            questions.add(
                    new Question("Puntos críticos de x³ - 3x:", new String[] { "0, 1", "1, -1", "0, 3", "2, -2" }, 1));
            questions.add(
                    new Question("Derivada de x^x:", new String[] { "x^x(1+ln(x))", "x·x^(x-1)", "x^x", "ln(x)" }, 0));
        }

        return questions;
    }
}
