package repository;

import java.io.*;
import java.util.*;
import model.*;
import exception.*;

public class ProgressRepository implements Persistable {
    private List<Progress> progresses = new ArrayList<>();
    private static final String FILE = "data/progress.csv";

    public void addProgress(Progress progress) {
        progresses.add(progress);
    }

    public Progress getProgress(String studentId, String courseId) throws Exception {
        return progresses.stream().filter(p -> p.getStudentId().equals(studentId) && p.getCourseId().equals(courseId)).findFirst()
                .orElseThrow(() -> new Exception("Progress not found"));
    }

    public void updateProgress(String studentId, String courseId, double percentage) throws InvalidProgressException {
        if (percentage < 0 || percentage > 100) {
            throw new InvalidProgressException("Invalid progress percentage");
        }
        Progress progress = progresses.stream().filter(p -> p.getStudentId().equals(studentId) && p.getCourseId().equals(courseId)).findFirst().orElse(null);
        if (progress != null) {
            progress.setPercentage(percentage);
        } else {
            progresses.add(new Progress(studentId, courseId, percentage));
        }
    }

    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                progresses.add(new Progress(parts[0], parts[1], Double.parseDouble(parts[2])));
            }
        } catch (IOException e) {
            // Tạo mẫu nếu tệp trống
            progresses.add(new Progress("S1", "C1", 50.0));
        }
    }

    @Override
    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Progress p : progresses) {
                pw.println(p.getStudentId() + "," + p.getCourseId() + "," + p.getPercentage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}