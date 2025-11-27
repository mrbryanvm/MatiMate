package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.funciones.TiposFuncionesView;

public class CuestionarioTiposFuncionesView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private TiposFuncionesView parentView;

    public CuestionarioTiposFuncionesView(ViewManager viewManager, TiposFuncionesView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader("Cuestionario: Tipos de Funciones", "Pon a prueba tus conocimientos",
                () -> {
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(parentView.createView());
                });

        VBox preguntasContainer = new VBox(20);
        preguntasContainer.setPadding(new Insets(20));

        preguntasContainer.getChildren().addAll(
                createPregunta(
                        "1. ¿Qué característica define a una función inyectiva?",
                        new String[] {
                                "A) A elementos distintos del dominio les corresponden imágenes distintas.",
                                "B) Todos los elementos del codominio tienen preimagen.",
                                "C) Es inyectiva y sobreyectiva a la vez.",
                                "D) Su gráfica es una parábola."
                        },
                        "A"),
                createPregunta(
                        "2. ¿Qué es una función sobreyectiva?",
                        new String[] {
                                "A) Una función que pasa por el origen.",
                                "B) Una función donde el rango es igual al codominio.",
                                "C) Una función que nunca toca el eje x.",
                                "D) Una función con pendiente positiva."
                        },
                        "B"),
                createPregunta(
                        "3. ¿Qué es una función biyectiva?",
                        new String[] {
                                "A) Solo inyectiva.",
                                "B) Solo sobreyectiva.",
                                "C) Inyectiva y sobreyectiva simultáneamente.",
                                "D) Ninguna de las anteriores."
                        },
                        "C"),
                createPregunta(
                        "4. La función f(x) = x² definida de R en R es:",
                        new String[] {
                                "A) Inyectiva",
                                "B) Sobreyectiva",
                                "C) Biyectiva",
                                "D) Ninguna de las anteriores (ni inyectiva ni sobreyectiva)"
                        },
                        "D"),
                createPregunta(
                        "5. ¿Cuál de las siguientes es una función trascendente?",
                        new String[] {
                                "A) f(x) = x² + 1",
                                "B) f(x) = sin(x)",
                                "C) f(x) = 1/x",
                                "D) f(x) = √x"
                        },
                        "B"));

        utils.UIComponents.addCheckButton(preguntasContainer,
                () -> mostrarResultado(preguntasContainer, "Tipos de Funciones", () -> {
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
