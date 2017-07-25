package com.cwbusinesservices.mergers;

/**
 * Created by Andrii on 25.07.2017.
 */
public abstract class Merger<E,V> {
    public abstract void merge (E entity, V view);
}
