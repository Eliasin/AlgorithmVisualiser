package me.steven.pham.me.steven.pham.decorators.containers;

import java.util.*;
import java.util.function.Consumer;

public final class InsertionListeningQueueDecorator<E> implements Queue<E> {

    private final Queue<E> backingQueue;

    private final Consumer<E> insertionListener;

    public InsertionListeningQueueDecorator(final Queue<E> backingQueue, final Consumer<E> insertionListener) {
        this.backingQueue = backingQueue;
        this.insertionListener = insertionListener;
    }

    @Override
    public int size() {
        return backingQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return backingQueue.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return backingQueue.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return backingQueue.iterator();
    }

    @Override
    public Object[] toArray() {
        return backingQueue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return backingQueue.toArray(a);
    }

    @Override
    public boolean add(E e) {
        insertionListener.accept(e);
        return backingQueue.add(e);
    }

    @Override
    public boolean offer(E e) {
        return backingQueue.offer(e);
    }

    @Override
    public E remove() {
        return backingQueue.remove();
    }

    @Override
    public E poll() {
        return backingQueue.poll();
    }

    @Override
    public E element() {
        return backingQueue.element();
    }

    @Override
    public E peek() {
        return backingQueue.peek();
    }

    @Override
    public boolean remove(Object o) {
        return backingQueue.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return backingQueue.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return backingQueue.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return backingQueue.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return backingQueue.retainAll(c);
    }

    @Override
    public void clear() {
        backingQueue.clear();
    }
}
