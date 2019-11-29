package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({
        "dwSize"
        ,"byLevel"
        ,"byAlarmInfoType"
        ,"byRetAlarmTypeV40"
        ,"byRetDevInfoVersion"
        ,"byRetVQDAlarmType"
        ,"byFaceAlarmDetection"
        ,"bySupport"
        ,"byBrokenNetHttp"
        ,"wTaskNo"
        ,"byDeployType"
        ,"byRes1"
        ,"byAlarmTypeURL"
        ,"byCustomCtrl"

})
public class NET_DVR_SETUPALARM_PARAM extends Structure{
    public int dwSize;
    public byte  byLevel; //布防优先级，0-一等级（高），1-二等级（中），2-三等级（低）
    public byte  byAlarmInfoType; //上传报警信息类型（抓拍机支持），0-老报警信息（NET_DVR_PLATE_RESULT），1-新报警信息(NET_ITS_PLATE_RESULT)2012-9-28
    public byte  byRetAlarmTypeV40; //0--返回NET_DVR_ALARMINFO_V30或NET_DVR_ALARMINFO, 1--设备支持NET_DVR_ALARMINFO_V40则返回NET_DVR_ALARMINFO_V40，不支持则返回NET_DVR_ALARMINFO_V30或NET_DVR_ALARMINFO
    public byte  byRetDevInfoVersion; //CVR上传报警信息回调结构体版本号 0-COMM_ALARM_DEVICE， 1-COMM_ALARM_DEVICE_V40
    public byte  byRetVQDAlarmType; //VQD报警上传类型，0-上传报报警NET_DVR_VQD_DIAGNOSE_INFO，1-上传报警NET_DVR_VQD_ALARM
    //1-表示人脸侦测报警扩展(INTER_FACE_DETECTION),0-表示原先支持结构(INTER_FACESNAP_RESULT)
    public byte  byFaceAlarmDetection;
    //Bit0- 表示二级布防是否上传图片: 0-上传，1-不上传
    //Bit1- 表示开启数据上传确认机制；0-不开启，1-开启
    //Bit6- 表示雷达检测报警(eventType:radarDetection)是否开启实时上传；0-不开启，1-开启（用于web插件实时显示雷达目标轨迹）
    public byte  bySupport;
    //断网续传类型 
    //bit0-车牌检测（IPC） （0-不续传，1-续传）
    //bit1-客流统计（IPC）  （0-不续传，1-续传）
    //bit2-热度图统计（IPC） （0-不续传，1-续传）
    //bit3-人脸抓拍（IPC） （0-不续传，1-续传）
    //bit4-人脸对比（IPC） （0-不续传，1-续传）
    public byte  byBrokenNetHttp;
    public short  wTaskNo;    //任务处理号 和 (上传数据NET_DVR_VEHICLE_RECOG_RESULT中的字段dwTaskNo对应 同时 下发任务结构 NET_DVR_VEHICLE_RECOG_COND中的字段dwTaskNo对应)
    public byte  byDeployType;    //布防类型：0-客户端布防，1-实时布防
    public byte[]  byRes1=new byte[3];
    public byte  byAlarmTypeURL;//bit0-表示人脸抓拍报警上传（INTER_FACESNAP_RESULT）；0-表示二进制传输，1-表示URL传输（设备支持的情况下，设备支持能力根据具体报警能力集判断,同时设备需要支持URL的相关服务，当前是”云存储“）
    //bit1-表示EVENT_JSON中图片数据长传类型；0-表示二进制传输，1-表示URL传输（设备支持的情况下，设备支持能力根据具体报警能力集判断）
    //bit2 - 人脸比对(报警类型为COMM_SNAP_MATCH_ALARM)中图片数据上传类型：0 - 二进制传输，1 - URL传输
    //bit3 - 行为分析(报警类型为COMM_ALARM_RULE)中图片数据上传类型：0 - 二进制传输，1 - URL传输，本字段设备是否支持，对应软硬件能力集中<isSupportBehaviorUploadByCloudStorageURL>节点是否返回且为true
    public byte  byCustomCtrl;//Bit0- 表示支持副驾驶人脸子图上传: 0-不上传,1-上传

    public NET_DVR_SETUPALARM_PARAM() {
        super();
    }



    public NET_DVR_SETUPALARM_PARAM(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_SETUPALARM_PARAM implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_SETUPALARM_PARAM implements Structure.ByValue {
    };


}
