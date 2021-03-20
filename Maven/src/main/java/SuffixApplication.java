public class SuffixApplication {
    public static void main(String[] args) {
        Suffix suffix = new Suffix();
        suffix.setProperties();
        System.out.println("suffix = " + suffix.suffix +
                "\ndirectory = " + suffix.directory +
                "\nfileHelp = " + suffix.fileHelp +
                "\nfileSetting = " + suffix.fileSetting +
                "\nfileInfo = " + suffix.fileInfo);
        suffix.checkFileIsExist();
    }
}
