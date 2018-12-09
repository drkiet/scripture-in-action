package com.drkiettran.scriptureinaction.catalog.lv;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.util.TestUtils;
import com.drkiettran.scriptureinaction.model.constants.DouayRheims;
import com.drkiettran.scriptureinaction.model.constants.LatinVulgate;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectLatinVulgate {
	private static final Logger logger = LoggerFactory.getLogger(CollectLatinVulgate.class);
	private static final String LV_BIBLE_URL = System.getProperty("sia.bible.lv.url");
	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	LvSteps lvSteps;

	/**
	 * Do this only one or when needed. So, mark it Ignore when done.
	 */
	@Ignore
	public void should_get_all_the_names_of_the_books() {
		logger.info("get names of books ...");
		List<String> bookNames = lvSteps.gathers_the_names_of_the_books(LV_BIBLE_URL, LatinVulgate.NUMBER_OF_ALL_BOOKS);

		assertThat(bookNames.size(), equalTo(LatinVulgate.NUMBER_OF_ALL_BOOKS));
		TestUtils.makeArrayOfAllBookNames(bookNames, "public static final String[] NAMES_OF_ALL_BOOKS = {");
		assertThat(bookNames, containsInAnyOrder(LatinVulgate.NAMES_OF_ALL_BOOKS));
	}

	@Ignore
	public void should_get_number_of_chapters_by_books() {
		int[] numberOfChaptersByBookName = lvSteps.get_number_of_chapters_by_book_name(LV_BIBLE_URL,
				LatinVulgate.NAMES_OF_ALL_BOOKS);
		logger.info("generated code:\n{}",
				TestUtils.makeArrayOfInteger("NUMBER_OF_CHAPTERS_BY_BOOK_NAME", numberOfChaptersByBookName));
	}

	@Test
	public void should_get_number_of_verses_by_chapter_by_books() {
		List<Integer[]> numberVersesByChapterByBook = lvSteps.get_number_of_verses_by_chapter_by_book(LV_BIBLE_URL,
				LatinVulgate.NUMBER_OF_CHAPTERS_BY_BOOK_NAME, LatinVulgate.NAMES_OF_ALL_BOOKS);
		logger.info("generated code:\n{}", TestUtils.makeArrayOfListOfInteger("NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK",
				LatinVulgate.NAMES_OF_ALL_BOOKS, numberVersesByChapterByBook));
	}
}
