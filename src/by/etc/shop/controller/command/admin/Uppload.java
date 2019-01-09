package by.etc.shop.controller.command.admin;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Uppload {
    public static String picture(Part part, HttpServletRequest req) throws IOException{
        String filePath = part.getHeader("Content-Disposition");
        int lastIndex = filePath.lastIndexOf("\\");
        filePath = filePath.substring(lastIndex+1, filePath.length()-1);
        ServletContext sc = req.getServletContext();
        String path = sc.getRealPath("\\pictures");
        filePath = path + "\\" + filePath;
        File file = null;
        InputStream reader = part.getInputStream();
        file = new File(filePath);
            file.createNewFile();
            FileOutputStream fileout = new FileOutputStream(filePath);
            byte[] arr = new byte[1024];
            while(reader.read(arr,0,1024)!=-1)
            {
                fileout.write(arr,0,1024);
            }
            fileout.flush();
            return filePath;
        }
}
