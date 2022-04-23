/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ICON
 */
public class InvoiceLineModel extends AbstractTableModel{
     private ArrayList<InvocieLine> linesArray;
    private String[] colums ={"Item name","Price","Count","Total"};

    public InvoiceLineModel(ArrayList<InvocieLine> linesArray) {
        this.linesArray = linesArray;
    }

    @Override
    public int getRowCount() {
        return linesArray == null ? 0 : linesArray.size();
    }

    @Override
    public int getColumnCount() {
         return colums.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (linesArray == null)
        {return "";}
        else {InvocieLine line =linesArray.get(rowIndex);
        switch(columnIndex){
            case 0:return line.getItem();
            case 1:return line.getPrice();
            case 2:return line.getCount();
            case 3:return line.getLineTotal();
        }
         return "";
    }
    }

    @Override
    public String getColumnName(int column) {
         return colums[column];
       
    }
    
    
    
}
