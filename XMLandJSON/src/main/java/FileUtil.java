import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class FileUtil {
    private final static Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    public static List<Path> getFilesExistList(Config config) {
        List<Path> filesExistList = new ArrayList<>();
        for (Path file : config.getFilesList()) {
            Path currentFile = Path.of(config.getDirectory() + "\\" + file);
            if (Files.exists(currentFile)) {
                LOG.info("File '{}' exist.", file);
                filesExistList.add(file);
            } else {
                LOG.info("File '{}' not exist.", file);
            }
        }
        LOG.info("filesExistList = {}", filesExistList);
        return filesExistList;
    }

    public static List<Path> renameFiles(Config config, List<Path> originalFiles) {
        List<Path> newFilesList = new ArrayList<>();
        for (Path file : originalFiles) {
            Path originalFilename = Path.of(config.getDirectory() + "\\" + file);
            String[] filename = String.valueOf(file).split("\\.");
            String newName = filename[0] + config.getSuffix() + "." + filename[1];
            Path newFilename = Path.of(config.getDirectory() + "\\" + newName);
            try {
                Files.move(originalFilename, newFilename);
                LOG.info("File '{}' renamed to file '{}'", file, newName);
            } catch (IOException e) {
                LOG.error("File '{}' not renamed: ", originalFilename, e);
            }
            newFilesList.add(Path.of(newName));
        }
        return newFilesList;
    }
}
