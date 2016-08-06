package io.sysmo.hooks.template;


import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;


public class AppHookRequestHandler extends HttpServlet {
    private int count;

    /**
     * This is where we init our servlet. Called once at startup.
     * @param config
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException
    {

        this.count = 100;
        MyHook.getInstance();

    }

    /**
     * Handle put request
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPut(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        // curl -X PUT -H 'Content-Type: application/json' -d'{"test": "test"}' http://localhost:8081/myhook/hooks/jojo
        String info = request.getPathInfo();
        String data = AppHookRequestHandler.getJsonDataObject(request);

        JsonObjectBuilder object = Json.createObjectBuilder();
        object.add("action", "put");
        object.add("data", data);
        object.add("resource_info", info);
        JsonWriter writer = Json.createWriter(response.getWriter());
        writer.writeObject(object.build());

    }

    /**
     * Handle delete request
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doDelete(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        // curl -X DELETE http://localhost:8081/myhook/hooks/jojo
        String info = request.getPathInfo();

        JsonObjectBuilder object = Json.createObjectBuilder();
        object.add("action", "delete");
        object.add("resource_info", info);
        JsonWriter writer = Json.createWriter(response.getWriter());
        writer.writeObject(object.build());

    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException
    {

        this.count ++;

        JsonObjectBuilder object = Json.createObjectBuilder();

        String info = request.getPathInfo();

        object.add("path_info", info);
        object.add("hello1", "b");
        object.add("hello2", "c");
        object.add("hello3", "d");
        object.add("hello4", "e");
        object.add("count", this.count);

        response.setContentType("application/json");
        response.setHeader("Expires", "Tue, 03 Jul 2001 06:00:00 GMT");
        response.setDateHeader("Last-Modified", new Date().getTime());
        response.setHeader("Pragma", "no-cache"); // for HTTP 1.0
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate, max-age=0, " +
                                                   "post-check=0, pre-check=0");
        JsonWriter writer = Json.createWriter(response.getWriter());

        writer.writeObject(object.build());

    }

    private static String getJsonDataObject(
            HttpServletRequest request) throws IOException
    {

        BufferedReader reader = request.getReader();
        String data = "";
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                data = data + line;
            }
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                //
            }
        }

        return data;
    }
}