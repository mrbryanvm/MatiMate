package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.LimitsContext;
import utils.LimitsContext.UserAnswer;
import views.ViewManager;
import java.util.List;

public class StatisticsView {
    private ViewManager viewManager;
    private LimitsFlowManager flowManager;

    public StatisticsView(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.flowManager = new LimitsFlowManager(viewManager);
    }

    public VBox createView() {
        VBox contentLayout = new VBox(0);
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        // Header
        StackPane header = new StackPane();
        header.setPadding(new Insets(35, 20, 25, 20));
        header.setStyle("-fx-background-color: #C6F6D5;"); // Light Green background for stats

        VBox headerContent = new VBox(3);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("ESTADÍSTICAS DE PRÁCTICA");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Resultados del tema actual");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);
        header.getChildren().add(headerContent);

        // Main Content
        ScrollPane scrollContent = new ScrollPane();
        scrollContent.setFitToWidth(true);
        scrollContent.setStyle("-fx-background: transparent; -fx-background-color: transparent; " +
                "-fx-border-color: transparent; -fx-padding: 0;");

        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40));
        mainContent.setAlignment(Pos.TOP_CENTER);

        // Stats List
        VBox statsBox = new VBox(15);
        statsBox.setMaxWidth(800);

        List<UserAnswer> answers = LimitsContext.getInstance().getCurrentPracticeAnswers();

        for (UserAnswer ans : answers) {
            VBox item = new VBox(10);
            item.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-padding: 20; " +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5, 0, 0, 2);");

            // Question Text
            Label qLabel = new Label(ans.question);
            qLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
            qLabel.setTextFill(AppConstants.TEXT_COLOR);
            qLabel.setWrapText(true);

            // Columns for details
            HBox details = new HBox(40); // Spacing between columns
            details.setAlignment(Pos.CENTER_LEFT);

            // Your Answer
            VBox yourAns = new VBox(5);
            Label yourLabel = new Label("Tu respuesta:");
            yourLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #718096;");
            Label yourVal = new Label(ans.selectedOption);
            yourVal.setWrapText(true);
            // Assuming max width for wrapping text in table-like structure
            yourVal.setMaxWidth(200);
            yourVal.setStyle("-fx-font-weight: bold; -fx-text-fill: " + (ans.isCorrect ? "green" : "red") + ";");
            yourAns.getChildren().addAll(yourLabel, yourVal);

            // Correct Answer
            VBox correctAns = new VBox(5);
            Label correctLabel = new Label("Correcta:");
            correctLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #718096;");
            Label correctVal = new Label(ans.correctOption);
            correctVal.setWrapText(true);
            correctVal.setMaxWidth(200);
            correctVal.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");
            correctAns.getChildren().addAll(correctLabel, correctVal);

            // Points
            VBox pointsBox = new VBox(5);
            Label pointsLabel = new Label("Puntos:");
            pointsLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #718096;");
            Label pointsVal = new Label((ans.points > 0 ? "+" : "") + ans.points);
            pointsVal.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
            pointsVal.setStyle("-fx-text-fill: " + (ans.points > 0 ? "blue" : "red") + ";");
            pointsBox.getChildren().addAll(pointsLabel, pointsVal);

            details.getChildren().addAll(yourAns, correctAns, pointsBox);
            item.getChildren().addAll(qLabel, details);
            statsBox.getChildren().add(item);
        }

        mainContent.getChildren().add(statsBox);

        // Next Topic Button
        LimitsContext.Topic current = LimitsContext.getInstance().getCurrentTopic();
        String nextButtonText = (current == LimitsContext.Topic.PROPIEDADES) ? "Finalizar y Ver Ranking"
                : "Siguiente Tema";

        Button nextButton = new Button(nextButtonText);
        nextButton.setStyle("-fx-background-color: #63B3ED; -fx-text-fill: white; " + // Light Blue button
                "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 15 40; -fx-font-size: 18; " +
                "-fx-cursor: hand;");

        nextButton.setOnAction(e -> {
            flowManager.nextTopic();
        });

        mainContent.getChildren().add(nextButton);

        scrollContent.setContent(mainContent);
        contentLayout.getChildren().addAll(header, scrollContent);
        return contentLayout;
    }
}
