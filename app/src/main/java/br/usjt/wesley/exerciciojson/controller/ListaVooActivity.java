package br.usjt.wesley.exerciciojson.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.wesley.exerciciojson.adapter.VooAdapter;
import br.usjt.wesley.exerciciojson.model.Voo;
import br.usjt.wesley.exerciciojson.R;

public class ListaVooActivity extends ActionBarActivity {
    ListView listView;
    Activity atividade;
    public final static String VOO = "br.usjt.wesley.exerciciojson.VOO";
    Voo[] voos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_voo);
        atividade = this;

        //pega a mensagem do intent
        Intent intent = getIntent();
        String modo = intent.getStringExtra(MainActivity.MODO);
        voos = ((ArrayList<Voo>)intent.getSerializableExtra(MainActivity.VOOS)).toArray(new Voo[0]);

        //cria o listview de voos
        listView = (ListView) findViewById(R.id.view_lista_voo);

        String[] lista = null;
        if(modo.equals(MainActivity.SIMPLES)) {
            lista = new String[voos.length];

            for (int i = 0; i < voos.length; i++) {
                lista[i] = ""+voos[i].getOrigem()+" - " + voos[i].getDestino();
            }
        }
        listView = (ListView) findViewById(R.id.view_lista_voo);

        BaseAdapter adapter;
        if(modo.equals(MainActivity.SIMPLES)) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, lista);
        } else {
            adapter = new VooAdapter(this, voos);
        }

        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheVooActivity.class);
                intent.putExtra(VOO, voos[position]);

                startActivity(intent);

            }

        });
    }

}