package UI;

import UI.MyComboBox.CheckListCellRenderer;
import UI.MyComboBox.CheckValue;
import UI.MyComboBox.MyComboBox;
import Type.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class MoudleJpanel extends TemplateJpanel {

    public MoudleJpanel(Mytype mytype){
        super(mytype);
    }

    public JComponent getChioce(Mytype mytype) {
        JComboBox comboBox;
        switch (mytype.type) {
            case Date:
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
            case string:
            case intt:
                chioce = new JTextField();
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
                if(mytype.must)
                    comboBox.addItem(new Chioce("",""));
                for (Chioce chioce : chioces) {
                    comboBox.addItem(chioce);
                }
                guize.setText("");
                ((JComboBox) chioce).addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        try {
                            String q = ((Chioce)e.getItem()).mingzi;
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
                if(mytype.must)
                {
                    CheckValue checkValue = new CheckValue();
                    checkValue.bolValue = false;
                    checkValue.value = new Chioce("", "");
                    comboBox.addItem(checkValue);
                }
                if (mytype.title.equals("车辆种类")) {
                    CheckValue checkValue = new CheckValue();
                    checkValue.bolValue = false;
                    checkValue.value = new Chioce("", "");
                    comboBox.addItem(checkValue);
                } else
                    for (Chioce chioce : chioces) {
                        CheckValue checkValue = new CheckValue();
                        checkValue.value = chioce;
                        checkValue.bolValue = false;
                        comboBox.addItem(checkValue);
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
                Chioce q =((Chioce)((JComboBox) chioce).getModel().getSelectedItem());
                s = q.daima;
                break;
            case shuang:

                MyComboBox jcomboBox = (MyComboBox) chioce;
                s = jcomboBox.getComboVc();
                break;
        }
        return s == null ? "" : s;
    }

}
