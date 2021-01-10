package Model;

public class Enemy {
    private int Id;
    private String Name;
    private int HP;
    private int Attack;
    private int element;



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return Attack;
    }

    public void setAttack(int attack) {
        Attack = attack;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }


    public void kenaSerang(int n){
        this.HP -= n;
    }
}
