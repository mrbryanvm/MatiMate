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

public class EjerciciosAplicacionesDerivadaView {
    private ViewManager viewManager;
    private AplicacionesDerivadaView parentView;

    public EjerciciosAplicacionesDerivadaView(ViewManager viewManager, AplicacionesDerivadaView parentView) {
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
        VBox header = UIComponents.createHeader("Ejercicios: Aplicaciones de la Derivada", "Practica lo aprendido");

        // Exercises
        VBox exercisesContainer = new VBox(15);
        exercisesContainer.getChildren().addAll(
                createExerciseItem(
                        "Ejercicio 1",
                        "Encuentra los puntos críticos de f(x) = x³ - 3x².",
                        "f'(x) = 3x² - 6x. Igualamos a 0: 3x(x - 2) = 0. Puntos críticos: x = 0, x = 2."),
                createExerciseItem(
                        "Ejercicio 2",
                        "Determina si f(x) = x² tiene un máximo o mínimo en x = 0.",
                        "f'(x) = 2x. f''(x) = 2. Como f''(0) = 2 > 0, es un mínimo local."),
                createExerciseItem(
                        "Ejercicio 3",
                        "La posición de una partícula es s(t) = t³ - 3t. Halla su velocidad en t = 2.",
                        "v(t) = s'(t) = 3t² - 3. v(2) = 3(2)² - 3 = 12 - 3 = 9 m/s."),
                createExerciseItem(
                        "Ejercicio 4",
                        "Encuentra dos números cuya suma sea 10 y su producto sea máximo.",
                        "x + y = 10 => y = 10 - x. P = x(10 - x) = 10x - x². P' = 10 - 2x. 10 - 2x = 0 => x = 5. Los números son 5 y 5."),
                createExerciseItem(
                        "Ejercicio 5",
                        "Halla la aceleración de una partícula con velocidad v(t) = 4t² + 1.",
                        "a(t) = v'(t) = 8t."));

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
