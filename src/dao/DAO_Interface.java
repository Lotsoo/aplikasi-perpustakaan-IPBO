/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import java.util.List;

/**
 *
 * @author Mahasiswa
 */
public interface DAO_Interface<A> {
   public void insert(A data);

    public void update(A data);

    public void delete(String key);

    public List<A> getAll();

    public List<A> getCari(String keyword);
}
