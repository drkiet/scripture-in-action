package com.drkiettran.scriptureinaction.catalog.lv;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public class LvPage extends PageObject {
	private static final Logger logger = LoggerFactory.getLogger(LvPage.class);

	public List<String> getAllBookNames(String lvUrl, int numberBooks) {
		logger.info("Getting {} book names @ {}", numberBooks, lvUrl);
		List<String> bookNames = new ArrayList<String>();

		for (int bookIdx = 0; bookIdx < numberBooks; bookIdx++) {
			String url = String.format("%s/lvb/chapter/%02d001.htm", lvUrl, bookIdx + 1);
			logger.info("Getting book name @ {}", url);
			getDriver().get(url);
			bookNames.add(getDriver().findElements(By.xpath("//td[@class='bookname']")).get(0).getText());
		}

		return bookNames;
	}

	public int[] getNumberOfChaptersByBookName(String lvUrl, String[] namesOfAllBooks) {
		logger.info("Getting number of chapters for {}", namesOfAllBooks.length);
		int[] numChaptersByBook = new int[namesOfAllBooks.length];

		for (int bookIdx = 0; bookIdx < namesOfAllBooks.length; bookIdx++) {
			String url = String.format("%s/lvb/chapter/%02d001.htm", lvUrl, bookIdx + 1);
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

	public List<Integer[]> getNumberOfVersesByChaptersByBookName(String lvBibleUrl, int[] numChaptersByBook,
			String[] namesOfBooks) {
		logger.info("Getting verses by chapter by book");
		List<Integer[]> numberVersesByChapterByBook = new ArrayList<Integer[]>();

		for (int bookIdx = 0; bookIdx < namesOfBooks.length; bookIdx++) {
			numberVersesByChapterByBook.add(new Integer[numChaptersByBook[bookIdx]]);

			for (int chapterIdx = 0; chapterIdx < numChaptersByBook[bookIdx]; chapterIdx++) {
				String url = String.format("%s/lvb/chapter/%02d%03d.htm", lvBibleUrl, bookIdx + 1, chapterIdx + 1);
				logger.info("Getting # chapters for book name @ {}", url);
				getDriver().get(url);
				List<WebElement> as = getDriver().findElements(By.xpath("// a[starts-with(text(),'[')]"));
				numberVersesByChapterByBook.get(bookIdx)[chapterIdx] = as.size();
			}
		}
		return numberVersesByChapterByBook;
	}
}
