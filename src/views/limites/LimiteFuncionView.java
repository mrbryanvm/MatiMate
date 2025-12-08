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
import views.ViewManager;

public class LimiteFuncionView {
        private ViewManager viewManager;
        private LimitsFlowManager flowManager;

        public LimiteFuncionView(ViewManager viewManager) {
                this.viewManager = viewManager;
                this.flowManager = new LimitsFlowManager(viewManager);
        }

        public VBox createView() {
                VBox contentLayout = new VBox(0);
                contentLayout.setBackground(new Background(
                                new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

                LimitsContext.Topic currentTopic = LimitsContext.getInstance().getCurrentTopic();
                String topicTitle = LimitsContentProvider.getTheoryTitle(currentTopic);
                String topicContent = LimitsContentProvider.getTheoryContent(currentTopic);

                // Header
                StackPane header = new StackPane();
                header.setPadding(new Insets(35, 20, 25, 20));
                header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

                Button backButton = new Button("←");
                backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #184093; -fx-font-size: 20; " +
                                "-fx-font-weight: bold; -fx-border-color: transparent; -fx-cursor: hand;");
                backButton.setOnAction(e -> viewManager.showLimitesMenu());

                VBox headerContent = new VBox(3);
                headerContent.setAlignment(Pos.CENTER);

                Text title = new Text(topicTitle.toUpperCase());
                title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
                title.setFill(AppConstants.TITLE_COLOR);

                Text subtitle = new Text("Teoría Fundamental");
                subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
                subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

                headerContent.getChildren().addAll(title, subtitle);

                BorderPane headerLayout = new BorderPane();
                headerLayout.setLeft(backButton);
                headerLayout.setCenter(headerContent);
                header.getChildren().add(headerLayout);

                // Main Content Scroll
                ScrollPane scrollContent = new ScrollPane();
                scrollContent.setFitToWidth(true);
                scrollContent.setStyle("-fx-background: transparent; -fx-background-color: transparent; " +
                                "-fx-border-color: transparent; -fx-padding: 0;");

                VBox mainContent = new VBox(20);
                mainContent.setPadding(new Insets(25));
                mainContent.setBackground(new Background(
                                new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

                // Theory Section
                VBox section = new VBox(15);
                section.setStyle("-fx-background-color: white; -fx-background-radius: 10; " +
                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5, 0, 0, 2);");
                section.setPadding(new Insets(25));

                Label theoryTitle = new Label(topicTitle);
                theoryTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
                theoryTitle.setTextFill(AppConstants.TITLE_COLOR);

                TextArea content = new TextArea(topicContent);
                content.setEditable(false);
                content.setWrapText(true);
                content.setPrefHeight(300);
                content.setStyle("-fx-font-size: 14; -fx-font-family: 'Segoe UI'; " +
                                "-fx-background-color: transparent; -fx-border-color: transparent; " +
                                "-fx-text-fill: #333333; -fx-padding: 0; -fx-control-inner-background: white;");

                section.getChildren().addAll(theoryTitle, content);
                mainContent.getChildren().add(section);

                // Next Button
                Button nextButton = new Button("Siguiente →");
                nextButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX
                                + "; -fx-text-fill: white; " +
                                "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 12 30; -fx-font-size: 16; "
                                +
                                "-fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");
                nextButton.setOnAction(e -> flowManager.showTheoryQuestions());

                HBox buttonContainer = new HBox(nextButton);
                buttonContainer.setAlignment(Pos.CENTER);
                buttonContainer.setPadding(new Insets(20, 0, 40, 0));

                mainContent.getChildren().add(buttonContainer);

                scrollContent.setContent(mainContent);
                contentLayout.getChildren().addAll(header, scrollContent);

                return contentLayout;
        }

        public void show() {
                viewManager.getRoot().getChildren().clear();
                viewManager.getRoot().getChildren().add(createView());
        }
}