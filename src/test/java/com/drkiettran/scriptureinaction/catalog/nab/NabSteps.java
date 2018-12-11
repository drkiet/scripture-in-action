package com.drkiettran.scriptureinaction.catalog.nab;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.pages.AbbrevPage;
import com.drkiettran.scriptureinaction.model.UniqueId;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class NabSteps extends ScenarioSteps {
	private static final Logger logger = LoggerFactory.getLogger(NabSteps.class);

	private static final long serialVersionUID = 6562660228054550177L;
	private NabPage nabPage;

	private AbbrevPage abbrevPage;

	@Step("A scripture collector gathers the names of the books")
	public List<String> gathers_the_names_of_the_books() {
		return nabPage.getAllBookNames();
	}

	@Step("A scripture collector visits the usccb website at {0}")
	public void visits_usccb_web_site(String booksOfTheBibleUrl) {
		nabPage.visit(booksOfTheBibleUrl);
	}

	@Step("A scripture collector gather the names of the collections")
	public List<String> gathers_the_names_of_the_collections() {
		return nabPage.getAllCollectionNames();
	}

	@Step("A scripture collector gathers names of books order by collections")
	public List<String> gathers_names_of_books_ordered_by_collections() {
		return nabPage.getAllBookNamesByCollections();
	}

	@Step("A scripture collector get number of chapters by {0}")
	public int[] get_number_of_chapters_by_book_name(String url, String[] namesOfAllBooks) {
		logger.info("# of books {}\n {}", namesOfAllBooks.length, namesOfAllBooks);
		return nabPage.getNumberOfChaptersByBookName(url, namesOfAllBooks);
	}

	@Step
	public List<Integer[]> get_number_of_verses_by_chapter_by_book_name(String usccBibleContentUrl, String[] bookNames,
			int[] numberOfChaptersByBookName) {
		return nabPage.getNumberOfVersesByChapterByBookName(usccBibleContentUrl, bookNames, numberOfChaptersByBookName);
	}

	@Step
	public UniqueId make_unique_id_(String bookName, int chapterNo, int verseNo) {
		UniqueId id = new UniqueId();
		id.setBookName(bookName);
		id.setChapterNo(chapterNo);
		id.setVerseNo(verseNo);
		id.setPubDate(new Date());
		id.setId(UUID.randomUUID().toString());
		return id;
	}

	@Step("A collector gets the abbreviation names (other names) of books")
	public List<String> get_abbreviations_names(String catholicResourcesAbbreviationsUrl, String[] namesOfAllBooks) {
		return abbrevPage.getAbbreviationsInEnglish(catholicResourcesAbbreviationsUrl, namesOfAllBooks);
	}
 
	public String collect_preface(String usccbBibleUrl) {
		return nabPage.getPreface(usccbBibleUrl);
	}

	public String collect_the_pentateuch(String usccbBibleUrl) {
		return nabPage.getThePentateuch(usccbBibleUrl);
	}

	public String collect_biblical_novellas(String usccbBibleUrl) {
		return nabPage.getBiblicalNovellas(usccbBibleUrl);
	}

	public String collect_historical_introduction(String usccbBibleUrl) {
		return nabPage.getHistoricalIntroduction(usccbBibleUrl);
	}

	public String collect_wisdom_books(String usccbBibleUrl) {
		return nabPage.getWisdomBooks(usccbBibleUrl);
	}

	public String collect_prophetic_books(String usccbBibleUrl) {
		return nabPage.getPropheticBooks(usccbBibleUrl);
	}

	public String collect_new_testament(String usccbBibleUrl) {
		return nabPage.getNewTestament(usccbBibleUrl);
	}

	public String collect_gospels_introduction(String usccbBibleUrl) {
		return nabPage.getGospelsIntroduction(usccbBibleUrl);
	}

	public String collect_new_testament_letters(String usccbBibleUrl) {
		return nabPage.getNewTestamentLetters(usccbBibleUrl);
	}

	public String collect_catholic_letters(String usccbBibleUrl) {
		return nabPage.getCatholicLetters(usccbBibleUrl);
	}

	public List<String> collect_introductions(String usccbBibleUrl, String[] bookNames) {
		return nabPage.getIntroductions(usccbBibleUrl, bookNames);
	}

}
