package model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {
    private List<String> taughtCourses = new ArrayList<>();

    public Instructor(String id, String name, String email, String phone) {
        super(id, name, email, phone);
    }

    public void addTaughtCourse(String courseId) {
        taughtCourses.add(courseId);
    }

    public List<String> getTaughtCourses() { return taughtCourses; }

    @Override
    public String toString() {
        return "Instructor{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", taughtCourses=" + taughtCourses + '}';
    }
}