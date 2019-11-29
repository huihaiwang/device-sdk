package net.similarsu.device.sdk.hik.union;

import com.sun.jna.Union;
import net.similarsu.device.sdk.hik.structure.NET_DVR_FACE_PARAM_BYCARD;
import net.similarsu.device.sdk.hik.structure.NET_DVR_FACE_PARAM_BYREADER;

public class NET_DVR_DEL_FACE_PARAM_MODE_UNION extends Union {
    public byte[]   uLen = new byte[588];   //联合体长度
    public NET_DVR_FACE_PARAM_BYCARD struByCard;     //按卡号的方式删除
    public NET_DVR_FACE_PARAM_BYREADER struByReader;   //按读卡器的方式删除
}
