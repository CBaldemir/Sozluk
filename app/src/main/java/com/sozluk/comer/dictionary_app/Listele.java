package com.sozluk.comer.dictionary_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import io.realm.Realm;

/**
 * Created by comer on 20.07.2017.
 */
public class Listele extends Activity {
    ListView listview;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listele);
        listview = (ListView)findViewById(R.id.listview);

    }
}