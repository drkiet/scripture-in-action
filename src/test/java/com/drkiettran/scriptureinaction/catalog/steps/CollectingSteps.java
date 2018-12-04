package com.drkiettran.scriptureinaction.catalog.steps;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.pages.AbbrevPage;
import com.drkiettran.scriptureinaction.catalog.pages.BooksOfTheBiblePage;
import com.drkiettran.scriptureinaction.model.UniqueId;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class CollectingSteps extends ScenarioSteps {
	private static final Logger logger = LoggerFactory.getLogger(CollectingSteps.class);

	private static final long serialVersionUID = 6562660228054550177L;
	private BooksOfTheBiblePage booksOfTheBiblePage;

	private AbbrevPage abbrevPage;

	@Step("A scripture collector gathers the names of the books")
	public List<String> gathers_the_names_of_the_books() {
		return booksOfTheBiblePage.getAllBookNames();
	}

	@Step("A scripture collector visits the usccb website at {0}")
	public void visits_usccb_web_site(String booksOfTheBibleUrl) {
		booksOfTheBiblePage.visit(booksOfTheBibleUrl);
	}

	public void should_have_this_number_of_book_names(int i) {
		// TODO Auto-generated method stub

	}

	@Step("A scripture collector gather the names of the collections")
	public List<String> gathers_the_names_of_the_collections() {
		return booksOfTheBiblePage.getAllCollectionNames();
	}

	@Step("A scripture collector gathers names of books order by collections")
	public List<String> gathers_names_of_books_ordered_by_collections() {
		return booksOfTheBiblePage.getAllBookNamesByCollections();
	}

	@Step("A scripture collector get number of chapters by {0}")
	public int[] get_number_of_chapters_by_book_name(String[] namesOfAllBooks) {
		logger.info("# of books {}\n {}", namesOfAllBooks.length, namesOfAllBooks);
		return booksOfTheBiblePage.getNumberOfChaptersByBookName(namesOfAllBooks);
	}

	@Step
	public List<Integer[]> get_number_of_verses_by_chapter_by_book_name(String usccBibleContentUrl, String[] bookNames,
			int[] numberOfChaptersByBookName) {
		return booksOfTheBiblePage.getNumberOfVersesByChapterByBookName(usccBibleContentUrl, bookNames,
				numberOfChaptersByBookName);
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
}
