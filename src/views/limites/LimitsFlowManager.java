package views.limites;

import views.ViewManager;
import utils.LimitsContext;
import utils.LimitsContext.Topic;

public class LimitsFlowManager {
    private ViewManager viewManager;

    public LimitsFlowManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void startFlow() {
        LimitsContext.getInstance().reset();
        LimitsContext.getInstance().startTopic(Topic.DEFINICION);
        showTheory();
    }

    public void nextTopic() {
        Topic current = LimitsContext.getInstance().getCurrentTopic();
        if (current == Topic.DEFINICION) {
            LimitsContext.getInstance().startTopic(Topic.LATERALES);
            showTheory();
        } else if (current == Topic.LATERALES) {
            LimitsContext.getInstance().startTopic(Topic.PROPIEDADES);
            showTheory();
        } else {
            // Finalizar flujo
            finishFlow();
        }
    }

    public void showTheory() {
        LimiteFuncionView view = new LimiteFuncionView(viewManager);
        viewManager.updateView(view.createView());
    }

    public void showTheoryQuestions() {
        TheoryQuestionView view = new TheoryQuestionView(viewManager);
        viewManager.updateView(view.createView());
    }

    public void showVideo() {
        VideoView view = new VideoView(viewManager);
        viewManager.updateView(view.createView());
    }

    public void showQuiz() {
        CuestionarioView view = new CuestionarioView(viewManager);
        viewManager.updateView(view.createView());
    }

    public void showExercises() {
        ExerciseSeriesView view = new ExerciseSeriesView(viewManager);
        viewManager.updateView(view.createView());
    }

    public void showPractice() {
        PracticeView view = new PracticeView(viewManager);
        viewManager.updateView(view.createView());
    }

    public void showStatistics() {
        StatisticsView view = new StatisticsView(viewManager);
        viewManager.updateView(view.createView());
    }

    private void finishFlow() {
        // Guardar puntuación en el usuario global
        int finalContextScore = LimitsContext.getInstance().getCurrentScore();
        viewManager.updateUserScore(finalContextScore);
        viewManager.showMainMenu();
    }
}
