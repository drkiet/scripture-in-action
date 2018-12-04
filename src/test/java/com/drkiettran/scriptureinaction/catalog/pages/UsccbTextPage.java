package com.drkiettran.scriptureinaction.catalog.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.drkiettran.scriptureinaction.catalog.util.TestUtils;

import net.serenitybdd.core.pages.PageObject;

public class UsccbTextPage extends PageObject {

	public List<List<String>> getTextForBook(String textUrl, String bookName, int numChapters,
			int[] numVersesByChapter) {
		List<List<String>> versesByChapterList = new ArrayList<List<String>>();

		for (int chapterIdx = 0; chapterIdx < numChapters; chapterIdx++) {
			int chapterNo = chapterIdx + 1;
			int numVerses = numVersesByChapter[chapterIdx];
			String url = String.format("%s/%s/%d", textUrl, TestUtils.compressLowercase(bookName), chapterNo);

			getDriver().get(url);
			List<WebElement> verseElements = getDriver().findElements(By.xpath("//span[@class='bcv']"));
			List<String> verses = new ArrayList<String>();

			for (int verseIdx = 0; verseIdx < numVerses; verseIdx++) {
				verses.add(verseElements.get(verseIdx).findElement(By.xpath("..")).getText());
			}

			versesByChapterList.add(verses);
		}
		return versesByChapterList;
	}

	public void getCommentariesAndLinksForBook(String usccbContentUrl, String bookName, int numChapters,
			int[] numVersesByChapter, List<List<String>> fnByVerses, List<List<String>> enByVerses) {
		List<List<String>> versesByChapterList = new ArrayList<List<String>>();

		for (int chapterIdx = 0; chapterIdx < numChapters; chapterIdx++) {
			int chapterNo = chapterIdx + 1;
			String url = String.format("%s/%s/%d", usccbContentUrl, TestUtils.compressLowercase(bookName), chapterNo);

			getDriver().get(url);
			{
				List<WebElement> fnElements = getDriver().findElements(By.xpath("//p[@class='fn']"));
				List<String> fns = new ArrayList<String>();

				for (WebElement fnElement : fnElements) {
					fns.add(fnElement.getText());
				}

				fnByVerses.add(fns);
			}
			{
				List<WebElement> enElements = getDriver().findElements(By.xpath("//p[@class='en']"));
				List<String> ens = new ArrayList<String>();

				for (WebElement enElement : enElements) {
					ens.add(enElement.getText());
				}

				enByVerses.add(ens);
			}
		}

	}

}
