//Nama:Weddy Alvino
//NRP:1872063
package Model;

public class Monster {
    private int id;
    private String name;
    private int hp;
    private int att;
    private int acc;
    private int element;

    public String getNamaelement() {
        return namaelement;
    }

    public void setNamaelement(String namaelement) {
        this.namaelement = namaelement;
    }

    private String  namaelement;

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

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }


}
