package com.drkiettran.scriptureinaction.catalog.steps;

import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class CollectingSteps extends ScenarioSteps {

	private static final long serialVersionUID = 6562660228054550177L;
	private BooksOfTheBiblePage booksOfTheBiblePage;

	@Step("A scripture collector gathers the names of the books")
	public List<String> gathers_the_names_of_the_books() {
		return booksOfTheBiblePage.getAllBookNames();
	}

	@Step("A scripture collector visit the usccb website at {0}")
	public void visits_usccb_web_site(String booksOfTheBibleUrl) {
		booksOfTheBiblePage.visit(booksOfTheBibleUrl);
	}

	public void should_have_this_number_of_book_names(int i) {
		// TODO Auto-generated method stub

	}

	public List<String> gathers_the_names_of_the_collections() {
		return booksOfTheBiblePage.getAllCollectionNames();
	}

	public List<String> gathers_names_of_books_ordered_by_collections() {
		return booksOfTheBiblePage.getAllBookNamesByCollections();
	}
}
