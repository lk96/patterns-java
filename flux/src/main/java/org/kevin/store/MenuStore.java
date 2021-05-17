package org.kevin.store;

import lombok.Getter;
import org.kevin.action.Action;
import org.kevin.action.ActionType;
import org.kevin.action.MenuAction;
import org.kevin.action.MenuItem;

import java.util.Objects;

public class MenuStore extends Store {

    @Getter
    private MenuItem selected = MenuItem.HOME;

    @Override
    public void onAction(Action action) {
        if (Objects.equals(action.getType(), ActionType.MENU_ITEM_SELECTED)) {
            MenuAction menuAction = (MenuAction) action;
            selected = menuAction.getMenuItem();
            notifyChange();
        }
    }
}
