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

public class ExerciseSeriesView {
    private ViewManager viewManager;
    private LimitsFlowManager flowManager;
    private int currentExerciseIndex = 0;
    private String[][] exercises;

    public ExerciseSeriesView(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.flowManager = new LimitsFlowManager(viewManager);
        this.exercises = LimitsContentProvider.getExercises(LimitsContext.getInstance().getCurrentTopic());
    }

    public VBox createView() {
        VBox contentLayout = new VBox(0);
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        String[] exerciseData = exercises[currentExerciseIndex];
        String exerciseTitle = exerciseData[0];
        String problemStatement = exerciseData[1];
        String solutionSteps = exerciseData[2];

        // Header
        StackPane header = new StackPane();
        header.setPadding(new Insets(35, 20, 25, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

        Button backButton = new Button("←");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #184093; -fx-font-size: 20; " +
                "-fx-font-weight: bold; -fx-border-color: transparent; -fx-cursor: hand;");
        backButton.setOnAction(e -> {
            if (currentExerciseIndex > 0) {
                currentExerciseIndex--;
                show();
            } else {
                flowManager.showQuiz();
            }
        });

        VBox headerContent = new VBox(3);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("SERIE DE EJERCICIOS");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text(exerciseTitle);
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

        // Exercise Card
        VBox card = new VBox(20);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        card.setPadding(new Insets(30));
        card.setMaxWidth(700);

        Label problemLabel = new Label("Problema:");
        problemLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        problemLabel.setTextFill(AppConstants.TITLE_COLOR);

        Label problemText = new Label(problemStatement);
        problemText.setFont(Font.font("Segoe UI", 16));
        problemText.setWrapText(true);
        problemText.setTextFill(AppConstants.TEXT_COLOR);

        Separator sep = new Separator();

        Label solutionLabel = new Label("Solución:");
        solutionLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        solutionLabel.setTextFill(AppConstants.TITLE_COLOR);

        TextArea solutionArea = new TextArea(solutionSteps);
        solutionArea.setEditable(false);
        solutionArea.setWrapText(true);
        solutionArea.setPrefHeight(150);
        solutionArea.setStyle(
                "-fx-font-size: 14; -fx-background-color: transparent; -fx-control-inner-background: #F7FAFC;");

        card.getChildren().addAll(problemLabel, problemText, sep, solutionLabel, solutionArea);

        // Botones de Navegación
        HBox navButtons = new HBox(20);
        navButtons.setAlignment(Pos.CENTER);

        Button nextButton = new Button(
                currentExerciseIndex < exercises.length - 1 ? "Siguiente Ejercicio" : "Ir a Práctica");
        nextButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + "; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 12 30; -fx-font-size: 16; " +
                "-fx-cursor: hand;");

        nextButton.setOnAction(e -> {
            if (currentExerciseIndex < exercises.length - 1) {
                currentExerciseIndex++;
                show();
            } else {
                flowManager.showPractice();
            }
        });

        navButtons.getChildren().add(nextButton);
        mainContent.getChildren().addAll(card, navButtons);

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
