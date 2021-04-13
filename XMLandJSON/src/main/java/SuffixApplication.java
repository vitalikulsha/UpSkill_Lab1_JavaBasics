import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
Description
Get Suffixing App project from Logging topic. Specify structure of its input and output.
App Specification
It is a Suffixing App - a small java application that refers to a config file and
renames a set of files and renames them adding a suffix specified in the same config.
Changes: config file now should be an XML file.
Details:
Application should read a config file on the startup
Then it should ensure that all of files from the config exist
Then it should rename each file adding a suffix from the config to its name
Logging Specification
Logging spec of previous exercise:
Application should log startup information.
Application should log information on config read.
Application should log renaming process information.
Application should log summary information.
Application should log shutdown information.
Application should handle and log possible errors.
Use different logging level. All log entries should contain a date and time information as well.
Changes:
When renaming is finished the application should print a document of completed actions.
Document should be XML-based. It should contain:
config file name
execution time
list of files with old and new names
All the logging entries from previous exercise should become JSON document of some structure.
They should contain:
- date and time
- message
- severity label
- error info, if its error
Steps
Specify structure of the following documents:
- Config file
- Completed actions document
- Log entries
Show the mentor your results.
 */
public class SuffixApplication {
    private final static Logger LOG = LoggerFactory.getLogger(SuffixApplication.class);

    public static void main(String[] args) {
        LOG.info("The application has started.");
        XMLJacksonHandler XMLJacksonHandler = new XMLJacksonHandler();
        Config config;
        try {
            config = XMLJacksonHandler.parse();
            XMLJacksonHandler.writeXML(config);
        } catch (MyException e) {
            LOG.error(e.getMessage(), e);
        } catch (FileNotFoundException e) {
            LOG.error("File not found. Config object not created.", e);
        } catch (IOException e) {
            LOG.error("Config file no read.", e);
        } catch (SAXException e) {
            LOG.error("Config file is invalid.", e);
        }
        LOG.info("The application has finished.");
    }
}