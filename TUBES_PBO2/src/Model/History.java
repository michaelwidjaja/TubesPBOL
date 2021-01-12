package Model;

import java.util.Date;

public class History {
    private int id;
    private String tanggal;
    private int id_user;
    private String winlose;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getWinlose() {
        return winlose;
    }

    public void setWinlose(String winlose) {
        this.winlose = winlose;
    }
}


