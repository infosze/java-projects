package servlet.listener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BackGroundJobManager implements ServletContextListener{

	private ScheduledExecutorService scheduler; 

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new HourlyJob(), 0, 1, TimeUnit.MINUTES); // TODO test setup . fix it later.
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow(); 
        if (!scheduler.isTerminated())
			try {
				scheduler.awaitTermination(10, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    }
}
