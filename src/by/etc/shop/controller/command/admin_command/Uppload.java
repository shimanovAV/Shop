package by.etc.shop.controller.command.admin_command;

import javax.imageio.IIOException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Uppload {
    public static String picture(Part part) throws IOException{
        String filePath = part.getHeader("Content-Disposition");
        int lastIndex = filePath.lastIndexOf("\\");
        filePath = filePath.substring(lastIndex+1, filePath.length()-1);
        filePath = "resources//pictures//" + filePath;
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
