package utils;

import domain.OrderState;

public class StatusValidator {

    public boolean isChangePossible(OrderState currentState, OrderState newState){

        boolean result = false;
        if(currentState == OrderState.ORDERED && (newState == OrderState.PREPARED || newState == OrderState.CANCELED) ){
            result = true;
        } else if(currentState == OrderState.PREPARED && (newState == OrderState.ON_TRANSPORT || newState == OrderState.CANCELED)){
            result = true;
        } else if(currentState == OrderState.ON_TRANSPORT && newState == OrderState.FINISHED) {
            result = true;
        }
     return result;
    }
}
