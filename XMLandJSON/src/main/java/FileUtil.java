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
                LOG.info("File \"{}\" exist.", file);
                filesExistList.add(file);
            } else {
                LOG.info("File \"{}\" not exist.", file);
            }
        }
        LOG.info("filesExistList = {}", filesExistList);
        return filesExistList;
    }

    public static List<Path> renameFiles(Config config, List<Path> originalFiles) {
        List<Path> newFiles = new ArrayList<>();
        for (Path file : originalFiles) {
            Path originalName = Path.of(config.getDirectory() + "\\" + file);
            String[] filenamesList = String.valueOf(file).split("\\.");
            String newNameFile = filenamesList[0] + config.getSuffix() + "." + filenamesList[1];
            Path newName = Path.of(config.getDirectory() + "\\" + newNameFile);
            try {
                Files.move(originalName, newName);
                LOG.info("File \"" + file + "\" renamed to file \"" + newNameFile + '\"');
            } catch (IOException e) {
                LOG.error("File \"" + originalName + "\" not renamed: " + e);
            }
            newFiles.add(Path.of(newNameFile));
        }
        return newFiles;
    }
}
