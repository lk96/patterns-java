package org.kevin.view;

import lombok.extern.slf4j.Slf4j;
import org.kevin.action.MenuItem;
import org.kevin.store.MenuStore;
import org.kevin.store.Store;

import java.util.Objects;

@Slf4j
public class MenuView implements View{

    private MenuItem selected = MenuItem.HOME;

    @Override
    public void storeChanged(Store store) {
        MenuStore menuStore = (MenuStore) store;
        selected = menuStore.getSelected();
        render();
    }

    @Override
    public void render() {
        for (MenuItem item : MenuItem.values()) {
            if (Objects.equals(selected, item)) {
                log.info("* {}",item);
            }else {
                log.info(item.toString());
            }
        }
    }

    public void itemClicked(MenuItem item) {

    }
}
