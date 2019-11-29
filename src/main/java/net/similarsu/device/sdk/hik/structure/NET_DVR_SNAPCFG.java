package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import net.similarsu.device.sdk.hik.library.HikLibrary;

@Structure.FieldOrder(
        {
                "dwSize"
                ,"byRelatedDriveWay"
                ,"bySnapTimes"
                ,"wSnapWaitTime"
                ,"wIntervalTime"
                ,"dwSnapVehicleNum"
                ,"struJpegPara"
                ,"byRes2"

        }
)
public class NET_DVR_SNAPCFG extends Structure {
    public int   dwSize;
    public byte    byRelatedDriveWay;//触发IO关联的车道号
    public byte     bySnapTimes; //线圈抓拍次数，0-不抓拍，非0-连拍次数，目前最大5次  
    public short    wSnapWaitTime;  //抓拍等待时间，单位ms，取值范围[0,60000]
    public short[]   wIntervalTime= new short[HikLibrary.MAX_INTERVAL_NUM];//连拍间隔时间，ms
    public int   dwSnapVehicleNum; //抓拍车辆序号。
    public NET_DVR_JPEGPARA  struJpegPara;//抓拍图片参数
    public byte[]    byRes2=new byte[16];//保留字节

    public NET_DVR_SNAPCFG() {
        super();
    }



    public NET_DVR_SNAPCFG(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_SNAPCFG implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_SNAPCFG implements Structure.ByValue {
    };
}
