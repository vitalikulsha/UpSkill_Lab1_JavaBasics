/*
Description
Get Suffixing App project from Maven topic. Add logging.

App Specification
It is a Suffixing App - a small java application that refers to a config file and renames a set of files and
renames them adding a suffix specified in the same config.

Details:
- Application should read a config file on the startup
- Then it should ensure that all of files from the config exist
- Then it should rename each file adding a suffix from the config to its name

Logging Specification
- Application should log startup information.
- Application should log information on config read.
- Application should log renaming process information.
- Application should log summary information.
- Application should log shutdown information.
- Application should handle and log possible errors.
Use different logging level. All log entries should contain a date and time information as well.

Steps
1. Complete the project to meet specifications.
2. Show the mentor your results.
 */

import org.apache.log4j.Logger;

public class SuffixApplication {
    final static Logger log = Logger.getLogger(FileUtil.class);

    public static void main(String[] args) {
        log.debug("The application has started.");
        Config config = FileUtil.initProps();
        FileUtil.renameFiles(config);
        log.debug("The application has finished.");
    }
}
