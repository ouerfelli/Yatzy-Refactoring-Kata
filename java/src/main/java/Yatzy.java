import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import domain.Roll;

public class Yatzy {

    public static int chance(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.sum();
    }

    public static int yatzy(int... dice)
    {
    	Roll roll = new Roll(dice);
    	return roll.distinctDices() == 1 ? 50 : 0;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
        return roll.sum(1);
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.sum(2);
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.sum(3);
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
    	Roll roll = new Roll(dice);
    	return roll.sum(4);
    }

    public int fives()
    {
    	Roll roll = new Roll(dice);
    	return roll.sum(5);
    }

    public int sixes()
    {
    	Roll roll = new Roll(dice);
    	return roll.sum(6);
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.filterByOccurence(1) //filter the dices that appeared more than once
		    			.map(Entry::getKey)
		    			.max(Comparator.naturalOrder()) //get the highest dice value
		    			.map(value -> value * 2)
		    			.orElse(0);
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	List<Integer> dicePairs = roll.filterByOccurence(1) //filter the dices that appeared more than once
				.map(Entry::getKey)
				.toList();
    	if(dicePairs.size() == 2) {
    		return dicePairs.stream().mapToInt(dice -> dice * 2).sum();
    	}
    	return 0;
    }

    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.filterByOccurence(3) //filter the dices that appeared more than thrice
		    			.findFirst()
		    			.map(Entry::getKey)
		    			.map(value -> value * 4)
		    			.orElse(0);
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.filterByOccurence(2) //filter the dices that appeared more than twice
		    			.findFirst()
		    			.map(Entry::getKey)
		    			.map(value -> value * 3)
		    			.orElse(0);
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.equals(Arrays.asList(1, 2, 3, 4, 5)) ? 15 : 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.equals(Arrays.asList(2, 3, 4, 5, 6)) ? 20 : 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	Map<Integer, Long> diceCount = roll.groupByDice();
    	boolean tripleSide = diceCount.entrySet().stream().anyMatch(e -> e.getValue() == 3);
    	boolean twoDistinctValues = diceCount.keySet().stream().distinct().count() == 2;
    	if(tripleSide && twoDistinctValues) {
    		roll = new Roll(d1, d2, d3, d4, d5);
    		return roll.sum();
    	}
    	return 0;
    }
}
