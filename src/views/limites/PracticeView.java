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

public class PracticeView {
    private ViewManager viewManager;
    private int currentExerciseIndex = 0;
    private int currentStepIndex = 0;

    // Data structure: Exercise -> Steps -> {Question, Option1, Option2, Option3,
    // Option4, CorrectIndex}
    private final String[][][] practiceData = {
            // Exercise 1
            {
                    {
                            "Ejercicio 1: Calcular lim(x→1) (2x + 5)\nPaso 1: ¿Qué debemos hacer primero?",
                            "Factorizar la expresión",
                            "Sustituir x por 1 directamente",
                            "Derivar la función",
                            "Graficar la función",
                            "1" // Correct: Sustituir
                    },
                    {
                            "Paso 2: Al sustituir x=1, ¿qué expresión obtenemos?",
                            "2(1) + 5",
                            "2(0) + 5",
                            "2 + 5x",
                            "2(1) - 5",
                            "0" // Correct: 2(1) + 5
                    },
                    {
                            "Paso 3: ¿Cuál es el resultado final?",
                            "6",
                            "5",
                            "7",
                            "8",
                            "2" // Correct: 7
                    }
            },
            // Exercise 2
            {
                    {
                            "Ejercicio 2: Calcular lim(x→2) (x² - 4)/(x - 2)\nPaso 1: Si evaluamos directamente, ¿qué obtenemos?",
                            "4",
                            "0",
                            "0/0 (Indeterminación)",
                            "Infinito",
                            "2" // Correct: 0/0
                    },
                    {
                            "Paso 2: ¿Cómo resolvemos la indeterminación?",
                            "Factorizando el numerador como diferencia de cuadrados",
                            "Multiplicando por el conjugado",
                            "Elevando al cuadrado",
                            "No se puede resolver",
                            "0" // Correct: Factorizando
                    },
                    {
                            "Paso 3: ¿Cómo queda la expresión factorizada?",
                            "(x-2)(x-2)/(x-2)",
                            "(x+2)(x-2)/(x-2)",
                            "(x+2)(x+2)/(x-2)",
                            "(x-4)(x+1)/(x-2)",
                            "1" // Correct: (x+2)(x-2)/(x-2)
                    },
                    {
                            "Paso 4: Al simplificar y evaluar el límite, ¿cuál es el resultado?",
                            "0",
                            "2",
                            "4",
                            "Infinito",
                            "2" // Correct: 4
                    }
            }
    };

    public PracticeView(ViewManager viewManager) {
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
            if (currentStepIndex > 0) {
                currentStepIndex--;
                show();
            } else if (currentExerciseIndex > 0) {
                currentExerciseIndex--;
                currentStepIndex = practiceData[currentExerciseIndex].length - 1;
                show();
            } else {
                ExerciseSeriesView exercisesView = new ExerciseSeriesView(viewManager);
                viewManager.getRoot().getChildren().clear();
                viewManager.getRoot().getChildren().add(exercisesView.createView());
            }
        });

        VBox headerContent = new VBox(3);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("PRÁCTICA GUIADA");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Ejercicio " + (currentExerciseIndex + 1) + " - Paso " + (currentStepIndex + 1));
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);

        BorderPane headerLayout = new BorderPane();
        headerLayout.setLeft(backButton);
        headerLayout.setCenter(headerContent);
        header.getChildren().add(headerLayout);

        // Main Content
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40));
        mainContent.setAlignment(Pos.TOP_CENTER);

        // Question Card
        VBox card = new VBox(20);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        card.setPadding(new Insets(30));
        card.setMaxWidth(600);

        String[] stepData = practiceData[currentExerciseIndex][currentStepIndex];
        String questionText = stepData[0];
        int correctIndex = Integer.parseInt(stepData[5]);

        Label qLabel = new Label(questionText);
        qLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        qLabel.setWrapText(true);
        qLabel.setTextFill(AppConstants.TEXT_COLOR);

        VBox optionsBox = new VBox(15);
        ToggleGroup group = new ToggleGroup();

        for (int i = 1; i <= 4; i++) {
            RadioButton rb = new RadioButton(stepData[i]);
            rb.setToggleGroup(group);
            rb.setFont(Font.font("Segoe UI", 16));
            rb.setTextFill(AppConstants.TEXT_COLOR);
            rb.setWrapText(true);
            rb.setUserData(i - 1);
            optionsBox.getChildren().add(rb);
        }

        Label feedbackLabel = new Label("");
        feedbackLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));

        Button checkButton = new Button("Siguiente Paso");
        checkButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + "; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 12 30; -fx-font-size: 16; " +
                "-fx-cursor: hand;");

        checkButton.setOnAction(e -> {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            if (selected == null) {
                feedbackLabel.setText("Por favor selecciona una opción.");
                feedbackLabel.setTextFill(javafx.scene.paint.Color.RED);
                return;
            }

            int selectedIndex = (int) selected.getUserData();
            if (selectedIndex == correctIndex) {
                // Correct
                if (currentStepIndex < practiceData[currentExerciseIndex].length - 1) {
                    currentStepIndex++;
                    show();
                } else if (currentExerciseIndex < practiceData.length - 1) {
                    currentExerciseIndex++;
                    currentStepIndex = 0;
                    show();
                } else {
                    // Finished all exercises
                    showCompletionScreen(contentLayout);
                }
            } else {
                feedbackLabel.setText("Respuesta incorrecta. Inténtalo de nuevo.");
                feedbackLabel.setTextFill(javafx.scene.paint.Color.RED);
            }
        });

        card.getChildren().addAll(qLabel, optionsBox, feedbackLabel, checkButton);
        mainContent.getChildren().add(card);

        contentLayout.getChildren().addAll(header, mainContent);
        return contentLayout;
    }

    private void showCompletionScreen(VBox contentLayout) {
        contentLayout.getChildren().clear();
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox completionBox = new VBox(25);
        completionBox.setAlignment(Pos.CENTER);
        completionBox.setPadding(new Insets(50));

        Text emoji = new Text("🏆");
        emoji.setFont(Font.font(60));

        Text title = new Text("¡Felicidades!");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
        title.setFill(AppConstants.TITLE_COLOR);

        Text message = new Text("Has completado exitosamente toda la sección de Límite de una Función.");
        message.setFont(Font.font("Segoe UI", 18));
        message.setFill(AppConstants.TEXT_COLOR);
        message.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Button menuButton = new Button("Volver al Menú Principal");
        menuButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + "; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-background-radius: 25; -fx-padding: 15 40; -fx-font-size: 18; " +
                "-fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");
        menuButton.setOnAction(e -> viewManager.showLimitesMenu());

        completionBox.getChildren().addAll(emoji, title, message, menuButton);
        contentLayout.getChildren().add(completionBox);
    }

    public void show() {
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(createView());
    }
}
