package by.etc.shop.controller.command.admin;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

public enum Picture {

    PICTURE;
    ServletContext servletContext;


    public static final int LENGTH_SLASH_SLASH = 2;
    public static final int ONE_KB = 1024;
    public static final int OFF = 0;
    public static final int LEN = 1024;
    public static final int READING_ERROR = -1;
    public static final String PICTURES_DIR = "\\pictures";
    public static final String SLASH = "\\";
    public static final String SLASH_SLASH = "//";
    public static final int LENGTH_OF_SLASH = 1;
    public static final String START_OF_NAME_PIC_IN_DB = ".//pictures//";

    Picture() {
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String upload(Part part, HttpServletRequest req) throws IOException {
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
        byte[] arr = new byte[ONE_KB];
        while (reader.read(arr, OFF, LEN) != READING_ERROR) {
            fileout.write(arr, OFF, LEN);
        }
        fileout.flush();
        fileout.close();
        reader.close();
        return filePath;
    }

    private String fromFolderToServer(String filePath, HttpServletRequest req) {
        servletContext = req.getServletContext();
        String path = servletContext.getRealPath(PICTURES_DIR);
        filePath = path + SLASH + filePath;
        return filePath;
    }

    public String fromDBToServer(String filePath) {
        int lastIndex = filePath.lastIndexOf(SLASH_SLASH);
        filePath = filePath.substring(lastIndex + LENGTH_SLASH_SLASH);
        String path = servletContext.getRealPath(PICTURES_DIR);
        filePath = path + SLASH + filePath;
        return filePath;
    }

    public String fromServerToDB(String pathToPicture) {
        int lastIndex = pathToPicture.lastIndexOf(SLASH);
        pathToPicture = pathToPicture.substring(lastIndex + LENGTH_OF_SLASH);
        pathToPicture = START_OF_NAME_PIC_IN_DB + pathToPicture;
        return pathToPicture;
    }
}
