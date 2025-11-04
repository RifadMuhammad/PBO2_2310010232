/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pbo2_2310010232;
import frame.frame_dashboard;

/**
 *
 * @author User
 */
public class PBO2_2310010232 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Menampilkan dashboard sebagai halaman utama aplikasi
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frame_dashboard().setVisible(true);
            }
        });
    }
    
}