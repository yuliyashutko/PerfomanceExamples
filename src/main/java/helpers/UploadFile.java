package helpers;

import net.lightbody.bmp.core.har.Har;

import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by juliashutko on 7/11/2016.
 */
public class UploadFile {

    private static final String UPLOAD_URL = "http://localhost/results/upload";

    public static String postToHarStorage(Har har) throws Exception {
        URL url = new URL(UPLOAD_URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Automated", "true");

        try(StringWriter dataWriter = new StringWriter();
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream())){
            har.writeTo(dataWriter);
            wr.write("file=" + URLEncoder.encode(dataWriter.toString(), "utf-8"));
        }

        String response = connection.getResponseMessage();

        connection.disconnect();

        if(!response.contains("OK")){
            throw new Exception("Failed to post to Harstorage: " + response);
        }

        return response;
    }
}
