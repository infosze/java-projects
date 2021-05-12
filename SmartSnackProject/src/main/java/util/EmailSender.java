package util;

import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import entity.Machine;

public class EmailSender {

	private static final String SMTP_SERVER = "smtp.mailtrap.io";
	private static final int PORT = 2525;
	private static final String USERNAME = "8879f84a687e7f";
	private static final String PASSWORD = "02aade719583e3";

	private static final String EMAIL_FROM = "800c67efdc-30f21b@inbox.mailtrap.io";
	private static final String EMAIL_TO = "800c67efdc-30f21b@inbox.mailtrap.io";

	public void sendEmail(List<Machine> faultyMachinesList) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(SMTP_SERVER);
		email.setSmtpPort(PORT);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));

		String machineList = faultyMachinesList.toString();
		String htmlMail = "<h3 style='font-size:16px;color:red'>Hibás Automaták: </h3>\r\n <p>" + machineList + "<p/>\r\n";

		try {
			email.setCharset("utf-8");
			email.setFrom(EMAIL_FROM, "From");
			email.addTo(EMAIL_TO, "To");
			email.setSubject("SSP - TEST Service Mail");
			email.setHtmlMsg(htmlMail);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}