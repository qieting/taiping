package UI;

import UI.MyComboBox.CheckValue;
import UI.MyComboBox.MyComboBox;
import com.sun.org.apache.xpath.internal.operations.Bool;
import util.ExcleUtil;
import Type.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Vector;

public class MoudleJFrame extends JFrame implements ActionListener {

    JButton quert, select;
    JTable table;
    JTextField jTextField;
    HashMap<String, TemplateJpanel> jpanelHashMap;

    DefaultTableModel model;
    TemplateJFrame templateJFrame;
    List<Modify> modifyList;
    UI.TemplateJFrame.Type type;

    public MoudleJFrame(TemplateJFrame t, TemplateJFrame.Type type) {
        super();
        this.type = type;
        this.setLayout(new MyVFlowLayout());
        this.setTitle("模板");
        templateJFrame = t;
        JPanel topJPanel = new JPanel();
        quert = new JButton("查询");
        select = new JButton("选择");
        jTextField = new JTextField("查询模式下什么都不选代表所有都可以，忽略必选标识");
        jTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
        jTextField.setEnabled(false);
        jTextField.setDisabledTextColor(Color.red);
        topJPanel.add(quert);
        topJPanel.add(select);
        quert.addActionListener(this);
        select.addActionListener(this);
        JButton delete = new JButton("删除");
        delete.addActionListener(this::actionPerformed);
        topJPanel.add(delete);
        JButton change = new JButton("修改");
        change.addActionListener(this);
        topJPanel.add(change);


        topJPanel.add(jTextField);

        this.add(topJPanel);


        JScrollPane jScrollPane = new JScrollPane();
        this.add(jScrollPane);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new MyVFlowLayout());
        jScrollPane.setViewportView(jPanel);
        jpanelHashMap = new HashMap<>();
        HashMap<String, Mytype> types = TypeMannger.types;
        List<String> titles = getTypes();
        for (String s : titles) {
            Mytype mytype = types.get(s);
            System.out.println(s);
            TemplateJpanel templateJpanel = new MoudleJpanel(mytype);
            if (mytype.title.equals("车辆用途")) {
                (templateJpanel.guize).getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {

                        MyComboBox comboBox = (MyComboBox) jpanelHashMap.get("车辆种类").chioce;
                        comboBox.removeAllItems();
                        comboBox.addItem(new CheckValue(false, new Chioce("", "")));
                        String s = templateJpanel.guize.getText();
                        System.out.println("车辆种类代码为" + s);
                        String[] daimas = s.split(",");

                        List<Chioce> chioces = types.get("车辆用途").chioces;

                        List<String> text = new ArrayList<>();
                        for (String daima : daimas) {
                            for (Chioce chioce : chioces) {
                                if (chioce.mingzi.equals(daima)) {
                                    text.add(chioce.daima);
                                    break;
                                }
                            }
                        }
                        List<CheckValue> chioceList = new ArrayList<>();
                        for (String m : text) {
                            CheckValue checkValue;
                            switch (m) {
                                case "01":
                                case "02":
                                case "03":
                                    checkValue = new CheckValue(false, new Chioce("11", "6座以下客车"));
                                    if (!chioceList.contains(checkValue)) {
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("12", "6-10座客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("13", "10-20座客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("14", "20-36座客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("15", "36座以上客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("21", "2吨以下货车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("22", "2-5吨货车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("23", "5-10吨货车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("24", "10吨以上货车"));
                                        chioceList.add(checkValue);
                                    }
                                    break;
                                case "04":
                                case "10":
                                    checkValue = new CheckValue(false, new Chioce("11", "6座以下客车"));
                                    if (!chioceList.contains(checkValue)) {
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("12", "6-10座客车"));
                                        chioceList.add(checkValue);
                                    }
                                    break;
                                case "05":
                                    checkValue = new CheckValue(false, new Chioce("11", "6座以下客车"));
                                    if (!chioceList.contains(checkValue)) {
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("12", "6-10座客车"));
                                        chioceList.add(checkValue);
                                    }

                                    checkValue = new CheckValue(false, new Chioce("13", "10-20座客车"));
                                    if (!chioceList.contains(checkValue)) {
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("14", "20-36座客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("15", "36座以上客车"));
                                        chioceList.add(checkValue);
                                    }
                                    break;
                                case "06":
                                case "07":
                                    checkValue = new CheckValue(false, new Chioce("13", "10-20座客车"));
                                    if (!chioceList.contains(checkValue)) {
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("14", "20-36座客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("15", "36座以上客车"));
                                        chioceList.add(checkValue);
                                    }
                                    break;
                                case "08":
                                    checkValue = new CheckValue(false, new Chioce("21", "2吨以下货车"));
                                    if (!chioceList.contains(checkValue)) {

                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("22", "2-5吨货车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("23", "5-10吨货车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("24", "10吨以上货车"));

                                    }
                                    break;
                                case "09":
                                    checkValue = new CheckValue(false, new Chioce("25", "2吨以下挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("26", "2-5吨挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("27", "5-10吨挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("28", "10吨以上挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("30", "特种车一"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("31", "特种车二"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("40", "特种车三"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("41", "特种车四"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("42", "特种车一挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("50", "特种车二挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("51", "冷藏保温挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("60", "特种车三挂车"));
                                    chioceList.add(checkValue);
                                    break;

                            }

                        }
                        for (CheckValue checkValue : chioceList) {
                            comboBox.addItem(checkValue);
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        MyComboBox comboBox = (MyComboBox) jpanelHashMap.get("车辆种类").chioce;
                        comboBox.removeAllItems();
                        comboBox.addItem(new CheckValue(false, new Chioce("", "")));
                        String s = templateJpanel.guize.getText();
                        System.out.println("车辆种类代码为" + s);
                        String[] daimas = s.split(",");

                        List<Chioce> chioces = types.get("车辆用途").chioces;

                        List<String> text = new ArrayList<>();
                        for (String daima : daimas) {
                            for (Chioce chioce : chioces) {
                                if (chioce.mingzi.equals(daima)) {
                                    text.add(chioce.daima);
                                    break;
                                }
                            }
                        }
                        List<CheckValue> chioceList = new ArrayList<>();
                        for (String m : text) {
                            CheckValue checkValue;
                            switch (m) {
                                case "01":
                                case "02":
                                case "03":
                                    checkValue = new CheckValue(false, new Chioce("11", "6座以下客车"));
                                    if (!chioceList.contains(checkValue)) {
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("12", "6-10座客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("13", "10-20座客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("14", "20-36座客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("15", "36座以上客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("21", "2吨以下货车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("22", "2-5吨货车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("23", "5-10吨货车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("24", "10吨以上货车"));
                                        chioceList.add(checkValue);
                                    }
                                    break;
                                case "04":
                                case "10":
                                    checkValue = new CheckValue(false, new Chioce("11", "6座以下客车"));
                                    if (!chioceList.contains(checkValue)) {
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("12", "6-10座客车"));
                                        chioceList.add(checkValue);
                                    }
                                    break;
                                case "05":
                                    checkValue = new CheckValue(false, new Chioce("11", "6座以下客车"));
                                    if (!chioceList.contains(checkValue)) {
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("12", "6-10座客车"));
                                        chioceList.add(checkValue);
                                    }

                                    checkValue = new CheckValue(false, new Chioce("13", "10-20座客车"));
                                    if (!chioceList.contains(checkValue)) {
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("14", "20-36座客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("15", "36座以上客车"));
                                        chioceList.add(checkValue);
                                    }
                                    break;
                                case "06":
                                case "07":
                                    checkValue = new CheckValue(false, new Chioce("13", "10-20座客车"));
                                    if (!chioceList.contains(checkValue)) {
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("14", "20-36座客车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("15", "36座以上客车"));
                                        chioceList.add(checkValue);
                                    }
                                    break;
                                case "08":
                                    checkValue = new CheckValue(false, new Chioce("21", "2吨以下货车"));
                                    if (!chioceList.contains(checkValue)) {

                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("22", "2-5吨货车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("23", "5-10吨货车"));
                                        chioceList.add(checkValue);
                                        checkValue = new CheckValue(false, new Chioce("24", "10吨以上货车"));

                                    }
                                    break;
                                case "09":
                                    checkValue = new CheckValue(false, new Chioce("25", "2吨以下挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("26", "2-5吨挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("27", "5-10吨挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("28", "10吨以上挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("30", "特种车一"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("31", "特种车二"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("40", "特种车三"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("41", "特种车四"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("42", "特种车一挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("50", "特种车二挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("51", "冷藏保温挂车"));
                                    chioceList.add(checkValue);
                                    checkValue = new CheckValue(false, new Chioce("60", "特种车三挂车"));
                                    chioceList.add(checkValue);
                                    break;

                            }

                        }
                        for (CheckValue checkValue : chioceList) {
                            comboBox.addItem(checkValue);
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });
            }
            jpanelHashMap.put(mytype.title, templateJpanel);

            jPanel.add(templateJpanel);
        }

        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        jScrollPane.setPreferredSize(new Dimension(700, (int) (height * 0.35)));

        JScrollBar Bar = null;

        Bar = jScrollPane.getVerticalScrollBar();

        Bar.setUnitIncrement(40);


        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setPreferredSize(new Dimension(700, 70));
        jScrollPane1.setSize(700, 70);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel jj = new JPanel();

        jj.setLayout(new FlowLayout());
        jj.setSize(2000, 60);
        modifyList = new ArrayList<>();

        switch (type) {
            case JILU:
                modifyList.add(new Modify("记录费用(率)"));
                modifyList.add(new Modify("记录费用(额)"));
                break;
            case KEHU:
                modifyList.add(new Modify("客户评分"));
                break;
            case HUISU:
                modifyList.add(new Modify("预估引流成本率"));
                modifyList.add(new Modify("预估业务维护成本率"));
                modifyList.add(new Modify("预估增值服务费成本率"));
                modifyList.add(new Modify("预估间接理赔成本率"));
                modifyList.add(new Modify("预估增值服务费成本率2"));
                break;
            case CHANGE:
                modifyList.add(new Modify("销售及服务成本"));
                modifyList.add(new Modify("可用变动费用率"));
                modifyList.add(new Modify("可用变动费用额"));
                modifyList.add(new Modify("代理手续费率"));
                modifyList.add(new Modify("月度提奖率"));
                modifyList.add(new Modify("可联动费用率"));
                modifyList.add(new Modify("组织利益（分）"));
                modifyList.add(new Modify("组织利益（总）"));
                modifyList.add(new Modify("保单服务费"));
                modifyList.add(new Modify("固定绩效"));
                break;
            case JILI:
                modifyList.add(new Modify("激励费率1"));
                modifyList.add(new Modify("激励费额1"));
                modifyList.add(new Modify("激励费率2"));
                modifyList.add(new Modify("激励费额2"));
                break;
            case ALL:
                break;
        }
        for (Modify modify : modifyList) {
            jj.add(modify);
        }
        jScrollPane1.setViewportView(jj);

        this.add(jScrollPane1);

        JScrollPane jScrolltable = new JScrollPane();
        this.add(jScrolltable);
        jScrolltable.setPreferredSize(new Dimension(200, (int) (height * 0.30)));
        JPanel tablepanle = new JPanel();
        tablepanle.setLayout(new MyVFlowLayout());
        jScrolltable.setViewportView(tablepanle);
        table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                if (column != 0)
                    return false;
                return true;
            }//表格不允许被编辑

            @Override
            public Class getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return  Boolean.class;
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }

        };


        model = new DefaultTableModel();

        table.setModel(model);

        model.addColumn("选择");
        model.addColumn("序号");
        //设置第0列显示JCheckBox
        TableColumnModel tcm = table.getColumnModel();
        // tcm.getColumn(0).setCellRenderer();
        tcm.getColumn(0).
                setCellEditor(new DefaultCellEditor(new JCheckBox()));

//添加标格监听事件
//        table.addMouseListener(new
//
//                                       MouseAdapter() {
//                                           public void mouseClicked(MouseEvent e) {
//                                               if (e.getClickCount() == 1) {
//                                                   int columnIndex = table.columnAtPoint(e.getPoint()); //获取点击的列
//                                                   int rowIndex = table.rowAtPoint(e.getPoint()); //获取点击的行
//                                                   System.out.println(rowIndex);
//                                                   if (columnIndex == 0&&rowIndex==-1) {//第0列时，执行代码
////                        if(table.getValueAt(rowIndex,columnIndex) == null){ //如果未初始化，则设置为false
////                            table.setValueAt(false, rowIndex, columnIndex);
////                        }
////
////                                                       if (((Boolean) table.getValueAt(rowIndex, columnIndex)).booleanValue()) { //原来选中
////                                                           table.setValueAt(false, rowIndex, 0); //点击后，取消选中
////                                                       } else {//原来未选中
////                                                           table.setValueAt(true, rowIndex, 0);
////                                                       }
//                                                       System.out.println("asfasfasf");
//                                                   }
//
//                                               }
//                                           }
//                                       });

        for (String s : templateJFrame.titles) {
            model.addColumn(s);
        }

        table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
           tablepanle.add(table.getTableHeader());
        tablepanle.add(table);


        this.

                setBackground(Color.WHITE);

        this.

                setBounds(200, 100, 700, (int) (height * 0.8));

        setLocationRelativeTo(getOwner());
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.

                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        t.setVisible(true);
                        MoudleJFrame.this.dispose();
                        super.windowClosing(e);
                    }
                });

        this.pack();

        this.setVisible(true);
        this.

                refresh();

    }

    public void initMoudle() {
        this.setVisible(false);
    }

    public List<String> getTypes() {
        List<String> titles = new ArrayList<>();
        titles.add("生效日期");
        titles.add("失效日期");
        titles.add("OA号");
        titles.add("决策单元代码");
        titles.add("归属机构");
        titles.add("决策单元大类");
        titles.add("渠道类型");
        titles.add("险种");
        titles.add("新旧车标志");
        titles.add("车辆用途");
        titles.add("车辆种类");
        return titles;
    }

    public void refresh() {

//        //字体会变大  com.sun.java.swing.plaf.windows.WindowsLookAndFeel
//        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
//        try {
//            javax.swing.UIManager.setLookAndFeel(lookAndFeel);
//            List<String> types = getTypes();
//            for (String s : types) {
//                TemplateJpanel templateJpanel = jpanelHashMap.get(s);
//                SwingUtilities.updateComponentTreeUI(templateJpanel.title);
//                SwingUtilities.updateComponentTreeUI(templateJpanel.guize);
//                SwingUtilities.updateComponentTreeUI(templateJpanel.must);
//                templateJpanel.must.setBackground(Color.WHITE);
//                // templateJpanel.must.
//
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }

    }

    public static void main(String args[]) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MoudleJFrame templateJFrame = new MoudleJFrame(null, TemplateJFrame.Type.CHANGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String string = jButton.getText();

        switch (string) {
            case "查询":
                List<String> titles = getTypes();
                HashMap<Integer, String> map = new HashMap<>();
                int sele[] =  {0, 1, 2, 34, 31, 32, 35, 39, 60, 58, 59};
//                switch (type) {
//                    case JILI:
//                        int q[] = {0, 1, 2, 14, 11, 12, 15, 19, 40, 38, 39};
//                        sele = q;
//                        break;
//                    case CHANGE:
//                        int sesle[] = {0, 1, 2, 22, 19, 20, 23, 26, 48, 46, 47};
//                        sele = sesle;
//
//                        break;
//                    case HUISU:
//                        int saele[] = {0, 1, 2, 15, 12, 13, 16, 19, 41, 39, 40};
//                        sele = saele;
//                        break;
//                    case KEHU:
//                        int selae[] = {0, 1, 2, 11, 8, 9, 12, 15, 37, 35, 36};
//                        sele = selae;
//                        break;
//                    case JILU:
//                        int qq[] = {0, 1, 2, 12, 9, 10, 13, 17, 39, 37, 38};
//                        sele = qq;
//                        break;
//
//
//                }
                for (int i = 0; i < titles.size(); i++) {

                    String s = jpanelHashMap.get(titles.get(i)).getContent();
                    if (s == null) {
                        JOptionPane.showMessageDialog(null, "属性出错：" + jpanelHashMap.get(titles.get(i)).mytype.title, "属性错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        if (!s.equals(""))
                            map.put(sele[i] + 1, "" + s);
                    }

                }


                try {
                    List<Vector> vectors = ExcleUtil.q(type, map);

                    JOptionPane.showConfirmDialog(null, "查询到" + vectors.size(), "", JOptionPane.CLOSED_OPTION);

                    model.setRowCount(0);

                    ;
                    for (int i = 0; i < vectors.size(); i++) {
                        Vector vector = vectors.get(i);
                        vector.insertElementAt(false, 0);
                        model.addRow(vector);
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            case "选择":
                selected();
                break;
            case "删除":
                delete();
                break;
            case "修改":
                change();
                break;
        }
//第一步

    }

    public void change() {

        int[] selectedRows = getSelectedRows();
        int lenth = selectedRows.length;
        if (lenth == 0) {
            JOptionPane.showConfirmDialog(MoudleJFrame.this, "需要先选择要修改的行哦", "提示框",
                    JOptionPane.DEFAULT_OPTION);
            return;
        }
        int result = JOptionPane.showConfirmDialog(MoudleJFrame.this, "确定要修改选中的" + selectedRows.length + "行吗", "提示框",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {

        } else {
            return;
        }


        int select[] = new int[lenth];
        for (int i = 0; i < lenth; i++) {

            int qq = Integer.parseInt(((String) table.getValueAt(selectedRows[i], 1)).split("#")[1]);
            select[i] = qq;

        }
        HashMap<Integer, String> map = new HashMap<>();
        int ioiii = 6;
        for (Modify modify : modifyList) {
            if (modify.getValue().length() > 0) {
                map.put(ioiii, modify.getValue().trim());
            }
            ioiii++;
        }
        try {
            ExcleUtil.change(type, select, map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showConfirmDialog(MoudleJFrame.this, "修改成功，为保证数据同步请重新查询", "提示框",
                JOptionPane.CLOSED_OPTION);
        model.setRowCount(0);
    }

    public void showError(String s) {
        JOptionPane.showMessageDialog(null, s, "修改成功", JOptionPane.ERROR_MESSAGE);
    }

    public void selected() {
        int[] selectedRows = getSelectedRows();
        int lenth = selectedRows.length;
        if(lenth==0){
            JOptionPane.showConfirmDialog(MoudleJFrame.this, "没有选择任何选项", "提示框",
                    JOptionPane.DEFAULT_OPTION);
            return;
        }
        int result = JOptionPane.showConfirmDialog(MoudleJFrame.this, "确定要返回选中的" + selectedRows.length + "行吗", "提示框",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {

        } else {
            return;
        }


        List<Vector> vectorList = new ArrayList<>();
        Vector vector = model.getDataVector();
        for (int i = 0; i < lenth; i++) {
            vectorList.add((Vector) vector.get(i));
        }

        templateJFrame.setValue(vectorList);
        templateJFrame.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }

    public int []  getSelectedRows(){
        List<Integer> integers =new ArrayList<>();
        for(int a =0;a<table.getRowCount();a++){
            if((boolean)table.getValueAt(a,0)){
                integers.add(a);
            }
        }
        int selecteda[] =new int[integers.size()];
        for(int q=0 ;q<selecteda.length;q++){
            selecteda[q]=integers.get(q);
        }
        return selecteda;
    }
    public void delete() {
        int[] selectedRows = getSelectedRows();
        int lenth = selectedRows.length;
        if (lenth == 0) {
            JOptionPane.showConfirmDialog(MoudleJFrame.this, "没有选择任何选项", "提示框",
                    JOptionPane.DEFAULT_OPTION);
            return;
        }
        int result = JOptionPane.showConfirmDialog(MoudleJFrame.this, "确定要删除选中的" + selectedRows.length + "行吗", "提示框",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {

        } else {
            return;
        }


        int select[] = new int[lenth];
        for (int i = lenth - 1; i >= 0; i--) {

            int qq = Integer.parseInt(((String) table.getValueAt(selectedRows[i], 1)).split("#")[1]);
            System.out.println(selectedRows[i] + "选中获得" + qq);
            select[i] = qq;
            model.removeRow(selectedRows[i]);
        }

        try {
            ExcleUtil.delete(type, select);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showConfirmDialog(MoudleJFrame.this, "删除成功", "提示框",
                JOptionPane.CLOSED_OPTION);
    }
}
