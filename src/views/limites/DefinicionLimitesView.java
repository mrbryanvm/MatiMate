package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.AppConstants;
import utils.UIComponents;
import utils.YouTubePlayer;
import views.ViewManager;
import views.cuestionarios.CuestionarioDefinicionView;

public class DefinicionLimitesView {
    private ViewManager viewManager;
    private LimiteFuncionView parentView;

    public DefinicionLimitesView(ViewManager viewManager, LimiteFuncionView parentView) {
        this.viewManager = viewManager;
        this.parentView = parentView;
    }

    public VBox createView() {
        VBox contentLayout = new VBox(0);
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(35, 20, 25, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

        Button backButton = new Button("←");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #184093; -fx-font-size: 20; " +
                "-fx-font-weight: bold; -fx-border-color: transparent; -fx-cursor: hand;");
        backButton.setOnAction(e -> parentView.show());

        VBox headerContent = new VBox(3);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("DEFINICIÓN");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Límites de una función");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);

        BorderPane headerLayout = new BorderPane();
        headerLayout.setLeft(backButton);
        headerLayout.setCenter(headerContent);
        header.getChildren().add(headerLayout);

        ScrollPane scrollContent = new ScrollPane();
        scrollContent.setFitToWidth(true);
        scrollContent.setStyle("-fx-background: transparent; -fx-background-color: transparent; " +
                "-fx-border-color: transparent; -fx-padding: 0;");

        VBox mainContent = new VBox(0);
        mainContent.setPadding(new Insets(0, 0, 20, 0));
        mainContent.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox definicionSection = createDefinicionSection();
        VBox videoSection = createVideoSection();
        VBox cuestionarioSection = createCuestionarioSection();

        mainContent.getChildren().addAll(
                definicionSection, videoSection,
                cuestionarioSection);

        scrollContent.setContent(mainContent);
        contentLayout.getChildren().addAll(header, scrollContent);

        return contentLayout;
    }

    private VBox createDefinicionSection() {
        VBox section = new VBox(15);
        section.setStyle("-fx-background-color: white; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0;");
        section.setPadding(new Insets(25, 25, 25, 25));

        Label title = new Label("Definición");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        title.setTextFill(AppConstants.TITLE_COLOR);

        TextArea definicionText = new TextArea(
                "El límite de una función f(x) cuando x se aproxima a un valor a, es el número L al cual se acercan los valores de f(x) cuando x se acerca a a.\n\n"
                        +
                        "Se escribe:\nlim(x→a) f(x) = L\n\n" +
                        "La idea del límite es que cuando los valores de x se acercan a un punto p, los valores de la función f(x) se acercan a un número L.\n"
                        +
                        "• Si los puntos cercanos a p tienen imágenes cercanas a L, entonces L es el límite.\n" +
                        "• Si existe algún punto cercano a p cuya imagen está lejos de L, entonces L no es el límite.\n\n"
                        +
                        "Para determinar el límite de una función en p, se puede evaluar f(x) en valores de x cada vez más próximos a p, observando hacia qué valor se aproximan los resultados.\n"
                        +
                        "Ese valor será, posiblemente, el límite buscado.");
        definicionText.setEditable(false);
        definicionText.setWrapText(true);
        definicionText.setStyle("-fx-font-size: 14; -fx-font-family: 'Segoe UI'; " +
                "-fx-background-color: transparent; -fx-border-color: transparent; " +
                "-fx-text-fill: #333333; -fx-padding: 0;");
        definicionText.setPrefHeight(280);

        section.getChildren().addAll(title, definicionText);
        return section;
    }

    private VBox createVideoSection() {
        VBox section = new VBox(15);
        section.setStyle("-fx-background-color: white; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0;");
        section.setPadding(new Insets(25, 25, 25, 25));

        Label title = new Label("Video Explicativo");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        title.setTextFill(AppConstants.TITLE_COLOR);

        VBox videoContainer = new VBox(10);
        videoContainer.setAlignment(Pos.CENTER);
        videoContainer.setStyle("-fx-background-color: #2D3748; -fx-background-radius: 10; " +
                "-fx-border-color: #00AEEF; -fx-border-width: 2; -fx-border-radius: 10;");
        videoContainer.setPrefSize(340, 200);
        videoContainer.setPadding(new Insets(20));

        Text playIcon = new Text("▶");
        playIcon.setFont(Font.font("Segoe UI", FontWeight.BOLD, 36));
        playIcon.setFill(Color.WHITE);

        Label videoText = new Label("Haz clic para reproducir el video");
        videoText.setFont(Font.font("Segoe UI", 14));
        videoText.setTextFill(Color.WHITE);

        videoContainer.getChildren().addAll(playIcon, videoText);

        videoContainer.setStyle(videoContainer.getStyle() + " -fx-cursor: hand;");
        videoContainer.setOnMouseClicked(e -> reproducirVideo());

        videoContainer.setOnMouseEntered(e -> {
            videoContainer.setStyle("-fx-background-color: #4A5568; -fx-background-radius: 10; " +
                    "-fx-border-color: #72DDFF; -fx-border-width: 2; -fx-border-radius: 10; " +
                    "-fx-cursor: hand;");
        });

        videoContainer.setOnMouseExited(e -> {
            videoContainer.setStyle("-fx-background-color: #2D3748; -fx-background-radius: 10; " +
                    "-fx-border-color: #00AEEF; -fx-border-width: 2; -fx-border-radius: 10; " +
                    "-fx-cursor: hand;");
        });

        section.getChildren().addAll(title, videoContainer);
        return section;
    }

    private VBox createCuestionarioSection() {
        VBox section = new VBox(15);
        section.setStyle("-fx-background-color: white;");
        section.setPadding(new Insets(25, 25, 25, 25));

        HBox headerBox = new HBox(20);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        VBox titlesBox = new VBox(2);

        Label mainTitle = new Label("PRÁCTICA");
        mainTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        mainTitle.setTextFill(AppConstants.TITLE_COLOR);

        Label subTitle = new Label("Ejercicios y Evaluación");
        subTitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subTitle.setTextFill(AppConstants.LIGHT_TEXT);

        titlesBox.getChildren().addAll(mainTitle, subTitle);
        HBox.setHgrow(titlesBox, Priority.ALWAYS);

        HBox buttonsBox = new HBox(15);
        buttonsBox.setAlignment(Pos.CENTER_RIGHT);

        Button ejerciciosBtn = new Button("Serie de ejercicios");
        ejerciciosBtn.setStyle("-fx-background-color: #4A5568; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 12 25; -fx-font-size: 14; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");
        ejerciciosBtn.setOnAction(e -> {
            EjerciciosDefinicionView ejerciciosView = new EjerciciosDefinicionView(viewManager, this);
            ejerciciosView.show();
        });

        Button resolverBtn = new Button("Resolver Cuestionario");
        resolverBtn.setStyle("-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 12 25; -fx-font-size: 14; " +
                "-fx-effect: dropshadow(gaussian, rgba(62,181,102,0.3), 10, 0, 0, 3);");
        resolverBtn.setOnAction(e -> {
            CuestionarioDefinicionView cuestionarioView = new CuestionarioDefinicionView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(cuestionarioView.createView());
        });

        buttonsBox.getChildren().addAll(ejerciciosBtn, resolverBtn);
        headerBox.getChildren().addAll(titlesBox, buttonsBox);

        section.getChildren().add(headerBox);
        return section;
    }

    private void reproducirVideo() {
        YouTubePlayer.playVideo(
                "https://www.youtube.com/watch?v=IFa_T-po71g",
                "Video Explicativo - Definición de Límites",
                "Aprende los conceptos fundamentales de los límites de una función");
    }

    public void show() {
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(createView());
    }
}