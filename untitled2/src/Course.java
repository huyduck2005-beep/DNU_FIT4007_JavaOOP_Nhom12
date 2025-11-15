public class Course extends AbstractEntity {
    private String name;
    private String instructor;
    private int duration;
    private double fee;

    public Course(String id, String name, String instructor, int duration, double fee) {
        super(id);
        this.name = name;
        this.instructor = instructor;
        this.duration = duration;
        this.fee = fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor() {
        this.instructor = instructor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration() {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee() {
        this.fee = fee;
    }

    @Override
    public void saveToFile() {
        DataManager.saveToFile(this, "course.txt");
    }

    @Override
    public void loadFromFile() {
        DataManager.loadFromFile(this, "course.txt");
    }

    @Override
    public String toString(){
        return id + "," + name + "," + instructor + "," + duration + "," + fee;
    }
}