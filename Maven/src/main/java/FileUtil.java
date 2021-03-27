import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileUtil {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    public static Config initProps() {
        Properties properties = new Properties();
        Config config = new Config();
        try (InputStream inputStream = Files.newInputStream(Paths.get(PATH_TO_PROPERTIES))) {
            properties.load(inputStream);
            config.setSuffix(properties.getProperty("suffix"));
            config.setDirectory(Paths.get(properties.getProperty("directory")));
            String[] filenamesArray = properties.getProperty("filesList").split(";");
            List<Path> filePathsList = new ArrayList<>();
            for (String filename : filenamesArray) {
                filePathsList.add(Paths.get(filename));
            }
            config.setFilesList(filePathsList);
        } catch (FileNotFoundException e) {
            System.out.println("File \"config.properties\" not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static Map<Path, String> renameFiles(Config config) {
        Map<Path, String> filesMap = new HashMap();
        for (int i = 0; i < config.getFilesList().size(); i++) {
            Path originalName = Path.of(config.getDirectory() + "\\" + config.getFilesList().get(i));
            if (Files.exists(originalName)) {
                String[] filenamesList = String.valueOf(config.getFilesList().get(i)).split("\\.");
                String newNameFile = filenamesList[0] + config.getSuffix() + "." + filenamesList[1];
                filesMap.put(config.getFilesList().get(i), newNameFile);
                Path newName = Path.of(config.getDirectory() + "\\" + newNameFile);
                try {
                    Files.move(originalName, newName);
                } catch (IOException e) {
                    System.out.println("File \"" + originalName + "\" not renamed");
                    e.printStackTrace();
                }
            } else {
                filesMap.put(config.getFilesList().get(i), "File not exist.");
                System.out.println("File \"" + config.getFilesList().get(i) + "\" not exist.");
            }
        }
        return filesMap;
    }

    public static void printResult(Config config) {
        Map<Path, String> filesMap = renameFiles(config);
        System.out.println("\nOriginal name --> New name\n===========================");
        for (Map.Entry<Path, String> file : filesMap.entrySet()) {
            System.out.println(file.getKey() + " --> " + file.getValue());
        }
    }
}
