package com.drkiettran.scriptureinaction.query;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.model.BibleBook;
import com.drkiettran.scriptureinaction.model.constants.DouayRheims;
import com.drkiettran.scriptureinaction.model.constants.LatinVulgate;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;
import com.drkiettran.scriptureinaction.model.constants.RevisedStandard;
import com.drkiettran.scriptureinaction.repository.BibleRepositoryViaFile;

public class AboutBookTest {
	private static final Logger logger = LoggerFactory.getLogger(AboutBookTest.class);
	private String translation;
	private String bookName;
	private AboutBook aboutBook;
	private int actualNumChapters;
	private int[] actualNumVersesByChapter;

	@Before
	public void setUp() {
		aboutBook = new AboutBookNab();
		aboutBook.setRepo(new BibleRepositoryViaFile());
	}

	@Test
	public void shouldGetNumberOfChaptersByBook() {
		for (int bookIdx = 0; bookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			logger.info("expected {} chapters for {} ({})", NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx],
					NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx], BibleBook.NAB);
			givenTranslationAndName(BibleBook.NAB, NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			whenQueryNumberOfChapters();
			thenNumChaptersShouldEqual(NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx]);
		}

		for (int bookIdx = 0; bookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			logger.info("expected {} chapters for {} ({})", RevisedStandard.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx],
					NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx], BibleBook.RSV);
			givenTranslationAndName(BibleBook.RSV, NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			whenQueryNumberOfChapters();
			thenNumChaptersShouldEqual(RevisedStandard.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx]);
		}

		for (int bookIdx = 0; bookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			logger.info("expected {} chapters for {} ({})", DouayRheims.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx],
					NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx], BibleBook.DR);
			givenTranslationAndName(BibleBook.DR, NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			whenQueryNumberOfChapters();
			thenNumChaptersShouldEqual(DouayRheims.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx]);
		}

		for (int bookIdx = 0; bookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			logger.info("expected {} chapters for {} ({})", LatinVulgate.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx],
					NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx], BibleBook.LV);
			givenTranslationAndName(BibleBook.LV, NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			whenQueryNumberOfChapters();
			thenNumChaptersShouldEqual(LatinVulgate.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx]);
		}

	}

	@Test
	public void shouldGetNumberOfVersesByChapterByBook() {
		for (int bookIdx = 0; bookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			logger.info("expected {} chapters for {} ({})", NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx],
					NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx], BibleBook.NAB);
			givenTranslationAndName(BibleBook.NAB, NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			whenQueryNumberOfVersesByChapter();
			thenNumVersesByChapterShouldEqual(NewAmerican.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME[bookIdx]);
		}
		for (int bookIdx = 0; bookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			logger.info("expected {} chapters for {} ({})", RevisedStandard.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx],
					NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx], BibleBook.RSV);
			givenTranslationAndName(BibleBook.RSV, NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			whenQueryNumberOfVersesByChapter();
			thenNumVersesByChapterShouldEqual(RevisedStandard.NUMBER_OF_VERSES_BY_CHAPTERS_BY_BOOK_NAME[bookIdx]);
		}

		for (int bookIdx = 0; bookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			logger.info("expected {} chapters for {} ({})", DouayRheims.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx],
					NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx], BibleBook.DR);
			givenTranslationAndName(BibleBook.DR, NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			whenQueryNumberOfVersesByChapter();
			thenNumVersesByChapterShouldEqual(DouayRheims.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK[bookIdx]);
		}

		for (int bookIdx = 0; bookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			logger.info("expected {} chapters for {} ({})", LatinVulgate.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx],
					NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx], BibleBook.LV);
			givenTranslationAndName(BibleBook.LV, NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			whenQueryNumberOfVersesByChapter();
			thenNumVersesByChapterShouldEqual(LatinVulgate.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK[bookIdx]);
		}
	}

	private void thenNumVersesByChapterShouldEqual(int[] expectedNumVersesByChapter) {
		for (int i = 0; i < actualNumVersesByChapter.length; i++) {
			if (actualNumVersesByChapter[i] != expectedNumVersesByChapter[i]) {
				logger.info("{}: {} instead {}", i, expectedNumVersesByChapter[i], actualNumVersesByChapter[i]);
			}
		}
		assertThat(this.actualNumVersesByChapter, equalTo(expectedNumVersesByChapter));
	}

	private void whenQueryNumberOfVersesByChapter() {
		actualNumVersesByChapter = aboutBook.getNumVersesByChapter(translation, bookName);
	}

	private void thenNumChaptersShouldEqual(int expectedNumChapters) {
		assertThat(actualNumChapters, equalTo(expectedNumChapters));
	}

	private void whenQueryNumberOfChapters() {
		actualNumChapters = aboutBook.getNumChapters(translation, bookName);
		logger.info("actual {} chapters", actualNumChapters);
	}

	private void givenTranslationAndName(String translation, String bookName) {
		this.translation = translation;
		this.bookName = bookName;
	}
}
