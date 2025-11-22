// File: Main.java (Đã chỉnh sửa/Bổ sung)
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    // Thay thế các ArrayList/TreeMap tĩnh bằng Repository instances
    private static final CourseRepository courseRepo = new CourseRepository();
    private static final LearnerRepository learnerRepo = new LearnerRepository();
    private static final CertificateRepository certRepo = new CertificateRepository();
    // private static final ModuleRepository moduleRepo = new ModuleRepository();
    // private static final ProgressRepository progressRepo = new ProgressRepository();
    // private static final GradeRepository gradeRepo = new GradeRepository();
    // Giữ lại Enrollment/Payment tạm thời cho tiện
    private static ArrayList<Enrollment> enrollments = new ArrayList<>();
    private static ArrayList<Payment> payments = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        // ... (Vòng lặp menu chính giữ nguyên) ...
        // Thêm các Case 9, 10...
    }

    private static void loadData() {
        courseRepo.loadAll();
        learnerRepo.loadAll();
        certRepo.loadAll();
        // enrollments = (ArrayList<Enrollment>) DataManager.loadFromFile("enrollments.txt", Enrollment.class); // Cập nhật tên file
        // payments = (ArrayList<Payment>) DataManager.loadFromFile("payments.txt", Payment.class);
    }

    private static void saveData() {
        courseRepo.saveAll();
        learnerRepo.saveAll();
        certRepo.saveAll();
        // DataManager.saveAllToFile(enrollments, "enrollments.txt"); // Cập nhật tên file
        // DataManager.saveAllToFile(payments, "payments.txt");
    }

    // Cần thay đổi logic: Student -> Learner
    private static void manageStudents(Scanner scanner) {
        // ... (sửa logic add/edit/delete để sử dụng learnerRepo) ...
        // Ví dụ: learnerRepo.save(new Learner(...))
    }

    // ... (các hàm quản lý Course, Enrollment, Payment giữ nguyên cấu trúc) ...

    // Hàm mới: Quản lý mô-đun (cần tạo Module Entity trước)
    private static void manageModules(Scanner scanner) {
        System.out.println("1. Add Module to Course");
        System.out.println("2. Update Module Content");
        // Logic để thêm/sửa/xóa Module trong ModuleRepository
    }

    // Hàm mới: Cập nhật tiến độ học tập (Logic nghiệp vụ chính)
    private static void updateProgress(Scanner scanner) {
        System.out.print("Learner ID: "); String learnerId = scanner.nextLine();
        System.out.print("Course ID: "); String courseId = scanner.nextLine();
        System.out.print("Module ID: "); String moduleId = scanner.nextLine();

        // 1. Tìm Progress record
        // 2. Cập nhật trạng thái/phần trăm hoàn thành
        // 3. Cập nhật Progress tổng thể của Course
        System.out.println("Progress updated successfully.");

        // Sau khi cập nhật, kiểm tra xem có thể cấp chứng chỉ không
        checkAndIssueCertificate(learnerId, courseId);
    }

    private static void checkAndIssueCertificate(String learnerId, String courseId) {
        // 1. Kiểm tra: Đã cấp chứng chỉ chưa? -> Ném CertificateAlreadyIssuedException
        // if (certRepo.hasCertificate(learnerId, courseId)) throw new CertificateAlreadyIssuedException();

        // 2. Kiểm tra: Tiến độ 100%? (Sử dụng ProgressRepository)
        // 3. Kiểm tra: Điểm tổng kết đạt yêu cầu? (Sử dụng GradeRepository)

        // Logic cấp chứng chỉ
        // if (isCompleted && isGradeSufficient) {
        //     String uniqueCode = UUID.randomUUID().toString();
        //     Certificate cert = new Certificate("C" + (certRepo.findAll().size() + 1),
        //                                       learnerId, courseId, uniqueCode, finalGrade, LocalDate.now());
        //     certRepo.save(cert);
        //     System.out.println("Certificate issued successfully! Code: " + uniqueCode);
        // }
    }

    // Hàm mới: Xuất báo cáo chi tiết 📊
    private static void generateReports() {
        // Báo cáo cũ (Revenue) giữ nguyên
        // Báo cáo mới theo yêu cầu đề tài:

        // 1. Danh sách học viên đã hoàn thành khóa học
        System.out.println("\n--- Learners with Completed Courses ---");
        // Lấy danh sách từ certRepo
        // certRepo.findAll().stream().forEach(c -> System.out.println(learnerRepo.findById(c.getLearnerId()).getFullName() + " - " + c.getCourseId()));

        // 2. Tỷ lệ hoàn thành theo khóa học
        System.out.println("\n--- Completion Rate by Course ---");
        // Logic tính toán từ ProgressRepository (Total Learners / Completed Learners)

        // 3. Top học viên điểm cao nhất
        System.out.println("\n--- Top Learners by Final Grade ---");
        // Logic lọc và sắp xếp từ GradeRepository/CertificateRepository
    }
}