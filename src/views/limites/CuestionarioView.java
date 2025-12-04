package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import views.ViewManager;
import java.util.ArrayList;
import java.util.List;

public class CuestionarioView {
    private ViewManager viewManager;

    // Questions data: {Question, Option1, Option2, Option3,
    // CorrectOptionIndex(0-based)}
    private final String[][] questions = {
            {
                    "Calcula: lim(x→2) (x + 3)",
                    "2",
                    "3",
                    "5",
                    "2" // Correct: 5
            },
            {
                    "Calcula: lim(x→3) 7",
                    "3",
                    "7",
                    "0",
                    "1" // Correct: 7
            },
            {
                    "Si lim(x→a⁻) f(x) = 3 y lim(x→a⁺) f(x) = 5, ¿existe el límite?",
                    "Sí, es 4",
                    "No existe",
                    "Sí, es 3",
                    "1" // Correct: No existe
            },
            {
                    "Calcula: lim(x→1) (x² + 1)",
                    "1",
                    "2",
                    "0",
                    "1" // Correct: 2
            },
            {
                    "Calcula: lim(x→0) x",
                    "0",
                    "1",
                    "Indeterminado",
                    "0" // Correct: 0
            }
    };

    public CuestionarioView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public VBox createView() {
        VBox contentLayout = new VBox(0);
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        // Header
        StackPane header = new StackPane();
        header.setPadding(new Insets(35, 20, 25, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

        Button backButton = new Button("←");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #184093; -fx-font-size: 20; " +
                "-fx-font-weight: bold; -fx-border-color: transparent; -fx-cursor: hand;");
        backButton.setOnAction(e -> {
            VideoView videoView = new VideoView(viewManager);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(videoView.createView());
        });

        VBox headerContent = new VBox(3);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("CUESTIONARIO");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Demuestra lo aprendido");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);

        BorderPane headerLayout = new BorderPane();
        headerLayout.setLeft(backButton);
        headerLayout.setCenter(headerContent);
        header.getChildren().add(headerLayout);

        // Main Content Scroll
        ScrollPane scrollContent = new ScrollPane();
        scrollContent.setFitToWidth(true);
        scrollContent.setStyle("-fx-background: transparent; -fx-background-color: transparent; " +
                "-fx-border-color: transparent; -fx-padding: 0;");

        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(30));
        mainContent.setAlignment(Pos.TOP_CENTER);
        mainContent.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        List<ToggleGroup> toggleGroups = new ArrayList<>();

        for (int i = 0; i < questions.length; i++) {
            mainContent.getChildren().add(createQuestionCard(i, toggleGroups));
        }

        Label feedbackLabel = new Label("");
        feedbackLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));

        Button checkButton = new Button("Verificar y Continuar");
        checkButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + "; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 12 30; -fx-font-size: 16; " +
                "-fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");

        checkButton.setOnAction(e -> {
            boolean allCorrect = true;
            boolean allAnswered = true;

            for (int i = 0; i < questions.length; i++) {
                ToggleGroup group = toggleGroups.get(i);
                RadioButton selected = (RadioButton) group.getSelectedToggle();

                if (selected == null) {
                    allAnswered = false;
                    break;
                }

                int selectedIndex = (int) selected.getUserData();
                int correctIndex = Integer.parseInt(questions[i][4]);

                if (selectedIndex != correctIndex) {
                    allCorrect = false;
                }
            }

            if (!allAnswered) {
                feedbackLabel.setText("Por favor responde todas las preguntas.");
                feedbackLabel.setTextFill(javafx.scene.paint.Color.RED);
            } else if (allCorrect) {
                feedbackLabel.setText("¡Excelente! Todas las respuestas son correctas.");
                feedbackLabel.setTextFill(javafx.scene.paint.Color.GREEN);

                // Proceed to Exercises
                ExerciseSeriesView exercisesView = new ExerciseSeriesView(viewManager);
                viewManager.getRoot().getChildren().clear();
                viewManager.getRoot().getChildren().add(exercisesView.createView());
            } else {
                feedbackLabel.setText("Algunas respuestas son incorrectas. Inténtalo de nuevo.");
                feedbackLabel.setTextFill(javafx.scene.paint.Color.RED);
            }
        });

        mainContent.getChildren().addAll(feedbackLabel, checkButton);

        scrollContent.setContent(mainContent);
        contentLayout.getChildren().addAll(header, scrollContent);

        return contentLayout;
    }

    private VBox createQuestionCard(int index, List<ToggleGroup> toggleGroups) {
        VBox card = new VBox(15);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5, 0, 0, 2);");
        card.setPadding(new Insets(20));
        card.setMaxWidth(700);

        String[] qData = questions[index];
        Label qLabel = new Label((index + 1) + ". " + qData[0]);
        qLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        qLabel.setWrapText(true);
        qLabel.setTextFill(AppConstants.TEXT_COLOR);

        VBox optionsBox = new VBox(10);
        ToggleGroup group = new ToggleGroup();
        toggleGroups.add(group);

        for (int i = 1; i <= 3; i++) {
            RadioButton rb = new RadioButton(qData[i]);
            rb.setToggleGroup(group);
            rb.setFont(Font.font("Segoe UI", 14));
            rb.setTextFill(AppConstants.TEXT_COLOR);
            rb.setUserData(i - 1);
            optionsBox.getChildren().add(rb);
        }

        card.getChildren().addAll(qLabel, optionsBox);
        return card;
    }
}
