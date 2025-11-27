package views.derivadas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.UIComponents;
import utils.YouTubePlayer;
import views.ViewManager;
import views.DerivadasMenuView;
import views.cuestionarios.CuestionarioReglaCadenaView;

public class ReglaCadenaView {
    private ViewManager viewManager;
    private DerivadasMenuView parentView;

    public ReglaCadenaView(ViewManager viewManager, DerivadasMenuView parentView) {
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
        VBox header = UIComponents.createHeader("Regla de la Cadena", "Derivada de funciones compuestas");

        // Definition Section
        VBox definitionBox = UIComponents.createContentBox("Concepto");
        Text definition = new Text(
                "La regla de la cadena se utiliza para derivar funciones compuestas.\n\n" +
                        "Si y = f(u) y u = g(x), entonces la derivada de y con respecto a x es:\n" +
                        "dy/dx = (dy/du) · (du/dx)\n\n" +
                        "O en notación funcional:\n" +
                        "d/dx [f(g(x))] = f'(g(x)) · g'(x)\n\n" +
                        "Es decir, derivamos la función externa evaluada en la interna, y multiplicamos por la derivada de la interna.");
        definition.setFont(Font.font("Segoe UI", 16));
        definition.setFill(AppConstants.TEXT_COLOR);
        definition.setWrappingWidth(700);
        definitionBox.getChildren().add(definition);

        // Video Section
        VBox videoBox = UIComponents.createContentBox("Video Explicativo");
        Button watchVideoBtn = UIComponents.createPrimaryButton("▶ Ver Video Explicativo");
        watchVideoBtn.setOnAction(e -> YouTubePlayer.playVideo("https://www.youtube.com/watch?v=m_5-WS9Nd68"));
        videoBox.getChildren().add(watchVideoBtn);

        // Actions Section
        HBox actionsBox = new HBox(20);
        actionsBox.setAlignment(Pos.CENTER);

        Button exercisesBtn = UIComponents.createPrimaryButton("📝 Ver Ejercicios Resueltos");
        exercisesBtn.setOnAction(e -> {
            EjerciciosReglaCadenaView ejerciciosView = new EjerciciosReglaCadenaView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(ejerciciosView.createView());
        });

        Button quizBtn = UIComponents.createPrimaryButton("🧠 Realizar Cuestionario");
        quizBtn.setOnAction(e -> {
            CuestionarioReglaCadenaView quizView = new CuestionarioReglaCadenaView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(quizView.createView());
        });

        actionsBox.getChildren().addAll(exercisesBtn, quizBtn);

        // Back Button
        Button backBtn = UIComponents.createSecondaryButton("⬅ Volver al Menú de Derivadas");
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
