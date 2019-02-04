package md.textanalysis.text.matcher.phrase.table.action;

import md.textanalysis.text.analyser.AnalyserFacade;
import md.textanalysis.text.element.phrase.Idiom;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;
import md.textanalysis.text.matcher.phrase.table.PhraseMatchingTable;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.net.URISyntaxException;

public class AbstractPMTActionTest {
    protected PhraseMatchingTable matchingTable;
    protected Idiom idiom;
    protected Phrase phrase;

    @BeforeEach
    protected void setUp() throws IOException, URISyntaxException {
        AnalyserFacade.init();

        matchingTable = new PhraseMatchingTable();

        idiom = new Idiom();
        idiom.addEntity(new Word("This"));         //0
        idiom.addEntity(new Word("is"));           //1
        idiom.addEntity(new Word("the"));          //2
        idiom.addEntity(new Word("test"));         //3
        idiom.addEntity(new Separator(","));       //4
        idiom.addEntity(new Word("testik"));       //5
        idiom.addEntity(new md.textanalysis.text.element.word.Number("1")); //6
        idiom.init();

        phrase = new Phrase();
        phrase.addEntity(new Word("This"));        //0
        phrase.addEntity(new Word("are"));         //1
        phrase.addEntity(new Separator(","));      //2
        phrase.addEntity(new Word("test"));        //3
        phrase.addEntity(new Separator(","));      //4
        phrase.addEntity(new Word("this"));        //5
        phrase.addEntity(new Word("nice"));        //6
        phrase.addEntity(new Separator(","));      //7
        phrase.addEntity(new Word("testik"));      //8
        phrase.addEntity(new Separator("."));      //9
        phrase.addEntity(new Separator("unused")); //10
        phrase.addEntity(new Separator("word"));   //11
        phrase.init();

        matchingTable.init(idiom, phrase, 0, 0);

        matchingTable.getLine2().getElement(2).setSkip();
    }

    protected void setUpSkips() {
        matchingTable.getLine1().getElement(2).setSkip();
        matchingTable.getLine1().getElement(4).setSkip();
        matchingTable.getLine1().getElement(6).setSkip();

        matchingTable.getLine2().getElement(2).setSkip();
        matchingTable.getLine2().getElement(4).setSkip();
        matchingTable.getLine2().getElement(7).setSkip();
        matchingTable.getLine2().getElement(9).setSkip();
    }

    protected void setUpMatches() {
        matchingTable.getLine1().getElement(0).setMatch(0);
        matchingTable.getLine1().getElement(1).setMatch(1);
        matchingTable.getLine1().getElement(3).setMatch(3);
        matchingTable.getLine1().getElement(5).setMatch(8);

        matchingTable.getLine2().getElement(0).setMatch(0);
        matchingTable.getLine2().getElement(1).setMatch(1);
        matchingTable.getLine2().getElement(3).setMatch(3);
        matchingTable.getLine2().getElement(8).setMatch(5);
    }
}
