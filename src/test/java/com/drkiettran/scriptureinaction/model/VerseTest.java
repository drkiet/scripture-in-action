package com.drkiettran.scriptureinaction.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>
 * 
 * 3 Then God said: Let there be light, and there was light.c
 * 4 God saw that the light was good. God then separated the light from the darkness.
 * 5 God called the light “day,” and the darkness he called “night.” Evening came, and morning followed—the first day.*
 * 6 Then God said: Let there be a dome in the middle of the waters, to separate one body of water from the other.
 * 7 God made the dome,* and it separated the water below the dome from the water above the dome. And so it happened.d
 * 
 * </code>
 * 
 * @author kiet
 *
 */
public class VerseTest {
	private static final Logger logger = LoggerFactory.getLogger(VerseTest.class);

	private static final String[] TEST_VERSES = { "3 Then God said: Let there be light, and there was light.c",
			"5 God called the light “day,” and the darkness he called “night.” Evening came, and morning followed—the first day.*",
			"7 God made the dome,* and it separated the water below the dome from the water above the dome. And so it happened.d",
			"4 God saw that the light was good. God then separated the light from the darkness.",
			"6 Then God said: Let there be a dome in the middle of the waters, to separate one body of water from the other.",
			"", "xyz", "ab cd" };
	private static final int[] EXPECTED_VERSE_NUMBERS = { 3, 5, 7, 4, 6, -1, -1, -1 };

	@Test
	public void shouldParseVerse() {
		for (int index = 0; index < TEST_VERSES.length; index++) {
			Verse verse = new Verse();
			verse.parse(TEST_VERSES[index]);
			logger.info("verse {} has verse number {}", TEST_VERSES[index], verse.getVerseNumber());
			assertThat(verse.getVerseNumber(), equalTo(EXPECTED_VERSE_NUMBERS[index]));
		}
	}
}
