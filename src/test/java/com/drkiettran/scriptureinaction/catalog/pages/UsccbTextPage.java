package com.drkiettran.scriptureinaction.catalog.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.util.TestUtils;

import net.serenitybdd.core.pages.PageObject;

public class UsccbTextPage extends PageObject {
	private static final Logger logger = LoggerFactory.getLogger(UsccbTextPage.class);

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

	private void getContent(List<List<String>> content, String xpath) {
		List<WebElement> elements = getDriver().findElements(By.xpath(xpath));
		List<String> verses = new ArrayList<String>();

		for (WebElement element : elements) {
			verses.add(element.getText());
		}

		content.add(verses);
	}

	public void getTextAndCommentariesAndLinksForBookNAB(String usccbContentUrl, String bookName, int bookNo,
			int numChapters, int[] numVersesByChapter, List<List<String>> fnByVerses, List<List<String>> enByVerses,
			List<List<String>> textByVerses) {
		for (int chapterIdx = 0; chapterIdx < numChapters; chapterIdx++) {
			int chapterNo = chapterIdx + 1;
			String url = String.format("%s/%s/%d", usccbContentUrl, TestUtils.compressLowercase(bookName), chapterNo);

			getDriver().get(url);

			getContent(textByVerses);
			getContent(fnByVerses, "//p[@class='fn']");
			getContent(enByVerses, "//p[@class='en']");
		}
	}

	private List<List<String>> getContent(List<List<String>> textByVerses) {
		textByVerses.add(parseContent(
				getDriver().findElement(By.xpath("//div[@class='contentarea']//div[@class='contentarea']")).getText()));
		return textByVerses;
	}

	private List<String> parseContent(String text) {

		StringTokenizer st = new StringTokenizer(text, "\n");
		List<String> content = new ArrayList<String>();

		while (st.hasMoreTokens()) {
			String line = st.nextToken();
			if (lineIsCommentaryOrLinkage(line)) {
				break;
			}

			if (!Character.isDigit(line.charAt(0))) {
				if (notFirstLine(content) && prevLineNotEmpty(content)) {
					line = mergeCurToPrevLine(content, line);
				}
			}
			content.add(line);
		}
		return content;
	}

	private boolean prevLineNotEmpty(List<String> content) {
		return !content.get(content.size() - 1).isEmpty();
	}

	private boolean notFirstLine(List<String> content) {
		return !content.isEmpty();
	}

	private boolean lineIsCommentaryOrLinkage(String line) {
		return line.startsWith("*[") || line.startsWith("* [") || line.startsWith("a.");
	}

	private String mergeCurToPrevLine(List<String> content, String line) {
		StringBuilder sb = new StringBuilder(content.remove(content.size() - 1));
		sb.append(' ').append(line);
		line = sb.toString();
		return line;
	}

}
