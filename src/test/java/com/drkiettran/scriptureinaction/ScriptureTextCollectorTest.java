package com.drkiettran.scriptureinaction;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptureTextCollectorTest {
	private static final Logger logger = LoggerFactory.getLogger(ScriptureTextCollector.class);
	private static final String VATICAN_NAB_WEBSITE = "http://www.vatican.va/archive/ENG0839/_INDEX.HTM";
	private WebDriver webDriver;
	private WebDriverHandler webDriverHandler;

	@Before
	public void setUp() {
		try {
			webDriverHandler = new WebDriverHandler();
		} catch (IOException e) {
			logger.info("Error in creating WebDriverHandler(): {}", e);
		}
		webDriver = webDriverHandler.getRemoteWebDriver();
	}

	@After
	public void tearDown() {
		webDriverHandler.stopChromeDriverService();
	}

	@Test
	public void shouldVerifyNumberOfBookInBible() {
		String website = VATICAN_NAB_WEBSITE;
		ScriptureTextCollector stc = new ScriptureTextCollectorImpl(website, webDriver);

		List<String> bookNames = stc.getAllBookNames();

		assertThat(bookNames.size(), equalTo(73));
	}
}
