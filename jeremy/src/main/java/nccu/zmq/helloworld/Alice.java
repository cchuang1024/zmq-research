package nccu.zmq.helloworld;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Alice implements Runnable {
    private static final Logger logger = LogManager.getLogger(Alice.class.getName());

    public static synchronized Alice getAlice() {
        Protocol protocol = Protocol.getDefaultRep();
        return new Alice(protocol);
    }

    private Protocol protocol;

    private Alice(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void run() {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket socket = context.createSocket(protocol.type);
            socket.bind(protocol.getUrl());
            int count = 0;

            while (!Thread.currentThread().isInterrupted()) {
                byte[] reply = socket.recv(0);
                logger.info(String.format("Received: [%s]", new String(reply, protocol.charset)));

                String response = String.format("This is Alice. (%d times)", ++count);
                socket.send(response.getBytes(protocol.charset), 0);

                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}
