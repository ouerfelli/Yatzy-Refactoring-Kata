package yatzy;
import java.util.Arrays;
import yatzy.domain.Roll;

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
    	return roll.sumOccurences(2);
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.sumPairs();
    }

    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.sumOccurences(4);
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
    	Roll roll = new Roll(d1, d2, d3, d4, d5);
    	return roll.sumOccurences(3);
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
    	return roll.isFullHouse() ? new Roll(d1, d2, d3, d4, d5).sum() : 0;
    }
}
