import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WordsLoaderUtil {

    private static final String URL_VALUE =
            "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";

    public static List<String> getWords() {
        List<String> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new URL(URL_VALUE).openConnection().getInputStream()))) {

            result = bufferedReader.lines().skip(2).toList();
            System.out.println("Total words collected: " + result.size());

            return result;
        } catch (Exception e) {
            System.out.println("Exception occurred while loading words from source!");
            System.out.println(e.getMessage());
        }

        return result;
    }

}
