package deque;

public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    // resize to be done
    public void addFirst(T item){
        resize();
        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    // resize to be done
    public void addLast(T item){
        resize();
        items[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);
    }


    private int plusOne(int index){
        if (index < items.length - 1){
            return index + 1;
        }
        return 0;
    }

    private int plusOne(int index, int len){
        if (index < len - 1){
            return index + 1;
        }
        return 0;
    }

    private int minusOne(int index){
        if (index != 0){
            return index - 1;
        }
        return items.length - 1;
    }

    private int minusOne(int index, int len){
        if (index != 0){
            return index - 1;
        }
        return len - 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        // deque items[start, end)
        int start = plusOne(nextFirst);
        int end = nextLast;

        int index = start;
        while (index != end){
            System.out.print(items[index] + " ");
            index = plusOne(index);
        }
        System.out.println();
    }

    public T removeFirst(){
        resize();
        if (isEmpty()){
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T temp = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return temp;
    }

    public T removeLast(){
        resize();
        if (isEmpty()){
            return null;
        }
        nextLast = minusOne(nextLast);
        T temp = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return temp;
    }

    public T get(int index){
        if (index >= size){
            return null;
        }
        int traversal = plusOne(nextFirst);
        while (index != 0){
            traversal = plusOne(traversal);
            index -= 1;
        }
        return items[traversal];
    }

    private T getRecursiveHelper(int cur, int index){
        if (index == 0){
            return items[cur];
        }
        return getRecursiveHelper(plusOne(cur), index - 1);
    }

    public T getRecursive(int index){
        if (index >= size){
            return null;
        }
        return getRecursiveHelper(plusOne(nextFirst), index);
    }

    private void resizeHelper(int capacity){

        int start = plusOne(nextFirst);
        int end = minusOne(nextLast);
        T[] temp = items;
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for (int i = start; i != end; i = plusOne(i, temp.length)){
            items[nextLast] = temp[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = temp[end];
        nextLast = plusOne(nextLast);
    }

    private void resize(){
        if (size == items.length){
            resizeHelper(size * 2);
        }
        else if (size <= items.length / 4 && size >= 16){
            resizeHelper(items.length / 2);
        }
    }
}
