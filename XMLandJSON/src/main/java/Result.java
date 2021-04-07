import java.util.List;

public class Result {
    private String fileConfig;
    private String timestamp;
    private List<String> originalFilenames;
    private List<String> newFilenames;


    public String getFileConfig() {
        return fileConfig;
    }

    public void setFileConfig(String fileConfig) {
        this.fileConfig = fileConfig;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
