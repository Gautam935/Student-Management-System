package org.seusl.dto.email;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class EmailTemplateCreation {
	
	public String getEmailText() {
		Template template = Velocity.getTemplate("src//main//resources//emailTemplate.vm");
		VelocityContext context = new VelocityContext();
		
	    StringWriter writer = new StringWriter();
	    template.merge( context, writer );
	    
	    return writer.toString();
	}
}
