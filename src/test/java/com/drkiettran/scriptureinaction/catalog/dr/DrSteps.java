package com.drkiettran.scriptureinaction.catalog.dr;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.thucydides.core.annotations.Step;

public class DrSteps {
	private static final Logger logger = LoggerFactory.getLogger(DrSteps.class);
	private DrPage drPage;

	@Step
	public List<String> gathers_the_names_of_the_books(String url, int numberOfAllBooks) {
		return drPage.getAllBookNames(url, numberOfAllBooks);
	}

	@Step
	public int[] get_number_of_chapters_by_book_name(String url, String[] namesOfAllBooks) {
		logger.info("# of chapters {}\n {}", namesOfAllBooks.length, namesOfAllBooks);
		return drPage.getNumberOfChaptersByBookName(url, namesOfAllBooks);
	}

	public List<Integer[]> get_number_of_verses_by_chapter_by_book(String url, int[] numberOfChaptersByBookName,
			String[] namesOfAllBooks) {
		logger.info("# of verses {}\n {}", namesOfAllBooks.length, namesOfAllBooks);
		return drPage.getNumberOfVersesByChaptersByBookName(url, numberOfChaptersByBookName, namesOfAllBooks);
	}

}
