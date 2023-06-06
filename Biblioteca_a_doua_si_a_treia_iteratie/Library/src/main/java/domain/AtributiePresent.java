package domain;

public class AtributiePresent implements Entity<Integer>{
    private int id;
    private int voluntar;
    private int atributie;
    private int returned;
    private int days;

    public AtributiePresent(){}

    public AtributiePresent(int voluntar, int atributie, int returned, int days) {
        this.voluntar = voluntar;
        this.atributie = atributie;
        this.returned = returned;
        this.days = days;
    }

    public AtributiePresent(int id, int voluntar, int atributie, int returned, int days) {
        setId(id);
        this.voluntar = voluntar;
        this.atributie = atributie;
        this.returned = returned;
        this.days = days;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoluntar() {
        return voluntar;
    }

    public void setVoluntar(int voluntar) {
        this.voluntar = voluntar;
    }

    public int getAtributie() {
        return atributie;
    }

    public void setAtributie(int atributie) {
        this.atributie = atributie;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }
}
