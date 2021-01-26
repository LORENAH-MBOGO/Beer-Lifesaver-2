package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.skylar.beer_lifesaver.Constants.SANDBOX_BASE_URL;


public class BeerClient {
    private static Retrofit retrofit = null;

    public static BeerApi getClient() {

        if  (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(SANDBOX_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(BeerApi.class);
    }
}

