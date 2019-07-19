package UI;

import Type.*;
import UI.MyComboBox.CheckValue;
import UI.MyComboBox.MyComboBox;
import Template.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import util.ExcleUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TemplateJFrame extends JFrame implements ActionListener {

    JButton change, huisu, jili, jilu, kehu, moudle,save;
    JButton export;

    HashMap<String, TemplateJpanel> jpanelHashMap;
    static List<String> titles;
    public static int limit = 4;

    public TemplateJFrame() throws Exception {
        super();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setTitle("制表小工具");
        this.setLayout(new MyVFlowLayout());
        JPanel topJPanel = new JPanel();
        change = new JButton("变动");
        huisu = new JButton("回溯");
        jilu = new JButton("记录");
        jili = new JButton("激励");
        kehu = new JButton("客户");
        export = new JButton("导出");
        save=new JButton("保存");

        moudle = new JButton("模板");
        initMoudle();
        change.addActionListener(this);
        huisu.addActionListener(this);
        jili.addActionListener(this);
        jilu.addActionListener(this);
        kehu.addActionListener(this);
        export.addActionListener(this);
        moudle.addActionListener(this::actionPerformed);
        save.addActionListener(this::actionPerformed);

        topJPanel.add(change);
        topJPanel.add(huisu);
        topJPanel.add(jili);
        topJPanel.add(jilu);
        topJPanel.add(kehu);
        topJPanel.add(save);
        topJPanel.add(export);
        topJPanel.add(moudle);
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
                (templateJpanel.guize).getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {

                        MyComboBox comboBox = (MyComboBox) jpanelHashMap.get("渠道小类").chioce;
                        comboBox.removeAllItems();
                        comboBox.addItem(new CheckValue(false, new Chioce("", "")));
                        String s = templateJpanel.guize.getText();
                        System.out.println("渠道大类代码为" + s);
                        String[] daimas = s.split(",");
                        List<Chioce> chioces = types.get("渠道小类").chioces;
                        List<Chioce> chioces1 = types.get("渠道类型").chioces;
                        List<String> text = new ArrayList<>();
                        for (String daima : daimas) {
                            for (Chioce chioce : chioces1) {
                                if (chioce.mingzi.equals(daima)) {
                                    text.add(chioce.daima);
                                    break;
                                }
                            }
                        }
                        for (String daima : text) {
                            for (Chioce chioce1 : chioces) {
                                if (chioce1.daima.startsWith(daima)) {
                                    CheckValue checkValue = new CheckValue();
                                    checkValue.value = new Chioce(chioce1.daima, chioce1.mingzi);
                                    checkValue.bolValue = false;
                                    comboBox.addItem(checkValue);
                                }
                            }
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        MyComboBox comboBox = (MyComboBox) jpanelHashMap.get("渠道小类").chioce;
                        comboBox.removeAllItems();
                        comboBox.addItem(new CheckValue(false, new Chioce("", "")));
                        String s = templateJpanel.guize.getText();
                        System.out.println("渠道大类代码为" + s);
                        String[] daimas = s.split(",");
                        List<Chioce> chioces = types.get("渠道小类").chioces;
                        List<Chioce> chioces1 = types.get("渠道类型").chioces;
                        List<String> text = new ArrayList<>();
                        for (String daima : daimas) {
                            for (Chioce chioce : chioces1) {
                                if (chioce.mingzi.equals(daima)) {
                                    text.add(chioce.daima);
                                    break;
                                }
                            }
                        }
                        for (String daima : text) {
                            for (Chioce chioce1 : chioces) {
                                if (chioce1.daima.startsWith(daima)) {
                                    CheckValue checkValue = new CheckValue();
                                    checkValue.value = new Chioce(chioce1.daima, chioce1.mingzi);
                                    checkValue.bolValue = false;
                                    comboBox.addItem(checkValue);
                                }
                            }
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                    }
                });
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
                                    checkValue = new CheckValue(false, new Chioce("28", "210吨以上挂车"));
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
                                    checkValue = new CheckValue(false, new Chioce("28", "210吨以上挂车"));
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
            } else if (mytype.title.equals("险种")) {
                ((JComboBox) templateJpanel.chioce).addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        String s = ((Chioce) e.getItem()).daima;
                        switch ((String) s) {
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
                    }
                });
            } else if (mytype.title.equals("可用变动费用率")) {
                (templateJpanel.chioce).addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {

                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        try {
                            int q = Integer.parseInt(((JTextField) templateJpanel.chioce).getText());
                            int a = Integer.parseInt(((JTextField) jpanelHashMap.get("代理手续费率").chioce).getText());
                            int b = Integer.parseInt(((JTextField) jpanelHashMap.get("月度提奖率").chioce).getText());
                            int c = Integer.parseInt(((JTextField) jpanelHashMap.get("组织利益(分)").chioce).getText());
                            int d = a + b + c;
                            if (q != d) {
                                JOptionPane.showMessageDialog(null, "可用变动费用率=代理手续费率+月度提奖率+组织利益(分)，应为：" + d, "输入错误提示", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (Exception ee) {
                            JOptionPane.showMessageDialog(null, "可用变动费率必须为数字哦", "输入错误", JOptionPane.ERROR_MESSAGE);
                        }


                    }
                });
            } else if (mytype.title.equals("销售及服务成本")) {
                (templateJpanel.chioce).addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {

                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        try {
                            int q = Integer.parseInt(((JTextField) templateJpanel.chioce).getText());
                            int a = Integer.parseInt(((JTextField) jpanelHashMap.get("可用变动费用率").chioce).getText());
                            int b = Integer.parseInt(((JTextField) jpanelHashMap.get("激励费用1").chioce).getText());
                            int c = Integer.parseInt(((JTextField) jpanelHashMap.get("激励费用2").chioce).getText());
                            int i = Integer.parseInt(((JTextField) jpanelHashMap.get("记录费用(率)").chioce).getText());
                            int f = Integer.parseInt(((JTextField) jpanelHashMap.get("客户评分").chioce).getText());
                            int g = Integer.parseInt(((JTextField) jpanelHashMap.get("预估增值服务费成本率").chioce).getText());

                            int h = Integer.parseInt(((JTextField) jpanelHashMap.get("预估间接理赔成本率").chioce).getText());
                            int d = a + b + c + f + g + h + i;
                            if (q != d) {
                                JOptionPane.showMessageDialog(null, "销售及服务成本=可用变动费用+激励费用1+激励费用2+记录费用(率) +客户评分+预估增值服务费成本率+预估间接理赔成本率\n，应为：" + d, "输入错误提示", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (Exception ee) {
                            JOptionPane.showMessageDialog(null, "销售及服务成本必须为数字哦", "输入错误", JOptionPane.ERROR_MESSAGE);
                        }


                    }
                });
            }
            jpanelHashMap.put(mytype.title, templateJpanel);
            jPanel.add(templateJpanel);
        }

        jScrollPane.setPreferredSize(new Dimension(700, 600));

        JScrollBar Bar = null;

        Bar = jScrollPane.getVerticalScrollBar();

        Bar.setUnitIncrement(40);


        this.setBackground(Color.WHITE);

        this.setBounds(200, 10, 700, (int) (height * 0.8));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(getOwner());
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    public void initMoudle() {
        this.setVisible(false);
    }

    static {
        titles = new ArrayList<>();
        titles.add("生效日期");
        titles.add("失效日期");
        titles.add("OA号");
        titles.add("保费是否含税");
        titles.add("备注");
        titles.add("销售及服务成本");
        titles.add("可用变动费用额");
        titles.add("险种");
        titles.add("新旧车标志");
        titles.add("月度提奖率");
        titles.add("可联动费用率");
        titles.add("组织利益(分)");
        titles.add("代理手续费率");
        titles.add("可用变动费用率");
        titles.add("组织利益(总)");
        titles.add("保单服务费");
        titles.add("固定绩效");
        titles.add("代理手续费率2");
        titles.add("代理手续费率2支付人");
        titles.add("预估引流成本率");
        titles.add("预估业务维护成本率");
        titles.add("预估增值服务费成本率");
        titles.add("预估间接理赔成本率");
        titles.add("预估增值服务费成本率2");
        titles.add("激励费率1");
        titles.add("激励费额1");
        titles.add("激励费率2");
        titles.add("激励费额2");
        titles.add("记录费用(率)");
        titles.add("记录费用(额)");
        titles.add("客户评分");
        titles.add("政策差异化方式");
        titles.add("费用险别");
        titles.add("归属机构");
        titles.add("决策单元大类");
        titles.add("决策单元小类");
        titles.add("决策单元代码");
        titles.add("渠道类型");
        titles.add("渠道小类");
        titles.add("是否秒杀");
        titles.add("产品组合");
        titles.add("双保标识");
        titles.add("业务方式");
        titles.add("协议号");
        titles.add("合作网点");
        titles.add("业务分类");
        titles.add("车损险车系分类");
        titles.add("决策表评分值");
        titles.add("车主年龄");
        titles.add("车主性别");
        titles.add("商业险精算折扣");
        titles.add("商业险报价折扣");
        titles.add("商业险签单折扣(自动)");
        titles.add("折扣率(%)区间");
        titles.add("无赔款优待系数区间");
        titles.add("商业险不浮动原因(平台)");
        titles.add("异地车标志");
        titles.add("往年索赔记录");
        titles.add("所属性质");
        titles.add("车辆用途");
        titles.add("车辆种类");
        titles.add("使用性质");
        titles.add("机动车种类");
        titles.add("生产国别");
        titles.add("车系代码");
        titles.add("续保标志");
        titles.add("拆单标志");
        titles.add("车龄区间");
        titles.add("贷款车标志");
        titles.add("新车购置价");
        titles.add("核定载质量(kg)");
        titles.add("签单保费");
        titles.add("起保日期");
        titles.add("品牌名称");
        titles.add("短期险标识");
        titles.add("自卸车标识");
        titles.add("交强险不浮动原因(平台)");
        titles.add("车型新分类");
        titles.add("平台车险分");
        titles.add("蚂蚁车险分");
        titles.add("商业险险别数量");
        titles.add("三者险状态");
        titles.add("车上人员险状态");
        titles.add("车损险状态");
        titles.add("机动车损失保险(IACJQL0001)状态");
        titles.add("盗抢险状态");
        titles.add("玻璃破碎险状态");
        titles.add("车身划痕险状态");
        titles.add("指定修理厂险状态");
        titles.add("自燃险状态");
        titles.add("新增设备险状态");
        titles.add("发动机损失险状态");
        titles.add("费用补偿险状态");
        titles.add("车上货物险状态");
        titles.add("精神损害险状态");
        titles.add("第三方特约险状态");
        titles.add("三者节假日翻倍险状态");
        titles.add("附加绝对免赔率特约(IACJQL0101)状态");
        titles.add("附加车轮单独损坏除外特约(IACJQL0201)状态");
        titles.add("新增设备损失险(IACJQL0001)状态");
        titles.add("操作员");
        titles.add("寿/养老业务员");
        titles.add("业务员");
        titles.add("车上人员险每座保额(万元)");

    }

    public static List<String> getTypes() {
        return titles;

    }


    public void refresh() {

        //字体会变大  com.sun.java.swing.plaf.windows.WindowsLookAndFeel
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            javax.swing.UIManager.setLookAndFeel(lookAndFeel);
            List<String> types = getTypes();
            for (String s : types) {
                TemplateJpanel templateJpanel = jpanelHashMap.get(s);
                SwingUtilities.updateComponentTreeUI(templateJpanel.title);
                SwingUtilities.updateComponentTreeUI(templateJpanel.guize);
                SwingUtilities.updateComponentTreeUI(templateJpanel.must);
                templateJpanel.must.setBackground(Color.WHITE);
                // templateJpanel.must.

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        //WebLookAndFeel.install();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
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
        });


    }

    public void saveAll() {

        File file = new File("all.xls");
        if (!file.exists()) {
            try {
                List<String> titles = getTypes();
                ExcleUtil.createExcle("all.xls", titles);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "创建文件错误，错误属性为：" + e.getMessage(), "保存错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {

            List<String> titles = getTypes();
            List<String> values = new ArrayList<>();
            for (int i = 0; i < titles.size(); i++) {
                try {
                    values.add(jpanelHashMap.get(titles.get(i)).getContent());
                } catch (ArrayIndexOutOfBoundsException e) {
                    values.add(e.getMessage());
                }
            }
            ExcleUtil.writetoExcle("all.xls", 0, values);
            JOptionPane.showConfirmDialog(null, "保存成功", "保存成功", JOptionPane.CLOSED_OPTION);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "写入文件错误，错误属性为：" + e.getMessage(), "保存错误", JOptionPane.ERROR_MESSAGE);
            return;
        }


//        //将文件保存到指定的位置
//        try {
//            FileOutputStream fos = new FileOutputStream("all.xls");
//            workbook.write(fos);
//            System.out.println("写入成功");
//            fos.close();
//            JOptionPane.showConfirmDialog(null, "保存成功","导出成功",JOptionPane.CLOSED_OPTION);
//        } catch (IOException ea) {
//            ea.printStackTrace();
//        }
    }

    public static Workbook getWorkBook(File file) {
        //获得文件名
        Workbook wb = null;
        FileInputStream in = null; // 文件流
        try {
            in = new FileInputStream(file);
            wb = new HSSFWorkbook(in);
            return wb;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }


    public void setValue(List<String> values) {
        for (int i = 0; i < getTypes().size(); i++) {
            jpanelHashMap.get(getTypes().get(i)).setValue(values.get(i));
        }

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String string = jButton.getText();
        List<String> titles = null;
        switch (string) {
            case "变动":
                titles = ChangeTemplate.initJtable();
                break;
            case "回溯":
                titles = HuisuTemplate.initJtable();
                break;
            case "激励":
                titles = JiliTemplate.initJtable();
                break;
            case "记录":
                titles = JiluTemplate.initJtable();
                break;
            case "客户":
                titles = KehuTemplate.initJtable();
                break;
            case "保存":
                saveAll();
                return;
            case "模板":
                File file = new File("all.xls");
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(null, "还没有保存任何模板呢", "显示错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception ee) {
                    System.out.println(ee);
                }

                //WebLookAndFeel.install();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    MoudleJFrame templateJFrame = new MoudleJFrame(TemplateJFrame.this);
                                    templateJFrame.refresh();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                });


                this.setVisible(false);
                return;

        }
//第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet(string + "模板");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        HSSFRow row1 = sheet.createRow(1);
        //第四步，创建单元格，设置表头
        HashMap<String, Mytype> types = TypeMannger.types;
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.RED.index);


//        HSSFFont font = workbook.createFont();
//        font.setColor(HSSFColor.RED.index);
//        style.setFont(font);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFCellStyle style1 = workbook.createCellStyle();
        style1.setFillForegroundColor(HSSFColor.YELLOW.index);
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

//        HSSFFont font1 = workbook.createFont();
//        font1.setColor(HSSFColor.YELLOW.index);
//        style1.setFont(font1);

        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i);
            //第四步，创建单元格，设置表头
            HSSFCell cell = row.createCell(i);
            if(types.get(title).must){
                cell.setCellStyle(style);
            }else {
                cell.setCellStyle(style1);
            }
            cell.setCellValue(types.get(title).getShorthand());
            cell = row1.createCell(i);
            if (jpanelHashMap.get(title) == null)
                System.out.println(title + "asd");
            try {
                cell.setCellValue(jpanelHashMap.get(title).getContent());
            } catch (ArrayIndexOutOfBoundsException ea) {
                JOptionPane.showMessageDialog(null, "属性错误，错误属性为：" + titles.get(i), "保存错误", JOptionPane.ERROR_MESSAGE);
                return;
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
            if(!path.endsWith(".xls")){
                path=path+".xls";
            }
            File file1 =new File(path);
            if(file1.exists()){
                JOptionPane.showMessageDialog(null, "错误：文件名重复", "保存错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                FileOutputStream fos = new FileOutputStream(path);
                workbook.write(fos);
                System.out.println("写入成功");
                fos.close();
                JOptionPane.showConfirmDialog(null, "导出路径为" + path, "导出成功", JOptionPane.CLOSED_OPTION);
            } catch (IOException ea) {
                JOptionPane.showMessageDialog(null, "错误：" + ea.getMessage(), "保存错误", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "您没有选择路径或者文件重复", "保存错误", JOptionPane.ERROR_MESSAGE);
        }

    }
}
