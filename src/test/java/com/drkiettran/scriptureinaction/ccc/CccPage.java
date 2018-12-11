package com.drkiettran.scriptureinaction.ccc;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.util.TestUtils;

import net.serenitybdd.core.pages.PageObject;

public class CccPage extends PageObject {
	private static final Logger logger = LoggerFactory.getLogger(CccPage.class);

	public List<String> loadAllPages(String cccUrl) {
		logger.info("loading ccc from {}", cccUrl);
		List<String> pages = new ArrayList<String>();
		String tocUrl = "http://www.scborromeo.org/ccc/ccc_toc.htm";
		String indexUrl = "http://www.scborromeo.org/ccc/index"; // /a.htm
		String textUrl = "http://www.scborromeo.org/ccc/aposletr.htm";

		getDriver().get(tocUrl);
		String toc = getDriver().findElement(By.xpath("//pre")).getText();
		pages.add(toc);

		// go to the first page.
		getDriver().get(textUrl);

		for (;;) {
			pages.add(getDriver().findElement(By.xpath("//body")).getText());
			List<WebElement> wes = getDriver().findElements(By.xpath("//img[contains(@alt,'next page')]"));
			if (wes.isEmpty()) {
				logger.info("*** Done! ***");
				break;
			}
			wes.get(0).click();
			TestUtils.rest(1);
		}

		char indexLetter = 'a';

		while (true) {
			logger.info("getting idex for {}", indexLetter);

			String url = null;
			if (indexLetter == 'q') {
				url = indexUrl + "/qr.htm";
				indexLetter = 's';
			} else if (indexLetter == 'w') {
				url = indexUrl + "/wxyz.htm";
				indexLetter = 'z';
			} else {
				url = indexUrl + "/" + indexLetter + ".htm";
			}

			getDriver().get(url);

			List<WebElement> wes = getDriver().findElements(By.xpath("//div[@class='entry']"));

			if (wes.isEmpty()) {
				logger.error("*** failed on {} ***", url);
			}

			pages.add(wes.get(0).getText());

			if (indexLetter == 'z') {
				break;
			}
			indexLetter++;
			TestUtils.rest(1);
		}
		return pages;
	}

}
