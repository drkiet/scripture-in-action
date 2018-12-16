package com.drkiettran.scriptureinaction.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class LinkTest {
	@Test
	public void shouldParseCorrectly() {
		String text = "b. [1:10] Lv 10:1–2; Sir 48:3; Lk 9:51–55.";
		Link link = new Link();
		link.parse(text);

		assertThat(link.getLinkRef(), equalTo("b"));
		assertThat(link.getChapterNumber(), equalTo(1));
		assertThat(link.getVerseNumber(), equalTo(10));
		assertThat(link.getRelatedVerses().get(0).getBookName(), equalTo("Leviticus"));
		assertThat(link.getRelatedVerses().get(0).getChapterNumber(), equalTo(10));
		assertThat(link.getRelatedVerses().get(0).getStartingVerseNumber(), equalTo(1));
		assertThat(link.getRelatedVerses().get(0).getEndingVerseNumber(), equalTo(2));

		assertThat(link.getRelatedVerses().get(1).getBookName(), equalTo("Sirach"));
		assertThat(link.getRelatedVerses().get(1).getChapterNumber(), equalTo(48));
		assertThat(link.getRelatedVerses().get(1).getStartingVerseNumber(), equalTo(3));
		assertThat(link.getRelatedVerses().get(1).getEndingVerseNumber(), equalTo(3));

		assertThat(link.getRelatedVerses().get(2).getBookName(), equalTo("Luke"));
		assertThat(link.getRelatedVerses().get(2).getChapterNumber(), equalTo(9));
		assertThat(link.getRelatedVerses().get(2).getStartingVerseNumber(), equalTo(51));
		assertThat(link.getRelatedVerses().get(2).getEndingVerseNumber(), equalTo(55));

		text = "d. [8:7ff] Gn 1:26, 28; Wis 9:2; 1 Cor 15:27.";
		link.parse(text);

		assertThat(link.getLinkRef(), equalTo("d"));
		assertThat(link.getChapterNumber(), equalTo(8));
		assertThat(link.getVerseNumber(), equalTo(7));
		assertThat(link.getRelatedVerses().get(0).getBookName(), equalTo("Genesis"));
		assertThat(link.getRelatedVerses().get(0).getChapterNumber(), equalTo(1));
		assertThat(link.getRelatedVerses().get(0).getVerses().get(0), equalTo(26));
		assertThat(link.getRelatedVerses().get(0).getVerses().get(1), equalTo(28));

		assertThat(link.getRelatedVerses().get(1).getBookName(), equalTo("Wisdom"));
		assertThat(link.getRelatedVerses().get(1).getChapterNumber(), equalTo(9));
		assertThat(link.getRelatedVerses().get(1).getStartingVerseNumber(), equalTo(2));
		assertThat(link.getRelatedVerses().get(1).getEndingVerseNumber(), equalTo(2));

		assertThat(link.getRelatedVerses().get(2).getBookName(), equalTo("1 Corinthians"));
		assertThat(link.getRelatedVerses().get(2).getChapterNumber(), equalTo(15));
		assertThat(link.getRelatedVerses().get(2).getStartingVerseNumber(), equalTo(27));
		assertThat(link.getRelatedVerses().get(2).getEndingVerseNumber(), equalTo(27));

		text = "c. [8:5] Heb 2:6ff.";
		link.parse(text);

		assertThat(link.getLinkRef(), equalTo("c"));
		assertThat(link.getChapterNumber(), equalTo(8));
		assertThat(link.getVerseNumber(), equalTo(5));
		assertThat(link.getRelatedVerses().get(0).getBookName(), equalTo("Hebrews"));
		assertThat(link.getRelatedVerses().get(0).getChapterNumber(), equalTo(2));
		assertThat(link.getRelatedVerses().get(0).getStartingVerseNumber(), equalTo(6));
		assertThat(link.getRelatedVerses().get(0).getEndingVerseNumber(), equalTo(6));
	}
}
