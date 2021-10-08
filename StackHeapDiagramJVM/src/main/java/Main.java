import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;

public class Main {
    private final static Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.debug("Start method main()");
        String name = "Kevin";
        LOG.debug("name = " + name);
        List<String> list = new ArrayList<>();
        LOG.debug("list = " + list);
        int times = 10;
        LOG.debug("times = " + times);
        System.out.println(times + fill(list, name + name, times));
        LOG.debug("Start method println()");
    }

    public static int fill(Collection<String> collection, String str, int times){
        LOG.debug("Start method println()");
        String shrunk = shrink(str);
        LOG.debug("shrunk = " + shrunk);
        times = (times + shrunk.length()) / 2;
        LOG.debug("times = " + times);
        for (int i = 0; i < times / 2; i++) {
            LOG.debug("cycle for start - fill()");
            collection.add(shrunk);
            LOG.debug("collection.add(shrunk) size = " + collection.size());
        }
        LOG.debug("times = " + times);
        return times;
    }

    public static String shrink(String str){
        LOG.debug("Start method shrink()");
        int newLength = str.length() / 2 + str.length() % 2;
        LOG.debug("newLength = " + newLength);
        char[] chars = new char[newLength];
        LOG.debug("chars[] = " + chars);
        for (int i = 0; i < str.length(); i+=2) {
            LOG.debug("cycle for start - shrink()");
            chars[i / 2] = str.charAt(i);
            LOG.debug("chars = " + chars[i / 2]);
        }
        LOG.debug("new String" + new String(chars));
        return new String(chars);
    }
}
