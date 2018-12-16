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
		verifyNumberOfChaptersByBook(NewAmerican.NAMES_OF_ALL_BOOKS, NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME,
				BibleBook.NAB);
		verifyNumberOfChaptersByBook(NewAmerican.NAMES_OF_ALL_BOOKS, RevisedStandard.NUMBER_OF_CHAPTERS_BY_BOOK_NAME,
				BibleBook.RSV);
		verifyNumberOfChaptersByBook(NewAmerican.NAMES_OF_ALL_BOOKS, DouayRheims.NUMBER_OF_CHAPTERS_BY_BOOK_NAME,
				BibleBook.DR);
		verifyNumberOfChaptersByBook(NewAmerican.NAMES_OF_ALL_BOOKS, LatinVulgate.NUMBER_OF_CHAPTERS_BY_BOOK_NAME,
				BibleBook.LV);
	}

	@Test
	public void shouldGetNumberOfVersesByChapterByBook() {
		verifyNumberOfVersesByChapterByBook(NewAmerican.NAMES_OF_ALL_BOOKS,
				NewAmerican.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME, BibleBook.NAB);

		verifyNumberOfVersesByChapterByBook(NewAmerican.NAMES_OF_ALL_BOOKS,
				RevisedStandard.NUMBER_OF_VERSES_BY_CHAPTERS_BY_BOOK_NAME, BibleBook.RSV);

		verifyNumberOfVersesByChapterByBook(NewAmerican.NAMES_OF_ALL_BOOKS,
				DouayRheims.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK, BibleBook.DR);

		verifyNumberOfVersesByChapterByBook(NewAmerican.NAMES_OF_ALL_BOOKS,
				LatinVulgate.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK, BibleBook.LV);

	}

	@Test
	public void shouldGetBookNameByAbbreviation() {
		String[] abbrevs = NewAmerican.ABBREVIATIONS_OF_ALL_BOOKS;

		for (int bookIdx = 0; bookIdx < abbrevs.length; bookIdx++) {
			logger.info("Value = {}", abbrevs[bookIdx]);
		}
	}

	// Supporting code.
	private void verifyNumberOfChaptersByBook(String[] bookNames, int[] numberChapters, String translatioType) {
		for (int bookIdx = 0; bookIdx < bookNames.length; bookIdx++) {
			logger.info("expected {} chapters for {} ({})", numberChapters[bookIdx], bookNames[bookIdx],
					translatioType);
			givenTranslationAndName(translatioType, bookNames[bookIdx]);
			whenQueryNumberOfChapters();
			thenNumChaptersShouldEqual(numberChapters[bookIdx]);
		}
	}

	private void verifyNumberOfVersesByChapterByBook(String[] bookNames, int[][] numVerses, String translationType) {
		for (int bookIdx = 0; bookIdx < bookNames.length; bookIdx++) {
			logger.info("expected {} verses by chapter by book for {} ({})", numVerses[bookIdx], bookNames[bookIdx],
					translationType);
			givenTranslationAndName(translationType, bookNames[bookIdx]);
			whenQueryNumberOfVersesByChapter();
			thenNumVersesByChapterShouldEqual(numVerses[bookIdx]);
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
