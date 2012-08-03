package com.scwcd.framework.deployment.parser;


import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.scwcd.framework.factory.IFactory;


public class ParserFactory implements IFactory<Integer, IParser> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParserFactory.class);

	private HashMap<Integer, IParser> m_RegisteredParser = new HashMap<Integer, IParser>();

	private static final ParserFactory INSTANCE = new ParserFactory();

	@Override
	public IParser getInstance(final Integer projectId) {
		return m_RegisteredParser.get(projectId);
	}

	@Override
	public void register(final Integer projectId, final IParser parser) {
		LOGGER.info("Registering project [" + projectId + "] with [" + parser + "]");
		final boolean exist = m_RegisteredParser.containsKey(projectId);
		if (exist) {
			throw new IllegalArgumentException("Project [" + projectId + "] has already been registered");
		}
		m_RegisteredParser.put(projectId, parser);
	}

	public IParser newInstance(final int projectId, final ParserType parserType) {
		IParser parser;

		switch (parserType) {
			case VISIO2007: {
				parser = new Visio2007Parser();
				register(projectId, parser);
				break;
			}
			case AUTOCAD: {
				throw new UnsupportedOperationException("AutoCAD parser not supported yet");
			}
			case DEFAULT: {
				parser = new SvgDefaultParser();
				register(projectId, parser);
				break;
			}
			default: {
				parser = new SvgDefaultParser();
				register(projectId, parser);
				break;
			}
		}

		return parser;
	}

	public static ParserFactory getInstance() {
		return INSTANCE;
	}
}