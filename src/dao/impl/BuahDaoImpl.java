/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.impl;

import entity.Buah;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.BuahDao;

/**
 *
 * @author arya
 */
public class BuahDaoImpl implements BuahDao{
private Connection connection;

    public BuahDaoImpl(Connection connection){
        this.connection = connection;
    }
    public void save(Buah buah) {
        String SQL = "INSERT INTO buah(nama_file,lokasi_file,ukuran_file,resolusi_file,foto,jenis_buah) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, buah.getNama_file());
            statement.setString(2, buah.getLokasi_file());
            statement.setInt(3, buah.getUkuran_file());
            statement.setInt(4, buah.getResolusi_file());
            statement.setBytes(5, buah.getGambar_file());
            statement.setInt(6, buah.getJenis_buah());
            statement.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(BuahDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Buah buah) {
        String SQL = "UPDATE buah set nama_file=?,lokasi_file=?,ukuran_file=?,resolusi_file=?,foto=?,jenis_buah=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, buah.getNama_file());
            statement.setString(2, buah.getLokasi_file());
            statement.setInt(3, buah.getUkuran_file());
            statement.setInt(4, buah.getResolusi_file());
            statement.setBytes(5, buah.getGambar_file());
            statement.setInt(6, buah.getJenis_buah());
            statement.setLong(7, buah.getId_buah());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BuahDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(Buah buah) {
        String SQL="DELETE FROM buah WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setLong(1, buah.getId_buah());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BuahDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Buah> getAll() {
        String SQL = "SELECT id_buah,nama_file,lokasi_file,ukuran_file,resolusi_file,gambar_file,jenis_buah FROM buah";
        List<Buah> list = new ArrayList<Buah>();
        Buah buah = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                buah = new Buah();
                buah.setId_buah(rs.getLong(1));
                buah.setNama_file(rs.getString(2));
                buah.setLokasi_file(rs.getString(3));
                buah.setUkuran_file(rs.getInt(4));
                buah.setResolusi_file(rs.getInt(5));
                buah.setGambar_file(rs.getBytes(6));
                list.add(buah);
                buah=null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuahDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
