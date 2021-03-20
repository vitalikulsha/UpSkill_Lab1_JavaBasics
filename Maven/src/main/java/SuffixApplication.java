public class SuffixApplication {
    public static void main(String[] args) {
        Suffix suffix = new Suffix();
        suffix.setProperties();
        System.out.println("suffix = " + suffix.getSuffix() +
                "\ndirectory = " + suffix.getDirectory() +
                "\nfileHelp = " + suffix.getFileHelp() +
                "\nfileSetting = " + suffix.getFileSetting() +
                "\nfileInfo = " + suffix.getFileInfo());
        suffix.checkFileIsExist();
        suffix.renameFile();
    }
}
