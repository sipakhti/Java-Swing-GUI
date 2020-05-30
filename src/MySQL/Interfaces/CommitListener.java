package MySQL.Interfaces;

public interface CommitListener {
    /**
     * when the button for committing return is pressed
     */
    void returnListener();

    /**
     * when button of committing update is pressed
     */
    void updateListener();

    /**
     * when button for clearing table the table is pressed
     */
    void purgeListener();
}
