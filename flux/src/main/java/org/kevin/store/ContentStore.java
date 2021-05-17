package org.kevin.store;

import lombok.Getter;
import org.kevin.action.Action;
import org.kevin.action.ActionType;
import org.kevin.action.Content;
import org.kevin.action.ContentAction;

import java.util.Objects;

public class ContentStore extends Store {

    @Getter
    private Content content = Content.PRODUCTS;

    @Override
    public void onAction(Action action) {
        if (Objects.equals(action.getType(), ActionType.CONTENT_CHANGED)) {
            ContentAction contentAction = (ContentAction) action;
            content = contentAction.getContent();
            notifyChange();
        }
    }
}
