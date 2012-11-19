package com.scwcd.framework.command.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class AbstractServletCommand implements IServletCommand {

    @Override
    public final String execute(final Object[] input) {
        final HttpServletRequest request = (HttpServletRequest) input[0];
        final HttpServletResponse response = (HttpServletResponse) input[1];

        try {
            return execute(request, response);
        } catch (IOException e) {
            // TODO: Log error
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public abstract String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException;

    @Override
    public IServletCommand create() {
        return this;
    }

    protected static ApplicationSession getSession(final HttpServletRequest request) {
        final HttpSession session = request.getSession();

        ApplicationSession appSession = (ApplicationSession) session.getAttribute(ApplicationSession.SESSION_APPLICATION);
        if (appSession == null) {
            appSession = new ApplicationSession(session);
            session.setAttribute(ApplicationSession.SESSION_APPLICATION, appSession);
        }

        return appSession;
    }

    protected static void debugRequest(final HttpServletRequest request, final boolean outputToFile) {
        {
            System.out.println("request.getAttributeNames()");
            Enumeration<String> enumeration = request.getAttributeNames();
            while (enumeration.hasMoreElements()) {
                final String element = enumeration.nextElement();
                final Object value = request.getAttribute(element);
                System.out.println(element + ": " + value);
            }
        }

        {
            System.out.println("request.getHeaderNames()");
            Enumeration<String> enumeration = request.getHeaderNames();
            while (enumeration.hasMoreElements()) {
                final String element = enumeration.nextElement();
                final String value = request.getHeader(element);
                System.out.println(element + ": " + value);
            }
        }

        {
            System.out.println("request.getParameterNames()");
            Enumeration<String> enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                final String element = enumeration.nextElement();
                for (final String value : request.getParameterValues(element)) {
                    System.out.println(element + ": " + value);
                }
            }
        }

        if (outputToFile) {
            try {
                final String filename = MessageFormat.format("c:\\temp\\svg-{0,date,yyyyMMdd_HHmmss_SSS}.log", new Date());
                try (FileOutputStream fos = new FileOutputStream(filename); InputStream is = request.getInputStream()) {
                    int b;
                    while ((b = is.read()) != -1) {
                        fos.write(b);
                    }
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected static void prepareResponse(final HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        response.addHeader("Content-Encoding", "UTF-8");
        response.addHeader("Content-Type", "text/xml");
    }
}