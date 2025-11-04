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

/**
 *
 * @author User
 */
public class crud_kurir {
    
    private String namaDB = "pertanian";
    private String url = "jdbc:mysql://localhost:3306/" + namaDB;
    private String username = "root";
    private String password = "";
    private Connection koneksi;
    public String VAR_NAMA_KURIR = null;
    public String VAR_KODE_KURIR = null;
    public double VAR_ONGKOS_KIRIM = 0;
    public boolean validasi = false;
    
    public crud_kurir(){
        try {
           Driver mysqldriver = new com.mysql.jdbc.Driver();
           DriverManager.registerDriver(mysqldriver);
           koneksi = DriverManager.getConnection(url,username,password);
           System.out.print("Berhasil dikoneksikan");
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
                
        }
    }
    
    public void simpanKurir01(String id_kurir, String nama_kurir, String kode_kurir, double ongkos_kirim){
        try {
            String sql = "insert into kurir(id_kurir_int, nama_kurir, kode_kurir, ongkos_kirim) "
                    + "values('"+id_kurir+"', '"+nama_kurir+"', '"+kode_kurir+"', "+ongkos_kirim+")";
            
            String cekPrimary = "select * from kurir where id_kurir_int= '"+id_kurir+"'";
            
            Statement check = koneksi.createStatement();
            ResultSet data = check.executeQuery(cekPrimary);
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID kurir sudah terdaftar");
                this.VAR_NAMA_KURIR = data.getString("nama_kurir");
                this.VAR_KODE_KURIR = data.getString("kode_kurir");
                this.VAR_ONGKOS_KIRIM = data.getDouble("ongkos_kirim");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_NAMA_KURIR = null;
                this.VAR_KODE_KURIR = null;
                this.VAR_ONGKOS_KIRIM = 0;
                Statement perintah = koneksi.createStatement();
                perintah.execute(sql);
                JOptionPane.showMessageDialog(null, "Data kurir berhasil disimpan");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
     public void simpanKurir02(String id_kurir, String nama_kurir, String kode_kurir, double ongkos_kirim){
        try {
            String sql = "insert into kurir(id_kurir_int, nama_kurir, kode_kurir, ongkos_kirim) value(?, ?, ?, ?)";
            String cekPrimary = "select * from kurir where id_kurir_int= ?";
            
            PreparedStatement check = koneksi.prepareStatement(cekPrimary);
            check.setString(1, id_kurir);
            ResultSet data = check.executeQuery();
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID kurir sudah terdaftar");
                this.VAR_NAMA_KURIR = data.getString("nama_kurir");
                this.VAR_KODE_KURIR = data.getString("kode_kurir");
                this.VAR_ONGKOS_KIRIM = data.getDouble("ongkos_kirim");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_NAMA_KURIR = null;
                this.VAR_KODE_KURIR = null;
                this.VAR_ONGKOS_KIRIM = 0;
                PreparedStatement perintah = koneksi.prepareStatement(sql);
                perintah.setString(1, id_kurir);
                perintah.setString(2, nama_kurir);
                perintah.setString(3, kode_kurir);
                perintah.setDouble(4, ongkos_kirim);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data kurir berhasil disimpan");
            }
            
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
     
     public void ubahKurir01(String id_kurir, String nama_kurir, String kode_kurir, double ongkos_kirim){
        try {
            String sql = "update kurir set nama_kurir = '"+nama_kurir+"', kode_kurir = '"+kode_kurir+"'"
                    + ", ongkos_kirim = "+ongkos_kirim+" where id_kurir_int = '"+id_kurir+"'";
                   
            Statement perintah = koneksi.createStatement();
            perintah.execute(sql);
             JOptionPane.showMessageDialog(null, "Data kurir berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
     
      public void ubahKurir02(String id_kurir, String nama_kurir, String kode_kurir, double ongkos_kirim){
        try {
            String sql = "update kurir set nama_kurir =?, kode_kurir =?, ongkos_kirim =? where id_kurir_int =?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, nama_kurir);
            perintah.setString(2, kode_kurir);
            perintah.setDouble(3, ongkos_kirim);
            perintah.setString(4, id_kurir);
            
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data kurir berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
      
       public void hapusKurir01(String id_kurir){
        try {
            String sql = "delete from kurir where id_kurir_int = '"+id_kurir+"'";
                   
            Statement perintah = koneksi.createStatement();
            perintah.execute(sql);
             JOptionPane.showMessageDialog(null, "Data kurir berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
       
       public void hapusKurir02(String id_kurir){
        try {
            String sql = "delete from kurir where id_kurir_int = ?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, id_kurir);
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data kurir berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
       
      public void tampilDataKurir (JTable komponenTable, String SQL){
          try {
              Statement perintah = koneksi.createStatement();
              ResultSet data = perintah.executeQuery(SQL);
              ResultSetMetaData meta = data.getMetaData();
              int jumKolom = meta.getColumnCount();
              DefaultTableModel modelTable = new DefaultTableModel();
              modelTable.addColumn("ID Kurir");
              modelTable.addColumn("Nama Kurir");
              modelTable.addColumn("Kode Kurir");
              modelTable.addColumn("Ongkos Kirim");
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
      
      public ArrayList<String> getKodeKurirList(){
          ArrayList<String> list = new ArrayList<>();
          try {
              String sql = "SELECT kode_kurir FROM kurir ORDER BY kode_kurir";
              Statement st = koneksi.createStatement();
              ResultSet rs = st.executeQuery(sql);
              while(rs.next()){
                  list.add(rs.getString("kode_kurir"));
              }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
          }
          return list;
      }
      
      public double getOngkirByKode(String kode_kurir){
          double ongkir = 0;
          try {
              String sql = "SELECT ongkos_kirim FROM kurir WHERE kode_kurir = ?";
              PreparedStatement ps = koneksi.prepareStatement(sql);
              ps.setString(1, kode_kurir);
              ResultSet rs = ps.executeQuery();
              if(rs.next()){
                  ongkir = rs.getDouble("ongkos_kirim");
              }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
          }
          return ongkir;
      }
}