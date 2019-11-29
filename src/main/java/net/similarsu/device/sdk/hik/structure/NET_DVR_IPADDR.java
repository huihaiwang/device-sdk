package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
@Structure.FieldOrder({
        "sIpV4"
        ,"byIPv6"
})
public class NET_DVR_IPADDR extends Structure {
    public byte[]    sIpV4 = new byte[16];                        /* IPv4地址 */
    public byte[]    byIPv6 = new byte[128];                        /* 保留 */

    public NET_DVR_IPADDR() {
        super();
    }



    public NET_DVR_IPADDR(Pointer peer) {
        super(peer);
    }

    public static class ByReference extends NET_DVR_IPADDR implements Structure.ByReference {
    };

    public static class ByValue extends NET_DVR_IPADDR implements Structure.ByValue {
    };
}
