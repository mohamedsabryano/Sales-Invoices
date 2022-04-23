/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.InvocieLine;
import com.model.InvoiceHeader;
import com.model.InvoiceLineModel;
import com.view.InvoiceFram;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ICON
 */
public class SelectTabel implements ListSelectionListener{
    
    private InvoiceFram fram;

    public SelectTabel(InvoiceFram fram) {
        this.fram = fram;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        int selectindex =fram.getHeaderT().getSelectedRow();
        System.out.println("Invoice selected"+selectindex);
        if(selectindex !=-1){
        InvoiceHeader selectInvo = fram.getInvoiceArray().get(selectindex);
        ArrayList<InvocieLine> lines =selectInvo.getLines();
        InvoiceLineModel linetabelmModel=new InvoiceLineModel(lines);
        fram.setInvoiceLines(lines);
        fram.getLineT().setModel(linetabelmModel);
        fram.getCustomername().setText(selectInvo.getCustomer());
        fram.getInvoicename().setText(""+selectInvo.getNum());
        fram.getInvoicetotal().setText(""+selectInvo.getInvoiceTotal());
        fram.getInvoicedate().setText(InvoiceFram.dateFormat.format(selectInvo.getInvDate()));
        }
        }
    
}
