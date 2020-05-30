package MySQL.POSsupport;

import MySQL.Interfaces.ActionPerformed;
import MySQL.Interfaces.AutoCompleter;
import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is class holds a <code>JComboBox</code> as the main input method.
 * it also holds a <code>JButton</code>
 */
public class InputPanel extends JPanel {
    private final GridBagLayout LAYOUT = new GridBagLayout();


    private JButton button;
    private ActionPerformed listener;
    private StringListener stringListener;
    private AutoCompleter completer;
    private JLabel autoCompleteLabel;
    private JComboBox autoComplete;

    private DefaultComboBoxModel model;


    GridBagConstraints bagConstraints;

    /**
     * Default Constructor
     */
    public InputPanel() {




        // ADDING AUTOCOMPLETE COMBOBOX
        model = new DefaultComboBoxModel();
        autoComplete = new JComboBox(model);
        autoComplete.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        autoComplete.setEditable(true);
        autoComplete.setBackground(ColoringConstants.textFieldsBackground);
        autoComplete.getEditor().getEditorComponent().setBackground(ColoringConstants.textFieldsBackground);
        // ADDING AUTOCOMLPLETE LABEL
        autoCompleteLabel = new JLabel("Product Name / Barcode");
        // ADDING REMOVE ITEM BUTTON
        button = new JButton("REMOVE ITEM");
        bagConstraints = new GridBagConstraints();

        setLayout(LAYOUT);

        // ADD COMPONENTS
        addAutoComplete();

        addButton();

        // ADD LISTENERS
        AddItemListener();

        autoCompleteListeners();

        FocusListener();


    }

    private void FocusListener() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                autoComplete.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }

    private void autoCompleteListeners() {
        autoComplete.getEditor().getEditorComponent().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {


            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER && e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_UP) {
                    String text = ((JTextField) autoComplete.getEditor().getEditorComponent()).getText();  // stores the input text of the JComboBox
                    completer.autoCompleteQuery(text); // passes the input string to the controller to be sent to the database class so that autocomplete data can be sent
                    autoComplete.setSelectedIndex(-1); // stops the JcomboBox from automatically selecting the first Item upon model load
                    ((JTextField) autoComplete.getEditor().getEditorComponent()).setText(text); // sets the comboBox TextField to the input text
                    autoComplete.showPopup(); // drops down the popup with suggestions
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    stringListener.searchQuery(((JTextField) autoComplete.getEditor().getEditorComponent()).getText()); // sends the selected item name to be added to the table

            }
        });
    }


    // notifies the controller that the button is pressed
    private void AddItemListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    listener.quantityUpdated();
            }
        });

    }


    private void addAutoComplete() {
        bagConstraints.weightx = 1;
        bagConstraints.weighty = 1;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        bagConstraints.gridwidth = 2;
        bagConstraints.insets = new Insets(0,5,0,5);
        bagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        autoCompleteLabel.setForeground(ColoringConstants.foreground);
        add(autoCompleteLabel, bagConstraints);

        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.weightx = 1;
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.gridwidth = 2;
        add(autoComplete,bagConstraints);

    }

    private void addButton() {
        bagConstraints.gridx = 3;
        bagConstraints.weightx = 0.25;
        bagConstraints.gridwidth = 1;
        bagConstraints.gridy = 1;
        add(button,bagConstraints);
        button.setFont(new Font(Font.SANS_SERIF,Font.BOLD,12));
    }

    /**
     * this listener is used fires the appropriate command when the <code>JButton</code> is pressed
     * allowing the controller to execute certain commands upon button press
     * @param listener instance of Interface <code>ActionPerformed</code>
     */
    public void setListener(ActionPerformed listener) {
        this.listener = listener;
    }

    /**
     * use this method to accept the product name from the <code>InputPanel</code> for further Processing
     * @param stringListener instance of Interfaace <code>StringListener</code> returns the selected Product name
     *                       to the controller to be passed on to the <code>DataBaseConnection</code> class
     *                       and updates the table with the corresponding value
     */
    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }

    /**
     * use tbis method to get what user types with every keystroke
     * @param completer instance of Interface <code>AutoCompleter</code>
     *                  retruns a partial String to be used as a query for autocomplete data from the DataBase
     *
     */
    public void setCompleter(AutoCompleter completer){
        this.completer = completer;
    }

    /**
     * sets all the Labels to the selected color
     * @param labelColor the color to be set
     */
    public void setForegroundColor(Color labelColor){
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JLabel){
                component.setForeground(labelColor);
            }
        }

    }

    /**
     * set foreground color of all <code>JLabels</code> and <code>JTextFields</code> to the passed color
     *
     * @param labelColor the color to be set for <code>JLabels</code>
     * @param textFieldColor the color to be set for the <code>JTextFields</code>
     */
    public void setForegroundColor(Color labelColor, Color textFieldColor){
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JLabel){
                component.setForeground(labelColor);
            }
            if (component instanceof JTextField){
                component.setForeground(textFieldColor);
            }
        }
    }


    /**
     * use this method to pass the array of String generated by the <code>DataBaseConnection.productNames()</code> method
     * to the <code>JComboBox</code> data model so that they can be displayed in the popup as suggestions
     * @param objects an array of objects to be added to the suggestion model for the <code>JComboBox</code>
     */
    public void populateSuggestions(Object[] objects){
        model.removeAllElements();

        for (Object object : objects) {

            model.addElement(object);

        }

    }

    /**
     * clears the <code>JComboBoxModel</code> and <code>JComboBox</code> displayed text
     */
    public void reset(){
        ((JTextField)autoComplete.getEditor().getEditorComponent()).setText("");model.removeAllElements();
    }



}
