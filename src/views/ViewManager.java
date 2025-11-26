package views;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.User;
import utils.UserManager;

public class ViewManager {
    private StackPane root;
    private Stage primaryStage;
    private User currentUser;
    private UserManager userManager;
    
    private LoginView loginView;
    private RegisterView registerView;
    private MainMenuView mainMenuView;
    private LimitesMenuView limitesMenuView;
    private ProfileView profileView;
    
    public ViewManager(StackPane root, Stage primaryStage) {
        this.root = root;
        this.primaryStage = primaryStage;
        this.userManager = UserManager.getInstance();
        initializeViews();
    }
    
    private void initializeViews() {
        loginView = new LoginView(this);
        registerView = new RegisterView(this);
        limitesMenuView = new LimitesMenuView(this);
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
        // Reinicializar vistas que dependen del usuario
        mainMenuView = new MainMenuView(this, currentUser.getNombre(), currentUser.getScore());
        profileView = new ProfileView(this, currentUser);
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public UserManager getUserManager() {
        return userManager;
    }
    
    public void showLoginScreen() {
        root.getChildren().clear();
        root.getChildren().add(loginView.createView());
    }
    
    public void showRegisterScreen() {
        root.getChildren().clear();
        root.getChildren().add(registerView.createView());
    }
    
    public void showMainMenu() {
        root.getChildren().clear();
        root.getChildren().add(mainMenuView.createView());
    }
    
    public void showLimitesMenu() {
        root.getChildren().clear();
        root.getChildren().add(limitesMenuView.createView());
    }
    
    public void showProfile() {
        root.getChildren().clear();
        root.getChildren().add(profileView.createView());
    }
    
    public void showComingSoon(String feature) {
        ComingSoonView comingSoonView = new ComingSoonView(this, feature);
        root.getChildren().clear();
        root.getChildren().add(comingSoonView.createView());
    }
    
    public StackPane getRoot() {
        return root;
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}