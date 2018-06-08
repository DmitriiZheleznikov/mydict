package md.textanalysis.text.element.ctrl;

public class HTMLTag extends AbstractHTMLEntity {
    private String nameCache = null;

    @Override
    public void clear() {
        super.clear();
        nameCache = null;
    }

    public String getNameLowerCase() {
        if (nameCache == null) {
            for (String element : elements) {
                if (!"<".equals(element) && !"/".equals(element)) {
                    nameCache = element.toLowerCase();
                    break;
                }
            }
        }
        return nameCache;
    }

    public boolean isOpen() {
        return !"/".equals(elements.get(1));
    }

    public boolean isClose() {
        return "/".equals(elements.get(1)) || "/".equals(elements.get(elements.size() - 2));

    }

    @Override
    public boolean isComplete() {
        return !elements.isEmpty() && ">".equals(elements.get(elements.size() - 1));
    }
}
