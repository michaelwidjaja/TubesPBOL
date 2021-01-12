//Nama:Weddy Alvino
//NRP:1872063
package Model;

public class Monster {
    private int id;
    private String name;
    private int hp;
    private int hpsementara;
    private int att;
    private int element;
    private int User_idpengguna;

    public int getHpsementara() {
        return hpsementara;
    }

    public void setHpsementara(int hpsementara) {
        this.hpsementara = hpsementara;
    }


    public int getUser_idpengguna() {
        return User_idpengguna;
    }

    public void setUser_idpengguna(int user_idpengguna) {
        User_idpengguna = user_idpengguna;
    }

    public void kenaSerang(int n){
        this.hpsementara -= n;
    }



    @Override
    public String toString() {
        return  id+" " + name ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }


}
