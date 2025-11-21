package model;

public class Payment {
    private String id;
    private String studentId;
    private String courseId;
    private double amount;
    private String date;
    private String status;

    public Payment(String id, String studentId, String courseId, double amount, String date, String status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    public String getStudentId() {
        return studentId;
    }
    public String getCourseId() {
        return courseId;
    }
    public double getAmount() {
        return amount;
    }
    public String getDate() {
        return date;
    }
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Payment{" + "id='" + id + '\'' + ", studentId='" + studentId + '\'' + ", courseId='" + courseId + '\'' + ", amount=" + amount + ", date='" + date + '\'' + ", status='" + status + '\'' + '}';
    }
}