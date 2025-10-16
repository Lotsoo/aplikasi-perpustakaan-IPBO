/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahasiswa
 */
public class KoneksiDb {

    static Connection c;
    static String driver, database, user, pass;

    public static Connection getKoneksi() {
        if (c == null) {
            try {
                driver = "com.mysql.cj.jdbc.Driver";
                database = "jdbc:mysql://localhost:3306/db_perpus";
                user = "lotso";
                pass = "123";

                Class.forName(driver);
                c = DriverManager.getConnection(database, user, pass);
                JOptionPane.showMessageDialog(null, "Koneksi Berhasil!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Koneksi Tidak Berhasil!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            }
        }
        return c;
    }
}
