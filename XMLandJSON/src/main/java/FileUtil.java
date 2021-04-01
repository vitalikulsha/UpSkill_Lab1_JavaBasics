import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class FileUtil {
    private final static Logger LOG = Logger.getLogger(FileUtil.class);

    private static List<Path> checkFilesExist(Config config) {
        List<Path> filesExistList = new ArrayList<>();
        for (Path file : config.getFilesList()) {
            Path currentFile = Path.of(config.getDirectory() + "\\" + file);
            if (Files.exists(currentFile)) {
                LOG.debug("File \"" + file + "\" exist.");
                filesExistList.add(file);
            } else {
                LOG.info("File \"" + file + "\" not exist.");
            }
        }
        LOG.debug("filesExistList = " + filesExistList);
        return filesExistList;
    }

    public static Map<Path, String> renameFiles(Config config) {
        List<Path> checkFilesExist = checkFilesExist(config);
        Map<Path, String> filesMap = new HashMap<>();
        for (Path file : checkFilesExist) {
            Path originalName = Path.of(config.getDirectory() + "\\" + file);
            String[] filenamesList = String.valueOf(file).split("\\.");
            String newNameFile = filenamesList[0] + config.getSuffix() + "." + filenamesList[1];
            filesMap.put(file, newNameFile);
            Path newName = Path.of(config.getDirectory() + "\\" + newNameFile);
            try {
                Files.move(originalName, newName);
                LOG.info("File \"" + file + "\" renamed to file \"" + newNameFile + '\"');
            } catch (IOException e) {
                LOG.error("File \"" + originalName + "\" not renamed: " + e);
            }
        }
        return filesMap;
    }

    public static void printResult(Config config) {
        Map<Path, String> filesMap = renameFiles(config);
        LOG.info("\nyyyy-MM-dd HH:mm:ss Level [main]: Original name --> New name\n" +
                "=============================================================");
        if (!filesMap.isEmpty()) {
            for (Map.Entry<Path, String> file : filesMap.entrySet()) {
                LOG.info(file.getKey() + " --> " + file.getValue());
            }
        } else {
            LOG.info("Files not find");
        }

    }
}
