package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import static net.similarsu.device.sdk.hik.library.HikLibrary.*;

@Structure.FieldOrder({
        "byUserIDValid"
        ,"bySerialValid"
        ,"byVersionValid"
        ,"byDeviceNameValid"
        ,"byMacAddrValid"
        ,"byLinkPortValid"
        ,"byDeviceIPValid"
        ,"bySocketIPValid"
        ,"lUserID"
        ,"sSerialNumber"
        ,"dwDeviceVersion"
        ,"sDeviceName"
        ,"byMacAddr"
        ,"wLinkPort"
        ,"sDeviceIP"
        ,"sSocketIP"
        ,"byIpProtocol"
        ,"byRes1"
        ,"bJSONBroken"
        ,"wSocketPort"
        ,"byRes2"
})
public class NET_DVR_ALARMER extends Structure {
    public byte byUserIDValid;                 /* userid是否有效 0-无效，1-有效 */
    public byte bySerialValid;                 /* 序列号是否有效 0-无效，1-有效 */
    public byte byVersionValid;                /* 版本号是否有效 0-无效，1-有效 */
    public byte byDeviceNameValid;             /* 设备名字是否有效 0-无效，1-有效 */
    public byte byMacAddrValid;                /* MAC地址是否有效 0-无效，1-有效 */
    public byte byLinkPortValid;               /* login端口是否有效 0-无效，1-有效 */
    public byte byDeviceIPValid;               /* 设备IP是否有效 0-无效，1-有效 */
    public byte bySocketIPValid;               /* socket ip是否有效 0-无效，1-有效 */
    public int lUserID;                       /* NET_DVR_Login()返回值, 布防时有效 */
    public byte[] sSerialNumber= new byte[SERIALNO_LEN];    /* 序列号 */
    public int dwDeviceVersion;                /* 版本信息 高16位表示主版本，低16位表示次版本*/
    public byte[] sDeviceName=new byte[NAME_LEN];            /* 设备名字 */
    public byte[] byMacAddr=new byte[MACADDR_LEN];        /* MAC地址 */
    public short wLinkPort;                     /* link port */
    public byte[] sDeviceIP=new byte[128];                /* IP地址 */
    public byte[] sSocketIP=new byte[128];                /* 报警主动上传时的socket IP地址 */
    public byte byIpProtocol;                  /* Ip协议 0-IPV4, 1-IPV6 */
    public byte[] byRes1=new byte[2];
    public byte bJSONBroken;                   //JSON断网续传标志。0：不续传；1：续传
    public short wSocketPort;
    public byte[] byRes2=new byte[6];

    public NET_DVR_ALARMER(){
        super();
    }

    public NET_DVR_ALARMER(Pointer pointer){
        super(pointer);
    }

    public static class ByReference extends NET_DVR_ALARMER implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_ALARMER implements Structure.ByValue {
    };
}
