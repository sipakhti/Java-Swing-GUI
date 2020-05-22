package MySQL.Interfaces;

public interface Row {
    void updateRow(String data);

    void updateRow(String[] data);

    boolean isEmpty();

    void incrementQuantity();

    void incrementQuantity(int val);

    float subtotal();

    String[] saveData();

    void reset();

    String[] updateData();
}
