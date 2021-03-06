package prezoom.view.table;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The custom table editor for the cells of JTables that has Color values
 * @author Oracle<p>
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 **/
public class ColorEditor extends AbstractCellEditor
        implements TableCellEditor,
        ActionListener
{
    Color currentColor;
    JButton button;
    JColorChooser colorChooser;
    JDialog dialog;
    protected static final String EDIT = "edit";

    public ColorEditor()
    {
        //Set up the editor (from the table's point of view),
        //which is a button.
        //This button brings up the color chooser dialog,
        //which is the editor from the user's point of view.
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);

        //Set up the dialog that the button brings up.
        colorChooser = new JColorChooser();
        dialog = JColorChooser.createDialog(button,
                "Pick a Color",
                true,  //modal
                colorChooser,
                this,  //OK button handler
                null); //no CANCEL button handler
    }

    /**
     * Handles events from the editor button and from
     * the dialog's OK button.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (EDIT.equals(e.getActionCommand()))
        {
            //The user has clicked the cell, so
            //bring up the dialog.
            button.setBackground(currentColor);
            colorChooser.setColor(currentColor);
            dialog.setVisible(true);

            //Make the renderer reappear.
            fireEditingStopped();

        } else
        { //User pressed dialog's "OK" button.
            currentColor = colorChooser.getColor();
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getCellEditorValue()
    {
        return currentColor;
    }

    /**
     * {@inheritDoc}
     */
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column)
    {
        currentColor = (Color) value;
        return button;
    }
}
