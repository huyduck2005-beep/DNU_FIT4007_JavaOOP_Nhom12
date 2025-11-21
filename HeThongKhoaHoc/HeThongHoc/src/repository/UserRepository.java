package repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import model.*;
import exception.*;

public class UserRepository implements Persistable {
    private List<User> users = new ArrayList<>();
    private static final String FILE = "data/users.csv";

    public void addUser(User user) {
        users.add(user);
    }

    public User findUserById(String id) throws Exception {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst()
                .orElseThrow(() -> new Exception("User not found"));
    }

    public void updateUser(User user) {
        // Update logic
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                break;
            }
        }
    }

    public List<User> searchUsers(String keyword) {
        return users.stream().filter(u -> u.getName().contains(keyword) || u.getEmail().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Student> getStudents() {
        return users.stream().filter(u -> u instanceof Student).map(u -> (Student) u).collect(Collectors.toList());
    }

    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[4].equals("Student")) {
                    users.add(new Student(parts[0], parts[1], parts[2], parts[3]));
                } else if (parts[4].equals("Instructor")) {
                    users.add(new Instructor(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            // Tạo mẫu nếu tệp trống
            users.add(new Student(java.util.UUID.randomUUID().toString(), "John Doe", "john@example.com", "123456789"));
            users.add(new Instructor(java.util.UUID.randomUUID().toString(), "Jane Smith", "jane@example.com", "987654321"));
        }
    }

    @Override
    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (User u : users) {
                String type = u instanceof Student ? "Student" : "Instructor";
                pw.println(u.getId() + "," + u.getName() + "," + u.getEmail() + "," + u.getPhone() + "," + type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
