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
import java.util.ArrayList;
import java.util.List;

public class TheoryQuestionView {
    private ViewManager viewManager;
    private int currentQuestionIndex = 0;

    // Questions data: {Question, Option1, Option2, Option3,
    // CorrectOptionIndex(0-based)}
    private final String[][] questions = {
            {
                    "¿Qué representa el límite L de una función f(x) cuando x tiende a 'a'?",
                    "El valor exacto de f(a)",
                    "El valor al que se acercan las imágenes f(x) cuando x se acerca a 'a'",
                    "El valor de x cuando f(x) es 0",
                    "1" // Correct is Option 2 (index 1)
            },
            {
                    "Para que exista el límite de una función en un punto p, ¿qué debe ocurrir con los límites laterales?",
                    "Deben ser diferentes",
                    "Uno debe ser infinito",
                    "Deben ser iguales",
                    "2" // Correct is Option 3 (index 2)
            },
            {
                    "¿Cuál es el límite de una constante k cuando x tiende a cualquier valor?",
                    "La misma constante k",
                    "Cero",
                    "Infinito",
                    "0" // Correct is Option 1 (index 0)
            },
            {
                    "Según las propiedades, el límite de una suma de funciones es igual a:",
                    "El producto de sus límites",
                    "La suma de sus límites",
                    "La diferencia de sus límites",
                    "1" // Correct is Option 2 (index 1)
            },
            {
                    "Si lim(x→p⁻) f(x) ≠ lim(x→p⁺) f(x), entonces:",
                    "El límite existe y es cero",
                    "El límite existe y es infinito",
                    "El límite no existe",
                    "2" // Correct is Option 3 (index 2)
            }
    };

    public TheoryQuestionView(ViewManager viewManager) {
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
        backButton.setOnAction(e -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                show();
            } else {
                LimiteFuncionView theoryView = new LimiteFuncionView(viewManager);
                theoryView.show();
            }
        });

        VBox headerContent = new VBox(3);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("PREGUNTA " + (currentQuestionIndex + 1) + " DE " + questions.length);
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Verificación de Teoría");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);

        BorderPane headerLayout = new BorderPane();
        headerLayout.setLeft(backButton);
        headerLayout.setCenter(headerContent);
        header.getChildren().add(headerLayout);

        // Main Content
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40));
        mainContent.setAlignment(Pos.TOP_CENTER);

        // Question Card
        VBox card = new VBox(20);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        card.setPadding(new Insets(30));
        card.setMaxWidth(600);

        String[] qData = questions[currentQuestionIndex];
        String questionText = qData[0];
        int correctIndex = Integer.parseInt(qData[4]);

        Label qLabel = new Label(questionText);
        qLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        qLabel.setWrapText(true);
        qLabel.setTextFill(AppConstants.TEXT_COLOR);

        VBox optionsBox = new VBox(15);
        ToggleGroup group = new ToggleGroup();

        for (int i = 1; i <= 3; i++) {
            RadioButton rb = new RadioButton(qData[i]);
            rb.setToggleGroup(group);
            rb.setFont(Font.font("Segoe UI", 16));
            rb.setTextFill(AppConstants.TEXT_COLOR);
            rb.setWrapText(true);
            rb.setUserData(i - 1); // Store index 0, 1, 2
            optionsBox.getChildren().add(rb);
        }

        Label feedbackLabel = new Label("");
        feedbackLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));

        Button checkButton = new Button("Siguiente");
        checkButton.setStyle("-fx-background-color: " + AppConstants.PRIMARY_COLOR_HEX + "; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 12 30; -fx-font-size: 16; " +
                "-fx-cursor: hand;");

        checkButton.setOnAction(e -> {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            if (selected == null) {
                feedbackLabel.setText("Por favor selecciona una opción.");
                feedbackLabel.setTextFill(javafx.scene.paint.Color.RED);
                return;
            }

            int selectedIndex = (int) selected.getUserData();
            if (selectedIndex == correctIndex) {
                // Correct
                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    show();
                } else {
                    // Go to Video View
                    VideoView videoView = new VideoView(viewManager);
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(videoView.createView());
                }
            } else {
                feedbackLabel.setText("Respuesta incorrecta. Inténtalo de nuevo.");
                feedbackLabel.setTextFill(javafx.scene.paint.Color.RED);
            }
        });

        card.getChildren().addAll(qLabel, optionsBox, feedbackLabel, checkButton);
        mainContent.getChildren().add(card);

        // Menu Button
        Button menuButton = new Button("Volver al Menú");
        menuButton.setStyle("-fx-background-color: #718096; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 10 20;");
        menuButton.setOnAction(e -> viewManager.showLimitesMenu()); // Actually this should probably go to main menu or
                                                                    // limits menu? User said "volver al menú".

        mainContent.getChildren().add(menuButton);

        contentLayout.getChildren().addAll(header, mainContent);
        return contentLayout;
    }

    public void show() {
        viewManager.getRoot().getChildren().clear();
        viewManager.getRoot().getChildren().add(createView());
    }
}
