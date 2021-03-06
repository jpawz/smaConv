package smaConv.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import smaConv.util.AnkiCard;
import smaConv.util.Deck;
import smaConv.util.SmpakParser;

public class ClozeConverterTest {
  Converter converter;
  SmpakParser smpakParser;
  Deck<AnkiCard> deck;

  String questionTemplateForExampleConverter = "{{cloze:sentence}}<br>{{synonyms}}";
  String questionTemplateForSimpleConverter = "{{cloze:sentence}}";

  @Before
  public void setUp() {
    converter = new ClozeConverter();
    smpakParser = mock(SmpakParser.class);
  }

  @Test
  public void converterFromExamplesShouldBeCalled() {
    when(smpakParser.getFile("course.xml"))
        .thenReturn(ClozeConverterFromExamplesTest.courseXml.getBytes());
    when(smpakParser.getFile(ClozeConverterFromExamplesTest.xmlFileName))
        .thenReturn(ClozeConverterFromExamplesTest.xmlFile.getBytes());

    deck = converter.makeDeck(smpakParser);

    assertThat(deck.getQuestionTemplate())
        .isEqualToIgnoringCase(questionTemplateForExampleConverter);
  }

  @Test
  public void simpleConverterShouldBeCalled() {
    when(smpakParser.getFile("course.xml"))
        .thenReturn(SimpleClozeConverterTest.courseXml.getBytes());
    when(smpakParser.getFile(ClozeConverterFromExamplesTest.xmlFileName))
    .thenReturn(SimpleClozeConverterTest.xmlFile.getBytes());
    when(smpakParser.getFile(SimpleClozeConverterTest.xmlFileName))
        .thenReturn(SimpleClozeConverterTest.xmlFile.getBytes());

    deck = converter.makeDeck(smpakParser);

    assertThat(deck.getQuestionTemplate())
        .isEqualToIgnoringCase(questionTemplateForSimpleConverter);
  }
}
