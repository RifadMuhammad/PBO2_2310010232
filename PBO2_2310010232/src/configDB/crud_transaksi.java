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
public class crud_transaksi {
    
    private String namaDB = "pertanian";
    private String url = "jdbc:mysql://localhost:3306/" + namaDB;
    private String username = "root";
    private String password = "";
    private Connection koneksi;
    public String VAR_NAMA_USER = null;
    public String VAR_NAMA_TOKO = null;
    public String VAR_NAMA_PRODUK = null;
    public double VAR_HARGA = 0;
    public int VAR_JLH_PRODUK = 0;
    public double VAR_TOTAL_HARGA = 0;
    public String VAR_KODE_KURIR = null;
    public double VAR_ONGKOS_KIRIM = 0;
    public double VAR_JLH_TOTAL = 0;
    public boolean validasi = false;
    
    public crud_transaksi(){
        try {
           Driver mysqldriver = new com.mysql.jdbc.Driver();
           DriverManager.registerDriver(mysqldriver);
           koneksi = DriverManager.getConnection(url,username,password);
           System.out.print("Berhasil dikoneksikan");
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
                
        }
    }
    
    public void simpanTransaksi01(String id_transaksi, String tgl_beli, String nama_user, String nama_toko, 
                                   String nama_produk, double harga, int jlh_produk, double total_harga, 
                                   String kode_kurir, double ongkos_kirim, double jlh_total){
        try {
            String sql = "insert into transaksi(id_int, tgl_beli, nama_user, nama_toko, nama_produk, harga, jlh_produk, total_harga, kode_kurir, ongkos_kirim, jlh_total) "
                    + "values('"+id_transaksi+"', '"+tgl_beli+"', '"+nama_user+"', '"+nama_toko+"', '"+nama_produk+"', "+harga+", "+jlh_produk+", "+total_harga+", '"+kode_kurir+"', "+ongkos_kirim+", "+jlh_total+")";
            
            String cekPrimary = "select * from transaksi where id_int= '"+id_transaksi+"'";
            
            Statement check = koneksi.createStatement();
            ResultSet data = check.executeQuery(cekPrimary);
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID transaksi sudah terdaftar");
                this.VAR_NAMA_USER = data.getString("nama_user");
                this.VAR_NAMA_TOKO = data.getString("nama_toko");
                this.VAR_NAMA_PRODUK = data.getString("nama_produk");
                this.VAR_HARGA = data.getDouble("harga");
                this.VAR_JLH_PRODUK = data.getInt("jlh_produk");
                this.VAR_TOTAL_HARGA = data.getDouble("total_harga");
                this.VAR_KODE_KURIR = data.getString("kode_kurir");
                this.VAR_ONGKOS_KIRIM = data.getDouble("ongkos_kirim");
                this.VAR_JLH_TOTAL = data.getDouble("jlh_total");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_NAMA_USER = null;
                this.VAR_NAMA_TOKO = null;
                this.VAR_NAMA_PRODUK = null;
                this.VAR_HARGA = 0;
                this.VAR_JLH_PRODUK = 0;
                this.VAR_TOTAL_HARGA = 0;
                this.VAR_KODE_KURIR = null;
                this.VAR_ONGKOS_KIRIM = 0;
                this.VAR_JLH_TOTAL = 0;
                Statement perintah = koneksi.createStatement();
                perintah.execute(sql);
                JOptionPane.showMessageDialog(null, "Data transaksi berhasil disimpan");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void simpanTransaksi02(String id_transaksi, String tgl_beli, String nama_user, String nama_toko, 
                                    String nama_produk, double harga, int jlh_produk, double total_harga, 
                                    String kode_kurir, double ongkos_kirim, double jlh_total){
        try {
            String sql = "insert into transaksi(id_int, tgl_beli, nama_user, nama_toko, nama_produk, harga, jlh_produk, total_harga, kode_kurir, ongkos_kirim, jlh_total) value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String cekPrimary = "select * from transaksi where id_int= ?";
            
            PreparedStatement check = koneksi.prepareStatement(cekPrimary);
            check.setString(1, id_transaksi);
            ResultSet data = check.executeQuery();
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID transaksi sudah terdaftar");
                this.VAR_NAMA_USER = data.getString("nama_user");
                this.VAR_NAMA_TOKO = data.getString("nama_toko");
                this.VAR_NAMA_PRODUK = data.getString("nama_produk");
                this.VAR_HARGA = data.getDouble("harga");
                this.VAR_JLH_PRODUK = data.getInt("jlh_produk");
                this.VAR_TOTAL_HARGA = data.getDouble("total_harga");
                this.VAR_KODE_KURIR = data.getString("kode_kurir");
                this.VAR_ONGKOS_KIRIM = data.getDouble("ongkos_kirim");
                this.VAR_JLH_TOTAL = data.getDouble("jlh_total");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_NAMA_USER = null;
                this.VAR_NAMA_TOKO = null;
                this.VAR_NAMA_PRODUK = null;
                this.VAR_HARGA = 0;
                this.VAR_JLH_PRODUK = 0;
                this.VAR_TOTAL_HARGA = 0;
                this.VAR_KODE_KURIR = null;
                this.VAR_ONGKOS_KIRIM = 0;
                this.VAR_JLH_TOTAL = 0;
                PreparedStatement perintah = koneksi.prepareStatement(sql);
                perintah.setString(1, id_transaksi);
                perintah.setString(2, tgl_beli);
                perintah.setString(3, nama_user);
                perintah.setString(4, nama_toko);
                perintah.setString(5, nama_produk);
                perintah.setDouble(6, harga);
                perintah.setInt(7, jlh_produk);
                perintah.setDouble(8, total_harga);
                perintah.setString(9, kode_kurir);
                perintah.setDouble(10, ongkos_kirim);
                perintah.setDouble(11, jlh_total);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data transaksi berhasil disimpan");
            }
            
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
     
    public void ubahTransaksi01(String id_transaksi, String tgl_beli, String nama_user, String nama_toko, 
                                 String nama_produk, double harga, int jlh_produk, double total_harga, 
                                 String kode_kurir, double ongkos_kirim, double jlh_total){
        try {
            String sql = "update transaksi set tgl_beli = '"+tgl_beli+"', nama_user = '"+nama_user+"', nama_toko = '"+nama_toko+"', nama_produk = '"+nama_produk+"'"
                    + ", harga = "+harga+", jlh_produk = "+jlh_produk+", total_harga = "+total_harga+", kode_kurir = '"+kode_kurir+"', ongkos_kirim = "+ongkos_kirim+", jlh_total = "+jlh_total+" where id_int = '"+id_transaksi+"'";
                   
            Statement perintah = koneksi.createStatement();
            perintah.execute(sql);
             JOptionPane.showMessageDialog(null, "Data transaksi berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
     
    public void ubahTransaksi02(String id_transaksi, String tgl_beli, String nama_user, String nama_toko, 
                                  String nama_produk, double harga, int jlh_produk, double total_harga, 
                                  String kode_kurir, double ongkos_kirim, double jlh_total){
        try {
            String sql = "update transaksi set tgl_beli =?, nama_user =?, nama_toko =?, nama_produk =?, harga =?, jlh_produk =?, total_harga =?, kode_kurir =?, ongkos_kirim =?, jlh_total =? where id_int =?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, tgl_beli);
            perintah.setString(2, nama_user);
            perintah.setString(3, nama_toko);
            perintah.setString(4, nama_produk);
            perintah.setDouble(5, harga);
            perintah.setInt(6, jlh_produk);
            perintah.setDouble(7, total_harga);
            perintah.setString(8, kode_kurir);
            perintah.setDouble(9, ongkos_kirim);
            perintah.setDouble(10, jlh_total);
            perintah.setString(11, id_transaksi);
            
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data transaksi berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
      
    public void hapusTransaksi01(String id_transaksi){
        try {
            String sql = "delete from transaksi where id_int = '"+id_transaksi+"'";
                   
            Statement perintah = koneksi.createStatement();
            perintah.execute(sql);
             JOptionPane.showMessageDialog(null, "Data transaksi berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
       
    public void hapusTransaksi02(String id_transaksi){
        try {
            String sql = "delete from transaksi where id_int = ?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, id_transaksi);
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data transaksi berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
       
    public void tampilDataTransaksi (JTable komponenTable, String SQL){
        try {
            Statement perintah = koneksi.createStatement();
            ResultSet data = perintah.executeQuery(SQL);
            ResultSetMetaData meta = data.getMetaData();
            int jumKolom = meta.getColumnCount();
            DefaultTableModel modelTable = new DefaultTableModel();
            modelTable.addColumn("ID Transaksi");
            modelTable.addColumn("Tgl Beli");
            modelTable.addColumn("Nama User");
            modelTable.addColumn("Nama Toko");
            modelTable.addColumn("Nama Produk");
            modelTable.addColumn("Harga");
            modelTable.addColumn("Jlh Produk");
            modelTable.addColumn("Total Harga");
            modelTable.addColumn("Kode Kurir");
            modelTable.addColumn("Ongkos Kirim");
            modelTable.addColumn("Jlh Total");
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
    
    public void cetakLaporanTransaksi(String fileLaporan, String SQL) {
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