package com.drkiettran.scriptureinaction.ccc;

import java.util.List;

public class CccSteps {

	private CccPage cccPage;

	public List<String> download_ccc_pages(String cccUrl) {
		return cccPage.loadAllPages(cccUrl);
	}

}
