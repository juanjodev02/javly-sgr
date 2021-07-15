package ec.edu.epn.javlySgr.devolution;

public class DevolutionResponse {
    private DevolutionStatus devolutionStatus;

    public DevolutionResponse(DevolutionStatus devolutionStatus) {
        this.devolutionStatus = devolutionStatus;
    }

    public DevolutionStatus getDevolutionStatus() {
        return devolutionStatus;
    }
}
