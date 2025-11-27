package utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;

/**
 * Utility class for opening YouTube videos in the system's default browser
 */
public class YouTubePlayer {

    /**
     * Opens a YouTube video in the system's default web browser
     * 
     * @param videoUrl    The full YouTube URL
     * @param title       The title to display
     * @param description The description to display
     */
    public static void playVideo(String videoUrl, String title, String description) {
        try {
            if (!Desktop.isDesktopSupported()) {
                showError("Tu sistema no soporta la apertura automática de navegadores.\n\nURL: " + videoUrl);
                return;
            }

            Desktop desktop = Desktop.getDesktop();

            if (!desktop.isSupported(Desktop.Action.BROWSE)) {
                showError("Tu sistema no soporta la apertura automática de navegadores.\n\nURL: " + videoUrl);
                return;
            }

            desktop.browse(new URI(videoUrl));
            showConfirmation(title, description);

        } catch (Exception e) {
            showError("Error al abrir el video:\n" + e.getMessage() + "\n\nURL: " + videoUrl);
            e.printStackTrace();
        }
    }

    /**
     * Opens a YouTube video in the system's default web browser with a default
     * title
     * 
     * @param videoUrl The full YouTube URL
     */
    public static void playVideo(String videoUrl) {
        playVideo(videoUrl, "Video Educativo", "Se ha abierto el video en tu navegador predeterminado.");
    }

    private static void showConfirmation(String title, String description) {
        Stage confirmStage = new Stage();
        confirmStage.setTitle("Video Abierto");
        confirmStage.initModality(Modality.NONE);

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #2D3748;");

        Label titleLabel = new Label("✓ Video abierto en el navegador");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#48BB78"));

        Label descLabel = new Label(title);
        descLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        descLabel.setTextFill(Color.WHITE);
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(350);

        Label infoLabel = new Label(description);
        infoLabel.setFont(Font.font("Segoe UI", 12));
        infoLabel.setTextFill(Color.web("#CBD5E0"));
        infoLabel.setWrapText(true);
        infoLabel.setMaxWidth(350);

        Button closeBtn = new Button("Cerrar");
        closeBtn.setStyle("-fx-background-color: #4299E1; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 5; -fx-padding: 8 20;");
        closeBtn.setOnAction(e -> confirmStage.close());

        layout.getChildren().addAll(titleLabel, descLabel, infoLabel, closeBtn);

        Scene scene = new Scene(layout, 400, 220);
        confirmStage.setScene(scene);
        confirmStage.show();
    }

    private static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No se pudo abrir el video");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
