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

public class IntegralesMenuView {
    private ViewManager viewManager;

    public IntegralesMenuView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public VBox createView() {
        VBox integralesLayout = new VBox(0);
        integralesLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(50, 20, 40, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

        VBox headerContent = new VBox(10);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("INTEGRALES");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Integrales indefinidas y definidas");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);
        header.getChildren().add(headerContent);

        VBox topicsBox = new VBox(12);
        topicsBox.setPadding(new Insets(30, 25, 30, 25));
        topicsBox.setAlignment(Pos.CENTER);

        String[] topics = {
                "📝 Integral indefinida",
                "📊 Integral definida",
                "🔧 Métodos de integración",
                "⚡ Teorema fundamental",
                "🎯 Evaluación"
        };

        for (String topic : topics) {
            HBox topicItem = UIComponents.createTopicItem(topic);
            topicItem.setOnMouseClicked(e -> viewManager.showComingSoon(topic));
            topicsBox.getChildren().add(topicItem);
        }

        Button backBtn = UIComponents.createSecondaryButton("↩️ VOLVER AL MENÚ");
        backBtn.setOnAction(e -> viewManager.showMainMenu());

        ScrollPane scrollPane = new ScrollPane(topicsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        integralesLayout.getChildren().addAll(header, scrollPane, backBtn);

        return integralesLayout;
    }
}
