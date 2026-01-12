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
public class crud_produk {
    
    private String namaDB = "pertanian";
    private String url = "jdbc:mysql://localhost:3306/" + namaDB;
    private String username = "root";
    private String password = "";
    private Connection koneksi;
    public String VAR_NAMA_PRODUK = null;
    public String VAR_DESKRIPSI = null;
    public String VAR_KATEGORI = null;
    public double VAR_HARGA = 0;
    public String VAR_BERAT = null;
    public String VAR_STOK = null;
    public boolean validasi = false;
    
    public crud_produk(){
        try {
           Driver mysqldriver = new com.mysql.jdbc.Driver();
           DriverManager.registerDriver(mysqldriver);
           koneksi = DriverManager.getConnection(url,username,password);
           System.out.print("Berhasil dikoneksikan");
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
                
        }
    }
    
    public void simpanProduk01(String id_produk, String nama_produk, String deskripsi, String kategori, 
                               double harga, String berat, String stok){
        try {
            String sql = "insert into produk(id_int, nama_produk, deskripsi, kategori, harga, berat, stok) "
                    + "values('"+id_produk+"', '"+nama_produk+"', '"+deskripsi+"', '"+kategori+"', "+harga+", '"+berat+"', '"+stok+"')";
            
            String cekPrimary = "select * from produk where id_int= '"+id_produk+"'";
            
            Statement check = koneksi.createStatement();
            ResultSet data = check.executeQuery(cekPrimary);
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID produk sudah terdaftar");
                this.VAR_NAMA_PRODUK = data.getString("nama_produk");
                this.VAR_DESKRIPSI = data.getString("deskripsi");
                this.VAR_KATEGORI = data.getString("kategori");
                this.VAR_HARGA = data.getDouble("harga");
                this.VAR_BERAT = data.getString("berat");
                this.VAR_STOK = data.getString("stok");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_NAMA_PRODUK = null;
                this.VAR_DESKRIPSI = null;
                this.VAR_KATEGORI = null;
                this.VAR_HARGA = 0;
                this.VAR_BERAT = null;
                this.VAR_STOK = null;
                Statement perintah = koneksi.createStatement();
                perintah.execute(sql);
                JOptionPane.showMessageDialog(null, "Data produk berhasil disimpan");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void simpanProduk02(String id_produk, String nama_produk, String deskripsi, String kategori, 
                                double harga, String berat, String stok){
        try {
            String sql = "insert into produk(id_int, nama_produk, deskripsi, kategori, harga, berat, stok) value(?, ?, ?, ?, ?, ?, ?)";
            String cekPrimary = "select * from produk where id_int= ?";
            
            PreparedStatement check = koneksi.prepareStatement(cekPrimary);
            check.setString(1, id_produk);
            ResultSet data = check.executeQuery();
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID produk sudah terdaftar");
                this.VAR_NAMA_PRODUK = data.getString("nama_produk");
                this.VAR_DESKRIPSI = data.getString("deskripsi");
                this.VAR_KATEGORI = data.getString("kategori");
                this.VAR_HARGA = data.getDouble("harga");
                this.VAR_BERAT = data.getString("berat");
                this.VAR_STOK = data.getString("stok");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_NAMA_PRODUK = null;
                this.VAR_DESKRIPSI = null;
                this.VAR_KATEGORI = null;
                this.VAR_HARGA = 0;
                this.VAR_BERAT = null;
                this.VAR_STOK = null;
                PreparedStatement perintah = koneksi.prepareStatement(sql);
                perintah.setString(1, id_produk);
                perintah.setString(2, nama_produk);
                perintah.setString(3, deskripsi);
                perintah.setString(4, kategori);
                perintah.setDouble(5, harga);
                perintah.setString(6, berat);
                perintah.setString(7, stok);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data produk berhasil disimpan");
            }
            
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
     
    public void ubahProduk01(String id_produk, String nama_produk, String deskripsi, String kategori, 
                              double harga, String berat, String stok){
        try {
            String sql = "update produk set nama_produk = '"+nama_produk+"', deskripsi = '"+deskripsi+"', kategori = '"+kategori+"'"
                    + ", harga = "+harga+", berat = '"+berat+"', stok = '"+stok+"' where id_int = '"+id_produk+"'";
                   
            Statement perintah = koneksi.createStatement();
            perintah.execute(sql);
             JOptionPane.showMessageDialog(null, "Data produk berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
     
    public void ubahProduk02(String id_produk, String nama_produk, String deskripsi, String kategori, 
                               double harga, String berat, String stok){
        try {
            String sql = "update produk set nama_produk =?, deskripsi =?, kategori =?, harga =?, berat =?, stok =? where id_int =?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, nama_produk);
            perintah.setString(2, deskripsi);
            perintah.setString(3, kategori);
            perintah.setDouble(4, harga);
            perintah.setString(5, berat);
            perintah.setString(6, stok);
            perintah.setString(7, id_produk);
            
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data produk berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
      
    public void hapusProduk01(String id_produk){
        try {
            String sql = "delete from produk where id_int = '"+id_produk+"'";
                   
            Statement perintah = koneksi.createStatement();
            perintah.execute(sql);
             JOptionPane.showMessageDialog(null, "Data produk berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
       
    public void hapusProduk02(String id_produk){
        try {
            String sql = "delete from produk where id_int = ?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, id_produk);
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data produk berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
       
    public void tampilDataProduk (JTable komponenTable, String SQL){
        try {
            Statement perintah = koneksi.createStatement();
            ResultSet data = perintah.executeQuery(SQL);
            ResultSetMetaData meta = data.getMetaData();
            int jumKolom = meta.getColumnCount();
            DefaultTableModel modelTable = new DefaultTableModel();
            modelTable.addColumn("ID Produk");
            modelTable.addColumn("Nama Produk");
            modelTable.addColumn("Deskripsi");
            modelTable.addColumn("Kategori");
            modelTable.addColumn("Harga");
            modelTable.addColumn("Berat");
            modelTable.addColumn("Stok");
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
      
    public ArrayList<String> getNamaProdukList(){
        ArrayList<String> list = new ArrayList<>();
        try {
            String sql = "SELECT nama_produk FROM produk ORDER BY nama_produk";
            Statement st = koneksi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                list.add(rs.getString("nama_produk"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list;
    }
      
    public double getHargaByNama(String nama_produk){
        double harga = 0;
        try {
            String sql = "SELECT harga FROM produk WHERE nama_produk = ?";
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, nama_produk);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                harga = rs.getDouble("harga");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return harga;
    }
    
    public void cetakLaporanProduk(String fileLaporan, String SQL) {
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