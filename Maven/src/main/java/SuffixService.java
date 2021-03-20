import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;

public class SuffixService {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    private String suffix;
    private Path directory;
    private Path[] filesList;
    private Map<Path, String> filesMap = new HashMap();

    public void setProperties() {
        InputStream inputStream;
        Properties properties = new Properties();
        try {
            inputStream = Files.newInputStream(Paths.get(PATH_TO_PROPERTIES));
            properties.load(inputStream);
            suffix = properties.getProperty("suffix");
            directory = Paths.get(properties.getProperty("directory"));
            String[] strArr = properties.getProperty("filesList").split(";");
            filesList = new Path[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                filesList[i] = Paths.get(strArr[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"config.properties\" not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renameFile() {
        for (int i = 0; i < filesList.length; i++) {
            Path originalName = Path.of(directory + "\\" + filesList[i]);
            if (Files.exists(originalName)) {
                String[] strArr = String.valueOf(filesList[i]).split("\\.");
                String newNameFile = strArr[0] + suffix + "." + strArr[1];
                filesMap.put(filesList[i], newNameFile);
                Path newName = Path.of(directory + "\\" + newNameFile);
                try {
                    Files.move(originalName, newName);
                } catch (IOException e) {
                    System.out.println("File \"" + originalName + "\" not renamed");
                    e.printStackTrace();
                }
            } else {
                filesMap.put(filesList[i], "File not exist.");
                System.out.println("File \"" + filesList[i] + "\" not exist.");
            }
        }
    }

    public void printResult() {
        System.out.println("\nOriginal name --> New name\n------------------------");
        for (Map.Entry<Path, String> file : filesMap.entrySet()) {
            System.out.println(file.getKey() + " --> " + file.getValue());
        }
    }
}
