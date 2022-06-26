package com.javid.quiz.part2;

import java.util.*;
import java.util.function.*;

public final class SynchronizedHashMap<K, V> extends HashMap<K, V> {

    private final Object lock = new Object();

    public SynchronizedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public SynchronizedHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public SynchronizedHashMap() {
        super();
    }

    public SynchronizedHashMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    private void synchronize(Runnable runnable) {
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

    private <T, U, R> R synchronize(BiFunction<T, U, R> function, T t, U u) {
        synchronized (lock) {
            return function.apply(t, u);
        }
    }

    @Override
    public int size() {
        return synchronize(super::size);
    }

    @Override
    public boolean isEmpty() {
        return synchronize(super::isEmpty);
    }

    @Override
    public V get(Object key) {
        return synchronize(super::get, key);
    }

    @Override
    public boolean containsKey(Object key) {
        return synchronize(super::containsKey, key);
    }

    @Override
    public V put(K key, V value) {
        return synchronize(super::put, key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        synchronize(super::putAll, m);
    }

    @Override
    public V remove(Object key) {
        synchronized (lock) {
            return super.remove(key);
        }
    }

    @Override
    public void clear() {
        synchronize(super::clear);
    }

    @Override
    public boolean containsValue(Object value) {
        return synchronize(super::containsValue, value);
    }

    @Override
    public Set<K> keySet() {
        return synchronize(this::synchronizedKeySet);
    }

    private Set<K> synchronizedKeySet() {
        return new SynchronizedSet<>(super.keySet(), lock);
    }

    @Override
    public Collection<V> values() {
        return synchronize(this::synchronizedValues);
    }

    private Collection<V> synchronizedValues() {
        return new SynchronizedCollection<>(super.values(), lock);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return synchronize(this::synchronizedEntrySet);
    }

    private Set<Entry<K, V>> synchronizedEntrySet() {
        return new SynchronizedSet<>(super.entrySet(), lock);
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return synchronize(super::getOrDefault, key, defaultValue);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return synchronize(super::putIfAbsent, key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return synchronize(super::remove, key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        synchronized (lock) {
            return super.replace(key, oldValue, newValue);
        }
    }

    @Override
    public V replace(K key, V value) {
        return synchronize(super::replace, key, value);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return synchronize(super::computeIfAbsent, key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return synchronize(super::computeIfPresent, key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return synchronize(super::compute, key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        synchronized (lock) {
            return super.merge(key, value, remappingFunction);
        }
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        synchronize(super::forEach, action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        synchronize(super::replaceAll, function);
    }

    @Override
    public Object clone() {
        return synchronize(super::clone);
    }

    @Override
    public boolean equals(Object o) {
        return synchronize(super::equals, o);
    }

    @Override
    public int hashCode() {
        return synchronize(super::hashCode);
    }

    @Override
    public String toString() {
        return synchronize(super::toString);
    }
}
