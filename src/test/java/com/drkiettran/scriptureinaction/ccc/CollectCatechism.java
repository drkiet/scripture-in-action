package com.drkiettran.scriptureinaction.ccc;

import java.util.List;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.util.TestUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectCatechism {
	private static final Logger logger = LoggerFactory.getLogger(CollectCatechism.class);
	private static final String CCC_URL = System.getProperty("sia.ccc.url");

	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	private CccSteps cccSteps;

	@Ignore
	public void load_ccc() {
		List<String> pages = cccSteps.download_ccc_pages(CCC_URL);
		StringBuilder sb = new StringBuilder("*** the catechism of the catholic church (ccc) ***\n\n");
		sb.append("\n*** toc ***\n\n").append(pages.get(0));

		for (int pageNo = 1; pageNo < pages.size(); pageNo++) {
			sb.append("\n*** page ").append(pageNo).append("***\n\n");
			sb.append(pages.get(pageNo));
		}

		logger.info("Content ... \n{}", sb);
		TestUtils.writeTextToFile(0, "ccc", "text", sb.toString(), System.getProperty("sia.ccc.text.folder"));
	}
}
