import java.time.LocalDate;
import java.util.*;

public class Main {
    private static TreeMap<String, Course> courses = new TreeMap<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Enrollment> enrollments = new ArrayList<>();
    private static ArrayList<Payment> payments = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Course Management System ===");
            System.out.println("1. Manage Courses (Add/Edit/Delete)");
            System.out.println("2. Manage Students (Add/Edit/Delete)");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Cancel Enrollment");
            System.out.println("5. Manage Payments");
            System.out.println("6. Search Courses");
            System.out.println("7. Generate Reports");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1: manageCourses(scanner); break;
                case 2: manageStudents(scanner); break;
                case 3: enrollStudent(scanner); break;
                case 4: cancelEnrollment(scanner); break;
                case 5: managePayments(scanner); break;
                case 6: searchCourses(scanner); break;
                case 7: generateReports(); break;
                case 8: saveData(); System.exit(0); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void loadData() {
        students = (ArrayList<Student>) DataManager.loadFromFile("students.txt", Student.class);
        List<Course> courseList = DataManager.loadFromFile("courses.txt", Course.class);
        for (Course c : courseList) courses.put(c.getId(), c);
        enrollments = (ArrayList<Enrollment>) DataManager.loadFromFile("enrollments.txt", Enrollment.class);
        payments = (ArrayList<Payment>) DataManager.loadFromFile("payments.txt", Payment.class);
    }

    private static void saveData() {
        DataManager.saveAllToFile(students, "students.txt");
        DataManager.saveAllToFile(new ArrayList<>(courses.values()), "courses.txt");
        DataManager.saveAllToFile(enrollments, "enrollments.txt");
        DataManager.saveAllToFile(payments, "payments.txt");
    }

    private static void manageCourses(Scanner scanner) {
        System.out.println("1. Add Course");
        System.out.println("2. Edit Course");
        System.out.println("3. Delete Course");
        int subChoice = scanner.nextInt();
        scanner.nextLine();

        if (subChoice == 1) {
            System.out.print("ID: "); String id = scanner.nextLine();
            System.out.print("Name: "); String name = scanner.nextLine();
            System.out.print("Instructor: "); String instructor = scanner.nextLine();
            System.out.print("Duration (hours): "); int duration = scanner.nextInt();
            System.out.print("Fee: "); double fee = scanner.nextDouble();
            courses.put(id, new Course(id, name, instructor, duration, fee));
        } else if (subChoice == 2) {
            System.out.print("Course ID: "); String id = scanner.nextLine();
            if (courses.containsKey(id)) {
                Course c = courses.get(id);
                System.out.print("New Name: "); c.setName(scanner.nextLine());
                System.out.print("New Instructor: "); c.setInstructor(scanner.nextLine());
                System.out.print("New Duration: "); c.setDuration(scanner.nextInt());
                System.out.print("New Fee: "); c.setFee(scanner.nextDouble());
            }
        } else if (subChoice == 3) {
            System.out.print("Course ID: "); String id = scanner.nextLine();
            courses.remove(id);
        }
    }

    private static void manageStudents(Scanner scanner) {
        System.out.println("1. Add Student");
        System.out.println("2. Edit Student");
        System.out.println("3. Delete Student");
        int subChoice = scanner.nextInt();
        scanner.nextLine();

        if (subChoice == 1) {
            System.out.print("ID: "); String id = scanner.nextLine();
            System.out.print("Full Name: "); String name = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            System.out.print("Phone: "); String phone = scanner.nextLine();
            students.add(new Student(id, name, email, phone));
        } else if (subChoice == 2) {
            System.out.print("Student ID: "); String id = scanner.nextLine();
            for (Student s : students) {
                if (s.getId().equals(id)) {
                    System.out.print("New Name: "); s.setFullName(scanner.nextLine());
                    System.out.print("New Email: "); s.setEmail(scanner.nextLine());
                    System.out.print("New Phone: "); s.setPhone(scanner.nextLine());
                    break;
                }
            }
        } else if (subChoice == 3) {
            System.out.print("Student ID: "); String id = scanner.nextLine();
            students.removeIf(s -> s.getId().equals(id));
        }
    }

    private static void enrollStudent(Scanner scanner) {
        System.out.print("Student ID: "); String studentId = scanner.nextLine();
        System.out.print("Course ID: "); String courseId = scanner.nextLine();
        String enrollmentId = "E" + (enrollments.size() + 1);
        enrollments.add(new Enrollment(enrollmentId, studentId, courseId, LocalDate.now()));
        System.out.println("Enrolled successfully.");
    }

    private static void cancelEnrollment(Scanner scanner) {
        System.out.print("Enrollment ID: "); String id = scanner.nextLine();
        enrollments.removeIf(e -> e.getId().equals(id));
        System.out.println("Enrollment canceled.");
    }

    private static void managePayments(Scanner scanner) {
        System.out.print("Enrollment ID: "); String enrollmentId = scanner.nextLine();
        System.out.print("Amount: "); double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Payment Date (yyyy-mm-dd): "); LocalDate date = LocalDate.parse(scanner.nextLine());
        String paymentId = "P" + (payments.size() + 1);
        payments.add(new Payment(paymentId, enrollmentId, amount, date, true));
        System.out.println("Payment recorded.");
    }

    private static void searchCourses(Scanner scanner) {
        System.out.println("Search by: 1. Name 2. Instructor 3. Fee Range");
        int type = scanner.nextInt();
        scanner.nextLine();
        List<Course> results = new ArrayList<>();
        if (type == 1) {
            System.out.print("Name: "); String name = scanner.nextLine();
            for (Course c : courses.values()) {
                if (c.getName().contains(name)) results.add(c);
            }
        } else if (type == 2) {
            System.out.print("Instructor: "); String instructor = scanner.nextLine();
            for (Course c : courses.values()) {
                if (c.getInstructor().contains(instructor)) results.add(c);
            }
        } else if (type == 3) {
            System.out.print("Min Fee: "); double min = scanner.nextDouble();
            System.out.print("Max Fee: "); double max = scanner.nextDouble();
            for (Course c : courses.values()) {
                if (c.getFee() >= min && c.getFee() <= max) results.add(c);
            }
        }
        for (Course c : results) System.out.println(c);
    }

    private static void generateReports() {
        // Thống kê doanh thu từng khóa học
        Map<String, Double> revenueByCourse = new HashMap<>();
        for (Payment p : payments) {
            if (p.isPaid()) {
                for (Enrollment e : enrollments) {
                    if (e.getId().equals(p.getEnrollmentId())) {
                        revenueByCourse.put(e.getCourseId(), revenueByCourse.getOrDefault(e.getCourseId(), 0.0) + p.getAmount());
                    }
                }
            }
        }
        System.out.println("Revenue by Course:");
        for (Map.Entry<String, Double> entry : revenueByCourse.entrySet()) {
            System.out.println("Course " + entry.getKey() + ": $" + entry.getValue());
        }

        // Thống kê doanh thu theo tháng (giả định từ paymentDate)
        Map<String, Double> revenueByMonth = new HashMap<>();
        for (Payment p : payments) {
            if (p.isPaid()) {
                String month = p.getPaymentDate().getYear() + "-" + p.getPaymentDate().getMonthValue();
                revenueByMonth.put(month, revenueByMonth.getOrDefault(month, 0.0) + p.getAmount());
            }
        }
        System.out.println("Revenue by Month:");
        for (Map.Entry<String, Double> entry : revenueByMonth.entrySet()) {
            System.out.println("Month " + entry.getKey() + ": $" + entry.getValue());
        }
    }
}
