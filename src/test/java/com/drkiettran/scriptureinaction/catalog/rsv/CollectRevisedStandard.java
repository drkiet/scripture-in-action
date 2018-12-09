package com.drkiettran.scriptureinaction.catalog.rsv;

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
import com.drkiettran.scriptureinaction.model.constants.RevisedStandard;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectRevisedStandard {
	private static final Logger logger = LoggerFactory.getLogger(CollectRevisedStandard.class);
	private static final String RSV_BIBLE_URL = System.getProperty("sia.bible.rsv.url");

	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	RsvSteps rsvSteps;

	@Ignore
	public void should_get_Lall_the_names_of_the_books() {
		List<String> bookNames = rsvSteps.gathers_the_names_of_the_books(RSV_BIBLE_URL,
				RevisedStandard.NUMBER_OF_ALL_BOOKS);

		assertThat(bookNames.size(), equalTo(RevisedStandard.NUMBER_OF_ALL_BOOKS));
		assertThat(bookNames, containsInAnyOrder(RevisedStandard.NAMES_OF_ALL_BOOKS));
		TestUtils.makeArrayOfAllBookNames(bookNames, "public static final String[] NAMES_OF_ALL_BOOKS = {");
	}

	@Test
	public void should_get_number_of_chapters_by_books() {
		List<Integer> numChaptersByBook = new ArrayList<Integer>();
		List<Integer[]> numVersesByChapterByBook = new ArrayList<Integer[]>();
		rsvSteps.get_number_of_chapters_by_book_name(RSV_BIBLE_URL, RevisedStandard.NAMES_OF_ALL_BOOKS,
				numChaptersByBook, numVersesByChapterByBook);
		logger.info("generated code:\n{}",
				TestUtils.makeArrayOfInteger("NUMBER_OF_CHAPTERS_BY_BOOK_NAME", numChaptersByBook));
		logger.info("generated code:\n{}",
				TestUtils.makeArrayOfListOfInteger("NUMBER_OF_VERSES_BY_CHAPTERS_BY_BOOK_NAME",
						RevisedStandard.NAMES_OF_ALL_BOOKS, numVersesByChapterByBook));
	}

}
