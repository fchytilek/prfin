package fin.management;

import java.io.File;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import fin.model.*;


public class Main {

	public static void main(String[] args) throws Exception {
		
		String name="goog";
		String startdatum="2015-10-08";
		String enddatum="2015-12-08";
		Worker.getInstance().download(name, startdatum, enddatum);
		
		
	}

}
