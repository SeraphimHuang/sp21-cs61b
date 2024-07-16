package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
          AListNoResizing<Integer> a = new AListNoResizing<>();
          BuggyAList<Integer> b = new BuggyAList<>();

          a.addLast(4);
          a.addLast(5);
          a.addLast(6);

          b.addLast(4);
          b.addLast(5);
          b.addLast(6);

          assertEquals(a.removeLast(), b.removeLast());
          assertEquals(a.removeLast(), b.removeLast());
          assertEquals(a.removeLast(), b.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();

        int n = 5000;
        for (int i = 0; i < n; i++) {
            int operationLimit;
            if (a.size() == 0){
                operationLimit = 2;
            }
            else {
                operationLimit = 4;
            }
            int operationNumber = StdRandom.uniform(0, operationLimit);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                a.addLast(randVal);
                b.addLast(randVal);
                // System.out.println("addLast(" + randVal + ")");
            }
            else if (operationNumber == 1) {
                int sizeA = a.size();
                int sizeB = b.size();
                // System.out.println("size: " + sizeA);
                assertEquals(sizeA, sizeB);
            }
            else if (operationNumber == 2) {
                int lastA = a.getLast();
                int lastB = b.getLast();
                // System.out.println("get last: " + lastA);
                assertEquals(lastA, lastB);
            }
            else if (operationNumber == 3) {
                int lastA = a.removeLast();
                int lastB = b.removeLast();
                assertEquals(lastA, lastB);
                // System.out.println("remove last: " + lastA);
            }
        }
    }
}
