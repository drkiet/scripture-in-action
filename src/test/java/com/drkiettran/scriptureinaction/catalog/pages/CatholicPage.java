package com.drkiettran.scriptureinaction.catalog.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.drkiettran.scriptureinaction.catalog.util.TestUtils;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public class CatholicPage extends PageObject {

	public String getFirstChapterHeading(String catholicContentUrl, int bookNo) {
		String firstChapterUrl = String.format("%s?id=%d", catholicContentUrl, bookNo);
		getDriver().get(firstChapterUrl);
		return getDriver().findElement(By.xpath("//h1")).getText();
	}

	public List<List<String>> getTextForBook(String textUrl, int bookNo, int numChapters, int[] numVersesByChapter) {
		List<List<String>> versesByChapterList = new ArrayList<List<String>>();

		for (int chapterIdx = 0; chapterIdx < numChapters; chapterIdx++) {
			int chapterNo = chapterIdx + 1;
			int numVerses = numVersesByChapter[chapterIdx];
			String url = String.format("%s?id=%d&bible_chapter=%d", textUrl, bookNo, chapterNo);

			getDriver().get(url);
			List<WebElement> verseElements = getDriver().findElements(By.xpath("//div/div/p"));
			List<String> verses = new ArrayList<String>();

			for (int verseIdx = 0; verseIdx < numVerses; verseIdx++) {
				int verseNo = verseIdx + 1;
				verses.add(verseElements.get(verseIdx).getText());
			}

			versesByChapterList.add(verses);
		}
		return versesByChapterList;
	}

}
