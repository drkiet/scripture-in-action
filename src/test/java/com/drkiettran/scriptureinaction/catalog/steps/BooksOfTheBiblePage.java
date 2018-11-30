package com.drkiettran.scriptureinaction.catalog.steps;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

/**
 * This class extract content from the USCCB's Books of the Bible page.
 * 
 * @author ktran
 *
 */
public class BooksOfTheBiblePage extends PageObject {
	private static final Logger logger = LoggerFactory.getLogger(BooksOfTheBiblePage.class);
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

}
