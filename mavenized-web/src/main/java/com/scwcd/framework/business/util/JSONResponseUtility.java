package com.scwcd.framework.business.util;

import com.osm.util.Exceptions;
import com.scwcd.framework.command.core.ApplicationSession;
import com.scwcd.framework.command.util.CommandUtility;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;

public class JSONResponseUtility {

    public static final String STATE = "gState";

    public static String buildState(final HttpServletRequest request, final String username) {
        final ApplicationSession appSession = (ApplicationSession) request.getSession().getAttribute(
                ApplicationSession.SESSION_APPLICATION);
        final Date date = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat();

        sdf.applyPattern("MMMMM");
        final String mmmmm = sdf.format(date);

        sdf.applyPattern("M");
        final String m = sdf.format(date);

        sdf.applyPattern("yyyy");
        final String yyyy = sdf.format(date);

        final StringBuilder state = new StringBuilder();

        // start of JSON
        state.append("{ ");

        // build user details and state
        state.append("\"u\": { \"id\": \"").append(username);
        state.append("\" }, ");

        // build application-context and messages
        state.append("\"ac\": { \"mv\": ").append(CommandUtility.MAX_CACHE);
        state.append(", \"mm\": [\"").append("Only ").append(CommandUtility.MAX_CACHE).append(" floor").append("s")
                .append(" can be loaded at a time.");
        state.append("\", \"").append("Changes will be lost. Are you sure?");
        state.append("\", \"").append("Changes updated");
        state.append("\"], \"mt\": [\"").append("Error");
        state.append("\", \"").append("Confirmation");
        state.append("\", \"").append("Success");
        state.append("\"] }, ");

        // build date state
        state.append("\"dt\": { \"mmmmm\": \"").append(mmmmm);
        state.append("\", \"m\": ").append(m);
        state.append(", \"yyyy\": ").append(yyyy);
        state.append(" }, ");

        // build project state
        final int projectId = (Integer) request.getAttribute("pid");
        state.append("\"pid\": ").append(projectId);
        state.append(", ");

        // build selected floor state
        state.append("\"id\": [");
        final Set<Integer> planIds = appSession.getPlanIds();
        Iterator<Integer> it = planIds.iterator();
        while (it.hasNext()) {
            state.append(" ").append(it.next());
            if (it.hasNext()) {
                state.append(", ");
            }
        }
        state.append(" ], ");

        // TODO: build view state (necessary to cache view state?)
        state.append("\"v\": { \"id\": ").append("999");
        state.append(", \"r\": ").append("null");
        state.append(", \"d\": ").append("false"); // true if view/lot has been
        // modified
        state.append(" } ");

        // end of JSON
        state.append("}");

        return state.toString();
    }

    public static String buildResponse() {
        final StringBuilder state = new StringBuilder();

        // start of JSON
        state.append("{ ");

        // build response code
        state.append("c: '").append("code");

        // build response message
        state.append("', m: '").append("message");

        // build response output
        state.append("', o: '").append("output");
        state.append("' ");

        // end of JSON
        state.append("}");

        return state.toString();
    }

    public static ByteArrayOutputStream buildFileUploadResponse(final HttpServletRequest request) {
        ByteArrayOutputStream baos = null;
        InputStream is = null;
        try {
            baos = new ByteArrayOutputStream();
            is = request.getInputStream();
            int b = 0;
            while ((b = is.read()) != -1) {
                baos.write(b);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(baos);
        }
        return baos;
    }

    private JSONResponseUtility() throws InstantiationException {
        throw Exceptions.instantiationException();
    }
}