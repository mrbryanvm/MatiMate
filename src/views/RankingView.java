package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import models.User;
import utils.AppConstants;
import utils.UserManager;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RankingView {
        private ViewManager viewManager;
        private UserManager userManager;

        public RankingView(ViewManager viewManager) {
                this.viewManager = viewManager;
                this.userManager = UserManager.getInstance();
        }

        public VBox createView() {
                VBox layout = new VBox(0);
                layout.setBackground(
                                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY,
                                                Insets.EMPTY)));

                // Encabezado
                StackPane header = new StackPane();
                header.setPadding(new Insets(35, 20, 25, 20));
                header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

                Button backButton = new Button("←");
                backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #184093; -fx-font-size: 20; " +
                                "-fx-font-weight: bold; -fx-border-color: transparent; -fx-cursor: hand;");
                backButton.setOnAction(e -> viewManager.showMainMenu());

                VBox headerContent = new VBox(3);
                headerContent.setAlignment(Pos.CENTER);

                Text title = new Text("RANKING GLOBAL");
                title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
                title.setFill(AppConstants.TITLE_COLOR);

                Text subtitle = new Text("Los mejores estudiantes de MatiMate");
                subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
                subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

                headerContent.getChildren().addAll(title, subtitle);

                BorderPane headerLayout = new BorderPane();
                headerLayout.setLeft(backButton);
                headerLayout.setCenter(headerContent);
                header.getChildren().add(headerLayout);

                // Contenido principal
                VBox mainContent = new VBox(20);
                mainContent.setPadding(new Insets(40));
                mainContent.setAlignment(Pos.TOP_CENTER);
                mainContent.setMaxWidth(800);

                // Fila de encabezado
                HBox tableHeader = new HBox();
                tableHeader.setPadding(new Insets(15));
                tableHeader.setStyle("-fx-background-color: #4A5568; -fx-background-radius: 10 10 0 0;");

                Label rankH = new Label("#");
                rankH.setTextFill(javafx.scene.paint.Color.WHITE);
                rankH.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
                rankH.setPrefWidth(50);

                Label userH = new Label("Estudiante");
                userH.setTextFill(javafx.scene.paint.Color.WHITE);
                userH.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
                userH.setPrefWidth(300);
                HBox.setHgrow(userH, Priority.ALWAYS);

                Label scoreH = new Label("Puntaje");
                scoreH.setTextFill(javafx.scene.paint.Color.WHITE);
                scoreH.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
                scoreH.setPrefWidth(100);
                scoreH.setAlignment(Pos.CENTER_RIGHT);

                tableHeader.getChildren().addAll(rankH, userH, scoreH);
                mainContent.getChildren().add(tableHeader);

                // Lista de usuarios
                List<User> sortedUsers = userManager.getAllUsers().stream()
                                .sorted(Comparator.comparingInt(User::getScore).reversed())
                                .collect(Collectors.toList());

                VBox userList = new VBox(5);

                int rank = 1;
                for (User user : sortedUsers) {
                        HBox row = new HBox();
                        row.setPadding(new Insets(15));
                        row.setAlignment(Pos.CENTER_LEFT);

                        // Destacar usuario actual
                        boolean isCurrentUser = user.getEmail().equals(viewManager.getCurrentUser().getEmail());
                        String bgStyle = isCurrentUser ? "-fx-background-color: #EBF8FF;"
                                        : "-fx-background-color: white;";
                        row.setStyle(bgStyle
                                        + " -fx-background-radius: 5; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 2, 0, 0, 1);");

                        Label rankL = new Label(String.valueOf(rank));
                        rankL.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
                        rankL.setTextFill(AppConstants.TEXT_COLOR);
                        rankL.setPrefWidth(50);

                        Label userL = new Label(user.getNombre());
                        userL.setFont(Font.font("Segoe UI", isCurrentUser ? FontWeight.BOLD : FontWeight.NORMAL, 14));
                        userL.setTextFill(AppConstants.TEXT_COLOR);
                        userL.setPrefWidth(300);
                        HBox.setHgrow(userL, Priority.ALWAYS);

                        Label scoreL = new Label(user.getScore() + " pts");
                        scoreL.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
                        scoreL.setTextFill(AppConstants.PRIMARY_COLOR);
                        scoreL.setPrefWidth(100);
                        scoreL.setAlignment(Pos.CENTER_RIGHT);

                        row.getChildren().addAll(rankL, userL, scoreL);
                        userList.getChildren().add(row);
                        rank++;
                }

                ScrollPane scrollMap = new ScrollPane(userList);
                scrollMap.setFitToWidth(true);
                scrollMap.setStyle(
                                "-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");
                mainContent.getChildren().add(scrollMap);

                layout.getChildren().addAll(header, mainContent);
                return layout;
        }
}
