package repository;

import java.io.*;
import java.util.*;
import model.*;

public class GradeRepository implements Persistable {
    private Map<String, Double> grades = new HashMap<>(); // Key: studentId-moduleId, Value: score
    private static final String FILE = "data/grades.csv";

    public void addGrade(String studentId, String moduleId, double score) {
        grades.put(studentId + "-" + moduleId, score);
    }

    public double getGrade(String studentId, String moduleId) {
        return grades.getOrDefault(studentId + "-" + moduleId, 0.0);
    }

    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                grades.put(parts[0] + "-" + parts[1], Double.parseDouble(parts[2]));
            }
        } catch (IOException e) {
            // Tạo mẫu nếu tệp trống
            grades.put("S1-M1", 80.0);
        }
    }

    @Override
    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Map.Entry<String, Double> entry : grades.entrySet()) {
                String[] keys = entry.getKey().split("-");
                pw.println(keys[0] + "," + keys[1] + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
