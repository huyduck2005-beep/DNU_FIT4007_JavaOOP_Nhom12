import java.time.LocalDate;

public class Payment extends AbstractEntity {
    private String enrollmentId;
    private double amount;
    private LocalDate paymentDate;
    private boolean isPaid;

    public Payment(String id, String enrollmentId, double amount, LocalDate paymentDate, boolean isPaid) {
        super(id);
        this.enrollmentId = enrollmentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.isPaid = isPaid;
    }

    // Getters và setters
    public String getEnrollmentId() { return enrollmentId; }
    public double getAmount() { return amount; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { this.isPaid = paid; }

    @Override
    public void saveToFile() {
        DataManager.saveToFile(this, "payments.txt");
    }

    @Override
    public void loadFromFile() {
        DataManager.loadFromFile(this, "payments.txt");
    }

    @Override
    public String toString() {
        return id + "," + enrollmentId + "," + amount + "," + paymentDate + "," + isPaid;
    }
}
