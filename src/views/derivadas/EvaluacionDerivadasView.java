package views.derivadas;

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
import views.ViewManager;
import views.DerivadasMenuView;

public class EvaluacionDerivadasView {
    private ViewManager viewManager;
    private DerivadasMenuView parentView;

    public EvaluacionDerivadasView(ViewManager viewManager, DerivadasMenuView parentView) {
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
        VBox header = UIComponents.createHeader("Evaluación de Derivadas", "Selecciona tu nivel de desafío");

        // Levels Container
        VBox levelsContainer = new VBox(20);
        levelsContainer.setAlignment(Pos.CENTER);
        levelsContainer.setPadding(new Insets(20));

        levelsContainer.getChildren().addAll(
                createLevelCard("Nivel Introducción", "Conceptos básicos y reglas simples", "Fácil", "Introducción"),
                createLevelCard("Nivel Intermedio", "Regla de la cadena y productos", "Medio", "Intermedio"),
                createLevelCard("Nivel Avanzado", "Aplicaciones y derivadas complejas", "Difícil", "Avanzado"));

        // Back Button
        Button backBtn = UIComponents.createSecondaryButton("⬅ Volver al Menú de Derivadas");
        backBtn.setOnAction(e -> {
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(parentView.createView());
        });

        contentLayout.getChildren().addAll(header, levelsContainer, backBtn);

        ScrollPane scrollPane = new ScrollPane(contentLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox mainContainer = new VBox(scrollPane);
        mainContainer.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        return mainContainer;
    }

    private VBox createLevelCard(String title, String description, String difficulty, String levelKey) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setStyle(
                "-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 5); -fx-cursor: hand;");
        card.setMaxWidth(600);

        Text titleText = new Text(title);
        titleText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        titleText.setFill(AppConstants.PRIMARY_COLOR);

        Text descText = new Text(description);
        descText.setFont(Font.font("Segoe UI", 14));
        descText.setFill(AppConstants.TEXT_COLOR);

        HBox difficultyBox = new HBox(5);
        difficultyBox.setAlignment(Pos.CENTER_LEFT);
        Text diffLabel = new Text("Dificultad: ");
        diffLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        Text diffValue = new Text(difficulty);
        diffValue.setFont(Font.font("Segoe UI", 12));

        if (difficulty.equals("Fácil"))
            diffValue.setFill(javafx.scene.paint.Color.GREEN);
        else if (difficulty.equals("Medio"))
            diffValue.setFill(javafx.scene.paint.Color.ORANGE);
        else
            diffValue.setFill(javafx.scene.paint.Color.RED);

        difficultyBox.getChildren().addAll(diffLabel, diffValue);

        card.getChildren().addAll(titleText, descText, difficultyBox);

        card.setOnMouseClicked(e -> {
            NivelEvaluacionDerivadasView nivelView = new NivelEvaluacionDerivadasView(viewManager, this, levelKey);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(nivelView.createView());
        });

        return card;
    }
}
