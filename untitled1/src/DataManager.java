import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataManager {
    // Generic method để save một entity ra file
    public static <T extends AbstractEntity> void saveToFile(T entity, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(entity.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generic method để load danh sách entities từ file
    public static <T extends AbstractEntity> List<T> loadFromFile(String filename, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (clazz == Course.class && parts.length == 5) {
                    list.add(clazz.cast(new Course(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]))));
                } else if (clazz == Student.class && parts.length == 4) {
                    list.add(clazz.cast(new Student(parts[0], parts[1], parts[2], parts[3])));
                } else if (clazz == Enrollment.class && parts.length == 4) {
                    list.add(clazz.cast(new Enrollment(parts[0], parts[1], parts[2], LocalDate.parse(parts[3]))));
                } else if (clazz == Payment.class && parts.length == 5) {
                    list.add(clazz.cast(new Payment(parts[0], parts[1], Double.parseDouble(parts[2]), LocalDate.parse(parts[3]), Boolean.parseBoolean(parts[4]))));
                }
            }
        } catch (IOException e) {
            // File không tồn tại hoặc lỗi, bỏ qua
        }
        return list;
    }

    // Save toàn bộ danh sách ra file (ghi đè)
    public static <T extends AbstractEntity> void saveAllToFile(List<T> list, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (T entity : list) {
                writer.write(entity.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
