/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import koneksi.KoneksiDb;
import model.Anggota;

/**
 *
 * @author Mahasiswa
 */
public class DAO_Anggota implements DAO_Interface<Anggota> {

    Connection connection;

    public DAO_Anggota() {
        connection = KoneksiDb.getKoneksi();
    }

    String INSERT = "INSERT INTO anggota(nama, alamat, telepon, email, jenis_kelamin) VALUES(?,?,?,?,?)";
    String UPDATE = "UPDATE anggota SET nama=?, alamat=?, telepon=?, email=?, jenis_kelamin=? WHERE id_anggota=?";
    String DELETE = "DELETE FROM anggota WHERE id_anggota=?";
    String SELECT = "SELECT * FROM anggota";
    String CARI = "SELECT * FROM anggota WHERE nama LIKE ?";

    @Override
    public void insert(Anggota anggota) {
         try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, anggota.getNama());
            ps.setString(2, anggota.getAlamat());
            ps.setString(3, anggota.getTelepon());
            ps.setString(4, anggota.getEmail());
            ps.setString(5, anggota.getJenisKelamin().name());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data!");
        }
    }

   @Override
    public void update(Anggota anggota) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, anggota.getNama());
            ps.setString(2, anggota.getAlamat());
            ps.setString(3, anggota.getTelepon());
            ps.setString(4, anggota.getEmail());
            ps.setString(5, anggota.getJenisKelamin().name());
            ps.setInt(6, anggota.getIdAnggota());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mengubah data!");
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menghapus data!");
        }
    }

    @Override
    public List<Anggota> getAll() {
        List<Anggota> list = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT)) {
            while (rs.next()) {
                Anggota a = new Anggota();
                a.setIdAnggota(rs.getInt("id_anggota"));
                a.setNama(rs.getString("nama"));
                a.setAlamat(rs.getString("alamat"));
                a.setTelepon(rs.getString("telepon"));
                a.setEmail(rs.getString("email"));
                a.setJenisKelamin(Anggota.JenisKelamin.valueOf(rs.getString("jenis_kelamin")));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Anggota> getCari(String keyword) {
        List<Anggota> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(CARI)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Anggota a = new Anggota();
                a.setIdAnggota(rs.getInt("id_anggota"));
                a.setNama(rs.getString("nama"));
                a.setAlamat(rs.getString("alamat"));
                a.setTelepon(rs.getString("telepon"));
                a.setEmail(rs.getString("email"));
                a.setJenisKelamin(Anggota.JenisKelamin.valueOf(rs.getString("jenis_kelamin")));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
