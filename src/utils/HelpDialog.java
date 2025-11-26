package utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.User;
import java.util.List;

public class HelpDialog {
    
    public static void showUsersHelp(Stage owner) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setTitle("Usuarios de Prueba - MatiMate");
        
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: white;");
        
        Text title = new Text("👥 USUARIOS DE PRUEBA");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        title.setFill(AppConstants.TITLE_COLOR);
        
        Text subtitle = new Text("Puedes usar estos usuarios para iniciar sesión:");
        subtitle.setFont(Font.font("Segoe UI", 14));
        subtitle.setFill(AppConstants.TEXT_COLOR);
        
        VBox usersBox = new VBox(15);
        usersBox.setAlignment(Pos.CENTER_LEFT);
        usersBox.setStyle("-fx-background-color: #F7FAFC; -fx-padding: 20; -fx-background-radius: 10;");
        
        // Obtener usuarios de prueba
        List<User> users = UserManager.getInstance().getAllUsers();
        
        for (User user : users) {
            VBox userBox = new VBox(5);
            userBox.setStyle("-fx-background-color: white; -fx-padding: 15; " +
                           "-fx-background-radius: 8; -fx-border-color: #00AEEF; " +
                           "-fx-border-width: 1; -fx-border-radius: 8;");
            
            Label nameLabel = new Label("👤 " + user.getNombre());
            nameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
            nameLabel.setTextFill(AppConstants.TITLE_COLOR);
            
            Label emailLabel = new Label("📧 Email: " + user.getEmail());
            emailLabel.setFont(Font.font("Segoe UI", 12));
            emailLabel.setTextFill(AppConstants.TEXT_COLOR);
            
            Label passwordLabel = new Label("🔑 Contraseña: " + user.getPassword());
            passwordLabel.setFont(Font.font("Segoe UI", 12));
            passwordLabel.setTextFill(AppConstants.TEXT_COLOR);
            
            Label scoreLabel = new Label("⭐ Puntaje: " + user.getScore());
            scoreLabel.setFont(Font.font("Segoe UI", 12));
            scoreLabel.setTextFill(AppConstants.TEXT_COLOR);
            
            userBox.getChildren().addAll(nameLabel, emailLabel, passwordLabel, scoreLabel);
            usersBox.getChildren().add(userBox);
        }
        
        Text note = new Text("💡 Tip: También puedes registrar tu propia cuenta");
        note.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        note.setFill(AppConstants.BUTTON_COLOR);
        
        Button closeBtn = new Button("Cerrar");
        closeBtn.setStyle("-fx-background-color: #72DDFF; -fx-text-fill: white; " +
                         "-fx-font-weight: bold; -fx-background-radius: 10; " +
                         "-fx-padding: 10 30; -fx-font-size: 14;");
        closeBtn.setOnAction(e -> dialog.close());
        
        layout.getChildren().addAll(title, subtitle, usersBox, note, closeBtn);
        
        Scene scene = new Scene(layout, 500, 600);
        dialog.setScene(scene);
        dialog.show();
    }
}