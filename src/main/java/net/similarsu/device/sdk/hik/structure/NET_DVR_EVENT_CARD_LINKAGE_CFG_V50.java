package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import net.similarsu.device.sdk.hik.union.NET_DVR_EVETN_CARD_LINKAGE_UNION;
import net.similarsu.device.sdk.hik.library.HikLibrary;

@Structure.FieldOrder(
        {
                "dwSize"
                ,"byProMode"
                ,"byRes1"
                ,"dwEventSourceID"
                ,"uLinkageInfo"
                ,"byAlarmout"
                ,"byRes2"
                ,"byOpenDoor"
                ,"byCloseDoor"
                ,"byNormalOpen"
                ,"byNormalClose"
                ,"byMainDevBuzzer"
                ,"byCapturePic"
                ,"byRecordVideo"
                ,"byRes3"
                ,"byReaderBuzzer"
                ,"byAlarmOutClose"
                ,"byAlarmInSetup"
                ,"byAlarmInClose"
                ,"byRes"

        }
)
public class NET_DVR_EVENT_CARD_LINKAGE_CFG_V50 extends Structure {
    public int            dwSize;    //结构体大小
    public byte              byProMode;                          //联动方式，0-事件，1-卡号, 2-MAC地址
    public byte[]           byRes1= new byte[3];
    public int         dwEventSourceID;                    //事件源ID，当主类型为设备事件时无效， 当主类型是门事件时为门编号;当主类型为读卡器事件时，为读卡器ID;当为报警输入事件时为防区报警输入ID或事件报警输入ID。0xffffffff表示联动全部
    public NET_DVR_EVETN_CARD_LINKAGE_UNION uLinkageInfo;  //联动方式参数
    public byte[]           byAlarmout=new byte[HikLibrary.MAX_ALARMHOST_ALARMOUT_NUM];            //关联的报警输出号，按位表示，为0表示不关联，为1表示关联
    public byte[]           byRes2=new byte[32];                         //保留
    public byte[]           byOpenDoor=new byte[HikLibrary.MAX_DOOR_NUM_256];     //按位表示,是否联动开门，0-不联动，1-联动
    public byte[]           byCloseDoor=new byte[HikLibrary.MAX_DOOR_NUM_256];    //按位表示,是否联动关门，0-不联动，1-联动
    public byte[]           byNormalOpen=new byte[HikLibrary.MAX_DOOR_NUM_256];   //按位表示,是否联动常开，0-不联动，1-联动
    public byte[]           byNormalClose=new byte[HikLibrary.MAX_DOOR_NUM_256];  //按位表示,是否联动常关，0-不联动，1-联动
    public byte           byMainDevBuzzer;                    //主机蜂鸣器   0-不联动，1-联动输出
    public byte           byCapturePic;                           //是否联动抓拍，0-不联动抓拍，1-联动抓拍
    public byte           byRecordVideo;                          //是否联动录像，0-不联动录像，1-联动录像
    public byte[]           byRes3=new byte[29];                         //保留
    public byte[]           byReaderBuzzer=new byte[HikLibrary.MAX_CARD_READER_NUM_512]; //联动读卡器蜂鸣器，按位表示，0-不联动，1-联动
    public byte[]           byAlarmOutClose=new byte[HikLibrary.MAX_ALARMHOST_ALARMOUT_NUM];            //关联报警输出关闭，按字节表示，为0表示不关联，为1表示关联
    public byte[]           byAlarmInSetup=new byte[HikLibrary.MAX_ALARMHOST_ALARMIN_NUM];  //关联防区布防，按字节表示，为0表示不关联，为1表示关联
    public byte[]           byAlarmInClose=new byte[HikLibrary.MAX_ALARMHOST_ALARMIN_NUM];  //关联防区撤防，按字节表示，为0表示不关联，为1表示关联
    public byte[]           byRes=new byte[500];

    public NET_DVR_EVENT_CARD_LINKAGE_CFG_V50() {
        super();
    }



    public NET_DVR_EVENT_CARD_LINKAGE_CFG_V50(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_EVENT_CARD_LINKAGE_CFG_V50 implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_EVENT_CARD_LINKAGE_CFG_V50 implements Structure.ByValue {
    };
}
