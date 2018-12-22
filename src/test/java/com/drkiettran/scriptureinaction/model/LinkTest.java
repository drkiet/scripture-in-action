package com.drkiettran.scriptureinaction.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class LinkTest {
	@Test
	public void shouldParseCorrectly() {
		String text = "b. [1:10] Lv 10:1–2; Sir 48:3; Lk 9:51–55.";
		Link link = new Link();
		link.setBookName("Genesis");
		link.parse(text);

		assertThat(link.getLinkRef(), equalTo("Gen 1:10"));
		assertThat(link.getLinkPointers().get(0).getChapterNumber(), equalTo(1));
		assertThat(link.getLinkPointers().get(0).getStartingVerseNumber(), equalTo(10));
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

		assertThat(link.getLinkRef(), equalTo("Gen 8:7ff"));
		assertThat(link.getLinkPointers().get(0).getChapterNumber(), equalTo(8));
		assertThat(link.getLinkPointers().get(0).getStartingVerseNumber(), equalTo(7));
		assertThat(link.getRelatedVerses().get(0).getBookName(), equalTo("Genesis"));
		assertThat(link.getRelatedVerses().get(0).getChapterNumber(), equalTo(1));
		assertThat(link.getRelatedVerses().get(0).getStartingVerseNumber(), equalTo(26));

		assertThat(link.getRelatedVerses().get(1).getBookName(), equalTo("Genesis"));
		assertThat(link.getRelatedVerses().get(1).getChapterNumber(), equalTo(1));
		assertThat(link.getRelatedVerses().get(1).getStartingVerseNumber(), equalTo(28));

		assertThat(link.getRelatedVerses().get(2).getBookName(), equalTo("Wisdom"));
		assertThat(link.getRelatedVerses().get(2).getChapterNumber(), equalTo(9));
		assertThat(link.getRelatedVerses().get(2).getStartingVerseNumber(), equalTo(2));
		assertThat(link.getRelatedVerses().get(2).getEndingVerseNumber(), equalTo(2));

		assertThat(link.getRelatedVerses().get(3).getBookName(), equalTo("1 Corinthians"));
		assertThat(link.getRelatedVerses().get(3).getChapterNumber(), equalTo(15));
		assertThat(link.getRelatedVerses().get(3).getStartingVerseNumber(), equalTo(27));
		assertThat(link.getRelatedVerses().get(3).getEndingVerseNumber(), equalTo(27));

		text = "c. [8:5] Heb 2:6ff; 3:5-6.";
		link.parse(text);

		assertThat(link.getLinkRef(), equalTo("Gen 8:5"));
		assertThat(link.getLinkPointers().get(0).getChapterNumber(), equalTo(8));
		assertThat(link.getLinkPointers().get(0).getStartingVerseNumber(), equalTo(5));
		assertThat(link.getRelatedVerses().get(0).getBookName(), equalTo("Hebrews"));
		assertThat(link.getRelatedVerses().get(0).getChapterNumber(), equalTo(2));
		assertThat(link.getRelatedVerses().get(0).getStartingVerseNumber(), equalTo(6));
		assertThat(link.getRelatedVerses().get(0).getEndingVerseNumber(), equalTo(6));

		assertThat(link.getRelatedVerses().get(1).getBookName(), equalTo("Hebrews"));
		assertThat(link.getRelatedVerses().get(1).getChapterNumber(), equalTo(3));
		assertThat(link.getRelatedVerses().get(1).getStartingVerseNumber(), equalTo(5));
		assertThat(link.getRelatedVerses().get(1).getEndingVerseNumber(), equalTo(6));

		text = "g. [24:50–51] Tb 7:11–12.";
		link.parse(text);

		assertThat(link.getLinkRef(), equalTo("Gen 24:50–51"));
		assertThat(link.getLinkPointers().get(0).getChapterNumber(), equalTo(24));
		assertThat(link.getLinkPointers().get(0).getStartingVerseNumber(), equalTo(50));
		assertThat(link.getLinkPointers().get(0).getEndingVerseNumber(), equalTo(51));
		assertThat(link.getRelatedVerses().get(0).getBookName(), equalTo("Tobit"));
		assertThat(link.getRelatedVerses().get(0).getChapterNumber(), equalTo(7));
		assertThat(link.getRelatedVerses().get(0).getStartingVerseNumber(), equalTo(11));
		assertThat(link.getRelatedVerses().get(0).getEndingVerseNumber(), equalTo(12));

		/**
		 * This example should be transformed to: <code>
		 *  From:
		 * c. [18:21, 25] Dt 1:15; 16:18.
		 *  To:
		 * c. [18:21] Dt 1:15; 16:18.
		 * c. [18:25] Dt 1:15; 16:18.
		 * </code>
		 **/
		text = "c. [18:21, 25] Dt 1:15; 16:18; cf. Lv 16:21; Est A:3; 2:6.";
		link.parse(text);

		assertThat(link.getLinkRef(), equalTo("Gen 18:21, 25"));
		assertThat(link.getLinkPointers().get(0).getChapterNumber(), equalTo(18));
		assertThat(link.getLinkPointers().get(0).getStartingVerseNumber(), equalTo(21));
		assertThat(link.getLinkPointers().get(0).getEndingVerseNumber(), equalTo(21));

		assertThat(link.getLinkPointers().get(1).getChapterNumber(), equalTo(18));
		assertThat(link.getLinkPointers().get(1).getStartingVerseNumber(), equalTo(25));
		assertThat(link.getLinkPointers().get(1).getEndingVerseNumber(), equalTo(25));

		assertThat(link.getRelatedVerses().get(0).getBookName(), equalTo("Deuteronomy"));
		assertThat(link.getRelatedVerses().get(0).getChapterNumber(), equalTo(1));
		assertThat(link.getRelatedVerses().get(0).getStartingVerseNumber(), equalTo(15));
		assertThat(link.getRelatedVerses().get(0).getEndingVerseNumber(), equalTo(15));

		assertThat(link.getRelatedVerses().get(1).getBookName(), equalTo("Deuteronomy"));
		assertThat(link.getRelatedVerses().get(1).getChapterNumber(), equalTo(16));
		assertThat(link.getRelatedVerses().get(1).getStartingVerseNumber(), equalTo(18));
		assertThat(link.getRelatedVerses().get(1).getEndingVerseNumber(), equalTo(18));

		assertThat(link.getRelatedVerses().get(2).getBookName(), equalTo("Leviticus"));
		assertThat(link.getRelatedVerses().get(2).getChapterNumber(), equalTo(16));
		assertThat(link.getRelatedVerses().get(2).getStartingVerseNumber(), equalTo(21));
		assertThat(link.getRelatedVerses().get(2).getEndingVerseNumber(), equalTo(21));

		assertThat(link.getRelatedVerses().get(3).getBookName(), equalTo("Esther"));
		assertThat(link.getRelatedVerses().get(3).getChapterNumber(), equalTo(100));
		assertThat(link.getRelatedVerses().get(3).getStartingVerseNumber(), equalTo(3));
		assertThat(link.getRelatedVerses().get(3).getEndingVerseNumber(), equalTo(3));

		assertThat(link.getRelatedVerses().get(4).getBookName(), equalTo("Esther"));
		assertThat(link.getRelatedVerses().get(4).getChapterNumber(), equalTo(2));
		assertThat(link.getRelatedVerses().get(4).getStartingVerseNumber(), equalTo(6));
		assertThat(link.getRelatedVerses().get(4).getEndingVerseNumber(), equalTo(6));
	}
}
