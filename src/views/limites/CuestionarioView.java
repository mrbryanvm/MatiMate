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
import views.ViewManager;

public class CuestionarioView {
    private ViewManager viewManager;
    private LimitsFlowManager flowManager;
    private int currentQuestionIndex = 0;
    private String[][] questions;

    // Seguimiento si la pregunta actual fue respondida incorrectamente al menos una
    // vez
    private boolean answeredWronglyOnce = false;

    public CuestionarioView(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.flowManager = new LimitsFlowManager(viewManager);
        this.questions = LimitsContentProvider.getQuizQuestions(LimitsContext.getInstance().getCurrentTopic());
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
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                answeredWronglyOnce = false;
                show();
            } else {
                flowManager.showVideo();
            }
        });

        VBox headerContent = new VBox(3);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("CUESTIONARIO (" + (currentQuestionIndex + 1) + "/" + questions.length + ")");
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

        // Contenido principal
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40));
        mainContent.setAlignment(Pos.TOP_CENTER);

        // Tarjeta de pregunta
        VBox card = new VBox(20);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        card.setPadding(new Insets(30));
        card.setMaxWidth(600);

        if (questions.length == 0) {
            Label errorLabel = new Label("No hay preguntas para este tema.");
            card.getChildren().add(errorLabel);
        } else {
            String[] qData = questions[currentQuestionIndex];
            String questionText = qData[0];
            int correctIndex = Integer.parseInt(qData[4]);
            int pointsValue = Integer.parseInt(qData[5]); // Get explicit points

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

            for (int i = 1; i <= 3; i++) {
                RadioButton rb = new RadioButton(qData[i]);
                rb.setToggleGroup(group);
                rb.setFont(Font.font("Segoe UI", 16));
                rb.setTextFill(AppConstants.TEXT_COLOR);
                rb.setWrapText(true);
                rb.setUserData(i - 1);
                optionsBox.getChildren().add(rb);
            }

            Label feedbackLabel = new Label("");
            feedbackLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));

            Button checkButton = new Button("Siguiente");
            checkButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + "; -fx-text-fill: white; "
                    +
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
                    // Solo agregra puntos si no se respondio mal
                    if (!answeredWronglyOnce) {
                        LimitsContext.getInstance().addScore(pointsValue);
                    }

                    if (currentQuestionIndex < questions.length - 1) {
                        currentQuestionIndex++;
                        answeredWronglyOnce = false; // Reset
                        show();
                    } else {
                        flowManager.showExercises();
                    }
                } else {
                    if (!answeredWronglyOnce) {
                        LimitsContext.getInstance().penalize();
                        answeredWronglyOnce = true;
                    }
                    feedbackLabel.setText("Respuesta incorrecta (-1 pto). Inténtalo de nuevo.");
                    feedbackLabel.setTextFill(javafx.scene.paint.Color.RED);
                }
            });

            card.getChildren().addAll(titleBox, optionsBox, feedbackLabel, checkButton);
        }
        mainContent.getChildren().add(card);

        // Boton menu
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
