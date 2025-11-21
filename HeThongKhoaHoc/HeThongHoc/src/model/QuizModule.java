package model;

import repository.Graded;
import java.util.List;

public class QuizModule extends Module implements Graded {
    private List<String> questions;
    private double score;

    public QuizModule(String id, String name, int duration, List<String> questions) {
        super(id, name, "Quiz", duration);
        this.questions = questions;
        this.score = 0;
    }

    @Override
    public int getDuration() { return duration; }

    @Override
    public boolean isCompleted() { return score >= 50; } // Ngưỡng điểm

    @Override
    public double getScore() { return score; }

    @Override
    public void setScore(double score) { this.score = score; }

    public List<String> getQuestions() { return questions; }

    @Override
    public String toString() {
        return super.toString() + ", score=" + score + '}';
    }
}
