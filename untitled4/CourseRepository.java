import java.util.*;

public class CourseRepository implements AbstractRepository<Course> {
    private final Map<String, Course> courses = new TreeMap<>();
    private final String FILENAME = "courses.csv";

    @Override
    public void save(Course course) {
        courses.put(course.getID(), course);
    }

    @Override
    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courses.get(id));
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public void delete(String id) {
        courses.remove(id);
    }

    @Override
    public void loadAll() {
        List<Course> loadedList = DataManager.loadFromFile(FILENAME, Course.class);
        courses.clear();
        for (Course c : loadedList) {
            courses.put(c.getID(), c);
        }
    }

    @Override
    public void saveAll() {
        DataManager.saveAllToFile(findAll(), FILENAME);
    }
}