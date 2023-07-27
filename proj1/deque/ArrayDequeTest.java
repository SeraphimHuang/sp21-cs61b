package deque;

import org.junit.Test;

import static org.junit.Assert.*;


public class ArrayDequeTest {
    @Test
    public void addFirstTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ad1.addFirst(3);
        ad1.addFirst(10);
        assertEquals(6,ad1.nextFirst);
        assertEquals(2,ad1.size);
        assertEquals(1,ad1.nextLast);
    }

    @Test
    public void addLastTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ad1.addLast(3);
        ad1.addLast(10);
        assertEquals(3,ad1.nextLast);
        assertEquals(2,ad1.size);
    }

    @Test
    public void isEmptyTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        ad2.addFirst(3);
        ad2.addFirst(10);
        assertTrue(ad1.isEmpty());
        assertFalse(ad2.isEmpty());
    }

    @Test
    public void sizeTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        ad2.addFirst(3);
        ad2.addFirst(10);
        assertEquals(0,ad1.size());
        assertEquals(2,ad2.size());
    }

    @Test
    public void printDequeTest(){
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        ad2.addFirst(3);
        ad2.addLast(15);
        ad2.addFirst(10);
        ad2.printDeque();
    }

    @Test
    public void removeFirstTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        ad2.addFirst(3);
        ad2.addLast(15);
        ad2.addFirst(10);
        assertEquals(null, ad1.removeFirst());
        assertEquals(10, (long) ad2.removeFirst());
        assertEquals(7, (long) ad2.nextFirst);
    }

    @Test
    public void removeLastTest(){
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        ad2.addFirst(3);
        ad2.addLast(15);
        ad2.addFirst(10);
        assertEquals(15, (long) ad2.removeLast());
        assertEquals(3, (long) ad2.removeLast());
        assertEquals(10, (long) ad2.removeLast());
        assertEquals(null, ad2.removeLast());
    }

    @Test
    public void getTest(){
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        ad2.addFirst(3);
        ad2.addLast(15);
        ad2.addFirst(10);
        assertEquals(10, (long) ad2.get(0));
        assertEquals(3, (long) ad2.get(1));
        assertEquals(15, (long) ad2.get(2));
        assertEquals(null, ad2.get(3));
    }
}
