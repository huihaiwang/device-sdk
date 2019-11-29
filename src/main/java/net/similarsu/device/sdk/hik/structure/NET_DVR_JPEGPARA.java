package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
@Structure.FieldOrder({
        "wPicSize"
        ,"wPicQuality"
})
public class NET_DVR_JPEGPARA extends Structure {
    public short    wPicSize;
    public short    wPicQuality;
    public NET_DVR_JPEGPARA() {
        super();
    }



    public NET_DVR_JPEGPARA(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_JPEGPARA implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_JPEGPARA implements Structure.ByValue {
    };
}
