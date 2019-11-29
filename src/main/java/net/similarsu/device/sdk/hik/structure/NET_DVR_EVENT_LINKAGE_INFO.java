package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({
        "wMainEventType"
        ,"wSubEventType"
        ,"byRes"

})
public class NET_DVR_EVENT_LINKAGE_INFO extends Structure {
    public short          wMainEventType;                     //事件主类型，0-设备事件，1-报警输入事件，2-门事件，3-读卡器事件
    public short          wSubEventType;                      //事件次类型
    public byte[]          byRes = new byte[28];

    public NET_DVR_EVENT_LINKAGE_INFO() {
        super();
    }



    public NET_DVR_EVENT_LINKAGE_INFO(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_EVENT_LINKAGE_INFO implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_EVENT_LINKAGE_INFO implements Structure.ByValue {
    };
}
