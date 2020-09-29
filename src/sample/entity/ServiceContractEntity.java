package sample.entity;

public class ServiceContractEntity {
    private int serviceId;
    private int contractId;

    public ServiceContractEntity(int contractId, int serviceId) {
        this.contractId = contractId;
        this.serviceId = serviceId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }
}
