package com.drkiettran.scriptureinaction.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUtils {
	private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);

	public static Object compressLowercase(String bookName) {
		return bookName.toLowerCase().trim().replaceAll(" ", "");
	}

	public static void writeAllLines(Path path, byte[] bytes) {
		try {
			Files.write(path, bytes, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			logger.error("ERROR: {}", e);
		}
	}

	public static List<String> readAllLines(Path path) {
		Charset charset = Charset.forName("ISO-8859-1");
		List<String> allLines = null;

		try {
			allLines = Files.readAllLines(path, charset);
		} catch (IOException e) {
			logger.error("ERROR: {}", e);
		}
		return allLines;
	}

	public static void restLittle(int delayInSecs) {
		long delayInMillis = (long) (Math.random() * delayInSecs) * 1000L;

		try {
			Thread.sleep(delayInMillis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void makeArrayOfAllBookNames(List<String> bookNames, String constantName) {
		StringBuilder sb = new StringBuilder(constantName).append('\n');
		for (int bookIdx = 0; bookIdx < bookNames.size(); bookIdx++) {

			String bookName = bookNames.get(bookIdx).replace('(', '_').replace(')', '_');
			sb.append("\t").append("/** ").append(bookIdx + 1).append(" **/ ").append('"').append(bookName).append('"')
					.append(",\n");
		}

		sb.deleteCharAt(sb.length() - 2);
		sb.append("};\n");
		logger.info("generated code as: \n {}", sb);
	}

	public static String underscoreUppercase(String bookName) {
		return bookName.toUpperCase().trim().replaceAll(" ", "_");
	}

	public static String makeArrayOfInteger(String arrayName, List<Integer> integers) {
		StringBuilder sb = new StringBuilder(String.format("public static final int[] %s = {", arrayName));

		for (int idx = 0; idx < integers.size(); idx++) {
			sb.append('\t').append("/** ").append(idx).append(" **/ ").append(integers.get(idx)).append(",\n");
		}

		sb.deleteCharAt(sb.length() - 2).append("};\n");
		return sb.toString();
	}

	public static String makeArrayOfInteger(String arrayName, int[] integers) {
		StringBuilder sb = new StringBuilder(String.format("public static final int[] %s = {", arrayName));

		for (int idx = 0; idx < integers.length; idx++) {
			sb.append('\t').append("/** ").append(idx).append(" **/ ").append(integers[idx]).append(",\n");
		}

		sb.deleteCharAt(sb.length() - 2).append("};\n");
		return sb.toString();
	}

	public static String makeArrayOfListOfInteger(String arrayName, String[] bookNames, List<Integer[]> integerList) {
		StringBuilder sb = new StringBuilder(String.format("public static final int[][] %s = {\n", arrayName));

		for (int listIdx = 0; listIdx < integerList.size(); listIdx++) {
			Integer[] versesPerBook = integerList.get(listIdx);
			sb.append("/** ").append('(').append(listIdx).append(')');
			sb.append(bookNames[listIdx]).append(" **/ {");

			for (int versesPerChapter : versesPerBook) {
				sb.append(versesPerChapter).append(',');
			}

			sb.deleteCharAt(sb.length() - 1).append("},\n");
		}

		sb.deleteCharAt(sb.length() - 2).append("};\n");
		return sb.toString();
	}

	public static void writeTextToFile(int bookNo, String bookName, String contentType, String text, String folder) {
		String bookFileName = String.format("%s/%d-%s-%s.txt", folder, bookNo, bookName, contentType);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(bookFileName))) {
			bw.write(text);
		} catch (IOException e) {
			logger.error("Error: {}", e);
		}

		return;
	}

	public static void rest(int delayInSecs) {
		long delayInMillis = (long) (delayInSecs) * 1000L;

		try {
			Thread.sleep(delayInMillis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
