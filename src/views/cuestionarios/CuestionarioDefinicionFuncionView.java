package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.funciones.DefinicionFuncionView;

public class CuestionarioDefinicionFuncionView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private DefinicionFuncionView parentView;

    public CuestionarioDefinicionFuncionView(ViewManager viewManager, DefinicionFuncionView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader("Cuestionario: Definición de Función", "Pon a prueba tus conocimientos",
                () -> {
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(parentView.createView());
                });

        VBox preguntasContainer = new VBox(20);
        preguntasContainer.setPadding(new Insets(20));

        preguntasContainer.getChildren().addAll(
                createPregunta(
                        "1. ¿Qué es una función?",
                        new String[] {
                                "A) Una relación donde a cada elemento del dominio le corresponde uno y solo un elemento del codominio.",
                                "B) Una relación donde a cada elemento del dominio le corresponden varios elementos del codominio.",
                                "C) Un conjunto de números aleatorios.",
                                "D) Una línea recta en un plano."
                        },
                        "A"),
                createPregunta(
                        "2. Si f(x) = 3x - 2, ¿cuánto es f(2)?",
                        new String[] {
                                "A) 2",
                                "B) 4",
                                "C) 6",
                                "D) 8"
                        },
                        "B"),
                createPregunta(
                        "3. ¿Cuál de las siguientes relaciones NO es una función?",
                        new String[] {
                                "A) {(1, 2), (2, 3), (3, 4)}",
                                "B) {(1, 2), (1, 3), (2, 4)}",
                                "C) {(1, 1), (2, 1), (3, 1)}",
                                "D) {(a, b), (c, d), (e, f)}"
                        },
                        "B"),
                createPregunta(
                        "4. En la notación f: A → B, ¿qué representa A?",
                        new String[] {
                                "A) El rango",
                                "B) El codominio",
                                "C) El dominio",
                                "D) La imagen"
                        },
                        "C"),
                createPregunta(
                        "5. ¿Qué prueba gráfica se usa para determinar si una gráfica representa una función?",
                        new String[] {
                                "A) Prueba de la línea horizontal",
                                "B) Prueba de la línea vertical",
                                "C) Prueba de la derivada",
                                "D) Prueba de la integral"
                        },
                        "B"));

        utils.UIComponents.addCheckButton(preguntasContainer,
                () -> mostrarResultado(preguntasContainer, "Definición de Función", () -> {
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
