package com.zt;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
  static {
    new Thread(() -> {
      Server server = new Server(8080);

      ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
      context.setContextPath("/");
      server.setHandler(context);

      try {
        server.start();
        server.join();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
  }

  public static void main(String[] args) throws Exception {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleAtFixedRate(App::get, 3, 5, TimeUnit.SECONDS);
  }

  private static String get() {
    StringBuilder sb = new StringBuilder();
    try {
      String spec = "http://www.ee";
      URL url = new URL(spec);
      URLConnection urlConnection = url.openConnection();
      InputStream inputStream = urlConnection.getInputStream();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        sb.append(line);
      }
      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }


}
