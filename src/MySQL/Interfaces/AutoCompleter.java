package MySQL.Interfaces;

public interface AutoCompleter {
    /**
     * used to transfer Strings from the <code>InputPanel</code> to the Controller class
     * @param subString partial String that the user typed
     */
    void autoCompleteQuery(String subString);
}
