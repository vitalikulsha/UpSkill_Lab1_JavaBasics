import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Properties;

public class Suffix {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    private String suffix;
    private Path directory;
    private Path fileHelp;
    private Path fileSetting;
    private Path fileInfo;

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
            System.out.println("File \"config.properties\" not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkFileIsExist() {
        Path[] filePaths = getFilePaths();
        for (Path path : filePaths) {
            if (!Files.exists(path)) {
                System.out.println("File \"" + path + "\" not exist.");
            }
        }
    }

    public void renameFile() {
        Path[] filePaths = getFilePaths();


        Path originalName = Path.of(directory + "\\" + fileHelp);
        String str = String.valueOf(fileHelp);
        String[] nameFile = str.split("\\.");
        Path newName = Path.of(directory + "\\" + (nameFile[0] + suffix + "." + nameFile[1]));
        try {
            Files.move(originalName,newName);
        } catch (IOException e) {
            System.out.println("File \""+ originalName + "\" not renamed");
            e.printStackTrace();
        }
    }

    private Path[] getFilePaths() {
        Path[] paths = new Path[]{fileHelp, fileSetting, fileInfo};
        Path[] filePaths = new Path[paths.length];
        for (int i = 0; i < paths.length; i++) {
            filePaths[i] = Paths.get(directory + "\\" + paths[i]);
        }
        return filePaths;
    }

    public String getSuffix() {
        return suffix;
    }

    public Path getDirectory() {
        return directory;
    }

    public Path getFileHelp() {
        return fileHelp;
    }

    public Path getFileSetting() {
        return fileSetting;
    }

    public Path getFileInfo() {
        return fileInfo;
    }
}
