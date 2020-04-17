package Experimentatoins.MatrixForm;

import javax.swing.*;
import java.awt.*;

public class InField extends JPanel implements Incrementable {
    private JTextField[][] matrix;
    private final GridBagLayout layout;
    private GridBagConstraints c;

    public InField(){
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
        this.c.insets.set(2,2,2,2);
        this.c.fill = GridBagConstraints.BOTH;
        this.c.weighty = 0;
        this.c.weightx = 0.5;
    }

    private void setMatrix(final int row, final int column) {
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
                this.c.gridx = j;
                this.c.gridy = i;
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

    public void setMatrix(JTextField[][] matrix) {
        this.matrix = matrix;
    }
}
