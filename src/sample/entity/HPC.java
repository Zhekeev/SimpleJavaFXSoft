package sample.entity;

public class HPC {
    private String name;
    private String lastName;
    private int idContract;
    private int employeeID;

    public HPC() {
    }

    public HPC(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public HPC(String name, String lastName, int idContract) {
        this.name = name;
        this.lastName = lastName;
        this.idContract = idContract;
    }
}
