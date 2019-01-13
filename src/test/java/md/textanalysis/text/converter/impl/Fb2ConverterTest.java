package md.textanalysis.text.converter.impl;

import md.textanalysis.text.analyser.text.TextOrderNumberSetter;
import md.textanalysis.text.analyser.text.TextPhrasesCreator;
import md.textanalysis.text.converter.AbstractTextConverter;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;

import java.util.ArrayList;
import java.util.List;

class Fb2ConverterTest extends AbstractConverterTest {
    @Override
    AbstractTextConverter getConverter(List<String> original) {
        return new Fb2Converter(original);
    }

    @Override
    List<String> getOriginal() {
        List<String> original = new ArrayList<>();
        original.add("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        original.add("<FictionBook xmlns=\"http://www.gribuser.ru/xml/fictionbook/2.0\" xmlns:l=\"http://www.w3.org/1999/xlink\"><description><title-info><genre>sf_fantasy</genre></title-info><document-info><author><nickname>ravenger</nickname></author><program-used>FictionBook Editor Release 2.5</program-used><date value=\"2010-10-17\">17.10.2010</date><id>A44CA341-EA1A-4B93-97A5-62077E48DE4E</id><version>1.01</version></document-info><publish-info><book-name>The Lord of the Rings</book-name><publisher>HarperCollins</publisher><year>2009</year><isbn>978-0-007-32259-6</isbn></publish-info><custom-info info-type=\"edition\">50th Anniversary Edition</custom-info></description><body><myheader><p>Спасибо, что скачали книгу в <a l:href=\"http://royallib.ru\">бесплатной электронной библиотеке Royallib.ru</a></p><p><a l:href=\"http://royallib.ru/author/Tolkien_J.html\">Все книги автора</a></p><p><a l:href=\"http://royallib.ru/book/Tolkien_J/The_Lord_of_the_Rings.html\">Эта же книга в других форматах</a></p><empty-line/><p>Приятного чтения!</p><empty-line/><empty-line/><empty-line/></myheader><title><p><strong>The Lord of the Rings</strong></p></title><epigraph><p><emphasis>Three &#38;Rings &tttttt; for the Elven-kings under the sky,</emphasis></p><p><emphasis>Seven for the Dwarf-lords in their halls of stone,&amp;</body><binary id=\"cover.jpg\" content-type=\"image/jpeg\">/9j/4AAQSkZJRgABAQEBLAEsAAD/2wCEAAYEBAQFBAYFBQYJBgUGCQsIBgYICwwKCgsKCgwQ");
        original.add("DAwMDAwMEAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwBBwcHDQwNGBAQGBQODg4UFA4O");
        original.add("Dg4UEQwMDAwMEREMDAwMDAwRDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDP/CABEIAg0B");
        original.add("XgMBEQACEQEDEQH/xAEHAAABBQEBAAAAAAAAAAAAAAADAAECBAUGBwEBAQADAQEAAAAAAAAA");
        original.add("AAAAAAECAwQFBhAAAgICAQIDBgYCAgIDAAAAAQIAAxEEEiETEDEFIEBBIjIUMEIjMzQVRDUk");
        original.add("</binary><binary id=\"i_001.png\" content-type=\"image/png\">iVBORw0KGgoAAAANSUhEUgAAAw0AAAU7CAMAAABczLlYAAAABGdBTUEAALGOfPtRkwAAACBj");
        original.add("SFJNAACHDwAAjA8AAP1SAACBQAAAfXkAAOmLAAA85QAAGcxzPIV3AAAKL2lDQ1BJQ0MgUHJv");
        original.add("ZmlsZQAASMedlndUVNcWh8+9d3qhzTACUobeu8AA0nuTXkVhmBlgKAMOMzSxIaICEUVEmiJI");
        original.add("UMSA0VAkVkSxEBRUsAckCCgxGEVULG9G1ouurLz38vL746xv7bP3ufvsvc9aFwCSpy+XlwZL");
        original.add("AZDKE/CDPJzpEZFRdOwAgAEeYIApAExWRrpfsHsIEMnLzYWeIXICXwQB8HpYvAJw09AzgE4H");
        original.add("/5+kWel8geiYABGbszkZLBEXiDglS5Auts+KmBqXLGYYJWa+KEERy4k5YZENPvsssqOY2ak8");
        original.add("tojFOaezU9li7hXxtkwhR8SIr4gLM7mcLBHfErFGijCVK+I34thUDjMDABRJbBdwWIkiNhEx");
        original.add("</binary>");

        return original;
    }
    //The Lord of the Rings Three Rings for the Elven-kings under the sky, Seven for the Dwarf-lords in their halls of stone,
    @Override
    List<AbstractWord> getExpected() {
        List<AbstractWord> expected = new ArrayList<>();

        expected.add(new Word("The"));
        expected.add(new Word("Lord"));
        expected.add(new Word("of"));
        expected.add(new Word("the"));
        expected.add(new Word("Rings"));
        expected.add(new Word("Three"));
        expected.add(new Separator("&"));
        expected.add(new Word("Rings"));
        expected.add(new Separator("&tttttt"));
        expected.add(new Separator(";"));

        expected.add(new Word("for"));
        expected.add(new Word("the"));
        expected.add(new Word("Elven-kings"));
        expected.add(new Word("under"));
        expected.add(new Word("the"));
        expected.add(new Word("sky"));
        expected.add(new Separator(","));

        expected.add(new Word("Seven"));
        expected.add(new Word("for"));
        expected.add(new Word("the"));
        expected.add(new Word("Dwarf-lords"));
        expected.add(new Word("in"));
        expected.add(new Word("their"));
        expected.add(new Word("halls"));
        expected.add(new Word("of"));
        expected.add(new Word("stone"));
        expected.add(new Separator(","));

        expected.add(new Separator("&"));

        new TextPhrasesCreator().perform(expected);
        new TextOrderNumberSetter().perform(expected);

        return expected;
    }
}