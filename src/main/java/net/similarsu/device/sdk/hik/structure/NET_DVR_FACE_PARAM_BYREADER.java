package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import static net.similarsu.device.sdk.hik.library.HikLibrary.ACS_CARD_NO_LEN;

@Structure.FieldOrder({
        "dwCardReaderNo"
        ,"byClearAllCard"
        ,"byRes1"
        ,"byCardNo"
        ,"byRes"
})
public class NET_DVR_FACE_PARAM_BYREADER extends Structure {
    public int dwCardReaderNo;  //按值表示，人脸读卡器编号
    public byte  byClearAllCard;  //是否删除所有卡的人脸信息，0-按卡号删除人脸信息，1-删除所有卡的人脸信息
    public byte[]  byRes1=new byte[3];       //保留
    public byte[]  byCardNo=new byte[ACS_CARD_NO_LEN]; //人脸关联的卡号
    public byte[]  byRes=new byte[548];          //保留

    public NET_DVR_FACE_PARAM_BYREADER() {
        super();
    }



    public NET_DVR_FACE_PARAM_BYREADER(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_FACE_PARAM_BYREADER implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_FACE_PARAM_BYREADER implements Structure.ByValue {
    };
}
