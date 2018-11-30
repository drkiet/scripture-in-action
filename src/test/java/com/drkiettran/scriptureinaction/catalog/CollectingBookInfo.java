package com.drkiettran.scriptureinaction.catalog;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.BibleConstants;
import com.drkiettran.scriptureinaction.catalog.steps.CollectingSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectingBookInfo {
	private static final Logger logger = LoggerFactory.getLogger(CollectingBookInfo.class);
	private static final String USCCB_BIBLE_URL = "http://www.usccb.org/bible/books-of-the-bible/index.cfm";

	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	CollectingSteps collector;

	@Test
	public void should_get_all_the_names_of_the_books() {
		collector.visits_usccb_web_site(USCCB_BIBLE_URL);
		List<String> bookNames = collector.gathers_the_names_of_the_books();

		assertThat(bookNames.size(), equalTo(73));
		assertThat(bookNames, containsInAnyOrder(BibleConstants.NAMES_OF_ALL_BOOKS));
		makeArrayOfAllBookNames(bookNames, "public static final String[] NAMES_OF_ALL_BOOKS = {");
	}

	@Test
	public void should_get_the_names_of_the_ot_book() {
		List<String> otBookNames = Arrays
				.asList(Arrays.copyOfRange(BibleConstants.NAMES_OF_ALL_BOOKS, 0, BibleConstants.NUMBER_OF_OT_BOOKS));
		assertThat(otBookNames.size(), equalTo(BibleConstants.NUMBER_OF_OT_BOOKS));
		makeArrayOfAllBookNames(otBookNames, "public static final String[] NAMES_OF_OT_BOOKS = {");
	}

	@Test
	public void should_get_the_names_of_the_nt_book() {
		List<String> otBookNames = Arrays.asList(Arrays.copyOfRange(BibleConstants.NAMES_OF_ALL_BOOKS,
				BibleConstants.NUMBER_OF_OT_BOOKS, BibleConstants.NUMBER_OF_ALL_BOOKS));
		assertThat(otBookNames.size(), equalTo(BibleConstants.NUMBER_OF_NT_BOOKS));
		makeArrayOfAllBookNames(otBookNames, "public static final String[] NAMES_OF_NT_BOOKS = {");
	}

	@Test
	public void should_get_name_of_collections() {
		collector.visits_usccb_web_site(USCCB_BIBLE_URL);
		List<String> collectionNames = collector.gathers_the_names_of_the_collections();
		makeArrayOfAllBookNames(collectionNames, "public static final String[] NAMES_OF_COLLECTIONS = {");

		List<String> bookNamesByCollections = collector.gathers_names_of_books_ordered_by_collections();
		makeArrayOfAllBookNames(bookNamesByCollections,
				"public static final String[] NAMES_OF_BOOKS_BY_COLLECTIONS = {");
	}

	@Test
	public void should_compare_list_of_book_names_with_list_of_book_names_by_collections() {

		assertThat(BibleConstants.NAMES_OF_ALL_BOOKS.length,
				equalTo(BibleConstants.NAMES_OF_BOOKS_BY_COLLECTIONS.length));
		for (int i = 0; i < BibleConstants.NUMBER_OF_ALL_BOOKS; i++) {
			assertThat(BibleConstants.NAMES_OF_ALL_BOOKS[i], equalTo(BibleConstants.NAMES_OF_BOOKS_BY_COLLECTIONS[i]));
		}

		assertThat(BibleConstants.NUMBER_OF_ALL_BOOKS,
				equalTo(BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + BibleConstants.NUMBER_OF_WISDOM_BOOKS
						+ BibleConstants.NUMBER_OF_PROPHETIC_BOOKS + BibleConstants.NUMBER_OF_GOSPEL_BOOKS
						+ BibleConstants.NUMBER_OF_LETTER_BOOKS + BibleConstants.NUMBER_OF_CATHOLIC_LETTER_BOOKS));
	}

	@Test
	public void should_get_names_of_pentateuch_books() {
		List<String> pentateuchBookNames = Arrays.asList(Arrays.copyOfRange(
				BibleConstants.NAMES_OF_BOOKS_BY_COLLECTIONS, 0, BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS));
		assertThat(pentateuchBookNames.size(), equalTo(BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS));
		makeArrayOfAllBookNames(pentateuchBookNames, "public static final String[] NAMES_OF_PENTATEUCH_BOOKS = {");
		assertThat(BibleConstants.NAMES_OF_PENTATEUCH_BOOKS.length, equalTo(BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS));
	}

	@Test
	public void should_get_names_of_historical_books() {
		List<String> historicalBookNames = Arrays.asList(Arrays.copyOfRange(
				BibleConstants.NAMES_OF_BOOKS_BY_COLLECTIONS, BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS));
		assertThat(historicalBookNames.size(), equalTo(BibleConstants.NUMBER_OF_HISTORICAL_BOOKS));
		makeArrayOfAllBookNames(historicalBookNames, "public static final String[] NAMES_OF_HISTORICAL_BOOKS = {");
		assertThat(BibleConstants.NAMES_OF_HISTORICAL_BOOKS.length, equalTo(BibleConstants.NUMBER_OF_HISTORICAL_BOOKS));
	}

	@Test
	public void should_get_names_of_biblical_novellas_books() {
		List<String> biblical_novellasBookNames = Arrays
				.asList(Arrays.copyOfRange(BibleConstants.NAMES_OF_BOOKS_BY_COLLECTIONS,
						BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS,
						BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
								+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS));
		assertThat(biblical_novellasBookNames.size(), equalTo(BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS));
		makeArrayOfAllBookNames(biblical_novellasBookNames,
				"public static final String[] NAMES_OF_BIBLICAL_NOVELLAS_BOOKS = {");
		assertThat(BibleConstants.NAMES_OF_BIBLICAL_NOVELLAS_BOOKS.length,
				equalTo(BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS));
	}

	@Test
	public void should_get_names_of_wisdom_books() {
		List<String> wisdomBookNames = Arrays.asList(Arrays.copyOfRange(BibleConstants.NAMES_OF_BOOKS_BY_COLLECTIONS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + BibleConstants.NUMBER_OF_WISDOM_BOOKS));
		assertThat(wisdomBookNames.size(), equalTo(BibleConstants.NUMBER_OF_WISDOM_BOOKS));
		makeArrayOfAllBookNames(wisdomBookNames, "public static final String[] NAMES_OF_WISDOM_BOOKS = {");
		assertThat(BibleConstants.NAMES_OF_WISDOM_BOOKS.length, equalTo(BibleConstants.NUMBER_OF_WISDOM_BOOKS));
	}

	@Test
	public void should_get_names_of_prophetic_books() {
		List<String> propheticBookNames = Arrays.asList(Arrays.copyOfRange(BibleConstants.NAMES_OF_BOOKS_BY_COLLECTIONS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + BibleConstants.NUMBER_OF_WISDOM_BOOKS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + BibleConstants.NUMBER_OF_WISDOM_BOOKS
						+ BibleConstants.NUMBER_OF_PROPHETIC_BOOKS));
		assertThat(propheticBookNames.size(), equalTo(BibleConstants.NUMBER_OF_PROPHETIC_BOOKS));
		makeArrayOfAllBookNames(propheticBookNames, "public static final String[] NAMES_OF_PROPHETIC_BOOKS = {");
		assertThat(BibleConstants.NAMES_OF_PROPHETIC_BOOKS.length, equalTo(BibleConstants.NUMBER_OF_PROPHETIC_BOOKS));
	}

	@Test
	public void should_get_names_of_gospel_books() {
		List<String> gospelBookNames = Arrays.asList(Arrays.copyOfRange(BibleConstants.NAMES_OF_BOOKS_BY_COLLECTIONS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + BibleConstants.NUMBER_OF_WISDOM_BOOKS
						+ BibleConstants.NUMBER_OF_PROPHETIC_BOOKS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + BibleConstants.NUMBER_OF_WISDOM_BOOKS
						+ BibleConstants.NUMBER_OF_PROPHETIC_BOOKS + BibleConstants.NUMBER_OF_GOSPEL_BOOKS));
		assertThat(gospelBookNames.size(), equalTo(BibleConstants.NUMBER_OF_GOSPEL_BOOKS));
		makeArrayOfAllBookNames(gospelBookNames, "public static final String[] NAMES_OF_GOSPEL_BOOKS = {");
		assertThat(BibleConstants.NAMES_OF_GOSPEL_BOOKS.length, equalTo(BibleConstants.NUMBER_OF_GOSPEL_BOOKS));
	}

	@Test
	public void should_get_names_of_letter_books() {
		List<String> letterBookNames = Arrays.asList(Arrays.copyOfRange(BibleConstants.NAMES_OF_BOOKS_BY_COLLECTIONS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + BibleConstants.NUMBER_OF_WISDOM_BOOKS
						+ BibleConstants.NUMBER_OF_PROPHETIC_BOOKS + BibleConstants.NUMBER_OF_GOSPEL_BOOKS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + BibleConstants.NUMBER_OF_WISDOM_BOOKS
						+ BibleConstants.NUMBER_OF_PROPHETIC_BOOKS + BibleConstants.NUMBER_OF_GOSPEL_BOOKS
						+ BibleConstants.NUMBER_OF_LETTER_BOOKS));
		assertThat(letterBookNames.size(), equalTo(BibleConstants.NUMBER_OF_LETTER_BOOKS));
		makeArrayOfAllBookNames(letterBookNames, "public static final String[] NAMES_OF_LETTER_BOOKS = {");
		assertThat(BibleConstants.NAMES_OF_LETTER_BOOKS.length, equalTo(BibleConstants.NUMBER_OF_LETTER_BOOKS));
	}

	@Test
	public void should_get_names_of_catholic_letter_books() {
		List<String> catholicLetterBookNames = Arrays.asList(Arrays.copyOfRange(
				BibleConstants.NAMES_OF_BOOKS_BY_COLLECTIONS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + BibleConstants.NUMBER_OF_WISDOM_BOOKS
						+ BibleConstants.NUMBER_OF_PROPHETIC_BOOKS + BibleConstants.NUMBER_OF_GOSPEL_BOOKS
						+ BibleConstants.NUMBER_OF_LETTER_BOOKS,
				BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS + BibleConstants.NUMBER_OF_HISTORICAL_BOOKS
						+ BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + BibleConstants.NUMBER_OF_WISDOM_BOOKS
						+ BibleConstants.NUMBER_OF_PROPHETIC_BOOKS + BibleConstants.NUMBER_OF_GOSPEL_BOOKS
						+ BibleConstants.NUMBER_OF_LETTER_BOOKS + BibleConstants.NUMBER_OF_CATHOLIC_LETTER_BOOKS));
		assertThat(catholicLetterBookNames.size(), equalTo(BibleConstants.NUMBER_OF_CATHOLIC_LETTER_BOOKS));
		makeArrayOfAllBookNames(catholicLetterBookNames,
				"public static final String[] NAMES_OF_CATHOLIC_LETTER_BOOKS = {");
		assertThat(BibleConstants.NAMES_OF_CATHOLIC_LETTER_BOOKS.length,
				equalTo(BibleConstants.NUMBER_OF_CATHOLIC_LETTER_BOOKS));
	}

	@Test
	public void should_get_number_of_chapters_by_books() {
		collector.visits_usccb_web_site(USCCB_BIBLE_URL);
		Hashtable<String, Integer> numberOfChaptersByBookNameTable = new Hashtable<String, Integer>();

		for (String bookName : BibleConstants.NAMES_OF_ALL_BOOKS) {
			numberOfChaptersByBookNameTable.put(bookName, 0);
		}
		collector.get_number_of_chapters_by(numberOfChaptersByBookNameTable);
		numberOfChaptersByBookNameTable.keySet().stream().forEach(bookName -> {
			logger.info("{} has {} chapters", bookName, numberOfChaptersByBookNameTable.get(bookName));
		});

		StringBuilder sb = new StringBuilder();
		int numberOfAllChaptersForAllBooks = 0;

		for (String bookName : BibleConstants.NAMES_OF_ALL_BOOKS) {
			sb.append(String.format("public final Integer NUMBER_OF_CHAPTERS_FOR_BOOK_OF_%s = %d;",
					underscoreUppercase(bookName), numberOfChaptersByBookNameTable.get(bookName))).append('\n');
			numberOfAllChaptersForAllBooks += numberOfChaptersByBookNameTable.get(bookName);
		}

		sb.append("public final Integer NUMBER_OF_ALL_CHAPTERS_FOR_ALL_BOOKS = ").append(numberOfAllChaptersForAllBooks)
				.append(";\n");
		logger.info("generated code:\n{}", sb);

	}

	private Object underscoreUppercase(String bookName) {
		return bookName.toUpperCase().trim().replaceAll(" ", "_");
	}

	private void makeArrayOfAllBookNames(List<String> bookNames, String constantName) {
		StringBuilder sb = new StringBuilder(constantName).append('\n');
		bookNames.stream().forEach(bookName -> {
			sb.append("\t").append('"').append(bookName).append('"').append(",\n");
		});
		sb.deleteCharAt(sb.length() - 2);
		sb.append("};\n");
		logger.info("generated code as: \n {}", sb);
	}
}
