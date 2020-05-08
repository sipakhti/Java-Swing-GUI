package MySQL;

import Experimentatoins.MatrixForm.Incrementable;

import javax.swing.*;
import java.awt.*;

public class ProductTable extends JPanel implements Incrementable {
    private JTextField[][] matrix;
    private final GridBagLayout layout;
    private GridBagConstraints c;
    private String[][] tableData = new String[0][1];

    public ProductTable(){
        this.layout = new GridBagLayout();
        this.setMatrix(new JTextField[1][1]);


        this.setLayout(this.layout);
        this.setConstraints();

        this.setMatrix(1,1);
        this.setMinimumSize(new Dimension(500,500));
        this.setVisible(true);
    }

    private void setConstraints() {
        this.c = new GridBagConstraints();
        this.c.insets = new Insets(0,2,0,2);
        this.c.fill = GridBagConstraints.BOTH;
    }

    public void setMatrix(final int row, final int column) {
        this.removeAll();
        setMatrix(new JTextField[row][column]);
        for (int r = 0; r < row; r++) {
            for (int col = 0; col < column ; col++) {
                getMatrix()[r][col] = new JTextField();
                getMatrix()[r][col].setName(String.format("%d%d",r,col));
            }
        }
        this.addMatrix();
    }

    public void updateRow(String[] data){
        int rowNum = 0;
        try {
            while (true) {
                if (this.getMatrix()[rowNum][0].getText().isEmpty()) {
                    for (int i = 0; i < this.getColumns(); i++) {
                        this.getMatrix()[rowNum][i].setText(data[i]);
                    }

                    break;
                }
                rowNum++;
            }
        }catch (IndexOutOfBoundsException e){
            this.incrementMatrixRow();
            this.updateRow(data);
        }
    }

    @Override
    public void incrementMatrixRow(){
            this.setMatrix(this.getMatrix().length + 1, this.getMatrix()[0].length);
    }

    @Override
    public void incrementMatrixColumn(){
        setMatrix(this.getMatrix().length, this.getMatrix()[0].length+1);
    }

    @Override
    public void decrementMatrixRow() {
        if (!(getMatrix().length == 1))
        setMatrix(this.getMatrix().length - 1, this.getMatrix()[0].length);

    }

    @Override
    public void decrementMatrixColumn() {
        if (!(getMatrix()[0].length == 1))
            setMatrix(this.getMatrix().length, this.getMatrix()[0].length - 1);
    }


    private void addMatrix(){

        for (int i = 0; i < this.getMatrix().length ; i++) {
            for (int j = 0; j < this.getMatrix()[0].length ; j++) {
                if (j == 0) {
                    this.c.gridx = j;
                    this.c.gridy = i;
                    this.c.gridwidth = 3;
                    this.c.weightx = 1;
                }
                else {
                    this.c.gridx = j+2;
                    this.c.gridy = i;
                    this.c.gridwidth = 1;
                    this.c.weightx = 0.25;
                }
                //this.getMatrix()[i][j].setBackground(new Color(240, 240, 240, 0));
                this.getMatrix()[i][j].setOpaque(false);
                this.add(this.getMatrix()[i][j], this.c);
            }
        }
    }

    public int getRows(){
        return this.getMatrix().length;
    }

    public int getColumns(){
        return this.getMatrix()[0].length;
    }

    public JTextField[][] getMatrix() {
        return matrix;
    }

    private void setMatrix(JTextField[][] matrix) {
        this.matrix = matrix;
    }

}
