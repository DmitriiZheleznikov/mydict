package heli.component.shape.text.htext;

import java.util.ArrayList;
import java.util.List;

public class HStringFlow {
    List<HString> data = new ArrayList<>();

    public void add(HString str) {
        data.add(str);
    }

    public List<HString> getData() {
        return data;
    }

    public int getStringLength() {
        if (getData().isEmpty()) return 0;

        int l = 0;
        for (HString str : getData()) {
            l += str.getString().length();
        }

        return l;
    }

    public void cutLeft(int howMany) {
        if (getData().isEmpty()) return;

        int l = howMany;
        for (HString str : getData()) {
            l -= str.getString().length();
            if (l >= 0) {
                str.setString("");
            } else {
                str.setString(str.getString().substring(str.getString().length()+l));
                break;
            }
        }
    }

    public void cutRight(int howMany) {
        if (getData().isEmpty()) return;

        int l = howMany;
        for (int i = getData().size()-1; i >= 0; i--) {
            HString str = getData().get(i);
            l -= str.getString().length();
            if (l >= 0) {
                str.setString("");
            } else {
                str.setString(str.getString().substring(0, -l));
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HString s : data) sb.append(s);
        return sb.toString();
    }
}
