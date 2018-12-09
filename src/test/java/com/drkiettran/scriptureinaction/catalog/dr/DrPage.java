package com.drkiettran.scriptureinaction.catalog.dr;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public class DrPage extends PageObject {
	private static final Logger logger = LoggerFactory.getLogger(DrPage.class);
	private static final String BOOK_CHAPTER_URL_TEMPLATE = "%s/chapter/%02d%03d.htm";

	public Object[] getTextForBook(String drContentUrl, String bookName, int bookNo, int numChapters) {
		logger.info("DR book: {}", bookName);
		List<List<String>> versesByChapters = new ArrayList<List<String>>();
		List<List<String>> notesByChapters = new ArrayList<List<String>>();

		for (int chapterIdx = 0; chapterIdx < numChapters; chapterIdx++) {
			String chapterUrl = String.format(BOOK_CHAPTER_URL_TEMPLATE, drContentUrl, bookNo, chapterIdx + 1);
			logger.info("Go to {}", chapterUrl);
			getDriver().get(chapterUrl);
			List<WebElement> ps = getDriver().findElements(By.xpath("//table[@class='texttable']/tbody/tr/td/p"));

			List<String> verses = new ArrayList<String>();
			List<String> notes = new ArrayList<String>();

			for (WebElement p : ps) {
				if (p.getAttribute("class").equalsIgnoreCase("note")) {
					StringTokenizer st = new StringTokenizer(p.getText(), "[");
					while (st.hasMoreTokens()) {
						StringBuilder note = new StringBuilder(st.nextToken());
						if (note.charAt(1) == ']') {
							note.deleteCharAt(1);
						} else if (note.charAt(2) == ']') {
							note.deleteCharAt(2);
						}

						notes.add(note.toString());
					}
				} else {
					StringTokenizer st = new StringTokenizer(p.getText(), "[");
					while (st.hasMoreTokens()) {
						StringBuilder verse = new StringBuilder(st.nextToken());
						if (verse.charAt(1) == ']') {
							verse.deleteCharAt(1);
						} else if (verse.charAt(2) == ']') {
							verse.deleteCharAt(2);
						}

						verses.add(verse.toString());
					}
				}
			}
			versesByChapters.add(verses);
			notesByChapters.add(notes);

		}
		Object[] returnList = { versesByChapters, notesByChapters };
		return returnList;
	}

	public List<String> getAllBookNames(String drboBibleContentUrl, int numberOfAllBooks) {
		logger.info("Getting {} book names @ {}", numberOfAllBooks, drboBibleContentUrl);
		List<String> bookNames = new ArrayList<String>();

		for (int bookIdx = 0; bookIdx < numberOfAllBooks; bookIdx++) {
			String url = String.format("%s/chapter/%02d001.htm", drboBibleContentUrl, bookIdx + 1);
			logger.info("Getting book name @ {}", url);
			getDriver().get(url);
			bookNames.add(getDriver().findElements(By.xpath("//td[@class='bookname']")).get(0).getText());
		}

		return bookNames;
	}

	public int[] getNumberOfChaptersByBookName(String drUrl, String[] namesOfAllBooks) {
		logger.info("Getting number of chapters for {}", namesOfAllBooks.length);
		int[] numChaptersByBook = new int[namesOfAllBooks.length];

		for (int bookIdx = 0; bookIdx < namesOfAllBooks.length; bookIdx++) {
			String url = String.format("%s/chapter/%02d001.htm", drUrl, bookIdx + 1);
			logger.info("Getting # chapters for book name @ {}", url);
			getDriver().get(url);
			List<WebElement> tds = getDriver().findElements(By.xpath("//table[@class='chapnumtable']//td"));
			int numChapters = 0;
			for (WebElement td : tds) {
				if (!td.getText().trim().isEmpty()) {
					numChapters++;
				}
			}
			numChaptersByBook[bookIdx] = numChapters;
		}

		return numChaptersByBook;
	}

	public List<Integer[]> getNumberOfVersesByChaptersByBookName(String drUurl, int[] numChaptersByBook,
			String[] namesOfBooks) {
		logger.info("Getting verses by chapter by book");
		List<Integer[]> numberVersesByChapterByBook = new ArrayList<Integer[]>();

		for (int bookIdx = 0; bookIdx < namesOfBooks.length; bookIdx++) {
			numberVersesByChapterByBook.add(new Integer[numChaptersByBook[bookIdx]]);

			for (int chapterIdx = 0; chapterIdx < numChaptersByBook[bookIdx]; chapterIdx++) {
				String url = String.format("%s/chapter/%02d%03d.htm", drUurl, bookIdx + 1, chapterIdx + 1);
				logger.info("Getting # chapters for book name @ {}", url);
				getDriver().get(url);
				List<WebElement> as = getDriver().findElements(By.xpath("// a[starts-with(text(),'[')]"));
				numberVersesByChapterByBook.get(bookIdx)[chapterIdx] = as.size();
			}
		}
		return numberVersesByChapterByBook;
	}
}
