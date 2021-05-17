package org.kevin.view;

import org.kevin.store.Store;

public interface View {

    void storeChanged(Store store);

    void render();
}
