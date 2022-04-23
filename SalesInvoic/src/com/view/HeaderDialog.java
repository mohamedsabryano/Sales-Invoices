/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ICON
 */
public class HeaderDialog extends JDialog{
 private JTextField CNameField;
  private JTextField DateField;
  private JLabel cNameLbl;
  private JLabel DateLbl;
  private JButton OkB;
  private JButton CancelB;
  
  
  public HeaderDialog(InvoiceFram fram){
      cNameLbl =new JLabel("Customer Name:");
      CNameField = new JTextField(20);
      DateLbl = new JLabel("Invoice Date:");
      DateField = new JTextField(20);
      OkB = new JButton ("Ok");
      CancelB = new JButton ("Cancel");
      OkB.setActionCommand("NewLineok");
      CancelB.setActionCommand("DeleteLinecancel");
      
      OkB.addActionListener(fram.getActionListener());
      CancelB.addActionListener(fram.getActionListener());
      setLayout(new GridLayout(3, 2));
      add(DateLbl);
      add(DateField);
      add(cNameLbl);
      add(CNameField);
      add(OkB);
      add(CancelB);
  } 
 public JTextField getCustNameField() {
        return this.CNameField;
    }

    public JTextField getInvDateField() {
        return this.DateField;
    }
}  
 
    

