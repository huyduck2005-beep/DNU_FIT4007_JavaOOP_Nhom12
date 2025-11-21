package repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import model.Module;
import model.VideoModule;
import model.QuizModule;
import model.ProjectModule;
import exception.*;

public class ModuleRepository implements Persistable {
    private List<Module> modules = new ArrayList<>();
    private static final String FILE = "data/modules.csv";

    public void addModule(Module module) {
        modules.add(module);
    }

    public Module findModuleById(String id) throws ModuleNotFoundException {
        return modules.stream().filter(m -> m.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ModuleNotFoundException("Module not found"));
    }

    public List<Module> getModulesByCourse(String courseId) {
        return modules.stream().filter(m -> m.getId().startsWith(courseId)).collect(Collectors.toList()); // Giả định ID module bắt đầu bằng courseId
    }

    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[3];
                if (type.equals("Video")) {
                    modules.add(new VideoModule(parts[0], parts[1], Integer.parseInt(parts[2]), parts[4]));
                } else if (type.equals("Quiz")) {
                    modules.add(new QuizModule(parts[0], parts[1], Integer.parseInt(parts[2]), Arrays.asList("Q1", "Q2")));
                } else if (type.equals("Project")) {
                    modules.add(new ProjectModule(parts[0], parts[1], Integer.parseInt(parts[2]), parts[4]));
                }
            }
        } catch (IOException e) {
            // Tạo mẫu nếu tệp trống
            modules.add(new VideoModule(java.util.UUID.randomUUID().toString(), "Intro Video", 5, "url1"));
            modules.add(new QuizModule(java.util.UUID.randomUUID().toString(), "Basic Quiz", 10, Arrays.asList("Q1", "Q2")));
        }
    }

    @Override
    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Module m : modules) {
                String extra = "";
                if (m instanceof VideoModule) {
                    extra = ((VideoModule) m).getVideoUrl();
                } else if (m instanceof ProjectModule) {
                    extra = ((ProjectModule) m).getDescription();
                }
                pw.println(m.getId() + "," + m.getName() + "," + m.getDuration() + "," + m.getType() + "," + extra);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}