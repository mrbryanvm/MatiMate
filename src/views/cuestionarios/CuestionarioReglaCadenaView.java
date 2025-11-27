package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.derivadas.ReglaCadenaView;

public class CuestionarioReglaCadenaView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private ReglaCadenaView parentView;

    public CuestionarioReglaCadenaView(ViewManager viewManager, ReglaCadenaView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader("Cuestionario: Regla de la Cadena", "Pon a prueba tus conocimientos",
                () -> {
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(parentView.createView());
                });

        VBox preguntasContainer = new VBox(20);
        preguntasContainer.setPadding(new Insets(20));

        preguntasContainer.getChildren().addAll(
                createPregunta(
                        "1. ¿Para qué se usa la regla de la cadena?",
                        new String[] {
                                "A) Para sumar funciones.",
                                "B) Para derivar funciones compuestas.",
                                "C) Para integrar funciones.",
                                "D) Para multiplicar funciones."
                        },
                        "B"),
                createPregunta(
                        "2. Si y = f(g(x)), entonces y' es:",
                        new String[] {
                                "A) f'(g(x))",
                                "B) f'(x)g'(x)",
                                "C) f'(g(x))g'(x)",
                                "D) g'(f(x))f'(x)"
                        },
                        "C"),
                createPregunta(
                        "3. La derivada de (2x)⁵ es:",
                        new String[] {
                                "A) 5(2x)⁴",
                                "B) 10(2x)⁴",
                                "C) 32x⁴",
                                "D) 10x⁴"
                        },
                        "B"),
                createPregunta(
                        "4. La derivada de cos(3x) es:",
                        new String[] {
                                "A) -sin(3x)",
                                "B) -3sin(3x)",
                                "C) 3sin(3x)",
                                "D) -3cos(3x)"
                        },
                        "B"),
                createPregunta(
                        "5. La derivada de e^(x²) es:",
                        new String[] {
                                "A) e^(x²)",
                                "B) 2xe^(x²)",
                                "C) x²e^(x²)",
                                "D) 2e^(x)"
                        },
                        "B"));

        utils.UIComponents.addCheckButton(preguntasContainer,
                () -> mostrarResultado(preguntasContainer, "Regla de la Cadena", () -> {
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
