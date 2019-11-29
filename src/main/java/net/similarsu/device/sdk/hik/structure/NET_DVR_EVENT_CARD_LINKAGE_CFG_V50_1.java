package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
@Structure.FieldOrder({
        "cfg_1"
})
public class NET_DVR_EVENT_CARD_LINKAGE_CFG_V50_1 extends Structure {
    public NET_DVR_EVENT_CARD_LINKAGE_CFG_V50 cfg_1;

    public NET_DVR_EVENT_CARD_LINKAGE_CFG_V50_1(){
        super();
    }

    public NET_DVR_EVENT_CARD_LINKAGE_CFG_V50_1(Pointer pointer){
        super(pointer);
    }

    public static class ByReference extends NET_DVR_EVENT_CARD_LINKAGE_CFG_V50_1 implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_EVENT_CARD_LINKAGE_CFG_V50_1 implements Structure.ByValue {
    };
}
