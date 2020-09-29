package sample.entity;

public class CountryTourEntity {
    private int idTour;
    private int idCountry;

    public CountryTourEntity() {

    }

    public CountryTourEntity(int idTour, int idCountry) {
        this.idTour = idTour;
        this.idCountry = idCountry;
    }

    public int getIdTour() {
        return idTour;
    }

    public void setIdTour(int idTour) {
        this.idTour = idTour;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }
}
