package util;

import Type.TypeMannger;
import UI.TemplateJFrame;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

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

    public static List<Vector> query(String path, int sheetname, HashMap<Integer, String> values) throws IOException {

        File excelFile = new File(path); // 创建文件对象
        FileInputStream in = new FileInputStream(excelFile); // 文件流
        HSSFWorkbook workbook = new HSSFWorkbook(in);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<Vector> list = new ArrayList<Vector>();
        if (workbook != null) {

            Sheet sheet = workbook.getSheetAt(sheetname);
            if (sheet == null) {
                return list;
            }
            //获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            //获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();
            //循环除了第一行的所有行

            for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                //获得当前行
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                //获得当前行的开始列
                int firstCellNum = row.getFirstCellNum();
                //获得当前行的列数
                int lastCellNum = TemplateJFrame.getTypes().size();
                Vector cells = new Vector();
                //循环当前行
                int cellNum = 0;
                for (cellNum = firstCellNum; cellNum < lastCellNum; ) {
                    Cell cell = row.getCell(cellNum);
                    String ce = String.valueOf(cell.getStringCellValue());
                    if (values.get(cellNum) != null) {
                        System.out.println(TemplateJFrame.getTypes().get(cellNum));
                        String q = values.get(cellNum).trim();
                        System.out.println(ce+"+"+q);
                        if (q .equals( "" )|| ce.equals(q)) {

                            cells.add(String.valueOf(cell.getStringCellValue()));
                        } else {
                            System.out.println("我被调用了"+cellNum);
                            break;
                        }
                    }else {
                        cells.add(String.valueOf(cell.getStringCellValue()));
                    }
                    cellNum++;

                }
                System.out.println(cellNum);
                if (cellNum == lastCellNum )
                    list.add(cells);
            }

            workbook.close();
        }
        return list;


    }

    public static void writetoExcle(String s, int sheetname, List<String> values) throws IOException {
        File excelFile = new File(s); // 创建文件对象
        FileInputStream in = new FileInputStream(excelFile); // 文件流
        HSSFWorkbook workbook = new HSSFWorkbook(in);        //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的


        HSSFSheet sheet = workbook.getSheetAt(sheetname);   // 遍历第三个Sheet

        int last = sheet.getLastRowNum();
        HSSFRow row1 = sheet.createRow(last + 1);
        for (int i = 0; i < values.size(); i++) {
            HSSFCell cell = row1.createCell(i);
            cell.setCellValue(values.get(i));
        }
        //将文件保存到指定的位置

        FileOutputStream fos = new FileOutputStream(s);
        workbook.write(fos);
        System.out.println("写入成功");
        workbook.close();
        fos.close();


    }


    public static void createExcle(String s) throws IOException {
        createExcle(s, null);
    }

    public static void createExcle(String s, List<String> titles) throws IOException {
        File file = new File(s);
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

        FileOutputStream fos = new FileOutputStream(s);
        workbook.write(fos);
        System.out.println("写入成功");
        fos.close();


    }

}
