package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
@Structure.FieldOrder({
        "wYear"
        ,"byMonth"
        ,"byDay"
})
public class NET_DVR_DATE extends Structure {
    public short wYear;        //年
    public byte byMonth;        //月
    public byte byDay;        //日

    public NET_DVR_DATE(){
        super();
    }

    public NET_DVR_DATE(Pointer pointer){
        super(pointer);
    }

    public static class ByReference extends NET_DVR_DATE implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_DATE implements Structure.ByValue {
    };

}
