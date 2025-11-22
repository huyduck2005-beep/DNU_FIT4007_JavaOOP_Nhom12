public class Student extends AbstractEntity {
    private String fullName;
    private String email;
    private String phone;

    public Student(String id, String fullName, String email, String phone) {
        super(id);
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void saveToFile() {
        DataManager.saveToFile(this, "students.txt");
    }
    @Override
    public void loadFromFile() {
        DataManager.loadFromFile(this, "students.txt");
    }
    @Override
    public String toString() {
        return id + "," + fullName + "," + email + "," + phone;
    }
}
