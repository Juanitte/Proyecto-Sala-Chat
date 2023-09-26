package util;

import com.juanite.model.domain.Chat;
import com.juanite.model.domain.Message;
import com.juanite.model.domain.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLManager {

	public static <T> boolean writeXML(T c, String fichero) {
		boolean result = false;
		
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(c.getClass());

			Marshaller m = context.createMarshaller();
			//Formatear XML
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			m.marshal(c, new FileOutputStream(fichero));
			result = true;

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			try {
				File file = new File(c.getClass().getName()+".xml");
				file.createNewFile();
				writeXML(c, fichero);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
		return result;
	}
	
	public static Chat readChatXML(String fichero) {
		Chat result = null;
		
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(result.getClass());

			Unmarshaller m = context.createUnmarshaller();
			result = (Chat)m.unmarshal(new File(fichero));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static User readUserXML(String fichero) {
		User result = null;

		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(result.getClass());

			Unmarshaller m = context.createUnmarshaller();
			result = (User)m.unmarshal(new File(fichero));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static Message readMessageXML(String fichero) {
		Message result = null;

		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(result.getClass());

			Unmarshaller m = context.createUnmarshaller();
			result = (Message) m.unmarshal(new File(fichero));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public static String extractTimestampFromXML(String xmlMessage) {
		return "";
	}

	public static String extractContentFromXML(String xmlMessage) {
		return "";
	}

	public static String extractSenderFromXML(String xmlMessage) {
		return "";
	}

}
