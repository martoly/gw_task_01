import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private static Set<String> allWords;
    private static final Set<String> ALLOWED_ONE_LETTER_WORDS = Set.of("I", "A");

    public static void solve() {
        List<String> result = WordsLoaderUtil.getWords();

        long start = System.currentTimeMillis();
        allWords = new HashSet<>(result);

        // "I" and "A" are not present as "valid" words from source.
        allWords.addAll(ALLOWED_ONE_LETTER_WORDS);

        List<String> matchingWords = new ArrayList<>();
        int nineLetterWordsCounter = 0;
        for (String word : result) {
            if (word.length() != 9) {
                continue;
            }
            nineLetterWordsCounter++;

            if (isValid(word)) {
                matchingWords.add(word);
            }
        }

        System.out.println("All 9 letter words: " + nineLetterWordsCounter);
        System.out.println("All 9 letter words which match criteria: " + matchingWords.size());
        System.out.println("=== Total running time: " + (System.currentTimeMillis() - start) + "ms ===");
    }

    private static String getCandidate(String originalWord, int i) {
        String firstPart = originalWord.substring(0, i);
        String secondPart = originalWord.substring(i + 1);
        String result = firstPart + secondPart;

        return result;
    }

    private static boolean isValid(String word) {
        if (word.length() == 1) {
            // Base case
            return ALLOWED_ONE_LETTER_WORDS.contains(word);
        }

        for (int i = 0; i < word.length(); i++) {
            String candidate = getCandidate(word, i);

            if (allWords.contains(candidate)) {
                if (isValid(candidate)) {
                    return true;
                }
            }
        }

        return false;
    }
}
