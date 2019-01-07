package com.drkiettran.scriptureinaction.query;

import org.springframework.beans.factory.annotation.Autowired;

import com.drkiettran.scriptureinaction.model.BibleBook;
import com.drkiettran.scriptureinaction.repository.BibleRepository;
import com.drkiettran.scriptureinaction.repository.BibleRepositoryViaFile;

public class AboutBookNab implements AboutBook {

	@Autowired
	private BibleRepository repo;

	private BibleBook book = null;

	public AboutBookNab(String translation, String bookName) {
		if (repo == null) {
			repo = new BibleRepositoryViaFile();
		}
		book = repo.load(translation, bookName);
	}

	public AboutBookNab() {

	}

	@Override
	public int getNumChapters(String translation, String bookName) {
		book = repo.load(translation, bookName);
		return book.getNumberOfChapters();
	}

	public void setRepo(BibleRepository repo) {
		this.repo = repo;
	}

	@Override
	public int[] getNumVersesByChapter(String translation, String bookName) {
		book = repo.load(translation, bookName);
		return book.getNumberOfVersesByChapter();
	}

	@Override
	public BibleBook getBook() {
		return book;
	}

}
