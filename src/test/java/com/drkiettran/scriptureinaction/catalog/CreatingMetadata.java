package com.drkiettran.scriptureinaction.catalog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.BibleConstants;

public class CreatingMetadata {
	private static final Logger logger = LoggerFactory.getLogger(CreatingMetadata.class);

	@Test
	public void should_store_bible_metadata_in_files() {

		//
		int startIdx = 0;
		int endIdx = BibleConstants.NUMBER_OF_ALL_BOOKS;

		String fileName = "book-names";
		String content = makeContent(BibleConstants.NAMES_OF_ALL_BOOKS, startIdx, endIdx);
		writeTextToFile(fileName, content);

		endIdx = BibleConstants.NUMBER_OF_OT_BOOKS;
		fileName = "old-testament-book-names";
		content = makeContent(BibleConstants.NAMES_OF_ALL_BOOKS, startIdx, endIdx);
		writeTextToFile(fileName, content);

		//
		startIdx = BibleConstants.NUMBER_OF_OT_BOOKS;
		endIdx = BibleConstants.NUMBER_OF_ALL_BOOKS;
		fileName = "new-testament-book-names";
		content = makeContent(BibleConstants.NAMES_OF_ALL_BOOKS, startIdx, endIdx);
		writeTextToFile(fileName, content);

		int[] lens = { BibleConstants.NUMBER_OF_PENTATEUCH_BOOKS, BibleConstants.NUMBER_OF_HISTORICAL_BOOKS,
				BibleConstants.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS, BibleConstants.NUMBER_OF_WISDOM_BOOKS,
				BibleConstants.NUMBER_OF_PROPHETIC_BOOKS, BibleConstants.NUMBER_OF_GOSPEL_BOOKS,
				BibleConstants.NUMBER_OF_LETTER_BOOKS, BibleConstants.NUMBER_OF_CATHOLIC_LETTER_BOOKS };

		String[] collectionNames = { "Pentateuch", "Historical", "Biblical-Novellas", "Wisdom", "Prophetic", "Gospel",
				"Letter", "Catholic-Letter" };

		//
		startIdx = 0;
		endIdx = 0;
		for (int i = 0; i < lens.length; i++) {
			fileName = String.format("%s-book-name", collectionNames[i]).toLowerCase();
			endIdx += lens[i];
			content = makeContent(BibleConstants.NAMES_OF_ALL_BOOKS, startIdx, endIdx);
			writeTextToFile(fileName, content);
			startIdx += lens[i];
		}

		//
		startIdx = 0;
		endIdx = BibleConstants.NUMBER_OF_ALL_BOOKS;

		fileName = "abbrev-book-names";
		content = makeContent(BibleConstants.ABBREVIATIONS_OF_ALL_BOOKS, startIdx, endIdx);
		writeTextToFile(fileName, content);

		//
		StringBuilder sb = new StringBuilder();
		int[] numChaptersByBook = BibleConstants.NUMBER_OF_CHAPTERS_BY_BOOK_NAME;
		for (int i = 0; i < numChaptersByBook.length; i++) {
			sb.append(i + 1).append(' ').append(numChaptersByBook[i]).append('\n');
		}

		fileName = "number-of-chapters-by-book";
		writeTextToFile(fileName, sb.toString());

		//
		sb = new StringBuilder();
		int[][] numVersesByBook = BibleConstants.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME;
		for (int i = 0; i < numVersesByBook.length; i++) {
			sb.append(i + 1);
			for (int verses : numVersesByBook[i]) {
				sb.append(' ').append(verses).append(",");
			}
			sb.append('\n');
		}

		fileName = "number-of-verses-by-chapter-by-book";
		writeTextToFile(fileName, sb.toString());

	}

	private String makeContent(String[] content, int start, int end) {
		StringBuilder sb = new StringBuilder();

		for (int i = start; i < end; i++) {
			sb.append(i + 1).append(' ').append(content[i]).append('\n');
		}
		return sb.toString();
	}

	private void writeTextToFile(String fileName, String text) {
		fileName = String.format("C:/book-catalog/bibles/metadata/%s.txt", fileName);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
			bw.write(text);
		} catch (IOException e) {
			logger.error("Error: {}", e);
		}

		return;
	}
}
