package com.sozluk.comer.dictionary_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sozluk.comer.dictionary_app.model;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    EditText trEditText,enEditText;
    Button trcevir,encevir,kayit,sil,listele;
    ListView listView;
    Realm realm;
    ArrayList<String> liste;
    ArrayAdapter veriadaptoru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RealmConfiguration realmConfig = new RealmConfiguration
                    .Builder(getApplicationContext())
                    .name(Realm.DEFAULT_REALM_NAME)
                    .deleteRealmIfMigrationNeeded()
                    .schemaVersion(0)
                    .build();
            Realm.setDefaultConfiguration(realmConfig);
            final Realm realm = Realm.getDefaultInstance();





        trEditText = (EditText)findViewById(R.id.trEditText);
        enEditText = (EditText)findViewById(R.id.enEditText);
        trcevir = (Button)findViewById(R.id.tcevir);
        encevir=(Button)findViewById(R.id.encevir);
        kayit = (Button)findViewById(R.id.kayit);
        sil=(Button)findViewById(R.id.sil);
        listele=(Button)findViewById(R.id.list);
       final ListView listview=(ListView)findViewById(R.id.listview);

        trcevir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    RealmResults<model> realmObjects = realm.where(model.class).findAll();
                    for (com.sozluk.comer.dictionary_app.model myRealmObject : realmObjects) {
                        if (trEditText.getText().toString().toLowerCase().equals(myRealmObject.getTr_text()))
                        {  enEditText.setText(myRealmObject.getEn_text());

                        }
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


        encevir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                try {

                    RealmResults<model> realmObjects = realm.where(model.class).findAll();
                    for (model myRealmObject : realmObjects) {
                        if (enEditText.getText().toString().toLowerCase().equals(myRealmObject.getEn_text()))
                        {  trEditText.setText(myRealmObject.getTr_text());

                        }
                    }

             }
             catch (Exception e)
             {
                 e.printStackTrace();
             }



            }
        });

        sil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {

                    RealmResults<model> realmObjects = realm.where(model.class).equalTo("en_text",enEditText.getText().toString().toLowerCase()).findAll();
                    for (model myRealmObject : realmObjects) {
                         realm.beginTransaction();
                            realmObjects.clear();
                            realm.commitTransaction();
                            Toast.makeText(getApplication(), "Silindi",Toast.LENGTH_SHORT).show();

                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }




            }




        });



       listele.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                RealmHelper helper=new RealmHelper(realm);

                liste=helper.liste();

                veriadaptoru=new ArrayAdapter(getApplicationContext(),R.layout.listele,liste);
                listview.setAdapter(veriadaptoru);



                }
       });
        kayit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                try{
                    realm.beginTransaction();
                    model models = realm.createObject(model.class);
                    models.setTr_text(trEditText.getText().toString().toLowerCase());
                    models.setEn_text(enEditText.getText().toString().toLowerCase());
                    realm.commitTransaction();
                    Toast.makeText(getApplication(), "Kayıt edildi",Toast.LENGTH_SHORT).show();
                    //KayitEkle(trEditText.getText().toString(),enEditText.getText().toString());
                }
                catch (Exception e)
                {e.printStackTrace();}

            }

        });
    }
}







