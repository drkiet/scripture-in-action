package com.drkiettran.scriptureinaction.catalog.nab;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.util.TestUtils;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

/**
 * This class extract content from the USCCB's Books of the Bible page.
 * 
 * @author ktran
 *
 */
public class NabPage extends PageObject {
	private static final Logger logger = LoggerFactory.getLogger(NabPage.class);
	@FindBy(xpath = "//div[@id='CS_Element_maincontent']//table")
	private WebElement table;

	@FindBy(xpath = "//a[contains(@href,'_intros')]")
	private List<WebElement> collections;

	@FindBy(xpath = "//span[@class='bookname']")
	private List<WebElement> bookNamesSpans;

	public void visit(String booksOfTheBibleUrl) {
		getDriver().get(booksOfTheBibleUrl);
	}

	public List<String> getAllBookNames() {
		logger.info("Getting all book names");
		List<String> bookNames = new ArrayList<String>();
		List<WebElement> cols = table.findElements(By.xpath("./tbody/tr/td"));

		for (WebElement col : cols) {
			List<WebElement> lis = col.findElements(By.xpath("./ul/li"));
			for (WebElement li : lis) {
				bookNames.add(li.findElement(By.xpath("./a")).getText());
			}
		}

		logger.info("books: {}", bookNames);
		return bookNames;
	}

	public List<String> getAllCollectionNames() {
		List<String> collectionNames = new ArrayList<String>();
		for (WebElement group : collections) {
			collectionNames.add(group.getText());
		}
		return collectionNames;
	}

	public List<String> getAllBookNamesByCollections() {
		List<String> bookNamesByCollections = new ArrayList<String>();
		for (WebElement bookNameSpan : bookNamesSpans) {
			bookNamesByCollections.add(bookNameSpan.getText());
		}
		return bookNamesByCollections;
	}

	public int[] getNumberOfChaptersByBookName(String url, String[] namesOfAllBooks) {
		int[] numberOfChaptersByBookName = new int[namesOfAllBooks.length];
		getDriver().get(url);

		for (int i = 0; i < namesOfAllBooks.length; i++) {
			String bookName = namesOfAllBooks[i];
			logger.info("getting number of chapters for book {}", bookName);
			if ("1 Maccabees".equalsIgnoreCase(bookName)) {
				bookName = "1mc";
			} else if ("2 Maccabees".equalsIgnoreCase(bookName)) {
				bookName = "2mc";
			}
			String xpath = String.format("//a[contains(@href,'/bible/%s/')]", TestUtils.compressLowercase(bookName));

			numberOfChaptersByBookName[i] = getDriver().findElements(By.xpath(xpath)).size();
		}
		return numberOfChaptersByBookName;
	}

	public void getNumberOfVersesByChapterByBookName(String usccBibleContentUrl,
			Hashtable<String, List<Integer>> numberOfVersesByChapterByBookNameTable) {
		String xpath = "//span[@class='bcv']";

		for (String bookName : numberOfVersesByChapterByBookNameTable.keySet()) {
			List<Integer> numberOfVersesByChapterList = numberOfVersesByChapterByBookNameTable.get(bookName);
			for (int chapIdx = 0; chapIdx < numberOfVersesByChapterList.size(); chapIdx++) {
				logger.info("getting number of verses for chapter {} for book {}", chapIdx + 1, bookName);
				String url = String.format("%s/%s/%d", usccBibleContentUrl, TestUtils.compressLowercase(bookName),
						chapIdx + 1);

				getDriver().get(url);
				numberOfVersesByChapterList.set(chapIdx, getDriver().findElements(By.xpath(xpath)).size());
			}
		}
	}

	public List<Integer[]> getNumberOfVersesByChapterByBookName(String usccBibleContentUrl, String[] bookNames,
			int[] numChaps) {
		List<Integer[]> versesArray = new ArrayList<Integer[]>();

		for (int bookIdx = 0; bookIdx < bookNames.length; bookIdx++) {
			Integer[] versesPerChapter = new Integer[numChaps[bookIdx]];
			for (int chapIdx = 0; chapIdx < numChaps[bookIdx]; chapIdx++) {
				String url = String.format("%s/%s/%d", usccBibleContentUrl,
						TestUtils.compressLowercase(bookNames[bookIdx]), chapIdx + 1);

				getDriver().get(url);
				versesPerChapter[chapIdx] = getDriver().findElements(By.xpath("//span[@class='bcv']")).size();
				logger.info("Book {} chapter {} has {} verses", bookNames[bookIdx], chapIdx + 1,
						versesPerChapter[chapIdx]);

				if (versesPerChapter[chapIdx] == 0) {
					logger.error("**** ERROR ****");
				}
			}
			sleepToAvoidOverLoadServer(5);
			versesArray.add(versesPerChapter);
		}
		return versesArray;
	}

	private void sleepToAvoidOverLoadServer(int secs) {
		try {
			Thread.sleep(secs * 1000L);
		} catch (InterruptedException e) {
			// do nothing
		}
	}

}
