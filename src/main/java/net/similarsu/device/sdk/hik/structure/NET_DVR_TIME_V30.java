package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({
        "wYear","byMonth","byDay","byHour"
        ,"byMinute","bySecond","byISO8601","wMilliSec"
        ,"cTimeDifferenceH","cTimeDifferenceM"
})
public class NET_DVR_TIME_V30 extends Structure {
    public short wYear;
    public byte byMonth;
    public byte byDay;
    public byte byHour;
    public byte byMinute;
    public byte bySecond;
    public byte    byISO8601;      /*是否是8601的时间格式，即时差字段是否有效0-时差无效，年月日时分秒为设备本地时间 1-时差有效 */
    public short	wMilliSec;       //毫秒，精度不够，默认为0
    public byte    cTimeDifferenceH;  		//与UTC的时差（小时），-12 ... +14，+表示东区, byISO8601为1时有效
    public byte    cTimeDifferenceM;
    public NET_DVR_TIME_V30() {
        super();
    }



    public NET_DVR_TIME_V30(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_TIME_V30 implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_TIME_V30 implements Structure.ByValue {
    };

}
