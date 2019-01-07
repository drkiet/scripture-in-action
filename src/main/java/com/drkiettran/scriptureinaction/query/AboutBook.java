package com.drkiettran.scriptureinaction.query;

import com.drkiettran.scriptureinaction.model.BibleBook;
import com.drkiettran.scriptureinaction.repository.BibleRepository;

public interface AboutBook {

	int getNumChapters(String translation, String bookName);

	public void setRepo(BibleRepository repo);

	int[] getNumVersesByChapter(String translation, String bookName);

	public BibleBook getBook();
}
