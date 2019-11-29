package net.similarsu.device.sdk.hik.callback;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface LoginResultCallBack extends Callback {
    void invoke(int lUserID, int dwResult, Pointer lpDeviceinfo, Pointer pUser);
}
