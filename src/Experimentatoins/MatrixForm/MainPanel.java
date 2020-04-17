package Experimentatoins.MatrixForm;

import Experimentatoins.support.GridBagConstraintCustom;
import Experimentatoins.support.Matrix;
import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JFrame {

    private InField matrixA, matrixB;
    private MatrixFormat matrixAFormat, matrixBFormat;
    private MatrixFunctions matrixFunctions;
    private GridBagConstraints c;



    public MainPanel(String title){
        super(title);
        matrixA = new InField();
        matrixB = new InField();
        matrixAFormat = new MatrixFormat();
        matrixBFormat = new MatrixFormat();
        matrixFunctions = new MatrixFunctions();
        c= new GridBagConstraints();

        setLayout(new GridBagLayout());

        matrixAFormat.setFields(matrixA);
        matrixBFormat.setFields(matrixB);
        containerListener(matrixA);
        containerListener(matrixB);


        addMatrixAFormat();
        addMatrixBFormat();

        addMatrixFunctions();

        addMatrixA();
        matrixA.setBackground(new Color(100,125,200));
        matrixAFormat.setBackground(new Color(100,125,200));

        addMatrixB();
        matrixB.setBackground(new Color(150,255,2));
        matrixBFormat.setBackground(new Color(150,255,2));

        matrixFunctions.setListener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                int[][] extractedA , extractedB;
                switch (text){
                    case "inv":
                        extractedA = extractMatrixVals(matrixA);
                        try {
                            updateFields(Matrix.inverseMatrix(extractedA));
                        }catch (NullPointerException ex){

                        }
                        break;
                    case "add":
                        extractedA = extractMatrixVals(matrixA);
                        extractedB = extractMatrixVals(matrixB);
                        updateFields(Matrix.addMatrix(extractedA,extractedB));
                        break;
                    case "sub":
                        extractedA = extractMatrixVals(matrixA);
                        extractedB = extractMatrixVals(matrixB);
                        updateFields(Matrix.subtractMatrix(extractedA,extractedB));
                        break;
                    case "mul":
                        extractedA = extractMatrixVals(matrixA);
                        extractedB = extractMatrixVals(matrixB);
                        updateFields(Matrix.multiplyMatrix(extractedA,extractedB));
                        break;
                }
            }
        });




        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        requestFocus();
    }

    private void addMatrixFunctions() {
        c.gridwidth = 5;
        c.weighty = 0;
        c.fill = 2;
        c.gridx = 0;
        c.gridy = 4;
        add(matrixFunctions, c);
    }

    private void addMatrixB() {
        c.weighty = 1;
        c.weightx = 1;
        c.fill =2;
        c.anchor = GridBagConstraintCustom.NORTH;
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 2;

        add(matrixB, c);

    }

    private void addMatrixA() {
        c.weighty = 1;
        c.weightx = 1;
        c.fill = 2;
        c.anchor = GridBagConstraintCustom.NORTH;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 2;

        add(matrixA,c);

    }

    private void addMatrixAFormat() {
        c.weightx = 1;
        c.weighty = 0;
        c.fill = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 2;
        add(matrixAFormat,c);
    }

    private void addMatrixBFormat(){
        c.weightx = 1;
        c.weighty = 0;
        c.fill = 2;
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 2;
        add(matrixBFormat,c);
    }

    private void containerListener(JPanel container) {
        container.addContainerListener(new ContainerListener() {
            @Override
            public void componentAdded(ContainerEvent e) {
                refresh();
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                refresh();
            }
        });
    }

    private void refresh() {
        repaint();
        revalidate();
        pack();
    }

    private int[][] extractMatrixVals(InField matrix) {
        int rows = matrix.getRows(), columns = matrix.getColumns();
        int[][] extractedMatrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns ; j++) {
                System.out.println(matrix.getMatrix()[i][j].getText());
                extractedMatrix[i][j] = Integer.parseInt(matrix.getMatrix()[i][j].getText());
            }
        }

        return extractedMatrix;
    }

    private void updateFields(double[][] processedMatrix){
        for (int i = 0; i < processedMatrix.length; i++) {
            for (int j = 0; j < processedMatrix[0].length; j++) {
                matrixA.getMatrix()[i][j].setText(Double.toString(processedMatrix[i][j]));
            }
        }
        refresh();
    }

    private void updateFields(long[][] processedMatrix){
        for (int i = 0; i < processedMatrix.length; i++) {
            for (int j = 0; j < processedMatrix[0].length; j++) {
                matrixA.getMatrix()[i][j].setText(Integer.toString(Math.round(processedMatrix[i][j])));
            }
        }
        refresh();
    }

    private void updateFields(int[][] processedMatrix){
        for (int i = 0; i < processedMatrix.length; i++) {
            for (int j = 0; j < processedMatrix[0].length; j++) {
                matrixA.getMatrix()[i][j].setText(Integer.toString(Math.round(processedMatrix[i][j])));
            }
        }
        refresh();
    }

}
