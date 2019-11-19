/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.Date;


/**
 *
 * @author arya
 */
public class Buah{
    private Long id_buah;
    private String nama;
    private String nama_file;
    private String lokasi_file;
    private int ukuran_file;
    private int resolusi_file;
    private byte[] gambar_file;
    private int jenis_buah;

    public Long getId_buah() {
        return id_buah;
    }

    public void setId_buah(Long id_buah) {
        this.id_buah = id_buah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama_file() {
        return nama_file;
    }

    public void setNama_file(String nama_file) {
        this.nama_file = nama_file;
    }

    public String getLokasi_file() {
        return lokasi_file;
    }

    public void setLokasi_file(String lokasi_file) {
        this.lokasi_file = lokasi_file;
    }

    public int getUkuran_file() {
        return ukuran_file;
    }

    public void setUkuran_file(int ukuran_file) {
        this.ukuran_file = ukuran_file;
    }

    public int getResolusi_file() {
        return resolusi_file;
    }

    public void setResolusi_file(int resolusi_file) {
        this.resolusi_file = resolusi_file;
    }

    public byte[] getGambar_file() {
        return gambar_file;
    }

    public void setGambar_file(byte[] gambar_file) {
        this.gambar_file = gambar_file;
    }

    public int getJenis_buah() {
        return jenis_buah;
    }

    public void setJenis_buah(int jenis_buah) {
        this.jenis_buah = jenis_buah;
    }


    
    
    
}
