package Experimentatoins.support;

import java.awt.*;

public class GridBagConstraintCustom extends GridBagConstraints {

    public void reset(){
        gridwidth = 1;
        gridheight = 1;
        weightx = 0;
        weighty = 0;
        ipadx = 0;
        ipady  = 0;
    }
}
