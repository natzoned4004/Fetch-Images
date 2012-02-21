/*
 * Noah Alonso-Torres
 * Fetch Icons Library from MyFav.es 
 * - For personal use and inspiration only.
 * 
 * Goes to myfav.es, scans css file for image url's,
 * fetches the images, and saves them into ./images/
 */


import java.net.*;
import java.util.regex.*;
import java.io.*;

public class Fetch {
   public static void main(String[] args) throws Exception {
       String home = "http://www.myfav.es/";
       URL site = new URL(home + "style/icons.css");
       BufferedReader in = new BufferedReader(
               new InputStreamReader(
                       site.openStream()));

       String inputLine;
       String pageContent = "";
       while ((inputLine = in.readLine()) != null)
           pageContent += inputLine;
       in.close();

       Pattern pattern = Pattern.compile("\\.\\./(images/icons/fullsize/([^']+))'");
       Matcher matcher = pattern.matcher(pageContent);

       while (matcher.find()) {
           getAndSaveFile(home+matcher.group(1), matcher.group(2));
       }
   }

   public static void getAndSaveFile(String location, String fileName) throws Exception {
       System.out.println("get  " + location + " into file " + fileName);
       URL cssFile = new URL(location);
       InputStream in = cssFile.openStream();
       
       new File("images").mkdir();
       FileOutputStream outputFile = new FileOutputStream(new File("./images/" + fileName));

       byte[] buffer = new byte[1000];
       int length;
       while ((length = in.read(buffer, 0, buffer.length)) != -1) {
           outputFile.write(buffer, 0, length);
       }

       outputFile.close();
       in.close();
       //System.exit(1);
   }
}