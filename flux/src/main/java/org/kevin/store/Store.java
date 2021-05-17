package org.kevin.store;

import org.kevin.action.Action;
import org.kevin.view.View;

import java.util.LinkedList;
import java.util.List;

public abstract class Store {

    private final List<View> views = new LinkedList<>();

    public abstract void onAction(Action action);

    public void registerView(View view) {
        views.add(view);
    }

    protected void notifyChange() {
        views.forEach(view -> view.storeChanged(this));
    }
}
