import java.nio.file.Path;
import java.util.List;

public class Result {
    private String fileConfig;
    private String time;
    private List<String> originalFilenames;
    private List<String> newFilenames;


    public String getFileConfig() {
        return fileConfig;
    }

    public void setFileConfig(String fileConfig) {
        this.fileConfig = fileConfig;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getOriginalFilenames() {
        return originalFilenames;
    }

    public void setOriginalFilenames(List<String> originalFilenames) {
        this.originalFilenames = originalFilenames;
    }

    public List<String> getNewFilenames() {
        return newFilenames;
    }

    public void setNewFilenames(List<String> newFilenames) {
        this.newFilenames = newFilenames;
    }
}
