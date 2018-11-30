package com.drkiettran.scriptureinaction;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverHandler {
	private ChromeDriverService service;
	private RemoteWebDriver webDriver;
	private String webSiteURL;

	public WebDriverHandler() throws IOException {
		String chromePath = System.getenv("WEBDRIVER_CHROME_DRIVER");
		// @formatter:off
		service = new ChromeDriverService.Builder().usingDriverExecutable(new File(chromePath)).usingAnyFreePort()
				.build();
		// @formatter:on
		service.start();
		webDriver = new RemoteWebDriver(getUrl(), DesiredCapabilities.chrome());
	}

	public void stopChromeDriverService() {
		service.stop();
	}

	public URL getUrl() {
		return service.getUrl();
	}

	public RemoteWebDriver getRemoteWebDriver() {
		return webDriver;
	}

	public void quit() {
		webDriver.quit();
	}

	public void setWebSiteURL(String webSiteURL) {
		this.webSiteURL = webSiteURL;
	}

	public String getWebSiteURL() {
		return webSiteURL;
	}

}