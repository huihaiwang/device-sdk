package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import static net.similarsu.device.sdk.hik.library.HikLibrary.MAX_NAMELEN;

@Structure.FieldOrder({
        "dwSize"
        ,"struIDCardCfg"
        ,"dwMajor"
        ,"dwMinor"
        ,"struSwipeTime"
        ,"byNetUser"
        ,"struRemoteHostAddr"
        ,"dwCardReaderNo"
        ,"dwDoorNo"
        ,"dwPicDataLen"
        ,"pPicData"
        ,"byCardType"
        ,"byDeviceNo"
        ,"byRes2"
        ,"dwFingerPrintDataLen"
        ,"pFingerPrintData"
        ,"dwCapturePicDataLen"
        ,"pCapturePicData"
        ,"byRes"
})
public class NET_DVR_ID_CARD_INFO_ALARM extends Structure{
    public int  dwSize;        //结构长度
    public NET_DVR_ID_CARD_INFO    struIDCardCfg ;//身份证信息
    public int dwMajor; //报警主类型，参考宏定义
    public int dwMinor; //报警次类型，参考宏定义
    public NET_DVR_TIME_V30  struSwipeTime; //时间
    public byte[]    byNetUser = new byte[MAX_NAMELEN] ;//网络操作的用户名
    public NET_DVR_IPADDR    struRemoteHostAddr ;//远程主机地址
    public int dwCardReaderNo; //读卡器编号，为0无效
    public int dwDoorNo; //门编号，为0无效
    public int dwPicDataLen;   //图片数据大小，不为0是表示后面带数据
    public Pointer pPicData;
    public byte byCardType; //卡类型，1-普通卡，2-残疾人卡，3-黑名单卡，4-巡更卡，5-胁迫卡，6-超级卡，7-来宾卡，8-解除卡，为0无效
    public byte byDeviceNo;                             // 设备编号，为0时无效（有效范围1-255）
    public byte[] byRes2 = new byte[2];
    public int dwFingerPrintDataLen;                  // 指纹数据大小，不为0是表示后面带数据
    public Pointer pFingerPrintData;
    public int dwCapturePicDataLen;                   // 抓拍图片数据大小，不为0是表示后面带数据
    public Pointer pCapturePicData;
    public byte[] byRes=new byte[188];

    public NET_DVR_ID_CARD_INFO_ALARM() {
        super();
    }



    public NET_DVR_ID_CARD_INFO_ALARM(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_ID_CARD_INFO_ALARM implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_ID_CARD_INFO_ALARM implements Structure.ByValue {
    };
}
