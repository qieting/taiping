package UI;

import UI.MyComboBox.CheckValue;
import UI.MyComboBox.MyComboBox;
import util.ExcleUtil;
import Type.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class MoudleJFrame extends JFrame implements ActionListener {

    JButton quert,select;
    JTextField index;
    JTable table;
    JTextField jTextField;
    HashMap<String, TemplateJpanel> jpanelHashMap;
    DefaultTableModel model;
    TemplateJFrame templateJFrame;

    public MoudleJFrame(TemplateJFrame t) {
        super();
        this.setLayout(new MyVFlowLayout());
        this.setTitle("模板");
        templateJFrame=t;
        JPanel topJPanel = new JPanel();
        int height=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        quert = new JButton("查询");
        select =new JButton("选择");
        jTextField =new JTextField("           查询模式下什么都不选代表所有都可以，忽略必选标识");
        jTextField.setBorder(new EmptyBorder(0,0,0,0));
        jTextField.setEnabled(false);
        jTextField.setDisabledTextColor(Color.red);
        index=new JTextField("1       ");
        quert.addActionListener(this);
        select.addActionListener(this);


        topJPanel.add(quert);
        topJPanel.add(index);
        topJPanel.add(select);
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

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });
            }
            jpanelHashMap.put(mytype.title, templateJpanel);
            jPanel.add(templateJpanel);
        }


        jScrollPane.setPreferredSize(new Dimension(700, (int)(height*0.35)));

        JScrollBar Bar = null;

        Bar = jScrollPane.getVerticalScrollBar();

        Bar.setUnitIncrement(40);

        JScrollPane jScrolltable = new JScrollPane();
        this.add(jScrolltable);
        jScrolltable.setPreferredSize(new Dimension(200, (int)(height*0.30)));
        JPanel tablepanle = new JPanel();
        tablepanle.setLayout(new MyVFlowLayout());
        jScrolltable.setViewportView(tablepanle);
        table =new JTable();
         model = new DefaultTableModel();

        table.setModel(model);
        model.addColumn("序号");
        for(String s :TemplateJFrame.getTypes()){
            model.addColumn(s);
        }
        tablepanle.add(table.getTableHeader());
        tablepanle.add(table);


        this.setBackground(Color.WHITE);

        this.setBounds(200, 100, 700, (int)(height*0.8));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(getOwner());

       this.pack();

        this.setVisible(true);
        this.refresh();
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

    public void refresh(){

        //字体会变大  com.sun.java.swing.plaf.windows.WindowsLookAndFeel
        String lookAndFeel =UIManager.getSystemLookAndFeelClassName();
        try {
            javax.swing. UIManager.setLookAndFeel(lookAndFeel);
            List<String> types =getTypes();
            for(String s :types){
                TemplateJpanel templateJpanel =jpanelHashMap.get(s);
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

       MoudleJFrame templateJFrame = new MoudleJFrame(null);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String string = jButton.getText();

        switch (string) {
            case "查询":
                index.setText("1");
                List<String> titles = getTypes();
                HashMap<Integer,String> map =new HashMap<>();
                int  sele []={0,1,2,36,33,34,37,7,8,59,60};
                for (int i = 0; i < titles.size(); i++) {
                    try {
                        String s =jpanelHashMap.get(titles.get(i)).getContent();
                        if(s.startsWith(",")){
                            s=s.replaceFirst(",","");
                        }
                        map.put(sele[i],""+s);
                    } catch (ArrayIndexOutOfBoundsException ea) {
                        map.put(sele[i],""+ea.getMessage());
                    }
                }


                try {
                    List<Vector> vectors=ExcleUtil.query("all.xls",0,map);

                    JOptionPane.showConfirmDialog(null, "查询到"+vectors.size(),"" ,JOptionPane.CLOSED_OPTION);

                        model.setRowCount(0);

;
                    for (int i=0 ;i<vectors.size();i++){
                        Vector vector =vectors.get(i);
                        vector.add(0,i);
                        model.addRow(vector);
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            case "选择":
                String s =index.getText()+"";
                if(s.equals("")){
                    showError("请输入要选择的行数");
                }else {
                    try {
                        int  q =Integer.parseInt(s.trim());
                        if(q>=table.getRowCount()){
                            showError("请输入正确的行数");
                        }
                        List<String> strings =new ArrayList<>();
                        for(int i =1 ;i<=104 ;i++){
                            strings.add(""+model.getValueAt(q,i));
                        }
                        templateJFrame.setValue(strings);

                        this.setVisible(false);


                    }catch (Exception  ee){
                        showError("输入错误"+ee.getMessage());

                    }


                }
                break;
        }
//第一步

    }

    public  void  showError(String s){
        JOptionPane.showMessageDialog(null, s, "错误", JOptionPane.ERROR_MESSAGE);
    }
}
