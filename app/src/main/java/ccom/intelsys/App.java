package ccom.intelsys;

import org.jpos.iso.*;
import org.jpos.util.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.packager.*;
public class App {
        public String getGreeting(){
                return( "Hello World" );

        }

    public static void main(String[] args) throws ISOException {
        String hexmsg = "080000200000008000001234563132333435363738";//args[0];
        // convert hex string to byte array
        byte[] bmsg =ISOUtil.hex2byte(hexmsg);
        ISOMsg m = new ISOMsg();
        // set packager, change ISO87BPackager for the matching one.
        m.setPackager(new ISO87BPackager());
        //unpack the message using the packager
        m.unpack(bmsg);
        //dump the message to standar output
        m.dump(System.out, "");
    }
}