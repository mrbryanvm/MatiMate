package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.derivadas.AplicacionesDerivadaView;

public class CuestionarioAplicacionesDerivadaView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private AplicacionesDerivadaView parentView;

    public CuestionarioAplicacionesDerivadaView(ViewManager viewManager, AplicacionesDerivadaView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader("Cuestionario: Aplicaciones de la Derivada", "Pon a prueba tus conocimientos",
                () -> {
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(parentView.createView());
                });

        VBox preguntasContainer = new VBox(20);
        preguntasContainer.setPadding(new Insets(20));

        preguntasContainer.getChildren().addAll(
                createPregunta(
                        "1. ¿Qué representa la derivada de la posición respecto al tiempo?",
                        new String[] {
                                "A) Aceleración",
                                "B) Velocidad",
                                "C) Distancia",
                                "D) Fuerza"
                        },
                        "B"),
                createPregunta(
                        "2. Para encontrar un máximo o mínimo local, primero buscamos:",
                        new String[] {
                                "A) Donde f'(x) = 0",
                                "B) Donde f(x) = 0",
                                "C) Donde f''(x) = 0",
                                "D) El dominio"
                        },
                        "A"),
                createPregunta(
                        "3. Si f''(x) < 0 en un punto crítico, entonces hay un:",
                        new String[] {
                                "A) Mínimo local",
                                "B) Máximo local",
                                "C) Punto de inflexión",
                                "D) Discontinuidad"
                        },
                        "B"),
                createPregunta(
                        "4. La Regla de L'Hôpital se usa para:",
                        new String[] {
                                "A) Integrar funciones",
                                "B) Calcular límites indeterminados",
                                "C) Hallar áreas",
                                "D) Derivar productos"
                        },
                        "B"),
                createPregunta(
                        "5. El costo marginal es la derivada de:",
                        new String[] {
                                "A) La función de ingreso",
                                "B) La función de costo",
                                "C) La función de utilidad",
                                "D) La función de demanda"
                        },
                        "B"));

        utils.UIComponents.addCheckButton(preguntasContainer,
                () -> mostrarResultado(preguntasContainer, "Aplicaciones de la Derivada", () -> {
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
