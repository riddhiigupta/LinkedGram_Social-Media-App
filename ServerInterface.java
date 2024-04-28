import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */
public interface ServerInterface extends Runnable {
    public ServerSocket getServerSocket();
    public ExecutorService getExecutorService();

}
