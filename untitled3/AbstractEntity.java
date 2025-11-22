public abstract class AbstractEntity implements Persistable {
    protected String id;

    public AbstractEntity(String id) {
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public abstract void saveToFile();

    @Override
    public abstract void loadFromFile();
}