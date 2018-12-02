package com.drkiettran.scriptureinaction.catalog;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.BibleConstants;
import com.drkiettran.scriptureinaction.catalog.steps.CollectingSteps;
import com.drkiettran.scriptureinaction.model.UniqueId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectingBookInfo {
	private static final Logger logger = LoggerFactory.getLogger(CollectingBookInfo.class);
	private static final String USCCB_BIBLE_URL = "http://www.usccb.org/bible/books-of-the-bible/index.cfm";
	private static final String USCC_BIBLE_CONTENT_URL = "http://www.usccb.org/bible";
	private static final String CATHOLIC_RESOURCES_ABBREVIATIONS_URL = "http://catholic-resources.org/Bible/Abbreviations-Abreviaciones.htm";

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
	public void should_get_name_of_collections() {
		collector.visits_usccb_web_site(USCCB_BIBLE_URL);
		List<String> collectionNames = collector.gathers_the_names_of_the_collections();
		makeArrayOfAllBookNames(collectionNames, "public static final String[] NAMES_OF_COLLECTIONS = {");

		List<String> bookNamesByCollections = collector.gathers_names_of_books_ordered_by_collections();
		makeArrayOfAllBookNames(bookNamesByCollections,
				"public static final String[] NAMES_OF_BOOKS_BY_COLLECTIONS = {");
	}

	@Test
	public void should_get_number_of_chapters_by_books() {
		collector.visits_usccb_web_site(USCCB_BIBLE_URL);
		StringBuilder numberChaptersArrayVar = new StringBuilder(
				"public static final int[] NUMBER_OF_CHAPTERS_BY_BOOK_NAME = {\n");
		StringBuilder numberChaptersVar = new StringBuilder();
		int[] numberOfChaptersByBookName = collector
				.get_number_of_chapters_by_book_name(BibleConstants.NAMES_OF_ALL_BOOKS);

		int numberOfAllChaptersForAllBooks = 0;

		for (int i = 0; i < BibleConstants.NAMES_OF_ALL_BOOKS.length; i++) {
			String bookName = BibleConstants.NAMES_OF_ALL_BOOKS[i];
			logger.info("{} has {} chapters", bookName, numberOfChaptersByBookName[i]);
			numberChaptersArrayVar.append('\t').append(numberOfChaptersByBookName[i]).append(",\n");
			numberChaptersVar
					.append(String.format("public static final Integer NUMBER_OF_CHAPTERS_FOR_BOOK_OF_%s = %d;\n",
							underscoreUppercase(bookName), numberOfChaptersByBookName[i]));
			numberOfAllChaptersForAllBooks += numberOfChaptersByBookName[i];
		}

		numberChaptersArrayVar.deleteCharAt(numberChaptersArrayVar.length() - 2).append("};\n");
		numberChaptersVar.append("public static final Integer NUMBER_OF_ALL_CHAPTERS_FOR_ALL_BOOKS = ")
				.append(numberOfAllChaptersForAllBooks).append(";\n");

		logger.info("generated code:\n{}", numberChaptersArrayVar);
		logger.info("generated code:\n{}", numberChaptersVar);
	}

	@Test
	public void should_get_number_of_verses_by_chapter_by_book() {
		List<Integer[]> versesPerBooks = collector.get_number_of_verses_by_chapter_by_book_name(USCC_BIBLE_CONTENT_URL,
				BibleConstants.NAMES_OF_ALL_BOOKS, BibleConstants.NUMBER_OF_CHAPTERS_BY_BOOK_NAME);
		StringBuilder sb = new StringBuilder(
				"public static final int[][] NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME = {\n");
		int numberOfAllVersesForAllBooks = 0;

		for (int bookIdx = 0; bookIdx < BibleConstants.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			Integer[] versesPerBook = versesPerBooks.get(bookIdx);
			sb.append("/** ").append(BibleConstants.NAMES_OF_ALL_BOOKS[bookIdx]).append(" **/ {");

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

	@Test
	public void should_get_abbreviations_for_all_book_names() {
		List<String> abbreviations = collector.get_abbreviations_names(CATHOLIC_RESOURCES_ABBREVIATIONS_URL,
				BibleConstants.NAMES_OF_ALL_BOOKS);
		assertThat(abbreviations.size(), equalTo(BibleConstants.NUMBER_OF_ALL_BOOKS));

		StringBuilder sb = new StringBuilder("private static final String[] ABBREVIATIONS_OF_ALL_BOOKS = {\n");

		for (int bookIdx = 0; bookIdx < BibleConstants.NUMBER_OF_ALL_BOOKS; bookIdx++) {
			String abbrev = abbreviations.get(bookIdx);
			String bookName = BibleConstants.NAMES_OF_ALL_BOOKS[bookIdx];

			abbrev = abbrev.replaceAll(" or", ",");
			sb.append("/** ").append(bookName).append(" **/ ").append('"').append(abbrev).append('"').append(",\n");
		}

		sb.deleteCharAt(sb.length() - 2);
		sb.append("};");
		logger.info("generated code:\n{}", sb);

	}

	// **** Non SERENITY use tests ****
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
	public void should_verify_number_chapters_by_name_equal_total_chapters() {
		assertThat(IntStream.of(BibleConstants.NUMBER_OF_CHAPTERS_BY_BOOK_NAME).sum(),
				equalTo(BibleConstants.NUMBER_OF_ALL_CHAPTERS_FOR_ALL_BOOKS));
	}

	@Test
	public void should_verify_number_verses_by_chapter_by_book_name_equal_total_verses() {
		int totalVerses = 0;

		for (int i = 0; i < BibleConstants.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME.length; i++) {
			totalVerses += IntStream.of(BibleConstants.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME[i]).sum();
		}

		assertThat(totalVerses, equalTo(BibleConstants.NUMBER_OF_ALL_VERSES_FOR_ALL_BOOKS));

	}

	/**
	 * Bible - locator:
	 * 
	 * <code>
	 * '%s %d:%d' 
	 * 
	 * where %s is book name (abbrev)
	 *       %d is chapter number
	 *       %d is verse number
	 * 
	 * </code>
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	public void should_get_unique_id() throws JsonProcessingException {
		String bookName = BibleConstants.NAMES_OF_ALL_BOOKS[2];
		int chapterNo = 2, verseNo = 3;

		UniqueId id = collector.make_unique_id_(bookName, chapterNo, verseNo);
		assertThat(id.getBookName(), equalTo(bookName));
		assertThat(id.getChapterNo(), equalTo(chapterNo));
		assertThat(id.getVerseNo(), equalTo(verseNo));

		ObjectMapper om = new ObjectMapper();
		logger.info("unique id: {}", om.writerWithDefaultPrettyPrinter().writeValueAsString(id));
	}

	// *** Supporting code ***
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
