package yatzy.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Class that represents a user roll, composed of 5 dices
 * this class contains roll scoring logic
 * @author borhen
 *
 */
public class Roll {
	
	private IntStream dices;
	
	public Roll(int d1, int d2, int d3, int d4, int d5) {
		dices = IntStream.of(d1, d2, d3, d4, d5);
	}
	
	public Roll(int[] dice) {
		dices = Arrays.stream(dice);
	}
	
	public int sum() {
		return this.dices.sum();
	}
	
	public int sum(int diceToSum) {
		return this.dices.filter(d -> d == diceToSum).sum();
	}
	
	public long distinctDices() {
		return this.dices.distinct().count();
	}
	
	public Map<Integer, Long> groupByDice() {
		return this.dices.boxed()
                .collect(Collectors.groupingBy(
                        i -> i,             // group by dice
                        Collectors.counting() // count occurrences of each dice
                ));
	}
	
	public Stream<Entry<Integer, Long>> filterByOccurence(int occurence) {
		return this.groupByDice().entrySet().stream()
						.filter(e -> e.getValue() >= occurence);
	}
	
	public List<Integer> sort() {
		return this.dices.sorted().boxed().toList();
	}
	
	public boolean equals(List<Integer> otherDices) {
		return otherDices.equals(this.sort());
	}
	
	public int sumPairs() {
		List<Integer> dicePairs = this.filterByOccurence(2) //filter the dices that appeared at least twice
				.map(Entry::getKey)
				.toList();
    	if(dicePairs.size() == 2) {
    		return dicePairs.stream().mapToInt(dice -> dice * 2).sum();
    	}
    	return 0;
	}
	public int sumOccurences(int occurence) {
		return this.filterByOccurence(occurence)
					.map(Entry::getKey)
					.max(Comparator.naturalOrder()) //get the highest dice value
					.map(value -> value * occurence)
					.orElse(0);
	}
	
	public boolean isFullHouse() {
		Map<Integer, Long> diceCount = this.groupByDice();
		boolean tripleSide = diceCount.entrySet().stream().anyMatch(e -> e.getValue() == 3);
    	boolean twoDistinctValues = diceCount.keySet().stream().distinct().count() == 2;
    	return tripleSide && twoDistinctValues;
	}
	
}

