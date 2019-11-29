package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import net.similarsu.device.sdk.hik.union.NET_DVR_DEL_FACE_PARAM_MODE_UNION;

@Structure.FieldOrder(
        {
                "dwSize"
                ,"byMode"
                ,"byRes1"
                ,"struProcessMode"
                ,"byRes"
        }
)
public class NET_DVR_FACE_PARAM_CTRL extends Structure {
    public int       dwSize;
    public byte  byMode;          //删除方式，0-按卡号方式删除，1-按读卡器删除
    public byte[]  byRes1 = new byte[3];        //保留
    public NET_DVR_DEL_FACE_PARAM_MODE_UNION struProcessMode;  //处理方式
    public byte[]  byRes = new byte[64];          //保留

    public NET_DVR_FACE_PARAM_CTRL() {
        super();
    }



    public NET_DVR_FACE_PARAM_CTRL(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_FACE_PARAM_CTRL implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_FACE_PARAM_CTRL implements Structure.ByValue {
    };
}
