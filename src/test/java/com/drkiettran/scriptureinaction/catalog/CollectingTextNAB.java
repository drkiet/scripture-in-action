package com.drkiettran.scriptureinaction.catalog;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.catalog.steps.CollectingTextSteps;
import com.drkiettran.scriptureinaction.model.constants.NewAmerican;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class) 
public class CollectingTextNAB {
	public static final Logger logger = LoggerFactory.getLogger(CollectingTextNAB.class);
	private static final String USCC_BIBLE_CONTENT_URL = "http://www.usccb.org/bible";
	private static final String CATHOLIC_CONTENT_URL = "https://www.catholic.org/bible/book.php";
	private static final String VATICAN_CONTENT_URL = "http://www.vatican.va/archive/ENG0839/_INDEX.HTM";

	@Managed(driver = "chrome")
	WebDriver driver;

	@Steps
	private CollectingTextSteps collectingSteps;

	@Ignore
	public void should_verify_order_of_books_by_name() {
		List<String> firstChapterHeadings = collectingSteps.verify_order_of_books_by_name(CATHOLIC_CONTENT_URL,
				NewAmerican.NAMES_OF_ALL_BOOKS);
		for (int bookIdx = 0; bookIdx < NewAmerican.NAMES_OF_ALL_BOOKS.length; bookIdx++) {
			logger.info("heading {} - book {}", firstChapterHeadings.get(bookIdx),
					NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]);
			assertThat(firstChapterHeadings.get(bookIdx), containsString(NewAmerican.NAMES_OF_ALL_BOOKS[bookIdx]));
		}
	}

	/**
	 * file name: genesis_1_1.json
	 */
	@Test
	public void should_get_all_text_of_all_books_nab() {
		logger.info("getting all text for all books");
		collectingSteps.get_all_text_all_books_via_usccb(USCC_BIBLE_CONTENT_URL);
	}

	@Test
	public void parsingTest() {
		String text = "The Second Journey to Egypt.*\n" + "1\n" + "Now the famine in the land grew severe.\n" + "2\n"
				+ "So when they had used up all the grain they had brought from Egypt, their father said to them, “Go back and buy us a little more food.”\n"
				+ "3\n"
				+ "But Judah replied: “The man strictly warned us, ‘You shall not see me unless your brother is with you.’a\n"
				+ "4\n" + "If you are willing to let our brother go with us, we will go down to buy food for you.\n"
				+ "5\n"
				+ "But if you are not willing, we will not go down, because the man told us, ‘You shall not see me unless your brother is with you.’”b\n"
				+ "6\n"
				+ "Israel demanded, “Why did you bring this trouble on me by telling the man that you had another brother?”\n"
				+ "7\n"
				+ "They answered: “The man kept asking about us and our family: ‘Is your father still living? Do you have another brother?’ We answered him accordingly. How could we know that he would say, ‘Bring your brother down here’?”\n"
				+ "8\n"
				+ "Then Judah urged his father Israel: “Let the boy go with me, that we may be off and on our way if you and we and our children are to keep from starving to death.c\n"
				+ "9\n"
				+ "I myself will serve as a guarantee for him. You can hold me responsible for him. If I fail to bring him back and set him before you, I will bear the blame before you forever.d\n"
				+ "10\n" + "Had we not delayed, we could have been there and back twice by now!”\n" + "11\n"
				+ "Israel their father then told them: “If it must be so, then do this: Put some of the land’s best products in your baggage and take them down to the man as gifts: some balm and honey, gum and resin, and pistachios and almonds.e\n"
				+ "12\n"
				+ "Also take double the money along, for you must return the amount that was put back in the mouths of your bags; it may have been a mistake.\n"
				+ "13\n" + "Take your brother, too, and be off on your way back to the man.\n" + "14\n"
				+ "May God Almighty grant you mercy in the presence of the man, so that he may let your other brother go, as well as Benjamin. As for me, if I am to suffer bereavement, I shall suffer it.”\n"
				+ "15\n"
				+ "So the men took those gifts and double the money and Benjamin. They made their way down to Egypt and presented themselves before Joseph.\n"
				+ "16\n"
				+ "When Joseph saw them and Benjamin, he told his steward, “Take the men into the house, and have an animal slaughtered and prepared, for they are to dine with me at noon.”\n"
				+ "17\n" + "Doing as Joseph had ordered, the steward conducted the men to Joseph’s house.\n" + "18\n"
				+ "But they became apprehensive when they were led to his house. “It must be,” they thought, “on account of the money put back in our bags the first time, that we are taken inside—in order to attack us and take our donkeys and seize us as slaves.”\n"
				+ "19\n" + "So they went up to Joseph’s steward and talked to him at the entrance of the house.\n"
				+ "20\n" + "“If you please, sir,” they said, “we came down here once before to buy food.f\n" + "21\n"
				+ "But when we arrived at a night’s encampment and opened our bags, there was each man’s money in the mouth of his bag—our money in the full amount! We have now brought it back.g\n"
				+ "22\n" + "We have brought other money to buy food. We do not know who put our money in our bags.”\n"
				+ "23\n"
				+ "He replied, “Calm down! Do not fear! Your God and the God of your father must have put treasure in your bags for you. As for your money, I received it.” With that, he led Simeon out to them.\n"
				+ "24\n"
				+ "The steward then brought the men inside Joseph’s house. He gave them water to wash their feet, and gave fodder to their donkeys.\n"
				+ "25\n"
				+ "Then they set out their gifts to await Joseph’s arrival at noon, for they had heard that they were to dine there.\n"
				+ "26\n"
				+ "When Joseph came home, they presented him with the gifts they had brought inside, while they bowed down before him to the ground.\n"
				+ "27\n"
				+ "After inquiring how they were, he asked them, “And how is your aged father, of whom you spoke? Is he still alive?”h\n"
				+ "28\n"
				+ "“Your servant our father is still alive and doing well,” they said, as they knelt and bowed down.\n"
				+ "29\n"
				+ "Then Joseph looked up and saw Benjamin, his brother, the son of his mother. He asked, “Is this your youngest brother, of whom you told me?” Then he said to him, “May God be gracious to you, my son!”i\n"
				+ "30\n"
				+ "With that, Joseph hurried out, for he was so overcome with affection for his brother that he was on the verge of tears. So he went into a private room and wept there.\n"
				+ "31\n"
				+ "After washing his face, he reappeared and, now having collected himself, gave the order, “Serve the meal.”\n"
				+ "32\n"
				+ "It was served separately to him,* to the brothers, and to the Egyptians who partook of his board. Egyptians may not eat with Hebrews; that is abhorrent to them.\n"
				+ "33\n"
				+ "When they were seated before him according to their age, from the oldest to the youngest, they looked at one another in amazement;\n"
				+ "34\n"
				+ "and as portions were brought to them from Joseph’s table, Benjamin’s portion was five times as large as* anyone else’s. So they drank freely and made merry with him.\n"
				+ "* [43:1–34] The second journey to Egypt. Joseph the sage has carefully prepared the brothers for a possible reconciliation. In this chapter and the following one Judah steps forward as the hero, in contrast to chaps. 37 and 42 where Reuben was the hero. Here Judah serves as guarantee for Benjamin.\n"
				+ "* [43:32] Separately to him: that Joseph did not eat with the other Egyptians was apparently a matter of rank.\n"
				+ "* [43:34] Five times as large as: probably an idiomatic expression for “much larger than.” Cf. 45:22.\n"
				+ "a. [43:3] Gn 44:23.\n" + "b. [43:5] Gn 42:20.\n" + "c. [43:8] Gn 42:37.\n" + "d. [43:9] Gn 44:32.\n"
				+ "e. [43:11] Gn 45:23.\n" + "f. [43:20] Gn 42:3.\n" + "g. [43:21] Gn 42:27–28.\n"
				+ "h. [43:27] Tb 7:4.\n" + "i. [43:29] Gn 42:13.";
		StringTokenizer st = new StringTokenizer(text, "\n");
		List<String> content = new ArrayList<String>();
		boolean prevLineNumber = false;

		while (st.hasMoreTokens()) {
			String line = st.nextToken();
			if (line.startsWith("*[") || line.startsWith("* [") || line.startsWith("a.")) {
				break;
			}

			if (Character.isDigit(line.charAt(0))) {
				prevLineNumber = true;
			} else {
				if (prevLineNumber) {
					StringBuilder sb = new StringBuilder(content.remove(content.size() - 1));
					sb.append(' ').append(line);
					line = sb.toString();
					prevLineNumber = false;
				}
			}
			content.add(line);
		}

		for (String line : content) {
			System.out.println(line);
		}
	}

}
