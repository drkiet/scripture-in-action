package com.drkiettran.scriptureinaction.catalog;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.steps.CollectingTextSteps;
import com.drkiettran.scriptureinaction.catalog.util.TestUtils;
import com.drkiettran.scriptureinaction.model.constants.RevisedStandard;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CollectingTextLV {
	public static final Logger logger = LoggerFactory.getLogger(CollectingTextLV.class);
	private static final String LV_CONTENT_URL = "http://www.drbo.org/lvb";

	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	private CollectingTextSteps collectingSteps;

	@Test
	public void should_get_all_text_of_all_books_rsv() {
		logger.info("getting all text for all books");
		collectingSteps.get_all_text_all_books_lv(LV_CONTENT_URL);
	}
}
