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

public class MainMenuView {
        private ViewManager viewManager;
        private String currentUser;
        private int totalScore;

        public MainMenuView(ViewManager viewManager, String currentUser, int totalScore) {
                this.viewManager = viewManager;
                this.currentUser = currentUser;
                this.totalScore = totalScore;
        }

        public BorderPane createView() {
                BorderPane mainLayout = new BorderPane();
                mainLayout.setBackground(
                                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY,
                                                Insets.EMPTY)));

                // Header
                HBox header = new HBox(20);
                header.setPadding(new Insets(20, 40, 20, 40));
                header.setAlignment(Pos.CENTER_LEFT);
                header.setStyle("-fx-background-color: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 5);");

                Text appTitle = new Text("MatiMate");
                appTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
                appTitle.setFill(AppConstants.PRIMARY_COLOR);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                HBox userActions = new HBox(15);
                userActions.setAlignment(Pos.CENTER_RIGHT);

                Text welcomeText = new Text("Hola, " + currentUser);
                welcomeText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
                welcomeText.setFill(AppConstants.TEXT_COLOR);

                Text rankingText = new Text("🏆 Ranking: " + totalScore + " pts");
                rankingText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
                rankingText.setFill(AppConstants.BUTTON_COLOR);

                Button userBtn = UIComponents.createSmallButton("👤 Mi Perfil");
                Button logoutBtn = UIComponents.createSmallButton("🚪 Salir");

                userBtn.setOnAction(e -> viewManager.showProfile());
                logoutBtn.setOnAction(e -> viewManager.showLoginScreen());

                userActions.getChildren().addAll(welcomeText, rankingText, userBtn, logoutBtn);
                header.getChildren().addAll(appTitle, spacer, userActions);

                mainLayout.setTop(header);

                // Content
                VBox contentContainer = new VBox(30);
                contentContainer.setPadding(new Insets(40));
                contentContainer.setAlignment(Pos.TOP_CENTER);

                Text menuTitle = new Text("¿Qué quieres aprender hoy?");
                menuTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
                menuTitle.setFill(AppConstants.TITLE_COLOR);

                FlowPane cardsContainer = new FlowPane();
                cardsContainer.setHgap(30);
                cardsContainer.setVgap(30);
                cardsContainer.setAlignment(Pos.CENTER);
                cardsContainer.setPadding(new Insets(20));

                Button funcionesBtn = UIComponents.createContentCard("f(x)", "Funciones",
                                "Dominios, rangos y tipos de funciones");
                Button limitesBtn = UIComponents.createContentCard("lim", "Límites",
                                "Conceptos básicos, continuidad y cálculo");
                Button derivadasBtn = UIComponents.createContentCard("f'(x)", "Derivadas",
                                "Reglas de derivación y aplicaciones");
                Button integralesBtn = UIComponents.createContentCard("∫", "Integrales",
                                "Integrales indefinidas y definidas");
                Button practicaBtn = UIComponents.createContentCard("🎯", "Práctica",
                                "Ejercicios mixtos para ponerte a prueba");

                funcionesBtn.setOnAction(e -> viewManager.showFuncionesMenu());
                limitesBtn.setOnAction(e -> viewManager.showLimitesMenu());
                derivadasBtn.setOnAction(e -> viewManager.showDerivadasMenu());
                integralesBtn.setOnAction(e -> viewManager.showIntegralesMenu());
                practicaBtn.setOnAction(e -> viewManager.showComingSoon("Práctica"));

                cardsContainer.getChildren().addAll(funcionesBtn, limitesBtn, derivadasBtn, integralesBtn, practicaBtn);

                contentContainer.getChildren().addAll(menuTitle, cardsContainer);

                ScrollPane scrollPane = new ScrollPane(contentContainer);
                scrollPane.setFitToWidth(true);
                scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                mainLayout.setCenter(scrollPane);

                return mainLayout;
        }
}