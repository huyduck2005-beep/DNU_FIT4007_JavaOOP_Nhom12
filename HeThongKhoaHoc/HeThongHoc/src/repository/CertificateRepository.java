package repository;

import java.io.*;
import java.util.*;
import model.*;
import exception.*;

public class CertificateRepository implements Persistable {
    private List<Certificate> certificates = new ArrayList<>();
    private static final String FILE = "data/certificates.csv";

    public void addCertificate(Certificate cert) {
        certificates.add(cert);
    }

    public boolean hasCertificate(String studentId, String courseId) {
        return certificates.stream().anyMatch(c -> c.getStudentId().equals(studentId) && c.getCourseId().equals(courseId));
    }

    public void issueCertificate(String studentId, String courseId) throws CertificateAlreadyIssuedException {
        if (hasCertificate(studentId, courseId)) {
            throw new CertificateAlreadyIssuedException("Certificate already issued for this student and course.");
        }
        String id = java.util.UUID.randomUUID().toString();
        String date = java.time.LocalDate.now().toString();
        double score = 85.0; // Giả định điểm
        certificates.add(new Certificate(id, studentId, courseId, date, score));
    }

    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                certificates.add(new Certificate(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));
            }
        } catch (IOException e) {
            // Tạo mẫu nếu tệp trống
            certificates.add(new Certificate(java.util.UUID.randomUUID().toString(), "S1", "C1", "2023-10-01", 90.0));
        }
    }

    @Override
    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Certificate c : certificates) {
                pw.println(c.getId() + "," + c.getStudentId() + "," + c.getCourseId() + "," + c.getIssueDate() + "," + c.getFinalScore());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}