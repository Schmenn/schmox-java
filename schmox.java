import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class schmox {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("at least use a command (help coming soon)");
        }
        switch (args[0].toString()) {
            case "time":
                time();
                break;
            case "geo":
                System.out.println(commands.geo(args[1]));
                break;
            default:
                return;
        }
    }

    public static void time() {
        Date date = new Date();
        System.out.println(date.toString());
    }
}

class commands extends schmox {
    static String geo(String ip) {
        String returner;
        try {
            URL url = new URL("http://ip-api.com/line/" + ip + "?fields=58458111");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            System.out.println(status);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            returner = content.toString();

        } catch (IOException e) {

            returner = "error: " + e;

        }
        return returner;
        
    }
}