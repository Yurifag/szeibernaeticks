package main.de.grzb.szeibernaeticks.client.gui;

public enum GuiId {
    ASSEMBLER(0);

    private final int id;

    private GuiId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
