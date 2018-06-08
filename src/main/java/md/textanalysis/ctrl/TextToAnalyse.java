package md.textanalysis.ctrl;

import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.element.word.AbstractWord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextToAnalyse {
    private File file;
    private List<AbstractWord> entities;

    public TextToAnalyse(File file) {
        this.file = file;
    }

    public void init() throws IOException {
        init(IProgressFunction.NULL);
    }

    public void init(IProgressFunction progressFunction) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException("File " + file.getName() + " doesn't exist");
        }

        List<String> rawLinesToAnalyse = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
        progressFunction.step();

        entities = TextAnalyserHelper.convertTextToWords(TextAnalyserHelper.getFileExt(file), rawLinesToAnalyse);
        progressFunction.step();

//        String textRaw = TextAnalyserHelper.convertToString(TextAnalyserHelper.getFileExt(file), rawLinesToAnalyse);
//        progressFunction.step();
//        System.out.println("convertToString end");
//
//        String[] rawEntities = TextAnalyserHelper.convertToList(textRaw);
//        progressFunction.step();
//        System.out.println("convertToList end");
//
//        createEntities(rawEntities);
//        progressFunction.step();
//        System.out.println("createEntities end");


    }

//    private void createEntities(String[] rawEntities) {
//        entities = new AbstractWord[rawEntities.length];
//        Phrase phrase = new Phrase(0);
//        for (int i = 0; i < entities.length; i++) {
//            AbstractWord entity = TextElementFactory.create(rawEntities[i], phrase);
//            entities[i] = entity;
//            if (entity.isPhraseBreak()) {
//                phrase = new Phrase(i+1/*, phrase*/);
//            }
//        }
//    }

    public File getFile() {
        return file;
    }

    public List<AbstractWord> getEntities() {
        return entities;
    }

    public int calcCountValidEntities() {
        int res = 0;
        for (AbstractWord entity : entities) {
            if (entity.isValid()) res++;
        }

        return res;
    }

}
