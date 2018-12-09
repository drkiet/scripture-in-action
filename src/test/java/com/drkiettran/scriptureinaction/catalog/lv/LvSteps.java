package com.drkiettran.scriptureinaction.catalog.lv;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LvSteps {
	private static final Logger logger = LoggerFactory.getLogger(LvSteps.class);

	private LvPage lvPage;

	public List<String> gathers_the_names_of_the_books(String lvUrl, int numberBooks) {
		return lvPage.getAllBookNames(lvUrl, numberBooks);
	}

	public int[] get_number_of_chapters_by_book_name(String lvBibleUrl, String[] namesOfAllBooks) {
		logger.info("# of chapters {}\n {}", namesOfAllBooks.length, namesOfAllBooks);
		return lvPage.getNumberOfChaptersByBookName(lvBibleUrl, namesOfAllBooks);
	}

	public List<Integer[]> get_number_of_verses_by_chapter_by_book(String lvBibleUrl, int[] numberOfChaptersByBookName,
			String[] namesOfAllBooks) {
		logger.info("# of verses {}\n {}", namesOfAllBooks.length, namesOfAllBooks);
		return lvPage.getNumberOfVersesByChaptersByBookName(lvBibleUrl, numberOfChaptersByBookName, namesOfAllBooks);
	}

}
