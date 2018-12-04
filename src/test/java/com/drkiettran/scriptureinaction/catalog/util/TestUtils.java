package com.drkiettran.scriptureinaction.catalog.util;

public class TestUtils {

	public static Object compressLowercase(String bookName) {
		return bookName.toLowerCase().trim().replaceAll(" ", "");
	}

}
