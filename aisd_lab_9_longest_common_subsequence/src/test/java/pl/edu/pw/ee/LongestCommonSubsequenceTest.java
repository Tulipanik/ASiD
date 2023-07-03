package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class LongestCommonSubsequenceTest {
    LongestCommonSubsequence sequenceFinder;

    @Test
    public void correctSubsequence() {
        //given
        String top = "PREZENTY";
        String left = "RESZTA";
        //when
        sequenceFinder = new LongestCommonSubsequence(top, left);
        String result = sequenceFinder.findLCS();
        //then
        String expected = "REZT";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void thereIsNoCommonSubsequence() {
        //given
        String top = "ABCDEFGHIJ";
        String left = "KLMNOPRST";
        //when
        sequenceFinder = new LongestCommonSubsequence(top, left);
        String result = sequenceFinder.findLCS();
        //then
        String expected = "";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void oneSequenceIsSubsequenceOfAnother() {
        //given
        String left = "PREZRESZTAENTY";
        String top = "RESZTA";
        //when
        sequenceFinder = new LongestCommonSubsequence(top, left);
        String result = sequenceFinder.findLCS();
        //then
        Assert.assertEquals(top, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void subsequenceWithNullValue() {
        //given
        String top = null;
        String left = null;
        //when
        sequenceFinder = new LongestCommonSubsequence(top, left);
        sequenceFinder.findLCS();
        //then
        assert false;
    }

    @Test
    public void correctDisplay() {
        //given
        String top = "PREZENTY";
        String left = "RESZTA";
        //when
        sequenceFinder = new LongestCommonSubsequence(top, left);
        LongestCommonSubsequence sequenceFinderSpy = spy(sequenceFinder);
        sequenceFinderSpy.findLCS();
        sequenceFinderSpy.display();
        //then
        verify(sequenceFinderSpy, times(left.length() + 3)).printingHorizontalBarrier();
        verify(sequenceFinderSpy, times(left.length() + 3)).printingHorizontalBarrier();
        verify(sequenceFinderSpy, times(1)).printWordHorizontally();

        for (int i = -1; i < left.length(); i++) {
            verify(sequenceFinderSpy).printingBetweenValuesAndBarriers(i + 1);
            verify(sequenceFinderSpy).printRow(i, i + 1);
        }

    }

    @Test
    public void correctDisplayWithWhiteCharacters() {
        //given
        String top = "\n\t \t\b \n";
        String left = "\n \b\n";
        //when
        sequenceFinder = new LongestCommonSubsequence(top, left);
        LongestCommonSubsequence sequenceFinderSpy = spy(sequenceFinder);
        sequenceFinderSpy.findLCS();
        sequenceFinderSpy.display();
        //then
        verify(sequenceFinderSpy, times(4)).getFromWhites("\n");
        verify(sequenceFinderSpy, times(2)).getFromWhites("\t");
        verify(sequenceFinderSpy, times(2)).getFromWhites("\b");
    }

    @Test
    public void correctDisplayWithoutPath() {
        //given
        String top = "PREZENTY";
        String left = "RESZTA";
        //when
        sequenceFinder = new LongestCommonSubsequence(top, left);
        LongestCommonSubsequence sequenceFinderSpy = spy(sequenceFinder);
        sequenceFinder.display();
        //then
        for (int i = 0; i < left.length(); i++) {
            for (int j = 0; j < top.length(); j++) {
                int[] position = {i, j};
                verify(sequenceFinderSpy, never()).printDirection(position, true);
                verify(sequenceFinderSpy, never()).printDirection(position, false);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void rowVerifierWithWrongPosition() {
        //given
        String top = "PREZENTY";
        String left = "RESZTA";
        //when
        sequenceFinder = new LongestCommonSubsequence(top, left);
        sequenceFinder.printRow(-1, 7);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void printDirectionWrongPosition() {
        //given
        String top = "PREZENTY";
        String left = "RESZTA";
        int[] position = {-1, 10};
        //when
        sequenceFinder = new LongestCommonSubsequence(top, left);
        sequenceFinder.printDirection(position, false);
        //then
        assert false;
    }
}