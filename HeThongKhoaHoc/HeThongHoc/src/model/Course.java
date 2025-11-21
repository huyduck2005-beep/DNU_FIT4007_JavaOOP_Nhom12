package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private String instructorId;
    private int duration;
    private double fee;
    private List<Module> modules = new ArrayList<>();

    public Course(String id, String name, String instructorId, int duration, double fee) {
        this.id = id;
        this.name = name;
        this.instructorId = instructorId;
        this.duration = duration;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getInstructorId() {
        return instructorId;
    }
    public int getDuration() {
        return duration;
    }
    public double getFee() {
        return fee;
    }
    public List<Module> getModules() {
        return modules;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addModule(Module module) {
        modules.add(module);
    }

    @Override
    public String toString() {
        return "Course{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", instructorId='" + instructorId + '\'' + ", duration=" + duration + ", fee=" + fee + '}';
    }
}
