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
    private FuncionesMenuView funcionesMenuView;
    private DerivadasMenuView derivadasMenuView;
    private IntegralesMenuView integralesMenuView;
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
        funcionesMenuView = new FuncionesMenuView(this);
        derivadasMenuView = new DerivadasMenuView(this);
        integralesMenuView = new IntegralesMenuView(this);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        mainMenuView = new MainMenuView(this, currentUser.getNombre(), currentUser.getScore());
        profileView = new ProfileView(this, currentUser);
    }

    public void updateUserScore(int newRunScore) {
        if (currentUser != null) {
            // Logic: Update only if the new run score is higher than the current best score
            if (newRunScore > currentUser.getScore()) {
                currentUser.setScore(newRunScore);
                // PERSISTE PUNTAJE
                userManager.updateUser(currentUser);
                // Refresh main menu with new score
                mainMenuView = new MainMenuView(this, currentUser.getNombre(), newRunScore);
            }
            // If not higher, do not save or update main menu, keeping the previous high
            // score.
        }
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

    public void showRanking() {
        RankingView rankingView = new RankingView(this);
        root.getChildren().clear();
        root.getChildren().add(rankingView.createView());
    }

    public void updateView(javafx.scene.layout.Pane view) {
        root.getChildren().clear();
        root.getChildren().add(view);
    }

    public void showLimitesMenu() {
        root.getChildren().clear();
        root.getChildren().add(limitesMenuView.createView());
    }

    public void showFuncionesMenu() {
        root.getChildren().clear();
        root.getChildren().add(funcionesMenuView.createView());
    }

    public void showDerivadasMenu() {
        root.getChildren().clear();
        root.getChildren().add(derivadasMenuView.createView());
    }

    public void showIntegralesMenu() {
        root.getChildren().clear();
        root.getChildren().add(integralesMenuView.createView());
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