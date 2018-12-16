package com.drkiettran.scriptureinaction.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class VersePointerTest {

	private static final String[][] testShortNames = {
			/** test 1 **/
			{ "Mt 1:1", "Matthew", "1", "1", "1" },
			/** test 2 **/
			{ "1 Cor 2:10-15", "1 Corinthians", "2", "10", "15" },
			/** test 3 **/
			{ "1Mc 3:3-5", "1 Maccabees", "3", "3", "5" },
			/** test 3 **/
			{ "1 Macc 3:3-5", "1 Maccabees", "3", "3", "5" },
			/** test 3 **/
			{ "2 Chr 3:3-5", "2 Chronicles", "3", "3", "5" },
			/** test 3 **/
			{ "2 Chron 3:3-5", "2 Chronicles", "3", "3", "5" },
			/** test 3 **/
			{ "2Ch 3:3-5", "2 Chronicles", "3", "3", "5" } };

	@Before
	public void setUp() {
	}

	@Test
	public void shouldParseString() {
		VersePointer vp = new VersePointer();
		for (String[] testShortName : testShortNames) {
			vp.parse(testShortName[0]);

			assertThat(vp.getBookName(), equalTo(testShortName[1]));
			assertThat(vp.getChapterNumber(), equalTo(Integer.valueOf(testShortName[2])));
			assertThat(vp.getStartingVerseNumber(), equalTo(Integer.valueOf(testShortName[3])));
			assertThat(vp.getEndingVerseNumber(), equalTo(Integer.valueOf(testShortName[4])));
			assertThat(vp.toString(), equalTo(testShortName[0]));
		}
	}
}
