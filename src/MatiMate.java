import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import views.*;
import utils.AppConstants;

public class MatiMate extends Application {
    private Stage primaryStage;
    private StackPane root;
    private ViewManager viewManager;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("MatiMate - Aprende Cálculo Diferencial");

        root = new StackPane();
        root.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + ";");

        viewManager = new ViewManager(root, primaryStage);
        viewManager.showLoginScreen();

        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}