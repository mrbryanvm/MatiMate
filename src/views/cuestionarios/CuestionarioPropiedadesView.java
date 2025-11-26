package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import utils.AppConstants;
import views.ViewManager;
import views.limites.PropiedadesLimitesView;

public class CuestionarioPropiedadesView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private PropiedadesLimitesView parentView;
    
    public CuestionarioPropiedadesView(ViewManager viewManager, PropiedadesLimitesView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }
    
    public VBox createView() {
        VBox contentLayout = createHeader(
            "RESOLVER CUESTIONARIO",
            "Tema: Límites - Propiedades",
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
            "1. ¿Cuál es el límite de una constante k?",
            new String[]{
                "A) 0",
                "B) x",
                "C) k",
                "D) No existe."
            },
            "C"
        );

        VBox pregunta2 = createPregunta(
            "2. ¿Qué establece la propiedad de unicidad del límite?",
            new String[]{
                "A) Que una función puede tener varios límites en un mismo punto.",
                "B) Que una función solo puede tener un límite en un punto.",
                "C) Que los límites siempre son iguales a 0.",
                "D) Que los límites laterales son diferentes."
            },
            "B"
        );

        VBox pregunta3 = createPregunta(
            "3. ¿Qué sucede si existen dos límites distintos en un mismo punto?",
            new String[]{
                "A) La función es continua.",
                "B) El límite no existe.",
                "C) Ambos son correctos.",
                "D) El límite es infinito."
            },
            "B"
        );

        VBox pregunta4 = createPregunta(
            "4. Si lim f(x) = A y lim g(x) = B, ¿cuál es lim[f(x) + g(x)]?",
            new String[]{
                "A) A + B",
                "B) A - B",
                "C) A × B",
                "D) A/B"
            },
            "A"
        );

        VBox pregunta5 = createPregunta(
            "5. Si lim f(x) = A y lim g(x) = B ≠ 0, ¿cuál es lim[f(x)/g(x)]?",
            new String[]{
                "A) A · B",
                "B) A/B",
                "C) A - B",
                "D) B/A"
            },
            "B"
        );

        preguntasContainer.getChildren().addAll(pregunta1, pregunta2, pregunta3, pregunta4, pregunta5);

        Button comprobarBtn = new Button("Comprobar");
        comprobarBtn.setStyle("-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; " +
                             "-fx-background-radius: 15; -fx-padding: 15 40; -fx-font-size: 16;");
        comprobarBtn.setOnAction(e -> mostrarResultado(preguntasContainer, "Propiedades", this::recreateView));

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