package me.steven.pham.me.steven.pham.containerDecorators;

import java.util.LinkedList;

public class LinkedListDecorator<T> extends LinkedList<T> {

    private Runnable onElementAdd = () -> {};

    public LinkedListDecorator(Runnable onElementAdd) {
        this.onElementAdd = onElementAdd;
    }

    public void setOnElementAdd(Runnable onElementAdd) {
        this.onElementAdd = onElementAdd;
    }

    @Override
    public boolean add(T t) {
        onElementAdd.run();
        return super.add(t);
    }
}
