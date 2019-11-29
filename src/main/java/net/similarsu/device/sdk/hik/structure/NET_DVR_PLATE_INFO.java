package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({
        "byPlateType","byColor","byBright","byLicenseLen","byEntireBelieve","byRegion","byCountry","byArea","byPlateSize"
        ,"byAddInfoFlag","byRes","pAddInfoBuffer","sPlateCategory","dwXmlLen","pXmlBuf","struPlateRect","sLicense","byBelieve"
})
public class NET_DVR_PLATE_INFO extends Structure {
    public byte  byPlateType;                    //车牌类型
    public byte  byColor;                        //车牌颜色
    public byte  byBright;                        //车牌亮度
    public byte  byLicenseLen;                    //车牌字符个数
    public byte  byEntireBelieve;                //整个车牌的置信度，-100
    public byte  byRegion;                       // 区域索引值 0-保留，1-欧洲(EU)，2-俄语区域(ER)，3-欧洲&俄罗斯(EU&CIS) ,4-中东(ME),0xff-所有
    public byte  byCountry;                      // 国家索引值，参照枚举COUNTRY_INDEX（不支持"COUNTRY_ALL = 0xff, //ALL  全部"）
    public byte  byArea;                         //区域（省份），各国家内部区域枚举，阿联酋参照 EMI_AREA
    public byte  byPlateSize;                    //车牌尺寸，0~未知，1~long, 2~short(中东车牌使用)
    /*附加信息标识（即是否有NET_DVR_VEHICLE_ADDINFO结构体）,0-无附加信息, 1-有附加信息。*/
    public byte  byAddInfoFlag;
    public byte[]  byRes = new byte[6];					//保留
    public Pointer pAddInfoBuffer;
    public byte[]  sPlateCategory = new byte[8];//车牌附加信息, 即中东车牌中车牌号码旁边的小字信息，(目前只有中东地区支持)
    public int dwXmlLen;                        //XML报警信息长度
    public Pointer pXmlBuf;                      // XML报警信息指针,报警类型为 COMM_ITS_PLATE_RESUL时有效，其XML对应到EventNotificationAlert XML Block
    public NET_VCA_RECT    struPlateRect;        //车牌位置
    public byte[] sLicense = new byte[16];        //车牌号码,注：中东车牌需求把小字也纳入车牌号码，小字和车牌号中间用空格分隔
    public byte[] byBelieve = new byte[16];

    public NET_DVR_PLATE_INFO() {
        super();
    }



    public NET_DVR_PLATE_INFO(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_PLATE_INFO implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_PLATE_INFO implements Structure.ByValue {
    };
}
