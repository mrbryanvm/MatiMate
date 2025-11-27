package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.derivadas.ReglasDerivacionView;

public class CuestionarioReglasDerivacionView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private ReglasDerivacionView parentView;

    public CuestionarioReglasDerivacionView(ViewManager viewManager, ReglasDerivacionView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader("Cuestionario: Reglas de Derivación", "Pon a prueba tus conocimientos",
                () -> {
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(parentView.createView());
                });

        VBox preguntasContainer = new VBox(20);
        preguntasContainer.setPadding(new Insets(20));

        preguntasContainer.getChildren().addAll(
                createPregunta(
                        "1. ¿Cuál es la derivada de xⁿ?",
                        new String[] {
                                "A) xⁿ⁻¹",
                                "B) n·xⁿ",
                                "C) n·xⁿ⁻¹",
                                "D) xⁿ⁺¹/(n+1)"
                        },
                        "C"),
                createPregunta(
                        "2. ¿Cuál es la regla del producto para (uv)'?",
                        new String[] {
                                "A) u'v'",
                                "B) u'v - uv'",
                                "C) u'v + uv'",
                                "D) (u'v - uv')/v²"
                        },
                        "C"),
                createPregunta(
                        "3. La derivada de sin(x) es:",
                        new String[] {
                                "A) cos(x)",
                                "B) -cos(x)",
                                "C) tan(x)",
                                "D) sec²(x)"
                        },
                        "A"),
                createPregunta(
                        "4. La derivada de eˣ es:",
                        new String[] {
                                "A) x·eˣ⁻¹",
                                "B) eˣ",
                                "C) ln(x)",
                                "D) 1/x"
                        },
                        "B"),
                createPregunta(
                        "5. Si f(x) = 3x² + 2x, entonces f'(x) es:",
                        new String[] {
                                "A) 6x + 2",
                                "B) 3x + 2",
                                "C) 6x",
                                "D) 5x"
                        },
                        "A"));

        utils.UIComponents.addCheckButton(preguntasContainer,
                () -> mostrarResultado(preguntasContainer, "Reglas de Derivación", () -> {
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
