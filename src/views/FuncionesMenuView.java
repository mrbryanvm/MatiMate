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

public class FuncionesMenuView {
    private ViewManager viewManager;

    public FuncionesMenuView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public VBox createView() {
        VBox funcionesLayout = new VBox(0);
        funcionesLayout.setBackground(
                new Background(new BackgroundFill(AppConstants.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane header = new StackPane();
        header.setPadding(new Insets(50, 20, 40, 20));
        header.setStyle("-fx-background-color: " + AppConstants.SECONDARY_COLOR_HEX + ";");

        VBox headerContent = new VBox(10);
        headerContent.setAlignment(Pos.CENTER);

        Text title = new Text("FUNCIONES");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setFill(AppConstants.TITLE_COLOR);

        Text subtitle = new Text("Dominios, rangos y tipos de funciones");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setFill(AppConstants.TITLE_COLOR.deriveColor(1, 1, 1, 0.9));

        headerContent.getChildren().addAll(title, subtitle);
        header.getChildren().add(headerContent);

        VBox topicsBox = new VBox(12);
        topicsBox.setPadding(new Insets(30, 25, 30, 25));
        topicsBox.setAlignment(Pos.CENTER);

        String[] topics = {
                "📖 Definición de función",
                "🔢 Tipos de funciones",
                "📊 Dominio y rango",
                "🔗 Composición de funciones",
                "🎯 Evaluación"
        };

        for (int i = 0; i < topics.length; i++) {
            String topic = topics[i];
            HBox topicItem = UIComponents.createTopicItem(topic);

            final int index = i;
            topicItem.setOnMouseClicked(e -> {
                switch (index) {
                    case 0:
                        views.funciones.DefinicionFuncionView view0 = new views.funciones.DefinicionFuncionView(
                                viewManager, this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view0.createView());
                        break;
                    case 1:
                        views.funciones.TiposFuncionesView view1 = new views.funciones.TiposFuncionesView(viewManager,
                                this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view1.createView());
                        break;
                    case 2:
                        views.funciones.DominioRangoView view2 = new views.funciones.DominioRangoView(viewManager,
                                this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view2.createView());
                        break;
                    case 3:
                        views.funciones.ComposicionFuncionesView view3 = new views.funciones.ComposicionFuncionesView(
                                viewManager, this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view3.createView());
                        break;
                    case 4:
                        views.funciones.EvaluacionFuncionesView view4 = new views.funciones.EvaluacionFuncionesView(
                                viewManager, this);
                        viewManager.getRoot().getChildren().clear();
                        viewManager.getRoot().getChildren().add(view4.createView());
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

        funcionesLayout.getChildren().addAll(header, scrollPane, backBtn);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        funcionesLayout.setAlignment(Pos.TOP_CENTER);

        return funcionesLayout;
    }
}
