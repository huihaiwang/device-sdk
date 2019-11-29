package net.similarsu.device.sdk.hik.callback;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import net.similarsu.device.sdk.hik.structure.NET_DVR_ALARMER;

public interface MSGCallBack_V31 extends Callback {
    boolean invoke(int lCommand, NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser);
}
