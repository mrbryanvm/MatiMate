package views.limites;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.AppConstants;
import utils.UIComponents;
import views.ViewManager;

public class NivelIntroduccionView {
    private ViewManager viewManager;
    private String nivel;
    private IntroduccionView introduccionView;
    
    public NivelIntroduccionView(ViewManager viewManager, String nivel, IntroduccionView introduccionView) {
        this.viewManager = viewManager;
        this.nivel = nivel;
        this.introduccionView = introduccionView;
    }
    
    public VBox createView() {
        VBox nivelLayout = new VBox(0);
        nivelLayout.setBackground(new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(40, 20, 30, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");
        
        VBox headerContent = new VBox(5);
        headerContent.setAlignment(Pos.CENTER);
        
        Text title = new Text("Evaluación");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        title.setFill(AppConstants.TITLE_COLOR);
        
        Text subtitle = new Text("Nivel: " + nivel);
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));
        
        headerContent.getChildren().addAll(title, subtitle);
        header.getChildren().add(headerContent);

        VBox contentContainer = new VBox(20);
        contentContainer.setPadding(new Insets(25, 25, 25, 25));
        contentContainer.setAlignment(Pos.CENTER);

        VBox ejercicioBox = crearEjercicioNivel(nivel);

        HBox navButtons = new HBox(15);
        navButtons.setAlignment(Pos.CENTER);
        
        Button atrasBtn = UIComponents.createSecondaryButton("Atrás");
        Button repetirBtn = UIComponents.createPrimaryButton("Repetir");
        
        atrasBtn.setOnAction(e -> introduccionView.show());
        repetirBtn.setOnAction(e -> {
            viewManager.getRoot().getChildren().clear();
            viewManager.getRoot().getChildren().add(createView());
        });

        Button siguienteBtn = UIComponents.createPrimaryButton(getSiguienteNivel(nivel));
        siguienteBtn.setOnAction(e -> {
            switch(nivel) {
                case "Fácil": 
                    NivelIntroduccionView normalView = new NivelIntroduccionView(viewManager, "Normal", introduccionView);
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(normalView.createView());
                    break;
                case "Normal": 
                    NivelIntroduccionView dificilView = new NivelIntroduccionView(viewManager, "Difícil", introduccionView);
                    viewManager.getRoot().getChildren().clear();
                    viewManager.getRoot().getChildren().add(dificilView.createView());
                    break;
                case "Difícil": 
                    introduccionView.show();
                    break;
            }
        });
        
        navButtons.getChildren().addAll(atrasBtn, repetirBtn, siguienteBtn);
        
        contentContainer.getChildren().addAll(ejercicioBox, navButtons);
        nivelLayout.getChildren().addAll(header, contentContainer);
        
        return nivelLayout;
    }
    
    private VBox crearEjercicioNivel(String nivel) {
        VBox ejercicioBox = new VBox(15);
        ejercicioBox.setAlignment(Pos.CENTER_LEFT);
        ejercicioBox.setPadding(new Insets(20));
        ejercicioBox.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                             "-fx-border-color: #00AEEF; -fx-border-width: 1; -fx-border-radius: 15; " +
                             "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);");
        ejercicioBox.setMaxWidth(350);

        Label enunciado = new Label("Calcular el límite del siguiente ejercicio:");
        enunciado.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        enunciado.setTextFill(AppConstants.TEXT_COLOR);

        Text formula = new Text();
        formula.setFont(Font.font("Cambria Math", FontWeight.BOLD, 18));
        formula.setFill(AppConstants.TEXT_COLOR);

        VBox solucionBox = new VBox(10);
        solucionBox.setAlignment(Pos.CENTER_LEFT);
        
        Label solucionTitle = new Label("Solución:");
        solucionTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        solucionTitle.setTextFill(AppConstants.TEXT_COLOR);
        
        Text solucionText = new Text();
        solucionText.setFont(Font.font("Segoe UI", 13));
        solucionText.setFill(AppConstants.TEXT_COLOR);
        solucionText.setWrappingWidth(300);

        VBox conclusionBox = new VBox(10);
        conclusionBox.setAlignment(Pos.CENTER_LEFT);
        
        Label conclusionTitle = new Label("Conclusión:");
        conclusionTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        conclusionTitle.setTextFill(AppConstants.TEXT_COLOR);
        
        Text conclusionText = new Text();
        conclusionText.setFont(Font.font("Segoe UI", 13));
        conclusionText.setFill(AppConstants.TEXT_COLOR);
        conclusionText.setWrappingWidth(300);

        switch(nivel) {
            case "Fácil":
                formula.setText("lim(x→1) (2x + 3)");
                solucionText.setText("• Sustituimos x = 1\n  = 2 * (1) + 3\n\n• Simplificamos\n  = 5");
                conclusionText.setText("lim(x→1) (2x + 3) = 5\n\nA medida que los valores de x se aproximan a 1, las imágenes de la función se aproximan al número 5.");
                break;
                
            case "Normal":
                formula.setText("lim(x→4) (-11x² + 9x + 30)");
                solucionText.setText("• Sustituimos x = 4\n  = -11 * 4² + 9 * 4 + 30\n\n• Simplificamos\n  = -110");
                conclusionText.setText("lim(x→4) (-11x² + 9x + 30) = -110\n\nA medida que los valores de x se aproximan a 4, las imágenes de la función se aproximan al número -110.");
                break;
                
            case "Difícil":
                formula.setText("lim(x→5) (3x³ - 9x² + 3x + 1)");
                solucionText.setText("• Sustituimos x = 5\n  = 3 * 5³ - 9 * 5² + 3 * 5 + 1\n\n• Simplificamos\n  = 166");
                conclusionText.setText("lim(x→5) (3x³ - 9x² + 3x + 1) = 166\n\nA medida que los valores de x se aproximan a 5, las imágenes de la función se aproximan al número 166.");
                break;
        }
        
        solucionBox.getChildren().addAll(solucionTitle, solucionText);
        conclusionBox.getChildren().addAll(conclusionTitle, conclusionText);
        
        ejercicioBox.getChildren().addAll(enunciado, formula, solucionBox, conclusionBox);
        
        return ejercicioBox;
    }
    
    private String getSiguienteNivel(String nivelActual) {
        switch(nivelActual) {
            case "Fácil": return "Normal";
            case "Normal": return "Difícil";
            case "Difícil": return "Menú";
            default: return "Siguiente";
        }
    }
}