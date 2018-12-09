package com.drkiettran.scriptureinaction.catalog;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.nab.NabSteps;
import com.drkiettran.scriptureinaction.catalog.util.TestUtils;
import com.drkiettran.scriptureinaction.model.UniqueId;
import com.drkiettran.scriptureinaction.model.constants.DouayRheims;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectingBookInfo {
	private static final Logger logger = LoggerFactory.getLogger(CollectingBookInfo.class);

	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	NabSteps collector;

	// **** Non SERENITY use tests ****
	@Test
	public void should_get_the_names_of_the_ot_book() {
		List<String> otBookNames = Arrays
				.asList(Arrays.copyOfRange(NewAmerican.NAMES_OF_ALL_BOOKS, 0, NewAmerican.NUMBER_OF_OT_BOOKS));
		assertThat(otBookNames.size(), equalTo(NewAmerican.NUMBER_OF_OT_BOOKS));
		TestUtils.makeArrayOfAllBookNames(otBookNames, "public static final String[] NAMES_OF_OT_BOOKS = {");
	}

	@Test
	public void should_get_the_names_of_the_nt_book() {
		List<String> otBookNames = Arrays.asList(Arrays.copyOfRange(NewAmerican.NAMES_OF_ALL_BOOKS,
				NewAmerican.NUMBER_OF_OT_BOOKS, NewAmerican.NUMBER_OF_ALL_BOOKS));
		assertThat(otBookNames.size(), equalTo(NewAmerican.NUMBER_OF_NT_BOOKS));
		TestUtils.makeArrayOfAllBookNames(otBookNames, "public static final String[] NAMES_OF_NT_BOOKS = {");
	}

	@Test
	public void should_compare_list_of_book_names_with_list_of_book_names_by_collections() {

		assertThat(NewAmerican.NAMES_OF_ALL_BOOKS.length, equalTo(NewAmerican.NAMES_OF_BOOKS_BY_COLLECTIONS.length));
		for (int i = 0; i < NewAmerican.NUMBER_OF_ALL_BOOKS; i++) {
			assertThat(NewAmerican.NAMES_OF_ALL_BOOKS[i], equalTo(NewAmerican.NAMES_OF_BOOKS_BY_COLLECTIONS[i]));
		}

		assertThat(NewAmerican.NUMBER_OF_ALL_BOOKS,
				equalTo(NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
						+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + NewAmerican.NUMBER_OF_WISDOM_BOOKS
						+ NewAmerican.NUMBER_OF_PROPHETIC_BOOKS + NewAmerican.NUMBER_OF_GOSPEL_BOOKS
						+ NewAmerican.NUMBER_OF_LETTER_BOOKS + NewAmerican.NUMBER_OF_CATHOLIC_LETTER_BOOKS));
	}

	@Test
	public void should_get_names_of_pentateuch_books() {
		List<String> pentateuchBookNames = Arrays.asList(Arrays.copyOfRange(NewAmerican.NAMES_OF_BOOKS_BY_COLLECTIONS,
				0, NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS));
		assertThat(pentateuchBookNames.size(), equalTo(NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS));
		TestUtils.makeArrayOfAllBookNames(pentateuchBookNames,
				"public static final String[] NAMES_OF_PENTATEUCH_BOOKS = {");
		assertThat(NewAmerican.NAMES_OF_PENTATEUCH_BOOKS.length, equalTo(NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS));
	}

	@Test
	public void should_get_names_of_historical_books() {
		List<String> historicalBookNames = Arrays.asList(
				Arrays.copyOfRange(NewAmerican.NAMES_OF_BOOKS_BY_COLLECTIONS, NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS,
						NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS));
		assertThat(historicalBookNames.size(), equalTo(NewAmerican.NUMBER_OF_HISTORICAL_BOOKS));
		TestUtils.makeArrayOfAllBookNames(historicalBookNames,
				"public static final String[] NAMES_OF_HISTORICAL_BOOKS = {");
		assertThat(NewAmerican.NAMES_OF_HISTORICAL_BOOKS.length, equalTo(NewAmerican.NUMBER_OF_HISTORICAL_BOOKS));
	}

	@Test
	public void should_get_names_of_biblical_novellas_books() {
		List<String> biblical_novellasBookNames = Arrays
				.asList(Arrays.copyOfRange(NewAmerican.NAMES_OF_BOOKS_BY_COLLECTIONS,
						NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS,
						NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
								+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS));
		assertThat(biblical_novellasBookNames.size(), equalTo(NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS));
		TestUtils.makeArrayOfAllBookNames(biblical_novellasBookNames,
				"public static final String[] NAMES_OF_BIBLICAL_NOVELLAS_BOOKS = {");
		assertThat(NewAmerican.NAMES_OF_BIBLICAL_NOVELLAS_BOOKS.length,
				equalTo(NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS));
	}

	@Test
	public void should_get_names_of_wisdom_books() {
		List<String> wisdomBookNames = Arrays.asList(Arrays.copyOfRange(NewAmerican.NAMES_OF_BOOKS_BY_COLLECTIONS,
				NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
						+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS,
				NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
						+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + NewAmerican.NUMBER_OF_WISDOM_BOOKS));
		assertThat(wisdomBookNames.size(), equalTo(NewAmerican.NUMBER_OF_WISDOM_BOOKS));
		TestUtils.makeArrayOfAllBookNames(wisdomBookNames, "public static final String[] NAMES_OF_WISDOM_BOOKS = {");
		assertThat(NewAmerican.NAMES_OF_WISDOM_BOOKS.length, equalTo(NewAmerican.NUMBER_OF_WISDOM_BOOKS));
	}

	@Test
	public void should_get_names_of_prophetic_books() {
		List<String> propheticBookNames = Arrays.asList(Arrays.copyOfRange(NewAmerican.NAMES_OF_BOOKS_BY_COLLECTIONS,
				NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
						+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + NewAmerican.NUMBER_OF_WISDOM_BOOKS,
				NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
						+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + NewAmerican.NUMBER_OF_WISDOM_BOOKS
						+ NewAmerican.NUMBER_OF_PROPHETIC_BOOKS));
		assertThat(propheticBookNames.size(), equalTo(NewAmerican.NUMBER_OF_PROPHETIC_BOOKS));
		TestUtils.makeArrayOfAllBookNames(propheticBookNames,
				"public static final String[] NAMES_OF_PROPHETIC_BOOKS = {");
		assertThat(NewAmerican.NAMES_OF_PROPHETIC_BOOKS.length, equalTo(NewAmerican.NUMBER_OF_PROPHETIC_BOOKS));
	}

	@Test
	public void should_get_names_of_gospel_books() {
		List<String> gospelBookNames = Arrays.asList(Arrays.copyOfRange(NewAmerican.NAMES_OF_BOOKS_BY_COLLECTIONS,
				NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
						+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + NewAmerican.NUMBER_OF_WISDOM_BOOKS
						+ NewAmerican.NUMBER_OF_PROPHETIC_BOOKS,
				NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
						+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + NewAmerican.NUMBER_OF_WISDOM_BOOKS
						+ NewAmerican.NUMBER_OF_PROPHETIC_BOOKS + NewAmerican.NUMBER_OF_GOSPEL_BOOKS));
		assertThat(gospelBookNames.size(), equalTo(NewAmerican.NUMBER_OF_GOSPEL_BOOKS));
		TestUtils.makeArrayOfAllBookNames(gospelBookNames, "public static final String[] NAMES_OF_GOSPEL_BOOKS = {");
		assertThat(NewAmerican.NAMES_OF_GOSPEL_BOOKS.length, equalTo(NewAmerican.NUMBER_OF_GOSPEL_BOOKS));
	}

	@Test
	public void should_get_names_of_letter_books() {
		List<String> letterBookNames = Arrays.asList(Arrays.copyOfRange(NewAmerican.NAMES_OF_BOOKS_BY_COLLECTIONS,
				NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
						+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + NewAmerican.NUMBER_OF_WISDOM_BOOKS
						+ NewAmerican.NUMBER_OF_PROPHETIC_BOOKS + NewAmerican.NUMBER_OF_GOSPEL_BOOKS,
				NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
						+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + NewAmerican.NUMBER_OF_WISDOM_BOOKS
						+ NewAmerican.NUMBER_OF_PROPHETIC_BOOKS + NewAmerican.NUMBER_OF_GOSPEL_BOOKS
						+ NewAmerican.NUMBER_OF_LETTER_BOOKS));
		assertThat(letterBookNames.size(), equalTo(NewAmerican.NUMBER_OF_LETTER_BOOKS));
		TestUtils.makeArrayOfAllBookNames(letterBookNames, "public static final String[] NAMES_OF_LETTER_BOOKS = {");
		assertThat(NewAmerican.NAMES_OF_LETTER_BOOKS.length, equalTo(NewAmerican.NUMBER_OF_LETTER_BOOKS));
	}

	@Test
	public void should_get_names_of_catholic_letter_books() {
		List<String> catholicLetterBookNames = Arrays
				.asList(Arrays.copyOfRange(NewAmerican.NAMES_OF_BOOKS_BY_COLLECTIONS,
						NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
								+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + NewAmerican.NUMBER_OF_WISDOM_BOOKS
								+ NewAmerican.NUMBER_OF_PROPHETIC_BOOKS + NewAmerican.NUMBER_OF_GOSPEL_BOOKS
								+ NewAmerican.NUMBER_OF_LETTER_BOOKS,
						NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS + NewAmerican.NUMBER_OF_HISTORICAL_BOOKS
								+ NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS + NewAmerican.NUMBER_OF_WISDOM_BOOKS
								+ NewAmerican.NUMBER_OF_PROPHETIC_BOOKS + NewAmerican.NUMBER_OF_GOSPEL_BOOKS
								+ NewAmerican.NUMBER_OF_LETTER_BOOKS + NewAmerican.NUMBER_OF_CATHOLIC_LETTER_BOOKS));
		assertThat(catholicLetterBookNames.size(), equalTo(NewAmerican.NUMBER_OF_CATHOLIC_LETTER_BOOKS));
		TestUtils.makeArrayOfAllBookNames(catholicLetterBookNames,
				"public static final String[] NAMES_OF_CATHOLIC_LETTER_BOOKS = {");
		assertThat(NewAmerican.NAMES_OF_CATHOLIC_LETTER_BOOKS.length,
				equalTo(NewAmerican.NUMBER_OF_CATHOLIC_LETTER_BOOKS));
	}

	@Test
	public void should_verify_number_chapters_by_name_equal_total_chapters() {
		assertThat(IntStream.of(NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME).sum(),
				equalTo(NewAmerican.NUMBER_OF_ALL_CHAPTERS_FOR_ALL_BOOKS));
	}

	@Test
	public void should_verify_number_verses_by_chapter_by_book_name_equal_total_verses() {
		int totalVerses = 0;

		for (int i = 0; i < NewAmerican.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME.length; i++) {
			totalVerses += IntStream.of(NewAmerican.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME[i]).sum();
		}

		assertThat(totalVerses, equalTo(NewAmerican.NUMBER_OF_ALL_VERSES_FOR_ALL_BOOKS));

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
		String bookName = NewAmerican.NAMES_OF_ALL_BOOKS[2];
		int chapterNo = 2, verseNo = 3;

		UniqueId id = collector.make_unique_id_(bookName, chapterNo, verseNo);
		assertThat(id.getBookName(), equalTo(bookName));
		assertThat(id.getChapterNo(), equalTo(chapterNo));
		assertThat(id.getVerseNo(), equalTo(verseNo));

		ObjectMapper om = new ObjectMapper();
		logger.info("unique id: {}", om.writerWithDefaultPrettyPrinter().writeValueAsString(id));
	}

	// *** Supporting code ***

}
