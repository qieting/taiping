package UI;

import Type.Chioce;
import Type.Mytype;
import Type.TypeMannger;
import UI.MyComboBox.CheckListCellRenderer;
import UI.MyComboBox.CheckValue;
import UI.MyComboBox.MyComboBox;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TemplateJpanel extends JPanel {


    Mytype mytype;
    JTextField title, guize, must;
    JComponent chioce;

    public TemplateJpanel(Mytype mytype) {
        this.setLayout(null);
        this.mytype = mytype;
        this.setBackground(Color.WHITE);
        //初始化

        title = new JTextField(mytype.title);

        guize = new JTextField("");
        must = new JTextField("*");
        chioce = initChioce(mytype);
        title.setBackground(Color.WHITE);

        //设置大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double w = screenSize.width * 0.4 - 25;
        must.setSize(20, 40);
        title.setSize((int) (w * 0.30), 40);
        chioce.setSize((int) (w * 0.35), 40);
        guize.setSize((int) (w * 0.50), 40);
        //是否可编辑
        must.setEnabled(false);
        title.setEnabled(false);
        guize.setEnabled(false);

        //设置内容

        title.setDisabledTextColor(Color.BLACK);
        guize.setDisabledTextColor(Color.BLACK);
        if (mytype.must) {
            must.setDisabledTextColor(Color.RED);
            must.setText("*");
        } else {
            must.setText(" ");
        }

        //删除标题和星号的边界
        title.setBorder(new EmptyBorder(0, 0, 0, 0));
        must.setBorder(new EmptyBorder(0, 0, 0, 0));
        //添加布局
        this.add(title);
        this.add(must);
        this.add(chioce);
        this.add(guize);
        //设置位置
        title.setLocation(0, 0);
        must.setLocation(title.getX() + title.getWidth() + 5, 0);
        chioce.setLocation(must.getX() + must.getWidth() + 5, 0);
        guize.setLocation(chioce.getX() + chioce.getWidth() + 5, 0);
        //设置大小
        this.setPreferredSize(new Dimension(guize.getWidth() + guize.getX(), 40));


    }

    public void setValue(String ss) {
        String s = null;
        switch (mytype.type) {
            case string:
            case Date:
            case intt:
                ((JTextField) chioce).setText(ss);
                break;
            case dan:

                List<Chioce> chioces = mytype.chioces;
                JComboBox comboBox;
                comboBox = (JComboBox) chioce;
                for (int i = 0; i < chioces.size(); i++) {
                    if (chioces.get(i).daima.equals(ss)) {
                        comboBox.setSelectedIndex(i);
                        guize.setText(chioces.get(i).mingzi);
                        return;
                    }
                }


                break;
            case shuang:

                MyComboBox jcomboBox = (MyComboBox) chioce;
                ComboBoxModel <CheckValue> checkValues = jcomboBox.getModel();
                String q[] = ss.split(",");
                for(int i =0 ;i<checkValues.getSize();i++){
                    CheckValue checkValue =checkValues.getElementAt(i);
                    checkValue.bolValue = false;
                    for (String ssss : q) {
                        if (checkValue.value.daima.equals(ssss)) {
                            checkValue.bolValue = true;
                        }
                    }
                }
                guize.setText(jcomboBox.getComboVc());



        }
    }

    public JComponent initChioce(Mytype mytype) {
        JComboBox comboBox;
        switch (mytype.type) {
            case string:
            case Date:
            case intt:
                chioce = new JTextField(mytype.getDefault());
                chioce.addFocusListener(new FocusListener() {
                                            @Override
                                            public void focusGained(FocusEvent e) {
                                            }

                                            @Override
                                            public void focusLost(FocusEvent e) {
                                                String text;
                                                text = ((JTextField) chioce).getText();
                                                if (!mytype.changevalidation(text)) {
                                                    title.setBackground(Color.RED);
                                                } else {
                                                    title.setBackground(Color.WHITE);
                                                }
                                            }
                                        }
                );
                break;
            case dan:
                chioce = new MComboBox();
                comboBox = (JComboBox) chioce;
                String q = mytype.chioces.get(0).mingzi;
                guize.setText(q);
                ((JComboBox) chioce).addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        try {
                            String q = ((Chioce) e.getItem()).mingzi;
                            guize.setText(q);
                        } catch (ArrayIndexOutOfBoundsException ee) {
                            System.out.println(mytype.title + "发生数组错误，疑似删除");
                        }
                    }
                });
                if (mytype.title.equals("渠道小类")) {
                    comboBox.removeAllItems();
                    comboBox.addItem(new Chioce("", ""));
                    comboBox.addItem(new Chioce("02030100", "个人代码"));

                } else {
                    List<Chioce> chioces = mytype.chioces;
                    for (Chioce chioce : chioces) {
                        comboBox.addItem(chioce);
                    }
                }

                break;

            case shuang:
                List<Chioce> chioces = mytype.chioces;
                chioce = new MyComboBox(guize, mytype.must);
                comboBox = (MyComboBox) chioce;
                chioces = mytype.chioces;
//                if (mytype.title.equals("渠道小类")) {
//
//                    CheckValue checkValue = new CheckValue();
//                    checkValue.bolValue = false;
//                    checkValue.value = new Chioce("", "");
//                    comboBox.addItem(checkValue);
//                    checkValue = new CheckValue();
//                    checkValue.bolValue = false;
//                    checkValue.value = new Chioce("02030100", "个人代码");
//                    comboBox.addItem(checkValue);
//                } else
                if (mytype.title.equals("车辆种类")) {
                    CheckValue checkValue = new CheckValue();
                    checkValue.bolValue = false;
                    checkValue.value = new Chioce("", "");
                    comboBox.addItem(checkValue);
                } else {
                    for (Chioce chioce : chioces) {
                        CheckValue checkValue = new CheckValue();
                        checkValue.value = chioce;
                        checkValue.bolValue = false;
                        comboBox.addItem(checkValue);
                    }
                }
                comboBox.setRenderer(new CheckListCellRenderer((MyComboBox)comboBox));
                break;
        }
        return chioce;

    }

    public String getContent() {
        String s = null;
        switch (mytype.type) {
            case string:
            case Date:
            case intt:
                s = ((JTextField) chioce).getText();
                if (!mytype.changevalidation(s))
                    return null;
                break;
            case dan:
                s = (((Chioce) ((JComboBox) chioce).getModel().getSelectedItem()).daima);
                if (mytype.must && s.length() == 0) {
                    return null;
                }
                break;
            case shuang:

                MyComboBox jcomboBox = (MyComboBox) chioce;
                s = jcomboBox.getComboid();
                if (mytype.must && s.length() == 0) {
                    return null;
                }
                break;
        }
        return s == null ? "" : s;
    }

}
