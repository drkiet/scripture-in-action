package com.drkiettran.scriptureinaction.repository;

import com.drkiettran.scriptureinaction.model.BibleBook;

public interface BibleRepository {

	BibleBook load(String translation, String bookName);

}
