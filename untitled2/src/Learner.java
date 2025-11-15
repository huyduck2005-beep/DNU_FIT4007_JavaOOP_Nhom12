// File: Learner.java (Thay thế cho Student.java)
public class Learner extends User {
    // Thêm trường dữ liệu riêng cho Learner nếu cần (ví dụ: date of birth)
    public Learner(String id, String fullName, String email, String phone) {
        super(id, fullName, email, phone);
    }
    // Setters và Getters cho các thuộc tính inherited từ User
    @Override
    public void saveToFile() { DataManager.saveToFile(this, "users.csv"); }
    @Override
    public void loadFromFile() { /* Load logic... */ }
    @Override
    public String toString() {
        return "LEARNER," + id + "," + fullName + "," + email + "," + phone; // Thêm type indicator
    }
}