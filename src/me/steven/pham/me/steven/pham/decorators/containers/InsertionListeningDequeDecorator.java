package me.steven.pham.me.steven.pham.decorators.containers;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Consumer;

public final class InsertionListeningDequeDecorator<E> implements Deque<E> {

    private final Deque<E> backingDeque;

    private final Consumer<E> insertionListener;

    public InsertionListeningDequeDecorator(final Deque<E> backingDeque, final Consumer<E> insertionListener) {
        this.backingDeque = backingDeque;
        this.insertionListener = insertionListener;
    }

    @Override
    public int size() {
        return backingDeque.size();
    }

    @Override
    public boolean isEmpty() {
        return backingDeque.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return backingDeque.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return backingDeque.iterator();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return backingDeque.descendingIterator();
    }

    @Override
    public Object[] toArray() {
        return backingDeque.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return backingDeque.toArray(a);
    }

    @Override
    public void addFirst(E e) {
        backingDeque.addFirst(e);
    }

    @Override
    public void addLast(E e) {
        backingDeque.addLast(e);
    }

    @Override
    public boolean offerFirst(E e) {
        return backingDeque.offerFirst(e);
    }

    @Override
    public boolean offerLast(E e) {
        return backingDeque.offerLast(e);
    }

    @Override
    public E removeFirst() {
        return backingDeque.removeFirst();
    }

    @Override
    public E removeLast() {
        return backingDeque.removeLast();
    }

    @Override
    public E pollFirst() {
        return backingDeque.pollFirst();
    }

    @Override
    public E pollLast() {
        return backingDeque.pollLast();
    }

    @Override
    public E getFirst() {
        return backingDeque.getFirst();
    }

    @Override
    public E getLast() {
        return backingDeque.getLast();
    }

    @Override
    public E peekFirst() {
        return backingDeque.peekFirst();
    }

    @Override
    public E peekLast() {
        return backingDeque.peekLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return backingDeque.removeFirstOccurrence(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return backingDeque.removeLastOccurrence(o);
    }

    @Override
    public boolean add(E e) {
        insertionListener.accept(e);
        return backingDeque.add(e);
    }

    @Override
    public boolean offer(E e) {
        return backingDeque.offer(e);
    }

    @Override
    public E remove() {
        return backingDeque.remove();
    }

    @Override
    public E poll() {
        return backingDeque.poll();
    }

    @Override
    public E element() {
        return backingDeque.element();
    }

    @Override
    public E peek() {
        return backingDeque.peek();
    }

    @Override
    public void push(E e) {
        backingDeque.push(e);
    }

    @Override
    public E pop() {
        return backingDeque.pop();
    }

    @Override
    public boolean remove(Object o) {
        return backingDeque.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return backingDeque.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return backingDeque.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return backingDeque.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return backingDeque.retainAll(c);
    }

    @Override
    public void clear() {
        backingDeque.clear();
    }
}
