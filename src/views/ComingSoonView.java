package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.UIComponents;

public class ComingSoonView {
    private ViewManager viewManager;
    private String feature;
    
    public ComingSoonView(ViewManager viewManager, String feature) {
        this.viewManager = viewManager;
        this.feature = feature;
    }
    
    public VBox createView() {
        VBox comingSoonLayout = new VBox(0);
        comingSoonLayout.setBackground(new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(50, 20, 40, 20));
        header.setStyle("-fx-background-color: #a0aec0;");
        
        Text title = new Text(feature);
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(javafx.scene.paint.Color.WHITE);
        header.getChildren().add(title);

        VBox content = new VBox(30);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(50, 25, 50, 25));
        
        Text emoji = new Text("🚧");
        emoji.setFont(Font.font(64));
        
        Text message = new Text("PRÓXIMAMENTE");
        message.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        message.setFill(AppConstants.TEXT_COLOR);
        
        Text description = new Text("Estamos trabajando en esta funcionalidad\n¡Vuelve pronto!");
        description.setFont(Font.font("Segoe UI", 14));
        description.setFill(AppConstants.LIGHT_TEXT);
        description.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        
        Button backBtn = UIComponents.createSecondaryButton("↩️ VOLVER AL MENÚ PRINCIPAL");
        backBtn.setOnAction(e -> viewManager.showMainMenu());

        content.getChildren().addAll(emoji, message, description, backBtn);
        comingSoonLayout.getChildren().addAll(header, content);
        
        return comingSoonLayout;
    }
}