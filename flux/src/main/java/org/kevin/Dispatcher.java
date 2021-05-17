package org.kevin;

import org.kevin.action.*;
import org.kevin.store.Store;

import java.util.LinkedList;
import java.util.List;

public final class Dispatcher {

    private static Dispatcher instance = new Dispatcher();

    private final List<Store> stores = new LinkedList<>();

    private Dispatcher() {
    }

    public static Dispatcher getInstance() {
        return instance;
    }

    public void registerStore(Store store) {
        stores.add(store);
    }

    public void menuItemSelected(MenuItem menuItem) {
        dispatchAction(new MenuAction(menuItem));
        if (menuItem == MenuItem.COMPANY) {
            dispatchAction(new ContentAction(Content.COMPANY));
        } else {
            dispatchAction(new ContentAction(Content.PRODUCTS));
        }
    }

    private void dispatchAction(Action action) {
        stores.forEach(store -> store.onAction(action));
    }
}
