public interface Persistable {
    void saveToFile();  // Giả định gọi DataManager để lưu CSV
    void loadFromFile(); // Giả định gọi DataManager để load CSV
}