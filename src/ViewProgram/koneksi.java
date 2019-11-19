package ViewProgram;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
public class koneksi {
    Connection con;
    public Connection getConnection(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/deteksibuah","root","");
            //JOptionPane.showMessageDialog(null, "Konesi ke Database BERHASIL");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Konesi ke Database GAGAL");
        }
        return con;
    }
}