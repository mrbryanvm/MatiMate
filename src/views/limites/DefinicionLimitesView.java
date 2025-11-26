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
        contentLayout.setBackground(new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

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
        mainContent.setBackground(new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox conceptosSection = UIComponents.createSection("Conceptos", new String[]{
            "Serie de ejercicios", "Práctica"
        });

        VBox definicionSection = createDefinicionSection();
        VBox videoSection = createVideoSection();
        VBox limitesLateralesSection = createLimitesLateralesSection();
        VBox cuestionarioSection = createCuestionarioSection();

        mainContent.getChildren().addAll(
            conceptosSection, definicionSection, videoSection, 
            limitesLateralesSection, cuestionarioSection
        );
        
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
            "El límite de una función f(x) cuando x se aproxima a un valor a, es el número L al cual se acercan los valores de f(x) cuando x se acerca a a.\n\n" +
            "Se escribe:\nlim(x→a) f(x) = L\n\n" +
            "La idea del límite es que cuando los valores de x se acercan a un punto p, los valores de la función f(x) se acercan a un número L.\n" +
            "• Si los puntos cercanos a p tienen imágenes cercanas a L, entonces L es el límite.\n" +
            "• Si existe algún punto cercano a p cuya imagen está lejos de L, entonces L no es el límite.\n\n" +
            "Para determinar el límite de una función en p, se puede evaluar f(x) en valores de x cada vez más próximos a p, observando hacia qué valor se aproximan los resultados.\n" +
            "Ese valor será, posiblemente, el límite buscado."
        );
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
    
    private VBox createLimitesLateralesSection() {
        VBox section = new VBox(15);
        section.setStyle("-fx-background-color: white; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0;");
        section.setPadding(new Insets(25, 25, 25, 25));

        Label title = new Label("Límites Laterales");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        title.setTextFill(AppConstants.TITLE_COLOR);

        VBox formulasBox = new VBox(10);
        formulasBox.setAlignment(Pos.CENTER_LEFT);

        HBox formulaDerecha = new HBox(10);
        formulaDerecha.setAlignment(Pos.CENTER_LEFT);
        
        Label y1Label = new Label("y₁ f(x)");
        y1Label.setFont(Font.font("Segoe UI", 14));
        y1Label.setTextFill(AppConstants.TEXT_COLOR);
        
        Text formulaDerechaText = new Text("lim(x→a⁺) f(x) = L");
        formulaDerechaText.setFont(Font.font("Cambria Math", FontWeight.BOLD, 16));
        formulaDerechaText.setFill(AppConstants.TITLE_COLOR);
        
        formulaDerecha.getChildren().addAll(y1Label, formulaDerechaText);

        HBox formulaIzquierda = new HBox(10);
        formulaIzquierda.setAlignment(Pos.CENTER_LEFT);
        
        Label y2Label = new Label("y₂ f(x)");
        y2Label.setFont(Font.font("Segoe UI", 14));
        y2Label.setTextFill(AppConstants.TEXT_COLOR);
        
        Text formulaIzquierdaText = new Text("lim(x→a⁻) f(x) = L");
        formulaIzquierdaText.setFont(Font.font("Cambria Math", FontWeight.BOLD, 16));
        formulaIzquierdaText.setFill(AppConstants.TITLE_COLOR);
        
        formulaIzquierda.getChildren().addAll(y2Label, formulaIzquierdaText);
        
        formulasBox.getChildren().addAll(formulaDerecha, formulaIzquierda);
        
        section.getChildren().addAll(title, formulasBox);
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
            CuestionarioDefinicionView cuestionarioView = new CuestionarioDefinicionView(viewManager, this);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(cuestionarioView.createView());
        });
        
        HBox.setHgrow(titlesBox, Priority.ALWAYS);
        headerBox.getChildren().addAll(titlesBox, resolverBtn);
        
        section.getChildren().add(headerBox);
        return section;
    }
    
    private void reproducirVideo() {
        Stage videoStage = new Stage();
        videoStage.setTitle("Video Explicativo - Límites");
        videoStage.initModality(Modality.APPLICATION_MODAL);
        
        VBox videoLayout = new VBox(20);
        videoLayout.setAlignment(Pos.CENTER);
        videoLayout.setPadding(new Insets(20));
        videoLayout.setStyle("-fx-background-color: #1A202C;");

        Label videoTitle = new Label("Límites de una Función - Explicación Completa");
        videoTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        videoTitle.setTextFill(Color.WHITE);

        VBox videoPlaceholder = new VBox(15);
        videoPlaceholder.setAlignment(Pos.CENTER);
        videoPlaceholder.setStyle("-fx-background-color: #000000; -fx-background-radius: 10;");
        videoPlaceholder.setPrefSize(560, 315);
        videoPlaceholder.setPadding(new Insets(20));
        
        Text playText = new Text("🎥 REPRODUCIENDO VIDEO...");
        playText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        playText.setFill(Color.WHITE);
        
        Label infoText = new Label("En una implementación real aquí se reproduciría\nun video educativo sobre límites matemáticos");
        infoText.setFont(Font.font("Segoe UI", 12));
        infoText.setTextFill(Color.LIGHTGRAY);
        infoText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        
        videoPlaceholder.getChildren().addAll(playText, infoText);

        Button closeBtn = new Button("Cerrar Video");
        closeBtn.setStyle("-fx-background-color: #E53E3E; -fx-text-fill: white; -fx-font-weight: bold; " +
                         "-fx-background-radius: 10; -fx-padding: 10 20;");
        closeBtn.setOnAction(e -> videoStage.close());
        
        videoLayout.getChildren().addAll(videoTitle, videoPlaceholder, closeBtn);
        
        javafx.scene.Scene videoScene = new javafx.scene.Scene(videoLayout, 600, 450);
        videoStage.setScene(videoScene);
        videoStage.show();
    }
    
    public void show() {
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(createView());
    }
}