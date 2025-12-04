package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import views.ViewManager;

public class LimiteFuncionView {
    private ViewManager viewManager;

    public LimiteFuncionView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public VBox createView() {
        VBox contentLayout = new VBox(0);
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

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

        Text title = new Text("LÍMITE DE UNA FUNCIÓN");
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
        mainContent.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        // 1. Definición Section
        mainContent.getChildren().add(createSection("1. Definición",
                "El límite de una función f(x) cuando x se aproxima a un valor a, es el número L al cual se acercan los valores de f(x) cuando x se acerca a a.\n\n"
                        +
                        "Se escribe:\nlim(x→a) f(x) = L\n\n" +
                        "La idea del límite es que cuando los valores de x se acercan a un punto p, los valores de la función f(x) se acercan a un número L.\n"
                        +
                        "• Si los puntos cercanos a p tienen imágenes cercanas a L, entonces L es el límite.\n" +
                        "• Si existe algún punto cercano a p cuya imagen está lejos de L, entonces L no es el límite."));

        // 2. Límites Laterales Section
        mainContent.getChildren().add(createSection("2. Límites Laterales",
                "Cuando analizamos un límite, podemos acercarnos al punto p por dos lados:\n" +
                        "• Desde la derecha → valores de x mayores que p (lim(x→p⁺) f(x) = L)\n" +
                        "• Desde la izquierda → valores de x menores que p (lim(x→p⁻) f(x) = L)\n\n" +
                        "Para que el límite exista, los límites laterales deben ser iguales."));

        // 3. Propiedades Section
        mainContent.getChildren().add(createSection("3. Propiedades",
                "1. Límite de una constante: lim(x→a) k = k\n" +
                        "2. Unicidad: Si el límite existe, es único.\n" +
                        "3. Suma/Resta: lim [f(x) ± g(x)] = lim f(x) ± lim g(x)\n" +
                        "4. Producto: lim [f(x) · g(x)] = lim f(x) · lim g(x)\n" +
                        "5. Cociente: lim [f(x)/g(x)] = lim f(x) / lim g(x) (si lim g(x) ≠ 0)"));

        // Next Button
        Button nextButton = new Button("Siguiente →");
        nextButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + "; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 12 30; -fx-font-size: 16; " +
                "-fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");
        nextButton.setOnAction(e -> {
            TheoryQuestionView questionView = new TheoryQuestionView(viewManager);
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(questionView.createView());
        });

        HBox buttonContainer = new HBox(nextButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(20, 0, 40, 0));

        mainContent.getChildren().add(buttonContainer);

        scrollContent.setContent(mainContent);
        contentLayout.getChildren().addAll(header, scrollContent);

        return contentLayout;
    }

    private VBox createSection(String titleText, String contentText) {
        VBox section = new VBox(15);
        section.setStyle("-fx-background-color: white; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5, 0, 0, 2);");
        section.setPadding(new Insets(25));

        Label title = new Label(titleText);
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        title.setTextFill(AppConstants.TITLE_COLOR);

        TextArea content = new TextArea(contentText);
        content.setEditable(false);
        content.setWrapText(true);
        content.setPrefHeight(180);
        content.setStyle("-fx-font-size: 14; -fx-font-family: 'Segoe UI'; " +
                "-fx-background-color: transparent; -fx-border-color: transparent; " +
                "-fx-text-fill: #333333; -fx-padding: 0; -fx-control-inner-background: white;");

        section.getChildren().addAll(title, content);
        return section;
    }

    public void show() {
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(createView());
    }
}