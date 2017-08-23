package org.seusl.dto.email;


/**
 * @author Gautam Kumar
 *
 */
public interface EmailConfiguration {

	void sendEmail(String to, String subjects);
	
}
