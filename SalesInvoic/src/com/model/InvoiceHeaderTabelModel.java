/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

;
import com.view.InvoiceFram;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ICON
 */
public class InvoiceHeaderTabelModel extends AbstractTableModel{
    private ArrayList<InvoiceHeader> invoiceArray;
    private String[] colums ={"Invoice Num","Invoice Date","Customer","Invoice Total"};

    public InvoiceHeaderTabelModel(ArrayList<InvoiceHeader> invoiceArray) {
        this.invoiceArray = invoiceArray;
    }
    

    @Override
    public int getRowCount() {
        return invoiceArray.size();
        
    }

    @Override
    public int getColumnCount() {
        return colums.length;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader inv =invoiceArray.get(rowIndex);
        switch(columnIndex){
            case 0: return inv.getNum();
            case 1:return InvoiceFram.dateFormat.format(inv.getInvDate());
            case 2:return inv.getCustomer();
            case 3:return inv.getInvoiceTotal();
        }
        return "";
        
    }
    @Override
    public String getColumnName(int column){
        return colums[column];
        
    }
    
    
}
