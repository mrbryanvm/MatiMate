package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.limites.CalculoLimitesView;

public class CuestionarioCalculoView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private CalculoLimitesView parentView;

    public CuestionarioCalculoView(ViewManager viewManager, CalculoLimitesView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader(
                "RESOLVER CUESTIONARIO",
                "Tema: Límites - Cálculo de límites",
                () -> parentView.show());

        ScrollPane scrollContent = new ScrollPane();
        scrollContent.setFitToWidth(true);
        scrollContent.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(25, 25, 25, 25));
        mainContent.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox preguntasContainer = new VBox(25);
        preguntasContainer.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-padding: 25;");

        VBox pregunta1 = createPregunta(
                "1. ¿Cuál es el primer paso para calcular un límite?",
                new String[] {
                        "A) Factorizar inmediatamente.",
                        "B) Sustitución directa.",
                        "C) Racionalizar.",
                        "D) Aplicar la regla de L'Hôpital."
                },
                "B");

        VBox pregunta2 = createPregunta(
                "2. Si al sustituir obtenemos 0/0, ¿qué significa?",
                new String[] {
                        "A) Que el límite no existe.",
                        "B) Que el límite es 0.",
                        "C) Que es una indeterminación y debemos simplificar.",
                        "D) Que el límite es infinito."
                },
                "C");

        VBox pregunta3 = createPregunta(
                "3. ¿Qué técnica se usa comúnmente cuando hay raíces cuadradas y una indeterminación 0/0?",
                new String[] {
                        "A) Factorización por aspa simple.",
                        "B) Racionalización (multiplicar por el conjugado).",
                        "C) División sintética.",
                        "D) Logaritmos."
                },
                "B");

        VBox pregunta4 = createPregunta(
                "4. ¿Cuál es el límite de una constante k cuando x tiende a cualquier valor?",
                new String[] {
                        "A) x",
                        "B) 0",
                        "C) k",
                        "D) Infinito"
                },
                "C");

        VBox pregunta5 = createPregunta(
                "5. Si f(x) es un polinomio, lim(x→a) f(x) es igual a:",
                new String[] {
                        "A) f(a)",
                        "B) f'(a)",
                        "C) 0",
                        "D) Indeterminado"
                },
                "A");

        preguntasContainer.getChildren().addAll(pregunta1, pregunta2, pregunta3, pregunta4, pregunta5);

        Button comprobarBtn = new Button("Comprobar");
        comprobarBtn.setStyle("-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 15; -fx-padding: 15 40; -fx-font-size: 16;");
        comprobarBtn.setOnAction(e -> mostrarResultado(preguntasContainer, "Cálculo de límites", this::recreateView));

        mainContent.getChildren().addAll(preguntasContainer, comprobarBtn);
        scrollContent.setContent(mainContent);
        contentLayout.getChildren().add(scrollContent);

        return contentLayout;
    }

    private void recreateView() {
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(createView());
    }
}
