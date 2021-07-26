package ccom.intelsys;

import java.io.*;
import org.jpos.iso.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.packager.*;
import org.jpos.util.*;

public class server implements ISORequestListener {

  public static void main(String[] args) throws Exception {
    Logger logger = new Logger();
    logger.addListener(new SimpleLogListener(System.out));
    ServerChannel channel = new XMLChannel(new XMLPackager());
    ((LogSource) channel).setLogger(logger, "channel");
    ISOServer server = new ISOServer(8000, channel, null);
    server.setLogger(logger, "server");
    server.addISORequestListener(new server());
    new Thread(server).start();
  }

  public boolean process(ISOSource source, ISOMsg m) {
    try {
      m.setResponseMTI();
      m.set(39, "00");
      source.send(m);
    } catch (ISOException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }
  // public interface MUX {
  //   public ISOMsg request(ISOMsg m, long timeout) throws ISOException;
  //   public boolean isConnected();

  //   MUX mux = (MUX) NameRegister.get ("mux.mymultiplexer");

  //   public void request(
  //     ISOMsg m = new ISOMsg(),
  //     long timeout = new long(),
  //     ISOResponseListener r = new ISOResponseListener(),
  //     Object handBack = new Object()
  //     )

  //   // ISOMsg m = new ISOMsg();
  //   m.setResponseMTI();
  //   m.setMTI ("0800");
  //   m.set (11, "000001");
  //   m.set (41, "00000001");
  // ISOMsg response = mux.request (m, 30000);
  // if (response != null) {
  //   // system.out.println ("Respon telah diberikan");
  // } else {
  //   // system.out.println("Kirim ulang request anda");
  // }

  //     throws ISOException;
  // }
}
