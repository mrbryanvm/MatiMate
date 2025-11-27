package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.derivadas.DefinicionDerivadaView;

public class CuestionarioDefinicionDerivadaView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private DefinicionDerivadaView parentView;

    public CuestionarioDefinicionDerivadaView(ViewManager viewManager, DefinicionDerivadaView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader("Cuestionario: Definición de Derivada", "Pon a prueba tus conocimientos",
                () -> {
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(parentView.createView());
                });

        VBox preguntasContainer = new VBox(20);
        preguntasContainer.setPadding(new Insets(20));

        preguntasContainer.getChildren().addAll(
                createPregunta(
                        "1. ¿Qué representa geométricamente la derivada en un punto?",
                        new String[] {
                                "A) El área bajo la curva.",
                                "B) La pendiente de la recta secante.",
                                "C) La pendiente de la recta tangente.",
                                "D) La longitud de la curva."
                        },
                        "C"),
                createPregunta(
                        "2. ¿Cuál es la definición formal de derivada?",
                        new String[] {
                                "A) lim(h→0) [f(x+h) + f(x)] / h",
                                "B) lim(h→0) [f(x+h) - f(x)] / h",
                                "C) lim(x→0) [f(x) - f(0)] / x",
                                "D) [f(x+h) - f(x)] / h"
                        },
                        "B"),
                createPregunta(
                        "3. Si f'(x) > 0 en un intervalo, entonces la función es:",
                        new String[] {
                                "A) Decreciente",
                                "B) Constante",
                                "C) Creciente",
                                "D) Cóncava"
                        },
                        "C"),
                createPregunta(
                        "4. ¿Cuál es la derivada de una constante?",
                        new String[] {
                                "A) 1",
                                "B) x",
                                "C) 0",
                                "D) La misma constante"
                        },
                        "C"),
                createPregunta(
                        "5. La derivada representa una razón de cambio:",
                        new String[] {
                                "A) Promedio",
                                "B) Instantánea",
                                "C) Total",
                                "D) Constante"
                        },
                        "B"));

        utils.UIComponents.addCheckButton(preguntasContainer,
                () -> mostrarResultado(preguntasContainer, "Definición de Derivada", () -> {
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
