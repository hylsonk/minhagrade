package com.devmob.minhagrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by devmob on 27/03/17.
 */

public class ProgressActivity extends AppCompatActivity {

    private ListView listasDePeriodos;
    private Periodo periodo;
    private ArrayAdapter<String> adapter;
    private String curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        periodo = new Periodo();

        Intent intent = getIntent();
        curso =  intent.getStringExtra("MESSAGE");
        TextView course = (TextView) findViewById(R.id.course);
        course.setText(curso);

        //Teste de back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listasDePeriodos = (ListView) findViewById(R.id.listaPeriodo);

        adapter = new ArrayAdapter<String>(this,R.layout.listperiodo, periodo.getPeriodos(curso));

        listasDePeriodos.setAdapter(adapter);

        listasDePeriodos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String periodoSelecionado = (String) adapter.getItem(position);
                Toast.makeText(ProgressActivity.this,periodoSelecionado,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProgressActivity.this, PeriodoActivity.class);
                String periodo = String.valueOf(periodoSelecionado);
                ArrayList<String> message = new ArrayList<String>();

                message.add(curso);
                message.add(periodo);

                intent.putExtra("MESSAGE", message);
                ProgressActivity.this.startActivity(intent);
            }
        });

    }

}
