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

public class EjerciciosLateralesView {
        private ViewManager viewManager;
        private LimitesLateralesView parentView;
        private int currentExerciseIndex = 0;

        // Data for exercises
        private final String[][] exercises = {
                        {
                                        "Ejercicio 1",
                                        "Calcular el límite lateral: lim(x→2⁺) (x² - 4)/(x - 2)",
                                        "Paso 1: Evaluar directamente (x = 2).\n" +
                                                        "   (2² - 4)/(2 - 2) = 0/0 (Indeterminación)\n\n" +
                                                        "Paso 2: Factorizar el numerador.\n" +
                                                        "   x² - 4 = (x - 2)(x + 2)\n\n" +
                                                        "Paso 3: Simplificar la expresión.\n" +
                                                        "   lim(x→2⁺) [(x - 2)(x + 2)] / (x - 2)\n" +
                                                        "   = lim(x→2⁺) (x + 2)\n\n" +
                                                        "Paso 4: Evaluar el límite simplificado.\n" +
                                                        "   = 2 + 2 = 4\n\n" +
                                                        "Resultado: El límite lateral derecho es 4."
                        },
                        {
                                        "Ejercicio 2",
                                        "Calcular el límite lateral: lim(x→0⁻) |x|/x",
                                        "Paso 1: Analizar el valor absoluto para x < 0.\n" +
                                                        "   Si x < 0, entonces |x| = -x\n\n" +
                                                        "Paso 2: Sustituir en la función.\n" +
                                                        "   lim(x→0⁻) (-x)/x\n\n" +
                                                        "Paso 3: Simplificar la expresión.\n" +
                                                        "   = lim(x→0⁻) -1\n\n" +
                                                        "Paso 4: El límite de una constante es la constante misma.\n" +
                                                        "   = -1\n\n" +
                                                        "Resultado: El límite lateral izquierdo es -1."
                        }
        };

        public EjerciciosLateralesView(ViewManager viewManager, LimitesLateralesView parentView) {
                this.viewManager = viewManager;
                this.parentView = parentView;
        }

        public void show() {
                viewManager.getRoot().getChildren().clear();
                viewManager.getRoot().getChildren().add(createView());
        }

        private VBox createView() {
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

                Text subtitle = new Text("Límites Laterales");
                subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
                subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

                headerContent.getChildren().addAll(title, subtitle);

                BorderPane headerLayout = new BorderPane();
                headerLayout.setLeft(backButton);
                headerLayout.setCenter(headerContent);
                header.getChildren().add(headerLayout);

                // Main Content
                VBox mainContent = new VBox(20);
                mainContent.setPadding(new Insets(30));
                mainContent.setAlignment(Pos.TOP_CENTER);
                mainContent.setBackground(
                                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY,
                                                Insets.EMPTY)));

                // Current Exercise Content
                VBox exerciseCard = createExerciseCard(currentExerciseIndex);
                mainContent.getChildren().add(exerciseCard);

                // Navigation Buttons
                HBox navigationBox = new HBox(20);
                navigationBox.setAlignment(Pos.CENTER);
                navigationBox.setPadding(new Insets(20, 0, 0, 0));

                if (currentExerciseIndex < exercises.length - 1) {
                        Button nextButton = new Button("Siguiente Ejercicio →");
                        nextButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX
                                        + "; -fx-text-fill: white; " +
                                        "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 12 25; -fx-font-size: 14; "
                                        +
                                        "-fx-cursor: hand;");
                        nextButton.setOnAction(e -> {
                                currentExerciseIndex++;
                                show();
                        });
                        navigationBox.getChildren().add(nextButton);
                } else {
                        Button finishButton = new Button("Volver a la sección principal");
                        finishButton.setStyle("-fx-background-color: #4A5568; -fx-text-fill: white; " +
                                        "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 12 25; -fx-font-size: 14; "
                                        +
                                        "-fx-cursor: hand;");
                        finishButton.setOnAction(e -> parentView.show());
                        navigationBox.getChildren().add(finishButton);
                }

                mainContent.getChildren().add(navigationBox);

                ScrollPane scrollContent = new ScrollPane(mainContent);
                scrollContent.setFitToWidth(true);
                scrollContent.setStyle("-fx-background: transparent; -fx-background-color: transparent; " +
                                "-fx-border-color: transparent; -fx-padding: 0;");

                contentLayout.getChildren().addAll(header, scrollContent);

                return contentLayout;
        }

        private VBox createExerciseCard(int index) {
                VBox card = new VBox(15);
                card.setStyle("-fx-background-color: white; -fx-background-radius: 10; " +
                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
                card.setPadding(new Insets(30));
                card.setMaxWidth(800);

                String[] data = exercises[index];
                String title = data[0];
                String problem = data[1];
                String solution = data[2];

                Label titleLabel = new Label(title);
                titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
                titleLabel.setTextFill(AppConstants.PRIMARY_COLOR);

                Label problemLabel = new Label(problem);
                problemLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
                problemLabel.setTextFill(AppConstants.TEXT_COLOR);
                problemLabel.setWrapText(true);

                Separator separator = new Separator();
                separator.setPadding(new Insets(10, 0, 10, 0));

                Label solutionTitle = new Label("Solución paso a paso:");
                solutionTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
                solutionTitle.setTextFill(AppConstants.TITLE_COLOR);

                TextArea solutionText = new TextArea(solution);
                solutionText.setEditable(false);
                solutionText.setWrapText(true);
                solutionText.setPrefHeight(250);
                solutionText.setStyle("-fx-font-family: 'Consolas', 'Monospaced'; -fx-font-size: 14; " +
                                "-fx-control-inner-background: #F7FAFC; -fx-background-color: #F7FAFC; -fx-border-color: transparent;");

                card.getChildren().addAll(titleLabel, problemLabel, separator, solutionTitle, solutionText);
                return card;
        }
}
