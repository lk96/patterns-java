package org.kevin.prototype.adapter;

public class Captain {

    private RowingBoat rowingBoat;

    public Captain() {
    }

    public Captain(final RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    void setRowingBot(final RowingBoat rowingBot) {
        this.rowingBoat = rowingBot;
    }

    void row(){
        rowingBoat.row();
    }
}
