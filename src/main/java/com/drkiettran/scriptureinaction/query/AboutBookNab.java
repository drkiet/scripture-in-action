package com.drkiettran.scriptureinaction.query;

import org.springframework.beans.factory.annotation.Autowired;

import com.drkiettran.scriptureinaction.model.BibleBook;
import com.drkiettran.scriptureinaction.repository.BibleRepository;

public class AboutBookNab implements AboutBook {

	@Autowired
	private BibleRepository repo;

	@Override
	public int getNumChapters(String translation, String bookName) {
		BibleBook book = repo.load(translation, bookName);
		return book.getNumberOfChapters();
	}

	public void setRepo(BibleRepository repo) {
		this.repo = repo;
	}

	@Override
	public int[] getNumVersesByChapter(String translation, String bookName) {
		BibleBook book = repo.load(translation, bookName);
		return book.getNumberOfVersesByChapter();
	}

}
