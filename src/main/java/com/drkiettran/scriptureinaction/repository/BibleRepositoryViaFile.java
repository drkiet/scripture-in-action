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
import com.drkiettran.scriptureinaction.model.Commentary;
import com.drkiettran.scriptureinaction.model.Link;
import com.drkiettran.scriptureinaction.model.Verse;
import com.drkiettran.scriptureinaction.util.CommonUtils;

@Component
public class BibleRepositoryViaFile implements BibleRepository {
	private static final Logger logger = LoggerFactory.getLogger(BibleRepository.class);

	private static Hashtable<String, BibleBook> booksHash = new Hashtable<String, BibleBook>();

	@Override
	public BibleBook load(String translation, String bookName) {
		String key = makeKey(translation, bookName);
		if (booksHash.get(key) == null) {
			BibleBook book = loadFromFile(translation, bookName);
			if (translation.equals(BibleBook.NAB)) {
				loadLinksFromFile(book, translation, bookName);
				loadCommentsFromFile(book, translation, bookName);
			}
			booksHash.put(key, book);
//			logger.info("Book summary: \n{}", book.logBookSummary());
		}

		return booksHash.get(key);
	}

	private void loadCommentsFromFile(BibleBook book, String translation, String bookName) {
		logger.info("load book {} ({}) from file", bookName, translation);
		Path path = Paths.get(getFolder(translation), CommonUtils.getCommentFileName(translation, bookName));
		List<String> lines = CommonUtils.loadCommentsFromFile(path);
		List<Commentary> comments = null;
		Chapter chapter = null;

		for (int lineIdx = 0; lineIdx < lines.size(); lineIdx++) {
			logger.info("*** line: \n{}", lines.get(lineIdx));
			if (lines.get(lineIdx).isEmpty()) {
				continue;
			}

			if (lineIsComment(lines.get(lineIdx))) {
				Commentary comment = new Commentary();
				comment.setBookName(bookName);
				comment.parse(lines.get(lineIdx));
				comment.setCommentaryId(CommonUtils.getUniqeId());
				comment.setBookId(book.getBookId());
				comment.setBookName(bookName);

				comments.add(comment);
			}

			if (lineIsChapterHeading(lines.get(lineIdx), bookName)) {
				int chapterNumber = getChapterNumber(lines.get(lineIdx));
				chapter = book.getChapters().get(chapterNumber - 1);

				if (chapter.getChapterNumber() != chapterNumber) {
					logger.error("*** ERROR *** {} verses {}", chapter.getChapterNumber(), chapterNumber);
				}
				chapter.setBookId(book.getBookId());
				chapter.setChapterId(CommonUtils.getUniqeId());
				chapter.setChapterNumber(getChapterNumber(lines.get(lineIdx)));
				comments = new ArrayList<Commentary>();
				chapter.setComments(comments);
				continue;
			}
		}
	}

	private boolean lineIsComment(String line) {
		return line.startsWith("* [");
	}

	private void loadLinksFromFile(BibleBook book, String translation, String bookName) {
		logger.info("load book {} ({}) from file", bookName, translation);
		Path path = Paths.get(getFolder(translation), CommonUtils.getLinkFileName(translation, bookName));
		List<String> lines = CommonUtils.loadLinksFromFile(path);
		List<Link> links = null;
		Chapter chapter = null;

		for (int lineIdx = 0; lineIdx < lines.size(); lineIdx++) {
			logger.info("*** line: \n{}", lines.get(lineIdx));
			if (lines.get(lineIdx).isEmpty()) {
				continue;
			}

			if (lineIsLink(lines.get(lineIdx))) {
				Link link = new Link();
				link.setBookName(bookName);
				link.parse(lines.get(lineIdx));
				link.setLinkId(CommonUtils.getUniqeId());
				link.setBookId(book.getBookId());
				link.setBookName(bookName);

				links.add(link);
			}

			if (lineIsChapterHeading(lines.get(lineIdx), bookName)) {
				int chapterNumber = getChapterNumber(lines.get(lineIdx));
				chapter = book.getChapters().get(chapterNumber - 1);

				if (chapter.getChapterNumber() != chapterNumber) {
					logger.error("*** ERROR *** {} verses {}", chapter.getChapterNumber(), chapterNumber);
				}
				chapter.setBookId(book.getBookId());
				chapter.setChapterId(CommonUtils.getUniqeId());
				chapter.setChapterNumber(getChapterNumber(lines.get(lineIdx)));
				links = new ArrayList<Link>();
				chapter.setLinks(links);
				continue;
			}
		}
	}

	private boolean lineIsLink(String line) {
		return Character.isAlphabetic(line.charAt(0)) && line.charAt(1) == '.';
	}

	private BibleBook loadFromFile(String translation, String bookName) {
		logger.info("load book {} ({}) from file", bookName, translation);
		Path path = Paths.get(getFolder(translation), CommonUtils.getFileName(translation, bookName));
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
