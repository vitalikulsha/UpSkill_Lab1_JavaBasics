import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;

import java.io.*;

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
        result.setFilesMap(FileUtil.renameFiles(config));
        result.setData("2021-04-04 09:00:00");
        result.setName("config.xml");
        try {
            mapper.writeValue(new File(PATH_TO_RESULT), result);
        } catch (IOException e) {
            LOG.error("File not create: " + e);
        }
    }
}
