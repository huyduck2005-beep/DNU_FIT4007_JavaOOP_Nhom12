package repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import model.*;

public class PaymentRepository implements Persistable {
    private List<Payment> payments = new ArrayList<>();
    private static final String FILE = "data/payments.csv";

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public Map<String, Double> getRevenueByCourse() {
        Map<String, Double> revenue = new TreeMap<>();
        for (Payment p : payments) {
            revenue.put(p.getCourseId(), revenue.getOrDefault(p.getCourseId(), 0.0) + p.getAmount());
        }
        return revenue;
    }

    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                payments.add(new Payment(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4], parts[5]));
            }
        } catch (IOException e) {
            // Tạo mẫu nếu tệp trống
            payments.add(new Payment(java.util.UUID.randomUUID().toString(), "S1", "C1", 100.0, "2023-09-01", "Paid"));
        }
    }

    @Override
    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Payment p : payments) {
                pw.println(p.getId() + "," + p.getStudentId() + "," + p.getCourseId() + "," + p.getAmount() + "," + p.getDate() + "," + p.getStatus());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}