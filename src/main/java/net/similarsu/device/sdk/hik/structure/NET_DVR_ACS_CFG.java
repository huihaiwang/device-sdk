package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
@Structure.FieldOrder({
        "dwSize"
        ,"byRS485Backup"
        ,"byShowCapPic"
        ,"byShowCardNo"
        ,"byShowUserInfo"
        ,"byOverlayUserInfo"
        ,"byVoicePrompt"
        ,"byUploadCapPic"
        ,"bySaveCapPic"
        ,"byInputCardNo"
        ,"byEnableWifiDetect"
        ,"byEnable3G4G"
        ,"byProtocol"
        ,"byRes"

})
public class NET_DVR_ACS_CFG extends Structure {
    public int           dwSize;            //结构体大小
    public byte            byRS485Backup;  //是否启用下行RS485通信备份功能，0-不启用，1-启用
    public byte            byShowCapPic;    //是否显示抓拍图片， 0-不显示，1-显示
    public byte            byShowCardNo;    //是否显示卡号，0-不显示，1-显示
    public byte            byShowUserInfo;  //是否显示用户信息，0-不显示，1-显示
    public byte            byOverlayUserInfo;//是否叠加用户信息，0-不叠加，1-叠加
    public byte            byVoicePrompt;  //是否启用语音提示，0-不启用，1-启用
    public byte             byUploadCapPic;     //联动抓拍是否上传图片，0-不上传，1-上传
    public byte             bySaveCapPic;    //是否保存抓拍图片，0-不保存，1-保存
    public byte            byInputCardNo;  //是否是否允许按键输入卡号，0-不允许，1-允许
    public byte            byEnableWifiDetect;  //是否启动wifi探针，0-不启动，1-启动
    public byte            byEnable3G4G;  //3G4G使能，0-不使能，1-使能
    public byte            byProtocol;//读卡器通信协议类型，0-私有协议（默认），1-OSDP协议
    public byte[] byRes= new byte[500];

    public NET_DVR_ACS_CFG(){
        super();
    }

    public NET_DVR_ACS_CFG(Pointer pointer){
        super(pointer);
    }

    public static class ByReference extends NET_DVR_ACS_CFG implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_ACS_CFG implements Structure.ByValue {
    };
}
