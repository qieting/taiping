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
        chioce = getChioce(mytype);
        title.setBackground(Color.WHITE);

        //设置大小
        must.setSize(20, 40);
        title.setSize(150, 40);
        chioce.setSize(200, 40);
        guize.setSize(300, 40);
        //是否可编辑
        must.setEnabled(false);
        title.setEnabled(false);
        guize.setEnabled(false);

        //设置内容
        title.setText(mytype.title);
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
                        return;
                    }
                }


                break;
            case shuang:

                MyComboBox jcomboBox = (MyComboBox) chioce;
                List<CheckValue> checkValues = jcomboBox.checkValues;
                String q[] = ss.split(",");
                for (CheckValue checkValue : checkValues) {
                    checkValue.bolValue = false;
                    for (String ssss : q) {
                        if (checkValue.value.mingzi.equals(ssss)) {
                            checkValue.bolValue = true;
                        }
                    }
                }


        }
    }

    public JComponent getChioce(Mytype mytype) {
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
                List<Chioce> chioces = mytype.chioces;
                System.out.println(mytype.title);
                comboBox = (JComboBox) chioce;
                for (Chioce chioce : chioces) {
                    comboBox.addItem(chioce);
                }
                String q = mytype.chioces.get(0).mingzi;
//                        comboBox
                guize.setText(q);
                ((JComboBox) chioce).addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        try {
                            String q = ((Chioce) e.getItem()).mingzi;
//                        comboBox
                            guize.setText(q);
                        } catch (ArrayIndexOutOfBoundsException ee) {
                            System.out.println(mytype.title + "发生数组错误，疑似删除");
                        }
                    }
                });
                break;

            case shuang:
                chioce = new MyComboBox(guize, mytype.must);
                comboBox = (MyComboBox) chioce;
                chioces = mytype.chioces;
                if (mytype.title.equals("渠道小类")) {

                    CheckValue checkValue = new CheckValue();
                    checkValue.bolValue = false;
                    checkValue.value = new Chioce("", "");
                    comboBox.addItem(checkValue);
                    checkValue = new CheckValue();
                    checkValue.bolValue = false;
                    checkValue.value = new Chioce("02030100", "个人代码");
                    comboBox.addItem(checkValue);
                } else if (mytype.title.equals("车辆种类")) {
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
                comboBox.setRenderer(new CheckListCellRenderer());

//                if (!mytype.must) {
//                    comboBox.setSelectedIndex(0);
//                }
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
                    throw new ArrayIndexOutOfBoundsException(s);
                break;
            case dan:

                List<Chioce> chioces = mytype.chioces;
                JComboBox comboBox;
                comboBox = (JComboBox) chioce;
                s = (((Chioce) ((JComboBox) chioce).getModel().getSelectedItem()).daima);
                break;
            case shuang:

                MyComboBox jcomboBox = (MyComboBox) chioce;
                s = jcomboBox.getComboVc();
                break;
        }
        return s == null ? "" : s;
    }

}
