package com.drkiettran.scriptureinaction.catalog;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.steps.CollectingTextSteps;
import com.drkiettran.scriptureinaction.catalog.util.TestUtils;
import com.drkiettran.scriptureinaction.model.constants.DouayRheims;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class BookInfoTest {
	private static final Logger logger = LoggerFactory.getLogger(BookInfoTest.class);

	private String nabFolder;
	private String rsvFolder;
	private String drFolder;
	private String lvFolder;

	@Steps
	private CollectingTextSteps collectingSteps;

	@Before
	public void setUp() {
		nabFolder = System.getProperty("sia.bible.nab.folder");
		rsvFolder = System.getProperty("sia.bible.rsv.folder");
		drFolder = System.getProperty("sia.bible.dr.folder");
		lvFolder = System.getProperty("sia.bible.lv.folder");
	}

	@Test
	public void verifyNumberOfBooksEachTranslation() {

		long numberOfBooks = getNumberOfBooksInFolder(nabFolder);
		assertThat(numberOfBooks, equalTo((long) (3 * NewAmerican.NUMBER_OF_ALL_BOOKS)));

		numberOfBooks = getNumberOfBooksInFolder(rsvFolder);
		assertThat(numberOfBooks, equalTo((long) NewAmerican.NUMBER_OF_ALL_BOOKS));

		numberOfBooks = getNumberOfBooksInFolder(lvFolder);
		assertThat(numberOfBooks, equalTo((long) (2 * NewAmerican.NUMBER_OF_ALL_BOOKS)));

		numberOfBooks = getNumberOfBooksInFolder(drFolder);
		assertThat(numberOfBooks, equalTo((long) (2 * NewAmerican.NUMBER_OF_ALL_BOOKS)));

	}

	@Test
	public void verifyChaptersVerses() {
//		verifyChaptersVerses(nabFolder, BibleConstants.NAMES_OF_ALL_BOOKS,
//				BibleConstants.NUMBER_OF_CHAPTERS_BY_BOOK_NAME,
//				BibleConstants.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME);
//		verifyChaptersVerses(rsvFolder);
//		verifyChaptersVerses(drFolder, BibleConstants.NAMES_OF_ALL_BOOKS_DR,
//				BibleConstants.NUMBER_OF_CHAPTERS_BY_BOOK_NAME_DR,
//				BibleConstants.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME_DR);
		verifyChaptersVerses(lvFolder, DouayRheims.NAMES_OF_ALL_BOOKS, DouayRheims.NUMBER_OF_CHAPTERS_BY_BOOK_NAME,
				DouayRheims.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK);

	}

	private void verifyChaptersVerses(String folder, String[] bookNames, int[] numberChaptersByBooks,
			int[][] numberVersesByChaptersByBooks) {
		for (int bookIdx = 0; bookIdx < NewAmerican.NUMBER_OF_ALL_BOOKS; bookIdx++) {
			logger.info("book {}", bookNames[bookIdx]);

			String bookName = bookNames[bookIdx].replace("(", "_").replace(")", "_");
			String fname = String.format("%d-%s-text.txt", bookIdx + 1, bookName);

			Path path = Paths.get(folder, fname);
			List<String> allLines = TestUtils.readAllLines(path);

			List<List<String>> versesByChapters = collectingSteps.loadBookFromFile(bookNames[bookIdx], allLines);

			assertThat(versesByChapters.size(), equalTo(numberChaptersByBooks[bookIdx]));
			for (int chapterIdx = 0; chapterIdx < versesByChapters.size(); chapterIdx++) {
				logger.info("chapter {}", chapterIdx + 1);

				assertThat(versesByChapters.get(chapterIdx).size(),
						equalTo(numberVersesByChaptersByBooks[bookIdx][chapterIdx]));
			}
		}
	}

	@Test
	public void should_get_number_of_verses_by_chapter_by_book_dr() {
		StringBuilder sb = new StringBuilder(
				"public static final int[][] NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME_DR = {\n");
		int numberOfAllVersesForAllBooks = 0;

		for (int bookIdx = 0; bookIdx < NewAmerican.NUMBER_OF_ALL_BOOKS; bookIdx++) {
			logger.info("book {}", DouayRheims.NAMES_OF_ALL_BOOKS[bookIdx]);
			String bookName = DouayRheims.NAMES_OF_ALL_BOOKS[bookIdx].replace("(", "_").replace(")", "_");
			String fname = String.format("%d-%s-text.txt", bookIdx + 1, bookName);
			Path path = Paths.get(drFolder, fname);
			List<String> allLines = TestUtils.readAllLines(path);

			List<List<String>> versesByChapters = collectingSteps
					.loadBookFromFile(DouayRheims.NAMES_OF_ALL_BOOKS[bookIdx], allLines);

			sb.append("/** ").append(DouayRheims.NAMES_OF_ALL_BOOKS[bookIdx]).append(" **/ {");
			for (int chapterIdx = 0; chapterIdx < versesByChapters.size(); chapterIdx++) {
				logger.info("chapter {}", chapterIdx + 1);
				sb.append(versesByChapters.get(chapterIdx).size()).append(',');
				numberOfAllVersesForAllBooks += versesByChapters.get(chapterIdx).size();
			}
			sb.deleteCharAt(sb.length() - 1).append("},\n");
		}

		sb.deleteCharAt(sb.length() - 2).append("};\n");
		sb.append("public static final Integer NUMBER_OF_ALL_VERSES_FOR_ALL_BOOKS_DR = ")
				.append(numberOfAllVersesForAllBooks).append(";\n");

		logger.info("generated code:\n{}", sb);

	}

	@Test
	public void should_get_number_of_verses_by_chapter_by_book_lv() {
		StringBuilder sb = new StringBuilder(
				"public static final int[][] NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME_LV = {\n");
		int numberOfAllVersesForAllBooks = 0;

		for (int bookIdx = 0; bookIdx < NewAmerican.NUMBER_OF_ALL_BOOKS; bookIdx++) {
			logger.info("book {}", DouayRheims.NAMES_OF_ALL_BOOKS[bookIdx]);
			String bookName = DouayRheims.NAMES_OF_ALL_BOOKS[bookIdx].replace("(", "_").replace(")", "_");
			String fname = String.format("%d-%s-text.txt", bookIdx + 1, bookName);
			Path path = Paths.get(lvFolder, fname);
			List<String> allLines = TestUtils.readAllLines(path);

			List<List<String>> versesByChapters = collectingSteps
					.loadBookFromFile(DouayRheims.NAMES_OF_ALL_BOOKS[bookIdx], allLines);

			sb.append("/** ").append(DouayRheims.NAMES_OF_ALL_BOOKS[bookIdx]).append(" **/ {");
			for (int chapterIdx = 0; chapterIdx < versesByChapters.size(); chapterIdx++) {
				logger.info("chapter {}", chapterIdx + 1);
				sb.append(versesByChapters.get(chapterIdx).size()).append(',');
				numberOfAllVersesForAllBooks += versesByChapters.get(chapterIdx).size();
			}
			sb.deleteCharAt(sb.length() - 1).append("},\n");
		}

		sb.deleteCharAt(sb.length() - 2).append("};\n");
		sb.append("public static final Integer NUMBER_OF_ALL_VERSES_FOR_ALL_BOOKS_LV = ")
				.append(numberOfAllVersesForAllBooks).append(";\n");

		logger.info("generated code:\n{}", sb);

	}

	@Test
	public void side_by_side_number_chapters_verses() {
		StringBuilder sb = new StringBuilder();
		for (int bookIdx = 0; bookIdx < NewAmerican.NUMBER_OF_ALL_BOOKS; bookIdx++) {
			sb.append("book: - nab:").append(NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]).append(" -- dr: ")
					.append(DouayRheims.NAMES_OF_ALL_BOOKS[bookIdx]).append('\n');
		}
		logger.info("\n{}", sb);
	}

	private long getNumberOfBooksInFolder(String folder) {
		File file = new File(folder);
		return file.listFiles().length;
	}

}
