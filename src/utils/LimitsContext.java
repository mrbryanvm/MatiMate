package utils;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Integer> answeredCorrectlyIndices;
    private Set<Integer> penaltyAppliedIndices;

    private LimitsContext() {
        this.currentTopic = Topic.DEFINICION;
        this.currentScore = 0;

        this.currentPracticeAnswers = new ArrayList<>();
        this.answeredCorrectlyIndices = new HashSet<>();
        this.penaltyAppliedIndices = new HashSet<>();
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
        this.answeredCorrectlyIndices.clear();
        this.penaltyAppliedIndices.clear();
    }

    public void startTopic(Topic topic) {
        this.currentTopic = topic;

        this.currentPracticeAnswers.clear();
        this.answeredCorrectlyIndices.clear();
        this.penaltyAppliedIndices.clear();
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

    public boolean hasAnsweredCorrectly(int questionIndex) {
        return answeredCorrectlyIndices.contains(questionIndex);
    }

    public void markAnsweredCorrectly(int questionIndex) {
        answeredCorrectlyIndices.add(questionIndex);
    }

    public boolean hasPenaltyApplied(int questionIndex) {
        return penaltyAppliedIndices.contains(questionIndex);
    }

    public void markPenaltyApplied(int questionIndex) {
        penaltyAppliedIndices.add(questionIndex);
    }
}
