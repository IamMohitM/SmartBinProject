import okhttp3.*;

import java.io.IOException;

/**
 * Created by Mohit on 22-02-2017.
 */
public class PostDataDemo {

// Just create the object of this class and use the post method to post the data
//first parameter url,second parameter - json object or string in json form

    public static final MediaType JSON=MediaType.parse("application/json;charset=utf-8");
    OkHttpClient client=new OkHttpClient();

    String post(String url,String json) throws IOException
    {
        RequestBody body=RequestBody.create(JSON,json);
        Request request=new Request.Builder().url(url).post(body).build();

        try(Response response=client.newCall(request).execute())
        {
            return response.body().string();
        }
    }


}
