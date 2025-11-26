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
import utils.AppConstants;
import utils.UIComponents;
import views.ViewManager;
import views.cuestionarios.CuestionarioLateralesView;
import utils.YouTubePlayer;

public class LimitesLateralesView {
    private ViewManager viewManager;
    private LimiteFuncionView parentView;

    public LimitesLateralesView(ViewManager viewManager, LimiteFuncionView parentView) {
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

        Text title = new Text("LÍMITES LATERALES");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Aproximación por la izquierda y derecha");
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

        VBox conceptosSection = createConceptosSection();

        VBox contenidoSection = createContenidoLimitesLaterales();
        VBox videoSection = createVideoSection();
        VBox cuestionarioSection = createCuestionarioSection();

        mainContent.getChildren().addAll(
                conceptosSection, contenidoSection, videoSection, cuestionarioSection);

        scrollContent.setContent(mainContent);
        contentLayout.getChildren().addAll(header, scrollContent);

        return contentLayout;
    }

    private VBox createContenidoLimitesLaterales() {
        VBox section = new VBox(20);
        section.setStyle("-fx-background-color: white; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0;");
        section.setPadding(new Insets(25, 25, 25, 25));

        Label title = new Label("Límites laterales");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        title.setTextFill(AppConstants.TITLE_COLOR);

        TextArea contenidoText = new TextArea(
                "Cuando analizamos un límite, podemos acercarnos al punto p por dos lados:\n" +
                        "• Desde la derecha → valores de x mayores que p.\n" +
                        "• Desde la izquierda → valores de x menores que p.\n\n" +
                        "Por eso existen dos límites distintos:\n" +
                        "• Límite lateral por la derecha\n" +
                        "• Límite lateral por la izquierda\n\n" +
                        "Límite lateral por la derecha\nSe denota como:\nlim(x→p⁺) f(x) = L\n\n" +
                        "significa:\nCuando x se acerca a p por valores mayores que p (por la derecha), los valores de f(x) se acercan a L.\n\n"
                        +
                        "Límite lateral por la izquierda\nSe escribe como:\nlim(x→p⁻) f(x) = L\n\n" +
                        "significa:\nCuando x se acerca a p por valores menores que p (por la izquierda), los valores de f(x) se acercan a L.");
        contenidoText.setEditable(false);
        contenidoText.setWrapText(true);
        contenidoText.setStyle("-fx-font-size: 14; -fx-font-family: 'Segoe UI'; " +
                "-fx-background-color: transparent; -fx-border-color: transparent; " +
                "-fx-text-fill: #333333; -fx-padding: 0;");
        contenidoText.setPrefHeight(380);

        VBox formulasBox = new VBox(15);
        formulasBox.setStyle("-fx-background-color: #F7FAFC; -fx-background-radius: 10; -fx-padding: 15;");

        HBox primeraFila = new HBox(20);
        primeraFila.setAlignment(Pos.CENTER);

        Label y1Label = new Label("y₁ f(x)");
        y1Label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        y1Label.setTextFill(AppConstants.TEXT_COLOR);

        Label y2Label = new Label("y₂ f(x)");
        y2Label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        y2Label.setTextFill(AppConstants.TEXT_COLOR);

        primeraFila.getChildren().addAll(y1Label, y2Label);

        HBox segundaFila = new HBox(30);
        segundaFila.setAlignment(Pos.CENTER);

        Text formulaDerecha = new Text("lim(x→p⁺) f(x) = L");
        formulaDerecha.setFont(Font.font("Cambria Math", FontWeight.BOLD, 18));
        formulaDerecha.setFill(AppConstants.TITLE_COLOR);

        Text formulaIzquierda = new Text("lim(x→p⁻) f(x) = L");
        formulaIzquierda.setFont(Font.font("Cambria Math", FontWeight.BOLD, 18));
        formulaIzquierda.setFill(AppConstants.TITLE_COLOR);

        segundaFila.getChildren().addAll(formulaDerecha, formulaIzquierda);

        formulasBox.getChildren().addAll(primeraFila, segundaFila);

        section.getChildren().addAll(title, contenidoText, formulasBox);
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
                    "-fx-border-color: #72DDFF; -fx-border-width: 2; -fx-border-radius: 10; -fx-cursor: hand;");
        });

        videoContainer.setOnMouseExited(e -> {
            videoContainer.setStyle("-fx-background-color: #2D3748; -fx-background-radius: 10; " +
                    "-fx-border-color: #00AEEF; -fx-border-width: 2; -fx-border-radius: 10; -fx-cursor: hand;");
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

        Label mainTitle = new Label("LÍMITES");
        mainTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        mainTitle.setTextFill(AppConstants.TITLE_COLOR);

        Label subTitle = new Label("INTRODUCCIÓN");
        subTitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subTitle.setTextFill(AppConstants.LIGHT_TEXT);

        titlesBox.getChildren().addAll(mainTitle, subTitle);

        Button resolverBtn = new Button("Resolver Cuestionario");
        resolverBtn.setStyle("-fx-background-color: #3EB566; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 12 25; -fx-font-size: 14; " +
                "-fx-effect: dropshadow(gaussian, rgba(62,181,102,0.3), 10, 0, 0, 3);");
        resolverBtn.setOnAction(e -> {
            CuestionarioLateralesView cuestionarioView = new CuestionarioLateralesView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(cuestionarioView.createView());
        });

        HBox.setHgrow(titlesBox, Priority.ALWAYS);
        headerBox.getChildren().addAll(titlesBox, resolverBtn);

        section.getChildren().add(headerBox);
        return section;
    }

    private void reproducirVideo() {
        YouTubePlayer.playVideo(
                "https://www.youtube.com/watch?v=-Izg1gMA3-I",
                "Video Explicativo - Límites Laterales",
                "Comprende la aproximación por la izquierda y por la derecha");
    }

    public void show() {
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(createView());
    }

    private VBox createConceptosSection() {
        VBox section = new VBox(15);
        section.setStyle("-fx-background-color: white; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0;");
        section.setPadding(new Insets(20, 25, 20, 25));
        Label titleLabel = new Label("Conceptos");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        titleLabel.setTextFill(AppConstants.TITLE_COLOR);
        titleLabel.setPadding(new Insets(0, 0, 15, 0));
        HBox itemBox = new HBox(10);
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setStyle("-fx-cursor: hand;");
        itemBox.setPadding(new Insets(8, 0, 8, 0));
        javafx.scene.shape.Circle bullet = new javafx.scene.shape.Circle(4);
        bullet.setFill(AppConstants.PRIMARY_COLOR);
        Label itemLabel = new Label("Serie de ejercicios");
        itemLabel.setFont(Font.font("Segoe UI", 14));
        itemLabel.setTextFill(AppConstants.TEXT_COLOR);
        Text arrow = new Text("→");
        arrow.setFont(Font.font("Segoe UI", 14));
        arrow.setFill(AppConstants.LIGHT_TEXT);
        HBox.setHgrow(itemLabel, Priority.ALWAYS);
        itemBox.getChildren().addAll(bullet, itemLabel, arrow);
        itemBox.setOnMouseEntered(e -> {
            itemBox.setStyle("-fx-background-color: #F7FAFC; -fx-cursor: hand; -fx-padding: 8 5;");
            arrow.setFill(AppConstants.PRIMARY_COLOR);
        });
        itemBox.setOnMouseExited(e -> {
            itemBox.setStyle("-fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 8 0;");
            arrow.setFill(AppConstants.LIGHT_TEXT);
        });
        itemBox.setOnMouseClicked(e -> {
            EjerciciosLateralesView ejerciciosView = new EjerciciosLateralesView(viewManager, this);
            ejerciciosView.show();
        });
        section.getChildren().addAll(titleLabel, itemBox);
        return section;
    }
}