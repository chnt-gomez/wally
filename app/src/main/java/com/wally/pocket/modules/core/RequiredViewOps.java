package com.wally.pocket.modules.core;

/**
 * Created by MAV1GA on 08/08/2017.
 */
public interface RequiredViewOps {

    void onOperationSuccess();
    void onOperationSuccess(int messageRes);
    void onOperationError();
    void onOperationError(int messageRes);
    void showMessage(int messageRes);
    @Deprecated
    void showMessage(String message);
    void onReload();
}
