package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import net.similarsu.device.sdk.hik.library.HikLibrary;

@Structure.FieldOrder({
        "byCardNo"
        ,"byEnableCardReader"
        ,"byFaceID"
        ,"byRes1"
})
public class NET_DVR_FACE_PARAM_BYCARD extends Structure {
    public byte[]  byCardNo = new byte[HikLibrary.ACS_CARD_NO_LEN]; //人脸关联的卡号
    public byte[]  byEnableCardReader=new byte[HikLibrary.MAX_CARD_READER_NUM_512];  //人脸的读卡器信息，按数组表示
    public byte[]  byFaceID= new byte[HikLibrary.MAX_FACE_NUM];        //需要删除的人脸编号，按数组下标，值表示0-不删除，1-删除该人脸
    public byte[]  byRes1=new byte[42];          //保留

    public NET_DVR_FACE_PARAM_BYCARD() {
        super();
    }



    public NET_DVR_FACE_PARAM_BYCARD(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_FACE_PARAM_BYCARD implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_FACE_PARAM_BYCARD implements Structure.ByValue {
    };
}
