package com.drkiettran.scriptureinaction.catalog;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.BibleConstants;
import com.drkiettran.scriptureinaction.catalog.steps.CollectingTextSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectingText implements readAllLines {
	public static final Logger logger = LoggerFactory.getLogger(CollectingText.class);
	private static final String USCC_BIBLE_CONTENT_URL = "http://www.usccb.org/bible";
	private static final String CATHOLIC_CONTENT_URL = "https://www.catholic.org/bible/book.php";
	private static final String RSV_CONTENT_URL = "https://quod.lib.umich.edu/r/rsv/browse.html";

	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	private CollectingTextSteps collectingSteps;

	@Ignore
	public void should_verify_order_of_books_by_name() {
		List<String> firstChapterHeadings = collectingSteps.verify_order_of_books_by_name(CATHOLIC_CONTENT_URL,
				BibleConstants.NAMES_OF_ALL_BOOKS);
		for (int bookIdx = 0; bookIdx < BibleConstants.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			logger.info("heading {} - book {}", firstChapterHeadings.get(bookIdx),
					BibleConstants.NAMES_OF_ALL_BOOKS[bookIdx]);
			assertThat(firstChapterHeadings.get(bookIdx), containsString(BibleConstants.NAMES_OF_ALL_BOOKS[bookIdx]));
		}
	}

	/**
	 * file name: genesis_1_1.json
	 */
	@Test
	public void should_get_all_text_of_all_books() {
		logger.info("getting all text for all books");
		collectingSteps.get_all_text_all_books_via_catholic_dot_org(CATHOLIC_CONTENT_URL, USCC_BIBLE_CONTENT_URL);
	}

	@Ignore
	public void should_get_all_text_of_all_books_rsv() {
		logger.info("getting all text for all books");
		collectingSteps.get_all_text_all_books_rsv(RSV_CONTENT_URL);
	}

	@Ignore
	public void should_parse_rsv_book() {
		logger.info("parsing rsv text.");
		Path path = Paths.get("C:/book-catalog/bibles/nab", "temp.txt");

		List<String> allLines = readAllLines(path);

		int idx = 0;
		String text = collectingSteps.makeRsvContent(BibleConstants.NAMES_OF_ALL_BOOKS[idx], allLines);
		logger.info("test book: {}", text);
	}

	public List<String> readAllLines(Path path) {
		Charset charset = Charset.forName("ISO-8859-1");
		List<String> allLines = null;

		try {
			allLines = Files.readAllLines(path, charset);
		} catch (IOException e) {
			logger.error("ERROR: {}", e);
		}
		return allLines;
	}

	@Test
	public void rsv_chapters_verses_agrees_with_constants() {
		logger.info("parsing rsv text.");
		Path path = Paths.get("C:/book-catalog/bibles/resv", "Revelation-text.txt");

		List<String> allLines = readAllLines(path);
		
		int idx = 0;
		String text = collectingSteps.loadBookFromFile(BibleConstants.NAMES_OF_ALL_BOOKS[idx], allLines);
		logger.info("test book: {}", text);
	}
}
