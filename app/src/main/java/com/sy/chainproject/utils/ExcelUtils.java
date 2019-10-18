package com.sy.chainproject.utils;

import android.content.Context;
import android.util.Log;
import com.sy.chainproject.R;
import com.sy.chainproject.bean.MonthlyBean;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.List;

/**
 * Excel 表格操作类
 */
public class ExcelUtils {

    private CellStyle style;
    private Context context;

    /***
     * data  数据源 头
     * path  文件保存路径
     */
    public ExcelUtils(Context context, List<MonthlyBean> list, String store, String time,String path) {
        this.context = context;
        try {
            saveFile(path);
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
            Sheet sheet = workbook.getSheetAt(0);
            sheet.getRow(0).getCell(0).setCellValue(store);
            sheet.getRow(1).getCell(12).setCellValue(time);
            if (list == null) return;
            style = getCellStyle(workbook);

            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.createRow(i+4);
                row.setHeightInPoints(30);
                Cell cell = row.createCell(0);
                cell.setCellValue(list.get(i).getStyleNo());
                cell.setCellStyle(style);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(list.get(i).getStyleNo());
                cell1.setCellStyle(style);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(list.get(i).getStyleName());
                cell2.setCellStyle(style);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(list.get(i).getColorName());
                cell3.setCellStyle(style);

                Cell cell4 = row.createCell(4);
                cell4.setCellValue(list.get(i).getSizeName());
                cell4.setCellStyle(style);

                Cell cell5 = row.createCell(5);
                cell5.setCellValue(list.get(i).getBatch() + "");
                cell5.setCellStyle(style);

                Cell cell6 = row.createCell(6);
                cell6.setCellValue(list.get(i).getQty1() + "");
                cell6.setCellStyle(style);

                Cell cell7 = row.createCell(7);
                cell7.setCellValue(list.get(i).getQty2() + "");
                cell7.setCellStyle(style);

                Cell cell8 = row.createCell(8);
                cell8.setCellValue(list.get(i).getAmount2());
                cell8.setCellStyle(style);

                Cell cell9 = row.createCell(9);
                cell9.setCellValue(list.get(i).getQty3());
                cell9.setCellStyle(style);

                Cell cell10 = row.createCell(10);
                cell10.setCellValue(list.get(i).getAmount3());
                cell10.setCellStyle(style);

                Cell cell11 = row.createCell(11);
                cell11.setCellValue(list.get(i).getQty4());
                cell11.setCellStyle(style);

                Cell cell12 = row.createCell(12);
                cell12.setCellValue(list.get(i).getAmount4());
                cell12.setCellStyle(style);
            }
            FileOutputStream out = new FileOutputStream(path);
            workbook.write(out);
            out.flush();
            out.close();
            Log.e("tag", "创建完成");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("tag", "Excel创建失败： " + e.toString());
        }
    }

    private void saveFile(String path) throws IOException {
        // 首先判断该目录下的文件夹是否存在
        File file1 = new File(path);
        file1.createNewFile();
        // 开始进行文件的复制
        InputStream input = context.getResources().openRawResource(R.raw.test); // 获取资源文件raw
        // 标号
        try {
            FileOutputStream out = new FileOutputStream(file1); // 文件输出流、用于将文件写到SD卡中
            // -- 从内存出去
            byte[] buffer = new byte[1024];
            int len;
            while ((len = (input.read(buffer))) != -1) { // 读取文件，-- 进到内存

                out.write(buffer, 0, len); // 写入数据 ，-- 从内存出
            }
            input.close();
            out.close(); // 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * BOLD  字体
     */
    private CellStyle getCellStyle(HSSFWorkbook wb) {
        style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // style.setFillForegroundColor(HSSFColor.LIME.number);// 设置背景色
        // style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //// // 垂直 居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 水平 居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        style.setFont(font);
        // 左右上下边框样式
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
           /* style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
            style.setBorderRight(HSSFCellStyle.BORDER_NONE);
            style.setBorderTop(HSSFCellStyle.BORDER_NONE);
            style.setBorderBottom(HSSFCellStyle.BORDER_NONE);*/
        // 设置前景填充样式
        // style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // style.setFillForegroundColor(HSSFColor.WHITE.index);
        return style;
    }
}
