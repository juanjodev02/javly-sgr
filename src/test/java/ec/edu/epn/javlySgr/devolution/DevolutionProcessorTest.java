package ec.edu.epn.javlySgr.devolution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DevolutionProcessorTest {
    private DevolutionGateway gateway = null;

    @Before()
    public void setUp() {
        this.gateway = Mockito.mock(DevolutionGateway.class);
        Mockito.when(this.gateway.requestDevolution(Mockito.any())).thenReturn(new DevolutionResponse(DevolutionStatus.OK));
    }

    @Test()
    public void given_devolution_data_when_data_is_correct_then_ok () {
        DevolutionProcessor devolutionProcessor = new DevolutionProcessor(this.gateway);
        boolean response = devolutionProcessor.makeDevolution(500.00, new BankAccount("Pichincha", "2205466547"));
        Assert.assertTrue(response);
    }
}