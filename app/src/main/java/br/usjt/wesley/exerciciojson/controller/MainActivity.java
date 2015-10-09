package br.usjt.wesley.exerciciojson.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.wesley.exerciciojson.model.Voo;
import br.usjt.wesley.exerciciojson.network.VooRequester;
import br.usjt.wesley.exerciciojson.R;

public class MainActivity extends ActionBarActivity {

    Spinner spinnerOrigem;
    Spinner spinnerDestino;


    Button btnConsultar;
    String origem,destino;
    ArrayList<Voo> voos;

    //Servidor
    //final String servidor = "jbossews-cerveja.rhcloud.com";
    final String servidor = "10.0.2.2:8080";
    VooRequester requester;
    ProgressBar mProgress;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

    }

    private void setupViews() {
        origem = "";
        destino = "";

        btnConsultar = (Button) findViewById(R.id.botao_enviar_melhor);
        spinnerOrigem = (Spinner) findViewById(R.id.dropdown_origem);
        spinnerOrigem.setOnItemSelectedListener(new OrigemSelecionado());
        spinnerDestino = (Spinner) findViewById(R.id.dropdown_destino);
        spinnerDestino.setOnItemSelectedListener(new DestinoSelecionada());
        mProgress = (ProgressBar) findViewById(R.id.carregando);
        mProgress.setVisibility(View.INVISIBLE);
    }

    private class OrigemSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            origem = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class DestinoSelecionada implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            destino = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    /*
        // constante static para identificar a mensagem
        public final static String ORIGEM = "br.usjt.wesley.exerciciojson13.ORIGEM";
        public final static String DESTINO = "br.usjt.wesley.exerciciojson13.DESTINO";
        public final static String MODO = "br.usjt.wesley.exerciciojson13.MODO";
        public final static String SIMPLES = "br.usjt.wesley.exerciciojson13.SIMPLES";
        public final static String MELHOR = "br.usjt.wesley.exerciciojson13.MELHOR";
        //será chamado quando o usuário clicar em enviar*/
    // constante static para identificar o parametro
    public final static String VOOS = "br.usjt.wesley.exerciciojson.VOOS";
    public final static String SIMPLES = "br.usjt.wesley.exerciciojson.SIMPLES";
    public final static String MELHOR = "br.usjt.wesley.exerciciojson.MELHOR";
    public final static String MODO = "br.usjt.wesley.exerciciojson.MODO";
    //será chamado quando o usuário clicar em enviar
    public void consultarVoos(View view) {
        consultar(view, SIMPLES);
    }

    public void consultarVoosMelhor(View view) {
        consultar(view, MELHOR);
    }

    private String anoAjuste(String ano){
        if(ano.length() == 3)return  "0"+ano;
        if(ano.length() == 2)return  "00"+ano;
        if(ano.length() == 1)return  "000"+ano;
        return ano;
    }
    public void consultar(View view, String modo){
        final String passarOrigem = this.origem.equals("Escolha um aeroporto")?"":origem;
        final String passarDestino = this.destino.equals("Escolha um aeroporto")?"":destino;
        final String passarModo = modo;

        requester = new VooRequester();
        if(requester.isConnected(this)) {
            intent = new Intent(this, ListaVooActivity.class);

            mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        voos = requester.get("http://" + servidor + "/listagem.json", passarOrigem, passarDestino);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra(VOOS, voos);
                                intent.putExtra(MODO,passarModo);
                                mProgress.setVisibility(View.INVISIBLE);
                                startActivity(intent);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }


}
