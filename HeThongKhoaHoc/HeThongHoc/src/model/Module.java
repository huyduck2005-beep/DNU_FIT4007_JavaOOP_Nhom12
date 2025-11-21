package model;

public abstract class Module {
    protected String id;
    protected String name;
    protected String type;
    protected int duration;
    protected boolean isActive;

    public Module(String id, String name, String type, int duration) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.isActive = true;
    }

    public abstract int getDuration();
    public abstract boolean isCompleted();

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Module{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", type='" + type + '\'' + ", duration=" + duration + ", isActive=" + isActive + '}';
    }
}
