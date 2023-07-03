package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashListChainingTest {

    int length = 10;
    HashListChaining<String> hash;

    @Before
    public void init() {

        hash = new HashListChaining<>(length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hashingListChainingIsLessThanZero() {
        //given
        int length = -2;
        //when
        hash = new HashListChaining<String>(length);
        //then
        assert false;
    }

    @Test
    public void hashingListChainingHaveAppropriateLength() {
        //given
        int length = 5;
        //when
        hash = new HashListChaining<String>(length);
        //then
        Assert.assertEquals(hash.hashElemsLength(), length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addedValueIsNull() {
        //given
        String value = null;
        //when
        hash.add(value);
        //then
        assert false;
    }

    @Test
    public void addingElementToHash() {
        //given
        String value = new String("Welcome Mr Zawadzki :)");
        int hashValue = value.hashCode();
        //when
        hash.add(value);
        //then
        int memory = -1;

        for (int i = 0; i < hash.hashElemsLength(); i++) {
            if (hash.hashElemArray()[i] != null) {
                memory = i;
                break;
            }
        }

        hashValue = hash.getHashId(hashValue);
        Assert.assertEquals(hashValue, memory);
    }

    @Test
    public void addingSameElementToHash() {
        //given
        String value = new String("Welcome Mr Zawadzki :)");
        //when
        hash.add(value);
        hash.add(value);
        //then
        int memory = -1;

        for (int i = 0; i < hash.hashElemsLength(); i++) {
            if (hash.hashElemArray()[i] != null) {
                memory = i;
                break;
            }
        }

        Assert.assertNull(hash.getNext(hash.getElem(memory)));
    }

    @Test
    public void gettingNotExistingElementFromHash() {
        //given
        String value = "Hash Element";
        //when
        String get = (String) hash.get(value);
        //then
        Assert.assertNull(get);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tryingToFindNullElementInHash() {
        //given
        String value = null;
        //when
        hash.get(value);
        //then
        assert false;
    }

    @Test
    public void lookingForAppropriateElement() {
        //given
        String firstValue = "hash element 1";
        String secondValue = "hash element 2";
        //when
        hash.add(firstValue);
        hash.add(secondValue);
        String get = (String) hash.get(firstValue);
        //then
        Assert.assertEquals(firstValue, get);

    }

    @Test(expected = IllegalArgumentException.class)
    public void deletingNullElement() {
        //given
        String value = null;
        //when
        hash.delete(value);
        //then
        assert false;

    }

    @Test
    public void deletingNotExistingElement() {
        //given
        String value = "Hash element";
        //when
        hash.add(value);
        hash.delete(value);
        //then
        Assert.assertNull(hash.get(value));

    }

    @Test
    public void deletingLastElementFromHashPosition() {
        //given
        hash = new HashListChaining<String>(1);
        String firstValue = "Hash element 1";
        String secondValue = "Hash element 2";
        //when
        hash.add(secondValue);
        hash.add(firstValue);
        hash.delete(secondValue);
        //then
        Assert.assertNull(hash.getNext(hash.getElem(0)));
    }

    @Test
    public void deletingFirstElementFromHashPosition() {
        //given
        hash = new HashListChaining<String>(1);
        String firstValue = "Hash element 1";
        String secondValue = "Hash element 2";
        //when
        hash.add(firstValue);
        hash.add(secondValue);
        hash.delete(secondValue);
        //then
        Assert.assertEquals(firstValue, hash.getValue(hash.getElem(0)));
    }

    @Test
    public void deletingMiddleElementFromHashPosition() {
        //given
        hash = new HashListChaining<>(1);
        String firstValue = "Hash element 1";
        String secondValue = "Hash element 2";
        String thirdValue = "Hash element 3";
        //when
        hash.add(firstValue);
        hash.add(secondValue);
        hash.add(thirdValue);
        hash.delete(secondValue);
        //then
        if (!hash.getValue(hash.getElem(0)).equals(thirdValue)) {
            assert false;
        }

        Assert.assertNull(firstValue, hash.getNext(hash.getNext(hash.getElem(0))));
    }
}
