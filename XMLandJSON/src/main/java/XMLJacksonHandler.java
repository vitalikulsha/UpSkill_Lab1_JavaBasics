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

    public Config parse() throws IOException, MyException {
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

        mapper.writeValue(new File(config.getPathFileResult()), result);
        LOG.info("File '{}' created.", config.getPathFileResult());
    }

    private boolean validateXMLSchema(String xsdPath, String xmlPath) throws MyException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            try {
                validator.validate(new StreamSource(new File(xmlPath)));
            } catch (SAXException e) {
                throw new MyException("XML-file '" + xmlPath + "' does not match XSD-schema '" + xsdPath + "'.", e);
            } catch (IOException e) {
                throw new MyException("XML-file '" + xmlPath + "' does not read.", e);
            }
        } catch (SAXException e) {
            throw new MyException("XSD-schema not created. Path to file with XSD-schema: " + xsdPath, e);
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