package UI.MyComboBox;

import Type.Chioce;
import UI.MComboBox;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MyComboBox<E> extends JComboBox implements ActionListener {


    //持有这个变量是因为在变化时直接改变guize的显示，如果直接设置itemlistener会有延时
    JTextField jTextField;
   // public List<CheckValue> checkValues;
    //通过must影响start的值，判断是否有空项
    int start;
    public  static  boolean init=false;


    public MyComboBox(JTextField jTextField, boolean must) {
        this.jTextField = jTextField;
       // checkValues = new ArrayList<>();
        if (must)
            start = 0;
        else
            start = 1;
        this.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        itemSelected();
                    }
                });
    }

    public void itemSelected() {
        if (super.getSelectedItem() instanceof CheckValue) {
            if(init) {
                SwingUtilities.invokeLater(
                        new Runnable() {
                            public void run() {
                                /*选中后依然保持当前弹出状态*/
                                showPopup();
                            }
                        });
            }
            CheckValue jcb = (CheckValue) super.getSelectedItem();
            jcb.bolValue = (!jcb.bolValue);
            //setSelectedIndex(getSelectedIndex());
            jTextField.setText(getComboVc());
        }
       // this.setSelectedIndex(0);

    }


//    @Override
//    public Object getSelectedItem() {
//        Object o = super.getSelectedItem();
//        if (o instanceof CheckValue) {
//            return ((CheckValue) o).value.daima;
//        } else return "多选出现异常";
//    }



    /*获取选取的对象*/
    public String getComboVc() {
        String s = "";
        for (int i = start; i < getItemCount(); i++) {
            CheckValue jcb = (CheckValue) getItemAt(i);
            if (jcb.bolValue) {
                s = s + jcb.value.mingzi + ",";
            }
        }
        if (s.length() > 1)
            s = s.substring(0, s.length() - 1);
        return s;
    }

    public String getComboid() {
        String s = "";
        for (int i = start; i < getItemCount(); i++) {
            CheckValue jcb = (CheckValue) getItemAt(i);
            if (jcb.bolValue) {
                s = s + jcb.value.daima + ",";
            }
        }
        if (s.length() > 1)
            s = s.substring(0, s.length() - 1);
        return s;
    }



}