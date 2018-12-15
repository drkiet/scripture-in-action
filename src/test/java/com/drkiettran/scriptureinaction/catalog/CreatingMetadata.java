package com.drkiettran.scriptureinaction.catalog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.model.constants.DouayRheims;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;

public class CreatingMetadata {
	private static final Logger logger = LoggerFactory.getLogger(CreatingMetadata.class);

	@Test
	public void should_store_bible_metadata_in_files() {

		//
		int startIdx = 0;
		int endIdx = NewAmerican.NUMBER_OF_ALL_BOOKS;

		String fileName = "book-names";
		String content = makeContent(NewAmerican.NAMES_OF_ALL_BOOKS, startIdx, endIdx);
		writeTextToFile(fileName, content);

		endIdx = NewAmerican.NUMBER_OF_OT_BOOKS;
		fileName = "old-testament-book-names";
		content = makeContent(NewAmerican.NAMES_OF_ALL_BOOKS, startIdx, endIdx);
		writeTextToFile(fileName, content);

		//
		startIdx = NewAmerican.NUMBER_OF_OT_BOOKS;
		endIdx = NewAmerican.NUMBER_OF_ALL_BOOKS;
		fileName = "new-testament-book-names";
		content = makeContent(NewAmerican.NAMES_OF_ALL_BOOKS, startIdx, endIdx);
		writeTextToFile(fileName, content);

		int[] lens = { NewAmerican.NUMBER_OF_PENTATEUCH_BOOKS, NewAmerican.NUMBER_OF_HISTORICAL_BOOKS,
				NewAmerican.NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS, NewAmerican.NUMBER_OF_WISDOM_BOOKS,
				NewAmerican.NUMBER_OF_PROPHETIC_BOOKS, NewAmerican.NUMBER_OF_GOSPEL_BOOKS,
				NewAmerican.NUMBER_OF_LETTER_BOOKS, NewAmerican.NUMBER_OF_CATHOLIC_LETTER_BOOKS };

		String[] collectionNames = { "Pentateuch", "Historical", "Biblical-Novellas", "Wisdom", "Prophetic", "Gospel",
				"Letter", "Catholic-Letter" };

		//
		startIdx = 0;
		endIdx = 0;
		for (int i = 0; i < lens.length; i++) {
			fileName = String.format("%s-book-name", collectionNames[i]).toLowerCase();
			endIdx += lens[i];
			content = makeContent(NewAmerican.NAMES_OF_ALL_BOOKS, startIdx, endIdx);
			writeTextToFile(fileName, content);
			startIdx += lens[i];
		}

		//
		startIdx = 0;
		endIdx = NewAmerican.NUMBER_OF_ALL_BOOKS;

		fileName = "abbrev-book-names";
		content = makeContent(NewAmerican.ABBREVIATIONS_OF_ALL_BOOKS, startIdx, endIdx);
		writeTextToFile(fileName, content);

		//
		StringBuilder sb = new StringBuilder();
		int[] numChaptersByBook = NewAmerican.NUMBER_OF_CHAPTERS_BY_BOOK_NAME;
		for (int i = 0; i < numChaptersByBook.length; i++) {
			sb.append(i + 1).append(' ').append(numChaptersByBook[i]).append('\n');
		}

		fileName = "number-of-chapters-by-book";
		writeTextToFile(fileName, sb.toString());

		//
		sb = new StringBuilder();
		int[][] numVersesByBook = NewAmerican.NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME;
		for (int i = 0; i < numVersesByBook.length; i++) {
			sb.append(i + 1);
			for (int verses : numVersesByBook[i]) {
				sb.append(' ').append(verses).append(",");
			}
			sb.append('\n');
		}

		fileName = "number-of-verses-by-chapter-by-book";
		writeTextToFile(fileName, sb.toString());

	}

	private String makeContent(String[] content, int start, int end) {
		StringBuilder sb = new StringBuilder();

		for (int i = start; i < end; i++) {
			sb.append(i + 1).append(' ').append(content[i]).append('\n');
		}
		return sb.toString();
	}

	private void writeTextToFile(String fileName, String text) {
		fileName = String.format("C:/book-catalog/bibles/metadata/%s.txt", fileName);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
			bw.write(text);
		} catch (IOException e) {
			logger.error("Error: {}", e);
		}

		return;
	}

	@Test
	public void prefixZeroToFileNames() {
		String naFolder = System.getProperty("sia.bible.nab.text.folder");
		String drFolder = System.getProperty("sia.bible.dr.text.folder");
		String lvFolder = System.getProperty("sia.bible.lv.text.folder");
		String rsFolder = System.getProperty("sia.bible.rsv.text.folder");

		File naDir = new File(naFolder);
		File drDir = new File(drFolder);
		File lvDir = new File(lvFolder);
		File rsDir = new File(rsFolder);

		File[] singleDigitFiles = insertZero(naDir);
		singleDigitFiles = insertZero(drDir);
		singleDigitFiles = insertZero(lvDir);
		singleDigitFiles = insertZero(rsDir);
	}

	private File[] insertZero(File dir) {
		File[] singleDigitFiles = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.charAt(1) == '-';
			}
		});
		for (File singleDigitFile : singleDigitFiles) {
			StringBuilder newName = new StringBuilder(singleDigitFile.getName());
			newName.insert(0, "0");
			renameFile(singleDigitFile, newName.toString());
			logger.info("old {} - new {}", singleDigitFile.getName(), newName);
		}

		return singleDigitFiles;
	}

	
	@Test
	public void mapNa2Dr() {
		for (int bookIdx = 0; bookIdx < DouayRheims.na2dr.length; bookIdx++) {
			System.out.println(String.format("na.%02d <==> dr.%02d", bookIdx, DouayRheims.na2dr[bookIdx]));
		}

		String naFolder = System.getProperty("sia.bible.nab.text.folder");
		String drFolder = System.getProperty("sia.bible.dr.text.folder");
		String lvFolder = System.getProperty("sia.bible.lv.text.folder");

		File naDir = new File(naFolder);
		File drDir = new File(drFolder);
		File lvDir = new File(lvFolder);

		String textFilter = "-text.txt";
		File[] naTextFiles = getListFilesByType(naDir, textFilter);
		File[] drTextFiles = getListFilesByType(drDir, textFilter);
		File[] lvTextFiles = getListFilesByType(lvDir, textFilter);
		Arrays.sort(naTextFiles, new SortByFileName());
		Arrays.sort(drTextFiles, new SortByFileName());
		Arrays.sort(lvTextFiles, new SortByFileName());

		StringBuilder sb = new StringBuilder("\nnaTextFile -> drTextFile:\n");

		for (int naBookIdx = 0; naBookIdx < naTextFiles.length; naBookIdx++) {

			int drBookIdx = DouayRheims.na2dr[naBookIdx];
			String newName = naTextFiles[naBookIdx].getName();
			sb.append(naTextFiles[naBookIdx].getName()).append(" -> ").append(drTextFiles[drBookIdx].getName());
			sb.append(" -> ").append(naTextFiles[naBookIdx].getName()).append('\n');

			renameFile(drTextFiles[drBookIdx], newName);
		}

		logger.info("dr file names map:{}", sb);

		sb = new StringBuilder("\nnaTextFile -> lvTextFile:\n");

		for (int naBookIdx = 0; naBookIdx < naTextFiles.length; naBookIdx++) {

			int lvBookIdx = DouayRheims.na2dr[naBookIdx];
			String newName = naTextFiles[naBookIdx].getName();
			sb.append(naTextFiles[naBookIdx].getName()).append(" -> ").append(lvTextFiles[lvBookIdx].getName());
			sb.append(" -> ").append(naTextFiles[naBookIdx].getName()).append('\n');

			renameFile(lvTextFiles[lvBookIdx], newName);
		}

		logger.info("lv file names map:{}", sb);

		// Commentary --
		textFilter = "-comment.txt";
		naTextFiles = getListFilesByType(naDir, textFilter);
		drTextFiles = getListFilesByType(drDir, textFilter);
		lvTextFiles = getListFilesByType(lvDir, textFilter);
		Arrays.sort(naTextFiles, new SortByFileName());
		Arrays.sort(drTextFiles, new SortByFileName());
		Arrays.sort(lvTextFiles, new SortByFileName());

		sb = new StringBuilder("\nnaCommentFile -> drCommentFile:\n");

		for (int naBookIdx = 0; naBookIdx < naTextFiles.length; naBookIdx++) {

			int drBookIdx = DouayRheims.na2dr[naBookIdx];
			String newName = naTextFiles[naBookIdx].getName();
			sb.append(naTextFiles[naBookIdx].getName()).append(" -> ").append(drTextFiles[drBookIdx].getName());
			sb.append(" -> ").append(naTextFiles[naBookIdx].getName()).append('\n');

			renameFile(drTextFiles[drBookIdx], newName);
		}

		logger.info("dr file names map:{}", sb);

		sb = new StringBuilder("\nnaCommentFile -> lvCommentFile:\n");

		for (int naBookIdx = 0; naBookIdx < naTextFiles.length; naBookIdx++) {

			int lvBookIdx = DouayRheims.na2dr[naBookIdx];
			String newName = naTextFiles[naBookIdx].getName();
			sb.append(naTextFiles[naBookIdx].getName()).append(" -> ").append(lvTextFiles[lvBookIdx].getName());
			sb.append(" -> ").append(naTextFiles[naBookIdx].getName()).append('\n');

			renameFile(lvTextFiles[lvBookIdx], newName);
		}

		logger.info("lv file names map:{}", sb);

	}

	private File[] getListFilesByType(File naDir, String filter) {
		File[] textFiles = naDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(filter) && !name.contains("-abcdef-");
			}
		});
		return textFiles;
	}

	@Test
	public void renameFiles() {
		String folder = "/media/kiet/toshiba1/Ebooks/bibles/dr";
		File dir = new File(folder);
		for (File file : dir.listFiles()) {

			String oldName = file.getName();
			String newName = oldName.replaceAll("First ", "");
			newName = newName.replaceAll("Second ", "");
			newName = newName.replaceAll("Third ", "");
			newName = newName.replaceAll("Fourth ", "");
			newName = newName.replaceAll("Book Of ", "");
			newName = newName.replaceAll("Prophecy Of ", "");
			newName = newName.replaceAll("Epistle Of ", "");
			newName = newName.replaceAll("Gospel According to Saint ", "");
			newName = newName.replaceAll("Saint Paul ", "");
			newName = newName.replaceAll("Canticle Of Canticles _Song Of Solomon_", "Song of Songs");
			newName = newName.replaceAll("Kings _", "");
			newName = newName.replaceAll("1 Paralipomenon _", "");
			newName = newName.replaceAll("Osee _", "");
			newName = newName.replaceAll("Ezechiel _", "");
			newName = newName.replaceAll("Abdias _", "");
			newName = newName.replaceAll("Esdras _", "");
			newName = newName.replaceAll("Tobias _", "");
			newName = newName.replaceAll("Aggeus _", "");
			newName = newName.replaceAll("To The ", "");
			newName = newName.replaceAll("To ", "");

			newName = newName.replaceAll("Ecclesiastes", "Sirach");
			newName = newName.replaceAll("Lamentations Of ", "");
			newName = newName.replaceAll("Micheas _", "");
			newName = newName.replaceAll("Habacuc _", "");
			newName = newName.replaceAll("Sophonias _", "");
			newName = newName.replaceAll("Zacharias _", "");
			newName = newName.replaceAll("Malachias _", "");
			newName = newName.replaceAll("The Acts Of The Apostles", "Acts");
			newName = newName.replaceAll("Saint ", "");
			newName = newName.replaceAll("Josue _", "");
			newName = newName.replaceAll("Jonas _", "");
			newName = newName.replaceAll("Jeremias _", "");
			newName = newName.replaceAll("Isaias _", "");

			newName = newName.replaceAll("_-", "-");
			renameFile(file, newName);

			logger.info("old {}, new {}", newName, oldName);

//			file.renameTo(newFile);
		}
	}

	public static boolean renameFile(File toBeRenamed, String new_name) {
		// need to be in the same path
		File fileWithNewName = new File(toBeRenamed.getParent(), new_name);
		if (fileWithNewName.exists()) {
			return false;
		}
		// Rename file (or directory)
		return toBeRenamed.renameTo(fileWithNewName);
	}

}

class SortByFileName implements Comparator<File> {
	public int compare(File file1, File file2) {
		return file1.getName().compareToIgnoreCase(file2.getName());
	}
}
