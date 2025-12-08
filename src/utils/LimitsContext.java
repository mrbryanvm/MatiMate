package utils;

import java.util.ArrayList;
import java.util.List;

public class LimitsContext {
    private static LimitsContext instance;

    public enum Topic {
        DEFINICION,
        LATERALES,
        PROPIEDADES
    }

    public static class UserAnswer {
        public String question;
        public String selectedOption;
        public String correctOption;
        public boolean isCorrect;
        public int points;

        public UserAnswer(String question, String selectedOption, String correctOption, boolean isCorrect, int points) {
            this.question = question;
            this.selectedOption = selectedOption;
            this.correctOption = correctOption; // Store correct option text for stats
            this.isCorrect = isCorrect;
            this.points = points;
        }
    }

    private Topic currentTopic;
    private int currentScore;
    private List<UserAnswer> currentPracticeAnswers;

    // We need to track if the current question (in any view) was already answered
    // incorrectly to prevent adding positive points on 2nd try.
    // However, the views manage the index and flow. It is simpler if specific views
    // manage the "first try" boolean locally for their current question.
    // EXCEPT for StatisticsView which needs the answers list.
    // And global score.

    private LimitsContext() {
        this.currentTopic = Topic.DEFINICION;
        this.currentScore = 0;
        this.currentPracticeAnswers = new ArrayList<>();
    }

    public static LimitsContext getInstance() {
        if (instance == null) {
            instance = new LimitsContext();
        }
        return instance;
    }

    public void reset() {
        this.currentTopic = Topic.DEFINICION;
        this.currentScore = 0;
        this.currentPracticeAnswers.clear();
    }

    public void startTopic(Topic topic) {
        this.currentTopic = topic;
        this.currentPracticeAnswers.clear();
    }

    public Topic getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(Topic topic) {
        this.currentTopic = topic;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void addScore(int points) {
        this.currentScore += points;
    }

    public void penalize() {
        this.currentScore -= 1;
    }

    public void addPracticeAnswer(UserAnswer answer) {
        this.currentPracticeAnswers.add(answer);
    }

    public List<UserAnswer> getCurrentPracticeAnswers() {
        return new ArrayList<>(currentPracticeAnswers);
    }
}
