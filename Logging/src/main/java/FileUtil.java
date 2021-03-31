import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class FileUtil {
    private final static Logger LOG = Logger.getLogger(FileUtil.class);
    private final static String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    public static Config initProps() {
        LOG.debug("Method \"initProps()\" running.");
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
            LOG.info("Config file reading");
        } catch (FileNotFoundException e) {
            LOG.error("File \"config.properties\" not found.");
            LOG.error(e);
        } catch (IOException e) {
            LOG.error(e);
        }
        return config;
    }

    public static void renameFiles(Config config) {
        LOG.debug("Method \"renameFiles()\" running.");
        for (int i = 0; i < config.getFilesList().size(); i++) {
            Path originalName = Path.of(config.getDirectory() + "\\" + config.getFilesList().get(i));
            if (Files.exists(originalName)) {
                String[] filenamesList = String.valueOf(config.getFilesList().get(i)).split("\\.");
                String newNameFile = filenamesList[0] + config.getSuffix() + "." + filenamesList[1];
                Path newName = Path.of(config.getDirectory() + "\\" + newNameFile);
                try {
                    Files.move(originalName, newName);
                    LOG.info("File \"" + config.getFilesList().get(i) + "\" renamed to file \"" + newNameFile + '\"');
                } catch (IOException e) {
                    LOG.error("File \"" + originalName + "\" not renamed");
                    LOG.error(e);
                }
            } else {
                LOG.info("File \"" + config.getFilesList().get(i) + "\" not exist.");
            }
        }
    }
}
