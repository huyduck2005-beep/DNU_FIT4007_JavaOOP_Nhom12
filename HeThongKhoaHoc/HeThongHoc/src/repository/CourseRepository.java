package repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import model.*;
import exception.*;

public class CourseRepository implements Persistable {
    private List<Course> courses = new ArrayList<>();
    private static final String FILE = "data/courses.csv";

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Course findCourseById(String id) throws CourseNotFoundException {
        return courses.stream().filter(c -> c.getId().equals(id)).findFirst()
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    public void updateCourse(Course course) {
        // Update logic (tìm và thay thế trong list)
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(course.getId())) {
                courses.set(i, course);
                break;
            }
        }
    }

    public void deleteCourse(String id) throws CourseNotFoundException {
        courses.remove(findCourseById(id));
    }

    public List<Course> searchCourses(String keyword) {
        return courses.stream().filter(c -> c.getName().contains(keyword) || c.getInstructorId().contains(keyword))
                .collect(Collectors.toList());
    }

    public void listCourses() {
        courses.forEach(System.out::println);
    }

    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                courses.add(new Course(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4])));
            }
        } catch (IOException e) {
            // Tạo mẫu nếu tệp trống
            courses.add(new Course(java.util.UUID.randomUUID().toString(), "Java Basics", "I1", 10, 100.0));
            courses.add(new Course(java.util.UUID.randomUUID().toString(), "Advanced Java", "I2", 20, 200.0));
        }
    }

    @Override
    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Course c : courses) {
                pw.println(c.getId() + "," + c.getName() + "," + c.getInstructorId() + "," + c.getDuration() + "," + c.getFee());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}