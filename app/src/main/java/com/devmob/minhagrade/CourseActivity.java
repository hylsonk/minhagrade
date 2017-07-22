package com.devmob.minhagrade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

//import com.devmob.minhagrade.Lixo.Cursos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CourseActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner1, spinner2;
    private Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        addItemsOnSpinner();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
        String course = Prefs.getString(this,"course");//"No name defined" is the default value.
        Log.d("CURSO", course);
        if(!course.isEmpty()){
            Intent intent = new Intent(CourseActivity.this, ProgressActivity.class);
            intent.putExtra("MESSAGE", course);
            startActivity(intent);
        }
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner() {
        //Cursos courses = new Cursos();

        String[] arrayCourse = getResources().getStringArray(R.array.cursos_array);

        spinner2 = (Spinner) findViewById(R.id.spinner);
        List<String> list = Arrays.asList(arrayCourse);  //courses.getCourses();
        Collections.sort(list);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list){

            public View getView(int position, View convertView,ViewGroup parent) {

                View v = super.getView(position, convertView, parent);

                ((TextView) v).setTextSize(26);

                return v;

            }

            public View getDropDownView(int position, View convertView,ViewGroup parent) {

                View v = super.getDropDownView(position, convertView,parent);

                ((TextView) v).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ((TextView) v).setTextSize(26);
                return v;

            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }


    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CourseActivity.this, ProgressActivity.class);
        String message = String.valueOf(spinner1.getSelectedItem());
        Prefs.setString(this,"course", message);
        Prefs.setInteger(this,"Concluido",0);
        Prefs.setInteger(this,"QuantidadeDisciplinas",100);
        //Log.d("PREF_COURSE", message);
        intent.putExtra("MESSAGE", message);
        /**
         * Animação de transição entre activitys
         */
        ActivityOptionsCompat opts =  ActivityOptionsCompat.makeCustomAnimation(CourseActivity.this,R.anim.slide_in_left,R.anim.slide_out_left);
        ActivityCompat.startActivity(CourseActivity.this,intent,opts.toBundle());
        //CourseActivity.this.startActivity(intent);
                /*Toast.makeText(CourseActivity.this,
                        "OnClickListener : " +
                                "\nSpinner : "+ String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();*/
    }
}