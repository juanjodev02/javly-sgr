package ec.edu.epn.javlySgr.service;

public class Service {
    private  ServiceType serviceType;
    private String observation;

    public Service(ServiceType serviceType, String observation) {
        this.serviceType = serviceType;
        this.observation = observation;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public String getObservation() {
        return observation;
    }
}
