package br.usjt.wesley.exerciciojson.controller;


import java.util.ArrayList;
import java.util.TreeSet;

import br.usjt.wesley.exerciciojson.model.Voo;

public class Especialista {
    private static final ArrayList<Voo> voos = cadastroDeVoos();

    public Especialista() {

    }


    public TreeSet<Voo> listarMarcas(String origem, String destino) {
        TreeSet<Voo> marcas = new TreeSet<Voo>();
        if (origem.length() > 0 && destino.length() > 0 ) {
            marcas = buscarOrigemDestino(origem, destino);
        } else if (origem.length() > 0) {
            marcas = buscarOrigem(origem);
        } else if (destino.length() > 0) {
            marcas = buscarDestino(destino);
        } else {
            marcas = todos();
        }

        return marcas;
    }



    private TreeSet<Voo> buscarOrigemDestino(String origem,String destino){
        TreeSet<Voo> lista = new TreeSet<Voo>();
        for (Voo voo : voos) {
            if(voo.getOrigem().equals(origem)
                    && voo.getDestino().equals(destino))lista.add(voo);
        }
        return lista;
    }

    private TreeSet<Voo> buscarOrigem(String origem){
        TreeSet<Voo> lista = new TreeSet<Voo>();
        for (Voo voo : voos) {
            if(voo.getOrigem().equals(origem))lista.add(voo);
        }
        return lista;
    }

    private TreeSet<Voo> buscarDestino(String destino){
        TreeSet<Voo> lista = new TreeSet<Voo>();
        for (Voo voo : voos) {
            if(voo.getDestino().equals(destino))lista.add(voo);
        }
        return lista;
    }

    private TreeSet<Voo> todos() {
        TreeSet<Voo> lista = new TreeSet<Voo>();
        for (Voo voo : voos) {
            lista.add(voo);
        }
        return lista;
    }

    private static ArrayList<Voo> cadastroDeVoos() {
        ArrayList<Voo> voos = new ArrayList<Voo>();
        voos.add(new Voo("Congonhas(SP)", "Campinas(Interior)","marca5",1000.99));
        voos.add(new Voo("Congonhas(SP)", "Campinas(Interior)","marca7",850.50));
        voos.add(new Voo("Congonhas(SP)", "Campinas(Interior)","marca8",1310.99));
        voos.add(new Voo("Congonhas(SP)", "Campinas(Interior)","marca2",799.09));

        voos.add(new Voo("Congonhas(SP)", "Rio de janeiro(RJ)","marca1",600.90));
        voos.add(new Voo("Congonhas(SP)", "Rio de janeiro(RJ)","marca2",500.00));
        voos.add(new Voo("Congonhas(SP)", "Rio de janeiro(RJ)","marca3",399.20));
        voos.add(new Voo("Congonhas(SP)", "Rio de janeiro(RJ)","marca8",650.12));
        voos.add(new Voo("Congonhas(SP)", "Rio de janeiro(RJ)","marca5",480.30));

        voos.add(new Voo("Congonhas(SP)", "Bahia","marca6",1230.69));
        voos.add(new Voo("Congonhas(SP)", "Bahia","marca3",1500.00));

        voos.add(new Voo("Campinas(Interior)","Congonhas(SP)","marca4",200.10));
        voos.add(new Voo("Campinas(Interior)","Congonhas(SP)","marca8",150.39));
        voos.add(new Voo("Campinas(Interior)","Rio de janeiro(RJ)","marca6",800.99));
        voos.add(new Voo("Campinas(Interior)","Rio de janeiro(RJ)","marca1",750.99));
        voos.add(new Voo("Campinas(Interior)","Bahia","marca6",1600.56));
        voos.add(new Voo("Campinas(Interior)","Bahia","marca2",1750.10));

        voos.add(new Voo("Rio de janeiro(RJ)","Congonhas(SP)","marca1",910.99));
        voos.add(new Voo("Rio de janeiro(RJ)","Congonhas(SP)","marca5",810.99));
        voos.add(new Voo("Rio de janeiro(RJ)","Congonhas(SP)","marca6",850.99));
        voos.add(new Voo("Rio de janeiro(RJ)","Campinas(Interior)","marca1",950.99));
        voos.add(new Voo("Rio de janeiro(RJ)","Campinas(Interior)","marca2",899.99));
        voos.add(new Voo("Rio de janeiro(RJ)","Campinas(Interior)","marca6",879.95));
        voos.add(new Voo("Rio de janeiro(RJ)","Bahia","marca5",750.00));
        voos.add(new Voo("Rio de janeiro(RJ)","Bahia","marca6",980.59));
        voos.add(new Voo("Rio de janeiro(RJ)","Bahia","marca7",699.50));

        voos.add(new Voo("Bahia","Congonhas(SP)","marca1",900.99));
        voos.add(new Voo("Bahia","Campinas(Interior)","marca3",910.99));
        voos.add(new Voo("Bahia","Rio de janeiro(RJ)","marca6",910.99));

        return voos;
    }
}
