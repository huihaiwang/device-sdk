package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({
        "cond_1"
})
public class NET_DVR_EVENT_CARD_LINKAGE_COND_1 extends Structure {
    public NET_DVR_EVENT_CARD_LINKAGE_COND cond_1;

    public NET_DVR_EVENT_CARD_LINKAGE_COND_1(){
        super();
    }

    public NET_DVR_EVENT_CARD_LINKAGE_COND_1(Pointer pointer){
        super(pointer);
    }

    public static class ByReference extends NET_DVR_EVENT_CARD_LINKAGE_COND_1 implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_EVENT_CARD_LINKAGE_COND_1 implements Structure.ByValue {
    };
}
