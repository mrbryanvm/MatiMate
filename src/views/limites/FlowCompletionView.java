package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import views.ViewManager;

public class FlowCompletionView {
    private ViewManager viewManager;
    private int finalScore;
    private int oldBestScore;
    private Runnable onFinish;

    public FlowCompletionView(ViewManager viewManager, int finalScore, int oldBestScore, Runnable onFinish) {
        this.viewManager = viewManager;
        this.finalScore = finalScore;
        this.oldBestScore = oldBestScore;
        this.onFinish = onFinish;
    }

    public VBox createView() {
        VBox layout = new VBox(0);
        layout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.setAlignment(Pos.CENTER);

        // Card
        VBox card = new VBox(25);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(40));
        card.setMaxWidth(500);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 20; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 5);");

        // Icon/Emoji
        Label emoji = new Label(finalScore >= oldBestScore ? "🎉" : "💪");
        emoji.setFont(Font.font("Segoe UI", 60));

        // Title
        Text title = new Text("¡Sección Completada!");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setFill(AppConstants.TITLE_COLOR);

        // Score display
        VBox scoreBox = new VBox(10);
        scoreBox.setAlignment(Pos.CENTER);

        Text scoreText = new Text("Puntaje Obtenido");
        scoreText.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 16));
        scoreText.setFill(AppConstants.TEXT_COLOR);

        Text scoreVal = new Text(finalScore + " pts");
        scoreVal.setFont(Font.font("Segoe UI", FontWeight.BOLD, 48));
        scoreVal.setFill(AppConstants.PRIMARY_COLOR);

        scoreBox.getChildren().addAll(scoreText, scoreVal);

        // Feedback / Comparison
        VBox feedbackBox = new VBox(5);
        feedbackBox.setAlignment(Pos.CENTER);

        Label feedbackLabel = new Label();
        feedbackLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));

        if (finalScore > oldBestScore) {
            feedbackLabel.setText("¡Felicidades! Has superado tu récord anterior (" + oldBestScore + " pts).");
            feedbackLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } else if (finalScore == oldBestScore) {
            feedbackLabel.setText("Has igualado tu mejor puntaje (" + oldBestScore + " pts).");
            feedbackLabel.setTextFill(AppConstants.TITLE_COLOR);
        } else {
            feedbackLabel.setText("Tu mejor puntaje sigue siendo " + oldBestScore + " pts. ¡Sigue practicando!");
            feedbackLabel.setTextFill(AppConstants.TITLE_COLOR);
        }

        feedbackBox.getChildren().add(feedbackLabel);

        // Action Button
        Button finishButton = new Button("Volver al Menú");
        finishButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + "; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 25; -fx-padding: 12 30; -fx-cursor: hand;");
        finishButton.setOnAction(e -> onFinish.run());

        card.getChildren().addAll(emoji, title, scoreBox, feedbackBox, finishButton);
        layout.getChildren().add(card);

        return layout;
    }
}
