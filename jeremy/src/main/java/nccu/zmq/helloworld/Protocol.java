package nccu.zmq.helloworld;

import java.nio.charset.Charset;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class Protocol {
    public static synchronized Protocol getDefaultRep() {
        return new Protocol(DEFAULT_SCHEME, DEFAULT_HOST, DEFAULT_PORT, SocketType.REP, DEFAULT_CHARSET);
    }

    public static synchronized Protocol getDefaultReq(){
        return new Protocol(DEFAULT_SCHEME, DEFAULT_HOST, DEFAULT_PORT, SocketType.REQ, DEFAULT_CHARSET);
    }

    public static final String DEFAULT_HOST = "127.0.0.1";
    public static final int DEFAULT_PORT = 5555;
    public static final String DEFAULT_SCHEME = "tcp://";
    public static final Charset DEFAULT_CHARSET = ZMQ.CHARSET;

    public final SocketType type;
    public final int port;
    public final String host;
    public final String scheme;
    public final Charset charset;

    public Protocol(String scheme, String host, int port, SocketType type, Charset charset) {
        this.scheme = scheme;
        this.host = host;
        this.port = port;
        this.type = type;
        this.charset = charset;
    }

    public String getUrl() {
        return String.format("%s%s:%d", scheme, host, port);
    }
}
