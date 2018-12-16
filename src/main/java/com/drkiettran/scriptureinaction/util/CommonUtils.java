package com.drkiettran.scriptureinaction.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	public static List<String> loadFromFile(Path path) {
		Charset charset = Charset.forName("ISO-8859-1");
		List<String> allLines = null;

		try {
			allLines = Files.readAllLines(path, charset);
		} catch (IOException e) {
			logger.error("ERROR: {}", e);
		}
		return allLines;
	}

	public static String getUniqeId() {
		return UUID.randomUUID().toString();
	}

	public static String cleansingNumberOnly(String number) {
		logger.info("number {}", number);
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < number.length(); index++) {
			if (Character.isDigit(number.charAt(index))) {
				sb.append(number.charAt(index));
			}
		}
		logger.info("number {}", sb);
		return sb.toString();
	}

}