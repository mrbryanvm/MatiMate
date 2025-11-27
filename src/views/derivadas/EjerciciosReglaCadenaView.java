package views.derivadas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.UIComponents;
import views.ViewManager;

public class EjerciciosReglaCadenaView {
    private ViewManager viewManager;
    private ReglaCadenaView parentView;

    public EjerciciosReglaCadenaView(ViewManager viewManager, ReglaCadenaView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = new VBox(20);
        contentLayout.setPadding(new Insets(30));
        contentLayout.setAlignment(Pos.TOP_CENTER);
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        // Header
        VBox header = UIComponents.createHeader("Ejercicios: Regla de la Cadena", "Practica lo aprendido");

        // Exercises
        VBox exercisesContainer = new VBox(15);
        exercisesContainer.getChildren().addAll(
                createExerciseItem(
                        "Ejercicio 1",
                        "Deriva f(x) = (2x + 1)³.",
                        "f'(x) = 3(2x + 1)² · (2) = 6(2x + 1)²."),
                createExerciseItem(
                        "Ejercicio 2",
                        "Deriva f(x) = sin(x²).",
                        "f'(x) = cos(x²) · (2x) = 2x·cos(x²)."),
                createExerciseItem(
                        "Ejercicio 3",
                        "Deriva f(x) = e^(3x).",
                        "f'(x) = e^(3x) · (3) = 3e^(3x)."),
                createExerciseItem(
                        "Ejercicio 4",
                        "Deriva f(x) = ln(x² + 1).",
                        "f'(x) = [1/(x² + 1)] · (2x) = 2x / (x² + 1)."),
                createExerciseItem(
                        "Ejercicio 5",
                        "Deriva f(x) = √(x³ + 2).",
                        "f(x) = (x³ + 2)^(1/2). f'(x) = (1/2)(x³ + 2)^(-1/2) · (3x²) = 3x² / [2√(x³ + 2)]."));

        // Back Button
        Button backBtn = UIComponents.createSecondaryButton("⬅ Volver");
        backBtn.setOnAction(e -> {
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(parentView.createView());
        });

        contentLayout.getChildren().addAll(header, exercisesContainer, backBtn);

        ScrollPane scrollPane = new ScrollPane(contentLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox mainContainer = new VBox(scrollPane);
        mainContainer.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        return mainContainer;
    }

    private VBox createExerciseItem(String title, String problem, String solution) {
        VBox exerciseBox = new VBox(10);
        exerciseBox.setPadding(new Insets(15));
        exerciseBox.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        titleLabel.setTextFill(AppConstants.PRIMARY_COLOR);

        Text problemText = new Text(problem);
        problemText.setFont(Font.font("Segoe UI", 14));
        problemText.setFill(AppConstants.TEXT_COLOR);
        problemText.setWrappingWidth(700);

        VBox solutionBox = new VBox(10);
        solutionBox.setVisible(false);
        solutionBox.setManaged(false);
        solutionBox.setPadding(new Insets(10));
        solutionBox.setStyle("-fx-background-color: #f0f8ff; -fx-background-radius: 5;");

        Text solutionText = new Text("Solución: " + solution);
        solutionText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        solutionText.setFill(AppConstants.TEXT_COLOR);
        solutionText.setWrappingWidth(680);

        solutionBox.getChildren().add(solutionText);

        Button toggleBtn = new Button("Ver Solución");
        toggleBtn.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        toggleBtn.setOnAction(e -> {
            boolean isVisible = solutionBox.isVisible();
            solutionBox.setVisible(!isVisible);
            solutionBox.setManaged(!isVisible);
            toggleBtn.setText(isVisible ? "Ver Solución" : "Ocultar Solución");
        });

        exerciseBox.getChildren().addAll(titleLabel, problemText, toggleBtn, solutionBox);
        return exerciseBox;
    }
}
