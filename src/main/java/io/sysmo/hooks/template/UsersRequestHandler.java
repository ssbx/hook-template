package io.sysmo.hooks.template;


import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class UsersRequestHandler extends HttpServlet {

    public void init(ServletConfig config) throws ServletException
    {

        MyHook.getInstance();

    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException
    {

        JsonObjectBuilder object = Json.createObjectBuilder();

        object.add("hello", "0");
        object.add("hello1", "b");
        object.add("hello2", "c");
        object.add("hello3", "d");
        object.add("hello4", "e");

        response.setContentType("application/json");
        response.setHeader("Expires", "Tue, 03 Jul 2001 06:00:00 GMT");
        response.setDateHeader("Last-Modified", new Date().getTime());
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate, max-age=0, " +
                                                   "post-check=0, pre-check=0");

        JsonWriter writer = Json.createWriter(response.getWriter());
        writer.writeObject(object.build());

    }
}