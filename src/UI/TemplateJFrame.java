package UI;

import Type.*;
import UI.MyComboBox.CheckValue;
import UI.MyComboBox.MyComboBox;
import javafx.scene.control.ComboBox;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import util.ExcleUtil;
import util.Template;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class TemplateJFrame extends JFrame implements ActionListener {

    JButton change, huisu, jili, jilu, kehu, moudle, save;
    JButton export;
    JTable table;
    public static String jclx = "0203";
    int click = 0;


    HashMap<String, TemplateJpanel> jpanelHashMap;
    public static List<String> titles;
    public static List<String> typesselected;
    Type t = Type.CHANGE;
    public static int limit = 4;

    public enum Type {
        CHANGE, JILI, JILU, KEHU, HUISU, ALL
    }

    public TemplateJFrame() {
        super();
        this.setTitle("制表小工具-变动模板");
        JPanel m = new JPanel();
        this.add(m);
        m.setLayout(new MyVFlowLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double w = screenSize.width * 0.4;
        this.setSize(new Dimension((int) w, (int) (screenSize.height * 0.8)));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(getOwner());
        this.setVisible(true);


        //这里是topjpanel的布局
        JPanel topJPanel = new JPanel();
        change = new JButton("变动");
        huisu = new JButton("回溯");
        jilu = new JButton("记录");
        jili = new JButton("激励");
        kehu = new JButton("客户");
        export = new JButton("导出");
        save = new JButton("保存");
        moudle = new JButton("历史");

        topJPanel.add(change);
        topJPanel.add(huisu);
        topJPanel.add(jili);
        topJPanel.add(jilu);
        topJPanel.add(kehu);
        topJPanel.add(save);
        topJPanel.add(export);
        topJPanel.add(moudle);

        //中间桌面布局
        JScrollPane tablepanle = new JScrollPane();
        table = new JTable(){
            public boolean isCellEditable(int row, int column) {

                    return false;

            }//表格不允许被编辑
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
                int r = table.getSelectedRow() + 1;

                if (click == 0) {
                    click = 1;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            if (click == 1) {
                                click=0;
                                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        try {
                                            int result = JOptionPane.showConfirmDialog(TemplateJFrame.this, "确定要修改第" + r + "行对话框吗", "提示框",
                                                    JOptionPane.YES_NO_OPTION);
                                            if (result == JOptionPane.YES_OPTION) {
                                                setOne((Vector) ((DefaultTableModel) table.getModel()).getDataVector().get(r - 1));
                                            } else if (result == JOptionPane.NO_OPTION) {
                                            } else if (result == JOptionPane.CANCEL_OPTION) {
                                            } else {
                                            }
                                        } catch (Exception e) {

                                        }
                                    }

                                });
                            }
                        }
                    }).start();
                } else {
                    click = 0;
                    int result = JOptionPane.showConfirmDialog(TemplateJFrame.this, "确定要删除第" + r + "行对话框吗", "提示框",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        ((DefaultTableModel) table.getModel()).removeRow(r - 1);
                    } else if (result == JOptionPane.NO_OPTION) {
                    } else if (result == JOptionPane.CANCEL_OPTION) {
                    } else {
                    }
                }


            }
        });
        tablepanle.setViewportView(table);


        //列表布局
        JScrollPane jScrollPane = new JScrollPane();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new MyVFlowLayout());
        jScrollPane.setViewportView(jPanel);


        double h = screenSize.height * 0.8 - topJPanel.getHeight();
        tablepanle.setPreferredSize(new Dimension((int) w, (int) (h * 0.2)));
        jScrollPane.setPreferredSize(new Dimension((int) w, (int) (h * 0.65)));
        m.add(topJPanel);
        m.add(tablepanle);
        m.add(jScrollPane);

        jpanelHashMap = new HashMap<>();
        HashMap<String, Mytype> types = TypeMannger.types;
        List<String> titles = getTypes();
        for (String s : titles) {
            Mytype mytype = types.get(s);
            System.out.println(s);
            TemplateJpanel templateJpanel = new TemplateJpanel(mytype);
            if (mytype.title.equals("决策单元大类")) {
                ((JComboBox) templateJpanel.chioce).addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        JComboBox comboBox = (JComboBox) jpanelHashMap.get("决策单元小类").chioce;
                        String s = ((Chioce) e.getItem()).daima;
                        comboBox.removeAllItems();
                        comboBox.addItem(new Chioce("", ""));
                        switch ((String) s) {
                            case "T":
                                comboBox.addItem(new Chioce("T01", "招标业务"));
                                comboBox.addItem(new Chioce("T02", "机关事业单位及央企"));
                                comboBox.addItem(new Chioce("T03", "企业"));
                                comboBox.addItem(new Chioce("T04", "运输公司"));
                                break;
                            case "C":
                                comboBox.addItem(new Chioce("C01", "车商总对总项目"));
                                comboBox.addItem(new Chioce("C02", "4S/3S店"));
                                comboBox.addItem(new Chioce("C03", "汽车卖场（带维修）"));
                                comboBox.addItem(new Chioce("C04", "汽车修理厂"));
                                comboBox.addItem(new Chioce("C05", "品牌展厅"));
                                comboBox.addItem(new Chioce("C06", "新车中心"));

                                break;
                            case "G":
                                comboBox.addItem(new Chioce("G01", "标准件"));
                                comboBox.addItem(new Chioce("G02", "私车团购"));
                                comboBox.addItem(new Chioce("G03", "电销"));
                                comboBox.addItem(new Chioce("G04", "平台网销"));
                                comboBox.addItem(new Chioce("G05", "职域营销"));
                                comboBox.addItem(new Chioce("G06", "直通网销"));
                                comboBox.addItem(new Chioce("G07", "个人代理"));
                                comboBox.addItem(new Chioce("G08", "蚂蚁金服"));
                                comboBox.addItem(new Chioce("G09", "新渠道"));
                                comboBox.addItem(new Chioce("G10", "微保"));

                                break;
                            case "D":

                                comboBox.addItem(new Chioce("D01", "代理总对总项目"));
                                comboBox.addItem(new Chioce("D02", "专业代理"));
                                comboBox.addItem(new Chioce("D03", "检车线/车管所"));
                                comboBox.addItem(new Chioce("D04", "汽车卖场（不带维修）"));
                                comboBox.addItem(new Chioce("D05", "汽车服务公司/美容店"));
                                comboBox.addItem(new Chioce("D06", "银行/邮政代理"));
                                comboBox.addItem(new Chioce("D07", "专业经纪"));
                                comboBox.addItem(new Chioce("D08", "代理网销"));
                                comboBox.addItem(new Chioce("D09", "挂靠业务"));
                                comboBox.addItem(new Chioce("D10", "担保公司"));
                                comboBox.addItem(new Chioce("D00", "其他"));
                                break;
                            case "G2":
                                comboBox.addItem(new Chioce("G201", "标准件2"));
                                break;


                        }
                    }
                });
            } else if (mytype.title.equals("渠道类型")) {
                ((JComboBox) templateJpanel.chioce).addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {

                        String s = ((Chioce) e.getItem()).daima;
                        jclx = s;

                    }
                });

//                (templateJpanel.guize).
//
//                    getDocument().
//
//                    addDocumentListener(new DocumentListener() {
//                        @Override
//                        public void insertUpdate (DocumentEvent e){
//
//                            MComboBox comboBox = (MComboBox) jpanelHashMap.get("渠道小类").chioce;
//                            comboBox.removeAllItems();
//                            comboBox.addItem(new Chioce("", ""));
//                            String s = templateJpanel.guize.getText();
//                            System.out.println("渠道大类代码为" + s);
//                            String[] daimas = s.split(",");
//                            List<Chioce> chioces = types.get("渠道小类").chioces;
//                            List<Chioce> chioces1 = types.get("渠道类型").chioces;
//                            List<String> text = new ArrayList<>();
//                            for (String daima : daimas) {
//                                for (Chioce chioce : chioces1) {
//                                    if (chioce.mingzi.equals(daima)) {
//                                        text.add(chioce.daima);
//                                        break;
//                                    }
//                                }
//                            }
//                            for (String daima : text) {
//                                for (Chioce chioce1 : chioces) {
//                                    if (chioce1.daima.startsWith(daima)) {
//                                        CheckValue checkValue = new CheckValue();
//                                        checkValue.value = new Chioce(chioce1.daima, chioce1.mingzi);
//                                        checkValue.bolValue = false;
//                                        comboBox.addItem(checkValue);
//                                    }
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void removeUpdate (DocumentEvent e){
//                            MyComboBox comboBox = (MyComboBox) jpanelHashMap.get("渠道小类").chioce;
//                            comboBox.removeAllItems();
//                            comboBox.addItem(new CheckValue(false, new Chioce("", "")));
//                            String s = templateJpanel.guize.getText();
//                            System.out.println("渠道大类代码为" + s);
//                            String[] daimas = s.split(",");
//                            List<Chioce> chioces = types.get("渠道小类").chioces;
//                            List<Chioce> chioces1 = types.get("渠道类型").chioces;
//                            List<String> text = new ArrayList<>();
//                            for (String daima : daimas) {
//                                for (Chioce chioce : chioces1) {
//                                    if (chioce.mingzi.equals(daima)) {
//                                        text.add(chioce.daima);
//                                        break;
//                                    }
//                                }
//                            }
//                            for (String daima : text) {
//                                for (Chioce chioce1 : chioces) {
//                                    if (chioce1.daima.startsWith(daima)) {
//                                        CheckValue checkValue = new CheckValue();
//                                        checkValue.value = new Chioce(chioce1.daima, chioce1.mingzi);
//                                        checkValue.bolValue = false;
//                                        comboBox.addItem(checkValue);
//                                    }
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void changedUpdate (DocumentEvent e){
//                        }
//                    });
            } else if (mytype.title.equals("车辆用途")) {
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
//            else if (mytype.title.equals("险种")) {
//                ((JComboBox) templateJpanel.chioce).addItemListener(new ItemListener() {
//                    @Override
//                    public void itemStateChanged(ItemEvent e) {
//                        String s = ((Chioce) e.getItem()).daima;
//                        switch ((String) s) {
//                            case "0801":
//                                limit = 4;
//                                break;
//                            case "0802":
//                                int q = ((JComboBox) jpanelHashMap.get("新旧车标志").chioce).getSelectedIndex();
//                                if (q == 0) {
//                                    limit = 100000;
//                                } else if (q == 1) {
//                                    limit = 22;
//                                } else if (q == 2) {
//                                    limit = 27;
//                                }
//                                break;
//                            default:
//                                limit = 100000;
//                        }
//                    }
//                });
//            }
// else if (mytype.title.equals("可用变动费用率")) {
//                (templateJpanel.chioce).addFocusListener(new FocusListener() {
//                    @Override
//                    public void focusGained(FocusEvent e) {
//
//                    }
//
//                    @Override
//                    public void focusLost(FocusEvent e) {
//                        try {
//                            int q = Integer.parseInt(((JTextField) templateJpanel.chioce).getText());
//                            int a = Integer.parseInt(((JTextField) jpanelHashMap.get("代理手续费率").chioce).getText());
//                            int b = Integer.parseInt(((JTextField) jpanelHashMap.get("月度提奖率").chioce).getText());
//                            int c = Integer.parseInt(((JTextField) jpanelHashMap.get("组织利益(分)").chioce).getText());
//                            int d = a + b + c;
//                            if (q < d) {
//                                JOptionPane.showMessageDialog(null, "可用变动费用率>=代理手续费率+月度提奖率+组织利益(分)，应为：" + d, "输入错误提示", JOptionPane.ERROR_MESSAGE);
//                            }
//
//                        } catch (Exception ee) {
//                            JOptionPane.showMessageDialog(null, "可用变动费率必须为数字哦", "输入错误", JOptionPane.ERROR_MESSAGE);
//                        }
//
//
//                    }
//                });
//            }
//            else if (mytype.title.equals("销售及服务成本")) {
//                (templateJpanel.chioce).addFocusListener(new FocusListener() {
//                    @Override
//                    public void focusGained(FocusEvent e) {
//
//                    }
//
//                    @Override
//                    public void focusLost(FocusEvent e) {
//                        try {
//                            int q = Integer.parseInt(((JTextField) templateJpanel.chioce).getText());
//                            int a = Integer.parseInt(((JTextField) jpanelHashMap.get("可用变动费用率").chioce).getText());
//                            int b = Integer.parseInt(((JTextField) jpanelHashMap.get("激励费用1").chioce).getText());
//                            int c = Integer.parseInt(((JTextField) jpanelHashMap.get("激励费用2").chioce).getText());
//                            int i = Integer.parseInt(((JTextField) jpanelHashMap.get("记录费用(率)").chioce).getText());
//                            int f = Integer.parseInt(((JTextField) jpanelHashMap.get("客户评分").chioce).getText());
//                            int g = Integer.parseInt(((JTextField) jpanelHashMap.get("预估增值服务费成本率").chioce).getText());
//
//                            int h = Integer.parseInt(((JTextField) jpanelHashMap.get("预估间接理赔成本率").chioce).getText());
//                            int d = a + b + c + f + g + h + i;
//                            if (q != d) {
//                                JOptionPane.showMessageDialog(null, "销售及服务成本=可用变动费用+激励费用1+激励费用2+记录费用(率) +客户评分+预估增值服务费成本率+预估间接理赔成本率\n，应为：" + d, "输入错误提示", JOptionPane.ERROR_MESSAGE);
//                            }
//
//                        } catch (Exception ee) {
//                            JOptionPane.showMessageDialog(null, "销售及服务成本必须为数字哦", "输入错误", JOptionPane.ERROR_MESSAGE);
//                        }
//
//
//                    }
//                });
//            }
            jpanelHashMap.put(mytype.title, templateJpanel);
            jPanel.add(templateJpanel);
        }
        //jScrollPane.setPreferredSize(new Dimension(200,200));
        JScrollBar Bar = jScrollPane.getVerticalScrollBar();
        Bar.setUnitIncrement(40);

        initJtable(Type.CHANGE);
        change.addActionListener(this::actionPerformed);
        huisu.addActionListener(this::actionPerformed);
        jili.addActionListener(this::actionPerformed);
        jilu.addActionListener(this::actionPerformed);
        kehu.addActionListener(this::actionPerformed);
        export.addActionListener(this::actionPerformed);
        moudle.addActionListener(this::actionPerformed);
        save.addActionListener(this::actionPerformed);

    }


    public void initJtable(Type type) {
        typesselected = Template.getTypes(type);
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        for (String s : typesselected) {
            defaultTableModel.addColumn(s);
        }

        table.setModel(defaultTableModel);
    }

    public static List<String> getTypes() {
        if (titles == null)
            titles = Template.getTypes(Type.ALL);
        return titles;
    }


    public void refresh() {
        //字体会变大  com.sun.java.swing.plaf.windows.WindowsLookAndFeel
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
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        MyComboBox.init = true;


    }

    public static void main(String args[]) {

//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }


        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    TemplateJFrame templateJFrame = new TemplateJFrame();
                    templateJFrame.refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public void saveAll(Vector<Vector> vector) {

        File file = new File(t.toString() + ".xls");
        if (!file.exists()) {
            try {
                ExcleUtil.createExcle(t);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "创建文件错误，错误属性为：" + e.getMessage(), "保存错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            ExcleUtil.writetoExcle(t, vector);
            JOptionPane.showConfirmDialog(null, "保存成功", "保存成功", JOptionPane.CLOSED_OPTION);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "写入文件错误，错误属性为：" + e.getMessage(), "保存错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }


    public void setValue(List<Vector> values) {
        for (Vector vector : values) {
            vector.remove(0);
            vector.remove(0);
            ((DefaultTableModel) table.getModel()).addRow(vector);
        }
        if (values.size() > 0)
            for (int i = 0; i < values.get(0).size(); i++) {
                jpanelHashMap.get(typesselected.get(i)).setValue((String) values.get(0).get(i));
            }

        this.setVisible(true);
    }

    public void setOne(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            jpanelHashMap.get(typesselected.get(i)).setValue((String) v.get(i));
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String string = jButton.getText();
        switch (string) {
            case "变动":
                if (t == Type.CHANGE) return;
                t = Type.CHANGE;
                this.setTitle("制表小工具-变动模板");
                initJtable(t);
                return;
            case "回溯":
                if (t == Type.HUISU) return;
                t = Type.HUISU;
                this.setTitle("制表小工具-回溯模板");
                initJtable(t);
                return;
            case "激励":
                if (t == Type.JILI) return;
                t = Type.JILI;
                this.setTitle("制表小工具-激励模板");
                initJtable(t);
                return;
            case "记录":
                if (t == Type.JILU) return;
                t = Type.JILU;
                this.setTitle("制表小工具-记录模板");
                initJtable(t);
                return;
            case "客户":
                if (t == Type.KEHU) return;
                t = Type.KEHU;
                initJtable(t);
                this.setTitle("制表小工具-客户模板");
                return;
            case "导出":
                export();
                break;
            case "保存":
                save();
                return;
            case "历史":
                File file = new File(t + ".xls");
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(null, "还没有保存任何模板呢", "显示错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
//                try {
//                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                        if ("Nimbus".equals(info.getName())) {
//                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                            break;
//                        }
//                    }
//                } catch (Exception ee) {
//                    System.out.println(ee);
//                }

                //WebLookAndFeel.install();

                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            MoudleJFrame templateJFrame = new MoudleJFrame(TemplateJFrame.this, t);
                            templateJFrame.refresh();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
//                MoudleJFrame templateJFrame = new MoudleJFrame(TemplateJFrame.this, t);
//                templateJFrame.refresh();
                this.setVisible(false);
                return;

        }

    }


    public void save() {

        String qq = ((String) ((MComboBox) jpanelHashMap.get("险种").chioce).getSelectedItem());
        switch ((String) qq) {
            case "0801":
                limit = 4;
                break;
            case "0802":
                int q = ((JComboBox) jpanelHashMap.get("新旧车标志").chioce).getSelectedIndex();
                if (q == 0) {
                    limit = 100000;
                } else if (q == 1) {
                    limit = 22;
                } else if (q == 2) {
                    limit = 27;
                }
                break;
            default:
                limit = 100000;
        }


        Vector<String> vector = new Vector<>();
        for (
                int i = 0; i < typesselected.size(); i++) {
            String title = typesselected.get(i);
            String s = jpanelHashMap.get(title).getContent();
            if (s == null) {
                JOptionPane.showMessageDialog(null, "属性错误，错误属性为：" + typesselected.get(i), "保存错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            vector.add(jpanelHashMap.get(title).getContent());

        }


        int q = Integer.parseInt(((JTextField) jpanelHashMap.get("可用变动费用率").chioce).getText());
        int a = Integer.parseInt(((JTextField) jpanelHashMap.get("代理手续费率").chioce).getText());
        int b = Integer.parseInt(((JTextField) jpanelHashMap.get("月度提奖率").chioce).getText());
        int c = Integer.parseInt(((JTextField) jpanelHashMap.get("组织利益(分)").chioce).getText());
        int d = a + b + c;
        if (q < d) {
            JOptionPane.showMessageDialog(null, "可用变动费用率>=代理手续费率+月度提奖率+组织利益(分)，应为：" + d, "输入错误提示", JOptionPane.ERROR_MESSAGE);
        }
        int e = Integer.parseInt(((JTextField) jpanelHashMap.get("销售及服务成本").chioce).getText());
        int f = Integer.parseInt(((JTextField) jpanelHashMap.get("激励费率1").chioce).getText());
        int g = Integer.parseInt(((JTextField) jpanelHashMap.get("客户评分").chioce).getText());
        int h = Integer.parseInt(((JTextField) jpanelHashMap.get("记录费用(率)").chioce).getText());

        int i = Integer.parseInt(((JTextField) jpanelHashMap.get("预估引流成本率").chioce).getText());
        int j = Integer.parseInt(((JTextField) jpanelHashMap.get("预估业务维护成本率").chioce).getText());
        int k = Integer.parseInt(((JTextField) jpanelHashMap.get("预估增值服务费成本率").chioce).getText());
        int l = Integer.parseInt(((JTextField) jpanelHashMap.get("预估间接理赔成本率").chioce).getText());
        int y = Integer.parseInt(((JTextField) jpanelHashMap.get("激励费率2").chioce).getText());
        int z = f + g + h + i + j + k + l + q + y;
        if (e != z) {
            JOptionPane.showMessageDialog(null, "销售及服务成本=可用变动费用率+激励费率1+激励费率2+客户评分+记录费用(率)+预估引流成本率+预估业务维护成本率+预估增值服务费成本率+预估间接理赔成本率，应为：" + z, "输入错误提示", JOptionPane.ERROR_MESSAGE);

        }

        int sele= 5;
        switch (t) {
            case JILI:

                sele = 4;
                break;
            case CHANGE:

                sele = 10;

                break;
            case HUISU:

                sele = 5;
                break;
            case KEHU:

                sele =1;
                break;
            case JILU:

                sele = 2;
                break;


        }


        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        Vector<Vector> dddd=defaultTableModel.getDataVector();
        int ql=0;
        for( ; ql<dddd.size(); ){
            boolean cc=true;
            for(int pp =0 ;pp<dddd.get(ql).size();pp++){
                if(pp>=5&&pp<=4+sele){

                }else
                if(dddd.get(ql).get(pp).equals(vector.get(pp)) ){
                }else{
                    cc=false;
                    break;
                }
            }
            if(cc){
                int result = JOptionPane.showConfirmDialog(TemplateJFrame.this, "此次数据与第" + (ql+1) + "行数据重复，确认覆盖吗", "提示框",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    defaultTableModel.removeRow(ql);
                    defaultTableModel.addRow(vector);
                } else if (result == JOptionPane.NO_OPTION) {
                } else if (result == JOptionPane.CANCEL_OPTION) {
                } else {
                }
                return;
            }
            ql++;
        }
        if(ql==dddd.size()){
            defaultTableModel.addRow(vector);
        }


    }

    public void export() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) (table.getModel());
        Vector<Vector> datas = defaultTableModel.getDataVector();
        if (datas.size() == 0) {
            JOptionPane.showMessageDialog(null, "错误：表格为空", "导出错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String filename = JOptionPane.showInputDialog(this, "请输入文件名(无需加.xls", this.getTitle().replace("制表小工具-", ""));
        if (filename == null || filename.length() == 0) {
            JOptionPane.showMessageDialog(null, "错误：你没有输入文件名", "导出错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("模板");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制

        HSSFRow row = sheet.createRow(0);
        HashMap<String, Mytype> types = TypeMannger.types;
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.RED.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFCellStyle style1 = workbook.createCellStyle();
        style1.setFillForegroundColor(HSSFColor.YELLOW.index);
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < typesselected.size(); i++) {
            String title = typesselected.get(i);
            //第四步，创建单元格，设置表头
            HSSFCell cell = row.createCell(i);
            if (types.get(title).must) {
                cell.setCellStyle(style);
            } else {
                cell.setCellStyle(style1);
            }
            cell.setCellValue(types.get(title).getShorthand());
        }
        for (int i = 0; i < datas.size(); i++) {
            Vector v = datas.get(i);
            row = sheet.createRow(1 + i);
            for (int ii = 0; ii < v.size(); ii++) {
                HSSFCell cell = row.createCell(ii);
                cell.setCellValue((String) v.get(ii));
            }

        }


        //将文件保存到指定的位置
        JFileChooser jfc = new JFileChooser();
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("xls");
//       // 设置文件类型
//        jfc.setFileFilter(filter);
        FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
        System.out.println(fsv.getHomeDirectory());                //得到桌面路径
        jfc.setCurrentDirectory(fsv.getHomeDirectory());
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//设置保存路径
        jfc.showDialog(new JLabel(), "选择");

        File file = jfc.getSelectedFile();
        String path;
        if (file != null) {
            path = file.getAbsolutePath();

            File file1 = new File(path + "\\" + filename + ".xls");
            if (file1.exists()) {
                JOptionPane.showMessageDialog(null, "错误：文件名重复", "保存错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println(file1.getAbsoluteFile());
            try {
                file1.createNewFile();
                FileOutputStream fos = new FileOutputStream(file1.getAbsoluteFile());
                workbook.write(fos);
                System.out.println("写入成功");
                fos.close();
                JOptionPane.showConfirmDialog(null, "导出路径为" + file1.getAbsolutePath(), "导出成功", JOptionPane.CLOSED_OPTION);
            } catch (IOException ea) {
                JOptionPane.showMessageDialog(null, "错误：" + ea.getMessage(), "保存错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "您没有选择路径或者文件重复", "保存错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        saveAll(datas);
    }
}
