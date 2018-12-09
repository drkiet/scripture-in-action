package com.drkiettran.scriptureinaction.catalog.nab;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.List;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.util.TestUtils;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectNewAmerican {
	private static final Logger logger = LoggerFactory.getLogger(CollectNewAmerican.class);
	private static final String USCCB_BIBLE_URL = System.getProperty("sia.bible.nab.url");
	private static final String SHORT_NAMES_URL = "http://catholic-resources.org/Bible/Abbreviations-Abreviaciones.htm";

	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	NabSteps nabSteps;

	@Ignore
	public void should_get_CATHOLIC_RESOURCES_ABBREVIATIONS_URLall_the_names_of_the_books() {
		nabSteps.visits_usccb_web_site(USCCB_BIBLE_URL);
		List<String> bookNames = nabSteps.gathers_the_names_of_the_books();

		assertThat(bookNames.size(), equalTo(NewAmerican.NUMBER_OF_ALL_BOOKS));
		assertThat(bookNames, containsInAnyOrder(NewAmerican.NAMES_OF_ALL_BOOKS));
		TestUtils.makeArrayOfAllBookNames(bookNames, "public static final String[] NAMES_OF_ALL_BOOKS = {");
	}

	@Ignore
	public void should_get_name_of_collections() {
		nabSteps.visits_usccb_web_site(USCCB_BIBLE_URL);
		List<String> collectionNames = nabSteps.gathers_the_names_of_the_collections();
		TestUtils.makeArrayOfAllBookNames(collectionNames, "public static final String[] NAMES_OF_COLLECTIONS = {");

		List<String> bookNamesByCollections = nabSteps.gathers_names_of_books_ordered_by_collections();
		TestUtils.makeArrayOfAllBookNames(bookNamesByCollections,
				"public static final String[] NAMES_OF_BOOKS_BY_COLLECTIONS = {");
	}

	@Ignore
	public void should_get_number_of_chapters_by_books() {
		int[] numberOfChaptersByBookName = nabSteps.get_number_of_chapters_by_book_name(USCCB_BIBLE_URL,
				NewAmerican.NAMES_OF_ALL_BOOKS);
		logger.info("generated code:\n{}",
				TestUtils.makeArrayOfInteger("NUMBER_OF_CHAPTERS_BY_BOOK_NAME", numberOfChaptersByBookName));
	}

	/**
	 * The USCCB website contains many errors in display - I manually correct them.
	 * So, when you run this, generated code will have errors.
	 */
	@Ignore
	public void should_get_number_of_verses_by_chapter_by_book() {
		List<Integer[]> versesPerBooks = nabSteps.get_number_of_verses_by_chapter_by_book_name(USCCB_BIBLE_URL,
				NewAmerican.NAMES_OF_ALL_BOOKS, NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME);
		StringBuilder sb = new StringBuilder(
				"public static final int[][] NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME = {\n");
		int numberOfAllVersesForAllBooks = 0;

		for (int bookIdx = 0; bookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			Integer[] versesPerBook = versesPerBooks.get(bookIdx);
			sb.append("/** ").append(NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]).append(" **/ {");

			for (int versesPerChapter : versesPerBook) {
				sb.append(versesPerChapter).append(',');
				numberOfAllVersesForAllBooks += versesPerChapter;
			}
			sb.deleteCharAt(sb.length() - 1).append("},\n");
		}

		sb.deleteCharAt(sb.length() - 2).append("};\n");
		sb.append("public static final Integer NUMBER_OF_ALL_VERSES_FOR_ALL_BOOKS = ")
				.append(numberOfAllVersesForAllBooks).append(";\n");

		logger.info("generated code:\n{}", sb);
	}

	@Ignore
	public void should_get_abbreviations_for_all_book_names() {
		List<String> abbreviations = nabSteps.get_abbreviations_names(SHORT_NAMES_URL, NewAmerican.NAMES_OF_ALL_BOOKS);
		assertThat(abbreviations.size(), equalTo(NewAmerican.NUMBER_OF_ALL_BOOKS));

		StringBuilder sb = new StringBuilder("private static final String[] ABBREVIATIONS_OF_ALL_BOOKS = {\n");

		for (int bookIdx = 0; bookIdx < NewAmerican.NUMBER_OF_ALL_BOOKS; bookIdx++) {
			String abbrev = abbreviations.get(bookIdx);
			String bookName = NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx];

			abbrev = abbrev.replaceAll(" or", ",");
			sb.append("/** ").append(bookName).append(" **/ ").append('"').append(abbrev).append('"').append(",\n");
		}

		sb.deleteCharAt(sb.length() - 2);
		sb.append("};");
		logger.info("generated code:\n{}", sb);

	}

}
