import java.util.Scanner;
import repository.*;
import model.*;
import exception.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CourseRepository courseRepo = new CourseRepository();
    private static final UserRepository userRepo = new UserRepository();
    private static final ModuleRepository moduleRepo = new ModuleRepository();
    private static final ProgressRepository progressRepo = new ProgressRepository();
    private static final GradeRepository gradeRepo = new GradeRepository();
    private static final CertificateRepository certRepo = new CertificateRepository();
    private static final PaymentRepository paymentRepo = new PaymentRepository();

    public static void main(String[] args) {
        loadData();
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1: manageCourses(); break;
                case 2: manageStudents(); break;
                case 3: managePayments(); break;
                case 4: searchAndStats(); break;
                case 5: saveData(); System.exit(0); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Online Course Management System ===");
        System.out.println("1. Manage Courses");
        System.out.println("2. Manage Students & Enrollment");
        System.out.println("3. Manage Payments");
        System.out.println("4. Search & Statistics");
        System.out.println("5. Exit");
        System.out.print("Choose: ");
    }

    private static void manageCourses() {
        System.out.println("1. Add Course 2. Edit Course 3. Delete Course 4. List Courses");
        int subChoice = scanner.nextInt();
        scanner.nextLine();
        switch (subChoice) {
            case 1: addCourse(); break;
            case 2: editCourse(); break;
            case 3: deleteCourse(); break;
            case 4: courseRepo.listCourses(); break;
        }
    }

    private static void addCourse() {
        System.out.print("Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Instructor ID: ");
        String instructorId = scanner.nextLine();
        System.out.print("Duration (hours): ");
        int duration = scanner.nextInt();
        System.out.print("Fee: ");
        double fee = scanner.nextDouble();
        scanner.nextLine();
        Course course = new Course(generateId(), name, instructorId, duration, fee);
        courseRepo.addCourse(course);
        System.out.println("Course added.");
    }

    private static void editCourse() {
        System.out.print("Course ID: ");
        String id = scanner.nextLine();
        try {
            Course course = courseRepo.findCourseById(id);
            System.out.print("New Name: ");
            course.setName(scanner.nextLine());
            courseRepo.updateCourse(course);
            System.out.println("Course updated.");
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteCourse() {
        System.out.print("Course ID: ");
        String id = scanner.nextLine();
        try {
            courseRepo.deleteCourse(id);
            System.out.println("Course deleted.");
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void manageStudents() {
        System.out.println("1. Register Course 2. Unregister Course 3. View Progress 4. Issue Certificate");
        int subChoice = scanner.nextInt();
        scanner.nextLine();
        switch (subChoice) {
            case 1: registerCourse(); break;
            case 2: unregisterCourse(); break;
            case 3: viewProgress(); break;
            case 4: issueCertificate(); break;
        }
    }

    private static void registerCourse() {
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Course ID: ");
        String courseId = scanner.nextLine();
        try {
            Student student = (Student) userRepo.findUserById(studentId);
            student.enrollCourse(courseId);
            userRepo.updateUser(student);
            System.out.println("Registered.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void unregisterCourse() {
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Course ID: ");
        String courseId = scanner.nextLine();
        try {
            Student student = (Student) userRepo.findUserById(studentId);
            student.unenrollCourse(courseId);
            userRepo.updateUser(student);
            System.out.println("Unregistered.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewProgress() {
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Course ID: ");
        String courseId = scanner.nextLine();
        try {
            Progress progress = progressRepo.getProgress(studentId, courseId);
            System.out.println("Progress: " + progress.getPercentage() + "%");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void issueCertificate() {
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Course ID: ");
        String courseId = scanner.nextLine();
        try {
            certRepo.issueCertificate(studentId, courseId);
            System.out.println("Certificate issued.");
        } catch (CertificateAlreadyIssuedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void managePayments() {
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        Payment payment = new Payment(generateId(), studentId, courseId, amount, date, "Paid");
        paymentRepo.addPayment(payment);
        System.out.println("Payment recorded.");
    }

    private static void searchAndStats() {
        System.out.println("1. Search Courses 2. Revenue Stats");
        int subChoice = scanner.nextInt();
        scanner.nextLine();
        switch (subChoice) {
            case 1: searchCourses(); break;
            case 2: revenueStats(); break;
        }
    }

    private static void searchCourses() {
        System.out.print("Keyword (name/instructor): ");
        String keyword = scanner.nextLine();
        courseRepo.searchCourses(keyword).forEach(System.out::println);
    }

    private static void revenueStats() {
        System.out.println("Revenue by Course:");
        paymentRepo.getRevenueByCourse().forEach((course, revenue) ->
                System.out.println(course + ": $" + revenue));
    }

    private static void loadData() {
        courseRepo.load();
        userRepo.load();
        moduleRepo.load();
        progressRepo.load();
        gradeRepo.load();
        certRepo.load();
        paymentRepo.load();
    }

    private static void saveData() {
        courseRepo.save();
        userRepo.save();
        moduleRepo.save();
        progressRepo.save();
        gradeRepo.save();
        certRepo.save();
        paymentRepo.save();
    }

    private static String generateId() {
        return java.util.UUID.randomUUID().toString();
    }
}