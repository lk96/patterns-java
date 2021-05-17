package org.kevin.view;

import lombok.extern.slf4j.Slf4j;
import org.kevin.action.Content;
import org.kevin.store.ContentStore;
import org.kevin.store.Store;

@Slf4j
public class ContentView implements View {

    private Content content = Content.PRODUCTS;

    @Override
    public void storeChanged(Store store) {
        ContentStore contentStore = (ContentStore) store;
        content = contentStore.getContent();
        render();
    }

    @Override
    public void render() {
        log.info(content.toString());
    }
}
