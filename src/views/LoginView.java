package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.UIComponents;

public class LoginView {
    private ViewManager viewManager;

    public LoginView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public StackPane createView() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: white;");

        HBox mainLayout = new HBox();
        mainLayout.setAlignment(Pos.CENTER);

        // Left Side - Branding
        VBox brandingBox = new VBox(20);
        brandingBox.setAlignment(Pos.CENTER);
        brandingBox.setPrefWidth(500);
        brandingBox.setStyle("-fx-background-color: linear-gradient(to bottom right, " + AppConstants.PRIMARY_COLOR_HEX
                + ", " + AppConstants.SECONDARY_COLOR_HEX + ");");
        HBox.setHgrow(brandingBox, Priority.ALWAYS);

        createBeautifulLogo(brandingBox);

        Text titleText = new Text("MatiMate");
        titleText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 48));
        titleText.setFill(Color.WHITE);
        titleText.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 3);");

        Text subtitle = new Text("Aprende Cálculo Diferencial\nde forma interactiva");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 24));
        subtitle.setFill(Color.WHITE);
        subtitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        brandingBox.getChildren().addAll(titleText, subtitle);

        // Right Side - Login Form
        VBox formContainer = new VBox(30);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPrefWidth(500);
        formContainer.setPadding(new Insets(50));
        formContainer.setStyle("-fx-background-color: white;");
        HBox.setHgrow(formContainer, Priority.ALWAYS);

        Text loginTitle = new Text("Bienvenido de nuevo");
        loginTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
        loginTitle.setFill(AppConstants.TITLE_COLOR);

        VBox formBox = new VBox(20);
        formBox.setAlignment(Pos.CENTER);
        formBox.setMaxWidth(400);

        VBox emailBox = new VBox(8);
        emailBox.setAlignment(Pos.CENTER_LEFT);
        Label emailLabel = new Label("CORREO ELECTRÓNICO");
        emailLabel
                .setStyle("-fx-text-fill: #666666; -fx-font-weight: bold; -fx-font-size: 12; -fx-letter-spacing: 1px;");
        TextField emailField = UIComponents.createModernTextField("tu@ejemplo.com");
        emailField.setMaxWidth(Double.MAX_VALUE);
        emailBox.getChildren().addAll(emailLabel, emailField);

        VBox passwordBox = new VBox(8);
        passwordBox.setAlignment(Pos.CENTER_LEFT);
        Label passwordLabel = new Label("CONTRASEÑA");
        passwordLabel
                .setStyle("-fx-text-fill: #666666; -fx-font-weight: bold; -fx-font-size: 12; -fx-letter-spacing: 1px;");
        PasswordField passwordField = UIComponents.createModernPasswordField("Ingresa tu contraseña");
        passwordField.setMaxWidth(Double.MAX_VALUE);
        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        // Label para mensajes de error
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #E53E3E; -fx-font-size: 14; -fx-font-weight: bold;");
        errorLabel.setVisible(false);

        Button loginBtn = UIComponents.createPrimaryButton("INICIAR SESIÓN");
        loginBtn.setMaxWidth(Double.MAX_VALUE);
        loginBtn.setOnAction(e -> {
            String email = emailField.getText().trim();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Por favor completa todos los campos");
                errorLabel.setVisible(true);
                return;
            }

            models.User user = viewManager.getUserManager().authenticate(email, password);

            if (user != null) {
                UIComponents.animateButton(loginBtn);
                errorLabel.setVisible(false);
                viewManager.setCurrentUser(user);
                viewManager.showMainMenu();
            } else {
                errorLabel.setText("❌ Correo o contraseña incorrectos");
                errorLabel.setVisible(true);
                passwordField.clear();
            }
        });

        HBox linksBox = new HBox(20);
        linksBox.setAlignment(Pos.CENTER);

        Hyperlink registerLink = new Hyperlink("Crear cuenta nueva");
        registerLink.setStyle("-fx-text-fill: " + AppConstants.PRIMARY_COLOR_HEX
                + "; -fx-font-size: 14; -fx-border-color: transparent; -fx-underline: false; -fx-font-weight: bold;");
        registerLink.setOnAction(e -> viewManager.showRegisterScreen());

        linksBox.getChildren().addAll(registerLink);

        formBox.getChildren().addAll(emailBox, passwordBox, errorLabel, loginBtn, linksBox);
        formContainer.getChildren().addAll(loginTitle, formBox);

        mainLayout.getChildren().addAll(brandingBox, formContainer);
        root.getChildren().add(mainLayout);

        return root;
    }

    private void createBeautifulLogo(VBox container) {
        try {
            Image logoImage = new Image(AppConstants.LOGO_PATH);
            ImageView logoView = new ImageView(logoImage);
            logoView.setFitWidth(200);
            logoView.setFitHeight(200);
            logoView.setPreserveRatio(true);
            logoView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0, 0, 5);");

            container.getChildren().add(logoView);
        } catch (Exception e) {
            Text logoText = new Text("🧮");
            logoText.setFont(Font.font("Segoe UI", FontWeight.EXTRA_BOLD, 120));
            logoText.setFill(Color.WHITE);
            logoText.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0, 0, 5);");
            container.getChildren().add(logoText);
        }
    }
}