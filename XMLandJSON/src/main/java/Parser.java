import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final static Logger LOG = Logger.getLogger(Parser.class);
    private final static String PATH_TO_CONFIG = "src/main/resources/config.xml";
    private final static String TAG_SUFFIX = "suffix";
    private final static String TAG_DIRECTORY = "directory";
    private final static String TAG_FILE_LIST = "filesList";
    private final static String TAG_FILE = "file";

    public Config parse() {
        Config config = new Config();
        Document doc = buildDocument();
        if (doc == null) {
            LOG.error("Config object not created");
            return null;
        }
        Node configNode = doc.getFirstChild();
        NodeList configChildes = configNode.getChildNodes();
        Node filesListNode = null;
        for (int i = 0; i < configChildes.getLength(); i++) {
            if (configChildes.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (configChildes.item(i).getNodeName()) {
                case (TAG_SUFFIX): {
                    config.setSuffix(configChildes.item(i).getTextContent());
                    break;
                }
                case (TAG_DIRECTORY): {
                    config.setDirectory(Path.of(configChildes.item(i).getTextContent()));
                    break;
                }
                case (TAG_FILE_LIST): {
                    filesListNode = configChildes.item(i);
                    break;
                }
            }
        }

        if (filesListNode == null) {
            LOG.error("Config object not created");
            return null;
        }
        List<Path> fileList = parsFileList(filesListNode);
        config.setFilesList(fileList);
        LOG.info("Config file reading: " + config.toString());
        return config;
    }

    private List<Path> parsFileList(Node filesListNode) {
        List<Path> fileList = new ArrayList<>();
        NodeList filesListChildes = filesListNode.getChildNodes();
        for (int i = 0; i < filesListChildes.getLength(); i++) {
            if (filesListChildes.item(i).getNodeType() != Node.ELEMENT_NODE &&
                    !filesListChildes.item(i).getNodeName().equals(TAG_FILE)) {
                continue;
            }
            fileList.add(Path.of(filesListChildes.item(i).getTextContent()));
        }
        return fileList;
    }

    private Document buildDocument() {
        File file = new File(PATH_TO_CONFIG);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            return factory.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            LOG.error("Open parsing error: " + e);
            return null;
        }
    }


}
