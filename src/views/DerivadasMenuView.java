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

public class DerivadasMenuView {
    private ViewManager viewManager;

    public DerivadasMenuView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public VBox createView() {
        VBox derivadasLayout = new VBox(0);
        derivadasLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(50, 20, 40, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

        VBox headerContent = new VBox(10);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("DERIVADAS");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Reglas de derivación y aplicaciones");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);
        header.getChildren().add(headerContent);

        VBox topicsBox = new VBox(12);
        topicsBox.setPadding(new Insets(30, 25, 30, 25));
        topicsBox.setAlignment(Pos.CENTER);

        String[] topics = {
                "📚 Definición de derivada",
                "📐 Reglas de derivación",
                "🔗 Regla de la cadena",
                "📈 Derivadas de orden superior",
                "🎨 Aplicaciones",
                "🎯 Evaluación"
        };

        for (int i = 0; i < topics.length; i++) {
            String topic = topics[i];
            HBox topicItem = UIComponents.createTopicItem(topic);

            final int index = i;
            topicItem.setOnMouseClicked(e -> {
                switch (index) {
                    case 0:
                        views.derivadas.DefinicionDerivadaView view0 = new views.derivadas.DefinicionDerivadaView(
                                viewManager, this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view0.createView());
                        break;
                    case 1:
                        views.derivadas.ReglasDerivacionView view1 = new views.derivadas.ReglasDerivacionView(
                                viewManager, this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view1.createView());
                        break;
                    case 2:
                        views.derivadas.ReglaCadenaView view2 = new views.derivadas.ReglaCadenaView(viewManager, this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view2.createView());
                        break;
                    case 3:
                        views.derivadas.OrdenSuperiorView view3 = new views.derivadas.OrdenSuperiorView(viewManager,
                                this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view3.createView());
                        break;
                    case 4:
                        views.derivadas.AplicacionesDerivadaView view4 = new views.derivadas.AplicacionesDerivadaView(
                                viewManager, this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view4.createView());
                        break;
                    case 5:
                        views.derivadas.EvaluacionDerivadasView view5 = new views.derivadas.EvaluacionDerivadasView(
                                viewManager, this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view5.createView());
                        break;
                }
            });

            topicsBox.getChildren().add(topicItem);
        }

        Button backBtn = UIComponents.createSecondaryButton("↩️ VOLVER AL MENÚ");
        backBtn.setOnAction(e -> viewManager.showMainMenu());

        ScrollPane scrollPane = new ScrollPane(topicsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        derivadasLayout.getChildren().addAll(header, scrollPane, backBtn);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        derivadasLayout.setAlignment(Pos.TOP_CENTER);

        return derivadasLayout;
    }
}
