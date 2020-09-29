package sample.entity;

        import java.sql.Date;
        import java.util.Objects;

public class ClientEntity {
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String gender;
    private String IIN;
    private String dateOfIIN;

    public ClientEntity(){

    }

    public ClientEntity(String firstName, String lastName, String address, String phoneNumber, String gender, String IIN, String dateOfIIN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.IIN = IIN;
        this.dateOfIIN = dateOfIIN;
    }

    public ClientEntity(Integer id, String firstName, String lastName, String address, String phoneNumber, String gender, String IIN, String dateOfIIN) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.IIN = IIN;
        this.dateOfIIN = dateOfIIN;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIIN() {
        return IIN;
    }

    public void setIIN(String IIN) {
        this.IIN = IIN;
    }

    public String getDateOfIIN() {
        return dateOfIIN;
    }

    public void setDateOfIIN(String dateOfIIN) {
        this.dateOfIIN = dateOfIIN;
    }
}
