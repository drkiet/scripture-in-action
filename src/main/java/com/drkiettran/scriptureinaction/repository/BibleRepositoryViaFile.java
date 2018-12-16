package com.drkiettran.scriptureinaction.repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.drkiettran.scriptureinaction.model.BibleBook;
import com.drkiettran.scriptureinaction.model.Chapter;
import com.drkiettran.scriptureinaction.model.Verse;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;
import com.drkiettran.scriptureinaction.util.CommonUtils;

@Component
public class BibleRepositoryViaFile implements BibleRepository {
	private static final Logger logger = LoggerFactory.getLogger(BibleRepository.class);

	private Hashtable<String, BibleBook> booksHash = new Hashtable<String, BibleBook>();

	@Override
	public BibleBook load(String translation, String bookName) {
		String key = makeKey(translation, bookName);
		if (booksHash.get(key) == null) {
			BibleBook book = loadFromFile(translation, bookName);
			booksHash.put(key, book);
//			logger.info("Book summary: \n{}", book.logBookSummary());
		}

		return booksHash.get(key);
	}

	private BibleBook loadFromFile(String translation, String bookName) {
		logger.info("load book {} ({}) from file", bookName, translation);
		Path path = Paths.get(getFolder(translation), getFileName(translation, bookName));
		List<String> lines = CommonUtils.loadFromFile(path);

		BibleBook book = new BibleBook();
		book.setBookId(CommonUtils.getUniqeId());
		book.setTranslation(translation);
		book.setName(bookName);
		List<Chapter> chapters = new ArrayList<Chapter>();

		book.setChapters(chapters);
		Chapter chapter = null;
		List<Verse> verses = null;
		String additionalLeadingText = null;

		for (int lineIdx = 0; lineIdx < lines.size(); lineIdx++) {
			if (lines.get(lineIdx).isEmpty()) {
				continue;
			}

			if (lineIsVerse(lines.get(lineIdx))) {
				Verse verse = new Verse();
				verse.setVerseId(CommonUtils.getUniqeId());
				verse.setChapterId(chapter.getChapterId());
				verse.setBookId(chapter.getBookId());
				verse.setBookName(bookName);
				verse.setChapterNumber(chapter.getChapterNumber());
				if (additionalLeadingText != null) {
					verse.setAdditionalLeadingText(additionalLeadingText);
					additionalLeadingText = null;
				}
				verse.setText(lines.get(lineIdx));
				verses.add(verse);
				verse.setVerseNumber(verses.size());
			}

			if (lineIsChapterHeading(lines.get(lineIdx), bookName)) {
				chapter = new Chapter();
				chapter.setBookId(book.getBookId());
				chapter.setChapterId(CommonUtils.getUniqeId());
				chapter.setChapterNumber(getChapterNumber(lines.get(lineIdx)));
				verses = new ArrayList<Verse>();
				chapter.setVerses(verses);
				chapters.add(chapter);
				continue;
			}

			if (lineIsNotBookHeading(lines.get(lineIdx))) {
				additionalLeadingText = lines.get(lineIdx);
			}
		}
		book.setNumberOfChapters(chapters.size());
		return book;
	}

	private int getChapterNumber(String chapterLine) {
		String[] tokens = chapterLine.split(" ");
		return Integer.valueOf(tokens[tokens.length - 2]);
	}

	private boolean lineIsNotBookHeading(String line) {
		return !(line.startsWith("*** the book of "));
	}

	private boolean lineIsVerse(String line) {
		return Character.isDigit(line.charAt(0));
	}

	private boolean lineIsChapterHeading(String line, String bookName) {
		return (line.startsWith("*** chapter") || line.startsWith(String.format("*** %s", bookName)));
	}

	private String getFileName(String translation, String bookName) {
		int bookNo = getBookNoByBookName(translation, bookName);
		return String.format("%02d-%s-text.txt", bookNo, bookName);
	}

	private int getBookNoByBookName(String translation, String bookName) {
		logger.info("searching for bookIdx for {}", bookName);
		String[] bookNames = NewAmerican.NAMES_OF_ALL_BOOKS;
		for (int bookIdx = 0; bookIdx < bookNames.length; bookIdx++) {
			if (bookName.equalsIgnoreCase(bookNames[bookIdx])) {
				logger.info("found bookIdx {}", bookIdx);
				return bookIdx + 1;
			}
		}
		logger.info("*** bookIdx not found for {}", bookName);
		return 0;
	}

	private String getFolder(String translation) {
		switch (translation) {
		case BibleBook.NAB:
			return System.getProperty("sia.bible.nab.text.folder");
		case BibleBook.RSV:
			return System.getProperty("sia.bible.rsv.text.folder");
		case BibleBook.DR:
			return System.getProperty("sia.bible.dr.text.folder");
		case BibleBook.LV:
			return System.getProperty("sia.bible.lv.text.folder");
		}
		return "";
	}

	private String makeKey(String translation, String bookName) {
		String key = String.format("%s_%s", translation, bookName);
		return key;
	}

}
