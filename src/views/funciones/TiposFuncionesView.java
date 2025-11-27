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
import views.cuestionarios.CuestionarioTiposFuncionesView;

public class TiposFuncionesView {
    private ViewManager viewManager;
    private FuncionesMenuView parentView;

    public TiposFuncionesView(ViewManager viewManager, FuncionesMenuView parentView) {
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
        VBox header = UIComponents.createHeader("Tipos de Funciones", "Clasificación y características");

        // Definition Section
        VBox definitionBox = UIComponents.createContentBox("Clasificación de Funciones");
        Text definition = new Text(
                "Las funciones se pueden clasificar según su comportamiento y características:\n\n" +
                        "• Inyectiva (Uno a uno): Cada elemento del codominio es imagen de a lo más un elemento del dominio.\n"
                        +
                        "• Sobreyectiva (Sobre): Cada elemento del codominio es imagen de al menos un elemento del dominio.\n"
                        +
                        "• Biyectiva: Es inyectiva y sobreyectiva a la vez.\n\n" +
                        "También existen funciones algebraicas (polinómicas, racionales, radicales) y trascendentes (exponenciales, logarítmicas, trigonométricas).");
        definition.setFont(Font.font("Segoe UI", 16));
        definition.setFill(AppConstants.TEXT_COLOR);
        definition.setWrappingWidth(700);
        definitionBox.getChildren().add(definition);

        // Video Section
        VBox videoBox = UIComponents.createContentBox("Video Explicativo");
        Button watchVideoBtn = UIComponents.createPrimaryButton("▶ Ver Video Explicativo");
        watchVideoBtn.setOnAction(e -> YouTubePlayer.playVideo("https://www.youtube.com/watch?v=xhBWUbY1VrM"));
        videoBox.getChildren().add(watchVideoBtn);

        // Actions Section
        HBox actionsBox = new HBox(20);
        actionsBox.setAlignment(Pos.CENTER);

        Button exercisesBtn = UIComponents.createPrimaryButton("📝 Ver Ejercicios Resueltos");
        exercisesBtn.setOnAction(e -> {
            EjerciciosTiposFuncionesView ejerciciosView = new EjerciciosTiposFuncionesView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(ejerciciosView.createView());
        });

        Button quizBtn = UIComponents.createPrimaryButton("🧠 Realizar Cuestionario");
        quizBtn.setOnAction(e -> {
            CuestionarioTiposFuncionesView quizView = new CuestionarioTiposFuncionesView(viewManager, this);
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
