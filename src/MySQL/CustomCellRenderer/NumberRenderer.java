package MySQL.CustomCellRenderer;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.NumberFormat;

public class NumberRenderer extends DefaultTableCellRenderer {
    NumberFormat formatter;

    public NumberRenderer(){
        super();

    }

    public void setValue(Object value){
        if (formatter == null){
            formatter = NumberFormat.getNumberInstance();

        }
        if (value instanceof String)
            value = Integer.parseInt((String) value);

        setText(formatter.format(value));
    }


}

