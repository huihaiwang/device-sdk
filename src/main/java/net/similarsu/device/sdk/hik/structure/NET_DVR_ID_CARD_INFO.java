package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import static net.similarsu.device.sdk.hik.library.HikLibrary.*;
@Structure.FieldOrder({
        "dwSize"
        ,"byName"
        ,"struBirth"
        ,"byAddr"
        ,"byIDNum"
        ,"byIssuingAuthority"
        ,"struStartDate"
        ,"struEndDate"
        ,"byTermOfValidity"
        ,"bySex"
        ,"byNation"
        ,"byRes"
})
public class NET_DVR_ID_CARD_INFO extends Structure {
    public int  dwSize;        //结构长度
    public byte[]  byName = new byte[MAX_ID_NAME_LEN];   //姓名
    public NET_DVR_DATE struBirth; //出生日期
    public byte[] byAddr= new byte[MAX_ID_ADDR_LEN];  //住址
    public byte[] byIDNum= new byte[MAX_ID_NUM_LEN];   //身份证号码
    public byte[] byIssuingAuthority=new byte[MAX_ID_ISSUING_AUTHORITY_LEN];  //签发机关
    public NET_DVR_DATE struStartDate;  //有效开始日期
    public NET_DVR_DATE struEndDate;  //有效截止日期
    public byte byTermOfValidity;  //是否长期有效， 0-否，1-是（有效截止日期无效）
    public byte   bySex;  //性别，1-男，2-女
    public byte  byNation;
    public byte[] byRes=new byte[101];

    public NET_DVR_ID_CARD_INFO() {
        super();
    }



    public NET_DVR_ID_CARD_INFO(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_ID_CARD_INFO implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_ID_CARD_INFO implements Structure.ByValue {
    };
}
