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
import views.cuestionarios.CuestionarioOrdenSuperiorView;

public class OrdenSuperiorView {
    private ViewManager viewManager;
    private DerivadasMenuView parentView;

    public OrdenSuperiorView(ViewManager viewManager, DerivadasMenuView parentView) {
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
        VBox header = UIComponents.createHeader("Derivadas de Orden Superior", "Derivando sucesivamente");

        // Definition Section
        VBox definitionBox = UIComponents.createContentBox("Concepto");
        Text definition = new Text(
                "Las derivadas de orden superior se obtienen al derivar una función repetidamente.\n\n" +
                        "• Primera derivada: f'(x) o dy/dx\n" +
                        "• Segunda derivada: f''(x) o d²y/dx² (derivada de la primera derivada)\n" +
                        "• Tercera derivada: f'''(x) o d³y/dx³ (derivada de la segunda derivada)\n" +
                        "• Enésima derivada: f⁽ⁿ⁾(x)\n\n" +
                        "La segunda derivada nos da información sobre la concavidad de la función.");
        definition.setFont(Font.font("Segoe UI", 16));
        definition.setFill(AppConstants.TEXT_COLOR);
        definition.setWrappingWidth(700);
        definitionBox.getChildren().add(definition);

        // Video Section
        VBox videoBox = UIComponents.createContentBox("Video Explicativo");
        Button watchVideoBtn = UIComponents.createPrimaryButton("▶ Ver Video Explicativo");
        watchVideoBtn.setOnAction(e -> YouTubePlayer.playVideo("https://youtu.be/sMct2JvYDlE?si=5pWIMKys7IdPRd8z"));
        videoBox.getChildren().add(watchVideoBtn);

        // Actions Section
        HBox actionsBox = new HBox(20);
        actionsBox.setAlignment(Pos.CENTER);

        Button exercisesBtn = UIComponents.createPrimaryButton("📝 Ver Ejercicios Resueltos");
        exercisesBtn.setOnAction(e -> {
            EjerciciosOrdenSuperiorView ejerciciosView = new EjerciciosOrdenSuperiorView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(ejerciciosView.createView());
        });

        Button quizBtn = UIComponents.createPrimaryButton("🧠 Realizar Cuestionario");
        quizBtn.setOnAction(e -> {
            CuestionarioOrdenSuperiorView quizView = new CuestionarioOrdenSuperiorView(viewManager, this);
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
