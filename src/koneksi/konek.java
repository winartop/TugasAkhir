/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Driver;
/**
 *
 * @author o
 */
public class konek {
     static Connection koneksi;
 
    public static Connection getConnection() {
            try {
                koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/deteksibuah","root","");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Koneksi Gagal! " + e);
                System.exit(0);
            }
            return koneksi;
    }
}
