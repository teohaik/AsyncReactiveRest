package gr.teohaik.async.service;
import gr.teohaik.async.rest.config.RequestForWorkEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.event.ObservesAsync;

/**
 *
 * @author Theodore Chaikalis
 */
@Stateless
public class EventHandlerService {

    Logger logger =  java.util.logging.Logger.getLogger(EventHandlerService.class.getName());
    
    public void catchRequestForWork(@ObservesAsync RequestForWorkEvent we) {
        int workType = we.getWorkType();
        if (workType == 1) {
            logger.info("RequestForWork 1 caught!");
            work1();
        } else if (workType == 2) {
            logger.info("RequestForWork 2 caught!");
            work2();
        }
    }

    public void work1() {
        logger.info("start to do work 1");
        try {
            Thread.sleep(8000);
            logger.info("work 1 done!");
        } catch (InterruptedException ex) {
           logger.log(Level.SEVERE, null, ex);
        }
    }

    public void work2() {
        logger.info("start to do work 2");
        try {
            Thread.sleep(2000);
            logger.info("work 2 done!");
        } catch (InterruptedException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
