import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Properties;

public class Suffix {
    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    protected String suffix;
    protected Path directory;
    protected Path fileHelp;
    protected Path fileSetting;
    protected Path fileInfo;

    public void setProperties() {
        InputStream inputStream;
        Properties properties = new Properties();
        try {
            inputStream = Files.newInputStream(Paths.get(PATH_TO_PROPERTIES));
            properties.load(inputStream);
            suffix = properties.getProperty("suffix");
            directory = Paths.get(properties.getProperty("directory"));
            fileHelp = Paths.get(properties.getProperty("fileHelp"));
            fileSetting = Paths.get(properties.getProperty("fileSetting"));
            fileInfo = Paths.get(properties.getProperty("fileInfo"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл config.properties не найден.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkFileIsExist() {
        Path[] paths = new Path[]{fileHelp, fileSetting, fileInfo};
        Path[] filePaths = new Path[paths.length];
        for (int i = 0; i < paths.length; i++) {
            filePaths[i] = Paths.get(directory + "\\" + paths[i]);
        }
        for (Path path : filePaths) {
            if (!Files.exists(path)) {
                System.out.println("Файл " + path + " не существует.");
            }
        }
    }
}
