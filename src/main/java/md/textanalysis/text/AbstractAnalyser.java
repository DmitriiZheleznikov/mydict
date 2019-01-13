package md.textanalysis.text;

import heli.helper.ResourceHelper;
import md.textanalysis.callback.IProgressFunction;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

abstract public class AbstractAnalyser {
    private static final String KEY_NAME_COMMENT_LINE = "#";

    public void init() throws IOException, URISyntaxException {
        init(IProgressFunction.NULL);
    }

    public void init(IProgressFunction progressFunction) throws IOException, URISyntaxException {
        if (iniFileName() == null) return;

        List<String> lines = ResourceHelper.readTextFile(AbstractAnalyser.class, iniFileName());
        for (String line : lines) {
            if (line == null) continue;

            String lineTrimmed = line.trim();
            if (lineTrimmed.length() == 0) continue;

            if (lineTrimmed.startsWith(KEY_NAME_COMMENT_LINE)) continue;

            initLine(lineTrimmed);
        }
        progressFunction.step();
    }

    abstract protected String iniFileName();
    abstract protected void initLine(String line);

    protected void initListByKey(List<String> list, String key, String line) {
        if (line.startsWith(key)) {
            list.add(line.substring(key.length()).trim());
        }
    }

    protected void initListByKey(Set<String> list, String key, String line) {
        if (line.startsWith(key)) {
            list.add(line.substring(key.length()).trim());
        }
    }

    protected void initMapByKey(Map<String, String> map, String key, String line) {
        if (line.startsWith(key)) {
            String[] values = line.substring(key.length()).trim().split(" ");
            map.put(values[0], values[1]);
        }
    }
}
