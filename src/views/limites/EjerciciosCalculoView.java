package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import views.ViewManager;

public class EjerciciosCalculoView {
        private ViewManager viewManager;
        private CalculoLimitesView parentView;

        public EjerciciosCalculoView(ViewManager viewManager, CalculoLimitesView parentView) {
                this.viewManager = viewManager;
                this.parentView = parentView;
        }

        public VBox createView() {
                VBox contentLayout = new VBox(0);
                contentLayout.setBackground(
                                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY,
                                                Insets.EMPTY)));

                // Header
                StackPane header = new StackPane();
                header.setPadding(new Insets(35, 20, 25, 20));
                header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

                Button backButton = new Button("←");
                backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #184093; -fx-font-size: 20; " +
                                "-fx-font-weight: bold; -fx-border-color: transparent; -fx-cursor: hand;");
                backButton.setOnAction(e -> parentView.show());

                VBox headerContent = new VBox(3);
                headerContent.setAlignment(Pos.CENTER);

                Text title = new Text("SERIE DE EJERCICIOS");
                title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
                title.setFill(AppConstants.TITLE_COLOR);

                Text subtitle = new Text("Cálculo de límites");
                subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
                subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

                headerContent.getChildren().addAll(title, subtitle);

                BorderPane headerLayout = new BorderPane();
                headerLayout.setLeft(backButton);
                headerLayout.setCenter(headerContent);
                header.getChildren().add(headerLayout);

                // ScrollPane with exercises
                ScrollPane scrollContent = new ScrollPane();
                scrollContent.setFitToWidth(true);
                scrollContent.setStyle("-fx-background: transparent; -fx-background-color: transparent; " +
                                "-fx-border-color: transparent; -fx-padding: 0;");

                VBox mainContent = new VBox(15);
                mainContent.setPadding(new Insets(20));
                mainContent.setBackground(
                                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY,
                                                Insets.EMPTY)));

                // Agregar 5 ejercicios
                mainContent.getChildren().addAll(
                                createExercise(1, "Calcular lim(x→2) (x² + 3x - 1)", "9 (Sustitución directa)"),
                                createExercise(2, "Calcular lim(x→3) (x²-9)/(x-3)",
                                                "6 (Factorización: (x-3)(x+3)/(x-3))"),
                                createExercise(3, "Calcular lim(x→0) (√(x+4)-2)/x", "1/4 (Racionalización)"),
                                createExercise(4, "Calcular lim(x→1) (x³-1)/(x-1)",
                                                "3 (Factorización: (x-1)(x²+x+1)/(x-1))"),
                                createExercise(5, "Calcular lim(x→-2) (x+2)/(x²-4)",
                                                "-1/4 (Factorización: (x+2)/((x-2)(x+2)))"));

                scrollContent.setContent(mainContent);
                contentLayout.getChildren().addAll(header, scrollContent);

                return contentLayout;
        }

        private VBox createExercise(int number, String problem, String result) {
                VBox exerciseBox = new VBox(10);
                exerciseBox.setStyle("-fx-background-color: white; -fx-background-radius: 10; " +
                                "-fx-border-color: #E2E8F0; -fx-border-width: 1; -fx-border-radius: 10;");
                exerciseBox.setPadding(new Insets(20));

                Label exerciseLabel = new Label("Ejercicio " + number);
                exerciseLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
                exerciseLabel.setTextFill(AppConstants.TITLE_COLOR);

                Label problemLabel = new Label(problem);
                problemLabel.setFont(Font.font("Segoe UI", 14));
                problemLabel.setTextFill(AppConstants.TEXT_COLOR);
                problemLabel.setWrapText(true);

                // Result section (initially hidden)
                VBox resultBox = new VBox(5);
                resultBox.setStyle("-fx-background-color: #F7FAFC; -fx-background-radius: 5; -fx-padding: 10;");
                resultBox.setVisible(false);
                resultBox.setManaged(false);

                Label resultLabel = new Label("Resultado:");
                resultLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
                resultLabel.setTextFill(AppConstants.LIGHT_TEXT);

                Label resultValue = new Label(result);
                resultValue.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
                resultValue.setTextFill(AppConstants.PRIMARY_COLOR);

                resultBox.getChildren().addAll(resultLabel, resultValue);

                // Toggle button
                Button toggleButton = new Button("Ver Resultado");
                toggleButton.setStyle("-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; " +
                                "-fx-background-radius: 5; -fx-padding: 8 15; -fx-font-size: 12;");

                toggleButton.setOnAction(e -> {
                        boolean isVisible = resultBox.isVisible();
                        resultBox.setVisible(!isVisible);
                        resultBox.setManaged(!isVisible);
                        toggleButton.setText(isVisible ? "Ver Resultado" : "Ocultar Resultado");
                        toggleButton.setStyle(isVisible
                                        ? "-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; "
                                                        +
                                                        "-fx-background-radius: 5; -fx-padding: 8 15; -fx-font-size: 12;"
                                        : "-fx-background-color: #E53E3E; -fx-text-fill: white; -fx-font-weight: bold; "
                                                        +
                                                        "-fx-background-radius: 5; -fx-padding: 8 15; -fx-font-size: 12;");
                });

                exerciseBox.getChildren().addAll(exerciseLabel, problemLabel, toggleButton, resultBox);

                return exerciseBox;
        }

        public void show() {
                viewManager.getRoot().getChildren().clear();
                viewManager.getRoot().getChildren().add(createView());
        }
}
