package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({
        "dwSize","dwMatchNo","byGroupNum","byPicNo"
        ,"bySecondCam","byFeaturePicNo","byDriveChan","byVehicleType"
        ,"byDetSceneID","byVehicleAttribute","wIllegalType","byIllegalSubType"
        ,"byPostPicNo","byChanIndex","wSpeedLimit","byChanIndexEx"
        ,"byVehiclePositionControl","struPlateInfo","struVehicleInfo","byMonitoringSiteID"
        ,"byDeviceID","byDir","byDetectType","byRelaLaneDirectionType"
        ,"byCarDirectionType","dwCustomIllegalType","pIllegalInfoBuf","byIllegalFromatType"
        ,"byPendant","byDataAnalysis","byYellowLabelCar"
        ,"byDangerousVehicles","byPilotSafebelt","byCopilotSafebelt","byPilotSunVisor"
        ,"byCopilotSunVisor","byPilotCall","byBarrierGateCtrlType","byAlarmDataType"
        ,"struSnapFirstPicTime","dwIllegalTime","dwPicNum","struPicInfo"
})
public class NET_ITS_PLATE_RESULT extends Structure {
    public int   dwSize;        //结构长度
    public int   dwMatchNo;        //匹配序号,由(车辆序号,数据类型,车道号)组成匹配码
    public byte    byGroupNum;    //图片组数量（一辆过车相机多次抓拍的数量，代表一组图片的总数，用于延时匹配数据）
    public byte    byPicNo;        //连拍的图片序号（接收到图片组数量后，表示接收完成;接收超时不足图片组数量时，根据需要保留或删除）

    public byte    bySecondCam;    //是否第二相机抓拍（如远近景抓拍的远景相机，或前后抓拍的后相机，特殊项目中会用到）
    public byte    byFeaturePicNo; //闯红灯电警，取第几张图作为特写图,0xff-表示不取
    public byte    byDriveChan;        //触发车道号
    public byte    byVehicleType;     //车辆类型，参考VTR_RESULT

    public byte    byDetSceneID;//检测场景号[1,4], IPC默认是0
    //车辆属性，按位表示，0- 无附加属性(普通车)，bit1- 黄标车(类似年检的标志)，bit2- 危险品车辆，值：0- 否，1- 是
    //该节点已不再使用,使用下面的byYellowLabelCar和byDangerousVehicles判断是否黄标车和危险品车
    public byte    byVehicleAttribute;
    public short    wIllegalType;       //违章类型采用国标定义
    public byte[]    byIllegalSubType = new byte[8];   //违章子类型

    public byte    byPostPicNo;    //违章时取第几张图片作为卡口图,0xff-表示不取
    //通道号(有效，报警通道号和所在设备上传报警通道号一致，在后端和所接入的 通道号一致)
    public byte    byChanIndex;
    public short    wSpeedLimit;        //限速上限（超速时有效）
    public byte    byChanIndexEx;      //byChanIndexEx*256+byChanIndex表示真实通道号。

    public byte    byVehiclePositionControl;  /*车辆位置布控标志,0~为普通车牌识别报警,1~为车辆位置布控触发报警
                                        (即通过PUT /ISAPI/Traffic/channels/<ID>/vehiclePositionControl?format=json触发)*/
    public NET_DVR_PLATE_INFO  struPlateInfo;     //车牌信息结构
    public NET_DVR_VEHICLE_INFO struVehicleInfo;    //车辆信息
    public byte[]    byMonitoringSiteID = new byte[48];        //监测点编号
    public byte[]    byDeviceID = new byte[48];                //设备编号
    public byte    byDir;            //监测方向，1-上行（反向），2-下行(正向)，3-双向，4-由东向西，5-由南向北,6-由西向东，7-由北向南，8-其它
    public byte    byDetectType;    //检测方式,1-地感触发，2-视频触发，3-多帧识别，4-雷达触发
    //关联车道方向类型，参考ITC_RELA_LANE_DIRECTION_TYPE
    //该参数为车道方向参数，与关联车道号对应，确保车道唯一性。
    public byte    byRelaLaneDirectionType;
    public byte    byCarDirectionType; //车辆具体行驶的方向，0表示从上往下，1表示从下往上（根据实际车辆的行驶方向来的区分）
    //当wIllegalType参数为空时，使用该参数。若wIllegalType参数为有值时，以wIllegalType参数为准，该参数无效。
    public int   dwCustomIllegalType; //违章类型定义(用户自定义)
    /*为0~数字格式时，为老的违章类型，wIllegalType、dwCustomIllegalType参数生效，赋值国标违法代码。
      为1~字符格式时，pIllegalInfoBuf参数生效。老的违章类型，wIllegalType、dwCustomIllegalType参数依然赋值国标违法代码*/
    public Pointer pIllegalInfoBuf;    //违法代码字符信息结构体指针；指向NET_ITS_ILLEGAL_INFO
    public byte    byIllegalFromatType; //违章信息格式类型； 0~数字格式， 1~字符格式
    public byte    byPendant;// 0-表示未知,1-车窗有悬挂物，2-车窗无悬挂物
    public byte    byDataAnalysis;            //0-数据未分析, 1-数据已分析
    public byte    byYellowLabelCar;        //0-表示未知, 1-非黄标车,2-黄标车
    public byte    byDangerousVehicles;    //0-表示未知, 1-非危险品车,2-危险品车
    //以下字段包含Pilot字符均为主驾驶，含Copilot字符均为副驾驶
    public byte    byPilotSafebelt;//0-表示未知,1-系安全带,2-不系安全带
    public byte    byCopilotSafebelt;//0-表示未知,1-系安全带,2-不系安全带
    public byte    byPilotSunVisor;//0-表示未知,1-不打开遮阳板,2-打开遮阳板
    public byte    byCopilotSunVisor;//0-表示未知, 1-不打开遮阳板,2-打开遮阳板
    public byte    byPilotCall;// 0-表示未知, 1-不打电话,2-打电话
    //0-开闸，1-未开闸 (专用于历史数据中相机根据黑白名单匹配后，是否开闸成功的标志)
    public byte    byBarrierGateCtrlType;
    public byte    byAlarmDataType;//0-实时数据，1-历史数据
    public NET_DVR_TIME_V30  struSnapFirstPicTime;//端点时间(ms)（抓拍第一张图片的时间）
    public int   dwIllegalTime;//违法持续时间（ms） = 抓拍最后一张图片的时间 - 抓拍第一张图片的时间
    public int   dwPicNum;        //图片数量（与picGroupNum不同，代表本条信息附带的图片数量，图片信息由struVehicleInfoEx定义    
    public NET_ITS_PICTURE_INFO[] struPicInfo = new NET_ITS_PICTURE_INFO[6];         //图片信息,单张回调，最多6张图，由序号区分

    public NET_ITS_PLATE_RESULT(){
        super();
    }

    public NET_ITS_PLATE_RESULT(Pointer pointer){
        super(pointer);
    }

    public static class ByReference extends NET_ITS_PLATE_RESULT implements Structure.ByReference {
    };

    public static class ByValue extends NET_ITS_PLATE_RESULT implements Structure.ByValue {
    };
}
