package jp.kobe_u.cspiral.alpaca.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 独自Date型のMarshaler
 * @author shin
 *
 */
public class DateAdapter extends XmlAdapter<String, Date> {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	@Override
	public String marshal(Date v) {
		return dateFormat.format(v);
	}

	@Override
	public Date unmarshal(String v) {
		try {
			return dateFormat.parse(v);
		} catch (ParseException e) {
			throw new WebApplicationException();
		}
	}
}