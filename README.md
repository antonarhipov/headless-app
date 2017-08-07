# headless-app
A simple standalone application that embeds Jetty. This is to demonstrate how XRebel (https://www.xrebel.com) can be used with standalone applications.

To try XRebel with the application:
1. Download the ZIP archive from https://www.xrebel.com/download
2. Extract the content of the archive to the local file system
3. Start the application with -javaagent:/path/to/xrebel.jar -Dxrebel.traces.all=true
4. Open XRebel UI at http://localhost:8080/xrebel 
