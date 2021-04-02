import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParserJackson {
    private final static Logger LOG = Logger.getLogger(ParserJackson.class);
    protected final static String PATH_TO_CONFIG = "src/main/resources/config.xml";
    private final static String PATH_TO_RESULT = "data/log/result.xml";

    public Config parse() {
        Config config;
        ObjectMapper mapper = new XmlMapper();
        try (InputStream input = new FileInputStream(new File(PATH_TO_CONFIG))) {
            TypeReference<Config> typeReference = new TypeReference<Config>() {
            };
            config = mapper.readValue(input, typeReference);
        } catch (FileNotFoundException e) {
            LOG.error("File not found. Config object not created: " + e);
            return null;
        } catch (IOException e) {
            LOG.error("Config object not created: " + e);
            return null;
        }
        LOG.info("Config file reading: " + config.toString());
        return config;
    }

    public void createXML(Config config) {
        ObjectMapper mapper = new XmlMapper();
        Result result = new Result();
        result.setFileConfig(getFilenameResult());
        result.setTime(getFileCreateDate());
        result.setOriginalFilenames(getFilenames(FileUtil.checkFilesExist(config)));
        result.setNewFilenames(getFilenames(FileUtil.renameFiles(config)));
        try {
            mapper.writeValue(new File(PATH_TO_RESULT), result);
            LOG.info("File " + getFilenameResult() + " created.");
        } catch (IOException e) {
            LOG.error("File not create: " + e);
        }
    }

    private String getFilenameResult() {
        String[] tempArray = PATH_TO_CONFIG.split("/");
        return tempArray[tempArray.length - 1];
    }

    private String getFileCreateDate() {
        Calendar currentTime = GregorianCalendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentTime.getTime());
    }

    private List<String> getFilenames(List<Path> pathList) {
        List<String> filenames = new ArrayList<>();
        for (Path file : pathList) {
            filenames.add(String.valueOf(file));
        }
        return filenames;
    }
}
