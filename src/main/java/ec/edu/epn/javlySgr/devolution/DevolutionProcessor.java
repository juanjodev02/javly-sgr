package ec.edu.epn.javlySgr.devolution;

public class DevolutionProcessor {
    private DevolutionGateway devolutionGateway;

    public DevolutionProcessor(DevolutionGateway devolutionGateway) {
        this.devolutionGateway = devolutionGateway;
    }

    public boolean makeDevolution(double amount, BankAccount bankAccount) {
        DevolutionRequest devolutionRequest = new DevolutionRequest(amount, bankAccount);
        DevolutionResponse devolutionResponse = this.devolutionGateway.requestDevolution(devolutionRequest);
        return devolutionResponse.getDevolutionStatus() == DevolutionStatus.OK;
    }
}
