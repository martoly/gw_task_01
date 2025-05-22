import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private static Set<String> allWords;

    public static void solve() {
        List<String> result = WordsLoaderUtil.getWords();

        long start = System.currentTimeMillis();
        allWords = new HashSet<>(result);

        List<String> matchingWords = new ArrayList<>();
        for (String word : result) {
            if (word.length() != 9) {
                continue;
            }
            word = "STARTLING";
            if (isValid(word)) {
                matchingWords.add(word);
            }
        }

        System.out.println("All 9 letter words: " + matchingWords.size());
        System.out.println("Total running time: " + (System.currentTimeMillis() - start) + "ms.");
    }

    private static String getCandidate(String originalWord, int i) {
        String firstPart = originalWord.substring(0, i);
        String secondPart = originalWord.substring(i + 1);
        return firstPart + secondPart;
    }

    private static boolean isValid(String word) {
        System.out.println(word);
        if (word.length() == 1) {
            // Base case
            return "A".equalsIgnoreCase(word) || "I".equalsIgnoreCase(word);
        }

        for (int i = 0; i < word.length(); i++) {
            String candidate = getCandidate(word, i);

            if (allWords.contains(candidate) && isValid(candidate)) {
                return true;
            }
        }

        return false;
    }
}
