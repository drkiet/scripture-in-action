package com.drkiettran.scriptureinaction.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.model.constants.NewAmerican;

public class CommonUtils {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	public static List<String> loadFromFile(Path path) {
		Charset charset = Charset.forName("UTF-8");
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

	public static String getFileName(String translation, String bookName) {
		int bookNo = getBookNoByBookName(translation, bookName);
		return String.format("%02d-%s-text.txt", bookNo, bookName);
	}

	private static int getBookNoByBookName(String translation, String bookName) {
		logger.info("searching for bookIdx for {}", bookName);
		String[] bookNames = NewAmerican.NAMES_OF_ALL_BOOKS;
		for (int bookIdx = 0; bookIdx < bookNames.length; bookIdx++) {
			if (bookName.equalsIgnoreCase(bookNames[bookIdx])) {
				logger.info("found bookIdx {}", bookIdx);
				return bookIdx + 1;
			}
		}
		logger.info("*** bookIdx not found for {}", bookName);
		return 0;
	}

	public static List<String> loadLinksFromFile(Path path) {
		Charset charset = Charset.forName("ISO-8859-1");
		List<String> allLines = null;

		try {
			allLines = Files.readAllLines(path, charset);
		} catch (IOException e) {
			logger.error("ERROR: {}", e);
		}
		return allLines;
	}

	public static String getLinkFileName(String translation, String bookName) {
		int bookNo = getBookNoByBookName(translation, bookName);
		return String.format("%02d-%s-link.txt", bookNo, bookName);
	}

	/**
	 * Reference can be: <code>
	 * Ps 1:3
	 * 1 Cor 1:3
	 * 2Mc 3:4-5
	 * </code>
	 * 
	 * @param verseRef
	 * @return
	 */
	public static String extractShortNameFromReference(String verseRef) {
		logger.info("verseRef: {}", verseRef);
		// search for the last digit.
		int lastDigitIndex = -1;
		for (int index = verseRef.length() - 1; index >= 0; index--) {
			if (Character.isDigit(verseRef.charAt(index))) {
				lastDigitIndex = index;
				break;
			}
		}

		char prevChar = '.';

		for (int index = lastDigitIndex - 1; index >= 0; index--) {
			if (Character.isAlphabetic(verseRef.charAt(index))) {
				if (prevChar == ':') {
					continue;
				}
				return verseRef.substring(0, index + 1);
			}
			prevChar = verseRef.charAt(index);
		}
		return "";
	}

	public static String getCommentFileName(String translation, String bookName) {
		int bookNo = getBookNoByBookName(translation, bookName);
		return String.format("%02d-%s-comment.txt", bookNo, bookName);
	}

	public static List<String> loadCommentsFromFile(Path path) {
		return loadLinksFromFile(path);
	}

}
