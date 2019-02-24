
package gr.teohaik.async.rest.config;

/**
 *
 * @author Theodore Chaikalis
 */
public class RequestForWorkEvent {
    
    int workType;

    public RequestForWorkEvent() {
    }

    public RequestForWorkEvent(int workType) {
        this.workType = workType;
    }

    public int getWorkType() {
        return workType;
    }
    
}
