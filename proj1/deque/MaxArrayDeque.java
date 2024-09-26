package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> orgComparator;
    public MaxArrayDeque(Comparator<T> c) {
        orgComparator = c;
    }

    public T max() {
        if (!isEmpty()) {
            Iterator<T> iter = iterator();
            T max = get(0);
            while (iter.hasNext()) {
                T n = iter.next();
                if (orgComparator.compare(max, n) < 0) {
                    max = n;
                }
            }
            return max;
        }
        return null;
    }

    public T max(Comparator<T> c) {
        if (!isEmpty()) {
            Iterator<T> iter = iterator();
            T max = get(0);
            while (iter.hasNext()) {
                T n = iter.next();
                if (c.compare(max, n) < 0) {
                    max = n;
                }
            }
            return max;
        }
        return null;
    }
}
