/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

/**
 *
 * @author sergiottellez
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author franc
 */
public class Lector {

    private static String url;

    public Lector(String u) {
        url = u;
    }

    public Fila getUna() throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(url));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();
        iterator.next();
        Row currentRow = iterator.next();
        return read(currentRow);
    }
    
    public Fila Prove() throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(url));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();
        for(int i =0;i<4;i++){
            iterator.next();
        }
        
        Row currentRow = iterator.next();
        return read(currentRow);
    }
    
    public List<Fila> getFile() throws FileNotFoundException, IOException {
        List<Fila> filas = new ArrayList<>();

        FileInputStream excelFile = new FileInputStream(new File(url));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();
        iterator.next();
        Row currentRow;
        while (iterator.hasNext()) {
            currentRow = iterator.next();
            filas.add(read(currentRow));
        }
        return filas;
    }

    public static Fila read(Row currentRow) {
        Fila f = new Fila();
        
        f.user_id = cogerCad(currentRow,0);
        if(f.user_id== ""){
            
        }
        f.apellido1 = cogerCad(currentRow,1);
        f.apellido2 = cogerCad(currentRow,2);
        f.nombre = cogerCad(currentRow,3);
        f.sexo = cogerCad(currentRow,4);
        f.dblppersoname = cogerCad(currentRow,5);
        f.dblpauthorkey = cogerCad(currentRow,6);
        f.universidad = cogerCad(currentRow,8);
        f.region = cogerCad(currentRow,9);
        f.empresa = cogerCad(currentRow,10);
        f.centro = cogerCad(currentRow,11);
        f.foreigner = cogerCad(currentRow,12);
        f.email = cogerCad(currentRow,13);
        try{
             f.antiguedad = (int) cogerDob(currentRow,17);
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
             System.out.println("PRUEBA ANTIGUEDAD");
             f.antiguedad = Integer.parseInt(cogerCad(currentRow,17));
 
            
        }
        
        try{
        f.activo = cogerCad(currentRow,18);
        }catch(Exception e){
              System.out.println(e.fillInStackTrace());
             System.out.println("PRUEBA ACTIVO");
             f.activo = String.valueOf(cogerDob(currentRow,17));

        }
        
        try{
            
        
             f.reciente = (int)(cogerDob(currentRow,19));
        }catch(Exception e){
                  System.out.println(e.fillInStackTrace());
             System.out.println("PRUEBA RECIENTE");
             f.reciente = Integer.parseInt(cogerCad(currentRow,19));

        }
       
       
        
        
        return f;
    }

    public static String reedCell(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
        }
        return "";
    }
    
    public static String cogerCad(Row r,int index){
        Cell c = r.getCell(index);
        String out;
        if(c==null){
            out=null;
        }else{
            out = c.getStringCellValue();
            if(out.contains("'")){
                out=out.replace("'", "´");
            }
        }
        return out;
    }
    
    public static double cogerDob(Row r,int index){
        Cell c = r.getCell(index);
        double out;
        if(c==null){
            out=-1;
        }else{
            out = c.getNumericCellValue();
        }
        return out;
    }
    
    public  static Date cogerDat(Row r,int index){
        Cell c = r.getCell(index);
        Date out;
        if(c==null){
            out=null;
        }else{
            out = c.getDateCellValue();
        }
        return out;
    }
}

