package com.cwbusinesservices.mergers;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface Merger<E,V> {
    void merge (E entity, V view);
}
