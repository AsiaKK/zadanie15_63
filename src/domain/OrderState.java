package domain;

public enum OrderState {

    ORDERED("zlozone"),
    PREPARED("przygotowane do wysylki"),
    ON_TRANSPORT("w transporcie"),
    FINISHED("zrealizowane"),
    CANCELED("anulowane");

    private String stateDescription;

    OrderState(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    public String getStateDescription() {
        return stateDescription;
    }
}
