package com.drkiettran.scriptureinaction.catalog.rsv;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.pages.RsvPage;

public class RsvSteps {
	public static final Logger logger = LoggerFactory.getLogger(RsvSteps.class);

	private RsvPage rsvPage;

	public List<String> gathers_the_names_of_the_books(String rsvBibleUrl, int numberOfAllBooks) {
		return rsvPage.getAllBookNames(rsvBibleUrl, numberOfAllBooks);
	}

	public void get_number_of_chapters_by_book_name(String rsvBibleUrl, String[] namesOfAllBooks,
			List<Integer> numChaptersByBook, List<Integer[]> numVersesByChapterByBook) {
		logger.info("# of chapters {}\n {}", namesOfAllBooks.length, namesOfAllBooks);
		rsvPage.getNumChaptersNumVersesAndText(rsvBibleUrl, namesOfAllBooks, numChaptersByBook,
				numVersesByChapterByBook);
		return;
	}

}
