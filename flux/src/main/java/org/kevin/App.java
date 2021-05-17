package org.kevin;

import org.kevin.action.MenuItem;
import org.kevin.store.ContentStore;
import org.kevin.store.MenuStore;
import org.kevin.view.ContentView;
import org.kevin.view.MenuView;

public class App {

    public static void main(String[] args) {
        MenuStore menuStore = new MenuStore();
        Dispatcher.getInstance().registerStore(menuStore);
        ContentStore contentStore = new ContentStore();
        Dispatcher.getInstance().registerStore(contentStore);
        MenuView menuView = new MenuView();
        menuStore.registerView(menuView);
        ContentView contentView = new ContentView();
        contentStore.registerView(contentView);

        menuView.render();
        contentView.render();

        menuView.itemClicked(MenuItem.COMPANY);
    }
}
