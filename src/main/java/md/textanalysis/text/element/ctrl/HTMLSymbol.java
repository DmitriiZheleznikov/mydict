package md.textanalysis.text.element.ctrl;

import java.util.HashMap;
import java.util.Map;

public class HTMLSymbol extends AbstractHTMLEntity {
    public static final Map<String, String> MAP_NAME_SYMBOL = new HashMap<>(101);
    public static final Map<String, String> MAP_CODE_SYMBOL = new HashMap<>(213);

    @Override
    public boolean isComplete() {
        return !elements.isEmpty() && ";".equals(elements.get(elements.size() - 1));
    }

    public boolean isValid() {
        return isValidByCode() || isValidByName();
    }

    public String get() {
        String res = getByCode();
        if (res == null) res = getByName();

        return res;
    }

    private String getByCode() {
        return elements.size() >= 3 ? MAP_CODE_SYMBOL.get(elements.get(2)) : null;
    }

    private String getByName() {
        return elements.size() >= 2 ? MAP_NAME_SYMBOL.get(elements.get(1)) : null;
    }

    private boolean isValidByCode() {
        if (elements.isEmpty()) return false;

        if (!"&".equals(elements.get(0))) return false;
        if (elements.size() == 1) return true;

        if (!"#".equals(elements.get(1))) return false;
        if (elements.size() == 2) return true;

        if (!MAP_CODE_SYMBOL.containsKey(elements.get(2))) return false;
        if (elements.size() == 3) return true;

        if (!";".equals(elements.get(3))) return false;
        return true;
    }

    private boolean isValidByName() {
        if (elements.isEmpty()) return false;

        if (!"&".equals(elements.get(0))) return false;
        if (elements.size() == 1) return true;

        if (!MAP_NAME_SYMBOL.containsKey(elements.get(1))) return false;
        if (elements.size() == 2) return true;

        if (!";".equals(elements.get(2))) return false;
        return true;
    }

    static {
        MAP_NAME_SYMBOL.put("quot", "\"");
        MAP_NAME_SYMBOL.put("amp", "&");
        MAP_NAME_SYMBOL.put("lt", "<");
        MAP_NAME_SYMBOL.put("gt", ">");
        MAP_NAME_SYMBOL.put("nbsp", " ");
        MAP_NAME_SYMBOL.put("iexcl", "¡");
        MAP_NAME_SYMBOL.put("cent", "¢");
        MAP_NAME_SYMBOL.put("pound", "£");
        MAP_NAME_SYMBOL.put("curren", "¤");
        MAP_NAME_SYMBOL.put("yen", "¥");
        MAP_NAME_SYMBOL.put("brvbar", "¦");
        MAP_NAME_SYMBOL.put("sect", "§");
        MAP_NAME_SYMBOL.put("uml", "¨");
        MAP_NAME_SYMBOL.put("copy", "©");
        MAP_NAME_SYMBOL.put("ordf", "ª");
        MAP_NAME_SYMBOL.put("laquo", "«");
        MAP_NAME_SYMBOL.put("not", "¬");
        MAP_NAME_SYMBOL.put("shy", " ");
        MAP_NAME_SYMBOL.put("reg", "®");
        MAP_NAME_SYMBOL.put("macr", "¯");
        MAP_NAME_SYMBOL.put("deg", "°");
        MAP_NAME_SYMBOL.put("plusmn", "±");
        MAP_NAME_SYMBOL.put("sup2", "²");
        MAP_NAME_SYMBOL.put("sup3", "³");
        MAP_NAME_SYMBOL.put("acute", "´");
        MAP_NAME_SYMBOL.put("micro", "µ");
        MAP_NAME_SYMBOL.put("para", "¶");
        MAP_NAME_SYMBOL.put("middot", "·");
        MAP_NAME_SYMBOL.put("cedil", "¸");
        MAP_NAME_SYMBOL.put("sup1", "¹");
        MAP_NAME_SYMBOL.put("ordm", "º");
        MAP_NAME_SYMBOL.put("raquo", "»");
        MAP_NAME_SYMBOL.put("frac14", "¼");
        MAP_NAME_SYMBOL.put("frac12", "½");
        MAP_NAME_SYMBOL.put("frac34", "¾");
        MAP_NAME_SYMBOL.put("iquest", "¿");
        MAP_NAME_SYMBOL.put("Agrave", "À");
        MAP_NAME_SYMBOL.put("Aacute", "Á");
        MAP_NAME_SYMBOL.put("Acirc", "Â");
        MAP_NAME_SYMBOL.put("Atilde", "Ã");
        MAP_NAME_SYMBOL.put("Auml", "Ä");
        MAP_NAME_SYMBOL.put("Aring", "Å");
        MAP_NAME_SYMBOL.put("AElig", "Æ");
        MAP_NAME_SYMBOL.put("Ccedil", "Ç");
        MAP_NAME_SYMBOL.put("Egrave", "È");
        MAP_NAME_SYMBOL.put("Eacute", "É");
        MAP_NAME_SYMBOL.put("Ecirc", "Ê");
        MAP_NAME_SYMBOL.put("Euml", "Ë");
        MAP_NAME_SYMBOL.put("Igrave", "Ì");
        MAP_NAME_SYMBOL.put("Iacute", "Í");
        MAP_NAME_SYMBOL.put("Icirc", "Î");
        MAP_NAME_SYMBOL.put("Iuml", "Ï");
        MAP_NAME_SYMBOL.put("ETH", "Ð");
        MAP_NAME_SYMBOL.put("Ntilde", "Ñ");
        MAP_NAME_SYMBOL.put("Ograve", "Ò");
        MAP_NAME_SYMBOL.put("Oacute", "Ó");
        MAP_NAME_SYMBOL.put("Ocirc", "Ô");
        MAP_NAME_SYMBOL.put("Otilde", "Õ");
        MAP_NAME_SYMBOL.put("Ouml", "Ö");
        MAP_NAME_SYMBOL.put("times", "×");
        MAP_NAME_SYMBOL.put("Oslash", "Ø");
        MAP_NAME_SYMBOL.put("Ugrave", "Ù");
        MAP_NAME_SYMBOL.put("Uacute", "Ú");
        MAP_NAME_SYMBOL.put("Ucirc", "Û");
        MAP_NAME_SYMBOL.put("Uuml", "Ü");
        MAP_NAME_SYMBOL.put("Yacute", "Ý");
        MAP_NAME_SYMBOL.put("THORN", "Þ");
        MAP_NAME_SYMBOL.put("szlig", "ß");
        MAP_NAME_SYMBOL.put("agrave", "à");
        MAP_NAME_SYMBOL.put("aacute", "á");
        MAP_NAME_SYMBOL.put("acirc", "â");
        MAP_NAME_SYMBOL.put("atilde", "ã");
        MAP_NAME_SYMBOL.put("auml", "ä");
        MAP_NAME_SYMBOL.put("aring", "å");
        MAP_NAME_SYMBOL.put("aelig", "æ");
        MAP_NAME_SYMBOL.put("ccedil", "ç");
        MAP_NAME_SYMBOL.put("egrave", "è");
        MAP_NAME_SYMBOL.put("eacute", "é");
        MAP_NAME_SYMBOL.put("ecirc", "ê");
        MAP_NAME_SYMBOL.put("euml", "ë");
        MAP_NAME_SYMBOL.put("igrave", "ì");
        MAP_NAME_SYMBOL.put("iacute", "í");
        MAP_NAME_SYMBOL.put("icirc", "î");
        MAP_NAME_SYMBOL.put("iuml", "ï");
        MAP_NAME_SYMBOL.put("eth", "ð");
        MAP_NAME_SYMBOL.put("ntilde", "ñ");
        MAP_NAME_SYMBOL.put("ograve", "ò");
        MAP_NAME_SYMBOL.put("oacute", "ó");
        MAP_NAME_SYMBOL.put("ocirc", "ô");
        MAP_NAME_SYMBOL.put("otilde", "õ");
        MAP_NAME_SYMBOL.put("ouml", "ö");
        MAP_NAME_SYMBOL.put("divide", "÷");
        MAP_NAME_SYMBOL.put("oslash", "ø");
        MAP_NAME_SYMBOL.put("ugrave", "ù");
        MAP_NAME_SYMBOL.put("uacute", "ú");
        MAP_NAME_SYMBOL.put("ucirc", "û");
        MAP_NAME_SYMBOL.put("uuml", "ü");
        MAP_NAME_SYMBOL.put("yacute", "ý");
        MAP_NAME_SYMBOL.put("thorn", "þ");
        MAP_NAME_SYMBOL.put("yuml", "ÿ");
        MAP_NAME_SYMBOL.put("euro", "€");

        MAP_CODE_SYMBOL.put("32", " ");
        MAP_CODE_SYMBOL.put("33", "!");
        MAP_CODE_SYMBOL.put("34", "\"");
        MAP_CODE_SYMBOL.put("35", "#");
        MAP_CODE_SYMBOL.put("36", "$");
        MAP_CODE_SYMBOL.put("37", "%");
        MAP_CODE_SYMBOL.put("38", "&");
        MAP_CODE_SYMBOL.put("39", "'");
        MAP_CODE_SYMBOL.put("40", "(");
        MAP_CODE_SYMBOL.put("41", ")");
        MAP_CODE_SYMBOL.put("42", "*");
        MAP_CODE_SYMBOL.put("43", "+");
        MAP_CODE_SYMBOL.put("44", ",");
        MAP_CODE_SYMBOL.put("45", "-");
        MAP_CODE_SYMBOL.put("46", ".");
        MAP_CODE_SYMBOL.put("47", "/");
        MAP_CODE_SYMBOL.put("48", "0");
        MAP_CODE_SYMBOL.put("49", "1");
        MAP_CODE_SYMBOL.put("50", "2");
        MAP_CODE_SYMBOL.put("51", "3");
        MAP_CODE_SYMBOL.put("52", "4");
        MAP_CODE_SYMBOL.put("53", "5");
        MAP_CODE_SYMBOL.put("54", "6");
        MAP_CODE_SYMBOL.put("55", "7");
        MAP_CODE_SYMBOL.put("56", "8");
        MAP_CODE_SYMBOL.put("57", "9");
        MAP_CODE_SYMBOL.put("58", ":");
        MAP_CODE_SYMBOL.put("59", ";");
        MAP_CODE_SYMBOL.put("60", "<");
        MAP_CODE_SYMBOL.put("61", "=");
        MAP_CODE_SYMBOL.put("62", ">");
        MAP_CODE_SYMBOL.put("63", "?");
        MAP_CODE_SYMBOL.put("64", "@");
        MAP_CODE_SYMBOL.put("65", "A");
        MAP_CODE_SYMBOL.put("66", "B");
        MAP_CODE_SYMBOL.put("67", "C");
        MAP_CODE_SYMBOL.put("68", "D");
        MAP_CODE_SYMBOL.put("69", "E");
        MAP_CODE_SYMBOL.put("70", "F");
        MAP_CODE_SYMBOL.put("71", "G");
        MAP_CODE_SYMBOL.put("72", "H");
        MAP_CODE_SYMBOL.put("73", "I");
        MAP_CODE_SYMBOL.put("74", "J");
        MAP_CODE_SYMBOL.put("75", "K");
        MAP_CODE_SYMBOL.put("76", "L");
        MAP_CODE_SYMBOL.put("77", "M");
        MAP_CODE_SYMBOL.put("78", "N");
        MAP_CODE_SYMBOL.put("79", "O");
        MAP_CODE_SYMBOL.put("80", "P");
        MAP_CODE_SYMBOL.put("81", "Q");
        MAP_CODE_SYMBOL.put("82", "R");
        MAP_CODE_SYMBOL.put("83", "S");
        MAP_CODE_SYMBOL.put("84", "T");
        MAP_CODE_SYMBOL.put("85", "U");
        MAP_CODE_SYMBOL.put("86", "V");
        MAP_CODE_SYMBOL.put("87", "W");
        MAP_CODE_SYMBOL.put("88", "X");
        MAP_CODE_SYMBOL.put("89", "Y");
        MAP_CODE_SYMBOL.put("90", "Z");
        MAP_CODE_SYMBOL.put("91", "[");
        MAP_CODE_SYMBOL.put("92", "\\");
        MAP_CODE_SYMBOL.put("93", "]");
        MAP_CODE_SYMBOL.put("94", "^");
        MAP_CODE_SYMBOL.put("95", "_");
        MAP_CODE_SYMBOL.put("96", "`");
        MAP_CODE_SYMBOL.put("97", "a");
        MAP_CODE_SYMBOL.put("98", "b");
        MAP_CODE_SYMBOL.put("99", "c");
        MAP_CODE_SYMBOL.put("100", "d");
        MAP_CODE_SYMBOL.put("101", "e");
        MAP_CODE_SYMBOL.put("102", "f");
        MAP_CODE_SYMBOL.put("103", "g");
        MAP_CODE_SYMBOL.put("104", "h");
        MAP_CODE_SYMBOL.put("105", "i");
        MAP_CODE_SYMBOL.put("106", "j");
        MAP_CODE_SYMBOL.put("107", "k");
        MAP_CODE_SYMBOL.put("108", "l");
        MAP_CODE_SYMBOL.put("109", "m");
        MAP_CODE_SYMBOL.put("110", "n");
        MAP_CODE_SYMBOL.put("111", "o");
        MAP_CODE_SYMBOL.put("112", "p");
        MAP_CODE_SYMBOL.put("113", "q");
        MAP_CODE_SYMBOL.put("114", "r");
        MAP_CODE_SYMBOL.put("115", "s");
        MAP_CODE_SYMBOL.put("116", "t");
        MAP_CODE_SYMBOL.put("117", "u");
        MAP_CODE_SYMBOL.put("118", "v");
        MAP_CODE_SYMBOL.put("119", "w");
        MAP_CODE_SYMBOL.put("120", "x");
        MAP_CODE_SYMBOL.put("121", "y");
        MAP_CODE_SYMBOL.put("122", "z");
        MAP_CODE_SYMBOL.put("123", "{");
        MAP_CODE_SYMBOL.put("124", "|");
        MAP_CODE_SYMBOL.put("125", "}");
        MAP_CODE_SYMBOL.put("126", "~");
        MAP_CODE_SYMBOL.put("160", " ");
        MAP_CODE_SYMBOL.put("161", "¡");
        MAP_CODE_SYMBOL.put("162", "¢");
        MAP_CODE_SYMBOL.put("163", "£");
        MAP_CODE_SYMBOL.put("164", "¤");
        MAP_CODE_SYMBOL.put("165", "¥");
        MAP_CODE_SYMBOL.put("166", "¦");
        MAP_CODE_SYMBOL.put("167", "§");
        MAP_CODE_SYMBOL.put("168", "¨");
        MAP_CODE_SYMBOL.put("169", "©");
        MAP_CODE_SYMBOL.put("170", "ª");
        MAP_CODE_SYMBOL.put("171", "«");
        MAP_CODE_SYMBOL.put("172", "¬");
        MAP_CODE_SYMBOL.put("173", " ");
        MAP_CODE_SYMBOL.put("174", "®");
        MAP_CODE_SYMBOL.put("175", "¯");
        MAP_CODE_SYMBOL.put("176", "°");
        MAP_CODE_SYMBOL.put("177", "±");
        MAP_CODE_SYMBOL.put("178", "²");
        MAP_CODE_SYMBOL.put("179", "³");
        MAP_CODE_SYMBOL.put("180", "´");
        MAP_CODE_SYMBOL.put("181", "µ");
        MAP_CODE_SYMBOL.put("182", "¶");
        MAP_CODE_SYMBOL.put("183", "·");
        MAP_CODE_SYMBOL.put("184", "¸");
        MAP_CODE_SYMBOL.put("185", "¹");
        MAP_CODE_SYMBOL.put("186", "º");
        MAP_CODE_SYMBOL.put("187", "»");
        MAP_CODE_SYMBOL.put("188", "¼");
        MAP_CODE_SYMBOL.put("189", "½");
        MAP_CODE_SYMBOL.put("190", "¾");
        MAP_CODE_SYMBOL.put("191", "¿");
        MAP_CODE_SYMBOL.put("192", "À");
        MAP_CODE_SYMBOL.put("193", "Á");
        MAP_CODE_SYMBOL.put("194", "Â");
        MAP_CODE_SYMBOL.put("195", "Ã");
        MAP_CODE_SYMBOL.put("196", "Ä");
        MAP_CODE_SYMBOL.put("197", "Å");
        MAP_CODE_SYMBOL.put("198", "Æ");
        MAP_CODE_SYMBOL.put("199", "Ç");
        MAP_CODE_SYMBOL.put("200", "È");
        MAP_CODE_SYMBOL.put("201", "É");
        MAP_CODE_SYMBOL.put("202", "Ê");
        MAP_CODE_SYMBOL.put("203", "Ë");
        MAP_CODE_SYMBOL.put("204", "Ì");
        MAP_CODE_SYMBOL.put("205", "Í");
        MAP_CODE_SYMBOL.put("206", "Î");
        MAP_CODE_SYMBOL.put("207", "Ï");
        MAP_CODE_SYMBOL.put("208", "Ð");
        MAP_CODE_SYMBOL.put("209", "Ñ");
        MAP_CODE_SYMBOL.put("210", "Ò");
        MAP_CODE_SYMBOL.put("211", "Ó");
        MAP_CODE_SYMBOL.put("212", "Ô");
        MAP_CODE_SYMBOL.put("213", "Õ");
        MAP_CODE_SYMBOL.put("214", "Ö");
        MAP_CODE_SYMBOL.put("215", "×");
        MAP_CODE_SYMBOL.put("216", "Ø");
        MAP_CODE_SYMBOL.put("217", "Ù");
        MAP_CODE_SYMBOL.put("218", "Ú");
        MAP_CODE_SYMBOL.put("219", "Û");
        MAP_CODE_SYMBOL.put("220", "Ü");
        MAP_CODE_SYMBOL.put("221", "Ý");
        MAP_CODE_SYMBOL.put("222", "Þ");
        MAP_CODE_SYMBOL.put("223", "ß");
        MAP_CODE_SYMBOL.put("224", "à");
        MAP_CODE_SYMBOL.put("225", "á");
        MAP_CODE_SYMBOL.put("226", "â");
        MAP_CODE_SYMBOL.put("227", "ã");
        MAP_CODE_SYMBOL.put("228", "ä");
        MAP_CODE_SYMBOL.put("229", "å");
        MAP_CODE_SYMBOL.put("230", "æ");
        MAP_CODE_SYMBOL.put("231", "ç");
        MAP_CODE_SYMBOL.put("232", "è");
        MAP_CODE_SYMBOL.put("233", "é");
        MAP_CODE_SYMBOL.put("234", "ê");
        MAP_CODE_SYMBOL.put("235", "ë");
        MAP_CODE_SYMBOL.put("236", "ì");
        MAP_CODE_SYMBOL.put("237", "í");
        MAP_CODE_SYMBOL.put("238", "î");
        MAP_CODE_SYMBOL.put("239", "ï");
        MAP_CODE_SYMBOL.put("240", "ð");
        MAP_CODE_SYMBOL.put("241", "ñ");
        MAP_CODE_SYMBOL.put("242", "ò");
        MAP_CODE_SYMBOL.put("243", "ó");
        MAP_CODE_SYMBOL.put("244", "ô");
        MAP_CODE_SYMBOL.put("245", "õ");
        MAP_CODE_SYMBOL.put("246", "ö");
        MAP_CODE_SYMBOL.put("247", "÷");
        MAP_CODE_SYMBOL.put("248", "ø");
        MAP_CODE_SYMBOL.put("249", "ù");
        MAP_CODE_SYMBOL.put("250", "ú");
        MAP_CODE_SYMBOL.put("251", "û");
        MAP_CODE_SYMBOL.put("252", "ü");
        MAP_CODE_SYMBOL.put("253", "ý");
        MAP_CODE_SYMBOL.put("254", "þ");
        MAP_CODE_SYMBOL.put("255", "ÿ");
        MAP_CODE_SYMBOL.put("338", "Œ");
        MAP_CODE_SYMBOL.put("339", "œ");
        MAP_CODE_SYMBOL.put("352", "Š");
        MAP_CODE_SYMBOL.put("353", "š");
        MAP_CODE_SYMBOL.put("376", "Ÿ");
        MAP_CODE_SYMBOL.put("402", "ƒ");
        MAP_CODE_SYMBOL.put("8211", "–");
        MAP_CODE_SYMBOL.put("8212", "—");
        MAP_CODE_SYMBOL.put("8216", "‘");
        MAP_CODE_SYMBOL.put("8217", "’");
        MAP_CODE_SYMBOL.put("8218", "‚");
        MAP_CODE_SYMBOL.put("8220", "“");
        MAP_CODE_SYMBOL.put("8221", "”");
        MAP_CODE_SYMBOL.put("8222", "„");
        MAP_CODE_SYMBOL.put("8224", "†");
        MAP_CODE_SYMBOL.put("8225", "‡");
        MAP_CODE_SYMBOL.put("8226", "•");
        MAP_CODE_SYMBOL.put("8230", "…");
        MAP_CODE_SYMBOL.put("8240", "‰");
        MAP_CODE_SYMBOL.put("8364", "€");
        MAP_CODE_SYMBOL.put("8482", "™");
    }
}
