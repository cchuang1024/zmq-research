package nccu.zmq.helloworld;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Athens {
    private static final Athens INSTANCE = new Athens();

    public static synchronized Athens getInstance() {
        return INSTANCE;
    }

    private ExecutorService stanza;

    private Athens() {
        stanza = Executors.newCachedThreadPool();
    }

    public void addPhilo(Runnable philo) {
        stanza.execute(philo);
    }
}
