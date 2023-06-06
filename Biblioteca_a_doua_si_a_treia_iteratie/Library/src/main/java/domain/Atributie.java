package domain;

public class Atributie implements  Entity<Integer>{
    private int id;
    private String title;
    private String catre;
    private String descriere;
    private int year_of_publication;

    public Atributie(){}

    public Atributie(int id, String title, String catre, String descriere, int year_of_publication) {
        this.id = id;
        this.title = title;
        this.catre = catre;
        this.descriere = descriere;
        this.year_of_publication = year_of_publication;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatre() {
        return catre;
    }

    public void setCatre(String catre) {
        this.catre = catre;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getYear_of_publication() {
        return year_of_publication;
    }

    public void setYear_of_publication(int year_of_publication) {
        this.year_of_publication = year_of_publication;
    }

    @Override
    public String toString() {
        return "Atributie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", catre='" + catre + '\'' +
                ", descriere='" + descriere + '\'' +
                ", year_of_publication=" + year_of_publication +
                '}';
    }
}
