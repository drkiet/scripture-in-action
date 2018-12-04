package com.drkiettran.scriptureinaction.catalog.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public class RsvPage extends PageObject {
	private static final Logger logger = LoggerFactory.getLogger(RsvPage.class);

	public String getTextForBook(String rsvContentUrl, String bookName, int bookNo, int numChapters,
			int[] numVersesByChapter) {
		List<List<String>> versesByChapterList = new ArrayList<List<String>>();
		logger.info("RSV book: {}", bookName);

		String url = String.format("//a[text()='%s']", bookName);

		getDriver().get(rsvContentUrl);
		if ("Song of Songs".equalsIgnoreCase(bookName)) {
			bookName = "Song of Solomon";
		} else if ("Wisdom".equalsIgnoreCase(bookName)) {
			bookName = "Song of Solomon";
		} else if ("2 Thessalonians".equalsIgnoreCase(bookName)) {
			bookName = "1 Thessalonian";
		}
		List<WebElement> link = getDriver()
				.findElements(By.xpath(String.format("//a[contains(text(),'%s')]", bookName)));
		if (link.isEmpty()) {
			logger.info("**** NO LINKS FOUND for {}", bookName);
			return "";
		}
		link.get(0).click();
		logger.info("Content: {}", getDriver().findElement(By.xpath("//body")).getText());

		return getDriver().findElement(By.xpath("//body")).getText();
	}

}
