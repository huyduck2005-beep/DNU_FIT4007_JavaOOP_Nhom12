package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<String> enrolledCourses = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    public Student(String id, String name, String email, String phone) {
        super(id, name, email, phone);
    }

    public void enrollCourse(String courseId) {
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
        }
    }

    public void unenrollCourse(String courseId) {
        enrolledCourses.remove(courseId);
    }

    public List<String> getEnrolledCourses() { return enrolledCourses; }
    public List<Payment> getPayments() { return payments; }
    public void addPayment(Payment payment) { payments.add(payment); }

    @Override
    public String toString() {
        return "Student{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", enrolledCourses=" + enrolledCourses + '}';
    }
}