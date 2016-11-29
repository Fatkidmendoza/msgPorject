package edu.msg.flightmanager.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.msg.flightmanager.backend.service.AuthenticationService;
import edu.msg.flightmanager.backend.service.UserService;

@SessionScoped
public class EmailHelper {

	@EJB
	private static UserService userService;

	@EJB
	private static AuthenticationService authenticationService;

	public static void sendEmail(String address, String subject, String message) {

		Properties properties = new Properties();
		Properties props = System.getProperties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream input = loader.getResourceAsStream("/config.properties");

		try {
			properties.load(input);

			props.put(properties.getProperty("mailsmptA"), "true");
			props.put(properties.getProperty("mailsmptStEn"), "true");
			props.put(properties.getProperty("mailsmptH"), (properties.getProperty("mailsmptHValue")));
			props.put(properties.getProperty("mailsmptP"), properties.getProperty("mailsmptPValue"));

			System.out.println(properties.getProperty("mailsmptP") + "///////////////////////////////////////////"
					+ "email address: " + properties.getProperty("flightManagerAddress"));
			Session session = Session.getInstance(props, new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("flightmanager2016", "testflight");
				}
			});
			System.out.println(properties.getProperty("mailsmptP") + "///////////////////////////////////////"
					+ "//////////////////////////////" + properties.getProperty("mailsmptHValue"));
			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress("flightmanager2016@gmail.com"));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);
			System.out.println("1");
			Transport transport = session.getTransport("smtp");// 587
																// //properties.getProperty("mailsmptPValue")
			// //properties.getProperty("mailFlightManager")
			transport.connect(properties.getProperty("mailsmptHValue"), properties.getProperty("flightManager"),
					properties.getProperty("flightManagerPassword"));
			transport.connect("smtp.gmail.com", "flightmanager2016", "testflight");

			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
