package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.limites.LimitesInfinitoView;

public class CuestionarioInfinitoView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private LimitesInfinitoView parentView;

    public CuestionarioInfinitoView(ViewManager viewManager, LimitesInfinitoView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader(
                "RESOLVER CUESTIONARIO",
                "Tema: Límites - Límites al infinito",
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
                "1. Si el grado del numerador es menor que el del denominador, el límite al infinito es:",
                new String[] {
                        "A) Infinito",
                        "B) 0",
                        "C) 1",
                        "D) Indeterminado"
                },
                "B");

        VBox pregunta2 = createPregunta(
                "2. Si el grado del numerador es igual al del denominador, el límite es:",
                new String[] {
                        "A) 0",
                        "B) Infinito",
                        "C) El cociente de los coeficientes principales.",
                        "D) 1"
                },
                "C");

        VBox pregunta3 = createPregunta(
                "3. Si el grado del numerador es mayor que el del denominador, el límite es:",
                new String[] {
                        "A) 0",
                        "B) Infinito o menos infinito.",
                        "C) 1",
                        "D) No existe."
                },
                "B");

        VBox pregunta4 = createPregunta(
                "4. ¿Qué representa geométricamente un límite al infinito?",
                new String[] {
                        "A) Una asíntota vertical.",
                        "B) Una asíntota horizontal.",
                        "C) Un punto de discontinuidad.",
                        "D) La pendiente de la tangente."
                },
                "B");

        VBox pregunta5 = createPregunta(
                "5. Calcular lim(x→∞) (1/x):",
                new String[] {
                        "A) ∞",
                        "B) 1",
                        "C) 0",
                        "D) -∞"
                },
                "C");

        preguntasContainer.getChildren().addAll(pregunta1, pregunta2, pregunta3, pregunta4, pregunta5);

        Button comprobarBtn = new Button("Comprobar");
        comprobarBtn.setStyle("-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 15; -fx-padding: 15 40; -fx-font-size: 16;");
        comprobarBtn.setOnAction(e -> mostrarResultado(preguntasContainer, "Límites al infinito", this::recreateView));

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
