package net.similarsu.device.sdk.hik.callback;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;


public interface RemoteConfigCallback extends Callback {
    void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData);
}
