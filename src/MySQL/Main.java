package MySQL;

import MySQL.Interfaces.CredentialListener;

import java.sql.SQLException;

public class Main {

    private static DataBaseConnection dataBaseConnection;
    private static boolean connected = false;

    public static void main(String[] args) throws SQLException {

//        try {
//            UIManager.setLookAndFeel(new MetalLookAndFeel());
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }


        var test = new Login();
        test.setCredentialListener(new CredentialListener() {
            @Override
            public void getCredentials(String[] cred) {
                try {
                    POSMain table = new POSMain(cred[0],cred[1]);
                    test.dispose();
                    connected = true;
//                    dataBaseConnection.close();
                } catch (SQLException e) {
                    test.wrongLogin();
                    connected =false;
                }

            }
        });

        if (connected) {
            System.out.println("Hjsabgjbsjdgbjkadbj");
            dataBaseConnection.close();
        }


    }
}
