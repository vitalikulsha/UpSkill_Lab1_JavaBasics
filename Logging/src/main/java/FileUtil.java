import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class FileUtil {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    final static Logger log = Logger.getLogger(FileUtil.class);

    public static Config initProps() {
        log.debug("Method \"initProps()\" running.");
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
            log.info("Config file reading");
        } catch (FileNotFoundException e) {
            log.error("File \"config.properties\" not found.");
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
        return config;
    }

    public static void renameFiles(Config config) {
        log.debug("Method \"renameFiles()\" running.");
        for (int i = 0; i < config.getFilesList().size(); i++) {
            Path originalName = Path.of(config.getDirectory() + "\\" + config.getFilesList().get(i));
            if (Files.exists(originalName)) {
                String[] filenamesList = String.valueOf(config.getFilesList().get(i)).split("\\.");
                String newNameFile = filenamesList[0] + config.getSuffix() + "." + filenamesList[1];
                Path newName = Path.of(config.getDirectory() + "\\" + newNameFile);
                try {
                    Files.move(originalName, newName);
                    log.info("File \"" + config.getFilesList().get(i) + "\" renamed to file " + newNameFile);
                } catch (IOException e) {
                    log.error("File \"" + originalName + "\" not renamed");
                    log.error(e);
                }
            } else {
                log.info("File \"" + config.getFilesList().get(i) + "\" not exist.");
            }
        }
    }
}
