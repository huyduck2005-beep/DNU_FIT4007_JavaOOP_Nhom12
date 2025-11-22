// File: DataManager.java (Đã chỉnh sửa/Bổ sung)
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataManager {
    // ... (saveToFile và saveAllToFile giữ nguyên) ...

    // Generic method để load danh sách entities từ file (Cần được mở rộng)
    public static <T extends AbstractEntity> List<T> loadFromFile(String filename, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // ... (Logic load cũ cho Course, Enrollment, Payment) ...

                // Logic load mới (Chỉ ví dụ với Learner và Certificate)
                if (filename.equals("users.csv") && parts.length == 5) { // Ví dụ cho Learner
                    if (parts[0].equals("LEARNER")) {
                        // parts[0] là Type, parts[1] là ID
                        list.add(clazz.cast(new Learner(parts[1], parts[2], parts[3], parts[4])));
                    }
                } else if (clazz == Certificate.class && parts.length == 6) {
                    list.add(clazz.cast(new Certificate(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), LocalDate.parse(parts[5]))));
                }
            }
        } catch (IOException e) {
            // File không tồn tại hoặc lỗi, bỏ qua
        } catch (Exception e) {
            // Xử lý lỗi parse
            e.printStackTrace();
        }
        return list;
    }
}