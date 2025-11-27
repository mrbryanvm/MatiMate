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
import views.cuestionarios.CuestionarioComposicionFuncionesView;

public class ComposicionFuncionesView {
    private ViewManager viewManager;
    private FuncionesMenuView parentView;

    public ComposicionFuncionesView(ViewManager viewManager, FuncionesMenuView parentView) {
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
        VBox header = UIComponents.createHeader("Composición de Funciones", "Operaciones con funciones");

        // Definition Section
        VBox definitionBox = UIComponents.createContentBox("Concepto");
        Text definition = new Text(
                "La composición de funciones es una operación que consiste en aplicar una función dentro de otra.\n\n" +
                        "Dadas dos funciones f y g, la función compuesta (f ∘ g)(x) se define como:\n" +
                        "(f ∘ g)(x) = f(g(x))\n\n" +
                        "Esto significa que primero aplicamos g a x, y luego aplicamos f al resultado g(x).\n" +
                        "Importante: El dominio de f ∘ g está formado por todos los x en el dominio de g tales que g(x) está en el dominio de f.");
        definition.setFont(Font.font("Segoe UI", 16));
        definition.setFill(AppConstants.TEXT_COLOR);
        definition.setWrappingWidth(700);
        definitionBox.getChildren().add(definition);

        // Video Section
        VBox videoBox = UIComponents.createContentBox("Video Explicativo");
        Button watchVideoBtn = UIComponents.createPrimaryButton("▶ Ver Video Explicativo");
        watchVideoBtn.setOnAction(e -> YouTubePlayer.playVideo("https://www.youtube.com/watch?v=qLBU9aP_WXQ"));
        videoBox.getChildren().add(watchVideoBtn);

        // Actions Section
        HBox actionsBox = new HBox(20);
        actionsBox.setAlignment(Pos.CENTER);

        Button exercisesBtn = UIComponents.createPrimaryButton("📝 Ver Ejercicios Resueltos");
        exercisesBtn.setOnAction(e -> {
            EjerciciosComposicionFuncionesView ejerciciosView = new EjerciciosComposicionFuncionesView(viewManager,
                    this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(ejerciciosView.createView());
        });

        Button quizBtn = UIComponents.createPrimaryButton("🧠 Realizar Cuestionario");
        quizBtn.setOnAction(e -> {
            CuestionarioComposicionFuncionesView quizView = new CuestionarioComposicionFuncionesView(viewManager, this);
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
