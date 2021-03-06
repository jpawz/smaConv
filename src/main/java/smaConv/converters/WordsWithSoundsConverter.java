package smaConv.converters;

import smaConv.util.AnkiCard;
import smaConv.util.Deck;
import smaConv.util.Parser;

public class WordsWithSoundsConverter extends WordsConverter {
  @Override
  public Deck<AnkiCard> makeDeck(Parser smpakParser) {
    Converter converter;
    if (super.hasExamples(smpakParser)) {
      converter = new WordsFromExamplesWithSoundConverter();
    } else {
      converter = new SimpleWordsWithSoundsConverter();
    }

    return converter.makeDeck(smpakParser);
  }
}