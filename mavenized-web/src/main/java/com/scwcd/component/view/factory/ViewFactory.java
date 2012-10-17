package com.scwcd.component.view.factory;


import com.scwcd.component.view.core.View;
import com.scwcd.framework.factory.IFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ViewFactory implements IFactory<Class<View>, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewFactory.class);

	public static final String SCHEMA = "/com/scwcd/component/view/schema/view.xsd";

	private static final String RESOURCE = "/com/scwcd/component/view/resource/";

	private static final ViewFactory INSTANCE = new ViewFactory();

	private HashMap<Integer, View> m_RegisteredView = new HashMap<>();

	private List<View> views = new ArrayList<>();
	
	private Schema schema;

	public static ViewFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public String getInstance(final Class<View> clazz) {
		return clazz.getCanonicalName();
	}

	public View getInstance(final int viewId) {
		return m_RegisteredView.get(viewId);
	}

	@Override
	public void register(final Class<View> clazz, final String xml) {
		try {
			final JAXBContext context = JAXBContext.newInstance(clazz);
			final Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setSchema(schema);

			final InputStream is = getClass().getResourceAsStream(RESOURCE + xml);
			final View view = clazz.cast(unmarshaller.unmarshal(is));

			LOGGER.info("Registering [" + view.getName() + "] with [" + xml + "]");
			m_RegisteredView.put(view.getViewId(), view);
			views.add(view);
		} catch (final JAXBException e) {
			LOGGER.info("Unable to register view [" + xml + "] - " + e.getMessage());
		}
	}

	public List<View> list() {
		return views;
	}

	public void setSchema(final Schema schema) {
		this.schema = schema;
	}

	@Override
	public String toString() {
		return m_RegisteredView.toString();
	}
}