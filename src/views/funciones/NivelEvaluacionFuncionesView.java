package views.funciones;

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

public class NivelEvaluacionFuncionesView {
    private ViewManager viewManager;
    private EvaluacionFuncionesView parentView;
    private String nivel;

    public NivelEvaluacionFuncionesView(ViewManager viewManager, EvaluacionFuncionesView parentView, String nivel) {
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
            questions.add(new Question("¿Qué es una función?", new String[] { "Una relación uno a uno",
                    "Una relación uno a muchos", "Una línea curva", "Un número" }, 0));
            questions.add(new Question("¿Cuál es el dominio de f(x) = x?",
                    new String[] { "Solo positivos", "Solo negativos", "Todos los reales", "Solo el cero" }, 2));
            questions.add(new Question("¿Qué es el rango?",
                    new String[] { "Valores de entrada", "Valores de salida", "El eje x", "El origen" }, 1));
        } else if (nivel.equals("Intermedio")) {
            questions.add(new Question("Si f(x) = 2x + 1, halla f(3)", new String[] { "6", "7", "5", "8" }, 1));
            questions.add(new Question("¿Cuál es el dominio de f(x) = 1/x?",
                    new String[] { "R", "R - {0}", "R - {1}", "Positivos" }, 1));
            questions.add(
                    new Question("Si f(x) = x² y g(x) = x + 1, halla f(g(1))", new String[] { "2", "3", "4", "1" }, 2));
        } else { // Avanzado
            questions.add(new Question("¿Cuál es el dominio de f(x) = ln(x-2)?",
                    new String[] { "x > 2", "x ≥ 2", "x < 2", "x ≠ 2" }, 0));
            questions.add(new Question("Si f(x) es inyectiva, entonces:", new String[] { "f(a)=f(b) implica a=b",
                    "f(a)=f(b) implica a≠b", "f es sobreyectiva", "f es constante" }, 0));
            questions.add(new Question("Halla la inversa de f(x) = 2x - 4",
                    new String[] { "x/2 + 2", "2x + 4", "x/2 - 2", "x + 2" }, 0));
        }

        return questions;
    }
}
