package com.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Collections;

public class ResultWindowRowRenderer extends DefaultTableCellRenderer
{
    public ResultWindowRowRenderer()
    {
        super();
        setOpaque(true);
    }
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column)
    {

         // ResultWindowModel resultwindowmodelObj=(ResultWindowModel) table.getModel();
         // ResultOutputTableStructure  resulttablestrucObj=(ResultOutputTableStructure) resultwindowmodelObj.getValueAtRow(row);
     //   WineTableModel wtm = (WineTableModel) table.getModel();
     //   Wine wine = (Wine) wtm.getValueAtRow(row);
         // ResultWindowModel rwm=(ResultWindowModel)table.getModel();
          //ResultOutputTableStructure rots= (ResultOutputTableStructure) rwm.getValueAtRow(row);

          //if(rots.getExpectedOutput()==rots.getActualOutput())
            // {
            //System.out.println("Lets fuck Nitish "+value+row+column);
    //    System.out.println("Test"+column+table.getValueAt(row, column).toString());
        if( column==1 && (table.getValueAt(row, column).toString().equals(table.getValueAt(row, column+1).toString())))
        {
  //          System.out.println("Test_B::"+column+table.getValueAt(row, column).toString()+table.getValueAt(row, column+1).toString());
            //System.out.println("TEST_GREEN"+row+"::"+column);
            //setForeground(Color.red);
            setBackground(Color.GREEN);
        }
        else if( column==2 && (table.getValueAt(row, column-1).toString().equals(table.getValueAt(row, column).toString())))
        {
//            System.out.println("Test_B::"+column+table.getValueAt(row, column).toString()+table.getValueAt(row, column+1).toString());
            //setForeground(Color.red);
            //System.out.println("TEST_GREEN"+row+"::"+column);
            setBackground(Color.GREEN);
        }
        else if(column==1 && (table.getValueAt(row, column-1).toString().equals(table.getValueAt(row, column).toString())==false))
        {
            //System.out.println("Test_B::"+row+"::"+column+"::"+column+table.getValueAt(row, column).toString());
            //System.out.println("TEST_RED"+row+"::"+column);
            setBackground(Color.red);
            //setForeground(Color.black);
        }
        else if( column==2 && (table.getValueAt(row, column-1).toString().equals(table.getValueAt(row, column).toString())==false))
        {
//            System.out.println("Test_B::"+column+table.getValueAt(row, column).toString()+table.getValueAt(row, column+1).toString());
            //setForeground(Color.red);
            //System.out.println("TEST_RED"+row+"::"+column);
            setBackground(Color.red);
        }
        else if(column==0)
        {
            setBackground(Color.white);
        }

        setText(value !=null ? value.toString() : "");
        //setBackground(Color.green);
            //}
        //else {
            //setBackground(Color.red);
          //   }

        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
}

