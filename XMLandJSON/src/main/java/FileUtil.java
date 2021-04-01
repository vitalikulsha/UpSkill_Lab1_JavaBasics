import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class FileUtil {
    private final static Logger LOG = Logger.getLogger(FileUtil.class);
    private static Map<Path, String> filesMap;

    public static List<Path> checkFileExist(Config config) {
        List<Path> filesExistList = new ArrayList<>();
        for (Path file : config.getFilesList()) {
            if (Files.exists(file)) {
                LOG.debug("File \"" + file + "\" exist.");
                filesExistList.add(file);
            } else {
                LOG.info("File \"" + file + "\" not exist.");
            }
        }
        return filesExistList;
    }

    public static void renameFiles(Config config) {
        for (int i = 0; i < config.getFilesList().size(); i++) {
            Path originalName = Path.of(config.getDirectory() + "\\" + config.getFilesList().get(i));
            if (Files.exists(originalName)) {
                String[] filenamesList = String.valueOf(config.getFilesList().get(i)).split("\\.");
                String newNameFile = filenamesList[0] + config.getSuffix() + "." + filenamesList[1];
                filesMap.put(config.getFilesList().get(i), newNameFile);
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

    public static void printResult() {
        LOG.info("\nOriginal name --> New name\n===========================");
        for (Map.Entry<Path, String> file : filesMap.entrySet()) {
            LOG.info(file.getKey() + " --> " + file.getValue());
        }
    }
}
