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

public class RegisterView {
    private ViewManager viewManager;

    public RegisterView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public StackPane createView() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: white;");

        HBox mainLayout = new HBox();
        mainLayout.setAlignment(Pos.CENTER);

        VBox brandingBox = new VBox(20);
        brandingBox.setAlignment(Pos.CENTER);
        brandingBox.setPrefWidth(500);
        brandingBox.setStyle("-fx-background-color: linear-gradient(to bottom right, " + AppConstants.PRIMARY_COLOR_HEX
                + ", " + AppConstants.SECONDARY_COLOR_HEX + ");");
        HBox.setHgrow(brandingBox, Priority.ALWAYS);

        createBeautifulLogo(brandingBox);

        Text titleText = new Text("Únete a MatiMate");
        titleText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 48));
        titleText.setFill(Color.WHITE);
        titleText.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 3);");

        Text subtitle = new Text("Crea tu cuenta y comienza\na aprender hoy mismo");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 24));
        subtitle.setFill(Color.WHITE);
        subtitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        brandingBox.getChildren().addAll(titleText, subtitle);

        // Right Side - Register Form
        VBox formContainer = new VBox(20);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPrefWidth(500);
        formContainer.setPadding(new Insets(40));
        formContainer.setStyle("-fx-background-color: white;");
        HBox.setHgrow(formContainer, Priority.ALWAYS);

        Text registerTitle = new Text("Crear Cuenta");
        registerTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
        registerTitle.setFill(AppConstants.TITLE_COLOR);

        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setMaxWidth(400);

        TextField nameField = UIComponents.createModernTextField("Nombre completo");
        TextField emailField = UIComponents.createModernTextField("Correo electrónico");
        PasswordField passwordField = UIComponents.createModernPasswordField("Contraseña");
        PasswordField confirmField = UIComponents.createModernPasswordField("Confirmar contraseña");
        TextField phoneField = UIComponents.createModernTextField("Celular (Opcional)");

        // Ajustar anchos
        nameField.setMaxWidth(Double.MAX_VALUE);
        emailField.setMaxWidth(Double.MAX_VALUE);
        passwordField.setMaxWidth(Double.MAX_VALUE);
        confirmField.setMaxWidth(Double.MAX_VALUE);
        phoneField.setMaxWidth(Double.MAX_VALUE);

        // Label para mensajes
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        messageLabel.setVisible(false);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(380);

        Button registerBtn = UIComponents.createPrimaryButton("REGISTRARSE");
        registerBtn.setMaxWidth(Double.MAX_VALUE);
        registerBtn.setOnAction(e -> {
            String nombre = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText();
            String confirmPassword = confirmField.getText();
            String telefono = phoneField.getText().trim();

            // Validaciones
            if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                messageLabel.setText("❌ Por favor completa todos los campos obligatorios");
                messageLabel.setStyle("-fx-text-fill: #E53E3E; -fx-font-size: 12; -fx-font-weight: bold;");
                messageLabel.setVisible(true);
                return;
            }

            if (!email.contains("@") || !email.contains(".")) {
                messageLabel.setText("❌ Por favor ingresa un correo válido");
                messageLabel.setStyle("-fx-text-fill: #E53E3E; -fx-font-size: 12; -fx-font-weight: bold;");
                messageLabel.setVisible(true);
                return;
            }

            if (password.length() < 6) {
                messageLabel.setText("❌ La contraseña debe tener al menos 6 caracteres");
                messageLabel.setStyle("-fx-text-fill: #E53E3E; -fx-font-size: 12; -fx-font-weight: bold;");
                messageLabel.setVisible(true);
                return;
            }

            if (!password.equals(confirmPassword)) {
                messageLabel.setText("❌ Las contraseñas no coinciden");
                messageLabel.setStyle("-fx-text-fill: #E53E3E; -fx-font-size: 12; -fx-font-weight: bold;");
                messageLabel.setVisible(true);
                return;
            }

            // Intentar registrar
            boolean success = viewManager.getUserManager().register(email, password, nombre, telefono);

            if (success) {
                UIComponents.animateButton(registerBtn);
                messageLabel.setText("✅ ¡Registro exitoso! Redirigiendo...");
                messageLabel.setStyle("-fx-text-fill: #3EB566; -fx-font-size: 14; -fx-font-weight: bold;");
                messageLabel.setVisible(true);

                // Pequeño delay antes de redirigir
                javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                        javafx.util.Duration.seconds(1.5));
                pause.setOnFinished(event -> viewManager.showLoginScreen());
                pause.play();
            } else {
                messageLabel.setText("❌ El correo ya está registrado");
                messageLabel.setStyle("-fx-text-fill: #E53E3E; -fx-font-size: 12; -fx-font-weight: bold;");
                messageLabel.setVisible(true);
            }
        });

        HBox linksBox = new HBox(10);
        linksBox.setAlignment(Pos.CENTER);

        Label accountLabel = new Label("¿Ya tienes cuenta?");
        accountLabel.setStyle("-fx-text-fill: #666666; -fx-font-size: 14;");

        Hyperlink loginLink = new Hyperlink("Inicia Sesión");
        loginLink.setStyle("-fx-text-fill: " + AppConstants.PRIMARY_COLOR_HEX
                + "; -fx-font-size: 14; -fx-border-color: transparent; -fx-underline: false; -fx-font-weight: bold;");
        loginLink.setOnAction(e -> viewManager.showLoginScreen());

        linksBox.getChildren().addAll(accountLabel, loginLink);

        formBox.getChildren().addAll(
                nameField, emailField, passwordField, confirmField, phoneField, messageLabel, registerBtn, linksBox);

        formContainer.getChildren().addAll(registerTitle, formBox);

        mainLayout.getChildren().addAll(brandingBox, formContainer);
        root.getChildren().add(mainLayout);

        return root;
    }

    private void createBeautifulLogo(VBox container) {
        try {
            Image logoImage = new Image(AppConstants.LOGO_PATH);
            ImageView logoView = new ImageView(logoImage);
            logoView.setFitWidth(150);
            logoView.setFitHeight(150);
            logoView.setPreserveRatio(true);
            logoView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0, 0, 5);");

            container.getChildren().add(logoView);
        } catch (Exception e) {
            Text logoText = new Text("🧮");
            logoText.setFont(Font.font("Segoe UI", FontWeight.EXTRA_BOLD, 100));
            logoText.setFill(Color.WHITE);
            logoText.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0, 0, 5);");
            container.getChildren().add(logoText);
        }
    }
}