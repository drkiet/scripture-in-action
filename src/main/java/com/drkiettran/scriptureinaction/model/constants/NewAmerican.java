package com.drkiettran.scriptureinaction.model.constants;

import java.util.Arrays;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.model.BibleBook;

public interface NewAmerican {
	public static final Logger logger = LoggerFactory.getLogger(NewAmerican.class);

	String OT_NAME = "Old Testament";
	String NT_NAME = "New Testament";
	public static final String[] NAMES_OF_ALL_BOOKS = {
			/** 1 **/
			"Genesis",
			/** 2 **/
			"Exodus",
			/** 3 **/
			"Leviticus",
			/** 4 **/
			"Numbers",
			/** 5 **/
			"Deuteronomy",
			/** 6 **/
			"Joshua",
			/** 7 **/
			"Judges",
			/** 8 **/
			"Ruth",
			/** 9 **/
			"1 Samuel",
			/** 10 **/
			"2 Samuel",
			/** 11 **/
			"1 Kings",
			/** 12 **/
			"2 Kings",
			/** 13 **/
			"1 Chronicles",
			/** 14 **/
			"2 Chronicles",
			/** 15 **/
			"Ezra",
			/** 16 **/
			"Nehemiah",
			/** 17 **/
			"Tobit",
			/** 18 **/
			"Judith",
			/** 19 **/
			"Esther",
			/** 20 **/
			"1 Maccabees",
			/** 21 **/
			"2 Maccabees",
			/** 22 **/
			"Job",
			/** 23 **/
			"Psalms",
			/** 24 **/
			"Proverbs",
			/** 25 **/
			"Ecclesiastes",
			/** 26 **/
			"Song of Songs",
			/** 27 **/
			"Wisdom",
			/** 28 **/
			"Sirach",
			/** 29 **/
			"Isaiah",
			/** 30 **/
			"Jeremiah",
			/** 31 **/
			"Lamentations",
			/** 32 **/
			"Baruch",
			/** 33 **/
			"Ezekiel",
			/** 34 **/
			"Daniel",
			/** 35 **/
			"Hosea",
			/** 36 **/
			"Joel",
			/** 37 **/
			"Amos",
			/** 38 **/
			"Obadiah",
			/** 39 **/
			"Jonah",
			/** 40 **/
			"Micah",
			/** 41 **/
			"Nahum",
			/** 42 **/
			"Habakkuk",
			/** 43 **/
			"Zephaniah",
			/** 44 **/
			"Haggai",
			/** 45 **/
			"Zechariah",
			/** 46 **/
			"Malachi",
			/** 47 **/
			"Matthew",
			/** 48 **/
			"Mark",
			/** 49 **/
			"Luke",
			/** 50 **/
			"John",
			/** 51 **/
			"Acts",
			/** 52 **/
			"Romans",
			/** 53 **/
			"1 Corinthians",
			/** 54 **/
			"2 Corinthians",
			/** 55 **/
			"Galatians",
			/** 56 **/
			"Ephesians",
			/** 57 **/
			"Philippians",
			/** 58 **/
			"Colossians",
			/** 59 **/
			"1 Thessalonians",
			/** 60 **/
			"2 Thessalonians",
			/** 61 **/
			"1 Timothy",
			/** 62 **/
			"2 Timothy",
			/** 63 **/
			"Titus",
			/** 64 **/
			"Philemon",
			/** 65 **/
			"Hebrews",
			/** 66 **/
			"James",
			/** 67 **/
			"1 Peter",
			/** 68 **/
			"2 Peter",
			/** 69 **/
			"1 John",
			/** 70 **/
			"2 John",
			/** 71 **/
			"3 John",
			/** 72 **/
			"Jude",
			/** 73 **/
			"Revelation" };
	String[] NAMES_OF_OT_BOOKS = { "Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua", "Judges",
			"Ruth", "1 Samuel", "2 Samuel", "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles", "Ezra", "Nehemiah",
			"Tobit", "Judith", "Esther", "1 Maccabees", "2 Maccabees", "Job", "Psalms", "Proverbs", "Ecclesiastes",
			"Song of Songs", "Wisdom", "Sirach", "Isaiah", "Jeremiah", "Lamentations", "Baruch", "Ezekiel", "Daniel",
			"Hosea", "Joel", "Amos", "Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah", "Haggai",
			"Zechariah", "Malachi" };
	String[] NAMES_OF_NT_BOOKS = { "Matthew", "Mark", "Luke", "John", "Acts", "Romans", "1 Corinthians",
			"2 Corinthians", "Galatians", "Ephesians", "Philippians", "Colossians", "1 Thessalonians",
			"2 Thessalonians", "1 Timothy", "2 Timothy", "Titus", "Philemon", "Hebrews", "James", "1 Peter", "2 Peter",
			"1 John", "2 John", "3 John", "Jude", "Revelation" };
	int NUMBER_OF_OT_BOOKS = NAMES_OF_OT_BOOKS.length;
	int NUMBER_OF_NT_BOOKS = NAMES_OF_NT_BOOKS.length;
	int NUMBER_OF_ALL_BOOKS = NAMES_OF_ALL_BOOKS.length;
	public static final String[] NAMES_OF_COLLECTIONS = {
			/** 1 **/
			"Preface",
			/** 2 **/
			"The Pentateuch",
			/** 3 **/
			"Historical Introduction",
			/** 4 **/
			"Biblical Novellas",
			/** 5 **/
			"Wisdom Books",
			/** 6 **/
			"Prophetic Books",
			/** 7 **/
			"New Testament",
			/** 8 **/
			"Gospels Introduction",
			/** 9 **/
			"New Testament Letters",
			/** 10 **/
			"Catholic Letters" };
	String[] NAMES_OF_BOOKS_BY_COLLECTIONS = { "Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua",
			"Judges", "Ruth", "1 Samuel", "2 Samuel", "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles", "Ezra",
			"Nehemiah", "Tobit", "Judith", "Esther", "1 Maccabees", "2 Maccabees", "Job", "Psalms", "Proverbs",
			"Ecclesiastes", "Song of Songs", "Wisdom", "Sirach", "Isaiah", "Jeremiah", "Lamentations", "Baruch",
			"Ezekiel", "Daniel", "Hosea", "Joel", "Amos", "Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah",
			"Haggai", "Zechariah", "Malachi", "Matthew", "Mark", "Luke", "John", "Acts", "Romans", "1 Corinthians",
			"2 Corinthians", "Galatians", "Ephesians", "Philippians", "Colossians", "1 Thessalonians",
			"2 Thessalonians", "1 Timothy", "2 Timothy", "Titus", "Philemon", "Hebrews", "James", "1 Peter", "2 Peter",
			"1 John", "2 John", "3 John", "Jude", "Revelation" };
	String[] NAMES_OF_PENTATEUCH_BOOKS = { "Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy" };
	public static final String[] NAMES_OF_HISTORICAL_BOOKS = { "Joshua", "Judges", "Ruth", "1 Samuel", "2 Samuel",
			"1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles", "Ezra", "Nehemiah" };

	String[] NAMES_OF_BIBLICAL_NOVELLAS_BOOKS = { "Tobit", "Judith", "Esther", "1 Maccabees", "2 Maccabees" };
	String[] NAMES_OF_WISDOM_BOOKS = { "Job", "Psalms", "Proverbs", "Ecclesiastes", "Song of Songs", "Wisdom",
			"Sirach" };
	String[] NAMES_OF_PROPHETIC_BOOKS = { "Isaiah", "Jeremiah", "Lamentations", "Baruch", "Ezekiel", "Daniel", "Hosea",
			"Joel", "Amos", "Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah", "Haggai", "Zechariah",
			"Malachi" };
	String[] NAMES_OF_GOSPEL_BOOKS = { "Matthew", "Mark", "Luke", "John", "Acts" };
	String[] NAMES_OF_LETTER_BOOKS = { "Romans", "1 Corinthians", "2 Corinthians", "Galatians", "Ephesians",
			"Philippians", "Colossians", "1 Thessalonians", "2 Thessalonians", "1 Timothy", "2 Timothy", "Titus",
			"Philemon", "Hebrews" };
	String[] NAMES_OF_CATHOLIC_LETTER_BOOKS = { "James", "1 Peter", "2 Peter", "1 John", "2 John", "3 John", "Jude",
			"Revelation" };
	int NUMBER_OF_PENTATEUCH_BOOKS = 5;
	int NUMBER_OF_HISTORICAL_BOOKS = 11;
	int NUMBER_OF_BIBLICAL_NOVELLAS_BOOKS = 5;
	int NUMBER_OF_WISDOM_BOOKS = 7;
	int NUMBER_OF_PROPHETIC_BOOKS = 18;
	int NUMBER_OF_GOSPEL_BOOKS = 5;
	int NUMBER_OF_LETTER_BOOKS = 14;
	int NUMBER_OF_CATHOLIC_LETTER_BOOKS = 8;
	String[] ABBREVIATIONS_OF_ALL_BOOKS = {
			/** Genesis **/
			"Gen,  Ge, Gn",
			/** Exodus **/
			"Exod,  Ex",
			/** Leviticus **/
			"Lev,  Lv, Le",
			/** Numbers **/
			"Num,  Nm, Nu",
			/** Deuteronomy **/
			"Deut,  Dt, De, Du",
			/** Joshua **/
			"Josh,  Jos, Jo",
			/** Judges **/
			"Judg,  Jdg, Jgs",
			/** Ruth **/
			"Ruth,  Ru",
			/** 1 Samuel **/
			"1 Sam,  1Sm, 1Sa",
			/** 2 Samuel **/
			"2 Sam,  2Sm, 2Sa",
			/** 1 Kings **/
			"1 Kgs,  1Kg, 1Ki",
			/** 2 Kings **/
			"2 Kgs,  2Kg, 2Ki",
			/** 1 Chronicles **/
			"1 Chr,  1 Chron, 1Ch",
			/** 2 Chronicles **/
			"2 Chr,  2 Chron, 2Ch",
			/** Ezra **/
			"Ezra,  Ezr",
			/** Nehemiah **/
			"Neh,  Ne",
			/** Tobit **/
			"Tob,  Tb",
			/** Judith **/
			"Jdt,  Jth",
			/** Esther **/
			"Esth,  Est, Es",
			/** 1 Maccabees **/
			"1 Macc,  1Mc, 1Ma, 1 Mc, 1 Ma",
			/** 2 Maccabees **/
			"2 Macc,  2Mc, 2Ma, 2 Mc, 2 Ma",
			/** Job **/
			"Job,  Jb",
			/** Psalms **/
			"Ps, Pss",
			/** Proverbs **/
			"Prov,  Prv, Pr",
			/** Ecclesiastes **/
			"Eccl,  Eccles, Ec, Qoh",
			/** Song of Songs **/
			"Song,  SS, So, Sg, Cant, Can",
			/** Wisdom **/
			"Wis,  Ws",
			/** Sirach **/
			"Sir, Ecclus",
			/** Isaiah **/
			"Isa,  Is",
			/** Jeremiah **/
			"Jer,  Je",
			/** Lamentations **/
			"Lam,  La",
			/** Baruch **/
			"Bar,  Ba",
			/** Ezekiel **/
			"Ezek,  Ezk, Ez",
			/** Daniel **/
			"Dan,  Dn, Da",
			/** Hosea **/
			"Hos,  Ho",
			/** Joel **/
			"Joel,  Joe, Jl",
			/** Amos **/
			"Amos,  Am",
			/** Obadiah **/
			"Obad,  Ob",
			/** Jonah **/
			"Jonah,  Jon",
			/** Micah **/
			"Mic,  Mi",
			/** Nahum **/
			"Nah,  Na",
			/** Habakkuk **/
			"Hab,  Hb",
			/** Zephaniah **/
			"Zeph,  Zep",
			/** Haggai **/
			"Hag,  Hg",
			/** Zechariah **/
			"Zech,  Zec",
			/** Malachi **/
			"Mal,  Ml",
			/** Matthew **/
			"Matt,  Mat, Mt",
			/** Mark **/
			"Mark,  Mar, Mk",
			/** Luke **/
			"Luke,  Lk, Lu",
			/** John **/
			"John,  Jn, Jo",
			/** Acts **/
			"Acts,  Ac",
			/** Romans **/
			"Rom,  Rm, Ro",
			/** 1 Corinthians **/
			"1 Cor,  1 Co, 1C",
			/** 2 Corinthians **/
			"2 Cor,  2 Co, 2C",
			/** Galatians **/
			"Gal,  Ga",
			/** Ephesians **/
			"Eph,  Ep",
			/** Philippians **/
			"Phil,  Php",
			/** Colossians **/
			"Col,  Co",
			/** 1 Thessalonians **/
			"1 Thess,  1 Thes, 1Th",
			/** 2 Thessalonians **/
			"2 Thess,  2 Thes, 2Th",
			/** 1 Timothy **/
			"1 Tim,  1 Tm, 1 Ti, 1T",
			/** 2 Timothy **/
			"2 Tim,  2 Tm, 2 Ti, 2T",
			/** Titus **/
			"Titus,  Tit, Ti",
			/** Philemon **/
			"Phlm,  Philem, Phm",
			/** Hebrews **/
			"Heb,  He",
			/** James **/
			"Jas,  Ja",
			/** 1 Peter **/
			"1 Pet,  1 Pt, 1P",
			/** 2 Peter **/
			"2 Pet,  2 Pt, 2P",
			/** 1 John **/
			"1 John,  1 Jn, 1 Jo, 1J",
			/** 2 John **/
			"2 John,  2 Jn, 2 Jo, 2J",
			/** 3 John **/
			"3 John,  3 Jn, 3 Jo, 3J",
			/** Jude **/
			"Jude,  Ju",
			/** Revelation **/
			"Rev,  Re, Rv, Apoc,  Ap" };
	/**
	 * <code>
	 * CHAPTERS
	 * 
	 * 1 Chronicles - chapters 1(54), 10(14), 11(47), 12(41), 13(14) are in error --- Could not detect #
	 * of verses for those chapters
	 *
	 * </code>
	 */

	public static final int[] NUMBER_OF_CHAPTERS_BY_BOOK_NAME = { /** 0 **/
			50,
			/** 1 **/
			40,
			/** 2 **/
			27,
			/** 3 **/
			36,
			/** 4 **/
			34,
			/** 5 **/
			24,
			/** 6 **/
			21,
			/** 7 **/
			4,
			/** 8 **/
			31,
			/** 9 **/
			24,
			/** 10 **/
			22,
			/** 11 **/
			25,
			/** 12 **/
			29,
			/** 13 **/
			36,
			/** 14 **/
			10,
			/** 15 **/
			13,
			/** 16 **/
			14,
			/** 17 **/
			16,
			/** 18 **/
			10,
			/** 19 **/
			16,
			/** 20 **/
			15,
			/** 21 **/
			42,
			/** 22 **/
			150,
			/** 23 **/
			31,
			/** 24 **/
			12,
			/** 25 **/
			8,
			/** 26 **/
			19,
			/** 27 **/
			51,
			/** 28 **/
			66,
			/** 29 **/
			52,
			/** 30 **/
			5,
			/** 31 **/
			6,
			/** 32 **/
			48,
			/** 33 **/
			14,
			/** 34 **/
			14,
			/** 35 **/
			4,
			/** 36 **/
			9,
			/** 37 **/
			1,
			/** 38 **/
			4,
			/** 39 **/
			7,
			/** 40 **/
			3,
			/** 41 **/
			3,
			/** 42 **/
			3,
			/** 43 **/
			2,
			/** 44 **/
			14,
			/** 45 **/
			3,
			/** 46 **/
			28,
			/** 47 **/
			16,
			/** 48 **/
			24,
			/** 49 **/
			21,
			/** 50 **/
			28,
			/** 51 **/
			16,
			/** 52 **/
			16,
			/** 53 **/
			13,
			/** 54 **/
			6,
			/** 55 **/
			6,
			/** 56 **/
			4,
			/** 57 **/
			4,
			/** 58 **/
			5,
			/** 59 **/
			3,
			/** 60 **/
			6,
			/** 61 **/
			4,
			/** 62 **/
			3,
			/** 63 **/
			1,
			/** 64 **/
			13,
			/** 65 **/
			5,
			/** 66 **/
			5,
			/** 67 **/
			3,
			/** 68 **/
			5,
			/** 69 **/
			1,
			/** 70 **/
			1,
			/** 71 **/
			1,
			/** 72 **/
			22 };
	Integer NUMBER_OF_ALL_CHAPTERS_FOR_ALL_BOOKS = 1328;
	/**
	 * <code>
	  * 1 Chronicles - chapters 1(54), 10(14), 11(47), 12(41), 13(14) are in error --- Could not detect #
	 * of verses for those chapters
	 *
	 * Psalms has only 150 chapters (Not 153) - chapters 151, 152, and 153 are in error ---- Could not detect # of
	 * verses for those chapters
	 *
	 * Wisdom has only 19 (Not 20) - Chapter 20 are in error --
	 *
	 * Mark has only 16 chapters (NOT 17) are in error.
	 * </code>
	 */

	int[][] NUMBER_OF_VERSES_BY_CHAPTER_BY_BOOK_NAME = {
			/** Genesis **/
			{ 31, 25, 24, 26, 32, 22, 24, 22, 29, 32, 32, 20, 18, 24, 21, 16, 27, 33, 38, 18, 34, 24, 20, 67, 34, 35,
					46, 22, 35, 43, 54, 33, 20, 31, 29, 43, 36, 30, 23, 23, 57, 38, 34, 34, 28, 34, 31, 22, 33, 26 },
			/** Exodus **/
			{ 22, 25, 22, 31, 23, 30, 29, 28, 35, 29, 10, 51, 22, 31, 27, 36, 16, 27, 25, 26, 37, 30, 33, 18, 40, 37,
					21, 43, 46, 38, 18, 35, 23, 35, 35, 38, 29, 31, 43, 38 },
			/** Leviticus **/
			{ 17, 16, 17, 35, 26, 23, 38, 36, 24, 20, 47, 8, 59, 57, 33, 34, 16, 30, 37, 27, 24, 33, 44, 23, 55, 46,
					34 },
			/** Numbers **/
			{ 54, 34, 51, 49, 31, 27, 89, 26, 23, 36, 35, 16, 33, 45, 41, 35, 28, 32, 22, 29, 35, 41, 30, 25, 18, 66,
					23, 31, 39, 17, 54, 42, 56, 29, 34, 13 },
			/** Deuteronomy **/
			{ 46, 37, 29, 49, 33, 25, 26, 20, 29, 22, 32, 31, 19, 29, 23, 22, 20, 22, 21, 20, 23, 29, 26, 22, 19, 19,
					26, 69, 28, 20, 30, 52, 29, 12 },
			/** Joshua **/
			{ 18, 24, 17, 24, 15, 27, 26, 35, 27, 43, 23, 24, 33, 15, 63, 10, 18, 28, 51, 9, 45, 34, 16, 33 },
			/** Judges **/
			{ 36, 23, 31, 24, 31, 40, 25, 35, 57, 18, 40, 15, 25, 20, 20, 31, 13, 31, 30, 48, 25 },
			/** Ruth **/
			{ 22, 23, 18, 22 },
			/** 1 Samuel **/
			{ 28, 36, 21, 22, 12, 21, 17, 22, 27, 27, 15, 25, 23, 52, 35, 23, 58, 30, 24, 42, 16, 23, 28, 23, 44, 25,
					12, 25, 11, 31, 13 },
			/** 2 Samuel **/
			{ 27, 32, 39, 12, 25, 23, 29, 18, 13, 19, 27, 31, 39, 33, 37, 23, 29, 32, 44, 26, 22, 51, 39, 25 },
			/** 1 Kings **/
			{ 53, 46, 28, 20, 32, 38, 51, 66, 28, 29, 43, 33, 34, 31, 34, 34, 24, 46, 21, 43, 29, 54 },
			/** 2 Kings **/
			{ 18, 25, 27, 44, 27, 33, 20, 29, 37, 36, 20, 22, 25, 29, 38, 20, 41, 37, 37, 21, 26, 20, 37, 20, 30 },
			/** 1 Chronicles **/
			{ 54, 55, 24, 43, 41, 66, 40, 40, 44, 14, 47, 41, 14, 17, 29, 43, 27, 17, 19, 8, 30, 19, 32, 31, 31, 32, 34,
					21, 30 },
			/** 2 Chronicles **/
			{ 18, 17, 17, 22, 14, 42, 22, 18, 31, 19, 23, 16, 23, 14, 19, 14, 19, 34, 11, 37, 20, 12, 21, 27, 28, 23, 9,
					27, 36, 27, 21, 33, 25, 33, 27, 23 },
			/** Ezra **/
			{ 11, 70, 13, 24, 17, 22, 28, 36, 15, 44 },
			/** Nehemiah **/
			{ 11, 20, 38, 17, 19, 19, 72, 18, 37, 40, 36, 47, 31 },
			/** Tobit **/
			{ 22, 14, 17, 21, 23, 17, 17, 21, 6, 13, 18, 22, 18, 15 },
			/** Judith **/
			{ 16, 28, 10, 15, 24, 21, 32, 36, 14, 23, 23, 20, 20, 19, 14, 25 },
			/** Esther **/
			{ 22, 23, 15, 17, 14, 14, 10, 17, 32, 3 },
			/** 1 Maccabees **/
			{ 64, 70, 60, 61, 68, 63, 50, 32, 73, 89, 74, 53, 53, 49, 41, 24 },
			/** 2 Maccabees **/
			{ 36, 32, 40, 50, 27, 31, 42, 36, 29, 38, 38, 46, 26, 46, 39 },
			/** Job **/
			{ 22, 13, 26, 21, 27, 30, 21, 22, 36, 21, 20, 25, 28, 22, 35, 22, 16, 21, 29, 29, 34, 30, 17, 25, 6, 14, 23,
					28, 25, 31, 40, 22, 33, 37, 16, 33, 24, 41, 30, 32, 26, 17 },
			/** Psalms **/
			{ 6, 11, 9, 9, 13, 11, 18, 10, 21, 18, 7, 9, 6, 7, 5, 11, 15, 51, 15, 10, 14, 32, 6, 10, 22, 12, 14, 9, 11,
					13, 25, 11, 22, 23, 28, 13, 40, 23, 14, 18, 14, 12, 5, 27, 18, 12, 10, 15, 21, 23, 21, 11, 7, 9, 24,
					14, 12, 12, 18, 14, 9, 13, 12, 11, 14, 20, 8, 36, 37, 6, 24, 20, 28, 23, 11, 13, 21, 72, 13, 20, 17,
					8, 19, 13, 14, 17, 7, 19, 53, 17, 16, 16, 5, 23, 11, 13, 12, 9, 9, 5, 8, 29, 22, 35, 45, 48, 43, 14,
					31, 7, 10, 10, 9, 8, 18, 19, 2, 29, 176, 7, 8, 9, 4, 8, 5, 6, 5, 6, 8, 8, 3, 18, 3, 3, 21, 26, 9, 8,
					24, 14, 10, 8, 12, 15, 21, 10, 20, 14, 9, 6 },
			/** Proverbs **/
			{ 33, 22, 35, 27, 23, 35, 27, 36, 18, 32, 31, 28, 25, 35, 33, 33, 28, 24, 29, 30, 31, 29, 35, 34, 28, 28,
					27, 28, 27, 33, 31 },
			/** Ecclesiastes **/
			{ 18, 26, 22, 17, 19, 12, 29, 17, 18, 20, 10, 14 },
			/** Song of Songs **/
			{ 17, 17, 11, 16, 16, 12, 14, 14 },
			/** Wisdom **/
			{ 16, 24, 19, 19, 23, 25, 30, 21, 18, 21, 27, 26, 19, 31, 19, 29, 21, 25, 22 },
			/** Sirach **/
			{ 28, 18, 30, 31, 15, 37, 36, 19, 18, 30, 32, 17, 25, 27, 20, 28, 28, 32, 27, 31, 28, 25, 27, 31, 25, 20,
					30, 26, 28, 25, 31, 24, 34, 31, 26, 29, 30, 34, 35, 30, 26, 25, 33, 23, 26, 20, 27, 25, 16, 29,
					30 },
			/** Isaiah **/
			{ 31, 22, 26, 6, 30, 13, 25, 23, 20, 34, 16, 6, 22, 32, 9, 14, 14, 7, 25, 6, 17, 25, 18, 23, 12, 21, 13, 29,
					24, 33, 9, 20, 24, 17, 10, 22, 38, 22, 8, 31, 29, 25, 28, 28, 25, 13, 15, 22, 26, 11, 23, 15, 12,
					17, 13, 12, 21, 14, 21, 22, 11, 12, 19, 11, 25, 24 },
			/** Jeremiah **/
			{ 19, 37, 25, 31, 31, 30, 34, 23, 25, 25, 23, 17, 27, 22, 21, 21, 27, 23, 15, 18, 14, 30, 40, 10, 38, 24,
					22, 17, 32, 24, 40, 44, 26, 22, 19, 32, 21, 28, 18, 16, 18, 22, 13, 30, 5, 28, 7, 47, 39, 46, 64,
					34 },
			/** Lamentations **/
			{ 22, 22, 66, 22, 22 },
			/** Baruch **/
			{ 22, 35, 38, 37, 9, 72 },
			/** Ezekiel **/
			{ 28, 10, 27, 17, 17, 14, 27, 18, 11, 22, 25, 28, 23, 23, 8, 63, 24, 32, 14, 44, 37, 31, 49, 27, 17, 21, 36,
					26, 21, 26, 18, 32, 33, 31, 15, 38, 28, 23, 29, 49, 26, 20, 27, 31, 25, 24, 23, 35 },
			/** Daniel **/
			{ 21, 49, 100, 34, 30, 29, 28, 27, 27, 21, 45, 13, 64, 42 },
			/** Hosea **/
			{ 9, 25, 5, 19, 15, 11, 16, 14, 17, 15, 11, 15, 15, 10 },
			/** Joel **/
			{ 20, 27, 5, 21 },
			/** Amos **/
			{ 15, 16, 15, 13, 27, 14, 17, 14, 15 },
			/** Obadiah **/
			{ 21 },
			/** Jonah **/
			{ 16, 11, 10, 11 },
			/** Micah **/
			{ 16, 13, 12, 14, 14, 16, 20 },
			/** Nahum **/
			{ 14, 14, 19 },
			/** Habakkuk **/
			{ 17, 20, 19 },
			/** Zephaniah **/
			{ 18, 15, 20 },
			/** Haggai **/
			{ 15, 23 },
			/** Zechariah **/
			{ 17, 17, 10, 14, 11, 15, 14, 23, 17, 12, 17, 14, 9, 21 },
			/** Malachi **/
			{ 14, 17, 24 },
			/** Matthew **/
			{ 25, 23, 17, 25, 48, 34, 29, 34, 38, 42, 30, 50, 58, 36, 39, 28, 27, 35, 30, 34, 46, 46, 39, 51, 46, 75,
					66, 20 },
			/** Mark **/
			{ 45, 28, 35, 41, 43, 56, 37, 38, 50, 52, 33, 44, 37, 72, 47, 20 },
			/** Luke **/
			{ 80, 52, 38, 44, 39, 49, 50, 56, 62, 42, 54, 59, 35, 35, 32, 31, 37, 43, 48, 47, 38, 71, 56, 53 },
			/** John **/
			{ 51, 25, 36, 54, 47, 71, 52, 60, 41, 42, 57, 50, 38, 31, 27, 33, 26, 40, 42, 31, 25 },
			/** Acts **/
			{ 26, 47, 26, 37, 42, 15, 60, 40, 43, 49, 30, 25, 52, 28, 41, 40, 34, 28, 40, 38, 40, 30, 35, 27, 27, 32,
					44, 31 },
			/** Romans **/
			{ 32, 29, 31, 25, 21, 23, 25, 39, 33, 21, 36, 21, 14, 23, 33, 27 },
			/** 1 Corinthians **/
			{ 31, 16, 23, 21, 13, 20, 40, 13, 27, 33, 34, 31, 13, 40, 58, 24 },
			/** 2 Corinthians **/
			{ 24, 17, 18, 18, 21, 18, 16, 24, 15, 18, 33, 21, 13 },
			/** Galatians **/
			{ 24, 21, 29, 31, 26, 18 },
			/** Ephesians **/
			{ 23, 22, 21, 32, 33, 24 },
			/** Philippians **/
			{ 30, 30, 21, 23 },
			/** Colossians **/
			{ 29, 23, 25, 18 },
			/** 1 Thessalonians **/
			{ 10, 20, 13, 18, 28 },
			/** 2 Thessalonians **/
			{ 12, 17, 18 },
			/** 1 Timothy **/
			{ 20, 15, 16, 16, 25, 21 },
			/** 2 Timothy **/
			{ 18, 26, 17, 22 },
			/** Titus **/
			{ 16, 15, 15 },
			/** Philemon **/
			{ 25 },
			/** Hebrews **/
			{ 14, 18, 19, 16, 14, 20, 28, 13, 28, 39, 40, 29, 25 },
			/** James **/
			{ 27, 26, 18, 17, 20 },
			/** 1 Peter **/
			{ 25, 25, 22, 19, 14 },
			/** 2 Peter **/
			{ 21, 22, 18 },
			/** 1 John **/
			{ 10, 29, 24, 21, 21 },
			/** 2 John **/
			{ 13 },
			/** 3 John **/
			{ 15 },
			/** Jude **/
			{ 25 },
			/** Revelation **/
			{ 20, 29, 22, 11, 14, 17, 17, 13, 21, 11, 19, 18, 18, 20, 8, 21, 18, 24, 21, 15, 27, 21 } };
	Integer NUMBER_OF_ALL_VERSES_FOR_ALL_BOOKS = 35528;

	public static final Hashtable<String, Integer> shortNameMap = new Hashtable<String, Integer>();

	/**
	 * Returns the name of a book on a abbreviated (short) name. For example, Mt
	 * returns Matthews.
	 * 
	 * @param shortName
	 * @return
	 */
	public static String getBookNameByShortName(String shortName) {
		logger.info("get name for '{}'", shortName);

		if (shortNameMap.size() == 0) {
			for (int nameIdx = 0; nameIdx < ABBREVIATIONS_OF_ALL_BOOKS.length; nameIdx++) {
				String[] abbrevNames = ABBREVIATIONS_OF_ALL_BOOKS[nameIdx].split(", ");
				for (String abbrevName : abbrevNames) {
					shortNameMap.put(abbrevName.trim(), nameIdx);
				}
			}
		}

		Integer bookIdx = shortNameMap.get(shortName);

		if (bookIdx == null) {
			bookIdx = shortNameMap.get(shortName.replace(" ", ""));
			if (bookIdx == null) {
				logger.info("'{}' get *** NOT FOUND ***", shortName);
				return "";
			}
		}

		logger.info("'{}' get {}", shortName, NAMES_OF_ALL_BOOKS[bookIdx]);

		return NAMES_OF_ALL_BOOKS[bookIdx];
	}

	public static String getType(String name) {
		if (Arrays.asList(NewAmerican.NAMES_OF_NT_BOOKS).contains(name)) {
			return BibleBook.NT;
		} else if (Arrays.asList(NewAmerican.NAMES_OF_OT_BOOKS).contains(name)) {
			return BibleBook.OT;
		}
		return "*** UNKNOWN ***";
	}

	public static String getCollectionType(String name) {
		if (Arrays.asList(NewAmerican.NAMES_OF_PENTATEUCH_BOOKS).contains(name)) {
			return BibleBook.PENTATEUCH;
		} else if (Arrays.asList(NewAmerican.NAMES_OF_HISTORICAL_BOOKS).contains(name)) {
			return BibleBook.HISTORICAL;
		} else if (Arrays.asList(NewAmerican.NAMES_OF_BIBLICAL_NOVELLAS_BOOKS).contains(name)) {
			return BibleBook.NOVELLAS;
		} else if (Arrays.asList(NewAmerican.NAMES_OF_WISDOM_BOOKS).contains(name)) {
			return BibleBook.WISDOM;
		} else if (Arrays.asList(NewAmerican.NUMBER_OF_PROPHETIC_BOOKS).contains(name)) {
			return BibleBook.PROPHETIC;
		} else if (Arrays.asList(NewAmerican.NAMES_OF_GOSPEL_BOOKS).contains(name)) {
			return BibleBook.GOSPELS;
		} else if (Arrays.asList(NewAmerican.NAMES_OF_LETTER_BOOKS).contains(name)) {
			return BibleBook.LETTERS;
		} else if (Arrays.asList(NewAmerican.NAMES_OF_CATHOLIC_LETTER_BOOKS).contains(name)) {
			return BibleBook.CATHOLIC_LETTERS;
		}
		return "*** UNKNOWN ***";
	}

	public static String getShortNameByBookName(String bookName) {
		logger.info("get short name for '{}'", bookName);
		for (int nameIdx = 0; nameIdx < NAMES_OF_ALL_BOOKS.length; nameIdx++) {
			if (bookName.equalsIgnoreCase(NAMES_OF_ALL_BOOKS[nameIdx])) {
				return ABBREVIATIONS_OF_ALL_BOOKS[nameIdx].split(",")[0].trim();
			}
		}
		return "*** NOT FOUND ***";
	}

}
