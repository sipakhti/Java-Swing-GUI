package MySQL.POSsupport;

import javax.swing.*;
import java.awt.*;

public class GrandTotal extends JPanel {
    
    private final FlowLayout LAYOUT = new FlowLayout(FlowLayout.RIGHT);
    
    
    private GridBagConstraints constraints;
    private JTextField total;
    private JLabel totalLabel;
    
    public GrandTotal(){
        setVisible(true);
        setLayout(LAYOUT);
        constraints = new GridBagConstraints();
        total = new JTextField();
        total.setName("total");
        totalLabel = new JLabel("TOTAL:");
        

        add(totalLabel);
        totalLabel.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
//        totalLabel.setPreferredSize(new Dimension(145,30));
//        totalLabel.setMaximumSize(totalLabel.getPreferredSize());
//        totalLabel.setMinimumSize(totalLabel.getPreferredSize());
        

        add(total);
        total.setHorizontalAlignment(SwingConstants.RIGHT);
        total.setPreferredSize(new Dimension(300,30));
        total.setMaximumSize(total.getPreferredSize());
        total.setMinimumSize(total.getPreferredSize());
        
    }

    public void setTotal(float total) {
        this.total.setText(Float.toString(total));
    }

    public void incrementTotal(float total){
        this.total.setText(Float.toString(total + Float.parseFloat(this.total.getText())));
    }
}
