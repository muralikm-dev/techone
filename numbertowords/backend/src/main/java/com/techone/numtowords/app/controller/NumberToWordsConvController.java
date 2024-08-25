package com.techone.numtowords.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberToWordsConvController {
    
    @CrossOrigin(origins = "*")
    @GetMapping("/convert")
    public String numberToWordsConvertion(@RequestParam double number) {

        long dollars = (long) number;
        int cents = (int) Math.round((number - dollars) * 100);

        String dollarsPart = wordsConvertor(dollars) + " DOLLARS";
        String centsPart = (cents > 0) ? " AND " + wordsConvertor(cents) + " CENTS" : "";

        return "{\"result\": \"" + dollarsPart + centsPart + "\"}";
    }

    private final String[] NUMBER_NAMES = {
        "QUINTILLION", "QUADRILLION", "TRILLION", "BILLION", "MILLION", "THOUSAND"
    };

    private final long[] NUMBER_SCALES = generateScales();

    private long[] generateScales() {
        long[] scales = new long[NUMBER_NAMES.length];
        for (int i = 0; i < NUMBER_NAMES.length; i++) {
            scales[i] = (long) Math.pow(10, (NUMBER_NAMES.length - i) * 3);
        }
        return scales;
    }
    
    public String wordsConvertor(long number) {
        StringBuilder words = new StringBuilder();

        if (number == 0) return "ZERO";

        for (int i = 0; i < NUMBER_SCALES.length; i++) {
            if (number / NUMBER_SCALES[i] > 0) {
                if (words.length() > 0) {
                    words.append("AND ");
                }
                words.append(wordsConvertor(number / NUMBER_SCALES[i])).append(" ").append(NUMBER_NAMES[i]).append(" ");
                number %= NUMBER_SCALES[i];
            }
        }

        if (number > 0) {
            if (words.length() > 0) {
                words.append("AND ");
            }
            words.append(wordsBelowThousand(number));
        }

        return words.toString().trim();
    }

    private String wordsBelowThousand(long number) {
        String[] unitsArray = { "", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN" };
        String[] tensArray = { "", "", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY" };

        StringBuilder resultsStringBuffer = new StringBuilder();
        
        if (number / 100 > 0) {
            resultsStringBuffer.append(unitsArray[(int) (number / 100)]).append(" HUNDRED ").append("AND ");
            number %= 100;
        }

        if (number >= 20) {
            resultsStringBuffer.append(tensArray[(int) (number / 10)]);
            number %= 10;
        }

        if (number > 0) {
            if (resultsStringBuffer.length() > 0) {
                resultsStringBuffer.append("-").append(unitsArray[(int) number]);
            } else {
                resultsStringBuffer.append(unitsArray[(int) number]);
            }
        }

        return resultsStringBuffer.toString().trim();
    }
}