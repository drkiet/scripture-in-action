package com.drkiettran.scriptureinaction.query;

import java.util.Arrays;
import java.util.List;

import com.drkiettran.scriptureinaction.model.BibleBook;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;

public class AboutBible {

	private static final String[] TRANSLATIONS = { BibleBook.NAB, BibleBook.DR, BibleBook.RSV, BibleBook.LV };

	public List<String> getBookNames() {
		return Arrays.asList(NewAmerican.NAMES_OF_ALL_BOOKS);
	}

	public List<String> getTranslations() {
		return Arrays.asList(TRANSLATIONS);
	}

	public AboutBook getAboutBook(String translation, String bookName) {
		switch (translation) {
		case BibleBook.NAB:
			return new AboutBookNab(translation, bookName);
		case BibleBook.RSV:
			return null;
		case BibleBook.DR:
			return null;
		case BibleBook.LV:
			return null;
		default:
			return null;
		}
	}

}
