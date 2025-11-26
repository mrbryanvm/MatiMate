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

public class IntroduccionView {
    private ViewManager viewManager;
    
    public IntroduccionView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
    
    public VBox createView() {
        VBox introLayout = new VBox(0);
        introLayout.setBackground(new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(50, 20, 40, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");
        
        VBox headerContent = new VBox(8);
        headerContent.setAlignment(Pos.CENTER);
        
        Text title = new Text("EVALUACION");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(AppConstants.TITLE_COLOR);
        
        Text subtitle = new Text("Niveles de dificultad");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));
        
        headerContent.getChildren().addAll(title, subtitle);
        header.getChildren().add(headerContent);

        VBox levelsContainer = new VBox(20);
        levelsContainer.setPadding(new Insets(30, 25, 30, 25));
        levelsContainer.setAlignment(Pos.CENTER);

        Button facilBtn = UIComponents.createLevelButton("🥇 FÁCIL", AppConstants.BUTTON_COLOR_HEX);
        Button normalBtn = UIComponents.createLevelButton("🥈 NORMAL", AppConstants.BORDER_COLOR_HEX); 
        Button dificilBtn = UIComponents.createLevelButton("🥉 DIFÍCIL", AppConstants.TITLE_COLOR_HEX);
        
        facilBtn.setOnAction(e -> showNivelIntroduccion("Fácil"));
        normalBtn.setOnAction(e -> showNivelIntroduccion("Normal"));
        dificilBtn.setOnAction(e -> showNivelIntroduccion("Difícil"));

        levelsContainer.getChildren().addAll(facilBtn, normalBtn, dificilBtn);

        Button backBtn = UIComponents.createSecondaryButton("↩️ VOLVER A LÍMITES");
        backBtn.setOnAction(e -> viewManager.showLimitesMenu());

        introLayout.getChildren().addAll(header, levelsContainer, backBtn);
        
        return introLayout;
    }
    
    private void showNivelIntroduccion(String nivel) {
        NivelIntroduccionView nivelView = new NivelIntroduccionView(viewManager, nivel, this);
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(nivelView.createView());
    }
    
    public void show() {
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(createView());
    }
}