package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * dwSize
 * 结构体大小
 * dwFaceTemplate1Size
 * 人脸模板1数据大小，等于0时表示无人脸模板1数据
 * pFaceTemplate1Buffer
 * 人脸模板1数据缓存（不大于2.5k）
 * dwFaceTemplate2Size
 * 人脸模板2数据大小，等于0时表示无人脸模板2数据
 * pFaceTemplate2Buffer
 * 人脸模板2数据缓存（不大于2.5K）
 * dwFacePicSize
 * 人脸图片数据大小，等于0时表示无人脸图片数据
 * pFacePicBuffer
 * 人脸图片数据缓存
 * byFaceQuality1
 * 模板1对应的人脸质量，取值范围：1~100
 * byFaceQuality2
 * 模板2对应的人脸质量，取值范围：1~100
 * byCaptureProgress
 * 采集进度，目前只有两种进度值：0- 未采集到人脸，100- 采集到人脸（只有在进度为100时，才解析人脸信息）
 * byRes
 * 保留，置为0
 * @author whh
 * @date 2019/11/27
 */
@Structure.FieldOrder({"dwSize","dwFaceTemplate1Size","pFaceTemplate1Buffer","dwFaceTemplate2Size","pFaceTemplate2Buffer",
        "dwFacePicSize","pFacePicBuffer","byFaceQuality1","byFaceQuality2","byCaptureProgress","byRes"})
public class NET_DVR_CAPTURE_FACE_CFG extends Structure {
    public int    dwSize;
    public int    dwFaceTemplate1Size;
    public Pointer     pFaceTemplate1Buffer;
    public int    dwFaceTemplate2Size;
    public Pointer     pFaceTemplate2Buffer;
    public int    dwFacePicSize;
    public Pointer pFacePicBuffer;
    public byte     byFaceQuality1;
    public byte     byFaceQuality2;
    public byte     byCaptureProgress;
    public byte[]     byRes = new byte[125];

    public NET_DVR_CAPTURE_FACE_CFG() {
        this.dwSize = size();
    }
}
