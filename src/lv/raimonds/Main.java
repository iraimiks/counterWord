package lv.raimonds;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] wordArray = readFileAndSplitIntoSeperateWords(args[0]);
        Map<String, Integer> wordFrequencies = findWordFrequencies(wordArray);
        printWordFrequenciesPopularFirst(wordFrequencies);
    }

    private static String[] readFileAndSplitIntoSeperateWords(String pathOfFile) throws IOException {
        String wordArray = new String(Files.readAllBytes(Paths.get(pathOfFile)), "UTF-8");
        String[] split = wordArray.split("[:.,!?*\\s\\\\-]+");
        return split;
    }

    private static Map<String, Integer> findWordFrequencies(String[] array) {
        Map<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (hashMap.containsKey(array[i])) {
                hashMap.put(array[i], hashMap.get(array[i]) + 1);
            } else {
                hashMap.put(array[i], 1);
            }
        }
        return hashMap;
    }

    private static void printWordFrequenciesPopularFirst(Map<String, Integer> hashMap) {
        System.out.println("Sorted word popular words desc:");
        hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }
}
