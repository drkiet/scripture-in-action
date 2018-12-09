package com.drkiettran.scriptureinaction.catalog.dr;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.util.TestUtils;
import com.drkiettran.scriptureinaction.model.constants.DouayRheims;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectDouayRheims {
	private static final Logger logger = LoggerFactory.getLogger(CollectDouayRheims.class);
	private static final String DRBO_BIBLE_URL = System.getProperty("sia.bible.dr.url");
	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	DrSteps drSteps;

	/**
	 * Do this only one or when needed. So, mark it Ignore when done.
	 */
	@Ignore
	public void should_get_all_the_names_of_the_books() {
		logger.info("get names of books ...");
		List<String> bookNames = drSteps.gathers_the_names_of_the_books(DRBO_BIBLE_URL,
				DouayRheims.NUMBER_OF_ALL_BOOKS);

		assertThat(bookNames.size(), equalTo(DouayRheims.NUMBER_OF_ALL_BOOKS));
		TestUtils.makeArrayOfAllBookNames(bookNames, "public static final String[] NAMES_OF_ALL_BOOKS = {");
		assertThat(bookNames, containsInAnyOrder(DouayRheims.NAMES_OF_ALL_BOOKS));
	}

	@Ignore
	public void should_get_number_of_chapters_by_books() {
		int[] numberOfChaptersByBookName = drSteps.get_number_of_chapters_by_book_name(DRBO_BIBLE_URL,
				DouayRheims.NAMES_OF_ALL_BOOKS);
		logger.info("generated code:\n{}",
				TestUtils.makeArrayOfInteger("NUMBER_OF_CHAPTERS_BY_BOOK_NAME", numberOfChaptersByBookName));
	}

	@Test
	public void should_get_number_of_verses_by_chapter_by_books() {
		List<Integer[]> numberVersesByChapterByBook = drSteps.get_number_of_verses_by_chapter_by_book(DRBO_BIBLE_URL,
				DouayRheims.NUMBER_OF_CHAPTERS_BY_BOOK_NAME, DouayRheims.NAMES_OF_ALL_BOOKS);
		logger.info("generated code:\n{}", TestUtils.makeArrayOfListOfInteger("NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK",
				DouayRheims.NAMES_OF_ALL_BOOKS, numberVersesByChapterByBook));
	}

	// NON WEBDRIVER

	@Test
	public void verifying_names_with_new_american() {
		List<Integer> na2dr = new ArrayList<Integer>();

		for (int naBookIdx = 0; naBookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; naBookIdx++) {
			int matchedDrBookIdx = -1;
			for (int drBookIdx = 0; drBookIdx < DouayRheims.NAMES_OF_ALL_BOOKS.length; drBookIdx++) {
				if (DouayRheims.NAMES_OF_ALL_BOOKS[drBookIdx].contains(NewAmerican.NAMES_OF_ALL_BOOKS[naBookIdx])) {
					matchedDrBookIdx = drBookIdx;
					break;
				}
			}
			na2dr.add(matchedDrBookIdx);
			if (matchedDrBookIdx < 0) {
				logger.info("*** NO MATCHED {} ***", NewAmerican.NAMES_OF_ALL_BOOKS[naBookIdx]);
			}
		}

		logger.info("result:\n{}", TestUtils.makeArrayOfInteger("na2dr", na2dr));
		logger.info("comment:\n{}", na2drMap(na2dr, NewAmerican.NAMES_OF_ALL_BOOKS, DouayRheims.NAMES_OF_ALL_BOOKS));
	}

	public static String na2drMap(List<Integer> src2dst, String[] src, String[] dst) {
		StringBuilder sb = new StringBuilder("/** <code>\n");
		for (int srcIdx = 0; srcIdx < src2dst.size(); srcIdx++) {
			sb.append(" * ").append('(').append(srcIdx).append(')');
			sb.append(src[srcIdx]).append(" => ").append(dst[src2dst.get(srcIdx)]);
			sb.append('(').append(src2dst.get(srcIdx)).append(')');
			sb.append('\n');
		}
		sb.append(" * </code>\n */");
		return sb.toString();
	}
}
