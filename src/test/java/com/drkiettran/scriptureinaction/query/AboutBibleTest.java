package com.drkiettran.scriptureinaction.query;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsNot.not;

import java.util.List;

import org.junit.Test;

import com.drkiettran.scriptureinaction.model.BibleBook;

public class AboutBibleTest {
	@Test
	public void getListOfBooks() {
		AboutBible aboutBible = new AboutBible();
		List<String> bookNames = aboutBible.getBookNames();
		assertThat(bookNames, notNullValue());
		assertThat(bookNames, not(empty()));
		assertThat(bookNames.size(), equalTo(73));
	}

	@Test
	public void getListOfTranslation() {
		AboutBible aboutBible = new AboutBible();
		List<String> translations = aboutBible.getTranslations();
		assertThat(translations, notNullValue());
		assertThat(translations, not(empty()));
		assertThat(translations.size(), equalTo(4));
	}

	@Test
	public void getBibleBook() {
		AboutBible aboutBible = new AboutBible();

		String bookName = aboutBible.getBookNames().get(0);
		String translation = aboutBible.getTranslations().get(0);
		System.out.println("loading: " + translation + ":" + bookName);

		AboutBook aboutBook = aboutBible.getAboutBook(translation, bookName);
		BibleBook book = aboutBook.getBook();
		assertThat(aboutBook, notNullValue());
		assertThat(book, notNullValue());
		assertThat(book.getNumberOfChapters(), equalTo(50));
	}
}
