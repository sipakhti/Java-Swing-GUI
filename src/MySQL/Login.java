package MySQL;

import MySQL.Interfaces.CredentialListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Login extends JFrame {

    private final GridBagLayout LAYOUT = new GridBagLayout();
    private final GridBagConstraints CONSTRAINTS = new GridBagConstraints();

    private JLabel userName, password,prompt;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton signIn;

    private CredentialListener credentialListener;
    private JButton popupButton;


    public Login(){
        popupButton = new JButton("OK");



        userName = new JLabel("USERNAME:");
        userName.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));
        password = new JLabel("PASSWORD:");
        password.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200,30));
        nameField.setMaximumSize(getPreferredSize());
        nameField.setMinimumSize(getPreferredSize());

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200,30));
        passwordField.setMaximumSize(getPreferredSize());
        passwordField.setMinimumSize(getPreferredSize());
        passwordField.setEchoChar('X');

        prompt = new JLabel("Invalid Credentials");
        prompt.setFont(new Font(Font.MONOSPACED,Font.ITALIC,10));
        prompt.setForeground(Color.RED);
        prompt.setVisible(false);

        signIn = new JButton("SIGN IN");
        signIn.setFont(new Font(Font.SERIF,Font.BOLD,16));
        signIn.setForeground(Color.darkGray);



        setLayout(LAYOUT);

        CONSTRAINTS.gridy = 0;
        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.anchor = GridBagConstraints.EAST;
        CONSTRAINTS.weighty = 1;
        CONSTRAINTS.weightx = 1;
        add(userName,CONSTRAINTS);

        CONSTRAINTS.gridy++;
        add(password,CONSTRAINTS);

        CONSTRAINTS.gridx = 1;
        CONSTRAINTS.gridy = 0;
        CONSTRAINTS.weightx = 3;
        CONSTRAINTS.gridwidth = 2;
        CONSTRAINTS.anchor = GridBagConstraints.CENTER;
        add(nameField,CONSTRAINTS);

        CONSTRAINTS.gridy++;
        add(passwordField,CONSTRAINTS);

        CONSTRAINTS.gridy++;
        CONSTRAINTS.fill = GridBagConstraints.HORIZONTAL;
        CONSTRAINTS.anchor = GridBagConstraints.BELOW_BASELINE;
        add(prompt,CONSTRAINTS);

        CONSTRAINTS.gridwidth = 1;
        CONSTRAINTS.gridx = 2;
        CONSTRAINTS.gridy+=2;
        CONSTRAINTS.fill = GridBagConstraints.HORIZONTAL;
        CONSTRAINTS.anchor = GridBagConstraints.CENTER;
        CONSTRAINTS.insets = new Insets(0,7,0,7);
        add(signIn,CONSTRAINTS);

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                credentialListener.getCredentials(new String[]{getUserName(),getPassword()});
            }
        });

        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    credentialListener.getCredentials(new String[]{getUserName(),getPassword()});
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setSize(280,131);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();
        nameField.requestFocus();


        Component[] components = getComponents();
        for (Component component : components) {
            JComponent jcomponent = (JComponent) component;
            jcomponent.setOpaque(true);
        }


    }

    public String getUserName(){
        return nameField.getText();
    }

    public String getPassword(){
        char[] arr = passwordField.getPassword();
        StringBuilder password = new StringBuilder();

        for (char c : arr) {
            password.append(c);
        }
        return password.toString();
    }

    public void setCredentialListener(CredentialListener credentialListener) {
        this.credentialListener = credentialListener;
    }

    public void wrongLogin(){
        prompt.setVisible(true);
        setSize(280,145);
        System.out.println(getSize());
        passwordField.setText("");
        nameField.setText("");
    }
}
