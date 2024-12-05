package payment_recommendation.models.user;

public class DeviceContext {

    private final boolean isUPIEnabled;
    public DeviceContext(boolean isUPIEnabled) {
        this.isUPIEnabled = isUPIEnabled;
    }
    public boolean isUPIEnabled() {
        return isUPIEnabled;
    }
}
