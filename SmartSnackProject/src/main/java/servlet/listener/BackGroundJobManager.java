 package servlet.listener;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import util.DatabaseConnect;
import util.HourlyJob;

public class BackGroundJobManager implements ServletContextListener {

	private ScheduledExecutorService scheduler;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ResourceBundle rb = ResourceBundle.getBundle("Resources", new Locale("hu"));
		ServletContext sc = event.getServletContext();
		sc.setAttribute("resource", rb);
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new HourlyJob(), 0, 60, TimeUnit.MINUTES); // TODO test setup . fix it later.
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		DatabaseConnect.close();
		scheduler.shutdownNow();
		if (!scheduler.isTerminated())
			try {
				scheduler.awaitTermination(20, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}
