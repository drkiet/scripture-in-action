package com.drkiettran.scriptureinaction.catalog.steps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.CollectingTextRSV;
import com.drkiettran.scriptureinaction.catalog.dr.DrPage;
import com.drkiettran.scriptureinaction.catalog.pages.CatholicPage;
import com.drkiettran.scriptureinaction.catalog.pages.RsvPage;
import com.drkiettran.scriptureinaction.catalog.pages.UsccbTextPage;
import com.drkiettran.scriptureinaction.catalog.util.TestUtils;
import com.drkiettran.scriptureinaction.model.constants.DouayRheims;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class CollectingTextSteps extends ScenarioSteps {
	private static final Logger logger = LoggerFactory.getLogger(CollectingTextSteps.class);
	private static final long serialVersionUID = 2816310373192404112L;

	private UsccbTextPage usccbTextPage;
	private CatholicPage catholicPage;
	private RsvPage rsvPage;
	private DrPage drPage;

	@Step("Collecting all text from all books")
	public void get_all_text_all_books_via_usccb(String textUrl) {
		String[] bookNames = NewAmerican.NAMES_OF_ALL_BOOKS;
		int[] chapters = NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME;
		int[][] versesByChapterByBook = NewAmerican.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME;

		logger.info("getting text for all books: {}", bookNames);

		/**
		 * Scrapping '1' at a time to avoid overrun the server.
		 */
		for (int bookIdx = 1; bookIdx < bookNames.length; bookIdx++) {
			List<String> content = makeBookNAB(textUrl, bookNames[bookIdx], bookIdx + 1, chapters[bookIdx],
					versesByChapterByBook[bookIdx]);

			String folder = System.getProperty("sia.bible.nab.folder");
			TestUtils.writeTextToFile(bookIdx + 1, bookNames[bookIdx], "text", content.get(0), folder);
			TestUtils.writeTextToFile(bookIdx + 1, bookNames[bookIdx], "comment", content.get(1), folder);
			TestUtils.writeTextToFile(bookIdx + 1, bookNames[bookIdx], "link", content.get(2), folder);

			TestUtils.restLittle(60);
		}
	}

	@Step
	public List<String> verify_order_of_books_by_name(String catholicContentUrl, String[] namesOfAllBooks) {
		List<String> firstPageHeadings = new ArrayList<String>();

		for (int bookIdx = 0; bookIdx < NewAmerican.NUMBER_OF_ALL_BOOKS; bookIdx++) {
			firstPageHeadings.add(catholicPage.getFirstChapterHeading(catholicContentUrl, bookIdx + 1));
		}

		return firstPageHeadings;
	}

	private List<String> makeBookNAB(String usccbContentUrl, String bookName, int bookNo, int numChapters,
			int[] numVersesByChapter) {
		List<String> content = new ArrayList<String>();

		List<List<String>> textByVerses = new ArrayList<List<String>>();
		List<List<String>> fnByVerses = new ArrayList<List<String>>();
		List<List<String>> enByVerses = new ArrayList<List<String>>();

		usccbTextPage.getTextAndCommentariesAndLinksForBookNAB(usccbContentUrl, bookName, bookNo, numChapters,
				numVersesByChapter, fnByVerses, enByVerses, textByVerses);

		content.add(makeContent(bookName, numChapters, numVersesByChapter, textByVerses));
		content.add(makeContent(bookName, numChapters, numVersesByChapter, fnByVerses));
		content.add(makeContent(bookName, numChapters, numVersesByChapter, enByVerses));

		return content;
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
	public void get_all_text_all_books_dr(String drContentUrl) {
		String[] bookNames = DouayRheims.NAMES_OF_ALL_BOOKS;
		int[] chapters = DouayRheims.NUMBER_OF_CHAPTERS_BY_BOOK_NAME;
		logger.info("getting text for all DR books: {}", bookNames);
		String folder = System.getProperty("sia.bible.dr.folder");

		for (int bookIdx = 0; bookIdx < bookNames.length; bookIdx++) {
			makeDrBook(drContentUrl, bookNames[bookIdx], bookIdx + 1, chapters[bookIdx], folder);
			TestUtils.restLittle(60);
		}

	}

	@Step
	public void get_all_text_all_books_lv(String lvContentUrl) {
		String[] bookNames = DouayRheims.NAMES_OF_ALL_BOOKS;
		int[] chapters = DouayRheims.NUMBER_OF_CHAPTERS_BY_BOOK_NAME;
		logger.info("getting text for all LV books: {}", bookNames);
		String folder = System.getProperty("sia.bible.lv.folder");

		for (int bookIdx = 0; bookIdx < bookNames.length; bookIdx++) {
			makeDrBook(lvContentUrl, bookNames[bookIdx], bookIdx + 1, chapters[bookIdx], folder);
			TestUtils.restLittle(60);
		}
	}

	private void makeDrBook(String drContentUrl, String bookName, int bookNo, int numChapters, String folder) {

		Object[] returnList = drPage.getTextForBook(drContentUrl, bookName, bookNo, numChapters);

		List<List<String>> versesByChapters = (List<List<String>>) returnList[0];
		List<List<String>> notesByChapters = (List<List<String>>) returnList[1];

		StringBuilder sb = new StringBuilder("*** the book of ").append(bookName).append(" ***\n");
		for (int chapterIdx = 0; chapterIdx < versesByChapters.size(); chapterIdx++) {
			sb.append("\n*** chapter ").append(chapterIdx + 1).append(" ***\n\n");
			for (String verse : versesByChapters.get(chapterIdx)) {
				sb.append(verse).append('\n');
			}
		}

		logger.info("Text:\n{}", sb.toString());

		bookName = bookName.replace("(", "_").replace(")", "_");
		TestUtils.writeTextToFile(bookNo, bookName, "text", sb.toString(), folder);

		sb = new StringBuilder("*** the book of ").append(bookName).append(" ***\n");
		for (int chapterIdx = 0; chapterIdx < notesByChapters.size(); chapterIdx++) {
			sb.append("\n*** chapter ").append(chapterIdx + 1).append(" ***\n\n");
			for (String note : notesByChapters.get(chapterIdx)) {
				sb.append(note).append('\n');
			}
		}

		logger.info("Note:\n{}", sb.toString());
		TestUtils.writeTextToFile(bookNo, bookName, "comment", sb.toString(), folder);
	}

	public String makeRsvContent(String bookName, List<String> allLines) {
		List<List<String>> chapters = new ArrayList<List<String>>();
		List<String> verses = null;
		CollectingTextRSV.logger.info("all lines: {}", allLines);
		int chapterNo = 1;
		for (int idx = 0; idx < allLines.size(); idx++) {

			if (allLines.get(idx).endsWith(String.format(".%d", chapterNo))) {
				if (verses != null) {
					chapters.add(verses);
				}
				verses = new ArrayList<String>();
				CollectingTextRSV.logger.info("{}", allLines.get(idx));
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
		CollectingTextRSV.logger.info("\n:{}", newSb);
		return newSb.toString();
	}

	@Step
	public List<List<String>> loadBookFromFile(String string, List<String> allLines) {
		List<List<String>> versesByChapters = new ArrayList<List<String>>();
		List<String> verses = null;

		for (String line : allLines) {
			if (line.isEmpty()) {
				continue;
			}

			if (line.startsWith("*** chapter")) {
				if (verses != null) {
					versesByChapters.add(verses);
				}
				verses = new ArrayList<String>();
				continue;
			}

			if (verses == null) {
				continue;
			}

			if (Character.isDigit(line.charAt(0))) {
				verses.add(line);
			}
		}

		versesByChapters.add(verses);
		return versesByChapters;
	}

}
