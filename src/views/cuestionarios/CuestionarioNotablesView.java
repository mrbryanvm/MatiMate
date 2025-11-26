package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.limites.LimitesNotablesView;

public class CuestionarioNotablesView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private LimitesNotablesView parentView;

    public CuestionarioNotablesView(ViewManager viewManager, LimitesNotablesView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader(
                "RESOLVER CUESTIONARIO",
                "Tema: Límites - Límites notables",
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
                "1. ¿Cuál es el valor de lim(x→0) (sen x)/x?",
                new String[] {
                        "A) 0",
                        "B) 1",
                        "C) Infinito",
                        "D) Indeterminado"
                },
                "B");

        VBox pregunta2 = createPregunta(
                "2. ¿Cuál es el valor de lim(x→∞) (1 + 1/x)^x?",
                new String[] {
                        "A) 1",
                        "B) 0",
                        "C) e",
                        "D) Infinito"
                },
                "C");

        VBox pregunta3 = createPregunta(
                "3. ¿Cuál es el valor de lim(x→0) (1 - cos x)/x?",
                new String[] {
                        "A) 1",
                        "B) 0",
                        "C) -1",
                        "D) 1/2"
                },
                "B");

        VBox pregunta4 = createPregunta(
                "4. Los límites notables son útiles para resolver indeterminaciones del tipo:",
                new String[] {
                        "A) 0/0 y 1^∞",
                        "B) ∞/∞",
                        "C) ∞ - ∞",
                        "D) 0 * ∞"
                },
                "A");

        VBox pregunta5 = createPregunta(
                "5. ¿Cuál es el valor de lim(x→0) (e^x - 1)/x?",
                new String[] {
                        "A) 0",
                        "B) e",
                        "C) 1",
                        "D) Infinito"
                },
                "C");

        preguntasContainer.getChildren().addAll(pregunta1, pregunta2, pregunta3, pregunta4, pregunta5);

        Button comprobarBtn = new Button("Comprobar");
        comprobarBtn.setStyle("-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 15; -fx-padding: 15 40; -fx-font-size: 16;");
        comprobarBtn.setOnAction(e -> mostrarResultado(preguntasContainer, "Límites notables", this::recreateView));

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
