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

public class MyComboBox extends JComboBox implements ActionListener {


    //持有这个变量是因为在变化时直接改变guize的显示，如果直接设置itemlistener会有延时
    JTextField jTextField;
    public List<CheckValue> checkValues;
    //通过must影响start的值，判断是否有空项
    int start;

    public MyComboBox(JTextField jTextField, boolean must) {
        this.jTextField = jTextField;
        checkValues = new ArrayList<>();
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

    @Override
    public void addItem(Object item) {
        super.addItem(item);
        checkValues.add((CheckValue) item);
    }

    public void removeAllItems() {
        super.removeAllItems();
        checkValues.clear();
    }

    public void itemSelected() {
        if (super.getSelectedItem() instanceof CheckValue) {
            SwingUtilities.invokeLater(
                    new Runnable() {
                        public void run() {
                            /*选中后依然保持当前弹出状态*/
                            showPopup();
                        }
                    });
            CheckValue jcb = (CheckValue) super.getSelectedItem();
            jcb.bolValue = (!jcb.bolValue);
            setSelectedIndex(getSelectedIndex());
            jTextField.setText(getComboVc());
        }
    }


    @Override
    public Object getSelectedItem() {
        Object o = super.getSelectedItem();
        if (o instanceof CheckValue) {
            return ((CheckValue) o).value.daima;
        } else return "多选出现异常";
    }

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



}