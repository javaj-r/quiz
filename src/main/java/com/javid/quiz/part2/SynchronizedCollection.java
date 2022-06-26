package com.javid.quiz.part2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.Stream;

public class SynchronizedCollection<E> implements Collection<E> {

    final Collection<E> collection;  // Backing Collection
    final Object lock;     // Object on which to synchronize

    SynchronizedCollection(Collection<E> c, Object mutex) {
        this.collection = Objects.requireNonNull(c);
        this.lock = Objects.requireNonNull(mutex);
    }

    void synchronize(Runnable runnable) {
        synchronized (lock) {
            runnable.run();
        }
    }

    private <T> T synchronize(Supplier<T> supplier) {
        synchronized (lock) {
            return supplier.get();
        }
    }

    private <T> void synchronize(Consumer<T> consumer, T t) {
        synchronized (lock) {
            consumer.accept(t);
        }
    }

    private <T, R> R synchronize(Function<T, R> function, T t) {
        synchronized (lock) {
            return function.apply(t);
        }
    }

    public int size() {
        return synchronize(collection::size);
    }

    public boolean isEmpty() {
        return synchronize(collection::isEmpty);
    }

    public boolean contains(Object o) {
        return synchronize(collection::contains, o);
    }

    public Object[] toArray() {
        synchronized (lock) {
            return collection.toArray();
        }
    }

    public <T> T[] toArray(T[] a) {
        synchronized (lock) {
            return collection.toArray(a);
        }
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> f) {
        synchronized (lock) {
            return collection.toArray(f);
        }
    }

    /**
     * Must be manually synched by user!
     */
    public Iterator<E> iterator() {
        return collection.iterator();
    }

    public boolean add(E e) {
        return synchronize(collection::add, e);
    }

    public boolean remove(Object o) {
        return synchronize(collection::remove, o);
    }

    public boolean containsAll(Collection<?> coll) {
        return synchronize(collection::containsAll, coll);
    }

    public boolean addAll(Collection<? extends E> coll) {
        return synchronize(collection::addAll, coll);
    }

    public boolean removeAll(Collection<?> coll) {
        return synchronize(collection::removeAll, coll);
    }

    public boolean retainAll(Collection<?> coll) {
        return synchronize(collection::retainAll, coll);
    }

    public void clear() {
        synchronize(collection::clear);
    }

    public String toString() {
        return synchronize(collection::toString);
    }

    @Override
    public void forEach(Consumer<? super E> consumer) {
        synchronize(collection::forEach, consumer);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return synchronize(collection::removeIf, filter);
    }

    /**
     * Must be manually synched by user!
     */
    @Override
    public Spliterator<E> spliterator() {
        return collection.spliterator();
    }

    /**
     * Must be manually synched by user!
     */
    @Override
    public Stream<E> stream() {
        return collection.stream();
    }

    /**
     * Must be manually synched by user!
     */
    @Override
    public Stream<E> parallelStream() {
        return collection.parallelStream();
    }
}
