import java.util.List;
import java.util.Optional;
public interface AbstractRepository<T extends AbstractEntity> {
    void save(T entity);
    Optional<T> findById(String id);
    List<T> findAll();
    void delete(String id);
    void loadAll();
    void saveAll();
}