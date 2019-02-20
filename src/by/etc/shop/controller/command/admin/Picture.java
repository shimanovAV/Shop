package by.etc.shop.controller.command.admin;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

public enum Picture {

    PICTURE;

     ServletContext servletContext;

    Picture() {
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String uppload(Part part, HttpServletRequest req) throws IOException{
        String filePath = part.getSubmittedFileName();
        filePath = fromFolderToServer(filePath, req);
        File file;
        InputStream reader = part.getInputStream();
        file = new File(filePath);
        if (!file.canWrite()) {
            file.setWritable(true);
        }
        file.createNewFile();
            FileOutputStream fileout = new FileOutputStream(filePath);
            byte[] arr = new byte[1024];
            while(reader.read(arr,0,1024)!=-1)
            {
                fileout.write(arr,0,1024);
            }
            fileout.flush();
            fileout.close();
            reader.close();
            return filePath;
        }

    private String fromFolderToServer(String filePath, HttpServletRequest req) throws IOException{
        servletContext = req.getServletContext();
        String path = servletContext.getRealPath("\\pictures");
        filePath = path + "\\" + filePath;
        return filePath;
    }

    public String fromDBToServer(String filePath) throws IOException{
        int lastIndex = filePath.lastIndexOf("//");
        filePath = filePath.substring(lastIndex+2);
        String path = servletContext.getRealPath("\\pictures");
        filePath = path + "\\" + filePath;
        return filePath;
    }

    public String fromServerToDB(String pathToPicture) throws IOException{
        int lastIndex = pathToPicture.lastIndexOf("\\");
        pathToPicture = pathToPicture.substring(lastIndex+1,pathToPicture.length());
        pathToPicture = ".//pictures//" + pathToPicture;
        return pathToPicture;
    }
}
