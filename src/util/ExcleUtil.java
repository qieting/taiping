package util;

import Type.TypeMannger;
import UI.MoudleJFrame;
import UI.TemplateJFrame;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.EditorKit;
import javax.xml.bind.ValidationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ExcleUtil {

    public static HSSFSheet getSheet(String s, int sheetname) throws IOException {
        File excelFile = new File(s); // 创建文件对象
        FileInputStream in = new FileInputStream(excelFile); // 文件流
        HSSFWorkbook workbook = new HSSFWorkbook(in);        //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的
        HSSFSheet sheet = workbook.getSheetAt(0);   // 遍历第三个Sheet
        return sheet;
    }


    public static List<Vector> q(TemplateJFrame.Type type, HashMap<Integer, String> values) throws IOException {
        List<Vector> list = new ArrayList<Vector>();
        if (values.get(35) != null) {
            String s[] = values.get(35).split(",");
            for (String sss : s) {
                values.put(35, sss);
                List<Vector> sqsd = query(type, values);
                for (Vector v : sqsd) {
                    boolean sadsa = false;
                    for (Vector vv : list) {
                        if (v.get(0).equals(vv.get(0))) {
                            sadsa = true;
                            break;
                        }
                    }
                    if (!sadsa) {
                        list.add(v);
                    }
                }
            }
        } else {
            list = query(type, values);
        }
        return list;
    }

    public static List<Vector> queryin(TemplateJFrame.Type type, String path) throws IOException {
        File excelFile = new File(path); // 创建文件对象
        FileInputStream in = new FileInputStream(excelFile); // 文件流
        HSSFWorkbook workbook = new HSSFWorkbook(in);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<Vector> list = new ArrayList<Vector>();
        if (workbook != null) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                return list;
            }

            //获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);
            int lastCellNum = Template.getTypes(type).size();
            String that = null;
            for (int rowNum = lastRowNum; rowNum >= 1; rowNum--) {
                //获得当前行
                Row row = sheet.getRow(rowNum);
                Vector cells = new Vector();
                for (int cellNum = 0; cellNum <= lastCellNum; cellNum++) {
                    Cell cell = row.getCell(cellNum);

                    if (cell == null)
                        cells.add("");
                    else {
                        String ce = "";
                        switch (cell.getCellTypeEnum()) {
                            case STRING:
                                ce = String.valueOf(cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                ce = String.valueOf(cell.getNumericCellValue());
                                break;
                            case BLANK:
                                break;
                            default:
                                System.out.println(cell.getCellTypeEnum());
                                return null;
                        }
                        cells.add(ce);


                    }
                }
                list.add(cells);
            }
            System.out.println("一次循环");
            workbook.close();
        }
        return list;
    }


    public static List<Vector> query(TemplateJFrame.Type type, HashMap<Integer, String> values) throws IOException {

        File excelFile = new File(type + ".xls"); // 创建文件对象
        FileInputStream in = new FileInputStream(excelFile); // 文件流
        HSSFWorkbook workbook = new HSSFWorkbook(in);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<Vector> list = new ArrayList<Vector>();
        if (workbook != null) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                return list;
            }

            //获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);
            int lastCellNum = TemplateJFrame.getTypes().size();
            String that = null;
            for (int rowNum = lastRowNum; rowNum >= 1; rowNum--) {
                //获得当前行
                Row row = sheet.getRow(rowNum);
                Cell cell = row.getCell(0);
                if (cell != null) {
                    String code = cell.getStringCellValue();
                    if (that != null && !code.startsWith(that)) {
                        break;
                    }
                    if (code.endsWith("#")) {
                        continue;
                    }
                } else {
                    break;
                }

                boolean ch = true;
                for (int key : values.keySet()) {
                    cell = row.getCell(key);
                    if (cell == null)
                        continue;
                    String ce = cell.getStringCellValue();
                    if (values.get(key) != null) {

                        String q = values.get(key).trim();
//                        if(key==35){
//                            String s[]=q.split(",");
//                            int i;
//                            for(i=0;i<s.length;){
//                                if (ce.equals(s[i]) || s[i].equals("")) {
//                                    break;
//                                } else {
//
//                                }
//                                i++;
//                            }
//                            if(i==s.length){
//                                ch=false;
//                            }
//
//                        }else {
                        System.out.println(ce + "+" + q);
                        if (ce.equals(q) || q.equals("")) {

                        } else {
                            ch = false;
                            break;

                        }
                    }
                }
                if (ch) {
                    cell = row.getCell(0);
                    if (cell != null) {
                        String code = cell.getStringCellValue();
                        if (that == null) {
                            that = code.split("#")[0];
                        } else if (!code.startsWith(that)) {
                            break;
                        }
                    } else {
                        break;
                    }

                    Vector cells = new Vector();
                    for (int cellNum = 0; cellNum <= lastCellNum; cellNum++) {
                        cell = row.getCell(cellNum);

                        if (cell == null)
                            cells.add("");
                        else {
                            String ce = String.valueOf(cell.getStringCellValue());

                            cells.add(ce);


                        }
                    }

                    list.add(cells);
                }

            }
            System.out.println("一次循环");
            workbook.close();
        }
        return list;


    }

    public static void delete(TemplateJFrame.Type type, int[] columns) throws IOException {

        File excelFile = new File(type + ".xls"); // 创建文件对象
        FileInputStream in = new FileInputStream(excelFile); // 文件流
        HSSFWorkbook workbook = new HSSFWorkbook(in);        //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的

        HSSFSheet sheet = workbook.getSheetAt(0);   // 遍历第三个Sheet
        for (int i : columns) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            cell.setCellValue(cell.getStringCellValue() + "#");
        }

        FileOutputStream fos = new FileOutputStream(type + ".xls");
        workbook.write(fos);
        workbook.close();
        fos.close();


    }

    public static void change(TemplateJFrame.Type type, int[] nums, HashMap<Integer, String> map) throws IOException {

        File excelFile = new File(type + ".xls"); // 创建文件对象
        FileInputStream in = new FileInputStream(excelFile); // 文件流
        HSSFWorkbook workbook = new HSSFWorkbook(in);        //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的
        System.out.println("开始写入");
        HSSFSheet sheet = workbook.getSheetAt(0);   // 遍历第三个Sheet
        for (int i : nums) {
            Row row = sheet.getRow(i);
            for (int key : map.keySet()) {
                Cell cell = row.getCell(key);
                cell.setCellValue(map.get(key));
            }

        }

        FileOutputStream fos = new FileOutputStream(type + ".xls");
        workbook.write(fos);
        workbook.close();
        fos.close();


    }


    public static void writetoExcle(TemplateJFrame.Type type, Vector<Vector> values) throws IOException {
        File excelFile = new File(type + ".xls"); // 创建文件对象
        FileInputStream in = new FileInputStream(excelFile); // 文件流
        HSSFWorkbook workbook = new HSSFWorkbook(in);        //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的

        HSSFSheet sheet = workbook.getSheetAt(0);   // 遍历第三个Sheet

        int last = sheet.getLastRowNum() + 1;
        System.out.println("要写入" + last);
        for (int i = 0; i < values.size(); i++) {

            Vector vector = (Vector) values.get(i);
            HSSFRow row1 = sheet.createRow(last);
            HSSFCell cellq = row1.createCell(0);
            cellq.setCellValue(last - i + "#" + last);
            last = last + 1;
            for (int ii = 1; ii <= vector.size(); ii++) {
                HSSFCell cell = row1.createCell(ii);
                cell.setCellValue((String) vector.get(ii - 1));
            }


        }
        //将文件保存到指定的位置

        FileOutputStream fos = new FileOutputStream(type + ".xls");
        workbook.write(fos);
        System.out.println("写入all成功" + values.size());
        workbook.close();
        fos.close();


    }


    public static void createExcle(TemplateJFrame.Type type) throws IOException {
        List<String> titles = TemplateJFrame.titles;
        titles.add(0, "ddd");
        File file = new File(type.toString() + ".xls");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(file.getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        //HSSFSheet sheet = workbook.createSheet();
        if (titles != null) {
            HSSFCell cell;
            HSSFSheet sheet = workbook.createSheet("all");


            HSSFRow row = sheet.createRow(0);
            for (int i = 0; i < titles.size(); i++) {

                //第四步，创建单元格，设置表头
                cell = row.createCell(i);

                cell.setCellValue(titles.get(i));

            }
        }


        //将文件保存到指定的位置

        FileOutputStream fos = new FileOutputStream(type.toString() + ".xls");
        workbook.write(fos);
        System.out.println("写入成功");
        fos.close();
        titles.remove(0);

    }

}
