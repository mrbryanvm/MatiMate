package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.UIComponents;
import views.limites.*;

public class LimitesMenuView {
    private ViewManager viewManager;

    public LimitesMenuView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public VBox createView() {
        VBox limitsLayout = new VBox(0);
        limitsLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(50, 20, 40, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

        VBox headerContent = new VBox(10);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("LÍMITES");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Aprende los fundamentos del cálculo");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);
        header.getChildren().add(headerContent);

        VBox topicsBox = new VBox(12);
        topicsBox.setPadding(new Insets(30, 25, 30, 25));
        topicsBox.setAlignment(Pos.CENTER);

        String[] topics = {
                "📚 Límite de una función",
                "🔍 Continuidad en un punto",
                "🧮 Cálculo de límites",
                "∞ Límites al infinito",
                "⭐ Límites notables",
                "🎯 Evaluación"
        };

        for (String topic : topics) {
            HBox topicItem = UIComponents.createTopicItem(topic);

            if (topic.equals("📚 Límite de una función")) {
                topicItem.setOnMouseClicked(e -> {
                    LimiteFuncionView limiteView = new LimiteFuncionView(viewManager);
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(limiteView.createView());
                });
            } else if (topic.equals("🔍 Continuidad en un punto")) {
                topicItem.setOnMouseClicked(e -> {
                    ContinuidadView continuidadView = new ContinuidadView(viewManager, this);
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(continuidadView.createView());
                });
            } else if (topic.equals("🧮 Cálculo de límites")) {
                topicItem.setOnMouseClicked(e -> {
                    CalculoLimitesView calculoView = new CalculoLimitesView(viewManager, this);
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(calculoView.createView());
                });
            } else if (topic.equals("∞ Límites al infinito")) {
                topicItem.setOnMouseClicked(e -> {
                    LimitesInfinitoView infinitoView = new LimitesInfinitoView(viewManager, this);
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(infinitoView.createView());
                });
            } else if (topic.equals("⭐ Límites notables")) {
                topicItem.setOnMouseClicked(e -> {
                    LimitesNotablesView notablesView = new LimitesNotablesView(viewManager, this);
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(notablesView.createView());
                });
            } else if (topic.equals("🎯 Evaluación")) {
                topicItem.setOnMouseClicked(e -> {
                    IntroduccionView introView = new IntroduccionView(viewManager);
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(introView.createView());
                });
            }
            topicsBox.getChildren().add(topicItem);
        }

        Button backBtn = UIComponents.createSecondaryButton("↩️ VOLVER AL MENÚ");
        backBtn.setOnAction(e -> viewManager.showMainMenu());

        ScrollPane scrollPane = new ScrollPane(topicsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        limitsLayout.getChildren().addAll(header, scrollPane, backBtn);

        return limitsLayout;
    }
}