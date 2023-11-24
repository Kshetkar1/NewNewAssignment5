package com.example.newassignment5pokemonwatchlistkanushetkar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;
import com.androidnetworking.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    Button search;

    EditText typePokemonET, nameET;

    TextView PokemonName, ID, Weight, Height, BaseEP, move, ability;

    EditText typePokemonID;
    ImageView pictureID;

    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        typePokemonID = findViewById(R.id.typePokemonID);
        search = findViewById(R.id.searchID);
        search.setOnClickListener(localListener);

        ////nameET = findViewById(R.id.PokemonNameID);
        ////nameET.setOnClickListener(localListener);
        PokemonName = findViewById(R.id.PokemonNameID);
        PokemonName.setOnClickListener(localListener);
        ID = findViewById(R.id.IDLL);
        ID.setOnClickListener(localListener);
        Weight = findViewById(R.id.WeightID);
        Weight.setOnClickListener(localListener);
        Height = findViewById(R.id.HeightID);
        Height.setOnClickListener(localListener);
        BaseEP = findViewById(R.id.BaseEpID);
        BaseEP.setOnClickListener(localListener);
        move = findViewById(R.id.moveID);
        move.setOnClickListener(localListener);
        ability = findViewById(R.id.abilityID);
        ability.setOnClickListener(localListener);
        pictureID = findViewById(R.id.pictureID);


    }


    private final View.OnClickListener localListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            EditText tickerTextView = (EditText) findViewById(R.id.typePokemonID);
            String ticker = tickerTextView.getText().toString().trim();
            String imageURL = "\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png\"";
            ImageView iv = findViewById(R.id.pictureID);
            Picasso.get().load(imageURL).into(iv);
            makeRequest(ticker);
        }

        private void makeRequest(String pokemon) {
            ANRequest req = AndroidNetworking.get("https://pokeapi.co/api/v2/pokemon/{pokemon}")
                    .addPathParameter("pokemon", pokemon)
                    .setPriority(Priority.LOW)
                    .build();

                    req.getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                //https://stackoverflow.com/questions/2591098/how-to-parse-json-in-java
                                // [] = array
                                // {} = object
                                String id = response.getString("id");
                                String name = response.getString("name");
                                String weight = response.getString("weight");
                                String height = response.getString("height");
                                String base_xp = response.getString("base_experience");
                                JSONArray move_list = response.getJSONArray("moves");
                                JSONArray ability_list = response.getJSONArray("abilities");
                                String move_0 = move_list.getJSONObject(0)
                                        .getJSONObject("move")
                                        .getString("name");
                                String ability_0 = ability_list.getJSONObject(0)
                                        .getJSONObject("ability")
                                        .getString("name");
                                String image_url = response.getJSONObject("sprites")
                                        .getJSONObject("other")
                                        .getJSONObject("official-artwork")
                                        .getString("front_default");
                                String sprite_url = response.getJSONObject("sprites")
                                        .getJSONObject("versions")
                                        .getJSONObject("generation-viii")
                                        .getJSONObject("icons")
                                        .getString("front_default");

                                ID.setText("#" + id);
                                PokemonName.setText(name);
                                Weight.setText(weight);
                                Height.setText(height);
                                BaseEP.setText(base_xp);
                                move.setText(move_0);
                                ability.setText(ability_0);
                                Picasso.get().load(image_url).into(pictureID);




                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }





//                            try {
//                                PokemonName.setText(response.getString("name"));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                            try {
//                                id = Integer.parseInt(response.getString("id"));
//                                ID.setText(response.getString("id"));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                            try {
//                                Weight.setText(response.getString("weight"));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                            try {
//                                Height.setText(response.getString("height"));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                            try {
//                                BaseEP.setText(response.getString("name"));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                            try {
//                                move.setText(response.getJSONArray("moves").getJSONObject(0).getJSONObject("move").getString("name"));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                            try {
//                                ability.setText(response.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("name"));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                            try {
//                                String imageURL = response.getJSONObject("sprites").getString("front_default");
//                                //String imageURL = "https://github.com/HybridShivam/Pokemon/tree/master/assets/images/" + ID + ".png";
//                                Picasso.get().load(imageURL).into(pictureID);
//
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                           // Toast.makeText(getApplicationContext(),"Error on getting data ", Toast.LENGTH_LONG).show();
                            Log.i("Hello", "onError errorCode : " + anError.getErrorCode());
                            Log.i("TAG", "onError errorBody : " + anError.getErrorBody());
                            Log.i("TAG", "onError errorDetail : " + anError.getErrorDetail());
                            Toast.makeText(getApplicationContext(), "Error retrieving data", Toast.LENGTH_LONG).show();
                        }

                    });
        }
    };
}

//                    TextView tickerTextView = (TextView) findViewById(R.id.typePokemonID);
//            String ticker = tickerTextView.getText().toString();
//            String imageURL = "\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png\"";
//            ImageView iv = findViewById(R.id.pictureID);
//            Picasso.get().load(imageURL).into(iv);
//            makeRequest(ticker);



//            Editable editable = typePokemonET.getText();
//            if (editable != null) {
//                makeRequest(editable.toString());
//            }
       // }

   // };





//        private void makeRequest(String ticker) {
//
//            //https://pokeapi.co/api/v2/ability/{id or name}/
//            String apiURL;
//            try{
//                int id = Integer.parseInt(ticker);
//                apiURL = "https://pokeapi.co/api/v2/pokemon/{id}/";
//                apiURL = apiURL.replace("{id}", String.valueOf(id));
//            }catch (NumberFormatException e) {
////        // If parsing as a number fails, assume it's a Pokemon name
//                apiURL = "https://pokeapi.co/api/v2/pokemon/{name}/";
//                apiURL = apiURL.replace("{name}", ticker.toLowerCase());
//    }
//            ANRequest req = AndroidNetworking.get(apiURL)
//                    //.addPathParameter("ticker", ticker)
//                    //.addQueryParameter("apikey", API_KEY)
//                    .setPriority(Priority.LOW)
//                    .build();
//
//            req.getAsObjectList(PokeInfo.class, new ParsedRequestListener<List<PokeInfo>>() {
//
//                @Override
//                public void onResponse(List<PokeInfo> response) {
//
//                }
//
//                @Override
//                public void onError(ANError anError) {
//
//                }
//            });
//
//
//        }

//}