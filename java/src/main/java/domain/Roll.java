package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Class that represents a user roll, composed of 5 dices
 * this class contains utility methods
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
						.filter(e -> e.getValue() > occurence);
	}
	
	public List<Integer> sort() {
		return this.dices.sorted().boxed().toList();
	}
	
	public boolean equals(List<Integer> otherDices) {
		return otherDices.equals(this.sort());
	}
	
}

