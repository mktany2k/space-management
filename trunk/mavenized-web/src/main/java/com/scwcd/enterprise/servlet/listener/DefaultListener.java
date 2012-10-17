package com.scwcd.enterprise.servlet.listener;


import com.scwcd.component.view.core.View;
import com.scwcd.component.view.factory.ViewFactory;
import com.scwcd.enterprise.sql.dao.DAOLot;
import com.scwcd.enterprise.sql.dao.DAOPlan;
import com.scwcd.enterprise.sql.dao.DAOProject;
import com.scwcd.enterprise.sql.dao.DAOUser;
import com.scwcd.framework.command.core.CommandResource;
import com.scwcd.framework.command.core.ServletCommandFactory;
import com.scwcd.framework.deployment.core.WebContext;
import com.scwcd.framework.deployment.core.WebContextManager;
import com.scwcd.framework.factory.IFactory;
import com.scwcd.framework.sql.core.DAOFactory;
import com.scwcd.framework.sql.core.IDataAccessObject;
import com.scwcd.process.ajax.core.CommandPrintSession;
import com.scwcd.process.ajax.core.CommandSelectFloor;
import com.scwcd.process.ajax.core.CommandSelectLot;
import com.scwcd.process.ajax.core.CommandSelectView;
import com.scwcd.process.ajax.core.CommandSyncState;
import com.scwcd.process.ajax.core.CommandUpdateLot;
import com.scwcd.process.login.core.CommandLogin;
import com.scwcd.process.login.core.CommandLogout;
import com.scwcd.process.login.core.CommandPostLogin;
import com.scwcd.process.login.core.CommandPreLogin;
import com.scwcd.process.maintenance.core.CommandMaintainProject;
import com.scwcd.process.plan.core.CommandNewProject;
import com.scwcd.process.plan.core.CommandOpenProject;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;


public class DefaultListener extends FrameworkListener {

	private static final String PROJECT_DIR = "WEB-INF";

	@Override
	public void init(final ServletContextEvent sce) {
		// framework registration
		registerContext(sce);
		registerCommand();
		registerDataAccess();
		registerView();
	}

	private void registerContext(final ServletContextEvent sce) {
		// camel deployment framework startup
		final ServletContext sc = sce.getServletContext();
		final String projectPath = sc.getRealPath(PROJECT_DIR);
		
		final WebContext wc = WebContextManager.newWebContext();
		wc.setProjectPath(projectPath);
		WebContextManager.setWebContext(wc);
	}

	// register IServletCommand objects
	private void registerCommand() {
		final CommandResource resource = CommandResource.getInstance();
		final ServletCommandFactory commandFactory = ServletCommandFactory.getInstance();

		// Process_Login
		commandFactory.register(resource.getOperation(CommandPreLogin.class), new CommandPreLogin());
		commandFactory.register(resource.getOperation(CommandLogin.class), new CommandLogin());
		commandFactory.register(resource.getOperation(CommandPostLogin.class), new CommandPostLogin());
		commandFactory.register(resource.getOperations(CommandLogout.class), new CommandLogout());

		// Process_Plan
		commandFactory.register(resource.getOperations(CommandOpenProject.class), new CommandOpenProject());
		commandFactory.register(resource.getOperations(CommandNewProject.class), new CommandNewProject());

		// Process_Ajax
		commandFactory.register(resource.getOperation(CommandPrintSession.class), new CommandPrintSession());
		commandFactory.register(resource.getOperation(CommandSelectFloor.class), new CommandSelectFloor());
		commandFactory.register(resource.getOperation(CommandSyncState.class), new CommandSyncState());
		commandFactory.register(resource.getOperation(CommandSelectView.class), new CommandSelectView());
		commandFactory.register(resource.getOperation(CommandSelectLot.class), new CommandSelectLot());
		commandFactory.register(resource.getOperation(CommandUpdateLot.class), new CommandUpdateLot());
		
		// Process_Maintenance
		commandFactory.register(resource.getOperation(CommandMaintainProject.class), new CommandMaintainProject());
	}

	// register DAO objects
	private void registerDataAccess() {
		final IFactory<Class<?>, IDataAccessObject> daoFactory = DAOFactory.getInstance();
		daoFactory.register(DAOProject.class, new DAOProject());
		daoFactory.register(DAOPlan.class, new DAOPlan());
		daoFactory.register(DAOUser.class, new DAOUser());
		daoFactory.register(DAOLot.class, new DAOLot());
	}

	// register IView objects
	private void registerView() {
		final ViewFactory viewFactory = ViewFactory.getInstance();

		try {
			final URL url = getClass().getResource(ViewFactory.SCHEMA);
			final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			final Schema schema = factory.newSchema(url);
			viewFactory.setSchema(schema);
		} catch (final SAXException e) {
			e.printStackTrace();
		} finally {
			viewFactory.register(View.class, "Category.xml");
			viewFactory.register(View.class, "LeaseExpiry.xml");
			viewFactory.register(View.class, "LeaseRental.xml");
			viewFactory.register(View.class, "Revenue.xml");
			viewFactory.register(View.class, "Normal.xml");
		}
	}
}