package com.drkiettran.scriptureinaction.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.model.BibleBook;

public class BibleRepositoryViaFileTest {
	private static final Logger logger = LoggerFactory.getLogger(BibleRepositoryViaFileTest.class);
	private String translation;
	private String bookName;

	@Test
	public void shouldLoadBook() {
		translation = BibleBook.NAB;
		bookName = "Genesis";
		BibleRepositoryViaFile repo = new BibleRepositoryViaFile();
		BibleBook book = repo.load(translation, bookName);
		assertThat(book.getNumberOfChapters(), equalTo(50));
		assertThat(book.getName(), equalTo("Genesis"));
		assertThat(book.getNumberOfVersesByChapter().length, equalTo(50));
		assertThat(book.getNumberOfVersesByChapter()[0], equalTo(31));
		assertThat(book.getNumberOfVersesByChapter()[49], equalTo(26));
		logger.info("book-summary:\n{}", book.logBookSummary());
	}
}
