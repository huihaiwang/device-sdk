package net.similarsu.device.sdk.hik.union;

import com.sun.jna.Union;
import net.similarsu.device.sdk.hik.library.HikLibrary;
import net.similarsu.device.sdk.hik.structure.NET_DVR_EVENT_LINKAGE_INFO;

public class NET_DVR_EVETN_CARD_LINKAGE_UNION extends Union {
    public byte[]                           byCardNo=new byte[HikLibrary.ACS_CARD_NO_LEN];          //卡号
    public NET_DVR_EVENT_LINKAGE_INFO struEventLinkage;                   //事件联动时参数
    public byte[]                           byMACAddr=new byte[HikLibrary.MACADDR_LEN];          //物理MAC地址
    public byte[]  byEmployeeNo=new byte[HikLibrary.NET_SDK_EMPLOYEE_NO_LEN]; //工号（人员ID）
}
