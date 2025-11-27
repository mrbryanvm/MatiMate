package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.derivadas.OrdenSuperiorView;

public class CuestionarioOrdenSuperiorView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private OrdenSuperiorView parentView;

    public CuestionarioOrdenSuperiorView(ViewManager viewManager, OrdenSuperiorView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader("Cuestionario: Derivadas de Orden Superior", "Pon a prueba tus conocimientos",
                () -> {
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(parentView.createView());
                });

        VBox preguntasContainer = new VBox(20);
        preguntasContainer.setPadding(new Insets(20));

        preguntasContainer.getChildren().addAll(
                createPregunta(
                        "1. ¿Qué es la segunda derivada?",
                        new String[] {
                                "A) El cuadrado de la derivada.",
                                "B) La derivada de la primera derivada.",
                                "C) La integral de la función.",
                                "D) La inversa de la derivada."
                        },
                        "B"),
                createPregunta(
                        "2. Si f(x) = x³, ¿cuál es f''(x)?",
                        new String[] {
                                "A) 3x²",
                                "B) 6x",
                                "C) 6",
                                "D) 0"
                        },
                        "B"),
                createPregunta(
                        "3. Si la segunda derivada es positiva, la función es:",
                        new String[] {
                                "A) Cóncava hacia arriba (convexa).",
                                "B) Cóncava hacia abajo.",
                                "C) Decreciente.",
                                "D) Lineal."
                        },
                        "A"),
                createPregunta(
                        "4. ¿Cuál es la derivada 50 de sin(x)?",
                        new String[] {
                                "A) sin(x)",
                                "B) cos(x)",
                                "C) -sin(x)",
                                "D) -cos(x)"
                        },
                        "C"),
                createPregunta(
                        "5. Si f(x) es un polinomio de grado n, su derivada n+1 es:",
                        new String[] {
                                "A) Una constante",
                                "B) 0",
                                "C) x",
                                "D) n!"
                        },
                        "B"));

        utils.UIComponents.addCheckButton(preguntasContainer,
                () -> mostrarResultado(preguntasContainer, "Derivadas de Orden Superior", () -> {
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(parentView.createView());
                }));

        ScrollPane scrollPane = new ScrollPane(preguntasContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        contentLayout.getChildren().add(scrollPane);

        return contentLayout;
    }
}
