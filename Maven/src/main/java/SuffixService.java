import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SuffixService {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    private String suffix;
    private Path directory;
    //    private Path[] fileList;
    private Path fileHelp;
    private Path fileSetting;
    private Path fileInfo;
    private Map<Path, String> filesMap = new HashMap();

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
//            String[] strArr = properties.getProperty("fileList").split(";");
//            for (int i = 0; i < strArr.length; i++) {
//               fileList[i] = Paths.get(strArr[i]);
//           }
        } catch (FileNotFoundException e) {
            System.out.println("File \"config.properties\" not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renameFile() {
        Path[] fileList = new Path[]{fileHelp, fileSetting, fileInfo};
        for (int i = 0; i < fileList.length; i++) {
            Path originalName = Path.of(directory + "\\" + fileList[i]);
            if (Files.exists(originalName)) {
                String[] strArr = String.valueOf(fileList[i]).split("\\.");
                String newNameFile = strArr[0] + suffix + "." + strArr[1];
                filesMap.put(fileList[i], newNameFile);
                Path newName = Path.of(directory + "\\" + newNameFile);
                try {
                    Files.move(originalName, newName);
                } catch (IOException e) {
                    System.out.println("File \"" + originalName + "\" not renamed");
                    e.printStackTrace();
                }
            } else {
                filesMap.put(fileList[i], "File not exist.");
                System.out.println("File \"" + fileList[i] + "\" not exist.");
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
