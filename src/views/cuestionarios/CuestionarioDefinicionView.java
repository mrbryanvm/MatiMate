package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import utils.AppConstants;
import utils.UIComponents;
import views.ViewManager;
import views.limites.DefinicionLimitesView;

public class CuestionarioDefinicionView extends CuestionarioBaseView {
    private ViewManager viewManager;
    private DefinicionLimitesView parentView;
    
    public CuestionarioDefinicionView(ViewManager viewManager, DefinicionLimitesView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }
    
    public VBox createView() {
        VBox contentLayout = createHeader(
            "RESOLVER CUESTIONARIO",
            "Tema: Límites - Definición de límites",
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
            "1. ¿Qué representa el límite de una función f(x) cuando x se aproxima a un valor a?",
            new String[]{
                "A) El valor exacto de la función en a.",
                "B) El número L al cual se acercan los valores de f(x) cuando x se acerca a a.",
                "C) El punto donde la función cambia de signo.",
                "D) El máximo valor que alcanza la función."
            },
            "B"
        );

        VBox pregunta2 = createPregunta(
            "2. ¿Cómo se escribe el límite de una función f(x) cuando x se aproxima a a?",
            new String[]{
                "A) f(x) = a",
                "B) lim(x→a) f(x) = L",
                "C) lim(x→a) f(a) = L",
                "D) L = f(a)"
            },
            "B"
        );

        VBox pregunta3 = createPregunta(
            "3. ¿Qué sucede si los valores cercanos a p tienen imágenes cercanas a L?",
            new String[]{
                "A) Entonces L es el límite.",
                "B) El límite no existe.",
                "C) La función es discontinua.",
                "D) La función no se puede evaluar."
            },
            "A"
        );

        VBox pregunta4 = createPregunta(
            "4. ¿Qué ocurre si algún punto cercano a p tiene una imagen lejos de L?",
            new String[]{
                "A) El límite es infinito.",
                "B) L no es el límite.",
                "C) El límite existe.",
                "D) f(x) = L."
            },
            "B"
        );

        VBox pregunta5 = createPregunta(
            "5. Para determinar un límite experimentalmente, se debe:",
            new String[]{
                "A) Evaluar f(x) en valores cada vez más próximos a p.",
                "B) Calcular la derivada de f(x).",
                "C) Sustituir directamente el punto p.",
                "D) Buscar el máximo valor de f(x)."
            },
            "A"
        );

        preguntasContainer.getChildren().addAll(pregunta1, pregunta2, pregunta3, pregunta4, pregunta5);

        Button comprobarBtn = new Button("Comprobar");
        comprobarBtn.setStyle("-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; " +
                             "-fx-background-radius: 15; -fx-padding: 15 40; -fx-font-size: 16;");
        comprobarBtn.setOnAction(e -> mostrarResultado(preguntasContainer, "Definición de límites", this::recreateView));

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