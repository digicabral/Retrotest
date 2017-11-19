package com.example.cabral.retrotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txtRepos;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //txtRepos = (TextView)findViewById(R.id.txtRepositorio);
        lv = (ListView) findViewById(R.id.lv);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubServices services = retrofit.create(GitHubServices.class);
        Call<List<Repo>> repos = services.listRepos("digicabral");

        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                // Pega a lista de repositorios no corpo da requisição.
                List<Repo> reposResponse = response.body();
                Log.i("reposResponse", reposResponse.toString());
                // Percorre a lista e exibe o nome de cada repositório.
                List<String> repoList = new ArrayList<String>();
                for (Repo user : reposResponse){
                    Log.i("GitHubServices", user.getmName());
                    repoList.add(user.getmName());
                    //lv.(user.getmName());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        repoList
                );
                lv.setAdapter(arrayAdapter);
            }
            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
            }
        });
    }
}
