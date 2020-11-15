package prezoom.view;

import prezoom.Main;
import prezoom.model.GAttributes;
import prezoom.model.GObject;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/** TODO
 * @author Zhijie Lan<p>
 * create date: 2020/11/3
 **/

/**
 * Changed(Abhishek Sharma):
 *  --> class: InspectorPanel method:rearrangeValues()
 *      --code: update the Inspector panel with the selectedObj attributes in centerCanvas
 *
 *  --> added class:PanelKeyboardListner, method: keyPressed()
 *      --code:update center canvas object with the values in inspector panel attributes
 */

public class InspectorPanel extends JPanel
{

    private final ArrayList<JTextField> textBoxList = new ArrayList<>();
    private JTextField textBoxLabel;
    private JTextField textBoxX;
    private JTextField textBoxY;
    private JTextField textBoxW;
    private JTextField textBoxH;

    public InspectorPanel()
    {
        setBackground(Color.darkGray);                          //customize the panel
        setPreferredSize(new Dimension(100, -1));
        setLayout(new FlowLayout(FlowLayout.CENTER,5,10));

        TitledBorder title = BorderFactory.createTitledBorder("Inspector");
        title.setTitleColor(Color.white);
        setBorder(title);

        JLabel label = new JLabel("Label:");
        label.setForeground(Color.white);
        add(label);

        textBoxLabel = new JTextField(8);
        add(textBoxLabel);
        textBoxList.add(textBoxLabel);
        PanelKeyboardListner panelKeyListener = new PanelKeyboardListner();
        label = new JLabel("X:");
        label.setForeground(Color.white);
        add(label);
        textBoxLabel.addKeyListener(panelKeyListener);
        textBoxLabel.setEditable(true);
        textBoxLabel.setEnabled(true);
        textBoxX = new JTextField(7);
        add(textBoxX);
        textBoxList.add(textBoxX);
        textBoxX.addKeyListener(panelKeyListener);
        textBoxX.setEditable(true);
        textBoxX.setEnabled(true);
        label = new JLabel("Y:");
        label.setForeground(Color.white);
        add(label);

        textBoxY = new JTextField(7);
        add(textBoxY);
        textBoxY.addKeyListener(panelKeyListener);
        textBoxList.add(textBoxY);

        label = new JLabel("Width:");
        label.setForeground(Color.white);
        add(label);

        textBoxW = new JTextField(5);
        add(textBoxW);
        textBoxW.addKeyListener(panelKeyListener);
        textBoxList.add(textBoxW);

        label = new JLabel("Height:");
        label.setForeground(Color.white);
        add(label);

        textBoxH = new JTextField(5);
        add(textBoxH);
        textBoxH.addKeyListener(panelKeyListener);
        textBoxList.add(textBoxH);

    }
//    public void update(Graphics g){
//        rearrangeValues();
//    }

    /**
     * Set the values in the inspector panel textboxes
     * x, y, width, height
     */
    public void rearrangeValues()
    {
        GObject gatt = Main.app.centerCanvas.inspectedObj;
        GAttributes currAttr = null;
        if(gatt!=null){
            currAttr = gatt.getCurrentAttributes();
        }
        int i = 0;
        for(JTextField text : textBoxList)
        {
            if(!textBoxList.isEmpty()){
                if(i==0){
                    if(gatt!=null)
                        text.setText(""+currAttr.getLabel());//+gatt.getClass());
                }else if(i==1){
                    if(gatt!=null)
                        text.setText(""+currAttr.getX());//+gatt.x);
                }else if(i==2){
                    if(gatt!=null)
                        text.setText(""+currAttr.getY());//+gatt.y);
                }else if(i==3){
                    if(gatt!=null)
                        text.setText(""+currAttr.getWidth());//+gatt.width);
                }else if(i==4){
                    if(gatt!=null)
                        text.setText(""+currAttr.getHeight());//+gatt.height);
                }
            }
            i++;
        }
    }

    private class PanelKeyboardListner implements KeyListener
    {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        String keyCode = "0";
        public void keyTyped(KeyEvent e) {
            //System.out.println(e+"KEY TYPED: ");
        }
        public void keyPressed(KeyEvent e) {
                String text = "";
                if(e.getSource().equals(textBoxLabel)){
                    if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if(textBoxX.getText().length()>0)
                            text = textBoxLabel.getText().substring(0, textBoxLabel.getText().length()-1);
                    }else{
                        text = textBoxLabel.getText() + e.getKeyChar();
                    }
                    //Main.app.centerCanvas.inspectedObj.setLabel(text);
                    //Main.app.centerCanvas.repaint();
                    textBoxLabel.requestFocusInWindow();
                }
                else if(e.getSource().equals(textBoxX)){
                    //System.out.println("here123"+ Main.app.centerCanvas.selectedObj);
                    if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if(textBoxX.getText().length()>0)
                            text = textBoxX.getText().substring(0, textBoxX.getText().length()-1);
                    }else{
                        text = textBoxX.getText() + e.getKeyChar();
                    }
                    if(Main.app.centerCanvas.inspectedObj != null) {
                        if (text.length() == 0)
                            Main.app.centerCanvas.inspectedObj.getCurrentAttributes().setX(0);
                        else
                            Main.app.centerCanvas.inspectedObj.getCurrentAttributes().setX(Double.parseDouble(text));
                    }
                    Main.app.centerCanvas.repaint();
                    textBoxX.requestFocusInWindow();
                }
                else if(e.getSource().equals(textBoxY)){
                    if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if(textBoxY.getText().length()>0)
                            text = textBoxY.getText().substring(0, textBoxY.getText().length()-1);
                    }else{
                        text = textBoxY.getText() + e.getKeyChar();
                    }
                    if(Main.app.centerCanvas.inspectedObj != null) {
                        if (text.length() == 0)
                            Main.app.centerCanvas.inspectedObj.getCurrentAttributes().setY(0);
                        else
                            Main.app.centerCanvas.inspectedObj.getCurrentAttributes().setY(Double.parseDouble(text));
                    }
                    Main.app.centerCanvas.repaint();
                    textBoxY.requestFocusInWindow();
                }
                else if(e.getSource().equals(textBoxW)){
                    if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if(textBoxW.getText().length()>0)
                            text = textBoxW.getText().substring(0, textBoxW.getText().length()-1);
                    }else{
                        text = textBoxW.getText() + e.getKeyChar();
                    }
                    if(Main.app.centerCanvas.inspectedObj != null) {
                        if (text.length() == 0)
                            Main.app.centerCanvas.inspectedObj.getCurrentAttributes().setWidth(0);
                        else
                            Main.app.centerCanvas.inspectedObj.getCurrentAttributes().setWidth(Integer.parseInt(text));
                    }
                    Main.app.centerCanvas.repaint();
                    textBoxW.requestFocusInWindow();
                }
                else if(e.getSource().equals(textBoxH)) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if (textBoxH.getText().length() > 0)
                            text = textBoxH.getText().substring(0, textBoxH.getText().length() - 1);
                    } else {
                        text = textBoxH.getText() + e.getKeyChar();
                    }
                    if (Main.app.centerCanvas.inspectedObj != null){
                        if (text.length() == 0)
                            Main.app.centerCanvas.inspectedObj.getCurrentAttributes().setHeight(0);
                        else
                            Main.app.centerCanvas.inspectedObj.getCurrentAttributes().setHeight(Integer.parseInt(text));
                    }
                    Main.app.centerCanvas.repaint();
                    textBoxH.requestFocus();
                }
                //keyCode = "0";
//            }
            //Main.app.centerCanvas.selectedObj.setX(e.getKeyCode());
            //Main.app.centerCanvas.repaint();
        }

        /** Handle the key-released event from the text field. */
        public void keyReleased(KeyEvent e) {
            //System.out.println(e+"KEY RELEASED: ");
        }
    }

}