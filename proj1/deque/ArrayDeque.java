package deque;

public class ArrayDeque<T> {
    public int size;
    public int capacity;
    public T[] items;
    public int nextFirst;
    public int nextLast;
    public ArrayDeque(){
        size = 0;
        capacity = 8;
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
    }

    /* Circular add, if the front item is at position zero, and add first,
    the new front should be added to the end of the array.
    Reasoning: In that case we only change one cell instead of moving all cells
     */

    /* Note
    If the first and last cell are both filled, and we add first,
    then we shouldn't simply replace the last item with the new add first
     */
    public void addFirst(T item){
        size += 1;
        items[nextFirst] = item;
        nextFirst -= 1;
        if (nextFirst < 0){
            nextFirst = capacity-1;
        }
    }

    /* Note
    If the first and last cell are both filled, and we add last,
    then we shouldn't simply replace the first item with the new add last
     */
    public void addLast(T item){
        size += 1;
        items[nextLast] = item;
        nextLast += 1;
        if (nextLast == capacity){
            nextLast = 0;
        }
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int curr = nextFirst + 1;
        while (size > 0){
            if (curr == capacity){
                curr = 0;

            }
            System.out.print(items[curr]);
            System.out.print(' ');
            curr += 1;
            size -= 1;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        } else {
            size -= 1;
            nextFirst += 1;
            if (nextFirst == capacity){
                nextFirst = 0;
            }
        } return items[nextFirst];
    }

    public T removeLast(){
        if (size == 0){
            return null;
        } else {
            size -= 1;
            nextLast -= 1;
            if (nextLast < 0){
                nextLast = capacity - 1;
            }
            return items[nextLast];
        }
    }
    
}
