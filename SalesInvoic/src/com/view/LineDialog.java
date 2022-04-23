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



public class LineDialog extends JDialog{
    private JTextField itemName;
  private JTextField itemcount;
     private JTextField itemprice;
  
  private JLabel itemcountlbl;
  private JLabel itempricelbl;
  private JLabel itemNamelbl;
  private JButton OkButton;
  private JButton CancelButton;
  
   
  
  public LineDialog (InvoiceFram fram) {
        itemName =new JTextField(20);
        itemNamelbl = new JLabel("Item Name");
        
        itemprice =new JTextField(20);
        itempricelbl = new JLabel("Item price");
        
        itemcount =new JTextField(20);
        itemcountlbl = new JLabel("Item count");
        
        OkButton =new JButton("Ok");
        CancelButton=new JButton("cancel");
        
        
        OkButton.setActionCommand("Lineok");
      CancelButton.setActionCommand("Linecancel");
      
       OkButton.addActionListener(fram.getActionListener());
      CancelButton.addActionListener(fram.getActionListener());
      
      setLayout(new GridLayout(3, 2));
      add(itemName);
      add(itemNamelbl);
      add(itemcount);
      add(itemcountlbl);
      add(itemprice);
      add(itempricelbl);
       add(OkButton);
      add(CancelButton);
        
        
    }

    public JTextField getItemName() {
        return itemName;
    }

    public JTextField getItemcount() {
        return itemcount;
    }

    public JTextField getItemprice() {
        return itemprice;
    }
   
}
