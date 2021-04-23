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

public class XMLJacksonHandler {
    private final static Logger LOG = LoggerFactory.getLogger(XMLJacksonHandler.class);
    private final static String PATH_TO_CONFIG = "src/main/resources/config.xml";
    private final static String PATH_TO_SCHEMA = "src/main/resources/config.xsd";

    private ObjectMapper mapper = new XmlMapper();

    public Config parse() throws IOException, MyException, ValidateException {
        Config config;
        validateXMLSchema(PATH_TO_SCHEMA, PATH_TO_CONFIG);
        LOG.info("The config file '{}' matches the schema '{}.", PATH_TO_CONFIG, PATH_TO_SCHEMA);
        InputStream input = new FileInputStream(PATH_TO_CONFIG);
        TypeReference<Config> typeReference = new TypeReference<Config>() {
        };
        config = mapper.readValue(input, typeReference);
        input.close();
        LOG.info("Config file reading: {}", config.toString());
        return config;
    }

    public void writeXML(Config config) throws MyException, IOException {
        if (config == null) {
            throw new MyException("Results file not created: config is null");
        }
        List<Path> originalFiles = FileUtil.getFilesExistList(config);

        Result result = new Result();
        result.setFileConfig(getFilenameResult());
        result.setTimestamp(getFileCreateDate());
        result.setOriginalFilenames(getFilenames(originalFiles));
        result.setNewFilenames(getFilenames(FileUtil.renameFiles(config, originalFiles)));

        mapper.writeValue(new File(config.getPathResult()), result);
        LOG.info("File '{}' created.", config.getPathResult());
    }

    private boolean validateXMLSchema(String xsdPath, String xmlPath) throws ValidateException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            try {
                validator.validate(new StreamSource(new File(xmlPath)));
            } catch (SAXException e) {
                LOG.error("XML-file '{}' does not match XSD-schema '{}'.", xmlPath, xsdPath, e);
                throw new ValidateException();
            } catch (IOException e) {
                LOG.error("XML-file '{}' does not read.", xmlPath, e);
                throw new ValidateException();
            }
        } catch (SAXException e) {
            LOG.error("XSD-schema not created. Path to file with XSD-schema: {}", xsdPath, e);
            throw new ValidateException();
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