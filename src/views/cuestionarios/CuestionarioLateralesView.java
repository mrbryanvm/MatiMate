package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.limites.LimitesLateralesView;

public class CuestionarioLateralesView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private LimitesLateralesView parentView;
    
    public CuestionarioLateralesView(ViewManager viewManager, LimitesLateralesView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }
    
    public VBox createView() {
        VBox contentLayout = createHeader(
            "RESOLVER CUESTIONARIO",
            "Tema: Límites - Límites laterales",
            () -> parentView.show()
        );

        ScrollPane scrollContent = new ScrollPane();
        scrollContent.setFitToWidth(true);
        scrollContent.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(25, 25, 25, 25));
        mainContent.setBackground(new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox preguntasContainer = new VBox(25);
        preguntasContainer.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-padding: 25;");

        VBox pregunta1 = createPregunta(
            "1. ¿Qué se entiende por límite lateral derecho?",
            new String[]{
                "A) Cuando x se acerca a p por valores menores que p.",
                "B) Cuando x se acerca a p por valores mayores que p.",
                "C) Cuando f(x) se hace constante.",
                "D) Cuando f(p) = 0."
            },
            "B"
        );

        VBox pregunta2 = createPregunta(
            "2. ¿Qué se entiende por límite lateral izquierdo?",
            new String[]{
                "A) Cuando x se acerca a p por valores menores que p.",
                "B) Cuando x se acerca a p por valores mayores que p.",
                "C) Cuando x = p.",
                "D) Cuando f(x) no existe."
            },
            "A"
        );

        VBox pregunta3 = createPregunta(
            "3. ¿Qué condición se debe cumplir para que el límite general exista en un punto?",
            new String[]{
                "A) Que solo exista el límite derecho.",
                "B) Que solo exista el límite izquierdo.",
                "C) Que ambos límites laterales existan y sean iguales.",
                "D) Que la función no tenga gráfica."
            },
            "C"
        );

        VBox pregunta4 = createPregunta(
            "4. Si el límite por la derecha y el límite por la izquierda no son iguales, entonces:",
            new String[]{
                "A) El límite existe.",
                "B) El límite no existe.",
                "C) La función es constante.",
                "D) El límite es infinito."
            },
            "B"
        );

        VBox pregunta5 = createPregunta(
            "5. ¿Qué indican los límites laterales de una función?",
            new String[]{
                "A) Cómo se comporta f(x) solo en un punto.",
                "B) Cómo se aproxima f(x) a un valor desde cada lado del punto p.",
                "C) La pendiente de la función.",
                "D) El área bajo la curva."
            },
            "B"
        );

        preguntasContainer.getChildren().addAll(pregunta1, pregunta2, pregunta3, pregunta4, pregunta5);

        Button comprobarBtn = new Button("Comprobar");
        comprobarBtn.setStyle("-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; " +
                             "-fx-background-radius: 15; -fx-padding: 15 40; -fx-font-size: 16;");
        comprobarBtn.setOnAction(e -> mostrarResultado(preguntasContainer, "Límites laterales", this::recreateView));

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