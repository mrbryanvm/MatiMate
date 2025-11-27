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

public class EjerciciosReglasDerivacionView {
    private ViewManager viewManager;
    private ReglasDerivacionView parentView;

    public EjerciciosReglasDerivacionView(ViewManager viewManager, ReglasDerivacionView parentView) {
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
        VBox header = UIComponents.createHeader("Ejercicios: Reglas de Derivación", "Practica lo aprendido");

        // Exercises
        VBox exercisesContainer = new VBox(15);
        exercisesContainer.getChildren().addAll(
                createExerciseItem(
                        "Ejercicio 1",
                        "Deriva f(x) = 5x³ - 2x² + 7.",
                        "Usando la regla de la potencia: f'(x) = 5(3x²) - 2(2x) + 0 = 15x² - 4x."),
                createExerciseItem(
                        "Ejercicio 2",
                        "Deriva f(x) = x² · sin(x).",
                        "Usando la regla del producto: f'(x) = (x²)'sin(x) + x²(sin(x))' = 2x·sin(x) + x²·cos(x)."),
                createExerciseItem(
                        "Ejercicio 3",
                        "Deriva f(x) = (3x + 1) / x.",
                        "Podemos reescribir como f(x) = 3 + x⁻¹. Entonces f'(x) = 0 - 1·x⁻² = -1/x². O usando la regla del cociente: [(3)(x) - (3x+1)(1)] / x² = [3x - 3x - 1] / x² = -1/x²."),
                createExerciseItem(
                        "Ejercicio 4",
                        "Deriva f(x) = √x + 1/x.",
                        "Reescribiendo: f(x) = x^(1/2) + x⁻¹. f'(x) = (1/2)x^(-1/2) - x⁻² = 1/(2√x) - 1/x²."),
                createExerciseItem(
                        "Ejercicio 5",
                        "Deriva f(x) = eˣ + ln(x).",
                        "La derivada de eˣ es eˣ, y la de ln(x) es 1/x. Entonces f'(x) = eˣ + 1/x."));

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
