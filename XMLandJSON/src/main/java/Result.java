import java.nio.file.Path;
import java.util.Map;

public class Result {
    private String name;
    private String data;
    private Map<Path, String> filesMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Map<Path, String> getFilesMap() {
        return filesMap;
    }

    public void setFilesMap(Map<Path, String> filesMap) {
        this.filesMap = filesMap;
    }
}
