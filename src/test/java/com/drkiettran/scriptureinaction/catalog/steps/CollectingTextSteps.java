package com.drkiettran.scriptureinaction.catalog.steps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.BibleConstants;
import com.drkiettran.scriptureinaction.catalog.CollectingText;
import com.drkiettran.scriptureinaction.catalog.pages.CatholicPage;
import com.drkiettran.scriptureinaction.catalog.pages.RsvPage;
import com.drkiettran.scriptureinaction.catalog.pages.UsccbTextPage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class CollectingTextSteps extends ScenarioSteps {
	private static final Logger logger = LoggerFactory.getLogger(CollectingTextSteps.class);
	private static final long serialVersionUID = 2816310373192404112L;

	private UsccbTextPage usccbTextPage;
	private CatholicPage catholicPage;
	private RsvPage rsvPage;

	@Step("Collecting all text from all books")
	public void get_all_text_all_books_via_usccb(String textUrl, String[] bookNames, int[] chapters,
			int[][] versesByChapterByBook) {
		logger.info("getting text for all books: {}", bookNames);

		List<List<String>> textByVerses = usccbTextPage.getTextForBook(textUrl, bookNames[0], chapters[0],
				versesByChapterByBook[0]);
		for (int chapterIdx = 0; chapterIdx < chapters[0]; chapterIdx++) {
			List<String> versesByChapter = textByVerses.get(chapterIdx);
			logger.info("book {} - chapter {}:\n", bookNames[0], chapterIdx + 1);
			for (int verseIdx = 0; verseIdx < versesByChapterByBook[0][chapterIdx]; verseIdx++) {
				logger.info("verse: {} - text {}", verseIdx + 1, versesByChapter.get(verseIdx));
			}
		}
	}

	@Step
	public List<String> verify_order_of_books_by_name(String catholicContentUrl, String[] namesOfAllBooks) {
		List<String> firstPageHeadings = new ArrayList<String>();

		for (int bookIdx = 0; bookIdx < BibleConstants.NUMBER_OF_ALL_BOOKS; bookIdx++) {
			firstPageHeadings.add(catholicPage.getFirstChapterHeading(catholicContentUrl, bookIdx + 1));
		}

		return firstPageHeadings;
	}

	@Step
	public void get_all_text_all_books_via_catholic_dot_org(String catholicContentUrl, String usccbContentUrl) {
		String[] bookNames = BibleConstants.NAMES_OF_ALL_BOOKS;
		int[] chapters = BibleConstants.NUMBER_OF_CHAPTERS_BY_BOOK_NAME;
		int[][] versesByChapterByBook = BibleConstants.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME;

		logger.info("getting text for all books: {}", bookNames);

		/**
		 * Scrapping '1' at a time to avoid overrun the server.
		 */
		int bookIdx = 12;
		List<String> content = makeBook(catholicContentUrl, usccbContentUrl, bookNames[bookIdx], bookIdx + 1,
				chapters[bookIdx], versesByChapterByBook[bookIdx]);

		String folder = "C:/book-catalog/bibles/nab";
		writeTextToFile(bookIdx + 1, bookNames[bookIdx], "text", content.get(0), folder);
		writeTextToFile(bookIdx + 1, bookNames[bookIdx], "comment", content.get(1), folder);
		writeTextToFile(bookIdx + 1, bookNames[bookIdx], "link", content.get(2), folder);
	}

	private void writeTextToFile(int bookNo, String bookName, String contentType, String text, String folder) {
		String bookFileName = String.format("%s/%d-%s-%s.txt", folder, bookNo, bookName, contentType);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(bookFileName))) {
			bw.write(text);
		} catch (IOException e) {
			logger.error("Error: {}", e);
		}

		return;
	}

	public List<String> makeBook(String catholicContentUrl, String usccbContentUrl, String bookName, int bookNo,
			int numChapters, int[] numVersesByChapter) {
		List<String> content = new ArrayList<String>();

		List<List<String>> textByVerses = catholicPage.getTextForBook(catholicContentUrl, bookNo, numChapters,
				numVersesByChapter);

		List<List<String>> fnByVerses = new ArrayList<List<String>>();
		List<List<String>> enByVerses = new ArrayList<List<String>>();

		usccbTextPage.getCommentariesAndLinksForBook(usccbContentUrl, bookName, numChapters, numVersesByChapter,
				fnByVerses, enByVerses);

		content.add(makeContent(bookName, numChapters, numVersesByChapter, textByVerses));
		content.add(makeContent(bookName, numChapters, numVersesByChapter, fnByVerses));
		content.add(makeContent(bookName, numChapters, numVersesByChapter, enByVerses));

		return content;
	}

	private String makeContent(String bookName, int numChapters, int[] numVersesByChapter,
			List<List<String>> contentByVerses) {
		StringBuilder content = new StringBuilder("*** the book of ").append(bookName).append(" ***");

		for (int chapterIdx = 0; chapterIdx < numChapters; chapterIdx++) {
			content.append("\n*** chapter ").append(chapterIdx + 1).append(" ***\n\n");

			for (String verse : contentByVerses.get(chapterIdx)) {
				content.append(verse).append('\n');
			}
		}

		logger.info("\n{}", content);
		return content.toString();
	}

	@Step
	public void get_all_text_all_books_rsv(String rsvContentUrl) {
		String[] bookNames = BibleConstants.NAMES_OF_ALL_BOOKS;
		int[] chapters = BibleConstants.NUMBER_OF_CHAPTERS_BY_BOOK_NAME;
		int[][] versesByChapterByBook = BibleConstants.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME;

		logger.info("getting text for all books: {}", bookNames);

		String folder = "C:/book-catalog/bibles/rsv";
		for (int bookIdx = 0; bookIdx < bookNames.length; bookIdx++) {

			String content = makeRsvBook(rsvContentUrl, bookNames[bookIdx], bookIdx + 1, chapters[bookIdx],
					versesByChapterByBook[bookIdx]);
			if (content.isEmpty()) {
				continue;
			}
			writeTextToFile(bookIdx + 1, bookNames[bookIdx], "text", content, folder);
		}

	}

	@Step
	private String makeRsvBook(String rsvContentUrl, String bookName, int bookNo, int numChapters,
			int[] numVersesByChapter) {

		String rawText = rsvPage.getTextForBook(rsvContentUrl, bookName, bookNo, numChapters, numVersesByChapter);
		List<String> allLines = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(rawText, "\n");
		while (st.hasMoreTokens()) {
			allLines.add(st.nextToken());
		}
		return makeRsvContent(bookName, allLines);
	}

	public String makeRsvContent(String bookName, List<String> allLines) {
		List<List<String>> chapters = new ArrayList<List<String>>();
		List<String> verses = null;
		CollectingText.logger.info("all lines: {}", allLines);
		int chapterNo = 1;
		for (int idx = 0; idx < allLines.size(); idx++) {

			if (allLines.get(idx).endsWith(String.format(".%d", chapterNo))) {
				if (verses != null) {
					chapters.add(verses);
				}
				verses = new ArrayList<String>();
				CollectingText.logger.info("{}", allLines.get(idx));
				chapterNo++;
			} else if (verses != null) {
				if (!allLines.get(idx).isEmpty()) {
					verses.add(allLines.get(idx));
				}
			}
		}
		chapters.add(verses);

		/**
		 * Making the text for book
		 */

		StringBuilder sb = new StringBuilder("*** the book of ").append(bookName).append(" ***\n");
		for (int chapterIdx = 0; chapterIdx < chapters.size(); chapterIdx++) {
			sb.append("\n*** chapter ").append(chapterIdx + 1).append(" ***\n\n");
			for (String verse : chapters.get(chapterIdx)) {
				if (verse.charAt(0) != '[') {
					sb.replace(sb.length() - 1, sb.length(), " ");
				}
				sb.append(verse).append('\n');
			}
		}

		/**
		 * Getting rid of [ and ] surround the verse no.
		 */
		StringBuilder newSb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(sb.toString(), "\n");
		while (st.hasMoreTokens()) {
			String line = st.nextToken();
			StringBuilder sbLine = new StringBuilder(line);
			if (sbLine.charAt(3) == ']') {
				sbLine.deleteCharAt(3);
			} else if (sbLine.charAt(2) == ']') {
				sbLine.deleteCharAt(2);
			}
			if (sbLine.charAt(0) == '[') {
				sbLine.deleteCharAt(0);
			}
			if (sbLine.toString().startsWith("*")) {
				sbLine.insert(0, '\n');
			}
			if (sbLine.toString().endsWith("*")) {
				sbLine.append('\n');
			}
			newSb.append(sbLine).append('\n');
		}
		CollectingText.logger.info("\n:{}", newSb);
		return newSb.toString();
	}

	public String loadBookFromFile(String string, List<String> allLines) {
		// TODO Auto-generated method stub
		return null;
	}
}
