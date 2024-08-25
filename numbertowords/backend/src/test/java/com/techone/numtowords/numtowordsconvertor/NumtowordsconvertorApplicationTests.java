package com.techone.numtowords.numtowordsconvertor;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.techone.numtowords.app.NumtowordsconvertorApplication;
import com.techone.numtowords.app.controller.NumberToWordsConvController;

@SpringBootTest(classes=NumtowordsconvertorApplication.class)
class NumtowordsconvertorApplicationTests {

	@Autowired
    private NumberToWordsConvController numberToWordsConvController;
	
	@Test
	void contextLoads() {
		assertThat(numberToWordsConvController).isNotNull();
	}

	@Test
    void testConvertToWords() {
        long number = 1234567890123456789L;
        String result = numberToWordsConvController.wordsConvertor(number);
        assertThat(result).isEqualTo("ONE QUINTILLION AND TWO HUNDRED AND THIRTY-FOUR QUADRILLION AND FIVE HUNDRED AND SIXTY-SEVEN TRILLION AND EIGHT HUNDRED AND NINETY BILLION AND ONE HUNDRED AND TWENTY-THREE MILLION AND FOUR HUNDRED AND FIFTY-SIX THOUSAND AND SEVEN HUNDRED AND EIGHTY-NINE");
    }

    @Test
    void testZeroToWords() {
        long number = 0L;
        String result = numberToWordsConvController.wordsConvertor(number);
        assertThat(result).isEqualTo("ZERO");
    }

    @Test
    void testLargeNumberToWords() {
        long number = 1000000000000000000L; // One Quintillion
        String result = numberToWordsConvController.wordsConvertor(number);
        assertThat(result).isEqualTo("ONE QUINTILLION");
    }

}
