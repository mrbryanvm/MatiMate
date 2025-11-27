package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.funciones.DominioRangoView;

public class CuestionarioDominioRangoView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private DominioRangoView parentView;

    public CuestionarioDominioRangoView(ViewManager viewManager, DominioRangoView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader("Cuestionario: Dominio y Rango", "Pon a prueba tus conocimientos",
                () -> {
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(parentView.createView());
                });

        VBox preguntasContainer = new VBox(20);
        preguntasContainer.setPadding(new Insets(20));

        preguntasContainer.getChildren().addAll(
                createPregunta(
                        "1. ¿Qué es el dominio de una función?",
                        new String[] {
                                "A) El conjunto de valores de salida (y).",
                                "B) El conjunto de valores de entrada (x) para los que la función está definida.",
                                "C) El punto donde la gráfica corta al eje y.",
                                "D) El valor máximo de la función."
                        },
                        "B"),
                createPregunta(
                        "2. ¿Cuál es el dominio de f(x) = 1/x?",
                        new String[] {
                                "A) Todos los reales (R).",
                                "B) R - {1}",
                                "C) R - {0}",
                                "D) (0, ∞)"
                        },
                        "C"),
                createPregunta(
                        "3. ¿Cuál es el dominio de f(x) = √x?",
                        new String[] {
                                "A) R",
                                "B) [0, ∞)",
                                "C) (0, ∞)",
                                "D) (-∞, 0]"
                        },
                        "B"),
                createPregunta(
                        "4. ¿Cuál es el rango de f(x) = |x|?",
                        new String[] {
                                "A) R",
                                "B) (-∞, 0]",
                                "C) [0, ∞)",
                                "D) R - {0}"
                        },
                        "C"),
                createPregunta(
                        "5. Si f(x) = x + 2, ¿cuál es su dominio y rango?",
                        new String[] {
                                "A) Dom = R, Ran = R",
                                "B) Dom = [0, ∞), Ran = [0, ∞)",
                                "C) Dom = R - {0}, Ran = R - {0}",
                                "D) Dom = R, Ran = [2, ∞)"
                        },
                        "A"));

        utils.UIComponents.addCheckButton(preguntasContainer,
                () -> mostrarResultado(preguntasContainer, "Dominio y Rango", () -> {
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
