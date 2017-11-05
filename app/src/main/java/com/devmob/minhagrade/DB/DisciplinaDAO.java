package com.devmob.minhagrade.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.devmob.minhagrade.Model.Disciplina;
import com.devmob.minhagrade.Model.Periodo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kobayashi on 02/11/2017.
 */

public class DisciplinaDAO extends DBHelper{
    private static final String TAG = "DiscipliaDAO";

    public DisciplinaDAO(Context context) {
        super(context);
    }

    public void insere(Disciplina disciplina){
        onOpen();
        ContentValues values = new ContentValues();
        values.put("nome", disciplina.getNome());
        values.put("status", disciplina.getStatus());
        values.put("periodo", disciplina.getPeriodo());
        db.insert(DBHelper.TABLE_DISCIPLINA,null, values);
        onClose();
    }

    public Disciplina getDisciplina(Integer id){
        Disciplina disciplina = null;
        String sql = "SELECT * FROM "+DBHelper.TABLE_DISCIPLINA+" WHERE "+DBHelper.KEY_ID+" = "+id;
        onOpen();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToNext()){
            disciplina = new Disciplina(
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getInt(cursor.getColumnIndex("status")),
                    cursor.getString(cursor.getColumnIndex("periodo")));
        }
        onClose();
        return disciplina;
    }

    public List<Disciplina> getDisciplinas(){
        List<Disciplina> disciplinas = new ArrayList<>();
        onOpen();
        Cursor cursor = db.rawQuery("SELECT * FROM "+DBHelper.TABLE_DISCIPLINA, null);
        while (cursor.moveToNext()){
            Disciplina disciplina = new Disciplina(
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getInt(cursor.getColumnIndex("status")),
                    cursor.getString(cursor.getColumnIndex("periodo")));
            disciplinas.add(disciplina);
        }
        onClose();
        return disciplinas;
    }

    public List<Disciplina> getDisciplinasPorPeriodo(int periodo){
        List<Disciplina> disciplinas = new ArrayList<>();
        onOpen();
        Cursor cursor = db.rawQuery("SELECT * FROM "+DBHelper.TABLE_DISCIPLINA+" WHERE periodo="+periodo, null);
        while (cursor.moveToNext()){
            Disciplina disciplina = new Disciplina(
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getInt(cursor.getColumnIndex("status")),
                    cursor.getString(cursor.getColumnIndex("periodo")));
            disciplinas.add(disciplina);
        }
        onClose();
        return disciplinas;
    }
}
