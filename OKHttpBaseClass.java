/**
 * Created by Mohit on 22-02-2017.
 */
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class GetDataDemo {

    OkHttpClient client=new OkHttpClient();
    String run(String url)throws IOException
    {
        Request request=new Request.Builder().url(url).build();

        try(Response response=client.newCall(request).execute())
        {
            String result=response.body().string();
            System.out.print(result);
            return result;
        }
    }
}
