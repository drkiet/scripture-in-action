package com.drkiettran.scriptureinaction.catalog;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.steps.CollectingTextSteps;
import com.drkiettran.scriptureinaction.catalog.util.TestUtils;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectingTextDR {
	public static final Logger logger = LoggerFactory.getLogger(CollectingTextDR.class);
	private static final String DR_CONTENT_URL = "http://www.drbo.org/";

	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	private CollectingTextSteps collectingSteps;

	@Test
	public void should_get_all_text_of_all_books_rsv() {
		logger.info("getting all text for all books");
		collectingSteps.get_all_text_all_books_dr(DR_CONTENT_URL);
	}

	
	@Ignore
	public void rsv_chapters_verses_arrays() {
		StringBuilder numChapterArray = new StringBuilder(
				"public static final int[] NUMBER_OF_CHAPTERS_BY_BOOK_NAME_RSV = {");
		StringBuilder numVerseArray = new StringBuilder(
				"public static final int[][] NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME_RSV = {");
		for (int bookIdx = 0; bookIdx < NewAmerican.NUMBER_OF_ALL_BOOKS; bookIdx++) {
			logger.info("Verifying RSV book {}", NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			String fileName = String.format("%d-%s-text.txt", bookIdx + 1, NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			Path path = Paths.get(System.getProperty("sia.bible.rsv.folder"), fileName);

			List<String> allLines = TestUtils.readAllLines(path);

			List<List<String>> versesByChapters = collectingSteps
					.loadBookFromFile(NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx], allLines);

			numChapterArray.append(versesByChapters.size()).append(",\n");
			numVerseArray.append("/*** ").append(NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			numVerseArray.append(" ***/ ").append("{");

			for (int chapterIdx = 0; chapterIdx < versesByChapters.size(); chapterIdx++) {
				numVerseArray.append(versesByChapters.get(chapterIdx).size()).append(", ");
			}

			numVerseArray.deleteCharAt(numVerseArray.length() - 2).append("}, \n");
		}

		numVerseArray.deleteCharAt(numVerseArray.length() - 3).append("};\n");
		numChapterArray.deleteCharAt(numChapterArray.length() - 2).append("};\n");

		logger.info("Generated code: {}\n{}\n", numVerseArray, numChapterArray);
	}

	@Ignore
	public void rsv_chapters_verses_agrees_with_constants() {

		logger.info("parsing rsv text.");

		for (int bookIdx = 0; bookIdx < NewAmerican.NUMBER_OF_ALL_BOOKS; bookIdx++) {
			logger.info("Verifying RSV book {}", NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			String fileName = String.format("%d-%s-text.txt", bookIdx + 1, NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			Path path = Paths.get(System.getProperty("sia.bible.rsv.folder"), fileName);

			List<String> allLines = TestUtils.readAllLines(path);

			List<List<String>> versesByChapters = collectingSteps
					.loadBookFromFile(NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx], allLines);

			if (versesByChapters.size() != NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx]) {
				logger.info("*** NOT EQUAL *** execpted {} but got {} chapters",
						NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx], versesByChapters.size());
			}

			int numChapters = Math.min(versesByChapters.size(),
					NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME[bookIdx]);

			for (int chapterIdx = 0; chapterIdx < numChapters; chapterIdx++) {
				logger.info("verifying chapter {}", chapterIdx + 1);
				if (versesByChapters.get(chapterIdx)
						.size() != NewAmerican.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME[bookIdx][chapterIdx]) {
					logger.info("*** NOT EQUAL *** execpted {} but got {} verses",
							NewAmerican.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME[bookIdx][chapterIdx],
							versesByChapters.get(chapterIdx).size());
				}
			}
		}
	}
}
