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
import models.User;

public class ProfileView {
    private ViewManager viewManager;
    private User currentUser;
    
    public ProfileView(ViewManager viewManager, User currentUser) {
        this.viewManager = viewManager;
        this.currentUser = currentUser;
    }
    
    public VBox createView() {
        VBox profileLayout = new VBox(0);
        profileLayout.setBackground(new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(50, 20, 40, 20));
        header.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + ";");
        
        Text title = new Text("👤 PERFIL DE USUARIO");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        title.setFill(javafx.scene.paint.Color.WHITE);
        header.getChildren().add(title);

        VBox content = new VBox(25);
        content.setPadding(new Insets(30, 25, 30, 25));
        content.setAlignment(Pos.CENTER);

        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setMaxWidth(320);
        
        TextField nameField = UIComponents.createModernTextField("Nombre completo");
        nameField.setText(currentUser.getNombre());
        
        TextField emailField = UIComponents.createModernTextField("Correo electrónico");
        emailField.setText(currentUser.getEmail());
        emailField.setDisable(true); // No se puede cambiar el email
        emailField.setStyle(emailField.getStyle() + "-fx-opacity: 0.6;");
        
        PasswordField currentPasswordField = UIComponents.createModernPasswordField("Contraseña actual");
        PasswordField newPasswordField = UIComponents.createModernPasswordField("Nueva contraseña (opcional)");
        
        TextField phoneField = UIComponents.createModernTextField("Número de celular");
        phoneField.setText(currentUser.getTelefono() != null ? currentUser.getTelefono() : "");
        
        // Label de puntaje
        Label scoreLabel = new Label("Puntaje actual: " + currentUser.getScore() + " puntos");
        scoreLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        scoreLabel.setTextFill(AppConstants.TITLE_COLOR);
        
        // Label para mensajes
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        messageLabel.setVisible(false);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(320);
        
        Button saveBtn = UIComponents.createPrimaryButton("💾 GUARDAR CAMBIOS");
        saveBtn.setOnAction(e -> {
            String nombre = nameField.getText().trim();
            String telefono = phoneField.getText().trim();
            String currentPassword = currentPasswordField.getText();
            String newPassword = newPasswordField.getText();
            
            if (nombre.isEmpty()) {
                messageLabel.setText("❌ El nombre no puede estar vacío");
                messageLabel.setStyle("-fx-text-fill: #E53E3E; -fx-font-size: 12; -fx-font-weight: bold;");
                messageLabel.setVisible(true);
                return;
            }
            
            // Actualizar datos básicos
            currentUser.setNombre(nombre);
            currentUser.setTelefono(telefono);
            
            // Si quiere cambiar contraseña
            if (!newPassword.isEmpty()) {
                if (currentPassword.isEmpty()) {
                    messageLabel.setText("❌ Debes ingresar tu contraseña actual");
                    messageLabel.setStyle("-fx-text-fill: #E53E3E; -fx-font-size: 12; -fx-font-weight: bold;");
                    messageLabel.setVisible(true);
                    return;
                }
                
                if (newPassword.length() < 6) {
                    messageLabel.setText("❌ La nueva contraseña debe tener al menos 6 caracteres");
                    messageLabel.setStyle("-fx-text-fill: #E53E3E; -fx-font-size: 12; -fx-font-weight: bold;");
                    messageLabel.setVisible(true);
                    return;
                }
                
                boolean passwordChanged = viewManager.getUserManager().changePassword(
                    currentUser.getEmail(), 
                    currentPassword, 
                    newPassword
                );
                
                if (!passwordChanged) {
                    messageLabel.setText("❌ Contraseña actual incorrecta");
                    messageLabel.setStyle("-fx-text-fill: #E53E3E; -fx-font-size: 12; -fx-font-weight: bold;");
                    messageLabel.setVisible(true);
                    return;
                }
            } else {
                // Solo actualizar datos sin cambiar contraseña
                viewManager.getUserManager().updateUser(currentUser);
            }
            
            messageLabel.setText("✅ ¡Cambios guardados exitosamente!");
            messageLabel.setStyle("-fx-text-fill: #3EB566; -fx-font-size: 12; -fx-font-weight: bold;");
            messageLabel.setVisible(true);
            
            currentPasswordField.clear();
            newPasswordField.clear();
        });
        
        Button backBtn = UIComponents.createSecondaryButton("↩️ VOLVER AL MENÚ");
        backBtn.setOnAction(e -> viewManager.showMainMenu());
        
        formBox.getChildren().addAll(
            nameField, emailField, currentPasswordField, newPasswordField, 
            phoneField, scoreLabel, messageLabel, saveBtn, backBtn
        );

        content.getChildren().add(formBox);
        profileLayout.getChildren().addAll(header, content);
        
        return profileLayout;
    }
}