package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import entity.Machine;

public class EmailSender {
	
	private final String resourceName = "email.properties";

	public void sendEmail(List<Machine> faultyMachinesList) {

		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		try {
			try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
				prop.load(resourceStream);
			}
			HtmlEmail email = new HtmlEmail();
			email.setHostName(prop.getProperty("SMTP_SERVER"));
			email.setSmtpPort(Integer.parseInt(prop.getProperty("PORT")));
			email.setAuthenticator(
					new DefaultAuthenticator(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD")));

			String machineList = faultyMachinesList.toString();

			StringBuffer msg = new StringBuffer();
			msg.append("<h3 style='font-size:16px;color:red'>Hibás Automaták: </h3>\r\n <p>");
			msg.append(machineList);
			msg.append("<p/>\r\n");
			String htmlMail = msg.toString();

			email.setCharset(prop.getProperty("CHARSET"));
			email.setFrom(prop.getProperty("EMAIL_FROM"), "From");
			email.addTo(prop.getProperty("EMAIL_TO"), "To");
			email.setSubject(prop.getProperty("SUBJECT")); // "SSP - TEST Service Mail"
			email.setHtmlMsg(htmlMail);
			email.send();
		} catch (EmailException | IOException e) {
			e.printStackTrace();
		}

	}

}