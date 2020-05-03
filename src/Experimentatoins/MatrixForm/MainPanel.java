package Experimentatoins.MatrixForm;

import Experimentatoins.support.GridBagConstraintCustom;
import Experimentatoins.support.Matrix;
import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

public class MainPanel extends JFrame {

    private InField matrixA, matrixB, matrixC;
    private MatrixFormat matrixAFormat, matrixBFormat;
    private MatrixFunctions matrixFunctions;
    private GridBagConstraintCustom c;
    private JLabel outputIdentifier, operationSymbol;



    public MainPanel(String title){
        super(title);
        matrixA = new InField();
        matrixB = new InField();
        matrixC = new InField();
        matrixC.setVisible(false);
        matrixAFormat = new MatrixFormat();
        matrixBFormat = new MatrixFormat();
        matrixFunctions = new MatrixFunctions();
        c= new GridBagConstraintCustom();
        outputIdentifier = new JLabel("=======>");
        operationSymbol = new JLabel();
        operationSymbol.setVisible(false);
        outputIdentifier.setVisible(false);

        setLayout(new GridBagLayout());

        matrixAFormat.setFields(matrixA); // dependency injector
        matrixBFormat.setFields(matrixB); // dependency injector
        containerListener(matrixA);
        containerListener(matrixB);


        addMatrixAFormat();
        addMatrixBFormat();

        addMatrixFunctions();

        addMatrixA();
        addMatrixB();
        addMatrixC();

        addOutputIdentifier();

        addOperationSmbol();

        color();



// Switch to link matrix modifier button class (MatrixFormat) and Matrix class(InField)
        Calculate();


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        requestFocus();
    }

    private void Calculate() {
        matrixFunctions.setListener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                int[][] extractedA , extractedB;
                switch (text){
                    case "inv":
                        extractedA = extractMatrixVals(matrixA);
                        try {
                            updateFields(Matrix.inverseMatrix(extractedA));
                            operationSymbol.setText("A`");
                        }catch (NullPointerException ex){

                        }
                        break;
                    case "add":
                        extractedA = extractMatrixVals(matrixA);
                        extractedB = extractMatrixVals(matrixB);
                        updateFields(Matrix.addMatrix(extractedA,extractedB));
                        operationSymbol.setText("+");
                        refresh();
                        break;
                    case "sub":
                        extractedA = extractMatrixVals(matrixA);
                        extractedB = extractMatrixVals(matrixB);
                        updateFields(Matrix.subtractMatrix(extractedA,extractedB));
                        operationSymbol.setText("-");
                        break;
                    case "mul":
                        extractedA = extractMatrixVals(matrixA);
                        extractedB = extractMatrixVals(matrixB);
                        updateFields(Matrix.multiplyMatrix(extractedA,extractedB));
                        operationSymbol.setText("x");
                        refresh();
                        break;
                }
            }
        });
    }

    private void addOperationSmbol() {
        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraintCustom.CENTER;
        c.fill = 0;
        operationSymbol.setFont(new Font("Monospace",Font.BOLD,20));
        add(operationSymbol,c);
    }

    private void color() {
        matrixA.setBackground(new Color(0,0,255));
        matrixAFormat.setBackground(new Color(0,0,255));
        matrixB.setBackground(new Color(255,0,0));
        matrixBFormat.setBackground(new Color(255,0,0));
        matrixC.setBackground(new Color(0,255,0));
        outputIdentifier.setForeground(new Color(0,255,0));
    }

    private void addMatrixFunctions() {
        /*
        Matrix Operation Buttons Adder
         */
        c.gridwidth = 5;
        c.weighty = 0;
        c.fill = 2;
        c.gridx = 0;
        c.gridy = 4;
        add(matrixFunctions, c);
    }

    private void addMatrixB() {
        /*
        left hand side matrix adder
         */
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
        /*
        right hand side Matrix adder
         */
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
        /*
        Left hand side Matrix Row and Column modifier bbuttons
         */
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
        /*
        Right hand side Matrix Row and Column modifier buttonsr
         */
        c.weightx = 1;
        c.weighty = 0;
        c.fill = 2;
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 2;
        add(matrixBFormat,c);
    }

    private void addMatrixC(){
        /*
        output matrix
         */
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraintCustom.BOTH;
        c.anchor = GridBagConstraintCustom.CENTER;
        c.gridx = 6;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 2;
        matrixC.setPreferredSize(new Dimension(150,-1));

        add(matrixC,c);
    }

    private void addOutputIdentifier(){
        /*
        label that points to output
         */
        c.reset();
        c.gridx = 5;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraintCustom.CENTER;
        c.fill = 2;
        add(outputIdentifier,c);
    }


    private void containerListener(JPanel container) {
        /*
        refreshes the Frame when the size changes
         */
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



    private void visibleAnswer(){
        matrixC.setVisible(true);
        outputIdentifier.setVisible(true);
        operationSymbol.setVisible(true);
    }

    private void refresh() {
        repaint();
        revalidate();
        pack();
    }

    //
    private int[][] extractMatrixVals(InField matrix) {
        int rows = matrix.getRows(), columns = matrix.getColumns();
        int[][] extractedMatrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns ; j++) {
                final var text = matrix.getMatrix()[i][j].getText();
                extractedMatrix[i][j] = Integer.parseInt(text);
            }
        }

        return extractedMatrix;
    }

    private void updateFields(double[][] processedMatrix){
        matrixC.setMatrix(processedMatrix.length,processedMatrix[0].length);
        for (int i = 0; i < processedMatrix.length; i++) {
            for (int j = 0; j < processedMatrix[0].length; j++) {
                matrixC.getMatrix()[i][j].setText(Double.toString(processedMatrix[i][j]));
            }
        }
        visibleAnswer();
        refresh();
    }

    private void updateFields(long[][] processedMatrix){
        matrixC.setMatrix(processedMatrix.length,processedMatrix[0].length);
        for (int i = 0; i < processedMatrix.length; i++) {
            for (int j = 0; j < processedMatrix[0].length; j++) {
                matrixC.getMatrix()[i][j].setText(Integer.toString(Math.round(processedMatrix[i][j])));
            }
        }
        visibleAnswer();
        refresh();
    }

    private void updateFields(int[][] processedMatrix){
        matrixC.setMatrix(processedMatrix.length,processedMatrix[0].length);
        for (int i = 0; i < processedMatrix.length; i++) {
            for (int j = 0; j < processedMatrix[0].length; j++) {
                matrixC.getMatrix()[i][j].setText(Integer.toString(Math.round(processedMatrix[i][j])));
            }
        }
        visibleAnswer();
        refresh();
    }

}
