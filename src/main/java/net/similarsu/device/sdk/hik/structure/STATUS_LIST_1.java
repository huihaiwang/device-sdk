package net.similarsu.device.sdk.hik.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({
        "status_1"
})
public class STATUS_LIST_1 extends Structure {
    public int status_1 = 0;

    public STATUS_LIST_1(){
        super();
    }

    public STATUS_LIST_1(Pointer pointer){
        super(pointer);
    }

    public static class ByReference extends STATUS_LIST_1 implements Structure.ByReference {
    };

    public static class ByValue extends STATUS_LIST_1 implements Structure.ByValue {
    };
}
