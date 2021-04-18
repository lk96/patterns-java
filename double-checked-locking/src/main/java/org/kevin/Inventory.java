package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Inventory {

    private final int inventorySize;

    private final List<Item> items;

    private final Lock lock;

    public Inventory(int inventorySize) {
        this.inventorySize = inventorySize;
        this.items = new ArrayList<>(inventorySize);
        this.lock = new ReentrantLock();
    }

    public boolean addItem(Item item) {
        if (items.size() < inventorySize) {
            lock.lock();
            try{
                if (items.size() < inventorySize) {
                    items.add(item);
                    Thread thread = Thread.currentThread();
                    log.info("{}:items.size()={},inventorySize={}", thread, items.size(), inventorySize);
                    return true;
                }
            }finally {
                lock.unlock();
            }
        }
        return false;
    }

    public final List<Item> getItems() {
        return List.copyOf(items);
    }
}
