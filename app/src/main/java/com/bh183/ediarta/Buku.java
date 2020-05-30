package com.bh183.ediarta;



public class Buku {
    private int idBuku;
    private String judul;
    private String penulis;
    private String penerbit;
    private String genre;
    private String tahun;
    private String gambar;
    private String sinopsis;


    public Buku(int idBuku, String judul, String penulis, String penerbit, String genre, String tahun, String gambar, String sinopsis) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.genre = genre;
        this.tahun = tahun;
        this.gambar = gambar;
        this.sinopsis = sinopsis;

    }


    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) { this.gambar = gambar; }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
