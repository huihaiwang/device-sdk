package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
@Structure.FieldOrder({"dwYear","dwMonth","dwDay","dwHour","dwMinute","dwSecond"})
public class NET_DVR_TIME extends Structure {
    public int dwYear;        //年
    public int dwMonth;        //月
    public int dwDay;        //日
    public int dwHour;        //时
    public int dwMinute;        //分
    public int dwSecond;        //秒
    public NET_DVR_TIME() {
        super();
    }

    public NET_DVR_TIME(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_TIME implements Structure.ByReference {
    }

    public static class ByValue extends NET_DVR_TIME implements Structure.ByValue {
    }
}
