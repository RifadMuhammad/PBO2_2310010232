/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.io.File;
import java.util.Set;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class crud_toko {
    
    private String namaDB = "pertanian";
    private String url = "jdbc:mysql://localhost:3306/" + namaDB;
    private String username = "root";
    private String password = "";
    private Connection koneksi;
    public String VAR_NAMA_TOKO = null;
    public String VAR_ALAMAT_TOKO = null;
    public String VAR_NO_HP_TOKO = null;
    public String VAR_RATING = null;
    public String VAR_PRODUK = null;
    public boolean validasi = false;
    
    public crud_toko(){
        try {
           Driver mysqldriver = new com.mysql.jdbc.Driver();
           DriverManager.registerDriver(mysqldriver);
           koneksi = DriverManager.getConnection(url,username,password);
           System.out.print("Berhasil dikoneksikan");
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
                
        }
    }
    
    public void simpanToko01(String id_toko, String nama_toko, String alamat_toko, String no_hp_toko, 
                             String rating, String produk, String tgl_daftar_user){
        try {
            String sql = "insert into toko(id_toko_int, nama_toko, alamat_toko, no_hp_toko, rating, produk, tgl_daftar_user) "
                    + "values('"+id_toko+"', '"+nama_toko+"', '"+alamat_toko+"', '"+no_hp_toko+"', '"+rating+"', '"+produk+"', '"+tgl_daftar_user+"')";
            
            String cekPrimary = "select * from toko where id_toko_int= '"+id_toko+"'";
            
            Statement check = koneksi.createStatement();
            ResultSet data = check.executeQuery(cekPrimary);
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID toko sudah terdaftar");
                this.VAR_NAMA_TOKO = data.getString("nama_toko");
                this.VAR_ALAMAT_TOKO = data.getString("alamat_toko");
                this.VAR_NO_HP_TOKO = data.getString("no_hp_toko");
                this.VAR_RATING = data.getString("rating");
                this.VAR_PRODUK = data.getString("produk");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_NAMA_TOKO = null;
                this.VAR_ALAMAT_TOKO = null;
                this.VAR_NO_HP_TOKO = null;
                this.VAR_RATING = null;
                this.VAR_PRODUK = null;
                Statement perintah = koneksi.createStatement();
                perintah.execute(sql);
                JOptionPane.showMessageDialog(null, "Data toko berhasil disimpan");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void simpanToko02(String id_toko, String nama_toko, String alamat_toko, String no_hp_toko, 
                              String rating, String produk, String tgl_daftar_user){
        try {
            String sql = "insert into toko(id_toko_int, nama_toko, alamat_toko, no_hp_toko, rating, produk, tgl_daftar_user) value(?, ?, ?, ?, ?, ?, ?)";
            String cekPrimary = "select * from toko where id_toko_int= ?";
            
            PreparedStatement check = koneksi.prepareStatement(cekPrimary);
            check.setString(1, id_toko);
            ResultSet data = check.executeQuery();
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID toko sudah terdaftar");
                this.VAR_NAMA_TOKO = data.getString("nama_toko");
                this.VAR_ALAMAT_TOKO = data.getString("alamat_toko");
                this.VAR_NO_HP_TOKO = data.getString("no_hp_toko");
                this.VAR_RATING = data.getString("rating");
                this.VAR_PRODUK = data.getString("produk");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_NAMA_TOKO = null;
                this.VAR_ALAMAT_TOKO = null;
                this.VAR_NO_HP_TOKO = null;
                this.VAR_RATING = null;
                this.VAR_PRODUK = null;
                PreparedStatement perintah = koneksi.prepareStatement(sql);
                perintah.setString(1, id_toko);
                perintah.setString(2, nama_toko);
                perintah.setString(3, alamat_toko);
                perintah.setString(4, no_hp_toko);
                perintah.setString(5, rating);
                perintah.setString(6, produk);
                perintah.setString(7, tgl_daftar_user);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data toko berhasil disimpan");
            }
            
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
     
    public void ubahToko01(String id_toko, String nama_toko, String alamat_toko, String no_hp_toko, 
                            String rating, String produk, String tgl_daftar_user){
        try {
            String sql = "update toko set nama_toko = '"+nama_toko+"', alamat_toko = '"+alamat_toko+"', no_hp_toko = '"+no_hp_toko+"'"
                    + ", rating = '"+rating+"', produk = '"+produk+"', tgl_daftar_user = '"+tgl_daftar_user+"' where id_toko_int = '"+id_toko+"'";
                   
            Statement perintah = koneksi.createStatement();
            perintah.execute(sql);
             JOptionPane.showMessageDialog(null, "Data toko berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
     
    public void ubahToko02(String id_toko, String nama_toko, String alamat_toko, String no_hp_toko, 
                             String rating, String produk, String tgl_daftar_user){
        try {
            String sql = "update toko set nama_toko =?, alamat_toko =?, no_hp_toko =?, rating =?, produk =?, tgl_daftar_user =? where id_toko_int =?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, nama_toko);
            perintah.setString(2, alamat_toko);
            perintah.setString(3, no_hp_toko);
            perintah.setString(4, rating);
            perintah.setString(5, produk);
            perintah.setString(6, tgl_daftar_user);
            perintah.setString(7, id_toko);
            
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data toko berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
      
    public void hapusToko01(String id_toko){
        try {
            String sql = "delete from toko where id_toko_int = '"+id_toko+"'";
                   
            Statement perintah = koneksi.createStatement();
            perintah.execute(sql);
             JOptionPane.showMessageDialog(null, "Data toko berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
       
    public void hapusToko02(String id_toko){
        try {
            String sql = "delete from toko where id_toko_int = ?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, id_toko);
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data toko berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
       
    public void tampilDataToko (JTable komponenTable, String SQL){
        try {
            Statement perintah = koneksi.createStatement();
            ResultSet data = perintah.executeQuery(SQL);
            ResultSetMetaData meta = data.getMetaData();
            int jumKolom = meta.getColumnCount();
            DefaultTableModel modelTable = new DefaultTableModel();
            modelTable.addColumn("ID Toko");
            modelTable.addColumn("Nama Toko");
            modelTable.addColumn("Alamat Toko");
            modelTable.addColumn("No HP Toko");
            modelTable.addColumn("Rating");
            modelTable.addColumn("Produk");
            modelTable.addColumn("Tgl Daftar User");
            modelTable.getDataVector().clear();
            modelTable.fireTableDataChanged();
            while (data.next() ) {
                Object[] row = new Object[jumKolom];
                for(int i = 1; i <= jumKolom; i++ ){
                    row [i - 1] = data.getObject(i);
                }
                modelTable.addRow(row);
            }
            komponenTable.setModel(modelTable);
        } catch (Exception e) {
            
        }
    }
      
    public ArrayList<String> getNamaTokoList(){
        ArrayList<String> list = new ArrayList<>();
        try {
            String sql = "SELECT nama_toko FROM toko ORDER BY nama_toko";
            Statement st = koneksi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                list.add(rs.getString("nama_toko"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list;
    }
    
    public void cetakLaporanToko(String fileLaporan, String SQL) {
        try {
            File file = new File(fileLaporan);
            JasperDesign jasDesign = JRXmlLoader.load(file);
            JRDesignQuery query = new JRDesignQuery();
            query.setText(SQL);
            jasDesign.setQuery(query);
            JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);
            JasperPrint jrPrint = JasperFillManager.fillReport(jasReport, null, koneksi);
            JasperViewer.viewReport(jrPrint, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
}