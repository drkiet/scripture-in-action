package com.drkiettran.scriptureinaction.catalog.steps;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class CollectingSteps extends ScenarioSteps {
	private static final Logger logger = LoggerFactory.getLogger(CollectingSteps.class);

	private static final long serialVersionUID = 6562660228054550177L;
	private BooksOfTheBiblePage booksOfTheBiblePage;

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
	public void get_number_of_chapters_by(Hashtable<String, Integer> numberOfChaptersByBookNames) {
		logger.info("# of books {}\n {}", numberOfChaptersByBookNames.size(), numberOfChaptersByBookNames);
		booksOfTheBiblePage.getNumberOfChaptersByBookName(numberOfChaptersByBookNames);
	}
}
