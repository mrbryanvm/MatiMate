package utils;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class UIComponents {

    // TextField moderno
    public static TextField createModernTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setMaxWidth(320);
        field.setStyle("-fx-background-radius: 12; -fx-border-radius: 12; -fx-padding: 16 20; -fx-font-size: 14; " +
                "-fx-font-family: 'Segoe UI'; -fx-background-color: #FFFFFF; " +
                "-fx-text-fill: #333333; -fx-prompt-text-fill: #666666; " +
                "-fx-border-color: #E2E8F0; -fx-border-width: 1; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);");
        return field;
    }

    // PasswordField moderno
    public static PasswordField createModernPasswordField(String prompt) {
        PasswordField field = new PasswordField();
        field.setPromptText(prompt);
        field.setMaxWidth(320);
        field.setStyle("-fx-background-radius: 12; -fx-border-radius: 12; -fx-padding: 16 20; -fx-font-size: 14; " +
                "-fx-font-family: 'Segoe UI'; -fx-background-color: #FFFFFF; " +
                "-fx-text-fill: #333333; -fx-prompt-text-fill: #666666; " +
                "-fx-border-color: #E2E8F0; -fx-border-width: 1; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);");
        return field;
    }

    // TextField con borde negro
    public static TextField createBlackBorderedTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setMaxWidth(280);
        field.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 14 18; -fx-font-size: 14; " +
                "-fx-font-family: 'Segoe UI'; -fx-background-color: #FFFFFF; " +
                "-fx-text-fill: #000000; -fx-prompt-text-fill: #666666; " +
                "-fx-border-color: #000000; -fx-border-width: 2; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");
        return field;
    }

    // PasswordField con borde negro
    public static PasswordField createBlackBorderedPasswordField(String prompt) {
        PasswordField field = new PasswordField();
        field.setPromptText(prompt);
        field.setMaxWidth(280);
        field.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 14 18; -fx-font-size: 14; " +
                "-fx-font-family: 'Segoe UI'; -fx-background-color: #FFFFFF; " +
                "-fx-text-fill: #000000; -fx-prompt-text-fill: #666666; " +
                "-fx-border-color: #000000; -fx-border-width: 2; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");
        return field;
    }

    // Botón primario
    public static Button createPrimaryButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: #3EB566; -fx-text-fill: #004428; -fx-font-weight: bold; " +
                "-fx-background-radius: 15; -fx-padding: 16 30; -fx-font-size: 14; " +
                "-fx-font-family: 'Segoe UI'; " +
                "-fx-effect: dropshadow(gaussian, rgba(62,181,102,0.3), 10, 0, 0, 3);");
        btn.setMaxWidth(320);
        return btn;
    }

    // Botón secundario
    public static Button createSecondaryButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: #a0aec0; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 15; -fx-padding: 14 25; -fx-font-size: 13; " +
                "-fx-font-family: 'Segoe UI';");
        btn.setMaxWidth(280);
        return btn;
    }

    // Botón pequeño
    public static Button createSmallButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-text-fill: #184093; -fx-font-weight: bold; " +
                "-fx-background-radius: 15; -fx-padding: 8 15; -fx-font-size: 12; " +
                "-fx-border-color: #184093; -fx-border-width: 1; -fx-border-radius: 15;");
        return btn;
    }

    // Botón del menú principal
    public static Button createMainMenuButton(String icon, String text) {
        Button btn = new Button();
        btn.setStyle("-fx-background-color: #72DDFF; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 25 40; -fx-font-size: 16; " +
                "-fx-effect: dropshadow(gaussian, rgba(114,221,255,0.4), 20, 0, 0, 8);");
        btn.setMaxWidth(320);
        btn.setMinHeight(80);
        btn.setWrapText(true);

        VBox content = new VBox(5);
        content.setAlignment(Pos.CENTER);

        Text iconText = new Text(icon);
        iconText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        iconText.setFill(Color.WHITE);

        Text textText = new Text(text);
        textText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        textText.setFill(Color.WHITE);

        content.getChildren().addAll(iconText, textText);
        btn.setGraphic(content);

        btn.setOnMouseEntered(
                e -> btn.setStyle("-fx-background-color: #5ac8e8; -fx-text-fill: white; -fx-font-weight: bold; " +
                        "-fx-background-radius: 20; -fx-padding: 25 40; -fx-font-size: 16; " +
                        "-fx-effect: dropshadow(gaussian, rgba(114,221,255,0.6), 25, 0, 0, 10);"));
        btn.setOnMouseExited(
                e -> btn.setStyle("-fx-background-color: #72DDFF; -fx-text-fill: white; -fx-font-weight: bold; " +
                        "-fx-background-radius: 20; -fx-padding: 25 40; -fx-font-size: 16; " +
                        "-fx-effect: dropshadow(gaussian, rgba(114,221,255,0.4), 20, 0, 0, 8);"));
        return btn;
    }

    // Card de contenido para el menú principal (Desktop)
    public static Button createContentCard(String icon, String title, String description) {
        Button btn = new Button();
        btn.setPrefSize(280, 200);
        btn.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

        VBox content = new VBox(15);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));

        Text iconText = new Text(icon);
        iconText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 48));
        iconText.setFill(AppConstants.PRIMARY_COLOR);

        Text titleText = new Text(title);
        titleText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        titleText.setFill(AppConstants.TITLE_COLOR);

        Text descText = new Text(description);
        descText.setFont(Font.font("Segoe UI", 14));
        descText.setFill(AppConstants.LIGHT_TEXT);
        descText.setWrappingWidth(240);
        descText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        content.getChildren().addAll(iconText, titleText, descText);
        btn.setGraphic(content);

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(62,181,102,0.4), 20, 0, 0, 8); " +
                "-fx-scale-x: 1.02; -fx-scale-y: 1.02;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3); " +
                "-fx-scale-x: 1.0; -fx-scale-y: 1.0;"));

        return btn;
    }

    // Botón de nivel
    public static Button createLevelButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 20 40; -fx-font-size: 16; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0, 0, 5);");
        btn.setMaxWidth(300);
        btn.setMinHeight(70);
        btn.setWrapText(true);
        return btn;
    }

    // Botón de navegación
    public static Button createNavButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 18 25; -fx-font-size: 14; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 15, 0, 0, 5);");
        btn.setMaxWidth(320);
        btn.setMinHeight(65);
        btn.setWrapText(true);
        btn.setAlignment(Pos.CENTER_LEFT);

        String darkerColor = darkenColor(color);
        btn.setOnMouseEntered(e -> btn
                .setStyle("-fx-background-color: " + darkerColor + "; -fx-text-fill: white; -fx-font-weight: bold; " +
                        "-fx-background-radius: 20; -fx-padding: 18 25; -fx-font-size: 14; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0, 0, 8);"));
        btn.setOnMouseExited(
                e -> btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold; " +
                        "-fx-background-radius: 20; -fx-padding: 18 25; -fx-font-size: 14; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 15, 0, 0, 5);"));
        return btn;
    }

    // Botón de dificultad
    public static Button createDifficultyButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 22 45; -fx-font-size: 16; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0, 0, 8);");
        btn.setMaxWidth(280);
        btn.setMinHeight(75);
        btn.setWrapText(true);
        return btn;
    }

    // Item de tema
    public static HBox createTopicItem(String topic) {
        HBox item = new HBox(20);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(20));
        item.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 15, 0, 0, 3); " +
                "-fx-border-color: #00AEEF; -fx-border-width: 1; -fx-border-radius: 15;");
        item.setMaxWidth(340);

        Text icon = new Text(topic.substring(0, 2));
        icon.setFont(Font.font("Segoe UI", 24));
        icon.setFill(AppConstants.PRIMARY_COLOR);

        VBox textContent = new VBox(4);
        textContent.setAlignment(Pos.CENTER_LEFT);

        Label topicLabel = new Label(topic.substring(2));
        topicLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        topicLabel.setTextFill(AppConstants.TEXT_COLOR);

        Text progress = new Text("0% completado");
        progress.setFont(Font.font("Segoe UI", 12));
        progress.setFill(AppConstants.LIGHT_TEXT);

        textContent.getChildren().addAll(topicLabel, progress);

        ProgressIndicator progressIndicator = new ProgressIndicator(0);
        progressIndicator.setPrefSize(30, 30);
        progressIndicator.setStyle("-fx-progress-color: #3EB566;");

        item.getChildren().addAll(icon, textContent, progressIndicator);

        item.setOnMouseEntered(e -> item.setStyle("-fx-background-color: #f7fafc; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 5); " +
                "-fx-border-color: #00AEEF; -fx-border-width: 2; -fx-border-radius: 15;"));
        item.setOnMouseExited(e -> item.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 15, 0, 0, 3); " +
                "-fx-border-color: #00AEEF; -fx-border-width: 1; -fx-border-radius: 15;"));

        return item;
    }

    // Item de opción
    public static HBox createOptionItem(String emoji, String text, boolean isFirst) {
        HBox item = new HBox(15);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(20, 25, 20, 25));
        item.setStyle("-fx-background-color: white; -fx-cursor: hand;");

        Text emojiText = new Text(emoji);
        emojiText.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 20));
        emojiText.setFill(AppConstants.TEXT_COLOR);

        Label textLabel = new Label(text);
        textLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 16));
        textLabel.setTextFill(AppConstants.TEXT_COLOR);

        Text arrow = new Text("→");
        arrow.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 18));
        arrow.setFill(AppConstants.LIGHT_TEXT);

        HBox.setHgrow(textLabel, Priority.ALWAYS);

        item.getChildren().addAll(emojiText, textLabel, arrow);

        item.setOnMouseEntered(e -> {
            item.setStyle("-fx-background-color: #F7FAFC; -fx-cursor: hand;");
            arrow.setFill(AppConstants.PRIMARY_COLOR);
        });

        item.setOnMouseExited(e -> {
            item.setStyle("-fx-background-color: white; -fx-cursor: hand;");
            arrow.setFill(AppConstants.LIGHT_TEXT);
        });

        return item;
    }

    // Animación de botón
    public static void animateButton(Button button) {
        FadeTransition ft = new FadeTransition(Duration.millis(200), button);
        ft.setFromValue(1.0);
        ft.setToValue(0.7);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.play();
    }

    // Oscurecer color
    private static String darkenColor(String color) {
        switch (color) {
            case "#72DDFF":
                return "#5ac8e8";
            case "#3EB566":
                return "#359c55";
            case "#00AEEF":
                return "#0095d0";
            case "#184093":
                return "#14357a";
            default:
                return color;
        }
    }

    // Crear sección con items
    public static VBox createSection(String title, String[] items) {
        VBox section = new VBox(0);
        section.setStyle("-fx-background-color: white; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0;");
        section.setPadding(new Insets(20, 25, 20, 25));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        titleLabel.setTextFill(AppConstants.TITLE_COLOR);
        titleLabel.setPadding(new Insets(0, 0, 15, 0));

        VBox itemsBox = new VBox(12);

        for (String item : items) {
            HBox itemBox = new HBox(10);
            itemBox.setAlignment(Pos.CENTER_LEFT);
            itemBox.setStyle("-fx-cursor: hand;");
            itemBox.setPadding(new Insets(8, 0, 8, 0));

            Circle bullet = new Circle(4);
            bullet.setFill(AppConstants.PRIMARY_COLOR);

            Label itemLabel = new Label(item);
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

            itemsBox.getChildren().add(itemBox);
        }

        section.getChildren().addAll(titleLabel, itemsBox);
        return section;
    }
}