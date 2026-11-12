import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

public class AdvancedListOperations {

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(4, 7, 11, 4, 2, 9, 7, 13, 7, 4);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10, 7);

        IntSummaryStats(list1);
        IntSummaryStats(list2);

        int k = 2;
        List<Integer> topK = getKFrequentElements(list1, k);
        System.out.println(topK);

        List<Integer> mergedList = Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());
        System.out.println(mergedList);

        List<Integer> commonElements = list1.stream()
                .filter(n -> list2.contains(n))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(commonElements);

        List<String> words = Arrays.asList("Java", "Stream", "Programming", "Lambda");
        Map<String, Integer> stringLengthMap = words.stream()
                .collect(Collectors.toMap(Function.identity(), String::length, (v1, v2) -> v1));
        System.out.println(stringLengthMap);

        List<Integer> primes = list1.stream()
                .filter(AdvancedListOperations::isPrime)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(primes);

        List<Integer> nonPrimes = list1.stream()
                .filter(n -> !isPrime(n))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(nonPrimes);

        String sampleStr = "programming";
        Set<Character> repeatingChars = sampleStr.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println(repeatingChars);

        Set<Integer> seen = new HashSet<>();
        list1.stream()
                .filter(n -> !seen.add(n))
                .findFirst()
                .ifPresentOrElse(
                        val -> System.out.println("First repeating: " + val),
                        () -> System.out.println("No repeating elements")
                );

        words.stream()
                .max(Comparator.comparingInt(String::length))
                .ifPresent(val -> System.out.println("Longest: " + val));

        words.stream()
                .min(Comparator.comparingInt(String::length))
                .ifPresent(val -> System.out.println("Shortest: " + val));
    }

    private static void IntSummaryStats(List<Integer> list) {
        IntSummaryStatistics stats = list.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Avg: " + stats.getAverage());
    }

    private static List<Integer> getKFrequentElements(List<Integer> list, int k) {
        Map<Integer, Long> freqMap = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return freqMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static Optional<Integer> getNthMostFrequent(List<Integer> list, int n) {
        Map<Integer, Long> freqMap = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> sortedByFreq = freqMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (sortedByFreq.size() >= n) {
            return Optional.of(sortedByFreq.get(n - 1));
        }
        return Optional.empty();
    }
}