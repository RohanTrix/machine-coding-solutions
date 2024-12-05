package payment_recommendation.models.user;

public class UserContext {
    private final DeviceContext deviceContext;
    public UserContext(DeviceContext deviceContext) {
        this.deviceContext = deviceContext;
    }

    public boolean isUpiEnabled() {
        return deviceContext.isUPIEnabled();
    }
}
