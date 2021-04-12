import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import java.io.*;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class HandlerXMLJackson {
    private final static Logger LOG = LoggerFactory.getLogger(HandlerXMLJackson.class);
    private final static String PATH_TO_CONFIG = "src/main/resources/config.xml";
    private final static String PATH_TO_SCHEMA = "src/main/resources/config.xsd";
    private final static String PATH_TO_RESULT = "data/result.xml";
    private ObjectMapper mapper = new XmlMapper();

    public Config parse() {
        Config config;
        if (!validateXMLSchema(PATH_TO_SCHEMA, PATH_TO_CONFIG)) {
            return null;
        }
        try (InputStream input = new FileInputStream(PATH_TO_CONFIG)) {
            TypeReference<Config> typeReference = new TypeReference<Config>() {
            };
            config = mapper.readValue(input, typeReference);
        } catch (FileNotFoundException e) {
            LOG.error("File not found. Config object not created: ", e);
            return null;
        } catch (IOException e) {
            LOG.error("Config object not created: ", e);
            return null;
        }
        LOG.info("Config file reading: {}", config.toString());
        return config;
    }

    public void writeXML(Config config) {
        if (config == null) {
            LOG.error("Results file not created: config file is missing");
            return;
        }
        Result result = new Result();
        result.setFileConfig(getFilenameResult());
        result.setTimestamp(getFileCreateDate());
        List<Path> originalFiles = FileUtil.getFilesExistList(config);
        result.setOriginalFilenames(getFilenames(originalFiles));
        result.setNewFilenames(getFilenames(FileUtil.renameFiles(config, originalFiles)));
        try {
            mapper.writeValue(new File(PATH_TO_RESULT), result);
            LOG.info("File '{}' created.", PATH_TO_RESULT);
        } catch (IOException e) {
            LOG.error("File not create: ", e);
        }
    }

    private boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            LOG.error("Config file no read", e);
            return false;
        } catch (SAXException e) {
            LOG.error("Config file is invalid", e);
            return false;
        }
        return true;
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
