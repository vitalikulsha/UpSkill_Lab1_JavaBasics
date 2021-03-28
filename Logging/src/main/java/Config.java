import lombok.Data;

import java.nio.file.Path;
import java.util.List;

@Data
public class Config {
    private String suffix;
    private Path directory;
    private List<Path> filesList;
}
