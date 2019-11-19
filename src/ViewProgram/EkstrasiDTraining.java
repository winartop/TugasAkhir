        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewProgram;

import com.mysql.jdbc.PreparedStatement;
import entity.Buah;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.List;
import java.awt.MediaTracker;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.CellType;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import koneksi.konek;
import lvq.DataManagement;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

/**
 *
 * @author oikawa
 */
public class EkstrasiDTraining extends javax.swing.JFrame  {
    
    private JFileChooser openFileChooser;
    private BufferedImage originalBI;
    private BufferedImage binarized;
    private BufferedImage grayImage;
    private BufferedImage binaryImage;
    private BufferedImage gAbu;
    private BufferedImage GTepi;
    private BufferedImage gKontur;
    Image img;
    Image img2;
    private int tinggiCitra;
    private int lebarCitra;
    private int maxGray;
    int lebar, tinggi, posX, posY, filter, tepi;
    int warna, alpha, red, green, blue, abuabu, tempwarna,hkontur,htepi;
    String tampil, rantai, kon;
    int m, n;
    double kernelK[][];

    int points;
    double perimeter;
    double area=0;
    
    private DefaultTableModel tabmode;
    
    /**
     * Creates new form EkstrasiDTraining
     */
    public EkstrasiDTraining() {
        initComponents();
        openFileChooser = new JFileChooser();
        //openFileChooser.setCurrentDirectory(new File("/media/oikawa/SEMENTARA/New folder/dataLatih"));
        //openFileChooser.setCurrentDirectory(new File("G:\\ProgramTugasAkhir\\Image Data Set"));
        openFileChooser.setCurrentDirectory(new File("G:\\ProgramTugasAkhir\\Image Data Set\\Manual Foto\\FixHasilManual"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("JPG images", "jpg"));

        ImageIcon icon = new ImageIcon(getClass().getResource("/aset/fruits.png"));
        setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
        setLebarKolom();
        //dataFromDataBaseToComboBox();

    }
 
    
    //==========================================================================
    //menampilkan isi database ke jtabel
    public void tampil_dataInisialisasi(){
        Object []baris = {"RED","GREEN","BLUE","AREA","PERIMETER","TARGET"};
        tabmode = new DefaultTableModel(null, baris);
        tabelExtraksiInBobot.setModel(tabmode);
        String sql = "select * from ekstraksifiturinisbobot";
        try {
            Connection con = new koneksi().getConnection();
            Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String red = hasil.getString("red");
                String green = hasil.getString("green");
                String blue = hasil.getString("blue");
                String area = hasil.getString("area");
                String perimeter = hasil.getString("perimeter");
                String jenis_buah = hasil.getString("jenis_buah");
                String[] data = {red, green, blue, area, perimeter,jenis_buah};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }
    
    public void tampil_dataLatih(){
       Object []baris = {"RED","GREEN","BLUE","AREA","PERIMETER","TARGET"};
        tabmode = new DefaultTableModel(null, baris);
        tabelExtraksiDataLatih.setModel(tabmode);
        String sql = "select * from ekstraksifiturlatih";
        try {
            Connection con = new koneksi().getConnection();
            Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String red = hasil.getString("red");
                String green = hasil.getString("green");
                String blue = hasil.getString("blue");
                String area = hasil.getString("area");
                String perimeter = hasil.getString("perimeter");
                String jenis_buah = hasil.getString("jenis_buah");
                String[] data = {red, green, blue, area, perimeter,jenis_buah};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }
    
    public void tampil_dataUji(){
       Object []baris = {"RED","GREEN","BLUE","AREA","PERIMETER","TARGET"};
        tabmode = new DefaultTableModel(null, baris);
        tabelExtraksiDataUji.setModel(tabmode);
        String sql = "select * from ekstraksifituruji";
        try {
            Connection con = new koneksi().getConnection();
            Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String red = hasil.getString("red");
                String green = hasil.getString("green");
                String blue = hasil.getString("blue");
                String area = hasil.getString("area");
                String perimeter = hasil.getString("perimeter");
                String jenis_buah = hasil.getString("jenis_buah");
                String[] data = {red, green, blue, area, perimeter,jenis_buah};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }
    
    public void dataFromDataBaseToComboBox(){
        try {
            String query = "SELECT * FROM detbuah";
            //String sql = "select NIM from nama_tabel_anda order by NIM asc";      // disini saya menampilkan NIM, anda dapat menampilkan
            Statement st = (Statement)konek.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
             
            while (rs.next()) {                
                comboBoxJenisBuah.addItem(rs.getString("jenis_buah"));
            }
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();
             
        } catch (SQLException e) {
        }
    }
   //===========================================================================
    
    
    //==========================================================================
    //Buka Gambar dan Extraksi
    public void bukaGambar(){
        int returnValue = openFileChooser.showOpenDialog(this);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                originalBI = ImageIO.read(openFileChooser.getSelectedFile());
                ImageIcon icon=new ImageIcon(originalBI);
                labelNama.setText("sukses buka gambar"); 
                LabelGambar.setIcon(icon);
                textNama.setText(openFileChooser.getSelectedFile().getName());
                textLokasiGambar.setText(openFileChooser.getSelectedFile().getPath());
                int x = originalBI.getWidth();
                int y = originalBI.getHeight();
                String path=this.textUkuran.getText();
                float uk1 = openFileChooser.getSelectedFile().length();
                float ukuran = uk1 / 1024;
                textUkuran.setText(""+ukuran);            
                String path1=this.textResolusiX.getText();
                textResolusiX.setText(""+x); textResolusiY.setText(""+y);
                System.out.println("BUKA GAMBAR SUKSES");
            } catch (Exception ioe) {
                 labelNama.setText("Salah masukan file");
            }
        }else{
            labelNama.setText("No file Chosen");
        }  
    }
    
    private void ekstrasiFiturWarna() throws IOException{
        int width1 = originalBI.getWidth();
        int height1 = originalBI.getHeight();
        
        int JRed=0;    int JGreen=0;       int JBlue=0;
        float JDRed=0;    float JDGreen=0;     float JDBlue=0;
        int JPiksel=width1*height1;
        
        for (int x = 0; x < width1; x++) {
            for (int y = 0; y < height1; y++) {
                Color c = new Color(originalBI.getRGB(x, y));
                int R = (int) c.getRed();
                int G = (int) c.getGreen();
                int B = (int) c.getBlue();
                JRed = JRed+R;
                JGreen = JGreen+G;
                JBlue = JBlue+B;         
            }
        }

        float Mred = JRed/JPiksel;
        float MGreen = JGreen/JPiksel;
        float MBlue = JBlue/JPiksel;

        textRed.setText(""+Mred);
        textGreen.setText(""+MGreen);
        textBlue.setText(""+MBlue);
        
        System.out.println(""+JPiksel);
        System.out.println(Mred + "+" + MGreen + "+" + MBlue);
        
    }
      
    public void imageToGray( ) {
         //int metode=0;
        int lebarCitra = originalBI.getWidth();
        int tinggiCitra = originalBI.getHeight();
        double red, green, blue;
        int gray = 0;
        Color before, after;

        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {

                before = new Color(originalBI.getRGB(x, y) & 0x00ffffff);
                int metode = 0;
                if (metode == 0) {
                    red = (double) (before.getRed());
                    green = (double) (before.getGreen());
                    blue = (double) (before.getBlue());
                    gray = (int) ((Math.max(green, Math.max(red, blue)) + Math.min(green, Math.min(red, blue))) / 2);
                } else if (metode == 1) {
                    red = (double) (before.getRed());
                    green = (double) (before.getGreen());
                    blue = (double) (before.getBlue());
                    gray = (int) ((red + green + blue) / 2);
                } else if (metode == 2) {
                    red = (double) (before.getRed() * 0.2989);
                    green = (double) (before.getGreen() * 0.5870);
                    blue = (double) (before.getBlue() * 0.1140);
                    gray = (int) (red + green + blue);
                }
                maxGray = gray > maxGray ? gray : maxGray;
                gray = gray < 0 ? 0 : gray;
                gray = gray > 255 ? 255 : gray;
                after = new Color(gray, gray, gray);

                output.setRGB(x, y, after.getRGB());

            }
        }
        this.grayImage = output;
        
        ImageIcon icon1=new ImageIcon(output);
        
        LabelGambarRed.setIcon(icon1);
        //imgGray.setRGB(width1, height1, newBI.getRGB());
    }
     
    private String decimalToBinary(int num) {
        int[] binArr = new int[8];
        int j = 0;
        while (num != 0) {
            int digit = num % 2;
            binArr[binArr.length - 1 - j] = digit;
            num = num / 2;
            j++;
        }
        return (Arrays.toString(binArr).replaceAll("\\,|\\[|\\]", ""));
    }
     
    public void imageToBitPlane() {
        int lebarCitra = grayImage.getWidth();
        int tinggiCitra = grayImage.getHeight();
        int hasil;
        String matrixBit[][][] = new String[lebarCitra][tinggiCitra][8];
        Color before, after;
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {
                before = new Color(grayImage.getRGB(x, y) & 0x00ffffff);
                matrixBit[x][y] = decimalToBinary(before.getRed()).split(" ");
                int level = 0;
                hasil = Integer.parseInt(matrixBit[x][y][level]);
                hasil = hasil == 1 ? 255 : hasil;
                after = new Color(hasil, hasil, hasil);
                output.setRGB(x, y, after.getRGB());
            }
        }
        
        this.binaryImage = output;
        ImageIcon icon2=new ImageIcon(output);
        LabelGambarGreen.setIcon(icon2);
    }     
              
    private static int colorToRGB(int alpha, int red, int green, int blue) { 
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;
        return newPixel;
     }
     
    private void bw() throws IOException{
        binarized = new BufferedImage(originalBI.getWidth(), originalBI.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
        int co;
        int newPixel;
        int threshold =230;//
        int jumGenap =0; int jumGanjil =1; 
        double Keliling=0;
        double Luas=0;
        
        double hasil_luas1 = 0;
        for(int i=0; i<originalBI.getWidth(); i++){
            for(int j=0; j<originalBI.getHeight(); j++){
                // Get pixels
                co = new Color(originalBI.getRGB(i, j)).getRed();
                int alpha = new Color(originalBI.getRGB(i, j)).getAlpha();
                if(co > threshold){
                    newPixel = 0;
                    jumGenap = jumGenap+1;
                    area = area + 1;
                }else {
                    newPixel = 255;
                    jumGanjil=jumGanjil+1;
                    area += -(i - 0.5);
                }
                newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
                binarized.setRGB(i, j, newPixel);
                if (co==255) {
                     hasil_luas1 = hasil_luas1 + 1;
                }
            }
        }
        
       
        ImageIcon icon3=new ImageIcon(binarized);
        LabelGambarBlue.setIcon(icon3);
        Keliling = jumGenap+Math.round(jumGanjil*Math.sqrt(2));
        Luas = hasil_luas1;
        
        System.out.println(""+Keliling);
        System.out.println(""+Luas);
        textArea.setText(""+Luas);
        textPerimeter.setText(""+Keliling);
        //ImageIO.write(binarized, "jpg",new File("blackwhiteimage") ); }
    }

    private void luasKel() throws IOException{
        binarized = new BufferedImage(originalBI.getWidth(), originalBI.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
        int imgS[][] = null;
        int newPixel[][] = null;
        int jumGenap =0; int jumGanjil =1; 
        int threshold =230;//
        double Keliling=0;
        for(int i=0; i<originalBI.getWidth(); i++){
            for(int j=0; j<originalBI.getHeight(); j++){
                // Get pixels
                imgS[i][j] = new Color(originalBI.getRGB(i, j)).getRed();
                int alpha = new Color(originalBI.getRGB(i, j)).getAlpha();
                if(imgS[i][j] > threshold){
                    newPixel[i][j] = 1;
                    jumGenap = jumGenap+1;
                }else {
                   newPixel[i][j] = 0;
                   jumGanjil=jumGanjil+1;
                }
                newPixel[i][j] = colorToRGB(alpha, newPixel[i][j], newPixel[i][j], newPixel[i][j]);
                Keliling = jumGenap+Math.round(jumGanjil*Math.sqrt(2));
                binarized.setRGB(i, j, newPixel[i][j]);
            }
        }     
        
        //ImageIO.write(binarized, "jpg",new File("blackwhiteimage") ); }
        
    }
  
    //==========================================================================
    
    //==========================================================================
    //Export excel
    
  
    //fix
    public void exportExcelInis(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deteksibuah","root","");
            Statement statement = con.createStatement();
            
            FileOutputStream fileOut;
            
            // Hasil Export
            
            //fileOut = new FileOutputStream("G:/ProgramTugasAkhir/HasilExport/DataInis.xls");
            fileOut = new FileOutputStream("G:/software/HasilExport/DataInis.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            
            // Nama Field
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("RED");
            row1.createCell(1).setCellValue("GREEN");
            row1.createCell(2).setCellValue("BLUE");
            row1.createCell(3).setCellValue("AREA");
            row1.createCell(4).setCellValue("PERIMETER");
            row1.createCell(5).setCellValue("TARGET");
            
            Row row2 ;
            ResultSet rs = statement.executeQuery("SELECT red,green,blue,area,perimeter,jenis_buah FROM ekstraksifiturinisbobot");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                // Sesuaikan dengan Jumlah Field
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getString(4));
                row2.createCell(4).setCellValue(rs.getString(5));
                row2.createCell(5).setCellValue(rs.getString(6));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            System.out.println("Export Success");
            JOptionPane.showMessageDialog(null, "EXPORT DATA SUKSES" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }
    
    public void exportDataLatih(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deteksibuah","root","");
            Statement statement = con.createStatement();
            
            FileOutputStream fileOut;
            
            // Hasil Export
            
            //fileOut = new FileOutputStream("G:/ProgramTugasAkhir/HasilExport/DataLatiha.xls");
            //fileOut = new FileOutputStream("G:/software/HasilExport/DataLatiha.xls");
            fileOut = new FileOutputStream("G:/ProgramTugasAkhir/HasilExport/DataLatih11.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            
            // Nama Field
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("RED");
            row1.createCell(1).setCellValue("GREEN");
            row1.createCell(2).setCellValue("BLUE");
            row1.createCell(3).setCellValue("AREA");
            row1.createCell(4).setCellValue("PERIMETER");
            row1.createCell(5).setCellValue("TARGET");
            
            Row row2 ;
            ResultSet rs = statement.executeQuery("SELECT red,green,blue,area,perimeter,jenis_buah FROM ekstraksifiturlatih");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                // Sesuaikan dengan Jumlah Field
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getString(4));
                row2.createCell(4).setCellValue(rs.getString(5));
                row2.createCell(5).setCellValue(rs.getString(6));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            System.out.println("Export Success");
            JOptionPane.showMessageDialog(null, "EXPORT DATA SUKSES" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }
    
    public void exportDatauji(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/deteksibuah","root","");
            Statement statement = con.createStatement();
            
            FileOutputStream fileOut;
            // Hasil Export
            //fileOut = new FileOutputStream("G:/ProgramTugasAkhir/HasilExport/DataUji.xls");
            //fileOut = new FileOutputStream("G:/software/HasilExport/DataUji.xls");
            fileOut = new FileOutputStream("G:/ProgramTugasAkhir/HasilExport/DataUji11.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            
            // Nama Field
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("RED");
            row1.createCell(1).setCellValue("GREEN");
            row1.createCell(2).setCellValue("BLUE");
            row1.createCell(3).setCellValue("AREA");
            row1.createCell(4).setCellValue("PERIMETER");
            row1.createCell(5).setCellValue("TARGET");
            
            Row row2 ;
            ResultSet rs = statement.executeQuery("SELECT red,green,blue,area,perimeter,jenis_buah FROM ekstraksifituruji");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                // Sesuaikan dengan Jumlah Field
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getString(4));
                row2.createCell(4).setCellValue(rs.getString(5));
                row2.createCell(5).setCellValue(rs.getString(6));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            System.out.println("Export Success");
            JOptionPane.showMessageDialog(null, "EXPORT DATA SUKSES" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    
    }
    
    //==========================================================================
    
    //==========================================================================
    //Simpan Data
    public void simpanInis(){
        double red = Double.valueOf(textRed.getText());
        double green = Double.valueOf(textGreen.getText());
        double blue = Double.valueOf(textBlue.getText());
        double area = Double.valueOf(textArea.getText());
        double perimeter = Double.valueOf(textPerimeter.getText());
        int jenis_buah = comboBoxJenisBuah.getSelectedIndex();

        try {
            Connection con = new koneksi().getConnection();
            Statement s=con.createStatement();
            s.execute("insert into ekstraksifiturinisbobot (red,green,blue,area,perimeter,jenis_buah)values('" + red + "','" + green + "','" + blue + "','" + area + "','" + perimeter + "','" + jenis_buah + "')");
            JOptionPane.showMessageDialog(null, "Data BERHASIL tersimpan" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
            bersih();
            tampil_dataInisialisasi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data GAGAL tersimpan" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void simpanDatalatih(){
        double red = Double.valueOf(textRed.getText());
        double green = Double.valueOf(textGreen.getText());
        double blue = Double.valueOf(textBlue.getText());
        double area = Double.valueOf(textArea.getText());
        double perimeter = Double.valueOf(textPerimeter.getText());
        int jenis_buah = comboBoxJenisBuah.getSelectedIndex();

        try {
            Connection con = new koneksi().getConnection();
            Statement s=con.createStatement();
            s.execute("insert into ekstraksifiturlatih (red,green,blue,area,perimeter,jenis_buah)values('" + red + "','" + green + "','" + blue + "','" + area + "','" + perimeter + "','" + jenis_buah + "')");
            JOptionPane.showMessageDialog(null, "Data BERHASIL tersimpan" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
            bersih();
            tampil_dataLatih();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data GAGAL tersimpan" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void simpanDatauji(){
        double red = Double.valueOf(textRed.getText());
        double green = Double.valueOf(textGreen.getText());
        double blue = Double.valueOf(textBlue.getText());
        double area = Double.valueOf(textArea.getText());
        double perimeter = Double.valueOf(textPerimeter.getText());
        int jenis_buah = comboBoxJenisBuah.getSelectedIndex();

        try {
            Connection con = new koneksi().getConnection();
            Statement s=con.createStatement();
            s.execute("insert into ekstraksifituruji (red,green,blue,area,perimeter,jenis_buah)values('" + red + "','" + green + "','" + blue + "','" + area + "','" + perimeter + "','" + jenis_buah + "')");
            JOptionPane.showMessageDialog(null, "Data BERHASIL tersimpan" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
            bersih();
            tampil_dataUji();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data GAGAL tersimpan" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }    
    }
    //==========================================================================

    //==========================================================================
    //Hapus Data
    public void hapusInis(){
        try {
            Connection con = new koneksi().getConnection();
            Statement s=con.createStatement();
            s.execute("delete from 	ekstraksifiturinisbobot where red='"+ textRed.getText() +"'");
            JOptionPane.showMessageDialog(null, "Data BERHASIL DIHAPUS" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
            bersih();
            tampil_dataInisialisasi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data GAGAL tersimpan" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(""+e);
        }    
    }
    
    public void hapusDatalatih(){
        try {
            Connection con = new koneksi().getConnection();
            Statement s=con.createStatement();
            s.execute("delete from 	ekstraksifiturlatih where area='"+ textArea.getText() +"'");
            JOptionPane.showMessageDialog(null, "Data BERHASIL DIHAPUS" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
            bersih();
            tampil_dataLatih();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data GAGAL tersimpan" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(""+e);
        }
    }
    
    public void hapusDatauji(){
        try {
            Connection con = new koneksi().getConnection();
            Statement s=con.createStatement();
            s.execute("delete from 	ekstraksifituruji where area='"+ textArea.getText() +"'");
            JOptionPane.showMessageDialog(null, "Data BERHASIL DIHAPUS" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
            bersih();
            tampil_dataUji();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data GAGAL tersimpan" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(""+e);
        }
    }
    
    //==========================================================================
    
    //==========================================================================
    //gabuangan
    DataManagement dataLatih;
    
    public void dataSelectInis(){
        int i = tabelExtraksiInBobot.getSelectedRow();
        if (i==-1){
            return;
        }
        textRed.setText(""+tabmode.getValueAt(i, 0));
        textGreen.setText(""+tabmode.getValueAt(i, 1));
        textBlue.setText(""+tabmode.getValueAt(i, 2));
        textArea.setText(""+tabmode.getValueAt(i, 3));
        textPerimeter.setText(""+tabmode.getValueAt(i, 4));
        //comboBoxJenisBuah.setSelectedI(tabmode.getValueAt(i, 5)+"");  
    }
    
    public void dataSelectLatih(){
        int i = tabelExtraksiDataLatih.getSelectedRow();
        if (i==-1){
            return;
        }
        textRed.setText(""+tabmode.getValueAt(i, 0));
        textGreen.setText(""+tabmode.getValueAt(i, 1));
        textBlue.setText(""+tabmode.getValueAt(i, 2));
        textArea.setText(""+tabmode.getValueAt(i, 3));
        textPerimeter.setText(""+tabmode.getValueAt(i, 4));
        //comboBoxJenisBuah.setSelectedI(tabmode.getValueAt(i, 5)+"");  
    }
   
    public void dataSelectUji(){
        int i = tabelExtraksiDataUji.getSelectedRow();
        if (i==-1){
            return;
        }
        textRed.setText(""+tabmode.getValueAt(i, 0));
        textGreen.setText(""+tabmode.getValueAt(i, 1));
        textBlue.setText(""+tabmode.getValueAt(i, 2));
        textArea.setText(""+tabmode.getValueAt(i, 3));
        textPerimeter.setText(""+tabmode.getValueAt(i, 4));
        //comboBoxJenisBuah.setSelectedI(tabmode.getValueAt(i, 5)+"");  
    }
    
    
    public void setColomnWidth(int kolom){
        DefaultTableColumnModel dtcm = (DefaultTableColumnModel) tabelExtraksiInBobot.getColumnModel();
        TableColumn kolomtabel = dtcm.getColumn(kolom);
        int lebar = 0;
        int margin = 10;
        int a;

        TableCellRenderer renderer = kolomtabel.getHeaderRenderer();
        if (renderer ==null){
            renderer = tabelExtraksiInBobot.getTableHeader().getDefaultRenderer();
        }
        Component komponen = renderer.getTableCellRendererComponent(tabelExtraksiInBobot, kolomtabel.getHeaderValue(), false, false, 0, 0);
        lebar = komponen.getPreferredSize().width;
        for (a = 0; a< tabelExtraksiInBobot.getRowCount(); a++){
            renderer = tabelExtraksiInBobot.getCellRenderer(a, kolom);
            //komponen = renderer.getTableCellRendererComponent(tabelExtraksi,tabel.getValueAt(a, kolom), false, false, a, kolom);
            int lebarKolom = komponen.getPreferredSize().width;
            lebar = Math.max(lebar, lebarKolom);
        }
        lebar = lebar + margin;
        kolomtabel.setPreferredWidth(lebar);

    }
    
    public void setLebarKolom(){
      int a;
      for(a =0; a< tabelExtraksiInBobot.getColumnCount(); a++){
          setColomnWidth(a);
      }
    }
    
     static File gambar = null; 
    public void bersih(){
        textNama.setText("");
        textLokasiGambar.setText("");
        textUkuran.setText("");
        textResolusiX.setText("");
        textResolusiY.setText("");
        labelNama.setText("");
        textArea.setText("");
        textRed.setText("");
        textGreen.setText("");
        textBlue.setText("");
        textArea.setText("");
        textPerimeter.setText("");
        LabelGambar.setIcon(null);
        ImageIcon icon = new ImageIcon(getClass().getResource("/aset/upload.png"));
        setIconImage(icon.getImage());
        LabelGambar.setIcon(icon);
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/aset/gambar.png"));
        setIconImage(icon.getImage());
        LabelGambarBlue.setIcon(null);
        LabelGambarGreen.setIcon(null);
        LabelGambarRed.setIcon(null);
        LabelGambarBlue.setIcon(icon1);
        LabelGambarGreen.setIcon(icon1);
        LabelGambarRed.setIcon(icon1); 
    }
    
    static File excelFile = null;
    
    
    //==========================================================================
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        jMenuItem = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnHome = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        textNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textLokasiGambar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textUkuran = new javax.swing.JTextField();
        textResolusiX = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboBoxJenisBuah = new javax.swing.JComboBox<>();
        labelNama = new javax.swing.JLabel();
        LabelGambar = new javax.swing.JLabel();
        textResolusiY = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnBukaGambar = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnBersihGambar = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        textRed = new javax.swing.JTextField();
        textGreen = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textBlue = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        textArea = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        textPerimeter = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        LabelGambarRed = new javax.swing.JLabel();
        LabelGambarBlue = new javax.swing.JLabel();
        LabelGambarGreen = new javax.swing.JLabel();
        btnEktrakFitur = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btnExportInisialisasi = new javax.swing.JLabel();
        btnTampilInisialisasi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelExtraksiInBobot = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelExtraksiDataLatih = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelExtraksiDataUji = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        btnSimpanDataInisialisasi = new javax.swing.JLabel();
        btnSimpanDataLatih = new javax.swing.JLabel();
        btnTampilLatih = new javax.swing.JButton();
        btnExportLatih = new javax.swing.JLabel();
        btnSimpanDataUji = new javax.swing.JLabel();
        btnTampilUji = new javax.swing.JButton();
        btnExportInisialUji = new javax.swing.JLabel();
        btnHapuslInisialisasi = new javax.swing.JButton();
        btnHapusLatih = new javax.swing.JButton();
        btnHapusUji = new javax.swing.JButton();

        jMenuItem.setText("jMenuItem1");
        jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemActionPerformed(evt);
            }
        });
        jPopupMenu.add(jMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(18, 181, 21));

        jLabel1.setBackground(new java.awt.Color(254, 254, 254));
        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("Aplikasi Pengenalan Citra Buah-buahan");

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/icons8_home_36px.png"))); // NOI18N
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("|");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/icons8_delete_sign2_16px.png"))); // NOI18N
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnHome)
                .addGap(4, 4, 4)
                .addComponent(jLabel17)
                .addGap(102, 102, 102)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 714, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, 40));

        jLabel2.setBackground(new java.awt.Color(254, 254, 254));
        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 1, 1));
        jLabel2.setText("MENU EKSTRASI FITUR CITRA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 38));

        jPanel3.setBackground(new java.awt.Color(234, 234, 234));

        jLabel3.setText("Nama Gambar");

        jLabel4.setText("Lokasi");

        jLabel5.setText("Ukuran");

        jLabel6.setText("Resolusi");

        jLabel7.setText("Jenis Buah");

        comboBoxJenisBuah.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buah Apel", "Buah Jeruk", "Buah Pisang" }));
        comboBoxJenisBuah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxJenisBuahActionPerformed(evt);
            }
        });

        LabelGambar.setBackground(new java.awt.Color(255, 255, 255));
        LabelGambar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelGambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/upload.png"))); // NOI18N

        jLabel16.setText("X");

        btnBukaGambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btnBukagmbr.png"))); // NOI18N
        btnBukaGambar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBukaGambarMouseClicked(evt);
            }
        });

        jLabel19.setText("/ KB");

        btnBersihGambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btnBersihGambar.png"))); // NOI18N
        btnBersihGambar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBersihGambarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelGambar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBukaGambar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textLokasiGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textResolusiY, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(52, 52, 52)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(textResolusiX, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel16))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(textUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel19))
                                            .addComponent(comboBoxJenisBuah, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(jLabel7))
                            .addComponent(labelNama)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnBersihGambar)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LabelGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textLokasiGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textResolusiX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(textResolusiY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxJenisBuah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnBersihGambar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelNama)
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnBukaGambar)
                        .addContainerGap(25, Short.MAX_VALUE))))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 530, 240));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel8.setText("FITUR WARNA");

        jLabel9.setText("RED");

        jLabel10.setText("GREEN");

        jLabel11.setText("BLUE");

        jLabel12.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel12.setText("FITUR BENTUK");

        jLabel13.setText("AREA");

        jLabel14.setText("PERIMETER");

        LabelGambarRed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelGambarRed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/gambar.png"))); // NOI18N
        LabelGambarRed.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LabelGambarBlue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelGambarBlue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/gambar.png"))); // NOI18N
        LabelGambarBlue.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LabelGambarGreen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelGambarGreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/gambar.png"))); // NOI18N
        LabelGambarGreen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnEktrakFitur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btnFitur.png"))); // NOI18N
        btnEktrakFitur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEktrakFiturMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(LabelGambarRed, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(LabelGambarGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LabelGambarBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textRed, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addGap(18, 18, 18)
                                            .addComponent(textGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel8))
                                .addGap(125, 125, 125)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textPerimeter, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 22, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEktrakFitur)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textRed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(textGreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(textBlue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textPerimeter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addGap(29, 29, 29)
                .addComponent(btnEktrakFitur)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LabelGambarGreen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(LabelGambarBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(LabelGambarRed, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 530, 410));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel15.setText("UNTUK DATA INISIALISASI BOBOT");

        btnExportInisialisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btnExportExcel.png"))); // NOI18N
        btnExportInisialisasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportInisialisasiMouseClicked(evt);
            }
        });

        btnTampilInisialisasi.setBackground(new java.awt.Color(0, 204, 204));
        btnTampilInisialisasi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTampilInisialisasi.setForeground(new java.awt.Color(255, 255, 255));
        btnTampilInisialisasi.setText("TAMPIL DATA");
        btnTampilInisialisasi.setBorder(null);
        btnTampilInisialisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilInisialisasiActionPerformed(evt);
            }
        });

        tabelExtraksiInBobot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelExtraksiInBobot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelExtraksiInBobotMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelExtraksiInBobotMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabelExtraksiInBobot);

        jLabel23.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel23.setText("UNTUK DATA LATIH");

        tabelExtraksiDataLatih.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelExtraksiDataLatih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelExtraksiDataLatihMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelExtraksiDataLatih);

        jLabel24.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel24.setText("UNTUK DATA UJI");

        tabelExtraksiDataUji.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelExtraksiDataUji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelExtraksiDataUjiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelExtraksiDataUji);

        btnSimpanDataInisialisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btnSimpan.png"))); // NOI18N
        btnSimpanDataInisialisasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanDataInisialisasiMouseClicked(evt);
            }
        });

        btnSimpanDataLatih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btnSimpan.png"))); // NOI18N
        btnSimpanDataLatih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanDataLatihMouseClicked(evt);
            }
        });

        btnTampilLatih.setBackground(new java.awt.Color(0, 204, 204));
        btnTampilLatih.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTampilLatih.setForeground(new java.awt.Color(255, 255, 255));
        btnTampilLatih.setText("TAMPIL DATA");
        btnTampilLatih.setBorder(null);
        btnTampilLatih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilLatihActionPerformed(evt);
            }
        });

        btnExportLatih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btnExportExcel.png"))); // NOI18N
        btnExportLatih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportLatihMouseClicked(evt);
            }
        });

        btnSimpanDataUji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btnSimpan.png"))); // NOI18N
        btnSimpanDataUji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanDataUjiMouseClicked(evt);
            }
        });

        btnTampilUji.setBackground(new java.awt.Color(0, 204, 204));
        btnTampilUji.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTampilUji.setForeground(new java.awt.Color(255, 255, 255));
        btnTampilUji.setText("TAMPIL DATA");
        btnTampilUji.setBorder(null);
        btnTampilUji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilUjiActionPerformed(evt);
            }
        });

        btnExportInisialUji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btnExportExcel.png"))); // NOI18N
        btnExportInisialUji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportInisialUjiMouseClicked(evt);
            }
        });

        btnHapuslInisialisasi.setBackground(new java.awt.Color(204, 51, 0));
        btnHapuslInisialisasi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHapuslInisialisasi.setForeground(new java.awt.Color(255, 255, 255));
        btnHapuslInisialisasi.setText("HAPUS DATA SELECTED");
        btnHapuslInisialisasi.setBorder(null);
        btnHapuslInisialisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapuslInisialisasiActionPerformed(evt);
            }
        });

        btnHapusLatih.setBackground(new java.awt.Color(204, 51, 0));
        btnHapusLatih.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHapusLatih.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusLatih.setText("HAPUS DATA SELECTED");
        btnHapusLatih.setBorder(null);
        btnHapusLatih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusLatihActionPerformed(evt);
            }
        });

        btnHapusUji.setBackground(new java.awt.Color(204, 51, 0));
        btnHapusUji.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHapusUji.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusUji.setText("HAPUS DATA SELECTED");
        btnHapusUji.setBorder(null);
        btnHapusUji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusUjiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnSimpanDataInisialisasi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTampilInisialisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExportInisialisasi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapuslInisialisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnSimpanDataLatih)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTampilLatih, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExportLatih)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapusLatih, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnSimpanDataUji)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTampilUji, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExportInisialUji)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapusUji, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 251, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTampilInisialisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnHapuslInisialisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnExportInisialisasi)
                                .addComponent(btnSimpanDataInisialisasi, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(1, 1, 1)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnTampilLatih, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnExportLatih, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(btnSimpanDataLatih, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(btnHapusLatih, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnTampilUji, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnExportInisialUji, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(btnSimpanDataUji, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(btnHapusUji, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 720, 670));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        new Home().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnHomeMouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this,
             "Apakah Anda yakin ingin menutup Aplikasi ini? ",  "Tutup Aplikasi? ",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
        System.exit(0);
        }
    }//GEN-LAST:event_jLabel18MouseClicked

    private void btnBukaGambarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBukaGambarMouseClicked
        bukaGambar();
    }//GEN-LAST:event_btnBukaGambarMouseClicked

    private void btnEktrakFiturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEktrakFiturMouseClicked
        try {
            ekstrasiFiturWarna();
            imageToGray();
            imageToBitPlane();
            bw();
            //luasKel();
        } catch (IOException ex) {
            Logger.getLogger(EkstrasiDTraining.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEktrakFiturMouseClicked
  
    private void btnSimpanDataInisialisasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanDataInisialisasiMouseClicked
        simpanInis();
    }//GEN-LAST:event_btnSimpanDataInisialisasiMouseClicked

    private void btnBersihGambarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBersihGambarMouseClicked
        bersih();
    }//GEN-LAST:event_btnBersihGambarMouseClicked

    private void btnExportInisialisasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportInisialisasiMouseClicked
        exportExcelInis();
    }//GEN-LAST:event_btnExportInisialisasiMouseClicked

    private void btnTampilInisialisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilInisialisasiActionPerformed
        tampil_dataInisialisasi();
    }//GEN-LAST:event_btnTampilInisialisasiActionPerformed
    
    private void btnSimpanDataLatihMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanDataLatihMouseClicked
        simpanDatalatih();
    }//GEN-LAST:event_btnSimpanDataLatihMouseClicked

    private void btnTampilLatihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilLatihActionPerformed
        tampil_dataLatih();
    }//GEN-LAST:event_btnTampilLatihActionPerformed
    
    private void btnExportLatihMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportLatihMouseClicked
        exportDataLatih();
    }//GEN-LAST:event_btnExportLatihMouseClicked

    private void btnSimpanDataUjiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanDataUjiMouseClicked
          simpanDatauji();
    }//GEN-LAST:event_btnSimpanDataUjiMouseClicked

    private void btnTampilUjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilUjiActionPerformed
        tampil_dataUji();
    }//GEN-LAST:event_btnTampilUjiActionPerformed

    private void btnExportInisialUjiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportInisialUjiMouseClicked
        exportDatauji();
    }//GEN-LAST:event_btnExportInisialUjiMouseClicked

    private void comboBoxJenisBuahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxJenisBuahActionPerformed
        
    }//GEN-LAST:event_comboBoxJenisBuahActionPerformed

    private void tabelExtraksiInBobotMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelExtraksiInBobotMouseReleased

    }//GEN-LAST:event_tabelExtraksiInBobotMouseReleased

    private void jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemActionPerformed
        
    }//GEN-LAST:event_jMenuItemActionPerformed

    private void tabelExtraksiInBobotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelExtraksiInBobotMouseClicked
        dataSelectInis();
    }//GEN-LAST:event_tabelExtraksiInBobotMouseClicked

    private void btnHapuslInisialisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapuslInisialisasiActionPerformed
        hapusInis();
    }//GEN-LAST:event_btnHapuslInisialisasiActionPerformed

    private void btnHapusLatihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusLatihActionPerformed
        hapusDatalatih();
    }//GEN-LAST:event_btnHapusLatihActionPerformed
    
    private void btnHapusUjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusUjiActionPerformed
        hapusDatauji();
    }//GEN-LAST:event_btnHapusUjiActionPerformed

    private void tabelExtraksiDataLatihMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelExtraksiDataLatihMouseClicked
        dataSelectLatih();
    }//GEN-LAST:event_tabelExtraksiDataLatihMouseClicked

    private void tabelExtraksiDataUjiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelExtraksiDataUjiMouseClicked
        // TODO add your handling code here:
        dataSelectUji();
    }//GEN-LAST:event_tabelExtraksiDataUjiMouseClicked

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EkstrasiDTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EkstrasiDTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EkstrasiDTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EkstrasiDTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EkstrasiDTraining().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel LabelGambar;
    private javax.swing.JLabel LabelGambarBlue;
    private javax.swing.JLabel LabelGambarGreen;
    private javax.swing.JLabel LabelGambarRed;
    private javax.swing.JLabel btnBersihGambar;
    private javax.swing.JLabel btnBukaGambar;
    private javax.swing.JLabel btnEktrakFitur;
    private javax.swing.JLabel btnExportInisialUji;
    private javax.swing.JLabel btnExportInisialisasi;
    private javax.swing.JLabel btnExportLatih;
    private javax.swing.JButton btnHapusLatih;
    private javax.swing.JButton btnHapusUji;
    private javax.swing.JButton btnHapuslInisialisasi;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnSimpanDataInisialisasi;
    private javax.swing.JLabel btnSimpanDataLatih;
    private javax.swing.JLabel btnSimpanDataUji;
    private javax.swing.JButton btnTampilInisialisasi;
    private javax.swing.JButton btnTampilLatih;
    private javax.swing.JButton btnTampilUji;
    public javax.swing.JComboBox<String> comboBoxJenisBuah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelNama;
    private javax.swing.JTable tabelExtraksiDataLatih;
    private javax.swing.JTable tabelExtraksiDataUji;
    private javax.swing.JTable tabelExtraksiInBobot;
    public javax.swing.JTextField textArea;
    public javax.swing.JTextField textBlue;
    public javax.swing.JTextField textGreen;
    public javax.swing.JTextField textLokasiGambar;
    public javax.swing.JTextField textNama;
    public javax.swing.JTextField textPerimeter;
    public javax.swing.JTextField textRed;
    public javax.swing.JTextField textResolusiX;
    private javax.swing.JTextField textResolusiY;
    public javax.swing.JTextField textUkuran;
    // End of variables declaration//GEN-END:variables

    

}
