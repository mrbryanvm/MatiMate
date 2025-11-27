package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.funciones.ComposicionFuncionesView;

public class CuestionarioComposicionFuncionesView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private ComposicionFuncionesView parentView;

    public CuestionarioComposicionFuncionesView(ViewManager viewManager, ComposicionFuncionesView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader("Cuestionario: Composición de Funciones", "Pon a prueba tus conocimientos",
                () -> {
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(parentView.createView());
                });

        VBox preguntasContainer = new VBox(20);
        preguntasContainer.setPadding(new Insets(20));

        preguntasContainer.getChildren().addAll(
                createPregunta(
                        "1. ¿Qué es (f ∘ g)(x)?",
                        new String[] {
                                "A) f(x) * g(x)",
                                "B) f(g(x))",
                                "C) g(f(x))",
                                "D) f(x) + g(x)"
                        },
                        "B"),
                createPregunta(
                        "2. Si f(x) = 2x y g(x) = x + 3, ¿cuánto es (f ∘ g)(1)?",
                        new String[] {
                                "A) 5",
                                "B) 6",
                                "C) 8",
                                "D) 4"
                        },
                        "C"),
                createPregunta(
                        "3. ¿Es la composición de funciones conmutativa?",
                        new String[] {
                                "A) Sí, siempre.",
                                "B) No, en general (f ∘ g) ≠ (g ∘ f).",
                                "C) Solo si las funciones son lineales.",
                                "D) Solo si las funciones son idénticas."
                        },
                        "B"),
                createPregunta(
                        "4. Si f(x) = x² y g(x) = √x, ¿cuál es el dominio de (f ∘ g)(x)?",
                        new String[] {
                                "A) R",
                                "B) [0, ∞)",
                                "C) (-∞, 0]",
                                "D) R - {0}"
                        },
                        "B"),
                createPregunta(
                        "5. Si f(g(x)) = x para todo x, entonces g es:",
                        new String[] {
                                "A) La inversa de f",
                                "B) La derivada de f",
                                "C) El cuadrado de f",
                                "D) Una constante"
                        },
                        "A"));

        utils.UIComponents.addCheckButton(preguntasContainer,
                () -> mostrarResultado(preguntasContainer, "Composición de Funciones", () -> {
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
