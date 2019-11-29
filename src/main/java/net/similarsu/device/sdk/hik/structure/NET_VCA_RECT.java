package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({
        "fX","fY","fWidth","fHeight"
})
public class NET_VCA_RECT extends Structure {
    public float fX;               //边界框左上角点的X轴坐标, 0.001~1
    public float fY;               //边界框左上角点的Y轴坐标, 0.001~1
    public float fWidth;           //边界框的宽度, 0.001~1
    public float fHeight;
    public NET_VCA_RECT() {
        super();
    }



    public NET_VCA_RECT(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_VCA_RECT implements Structure.ByReference {
    };

    public static class ByValue extends NET_VCA_RECT implements Structure.ByValue {
    };
}
