package org.kevin.list;

import org.kevin.Iterator;

import java.util.List;
import java.util.Objects;

public class TreasureChestIterator implements Iterator<Item> {

    private final TreasureChest chest;

    private int idx;

    private final ItemType type;

    public TreasureChestIterator(TreasureChest chest, ItemType type) {
        this.chest = chest;
        this.type = type;
        this.idx = -1;
    }

    @Override
    public boolean hasNext() {
        return findNextIdx() != -1;
    }

    @Override
    public Item next() {
        idx = findNextIdx();
        if (idx != -1) {
            return chest.getItems().get(idx);
        }
        return null;
    }

    private int findNextIdx(){
        List<Item> items = chest.getItems();
        int tempIdx = idx;
        while (true) {
            tempIdx++;
            if (tempIdx >= items.size()) {
                tempIdx = -1;
                break;
            }
            if (Objects.equals(type, ItemType.ANY) || Objects.equals(items.get(tempIdx).getType(), type)) {
                break;
            }
        }
        return tempIdx;
    }
}
