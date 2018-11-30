package com.drkiettran.scriptureinaction;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptureTextCollectorImpl implements ScriptureTextCollector {
	private static final Logger logger = LoggerFactory.getLogger(ScriptureTextCollector.class);

	public static final String[] SCRIPTURE_PART_NAMES = { "The Pentateuch", "The Historical Books", "The Wisdom Books",
			"The Prophetic Books", "The Gospels", "New Testament Letters", "The Catholic Letters" };

	private String website;

	private WebDriver webDriver;

	public ScriptureTextCollectorImpl(String website, WebDriver webDriver) {
		this.website = website;
		this.webDriver = webDriver;
	}

	@Override
	public List<String> getAllBookNames() {
		List<String> bookNames = new ArrayList<String>();
		for (String scripturePartName : SCRIPTURE_PART_NAMES) {
			getBookNames(bookNames, scripturePartName);
		}
		return bookNames;
	}

	private void getBookNames(List<String> bookNames, String scripturePartName) {
		webDriver.get(website);
		WebElement part = webDriver
				.findElement(By.xpath(String.format("//a[contains(text(),'%s')]", scripturePartName)));
		List<WebElement> books = null;

		if ("The Wisdom Books".equalsIgnoreCase(scripturePartName)) {
			// Someone at the Vatican messed this up --- deal with this element tag a little
			// differently
			books = part.findElements(By.xpath("../../../ul/font[1]/li"));
			books.addAll(part.findElements(By.xpath("../../../ul/li/font")));
			books.addAll(part.findElements(By.xpath("../../../ul/font[2]/li")));

		} else {
			books = part.findElements(By.xpath("../../ul/li"));

		}
		logger.info("\n\n>>> part {} # books: {}", scripturePartName, books.size());
		books.stream().forEach(book -> {
			logger.info("Book: {}", book.findElement(By.xpath("font")).getText());
			bookNames.add(book.getText());
		});

	}

}
