import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Yatzy {

    public static int chance(int d1, int d2, int d3, int d4, int d5)
    {
    	return IntStream.of(d1, d2, d3, d4, d5).sum();
    }

    public static int yatzy(int... dice)
    {
    	return IntStream.of(dice).distinct().count() == 1 ? 50 : 0;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
    	return IntStream.of(d1, d2, d3, d4, d5).filter(d -> d == 1).sum();
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
    	return IntStream.of(d1, d2, d3, d4, d5).filter(d -> d == 2).sum();
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
    	return IntStream.of(d1, d2, d3, d4, d5).filter(d -> d == 3).sum();
    }

    protected int[] dice;
    public Yatzy(int d1, int d2, int d3, int d4, int _5)
    {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = _5;
    }

    public int fours()
    {
    	return Arrays.stream(dice).filter(d -> d == 4).sum();
    }

    public int fives()
    {
    	return Arrays.stream(dice).filter(d -> d == 5).sum();
    }

    public int sixes()
    {
    	return Arrays.stream(dice).filter(d -> d == 6).sum();
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5)
    {
    	List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);
    	Map<Integer, Long> sideCount = dices.stream()
                .collect(Collectors.groupingBy(
                        i -> i,             // group by dice
                        Collectors.counting() // count occurrences of each dice
                ));
    	return sideCount.entrySet().stream()
		    			.filter(e -> e.getValue() > 1) //filter the dices that appeared more than once
		    			.map(Entry::getKey)
		    			.max(Comparator.naturalOrder()) //get the highest dice value
		    			.map(value -> value * 2)
		    			.orElse(0);
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
    	List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);
    	Map<Integer, Long> sideCount = dices.stream()
                .collect(Collectors.groupingBy(
                        i -> i,             // group by dice
                        Collectors.counting() // count occurrences of each dice
                ));
    	List<Integer> dicePairs = sideCount.entrySet().stream()
				.filter(e -> e.getValue() > 1) //filter the dices that appeared more than once
				.map(Entry::getKey)
				.collect(Collectors.toList());
    	if(dicePairs.size() == 2) {
    		return dicePairs.stream().mapToInt(dice -> dice * 2).sum();
    	}
    	return 0;
    }

    public static int four_of_a_kind(int _1, int _2, int d3, int d4, int d5)
    {
    	List<Integer> dices = Arrays.asList(_1, _2, d3, d4, d5);
    	Map<Integer, Long> sideCount = dices.stream()
                .collect(Collectors.groupingBy(
                        i -> i,             // group by dice
                        Collectors.counting() // count occurrences of each dice
                ));
    	return sideCount.entrySet().stream()
		    			.filter(e -> e.getValue() > 3) //filter the dices that appeared more than once
		    			.findFirst()
		    			.map(Entry::getKey)
		    			.map(value -> value * 4)
		    			.orElse(0);
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
    	List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);
    	Map<Integer, Long> sideCount = dices.stream()
                .collect(Collectors.groupingBy(
                        i -> i,             // group by dice
                        Collectors.counting() // count occurrences of each dice
                ));
    	return sideCount.entrySet().stream()
		    			.filter(e -> e.getValue() > 2) //filter the dices that appeared more than once
		    			.findFirst()
		    			.map(Entry::getKey)
		    			.map(value -> value * 3)
		    			.orElse(0);
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
    	List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);
    	dices.sort(Comparator.naturalOrder());
    	return Arrays.asList(1, 2, 3, 4, 5).equals(dices) ? 15 : 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
    	List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);
    	dices.sort(Comparator.naturalOrder());
    	return Arrays.asList(2, 3, 4, 5, 6).equals(dices) ? 20 : 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
    	List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);
    	Map<Integer, Long> sideCount = dices.stream()
                .collect(Collectors.groupingBy(
                        i -> i,             // group by dice
                        Collectors.counting() // count occurrences of each dice
                ));
    	boolean tripleSide = sideCount.entrySet().stream().anyMatch(e -> e.getValue() == 3);
    	boolean twoDistinctValues = dices.stream().distinct().count() == 2;
    	return tripleSide && twoDistinctValues ? IntStream.of(d1, d2, d3, d4, d5).sum() : 0;
    }
}
