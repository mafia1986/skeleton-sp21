package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Iterable<T> {
    private T[] items;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
 //       this(8);
        size = 0;
        this.capacity = 8;
        items = (T[]) new Object[capacity];
        nextFirst = capacity / 2 - 1;
        nextLast = nextFirst + 1;
    }
//    public ArrayDeque(int capacity) {
//        size = 0;
//        this.capacity = capacity;
//        items = (T[]) new Object[capacity];
//        nextFirst = capacity / 2 - 1;
//        nextLast = nextFirst + 1;
//    }

    public void addFirst(T item) {
        if(isFull()) {
            resize(2 * capacity);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1) >= 0 ? nextFirst - 1 : (nextFirst - 1 + capacity) % capacity;
        size += 1;
    }

    public void addLast(T item) {
        if(isFull()) {
            resize(2 * capacity);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        size += 1;
    }
    private void resize(int capacity) {
        T[] a = (T[])new Object[capacity];
        int aIndex = capacity / 4;
        int newNextFirst = aIndex - 1;
        int newNextLast = aIndex + size;
        for(int i = 0; i < size; i += 1) {
            a[aIndex] = get(i);
            aIndex +=1;
        }
        items = a;
        nextFirst = newNextFirst;
        nextLast = newNextLast;
        this.capacity = capacity;
    }
    private boolean isFull() {
        if(size == capacity) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = (nextFirst + 1) % capacity;
        for(int n = size; n > 0; n -= 1) {
            System.out.print(items[i]);
            if(n > 1) {
                System.out.print(" ");
            }
            i = (i + 1) % capacity;
        }
        System.out.println();
    }

    public T removeFirst() {
        if(size > 0) {
            nextFirst = (nextFirst + 1) % capacity;
            size -= 1;
            return items[nextFirst];
        }
        return null;
    }

    public T removeLast() {
        if(size > 0) {
            nextLast = (nextLast - 1) >= 0 ? nextLast - 1 : (nextLast - 1 + capacity) % capacity;
            size -= 1;
            return items[nextLast];
        }
        return null;
    }

    public T get(int index) {
        if(index >= 0 && index < size) {
            return items[(nextFirst + 1 + index) % capacity];
        }
        return null;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = (nextFirst + 1) % capacity;
            @Override
            public boolean hasNext() {
                if(size > 0 && (index + 1) % capacity != nextLast) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T v = items[index];
                index = (index + 1) % capacity;
                return v;
            }
        };
    }
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o instanceof ArrayDeque<?>) {
            ArrayDeque<?> other = (ArrayDeque<?>)o;
            if(this.size == other.size) {
                Iterator<?> otherIt = other.iterator();
                Iterator<?> selfIt = iterator();
                while(selfIt.hasNext()) {
                    T selfValue = (T)selfIt.next();
                    T otherValue = (T)otherIt.next();
                    if(!selfValue.equals(otherValue)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
