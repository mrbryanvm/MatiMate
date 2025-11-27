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

public class EjerciciosOrdenSuperiorView {
    private ViewManager viewManager;
    private OrdenSuperiorView parentView;

    public EjerciciosOrdenSuperiorView(ViewManager viewManager, OrdenSuperiorView parentView) {
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
        VBox header = UIComponents.createHeader("Ejercicios: Derivadas de Orden Superior", "Practica lo aprendido");

        // Exercises
        VBox exercisesContainer = new VBox(15);
        exercisesContainer.getChildren().addAll(
                createExerciseItem(
                        "Ejercicio 1",
                        "Halla la segunda derivada de f(x) = x³ - 5x² + 2.",
                        "f'(x) = 3x² - 10x.\n" +
                                "f''(x) = 6x - 10."),
                createExerciseItem(
                        "Ejercicio 2",
                        "Halla la tercera derivada de f(x) = sin(x).",
                        "f'(x) = cos(x).\n" +
                                "f''(x) = -sin(x).\n" +
                                "f'''(x) = -cos(x)."),
                createExerciseItem(
                        "Ejercicio 3",
                        "Halla la segunda derivada de f(x) = e^(2x).",
                        "f'(x) = 2e^(2x).\n" +
                                "f''(x) = 4e^(2x)."),
                createExerciseItem(
                        "Ejercicio 4",
                        "Halla la cuarta derivada de f(x) = x⁴.",
                        "f'(x) = 4x³.\n" +
                                "f''(x) = 12x².\n" +
                                "f'''(x) = 24x.\n" +
                                "f⁽⁴⁾(x) = 24."),
                createExerciseItem(
                        "Ejercicio 5",
                        "Halla la segunda derivada de f(x) = ln(x).",
                        "f'(x) = 1/x = x⁻¹.\n" +
                                "f''(x) = -x⁻² = -1/x²."));

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
