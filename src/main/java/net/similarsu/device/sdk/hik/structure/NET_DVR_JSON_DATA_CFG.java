package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * @author whh
 * @date 2019/11/28
 */
@Structure.FieldOrder({"dwSize","lpJsonData","dwJsonDataSize","lpPicData","dwPicDataSize",
        "dwInfraredFacePicSize","lpInfraredFacePicBuffer","byRes"})
public class NET_DVR_JSON_DATA_CFG extends Structure {
    public int dwSize;//结构体大小
    public Pointer lpJsonData; //JSON报文（Return（JSON）报文或ResponseStatus（JSON）报文）
    public int dwJsonDataSize; //JSON报文大小
    public Pointer lpPicData; //图片内容（当JSON报文为当ResponseStatus（JSON）报文时，该字段无意义；当Inbound Data（JSON）报文中没有faceURL时，该字段需要带上二进制图片内容）
    public int dwPicDataSize; //图片内容大小（大小限制为200k以内）
    public int dwInfraredFacePicSize; //红外人脸图片数据大小，等于0时，代表无人脸图片数据(当JSON报文为当ResponseStatus（JSON）报文时，该字段无意义；当Inbound Data（JSON）报文中没有infraredFaceURL时，该字段需要带上二进制图片内容）
    public String lpInfraredFacePicBuffer; //红外人脸图片数据缓存
    public byte[] byRes = new byte[248]; //保留

    public NET_DVR_JSON_DATA_CFG() {
        this.dwSize = size();
    }
}
