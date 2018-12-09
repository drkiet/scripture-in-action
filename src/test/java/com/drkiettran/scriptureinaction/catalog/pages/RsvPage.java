package com.drkiettran.scriptureinaction.catalog.pages;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.util.TestUtils;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;
import com.drkiettran.scriptureinaction.model.constants.RevisedStandard;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public class RsvPage extends PageObject {
	private static final Logger logger = LoggerFactory.getLogger(RsvPage.class);

	public List<String> getAllBookNames(String rsvBibleUrl, int numberOfAllBooks) {
		logger.info("Gett book names");
		getDriver().get(rsvBibleUrl);
		List<String> bookNames = new ArrayList<String>();
		List<WebElement> selects = getDriver().findElements(By.xpath("//select"));

		for (WebElement webElement : selects) {
			Select select = new Select(webElement);
			for (WebElement option : select.getOptions()) {
				if (option.getText().isEmpty()) {
					continue;
				}

				bookNames.add(option.getText());
			}
		}
		logger.info("Got {} book names", bookNames.size());
		return bookNames;
	}

	/**
	 * To avoid saturating the website, in this method, I do everything at once: -
	 * get number of chapters by book - get number of verses by chapter by book -
	 * get text by book & write it to file.
	 * 
	 * @param rsvBibleUrl
	 * @param namesOfAllBooks
	 * @param numChaptersByBook
	 * @param numVersesByChapterByBook
	 */
	public void getNumChaptersNumVersesAndText(String rsvBibleUrl, String[] bookNames, List<Integer> numChaptersByBook,
			List<Integer[]> numVersesByChapterByBook) {
		logger.info("Getting number of chapters, verses & text for all books");

		for (int bookIdx = 0; bookIdx < bookNames.length; bookIdx++) {
			TestUtils.restLittle(60);

			getDriver().get(rsvBibleUrl);
			getDriver().findElement(By.xpath(String.format("//option[contains(text(),'%s')]", bookNames[bookIdx])))
					.click();
			getDriver().findElement(By.xpath("//input[@type='submit']")).click();

			StringTokenizer st = new StringTokenizer(getDriver().findElement(By.xpath("//table")).getText(), "\n");

			StringBuilder text = new StringBuilder("*** the book of ").append(bookNames[bookIdx]).append('\n');
			int numVerses = 0;
			List<Integer> numVersesByChapter = new ArrayList<Integer>();

			while (st.hasMoreTokens()) {
				String line = st.nextToken().trim();
				if (line.isEmpty()) {
					continue;
				}

				if (line.startsWith(bookNames[bookIdx])) {
					line = new StringBuilder(line).insert(0, "\n*** ").append(" ***\n").toString();
					if (numVerses != 0) {
						numVersesByChapter.add(numVerses);
					}
					numVerses = 0;
					text.append(line).append('\n');
					continue;
				}

				if (Character.isDigit(line.charAt(0))) {
					text.append(line).append('\n');
					numVerses++;
					continue;
				}
				logger.info("What is this '{}'?", line);
			}
			numVersesByChapter.add(numVerses);
			numVersesByChapterByBook.add(numVersesByChapter.toArray(new Integer[numVersesByChapter.size()]));

			String folder = System.getProperty("sia.bible.rsv.text.folder");
			String fileName = String.format("%d-%s-text.txt", bookIdx + 1, RevisedStandard.NAMES_OF_ALL_BOOKS[bookIdx]);

			logger.info("Writing {} to {} folder", fileName, folder);
			numChaptersByBook.add(numVersesByChapter.size());
			TestUtils.writeTextToFile(bookIdx + 1, bookNames[bookIdx], "text", text.toString(), folder);

			logger.info("text:\n{}", text.toString());
		}

		return;
	}

}
