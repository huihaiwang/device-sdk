package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({
        "dwDataLen","byType","byDataType","byCloseUpType","byPicRecogMode","dwRedLightTime","byAbsTime","struPlateRect"
        ,"struPlateRecgRect","pBuffer","dwUTCTime","byCompatibleAblity","byTimeDiffFlag","cTimeDifferenceH","cTimeDifferenceM"
        ,"byRes2"
})
public class NET_ITS_PICTURE_INFO extends Structure {
    public int   dwDataLen;            //媒体数据长度
    public byte    byType;            // 0:车牌图;1:车辆图;2:合成图; 3:特写图;4:二直图;5:码流;6:人脸子图(主驾驶);7:人脸子图(副驾驶)成图;8-非机动车;9-行人;10-称重原始裸数据;11-目标图;12-主驾驶室图 ;13-副驾驶室图;14-人脸图抠小图
    // 0-数据直接上传; 1-云存储服务器URL(3.7Ver)原先的图片数据变成URL数据，图片长度变成URL长度
    public byte    byDataType;
    public byte    byCloseUpType;//特写图类型，0-保留,1-非机动车,2-行人
    public byte    byPicRecogMode;  //图片背向识别：0-正向车牌识别，1-背向识别(尾牌识别) ；
    public int   dwRedLightTime;        //经过的红灯时间  （s）
    public byte[]    byAbsTime =new byte[32];        //绝对时间点,yyyymmddhhmmssxxx,e.g.20090810235959999  最后三位为毫秒数
    NET_VCA_RECT   struPlateRect;    //车牌位置,当byType为8-非机动车;9-行人时，表示人体坐标
    NET_VCA_RECT  struPlateRecgRect;   //牌识区域坐标，当图片类型为12-主驾驶室图13-副驾驶室图是，该坐标为驾驶员坐标
    public Pointer   pBuffer;    //数据指针
    public int   dwUTCTime;//UTC时间定义
    public byte    byCompatibleAblity; //兼容能力字段 0表示无效，1表示有效; bit0-表示dwUTCTime字段有效
    public byte    byTimeDiffFlag;      /*时差字段是否有效  0-时差无效， 1-时差有效 */
    public byte    cTimeDifferenceH;         /*与UTC的时差（小时），-12 ... +14， +表示东区,，byTimeDiffFlag为1时有效*/
    public byte    cTimeDifferenceM;      	/*与UTC的时差（分钟），-30, 30, 45， +表示东区，byTimeDiffFlag为1时有效*/
    public byte[]   byRes2= new byte[4];           //保留

    public NET_ITS_PICTURE_INFO() {
        super();
    }



    public NET_ITS_PICTURE_INFO(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_ITS_PICTURE_INFO implements Structure.ByReference {
    };

    public static class ByValue extends NET_ITS_PICTURE_INFO implements Structure.ByValue {
    };
}
