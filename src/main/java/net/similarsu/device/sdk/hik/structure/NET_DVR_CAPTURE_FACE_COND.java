package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Structure;

/**
 * @author whh
 * @date 2019/11/27
 */
@Structure.FieldOrder({"dwSize","byRes"})
public class NET_DVR_CAPTURE_FACE_COND extends Structure {
    public int dwSize;// 结构体大小
    public byte[] byRes = new byte[128];// 保留，置为0

    public NET_DVR_CAPTURE_FACE_COND() {
        this.dwSize = size();
    }
}
