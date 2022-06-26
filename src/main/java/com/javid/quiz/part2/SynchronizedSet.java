package com.javid.quiz.part2;

import java.util.Set;

public class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {

    SynchronizedSet(Set<E> s, Object mutex) {
        super(s, mutex);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        synchronized (lock) {return collection.equals(o);}
    }
    public int hashCode() {
        synchronized (lock) {return collection.hashCode();}
    }
}
