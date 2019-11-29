package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
@Structure.FieldOrder(
        {
                "dwSize"
                ,"dwEventID"
                ,"wLocalControllerID"
                ,"byRes"
        }
)
public class NET_DVR_EVENT_CARD_LINKAGE_COND extends Structure {
    public int dwSize;
    public int dwEventID; //事件ID
    public short wLocalControllerID; //就地控制器序号[1,64]
    public byte[] byRes = new byte[106];

    public NET_DVR_EVENT_CARD_LINKAGE_COND() {
        super();
    }



    public NET_DVR_EVENT_CARD_LINKAGE_COND(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_EVENT_CARD_LINKAGE_COND implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_EVENT_CARD_LINKAGE_COND implements Structure.ByValue {
    };
}
