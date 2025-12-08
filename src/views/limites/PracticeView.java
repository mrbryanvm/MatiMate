package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.LimitsContentProvider;
import utils.LimitsContext;
import utils.LimitsContext.UserAnswer;
import views.ViewManager;

public class PracticeView {
    private ViewManager viewManager;
    private LimitsFlowManager flowManager;
    private int currentExerciseIndex = 0;
    private int currentStepIndex = 0;
    private String[][][] practiceData;

    // Rastrear si el paso actual fue respondido incorrectamente al menos una vez
    // (para mostrar en las estadísticas como
    // incorrecto inicialmente)
    private boolean currentStepAnsweredWrongly = false;

    public PracticeView(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.flowManager = new LimitsFlowManager(viewManager);
        this.practiceData = LimitsContentProvider.getPracticeSteps(LimitsContext.getInstance().getCurrentTopic());
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
                currentStepAnsweredWrongly = false;
                show();
            } else if (currentExerciseIndex > 0) {
                currentExerciseIndex--;
                currentStepIndex = practiceData[currentExerciseIndex].length - 1;
                currentStepAnsweredWrongly = false;
                show();
            } else {
                flowManager.showExercises();
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

        // Contenido Principal
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40));
        mainContent.setAlignment(Pos.TOP_CENTER);

        if (practiceData.length == 0) {
            Label errorLabel = new Label("No hay ejercicios de práctica para este tema.");
            mainContent.getChildren().add(errorLabel);
            contentLayout.getChildren().addAll(header, mainContent);
            return contentLayout;
        }

        // Tarjeta de Pregunta
        VBox card = new VBox(20);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        card.setPadding(new Insets(30));
        card.setMaxWidth(600);

        String[] stepData = practiceData[currentExerciseIndex][currentStepIndex];
        String questionText = stepData[0];
        int correctIndex = Integer.parseInt(stepData[5]);
        int pointsValue = Integer.parseInt(stepData[6]); // Puntos explícitos

        HBox titleBox = new HBox(10);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Label qLabel = new Label(questionText);
        qLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        qLabel.setWrapText(true);
        qLabel.setTextFill(AppConstants.TEXT_COLOR);
        qLabel.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(qLabel, Priority.ALWAYS);

        Label pointsLabel = new Label("(" + pointsValue + " pts)");
        pointsLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        pointsLabel.setTextFill(AppConstants.PRIMARY_COLOR);
        pointsLabel.setMinWidth(Region.USE_PREF_SIZE);

        titleBox.getChildren().addAll(qLabel, pointsLabel);

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
            String selectedText = ((RadioButton) selected).getText();
            String correctText = stepData[correctIndex + 1];

            if (selectedIndex == correctIndex) {
                if (!currentStepAnsweredWrongly) {
                    // Correcto en la primera vez
                    LimitsContext.getInstance().addScore(pointsValue);
                    LimitsContext.getInstance().addPracticeAnswer(
                            new UserAnswer(questionText, selectedText, correctText, true, pointsValue));
                } else {
                    // Fue incorrecto antes, ahora correcto.
                    // IMPORTANT: No registramos un nuevo "Correcto" para las estadísticas si ya
                    // registramos un incorrecto.
                }

                if (currentStepIndex < practiceData[currentExerciseIndex].length - 1) {
                    currentStepIndex++;
                    currentStepAnsweredWrongly = false; // Reset for next step
                    show();
                } else if (currentExerciseIndex < practiceData.length - 1) {
                    currentExerciseIndex++;
                    currentStepIndex = 0;
                    currentStepAnsweredWrongly = false;
                    show();
                } else {
                    // Terminó todos los ejercicios -> Estadísticas
                    flowManager.showStatistics();
                }
            } else {
                // Incorrecto
                feedbackLabel.setText("Respuesta incorrecta (-1 pto). Inténtalo de nuevo.");
                feedbackLabel.setTextFill(javafx.scene.paint.Color.RED);

                if (!currentStepAnsweredWrongly) {
                    // Primera respuesta incorrecta: penalizar y registrar
                    LimitsContext.getInstance().penalize();
                    LimitsContext.getInstance().addPracticeAnswer(
                            new UserAnswer(questionText, selectedText, correctText, false, -1));
                    currentStepAnsweredWrongly = true;
                }
            }
        });

        card.getChildren().addAll(titleBox, optionsBox, feedbackLabel, checkButton);
        mainContent.getChildren().add(card);

        // Botón Menú
        Button menuButton = new Button("Volver al Menú");
        menuButton.setStyle("-fx-background-color: #718096; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 10 20;");
        menuButton.setOnAction(e -> viewManager.showLimitesMenu());
        mainContent.getChildren().add(menuButton);

        contentLayout.getChildren().addAll(header, mainContent);
        return contentLayout;
    }

    public void show() {
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(createView());
    }
}
