package MySQL.CustomCellRenderer;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.NumberFormat;

public class FloatRenderer extends DefaultTableCellRenderer {

    NumberFormat formatter;

    public FloatRenderer(){
        super();
    }

    public void setValue(Object value){
        if (formatter == null)
            formatter = NumberFormat.getIntegerInstance();

        formatter.setMinimumFractionDigits(2);


        setText(formatter.format(value));
    }

}
