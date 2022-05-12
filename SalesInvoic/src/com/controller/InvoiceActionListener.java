/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.InvocieLine;
import com.model.InvoiceHeader;
import com.model.InvoiceHeaderTabelModel;
import com.model.InvoiceLineModel;
import com.view.HeaderDialog;
import com.view.InvoiceFram;
import com.view.LineDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ICON
 */
public class InvoiceActionListener implements ActionListener{
    private InvoiceFram fram;
    private HeaderDialog headerDialog;
    private LineDialog lineDialog;
   
    public InvoiceActionListener(InvoiceFram fram){
        this.fram=fram;
    }

    public InvoiceActionListener(HeaderDialog headerDialog, LineDialog lineDialog) {
        this.headerDialog = headerDialog;
        this.lineDialog = lineDialog;
    }
    
 
    @Override
    public void actionPerformed(ActionEvent e) {
 switch(e.getActionCommand()){
     case"Save Files":
         SaveFiles(); 
     break;
     case "Load Files":
         LoadFiles();
     break;
     case "New Invoice":
         creatNewInvoice();
     break;
     case "Delete Invoice":
         creatDeleteInvoice();
     break;
     case "New Line":
         creatNewline();
     break;
     case "Delete Line":
         Deleteline();
     break;
     case "NewLineok":
     NewLineok();
     break;
     case "DeleteLinecancel":
         DeleteLinecancel();
         break;
         case "Lineok":
             Lineok();
             break;
         case "Linecancel":
             Linecancel();
                     break;
 }
    }

    private void SaveFiles() {
        ArrayList<InvoiceHeader> insArray =fram.getInvoiceArray();
        JFileChooser ff=new JFileChooser();
         try{
         int result=ff.showOpenDialog(fram);
         
         if(result==JFileChooser.APPROVE_OPTION){
             File headerFile = ff.getSelectedFile();
             FileWriter fm =new FileWriter(headerFile);
             String heads = "";
             String lines="";
             for(InvoiceHeader invoic : insArray){
                 heads += invoic.toString();
                 heads +="\n";
                 for(InvocieLine line : invoic.getLines()){
                     lines +=line.toString();
                     lines +="\n";
                 }
             }
             result = ff.showSaveDialog(fram);
             File linefile = ff.getSelectedFile();
             FileWriter fw =new FileWriter(linefile);
             fm.write(heads);
             fw.write(lines);
             fm.close();
             fw.close();
                     
         } 
    }catch (IOException ex){
         JOptionPane.showMessageDialog(fram, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE );
    }
        
    }
    private void creatNewInvoice() {
        headerDialog =new HeaderDialog(fram);
        headerDialog.setVisible(true);
    }

    private void creatDeleteInvoice() {
        int selectInvoIndex = fram.getHeaderT().getSelectedRow();
        if (selectInvoIndex != -1){
            fram.getInvoiceArray().remove(selectInvoIndex);
            fram.getHeaderTabelModel().fireTableDataChanged();
            fram.getLineT().setModel(new InvoiceLineModel(null));
            fram.setInvoiceLines(null);
        fram.getCustomername().setText("");
        fram.getInvoicename().setText("");
        fram.getInvoicetotal().setText("");
        fram.getInvoicedate().setText("");
        }
    }

    private void creatNewline() {
         lineDialog =new LineDialog(fram);
        lineDialog.setVisible(true);
    }
    private void Deleteline() {
        int selectedInvoiceindex = fram.getHeaderT().getSelectedRow();
        int selectlineindex = fram.getLineT().getSelectedRow();
        if (selectlineindex !=-1){
            fram.getInvoiceLines().remove(selectlineindex);
            InvoiceLineModel lineTmodel = (InvoiceLineModel)fram.getLineT().getModel();
            lineTmodel.fireTableDataChanged();
            fram.getHeaderTabelModel().fireTableDataChanged();
            fram.getHeaderT().setRowSelectionInterval(selectedInvoiceindex, selectedInvoiceindex);
        }

    }

    private void LoadFiles() {
         JFileChooser fileChooser=new JFileChooser();
         try{
         int result=fileChooser.showOpenDialog(fram);
         
         if(result==JFileChooser.APPROVE_OPTION){
             File headerFile = fileChooser.getSelectedFile();
            Path heraderPath = Paths.get(headerFile.getAbsolutePath());
             List<String> headerLines =Files.readAllLines(heraderPath);
             ArrayList<InvoiceHeader> invoiceHeaders =new ArrayList<>();
             for(String headerline:headerLines){
                 String[] arr =headerline.split(",");
                 String str1 =arr[0];
                 String str2 =arr[1];
                 String str3 =arr[2];
                 int code =Integer.parseInt(str1);
                 Date invodate = InvoiceFram.dateFormat.parse(str2);
                 InvoiceHeader header = new InvoiceHeader(code, str3, invodate);
                 invoiceHeaders.add(header);
                        
             }
             fram.setInvoiceArray(invoiceHeaders);
            result=fileChooser.showOpenDialog(fram); 
         if(result==JFileChooser.APPROVE_OPTION){ 
             File linFile =fileChooser.getSelectedFile();
             Path linPath =Paths.get(linFile.getAbsolutePath());
             List<String> linelines =Files.readAllLines(linPath);
             ArrayList<InvocieLine> invoiceLines =new ArrayList<>();
             for(String lineLine : linelines){
                 String[] arr =lineLine.split(",");
                 String str1 =arr[0];  //invoic num
                 String str2 =arr[1];   //item name
                 String str3 =arr[2];  //price
                 String str4=arr[3];   //count
                 int invcode =Integer.parseInt(str1);
                 double price = Double.parseDouble(str3);
                 int count =Integer.parseInt(str4);
                 InvoiceHeader inv =fram.getInvObject(invcode);
                 InvocieLine line = new InvocieLine(str2, price, count, inv);
                 inv.getLines().add(line);
                }
             System.out.println("Headers File:");
             for(String headerline:headerLines){
                 System.out.println(headerline);
             }
             System.out.println("-----------------------------------");
              System.out.println("Lines File:");
              for(String lineLine : linelines){
                  System.out.println(lineLine);
              }
         
             
             }
                
             InvoiceHeaderTabelModel headerTabelModel=new InvoiceHeaderTabelModel(invoiceHeaders);
             fram.setHeaderTabelModel(headerTabelModel);
             fram.getHeaderT().setModel(headerTabelModel);
         } 
         
    }catch(Exception ex){
             JOptionPane.showMessageDialog(fram, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE );
    }
    
}

    private void NewLineok() {
        headerDialog.setVisible(false);
        String custname = headerDialog.getCustomerField().getText();
        String custdata = headerDialog.getDateField().getText();
        Date d =new Date();
        try {
            d =InvoiceFram.dateFormat.parse(custdata);
        } catch (ParseException e){
            JOptionPane.showMessageDialog(fram, "NO Parse date "," Invalid Data", JOptionPane.ERROR_MESSAGE);
        }
        int invNum =0;
        for(InvoiceHeader inv :fram.getInvoiceArray()){
            if (inv.getNum() > invNum) { invNum = inv.getNum();}
        }
        invNum++;
        InvoiceHeader NewInv = new InvoiceHeader(invNum, custname, d);
        fram.getInvoiceArray().add(NewInv);
        fram.getHeaderTabelModel().fireTableDataChanged();
        headerDialog.dispose();
        headerDialog = null;
    }

    private void DeleteLinecancel() {
        headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog = null;
    }

    private void Lineok() {
          lineDialog.setVisible(false);
          String name =lineDialog.getItemNameField().getText();
          String sab =lineDialog.getItemCountField().getText();
          String sab2=lineDialog.getItemPriceField().getText();
          int count =1;
          double price=1;
          
          try {
            count =Integer.parseInt(sab);
           } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(fram,"Convert Invaild", "Invalid number fromat",JOptionPane.ERROR_MESSAGE);
           }
          try {
            price =Double.parseDouble(sab2);
           } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(fram,"Convert Invaild", "Invalid number fromat",JOptionPane.ERROR_MESSAGE);
           }
          int selectinHeader =fram.getHeaderT().getSelectedRow();
            if(selectinHeader != -1){
               InvoiceHeader inHeader = fram.getInvoiceArray().get(selectinHeader);
            InvocieLine line = new InvocieLine(name, price, count, inHeader);
            fram.getInvoiceLines().add(line);
            InvoiceLineModel inLineModel =(InvoiceLineModel)fram.getLineT().getModel();
             inLineModel.fireTableDataChanged();
             fram.getHeaderTabelModel().fireTableDataChanged();
            }
            fram.getHeaderT().setRowSelectionInterval(selectinHeader, selectinHeader);
             lineDialog.dispose();
             lineDialog = null;
        
  
        }  

    private void Linecancel() {
         lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }
       
    }


 