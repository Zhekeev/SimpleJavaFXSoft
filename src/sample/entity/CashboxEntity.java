package sample.entity;

        import java.math.BigInteger;
        import java.sql.Date;
        import java.util.Objects;

public class CashboxEntity {
    private Integer idEmployee;
    private Integer idClient;
    private Integer idTour;
    private Integer amount;
    private String date;

    public CashboxEntity(){

    }

    public CashboxEntity(Integer idEmployee, Integer idClient, Integer idTour, Integer amount, String date) {
        this.idEmployee = idEmployee;
        this.idClient = idClient;
        this.idTour = idTour;
        this.amount = amount;
        this.date = date;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
