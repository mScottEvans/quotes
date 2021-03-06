/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


//import org.json.*;

public class App {


    public static void main(String[] args) throws IOException {
        HttpURLConnection urlConn = createRequest("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en");
        if(urlConn.getResponseCode() == 200){
            StringBuffer content = readResponse(urlConn);
            QuoteForis quote = parseQuoteFromForis(content);
            System.out.println(quote.quoteAuthor + "\n" + quote.quoteText);
        }
        else{
            FileReader reader = readerFile("./app/src/test/resources/recentquotes.json");
            ArrayList<Quote> quoteArray = createQuoteArray(reader);
            System.out.println(getRandomQuoteOffline(quoteArray));
        }
    }

    public static FileReader readerFile(String path) throws IOException{
        File quoteFile = new File(path);
        return new FileReader(quoteFile);
    }

    public static ArrayList<Quote> createQuoteArray(FileReader _readerFile){
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Quote>>(){}.getType();
        return gson.fromJson(_readerFile, collectionType);
    }

    public static String getRandomQuoteOffline(ArrayList<Quote> list){
        Random random = new Random();
        int n = random.nextInt(138);
        return list.get(n).author + "\n" + list.get(n).text;
    }

    public static HttpURLConnection createRequest(String _url) throws IOException{
        URL url = new URL(_url);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setRequestMethod("GET");
        return urlConn;
    }

    public static StringBuffer readResponse(HttpURLConnection _connection) throws IOException {
        try(BufferedReader response = new BufferedReader(
                new InputStreamReader(_connection.getInputStream())
        )){
            String input;
            StringBuffer content = new StringBuffer();
            while((input = response.readLine()) != null){
                content.append(input);
            }
            return content;
        }
    }

    public static QuoteForis parseQuoteFromForis(StringBuffer _content){
        Gson gson = new Gson();
        return gson.fromJson(_content.toString(), QuoteForis.class);
    }

}
