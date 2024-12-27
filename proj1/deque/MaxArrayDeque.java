package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxElement = this.get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(maxElement, get(i)) < 0) {
                maxElement = get(i);
            }
        }
        return maxElement;
    }
}
