package model;

import repository.Graded;
public class ProjectModule extends Module implements Graded {
    private String description;
    private double score;

    public ProjectModule(String id, String name, int duration, String description) {
        super(id, name, "Project", duration);
        this.description = description;
        this.score = 0;
    }

    @Override
    public int getDuration() { return duration; }

    @Override
    public boolean isCompleted() { return score >= 60; } // Ngưỡng điểm

    @Override
    public double getScore() { return score; }

    @Override
    public void setScore(double score) { this.score = score; }

    public String getDescription() { return description; }

    @Override
    public String toString() {
        return super.toString() + ", description='" + description + '\'' + ", score=" + score + '}';
    }
}
