package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.limites.ContinuidadView;

public class CuestionarioContinuidadView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private ContinuidadView parentView;

    public CuestionarioContinuidadView(ViewManager viewManager, ContinuidadView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = createHeader(
                "RESOLVER CUESTIONARIO",
                "Tema: Límites - Continuidad en un punto",
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
                "1. ¿Cuál es la primera condición para que una función sea continua en x=a?",
                new String[] {
                        "A) Que el límite exista.",
                        "B) Que f(a) exista (esté definida).",
                        "C) Que el límite sea igual a la función.",
                        "D) Que la función sea derivable."
                },
                "B");

        VBox pregunta2 = createPregunta(
                "2. Si lim(x→a) f(x) existe pero es diferente de f(a), entonces:",
                new String[] {
                        "A) La función es continua.",
                        "B) La función es discontinua evitable.",
                        "C) El límite no existe.",
                        "D) f(a) no existe."
                },
                "B");

        VBox pregunta3 = createPregunta(
                "3. ¿Qué significa intuitivamente que una función sea continua?",
                new String[] {
                        "A) Que su gráfica es una línea recta.",
                        "B) Que se puede dibujar sin levantar el lápiz del papel.",
                        "C) Que siempre es positiva.",
                        "D) Que tiene derivada en todos los puntos."
                },
                "B");

        VBox pregunta4 = createPregunta(
                "4. Si f(x) = 1/x, ¿es continua en x=0?",
                new String[] {
                        "A) Sí, porque el límite es infinito.",
                        "B) No, porque f(0) no está definida.",
                        "C) Sí, pero tiene una asíntota.",
                        "D) Depende del dominio."
                },
                "B");

        VBox pregunta5 = createPregunta(
                "5. Para que f(x) sea continua en x=a, se debe cumplir:",
                new String[] {
                        "A) Solo que f(a) exista.",
                        "B) Solo que el límite exista.",
                        "C) Que f(a) exista, el límite exista y sean iguales.",
                        "D) Que la función sea polinómica."
                },
                "C");

        preguntasContainer.getChildren().addAll(pregunta1, pregunta2, pregunta3, pregunta4, pregunta5);

        Button comprobarBtn = new Button("Comprobar");
        comprobarBtn.setStyle("-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 15; -fx-padding: 15 40; -fx-font-size: 16;");
        comprobarBtn.setOnAction(e -> mostrarResultado(preguntasContainer, "Continuidad", this::recreateView));

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
