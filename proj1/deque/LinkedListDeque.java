package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;
        Node(T v, Node<T> n, Node<T> p) {
            value = v;
            next = n;
            prev = p;
        }
    }
    private Node<T> sentinel;
    private int size;
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node<T> n = new Node<>(item, sentinel.next, sentinel);
        sentinel.next.prev = n;
        sentinel.next = n;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node<T> n = new Node<>(item, sentinel, sentinel.prev);
        sentinel.prev.next = n;
        sentinel.prev = n;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.value.toString());
            if (p.next != sentinel) {
                System.out.print(" ");
            }
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size > 0) {
            Node<T> rNode = sentinel.next;
            sentinel.next = rNode.next;
            sentinel.next.prev = sentinel;
            rNode.prev = rNode.next = null;
            size -= 1;
            return rNode.value;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (size > 0) {
            Node<T> rNode = sentinel.prev;
            sentinel.prev = rNode.prev;
            sentinel.prev.next = sentinel;
            rNode.prev = rNode.next = null;
            size -= 1;
            return rNode.value;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (size > index) {
            int i = 0;
            Node<T> p = sentinel.next;
            while (i < index && p != sentinel) {
                i += 1;
                p = p.next;
            }
            if (i == index) {
                return p.value;
            }
        }
        return null;
    }
    public T getRecursive(int index) {
        if (size > index) {
            return getRecursiveHelper(index, sentinel.next);
        }
        return null;
    }
    private T getRecursiveHelper(int index, Node<T> queue) {
        if (queue == sentinel)
            return null;
        else if (index == 0) {
            return queue.value;
        }
        return getRecursiveHelper(index - 1, queue.next);
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> p = sentinel.next;
            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T v = p.value;
                p = p.next;
                return v;
            }
        };
    }
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque<?>) {
            LinkedListDeque<?> other = (LinkedListDeque<?>)o;
            if (this.size == other.size) {
                Iterator<?> otherIt = other.iterator();
                Iterator<?> selfIt = iterator();
                while (selfIt.hasNext()) {
                    T selfValue = (T) selfIt.next();
                    T otherValue = (T) otherIt.next();
                    if (!selfValue.equals(otherValue)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}

