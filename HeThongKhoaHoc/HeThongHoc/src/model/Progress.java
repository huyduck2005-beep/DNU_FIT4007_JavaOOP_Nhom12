package model;

public class Progress {
    private String studentId;
    private String courseId;
    private double percentage;

    public Progress(String studentId, String courseId, double percentage) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.percentage = percentage;
    }

    public String getStudentId() {
        return studentId;
    }
    public String getCourseId() {
        return courseId;
    }
    public double getPercentage() {
        return percentage;
    }
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Progress{" + "studentId='" + studentId + '\'' + ", courseId='" + courseId + '\'' + ", percentage=" + percentage + '}';
    }
}
