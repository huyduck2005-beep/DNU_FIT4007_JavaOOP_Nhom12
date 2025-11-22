public abstract class User extends AbstractEntity {
    protected String fullName;
    protected String email;
    protected String phone;

    public User(String id, String fullName, String email, String phone) {
        super(id);
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    // Setter, Getter...
}