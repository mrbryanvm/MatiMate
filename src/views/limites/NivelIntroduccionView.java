package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.UIComponents;
import views.ViewManager;
import java.util.ArrayList;
import java.util.List;

public class NivelIntroduccionView {
    private ViewManager viewManager;
    private String nivel;
    private IntroduccionView introduccionView;

    public NivelIntroduccionView(ViewManager viewManager, String nivel, IntroduccionView introduccionView) {
        this.viewManager = viewManager;
        this.nivel = nivel;
        this.introduccionView = introduccionView;
    }

    public VBox createView() {
        VBox nivelLayout = new VBox(0);
        nivelLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(40, 20, 30, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

        VBox headerContent = new VBox(5);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("Evaluación");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Nivel: " + nivel);
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);
        header.getChildren().add(headerContent);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        VBox contentContainer = new VBox(20);
        contentContainer.setPadding(new Insets(25, 25, 25, 25));
        contentContainer.setAlignment(Pos.CENTER);

        // Quiz Container
        VBox quizBox = createQuizBox();

        contentContainer.getChildren().add(quizBox);
        scrollPane.setContent(contentContainer);

        nivelLayout.getChildren().addAll(header, scrollPane);

        return nivelLayout;
    }

    private VBox createQuizBox() {
        VBox quizBox = new VBox(20);
        quizBox.setAlignment(Pos.TOP_LEFT);
        quizBox.setPadding(new Insets(25));
        quizBox.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);");
        quizBox.setMaxWidth(600);

        List<ToggleGroup> toggleGroups = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();

        // Generate questions based on level
        if (nivel.equals("Fácil")) {
            addQuestion(quizBox, 1, "Calcular lim(x→2) (x + 5)",
                    new String[] { "5", "7", "2", "0" }, "7", toggleGroups, correctAnswers);
            addQuestion(quizBox, 2, "Calcular lim(x→3) (2x - 1)",
                    new String[] { "5", "6", "4", "3" }, "5", toggleGroups, correctAnswers);
            addQuestion(quizBox, 3, "Calcular lim(x→0) (x² + 2)",
                    new String[] { "0", "2", "4", "Indefinido" }, "2", toggleGroups, correctAnswers);
        } else if (nivel.equals("Normal")) {
            addQuestion(quizBox, 1, "Calcular lim(x→2) (x² - 4)/(x - 2)",
                    new String[] { "0", "2", "4", "Indefinido" }, "4", toggleGroups, correctAnswers);
            addQuestion(quizBox, 2, "Calcular lim(x→1) (x² + x - 2)/(x - 1)",
                    new String[] { "1", "3", "0", "2" }, "3", toggleGroups, correctAnswers);
            addQuestion(quizBox, 3, "Calcular lim(x→3) (x² - 9)/(x - 3)",
                    new String[] { "6", "0", "9", "3" }, "6", toggleGroups, correctAnswers);
        } else if (nivel.equals("Difícil")) {
            addQuestion(quizBox, 1, "Calcular lim(x→0) (√(x+1) - 1)/x",
                    new String[] { "0", "1", "1/2", "Indefinido" }, "1/2", toggleGroups, correctAnswers);
            addQuestion(quizBox, 2, "Calcular lim(x→∞) (2x² + 3)/(x² - 1)",
                    new String[] { "2", "∞", "0", "1" }, "2", toggleGroups, correctAnswers);
            addQuestion(quizBox, 3, "Calcular lim(x→0) (sen x)/x",
                    new String[] { "0", "1", "∞", "Indefinido" }, "1", toggleGroups, correctAnswers);
        }

        // Result Label
        Label resultLabel = new Label("");
        resultLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        resultLabel.setTextFill(AppConstants.PRIMARY_COLOR);

        // Buttons
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button calificarBtn = UIComponents.createPrimaryButton("Calificar");
        calificarBtn.setOnAction(e -> {
            int score = 0;
            for (int i = 0; i < toggleGroups.size(); i++) {
                RadioButton selected = (RadioButton) toggleGroups.get(i).getSelectedToggle();
                if (selected != null && selected.getText().equals(correctAnswers.get(i))) {
                    score++;
                }
            }
            resultLabel.setText("Tu nota es: " + score + "/3");
            if (score == 3) {
                resultLabel.setTextFill(AppConstants.PRIMARY_COLOR);
            } else {
                resultLabel.setTextFill(AppConstants.TITLE_COLOR);
            }
        });

        Button atrasBtn = UIComponents.createSecondaryButton("Atrás");
        atrasBtn.setOnAction(e -> introduccionView.show());

        buttonBox.getChildren().addAll(atrasBtn, calificarBtn);

        quizBox.getChildren().addAll(new Separator(), resultLabel, buttonBox);

        return quizBox;
    }

    private void addQuestion(VBox container, int number, String questionText, String[] options, String correctAnswer,
            List<ToggleGroup> toggleGroups, List<String> correctAnswers) {
        VBox questionBox = new VBox(10);
        questionBox.setPadding(new Insets(0, 0, 15, 0));

        Label qLabel = new Label(number + ". " + questionText);
        qLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        qLabel.setTextFill(AppConstants.TEXT_COLOR);
        qLabel.setWrapText(true);

        ToggleGroup group = new ToggleGroup();
        VBox optionsBox = new VBox(5);
        optionsBox.setPadding(new Insets(0, 0, 0, 15));

        for (String option : options) {
            RadioButton rb = new RadioButton(option);
            rb.setToggleGroup(group);
            rb.setFont(Font.font("Segoe UI", 13));
            rb.setTextFill(AppConstants.TEXT_COLOR);
            optionsBox.getChildren().add(rb);
        }

        toggleGroups.add(group);
        correctAnswers.add(correctAnswer);

        questionBox.getChildren().addAll(qLabel, optionsBox);
        container.getChildren().add(questionBox);
    }
}