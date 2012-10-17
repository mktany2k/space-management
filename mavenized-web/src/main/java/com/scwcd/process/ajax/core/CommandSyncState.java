package com.scwcd.process.ajax.core;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.enterprise.sql.hbm.User;
import com.scwcd.framework.business.util.JSONResponseUtility;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;

public class CommandSyncState extends AbstractServletCommand {

    private static final String METHOD_GET = "get";
    private static final String METHOD_POST = "post";

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {

        final String method = request.getParameter("m");

        if (METHOD_POST.equals(method)) {
            // update session with latest state
//			final String state = request.getParameter("s");
//			try {
//				JSONParser parser = new JSONParser();
//				JSONObject object = (JSONObject) parser.parse(state);
//				System.out.println("object: " + object.get("dt"));
//				System.out.println("object: " + ((JSONObject) object.get("dt")).get("mmmmm"));
//			} catch (final ParseException e) {
//				e.printStackTrace();
//			}
        } else if (METHOD_GET.equals(method)) {
            final ApplicationSession session = getSession(request);
            final Project project = (Project) session.getProject();
            request.setAttribute("pid", project.getProjectId());

            // build state and write as response output stream
            final User user = (User) session.getUser();
            final String result = JSONResponseUtility.buildState(request, user.getUsername());
            try (OutputStream os = response.getOutputStream()) {
                os.write(result.getBytes());
                os.flush();
            }
        }

        return null;
    }
}