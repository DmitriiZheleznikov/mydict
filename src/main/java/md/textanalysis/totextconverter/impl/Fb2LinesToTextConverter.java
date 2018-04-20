package md.textanalysis.totextconverter.impl;

import md.textanalysis.totextconverter.impl.fb2.Fb2ConverterConstants;
import md.textanalysis.totextconverter.impl.html.tag.SearchTagResult;
import md.textanalysis.totextconverter.impl.html.Tag;
import md.textanalysis.totextconverter.impl.html.TagHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts list of lines to 1 big String for FB2 file. Removes all tags, takes into account word breaks and so on
 */
public class Fb2LinesToTextConverter extends TxtLinesToTextConverter {
    private String tagForEraseOpened = "";
    private int tagForEraseOpenedCount = 0;
    private int tagForEraseOpenedStartPos = 0;

    public Fb2LinesToTextConverter(List<String> rawLinesToAnalyse) {
        super(rawLinesToAnalyse);
    }

    //Two steps here
    //1. Pre-process:  will make tags to be on same line, ie if tag started on one line and ended on second(third etc)
    //                 then line to be combined. Also trim redundant spaces inside tag
    //2. Process Line: will identify tags to be erased with content and delete them
    //                 second step is to delete all other tag without removing content

    protected List<String> preProcessList(List<String> rawList) {
        if (rawList == null || rawList.isEmpty()) return rawList;

        List<String> resultList = new ArrayList<>(rawList.size());
        String lineResult = "";

        for (String line : rawList) {
            lineResult = TagHelper.removeSpacesInsideTag(lineResult + line);
            if (TagHelper.isAllTagsClosed(lineResult)) {
                resultList.add(lineResult);
                lineResult = "";
            }
        }

        return resultList;
    }

    @Override
    protected String processLine(String line) {
        line = TagHelper.deleteTags(line, Fb2ConverterConstants.TAGS_TO_BE_ERASED_WITH_CONTENT);

        tagForEraseOpenedStartPos = 0;
        line = eraseTagsWithContentRecursive(line, 0);

        return super.processLine(line);
    }

    //complexity is in tag inside tag, for example:
    //line 1: <genre />text-2 <genre>text-1</genre>text0 <genre>text1<genre>te
    //line 2: xt2</genre>text 3 <genre>text 4</genre>
    //line 3: text 5</genre> text6
    //line 4: 111<genre>text 7<genre>text 7.1</genre></genre>222</genre>
    //result to be
    //line 1: text-2 text0
    //line 2:  text6
    //line 3: 111222
    private String eraseTagsWithContentRecursive(String line, int startIndex) {
        SearchTagResult tagResult = TagHelper.searchTag(line, startIndex, tagForEraseOpened);
        if (!tagResult.isFound()) {
            if (tagForEraseOpenedCount > 0) {
                return line.substring(0, tagForEraseOpenedStartPos);
            }
            return line;
        }

        Tag tag = tagResult.getTag();
        if (tag.isOpen() && tag.isClose()) {
            //<genre /> - just remove it and go further
            return eraseTagsWithContentRecursive(line.substring(0, tagResult.getTagStartPos()) + line.substring(tagResult.getTagEndPos()), tagResult.getTagStartPos());
        } else if (tag.isOpen()) {
            //<genre>text1<genre>te - remember tag and increase counter
            tagForEraseOpenedCount++;
            if (tagForEraseOpenedCount == 1) {
                tagForEraseOpened = tag.getNameLowerCase();
                tagForEraseOpenedStartPos = tagResult.getTagStartPos();
            }
        } else if (tag.isClose()) {
            //</genre></genre>222</genre> - decrease counter until 0 and remove it with content
            //in case counter became negative, that means there is close tag which doesn't have open pair, just remove it
            tagForEraseOpenedCount--;
            if (tagForEraseOpenedCount == 0) {
                tagForEraseOpened = "";
                return eraseTagsWithContentRecursive(line.substring(0, tagForEraseOpenedStartPos) + line.substring(tagResult.getTagEndPos()), tagForEraseOpenedStartPos);
            } else if (tagForEraseOpenedCount < 0) {
                tagForEraseOpenedCount = 0;
                return eraseTagsWithContentRecursive(line.substring(0, tagResult.getTagStartPos()) + line.substring(tagResult.getTagEndPos()), tagResult.getTagStartPos());
            }
        }
        //Go further
        return eraseTagsWithContentRecursive(line, tagResult.getTagEndPos());
    }

}
