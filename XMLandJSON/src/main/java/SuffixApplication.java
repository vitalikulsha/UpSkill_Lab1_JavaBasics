/*

 */
public class SuffixApplication {
    public static void main(String[] args) {
        Config config = FileUtil.initProps();
        FileUtil.renameFiles(config);
    }
}
