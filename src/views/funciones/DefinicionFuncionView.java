package views.funciones;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.UIComponents;
import utils.YouTubePlayer;
import views.ViewManager;
import views.FuncionesMenuView;
import views.cuestionarios.CuestionarioDefinicionFuncionView;

public class DefinicionFuncionView {
    private ViewManager viewManager;
    private FuncionesMenuView parentView;

    public DefinicionFuncionView(ViewManager viewManager, FuncionesMenuView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = new VBox(20);
        contentLayout.setPadding(new Insets(30));
        contentLayout.setAlignment(Pos.TOP_CENTER);
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        // Header
        VBox header = UIComponents.createHeader("Definición de Función", "Conceptos fundamentales");

        // Definition Section
        VBox definitionBox = UIComponents.createContentBox("¿Qué es una función?");
        Text definition = new Text(
                "Una función es una relación entre dos conjuntos, llamados dominio y codominio, " +
                        "de tal manera que a cada elemento del dominio le corresponde uno y solo un elemento del codominio.\n\n"
                        +
                        "Matemáticamente, si f es una función de A en B, escribimos f: A → B.\n" +
                        "Si x ∈ A, entonces f(x) es el elemento de B asociado a x.");
        definition.setFont(Font.font("Segoe UI", 16));
        definition.setFill(AppConstants.TEXT_COLOR);
        definition.setWrappingWidth(700);
        definitionBox.getChildren().add(definition);

        // Video Section
        VBox videoBox = UIComponents.createContentBox("Video Explicativo");
        Button watchVideoBtn = UIComponents.createPrimaryButton("▶ Ver Video Explicativo");
        watchVideoBtn.setOnAction(e -> YouTubePlayer.playVideo("https://www.youtube.com/watch?v=Ll7xfe3HoZE&t=179s"));
        videoBox.getChildren().add(watchVideoBtn);

        // Actions Section
        HBox actionsBox = new HBox(20);
        actionsBox.setAlignment(Pos.CENTER);

        Button exercisesBtn = UIComponents.createPrimaryButton("📝 Ver Ejercicios Resueltos");
        exercisesBtn.setOnAction(e -> {
            EjerciciosDefinicionFuncionView ejerciciosView = new EjerciciosDefinicionFuncionView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(ejerciciosView.createView());
        });

        Button quizBtn = UIComponents.createPrimaryButton("🧠 Realizar Cuestionario");
        quizBtn.setOnAction(e -> {
            CuestionarioDefinicionFuncionView quizView = new CuestionarioDefinicionFuncionView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(quizView.createView());
        });

        actionsBox.getChildren().addAll(exercisesBtn, quizBtn);

        // Back Button
        Button backBtn = UIComponents.createSecondaryButton("⬅ Volver al Menú de Funciones");
        backBtn.setOnAction(e -> {
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(parentView.createView());
        });

        contentLayout.getChildren().addAll(header, definitionBox, videoBox, actionsBox, backBtn);

        ScrollPane scrollPane = new ScrollPane(contentLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox mainContainer = new VBox(scrollPane);
        mainContainer.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        return mainContainer;
    }
}
