package com.example.newassignment5pokemonwatchlistkanushetkar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.commons.lang3.StringUtils;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;
import com.androidnetworking.*;
import org.apache.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button search;
    Button add;

    EditText typePokemonET, nameET;

    TextView PokemonName, ID, Weight, Height, BaseEP, move, ability;

    EditText typePokemonID;
    ImageView pictureID;

    private NEWPROFILE profileBaseAdapter;
    private Watchlist watchlistBaseAdapter;
    private List<String> textList;
    private List<PokeInfo> watchList;
    private ListView profileListView;
    private ListView watchlistListView;
    int id;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        typePokemonID = findViewById(R.id.typePokemonID);
        search = findViewById(R.id.searchID);
        search.setOnClickListener(localListener);


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
        textList = new ArrayList<>();
        watchList = new ArrayList<>();


        watchlistBaseAdapter = new Watchlist(getApplicationContext(), watchList);

        watchlistListView = findViewById(R.id.watchlistListView);

        watchlistListView.setAdapter(watchlistBaseAdapter);

    }

//jsut need to add the name and id to the list as an entry
    //make it as a ListView
    //if you click the item in the list view it will load the data for that

    //this is for the search button
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

        //looks at the API and is requesting for a name or ID that is provided
        private void makeRequest(String pokemon) {
            pokemon = pokemon.toLowerCase();
            ANRequest req = AndroidNetworking.get("https://pokeapi.co/api/v2/pokemon/{pokemon}")
                    .addPathParameter("pokemon", pokemon)
                    .setPriority(Priority.LOW)
                    .build();

            req.getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                //changing the responses from what they are called to what they are. ex: if id is 2, then it changes to 2

                public void onResponse(JSONObject response) {
//
                    try {
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
                }
//gives a toast if there is an error or the required constraints are not followed
                @Override
                public void onError(ANError anError) {
                    // Toast.makeText(getApplicationContext(),"Error on getting data ", Toast.LENGTH_LONG).show();
                    Log.i("add", "onError errorCode : " + anError.getErrorCode());
                    Log.i("add", "onError errorBody : " + anError.getErrorBody());
                    Log.i("add", "onError errorDetail : " + anError.getErrorDetail());
                    Toast.makeText(getApplicationContext(), "Error in getting your data DR. ESTEBAN!", Toast.LENGTH_LONG).show();
                }

            });
        }
    };

//this is suppose to add the response to the Watchlist
    public void add(View view) {
        ANRequest req = AndroidNetworking.get("https://pokeapi.co/api/v2/pokemon/charizard")
                .setPriority(Priority.LOW)
                .build();
                req .getAsJSONObject(new JSONObjectRequestListener() {
                                         @Override
                                         public void onResponse(JSONObject response) {
                                             String name;
                                             int id;
                                             String weight;
                                             String height;
                                             String baseEP;
                                             String move;
                                             String ability;

                                             try {
                                                 name = response.getString("name");
                                                 id = Integer.parseInt(response.getString("id"));
                                                 weight = response.getString("weight");
                                                 height = response.getString("height");
                                                 baseEP = response.getString("base_experience");

                                                 // Assuming you want the first move and ability
                                                 move = response.getJSONArray("moves").getJSONObject(0)
                                                         .getJSONObject("move")
                                                         .getString("name");
                                                 ability = response.getJSONArray("abilities").getJSONObject(0)
                                                         .getJSONObject("ability")
                                                         .getString("name");
                                             } catch (JSONException e) {
                                                 throw new RuntimeException(e);
                                             }

                                             PokeInfo pokemon = new PokeInfo(name, id, weight, height, baseEP, move, ability);
                                             watchList.add(pokemon);
                                             watchlistBaseAdapter.updateAdapter(watchList);
                                         }

                                         @Override
                                         public void onError(ANError anError) {
                                             Log.i("Hello", "onError errorCode : " + anError.getErrorCode());
                                             Log.i("TAG", "onError errorBody : " + anError.getErrorBody());
                                             Log.i("TAG", "onError errorDetail : " + anError.getErrorDetail());
                                             Toast.makeText(getApplicationContext(), "Please enter an actual Pokemon or ID DR. ESTEBAN!", Toast.LENGTH_LONG).show();


                                         }
                                     });
    }


}