package views.cuestionarios;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.UIComponents;
import java.util.ArrayList;
import java.util.List;

public abstract class CuestionarioBaseView {

    protected VBox createPregunta(String pregunta, String[] opciones, String respuestaCorrecta) {
        VBox preguntaBox = new VBox(15);
        preguntaBox.setPadding(new Insets(15));
        preguntaBox.setStyle("-fx-background-color: #f8f9fa; -fx-background-radius: 10; " +
                "-fx-border-color: #00AEEF; -fx-border-width: 1; -fx-border-radius: 10;");

        Label preguntaLabel = new Label(pregunta);
        preguntaLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        preguntaLabel.setTextFill(AppConstants.TEXT_COLOR);
        preguntaLabel.setWrapText(true);

        VBox opcionesBox = new VBox(8);

        ToggleGroup grupo = new ToggleGroup();
        List<RadioButton> radioButtons = new ArrayList<>();

        for (String opcion : opciones) {
            RadioButton radio = new RadioButton(opcion);
            radio.setToggleGroup(grupo);
            radio.setFont(Font.font("Segoe UI", 13));
            radio.setUserData(opcion.substring(0, 1));
            radio.setWrapText(true);
            radio.setTextFill(AppConstants.TEXT_COLOR);
            radioButtons.add(radio);
            opcionesBox.getChildren().add(radio);
        }

        preguntaBox.setUserData(new Object[] { respuestaCorrecta, radioButtons });

        preguntaBox.getChildren().addAll(preguntaLabel, opcionesBox);
        return preguntaBox;
    }

    protected void mostrarResultado(VBox preguntasContainer, String tema, Runnable volverAction) {
        int correctas = 0;
        int total = preguntasContainer.getChildren().size();

        for (javafx.scene.Node node : preguntasContainer.getChildren()) {
            if (node instanceof VBox) {
                VBox preguntaBox = (VBox) node;
                Object[] userData = (Object[]) preguntaBox.getUserData();
                String respuestaCorrecta = (String) userData[0];
                @SuppressWarnings("unchecked")
                List<RadioButton> radioButtons = (List<RadioButton>) userData[1];

                for (RadioButton radio : radioButtons) {
                    if (radio.isSelected() && radio.getUserData().equals(respuestaCorrecta)) {
                        correctas++;
                        break;
                    }
                }
            }
        }

        VBox resultadoLayout = new VBox(20);
        resultadoLayout.setAlignment(Pos.CENTER);
        resultadoLayout.setPadding(new Insets(30));
        resultadoLayout.setStyle("-fx-background-color: white; -fx-background-radius: 15;");

        Text emoji = new Text(correctas == total ? "🎉" : "📊");
        emoji.setFont(Font.font(48));

        Text resultadoText = new Text("Respuesta " + correctas + "/" + total + " correctas");
        resultadoText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        resultadoText.setFill(correctas == total ? Color.GREEN : AppConstants.TITLE_COLOR);

        Text mensaje = new Text(
                correctas == total ? "¡Excelente! Dominas el tema de " + tema : "Sigue practicando " + tema);
        mensaje.setFont(Font.font("Segoe UI", 14));
        mensaje.setFill(AppConstants.LIGHT_TEXT);

        Button volverBtn = new Button("Atrás");
        volverBtn.setStyle("-fx-background-color: #72DDFF; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 10; -fx-padding: 10 25;");
        volverBtn.setOnAction(e -> volverAction.run());

        resultadoLayout.getChildren().addAll(emoji, resultadoText, mensaje, volverBtn);

        VBox parent = (VBox) preguntasContainer.getParent();
        parent.getChildren().remove(preguntasContainer);
        parent.getChildren().remove(parent.getChildren().size() - 1); // Remover botón comprobar
        parent.getChildren().add(0, resultadoLayout);
    }

    protected VBox createHeader(String titulo, String subtitulo, Runnable backAction) {
        VBox contentLayout = new VBox(0);
        contentLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(35, 20, 25, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

        Button backButton = new Button("←");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #184093; -fx-font-size: 20; " +
                "-fx-font-weight: bold; -fx-border-color: transparent; -fx-cursor: hand;");
        backButton.setOnAction(e -> backAction.run());

        VBox headerContent = new VBox(3);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text(titulo);
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text(subtitulo);
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);

        BorderPane headerLayout = new BorderPane();
        headerLayout.setLeft(backButton);
        headerLayout.setCenter(headerContent);
        header.getChildren().add(headerLayout);

        contentLayout.getChildren().add(header);
        return contentLayout;
    }
}