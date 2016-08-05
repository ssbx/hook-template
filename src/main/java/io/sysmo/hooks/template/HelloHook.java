package io.sysmo.hooks.template;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.Json;
import javax.json.JsonWriter;
import javax.json.JsonObjectBuilder;


public class HelloHook extends HttpServlet {

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
        response.setHeader("Pragma", "no-cache"); // for HTTP 1.0
        response.setHeader("Cache-Control",
               "private, no-store, no-cache, must-revalidate"); // for HTTP 1.1

        JsonWriter writer = Json.createWriter(response.getWriter());
        writer.writeObject(object.build());

    }
}