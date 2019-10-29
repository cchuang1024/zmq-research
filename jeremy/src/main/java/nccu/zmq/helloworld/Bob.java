package nccu.zmq.helloworld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Bob implements Runnable {
    private static final Logger logger = LogManager.getLogger(Bob.class.getName());

    public static synchronized Bob getBob() {
        Protocol protocol = Protocol.getDefaultReq();
        return new Bob(protocol);
    }

    private Protocol protocol;

    private Bob(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void run() {
        try (ZContext context = new ZContext()) {
            logger.info("Connecting to Alice...");

            ZMQ.Socket socket = context.createSocket(protocol.type);
            socket.connect(protocol.getUrl());

            for (int requestNo = 1; requestNo <= 10; requestNo++) {
                String request = "Hello, I'm Bob.";
                logger.info(String.format("Sending Hello. (%d times)", requestNo));
                socket.send(request.getBytes(protocol.charset), 0);

                byte[] reply = socket.recv(0);
                logger.info(String.format("Received: [%s]", new String(reply, protocol.charset)));
            }
        }
    }
}
