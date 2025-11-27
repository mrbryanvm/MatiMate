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

public class EjerciciosPropiedadesView {
        private ViewManager viewManager;
        private PropiedadesLimitesView parentView;
        private int currentExerciseIndex = 0;

        // Data for exercises
        private final String[][] exercises = {
                        {
                                        "Ejercicio 1",
                                        "Si lim(x→2) f(x) = 5 y lim(x→2) g(x) = 3, calcular: lim(x→2) [f(x) + g(x)]",
                                        "Paso 1: Identificar la propiedad de la suma.\n" +
                                                        "   lim(x→a) [f(x) + g(x)] = lim(x→a) f(x) + lim(x→a) g(x)\n\n"
                                                        +
                                                        "Paso 2: Sustituir los valores conocidos.\n" +
                                                        "   lim(x→2) f(x) = 5\n" +
                                                        "   lim(x→2) g(x) = 3\n\n" +
                                                        "Paso 3: Realizar la suma.\n" +
                                                        "   = 5 + 3\n" +
                                                        "   = 8\n\n" +
                                                        "Resultado: El límite de la suma es 8."
                        },
                        {
                                        "Ejercicio 2",
                                        "Calcular usando propiedades: lim(x→2) [5·(x² + 1)]",
                                        "Paso 1: Identificar la propiedad de constante por función.\n" +
                                                        "   lim(x→a) [c · f(x)] = c · lim(x→a) f(x)\n\n" +
                                                        "Paso 2: Sacar la constante 5 fuera del límite.\n" +
                                                        "   = 5 · lim(x→2) (x² + 1)\n\n" +
                                                        "Paso 3: Calcular el límite de la función (x² + 1).\n" +
                                                        "   lim(x→2) (x² + 1) = 2² + 1 = 4 + 1 = 5\n\n" +
                                                        "Paso 4: Multiplicar por la constante.\n" +
                                                        "   = 5 · 5\n" +
                                                        "   = 25\n\n" +
                                                        "Resultado: El límite es 25."
                        }
        };

        public EjerciciosPropiedadesView(ViewManager viewManager, PropiedadesLimitesView parentView) {
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

                Text subtitle = new Text("Propiedades de Límites");
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
