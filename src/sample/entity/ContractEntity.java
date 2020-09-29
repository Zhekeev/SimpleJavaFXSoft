package sample.entity;



public class ContractEntity {
    private Integer id;
    private Integer idEmployee;
    private Integer idClient;
    private Integer idTour;
    private String tourStartDate;
    private String tourFinishDate;

    public ContractEntity(){

    }

    public ContractEntity(Integer id, Integer idClient,Integer idEmployee , Integer idTour, String tourStartDate, String tourFinishDate) {
        this.id = id;
        this.idClient = idClient;
        this.idEmployee = idEmployee;
        this.idTour = idTour;
        this.tourStartDate = tourStartDate;
        this.tourFinishDate = tourFinishDate;
    }
    public ContractEntity(Integer idClient, Integer idEmployee, Integer idTour, String tourStartDate, String tourFinishDate) {
        this.idClient = idClient;
        this.idEmployee = idEmployee;
        this.idTour = idTour;
        this.tourStartDate = tourStartDate;
        this.tourFinishDate = tourFinishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdTour() {
        return idTour;
    }

    public void setIdTour(Integer idTour) {
        this.idTour = idTour;
    }

    public String getTourStartDate() {
        return tourStartDate;
    }

    public void setTourStartDate(String tourStartDate) {
        this.tourStartDate = tourStartDate;
    }

    public String getTourFinishDate() {
        return tourFinishDate;
    }

    public void setTourFinishDate(String tourFinishDate) {
        this.tourFinishDate = tourFinishDate;
    }

}
