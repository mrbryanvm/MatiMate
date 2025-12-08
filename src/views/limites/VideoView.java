package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.LimitsContentProvider;
import utils.LimitsContext;
import utils.YouTubePlayer;
import views.ViewManager;

public class VideoView {
    private ViewManager viewManager;
    private LimitsFlowManager flowManager;

    public VideoView(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.flowManager = new LimitsFlowManager(viewManager);
    }

    public VBox createView() {
        VBox contentLayout = new VBox(0);
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        String[] videoData = LimitsContentProvider.getVideoData(LimitsContext.getInstance().getCurrentTopic());
        String videoUrl = videoData[0];
        String videoTitle = videoData[1];
        String videoSubtitle = videoData[2];

        // Header
        StackPane header = new StackPane();
        header.setPadding(new Insets(35, 20, 25, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

        Button backButton = new Button("←");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #184093; -fx-font-size: 20; " +
                "-fx-font-weight: bold; -fx-border-color: transparent; -fx-cursor: hand;");
        backButton.setOnAction(e -> flowManager.showTheoryQuestions());

        VBox headerContent = new VBox(3);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("VIDEO EXPLICATIVO");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text(videoTitle);
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);

        BorderPane headerLayout = new BorderPane();
        headerLayout.setLeft(backButton);
        headerLayout.setCenter(headerContent);
        header.getChildren().add(headerLayout);

        // Main Content
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40));
        mainContent.setAlignment(Pos.TOP_CENTER);

        // Video Card
        VBox videoCard = new VBox(20);
        videoCard.setAlignment(Pos.CENTER);
        videoCard.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-padding: 40; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        videoCard.setMaxWidth(600);

        Label infoLabel = new Label(videoSubtitle);
        infoLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 16));
        infoLabel.setTextFill(AppConstants.TEXT_COLOR);
        infoLabel.setWrapText(true);
        infoLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Button playButton = new Button("▶ Reproducir Video en YouTube");
        playButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 15 30; -fx-font-size: 16; " +
                "-fx-cursor: hand;");
        playButton.setOnAction(e -> YouTubePlayer.playVideo(videoUrl, videoTitle, videoSubtitle));

        videoCard.getChildren().addAll(infoLabel, playButton);

        // Next Button
        Button nextButton = new Button("Siguiente");
        nextButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + "; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 12 30; -fx-font-size: 16; " +
                "-fx-cursor: hand;");
        nextButton.setOnAction(e -> flowManager.showQuiz());

        mainContent.getChildren().addAll(videoCard, nextButton);

        // Menu Button
        Button menuButton = new Button("Volver al Menú");
        menuButton.setStyle("-fx-background-color: #718096; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 10 20;");
        menuButton.setOnAction(e -> viewManager.showLimitesMenu());

        mainContent.getChildren().add(menuButton);

        contentLayout.getChildren().addAll(header, mainContent);
        return contentLayout;
    }
}
