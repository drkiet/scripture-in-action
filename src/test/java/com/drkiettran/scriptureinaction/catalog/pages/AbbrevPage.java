package com.drkiettran.scriptureinaction.catalog.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

public class AbbrevPage extends PageObject {
	private static final Logger logger = LoggerFactory.getLogger(AbbrevPage.class);

	@FindBy(xpath = "//table/tbody/tr")
	private List<WebElement> rows;

	public List<String> getAbbreviationsInEnglish(String catholicResourcesAbbreviationsUrl, String[] namesOfAllBooks) {
		logger.info("getting aabreviations in english for {} at {}", namesOfAllBooks,
				catholicResourcesAbbreviationsUrl);
		getDriver().get(catholicResourcesAbbreviationsUrl);
		Hashtable<String, String> nameAbbrevTable = new Hashtable<String, String>();

		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.xpath("td"));
			String bookName = cols.get(1).getText();

			if (!Arrays.asList(namesOfAllBooks).contains(bookName)) {
				for (String name : namesOfAllBooks) {
					if (bookName.startsWith(name)) {
						bookName = name;
					}
				}
			}
			nameAbbrevTable.put(bookName, cols.get(2).getText().trim().replace("\n", " "));
		}

		List<String> abbrevsOrderedByBookNames = new ArrayList<String>();

		for (String bookName : namesOfAllBooks) {
			abbrevsOrderedByBookNames.add(nameAbbrevTable.get(bookName));
		}

		logger.info("returning {} abbreviations.", abbrevsOrderedByBookNames.size());
		return abbrevsOrderedByBookNames;
	}

}
