package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import net.similarsu.device.sdk.hik.library.HikLibrary;

@Structure.FieldOrder({
        "dwSize"
        ,"dwChannel"
        ,"dwDataIndex"
        ,"sLicense"
        ,"byListType"
        ,"byPlateType"
        ,"byPlateColor"
        ,"byRes"
        ,"sCardNo"
        ,"struStartTime"
        ,"struStopTime"
        ,"sOperateIndex"
        ,"byRes1"
})
public class NET_DVR_VEHICLE_CONTROL_LIST_INFO extends Structure {
    public int  dwSize;
    public int  dwChannel;//通道号0xff - 全部通道（ITC 默认是1）
    public int  dwDataIndex;//数据流水号（平台维护的数据唯一值，客户端操作的时候，该值不会起效。该值主要用于数据增量同步）
    public byte[]   sLicense=new byte[HikLibrary.MAX_LICENSE_LEN]; //车牌号码
    public byte   byListType;//名单属性（黑白名单）0-白名单，1-黑名单
    public byte   byPlateType;    //车牌类型
    public byte   byPlateColor;    //车牌颜色
    public byte[]   byRes=new byte[21];
    public byte[]   sCardNo=new byte[HikLibrary.MAX_CARDNO_LEN]; // 卡号
    public NET_DVR_TIME_V30  struStartTime;//有效开始时间
    public NET_DVR_TIME_V30  struStopTime;//有效结束时间
    //操作数（平台同步表流水号不会重复，用于增量更新，代表同步到同步表的某一条记录了，存在相机内存，重启后会清0）2014-03-03
    public byte[]        sOperateIndex=new byte[HikLibrary.MAX_OPERATE_INDEX_LEN];
    public byte[]  byRes1=new byte[224]; // 保留字节

    public NET_DVR_VEHICLE_CONTROL_LIST_INFO() {
        super();
    }



    public NET_DVR_VEHICLE_CONTROL_LIST_INFO(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_VEHICLE_CONTROL_LIST_INFO implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_VEHICLE_CONTROL_LIST_INFO implements Structure.ByValue {
    };
}
