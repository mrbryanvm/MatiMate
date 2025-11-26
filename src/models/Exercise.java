package models;

public class Exercise {
    private String problem;
    private String answer;
    private String solution;
    
    public Exercise(String problem, String answer, String solution) {
        this.problem = problem;
        this.answer = answer;
        this.solution = solution;
    }
    
    public String getProblem() {
        return problem;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public String getSolution() {
        return solution;
    }
    
    public void setProblem(String problem) {
        this.problem = problem;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public void setSolution(String solution) {
        this.solution = solution;
    }
}