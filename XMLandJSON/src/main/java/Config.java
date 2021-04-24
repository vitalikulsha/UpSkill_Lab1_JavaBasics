import java.nio.file.Path;
import java.util.List;

public class Config {
    private String suffix;
    private Path directory;
    private List<Path> filesList;
    private String pathFileResult;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Path getDirectory() {
        return directory;
    }

    public void setDirectory(Path directory) {
        this.directory = directory;
    }

    public List<Path> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<Path> filesList) {
        this.filesList = filesList;
    }

    public String getPathFileResult() {
        return pathFileResult;
    }

    public void setPathFileResult(String pathFileResult) {
        this.pathFileResult = pathFileResult;
    }

    @Override
    public String toString() {
        return "Config{" +
                "suffix='" + suffix + '\'' +
                ", directory=" + directory +
                ", filesList=" + filesList +
                ", pathFileResult='" + pathFileResult + '\'' +
                '}';
    }
}
