package nccu.zmq.helloworld;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class TestHelloWorld {
    @Test
    public void testHelloWorld() throws InterruptedException {
        Athens school = Athens.getInstance();

        Bob bob = Bob.getBob();
        school.addPhilo(bob);

        Alice alice = Alice.getAlice();
        school.addPhilo(alice);

        int times = 0;
        while(times < 15){
            TimeUnit.MILLISECONDS.sleep(1000);
            times++;
        }
    }
}
