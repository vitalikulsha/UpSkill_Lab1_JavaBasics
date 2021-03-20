import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;

public class Suffix {
    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    protected String suffix;
    protected Path fileHelp;
    protected Path fileSetting;
    protected Path fileInfo;

    public void setProperties() {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
            suffix = properties.getProperty("suffix");
            fileHelp = Paths.get(properties.getProperty("fileHelp"));
            fileSetting = Paths.get(properties.getProperty("fileSetting"));
            fileInfo = Paths.get(properties.getProperty("fileInfo"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkFileIsExist() {
        Path[] paths = new Path[]{fileHelp, fileSetting, fileInfo};
        for (Path path : paths) {
            if (!Files.exists(path)) {
                System.out.println("Файл " + path + " не существует.");
            }
        }
    }
}
