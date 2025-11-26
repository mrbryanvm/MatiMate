package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.UIComponents;
import views.ViewManager;

public class LimiteFuncionView {
    private ViewManager viewManager;
    
    public LimiteFuncionView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
    
    public VBox createView() {
        VBox contentLayout = new VBox(0);
        contentLayout.setBackground(new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(40, 20, 30, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");
        
        VBox headerContent = new VBox(5);
        headerContent.setAlignment(Pos.CENTER);
        
        Text title = new Text("LÍMITES DE UNA FUNCIÓN");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(AppConstants.TITLE_COLOR);
        
        Text subtitle = new Text("Aprende los fundamentos del cálculo");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));
        
        headerContent.getChildren().addAll(title, subtitle);
        header.getChildren().add(headerContent);

        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(25, 25, 25, 25));
        mainContent.setAlignment(Pos.TOP_CENTER);

        VBox card = new VBox(0);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 20; " +
                     "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 15, 0, 0, 5); " +
                     "-fx-border-color: #00AEEF; -fx-border-width: 1; -fx-border-radius: 20;");
        card.setMaxWidth(350);
        card.setPadding(new Insets(0, 0, 20, 0));

        VBox optionsBox = new VBox(0);
        optionsBox.setAlignment(Pos.TOP_CENTER);

        HBox option1 = UIComponents.createOptionItem("📖", "Definición de límites", true);
        option1.setOnMouseClicked(e -> {
            DefinicionLimitesView definicionView = new DefinicionLimitesView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(definicionView.createView());
        });

        Separator sep1 = new Separator();
        sep1.setStyle("-fx-background-color: #E2E8F0;");

        HBox option2 = UIComponents.createOptionItem("↔️", "Límites laterales", false);
        option2.setOnMouseClicked(e -> {
            LimitesLateralesView lateralesView = new LimitesLateralesView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(lateralesView.createView());
        });

        Separator sep2 = new Separator();
        sep2.setStyle("-fx-background-color: #E2E8F0;");

        HBox option3 = UIComponents.createOptionItem("📊", "Propiedades", false);
        option3.setOnMouseClicked(e -> {
            PropiedadesLimitesView propiedadesView = new PropiedadesLimitesView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(propiedadesView.createView());
        });

        optionsBox.getChildren().addAll(option1, sep1, option2, sep2, option3);
        card.getChildren().add(optionsBox);

        Button backBtn = UIComponents.createSecondaryButton("↩️ VOLVER A LÍMITES");
        backBtn.setOnAction(e -> viewManager.showLimitesMenu());

        mainContent.getChildren().addAll(card, backBtn);
        contentLayout.getChildren().addAll(header, mainContent);
        
        return contentLayout;
    }
    
    public void show() {
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(createView());
    }
}