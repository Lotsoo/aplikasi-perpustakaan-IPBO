package controller;

import dao.DAO_Anggota;
import dao.DAO_Interface;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Anggota;
import view.FormAnggota;

public class Controller_Anggota {
    
    FormAnggota form;
    DAO_Interface<Anggota> model;
    List<Anggota> list;
    String[] header;

    public Controller_Anggota(FormAnggota form) {
        this.form = form;
        model = new DAO_Anggota(); // Pastikan ini sesuai
        list = model.getAll();
        header = new String[]{"ID", "Nama", "Alamat", "Telepon", "Email", "Jenis Kelamin"};
        
        form.getTable().setShowGrid(true);
        form.getTable().setShowVerticalLines(true);
        form.getTable().setGridColor(Color.BLUE);
    }

    public void reset() {
        form.getTxtNama().setText("");
        form.getTxtAlamat().setText("");
        form.getTxtTelepon().setText("");
        form.getTxtEmail().setText("");
        form.getRbLaki().setSelected(true); // default
        isiTabel();
    }

    public void isiTabel() {
        list = model.getAll();

        DefaultTableModel tableModel = new DefaultTableModel(null, header) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[header.length];
        for (Anggota a : list) {
            data[0] = a.getIdAnggota();
            data[1] = a.getNama();
            data[2] = a.getAlamat();
            data[3] = a.getTelepon();
            data[4] = a.getEmail();
            data[5] = a.getJenisKelamin().toString();
            tableModel.addRow(data);
        }

        form.getTable().setModel(tableModel);
    }

    public void isiField(int row) {
        Anggota a = list.get(row);
        form.getTxtNama().setText(a.getNama());
        form.getTxtAlamat().setText(a.getAlamat());
        form.getTxtTelepon().setText(a.getTelepon());
        form.getTxtEmail().setText(a.getEmail());

        if (a.getJenisKelamin().toString().equals("L")) {
            form.getRbLaki().setSelected(true);
        } else {
            form.getRbPerempuan().setSelected(true);
        }
    }

    public void insert() {
        Anggota a = new Anggota();
        a.setNama(form.getTxtNama().getText());
        a.setAlamat(form.getTxtAlamat().getText());
        a.setTelepon(form.getTxtTelepon().getText());
        a.setEmail(form.getTxtEmail().getText());
        a.setJenisKelamin(form.getRbLaki().isSelected() ? Anggota.JenisKelamin.L : Anggota.JenisKelamin.P);

        model.insert(a);
    }

    public void update() {
        int row = form.getTable().getSelectedRow();
        if (row >= 0) {
            Anggota a = list.get(row);
            a.setNama(form.getTxtNama().getText());
            a.setAlamat(form.getTxtAlamat().getText());
            a.setTelepon(form.getTxtTelepon().getText());
            a.setEmail(form.getTxtEmail().getText());
            a.setJenisKelamin(form.getRbLaki().isSelected() ? Anggota.JenisKelamin.L : Anggota.JenisKelamin.P);

            model.update(a);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan diubah!");
        }
    }

    public void delete() {
        int row = form.getTable().getSelectedRow();
        if (row >= 0) {
            String id = String.valueOf(list.get(row).getIdAnggota());
            model.delete(id);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus!");
        }
    }

    public void isiTabelCari() {
        list = model.getCari(form.getTxtNama().getText().trim());

        DefaultTableModel tableModel = new DefaultTableModel(null, header);
        Object[] data = new Object[header.length];
        for (Anggota a : list) {
            data[0] = a.getIdAnggota();
            data[1] = a.getNama();
            data[2] = a.getAlamat();
            data[3] = a.getTelepon();
            data[4] = a.getEmail();
            data[5] = a.getJenisKelamin().toString();
            tableModel.addRow(data);
        }

        form.getTable().setModel(tableModel);
    }
}
