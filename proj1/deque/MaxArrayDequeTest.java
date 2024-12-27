package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {

    private class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int l1 = o1.length();
            int l2 = o2.length();

            for (int i = 0; i < Math.min(l1,l2); i++) {
                int o1Char = o1.charAt(i);
                int o2Char = o2.charAt(i);

                if (o1Char != o2Char) {
                    return o1Char - o2Char;
                }
            }
            if (l1 != l2) {
                return l1 - l2;
            }
            return 0;
        }
    }

    private class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    @Test
    public void testIntMaxArrayDeque() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(5);
        mad.addFirst(10);
        mad.addFirst(2);

        assertEquals("Should have the same value", 10, mad.max(), 0);
    }

    @Test
    public void testStringMaxArrayDeque() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new StringComparator());
        mad.addLast("aa");
        mad.addLast("b");
        mad.addLast("c");
        mad.addFirst("d");
        mad.addFirst("a");

        assertEquals("Should have the same value", "d", mad.max());
        assertEquals("Should have the same value", "aa", mad.max(new StringLengthComparator()));
    }
}
