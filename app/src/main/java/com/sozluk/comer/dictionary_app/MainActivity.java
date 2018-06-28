package com.sozluk.comer.dictionary_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sozluk.comer.dictionary_app.model;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    EditText trEditText,enEditText;
    Button trcevir,encevir,kayit,sil,listele;
    ListView listView;
    Realm realm;
    ArrayList<String> liste;
    ArrayAdapter veriadaptoru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RealmConfiguration realmConfig = new RealmConfiguration
                    .Builder(getApplicationContext())
                    .name(Realm.DEFAULT_REALM_NAME)
                    .deleteRealmIfMigrationNeeded()
                    .schemaVersion(0)
                    .build();
            Realm.setDefaultConfiguration(realmConfig);
            final Realm realm = Realm.getDefaultInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View subview=inflater.inflate(R.layout.dialog,null);
                final Spinner subspinner = (Spinner) subview.findViewById(R.id.spinner1);
                final Spinner subspinner2 = (Spinner) subview.findViewById(R.id.spinner2);
                final EditText subdiolog = (EditText)subview.findViewById(R.id.dialogEditText);
                final EditText subdiaog2 = (EditText)subview.findViewById(R.id.dialogEditText2);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Yeni Kelime Ekleyiniz..")
                        .setView(subview)
                        .setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    realm.beginTransaction();
                                    model models = realm.createObject(model.class);
                                    models.setDialogspinner(subspinner.getSelectedItem().toString());
                                    models.setDialogspinner2(subspinner2.getSelectedItem().toString());
                                    models.setTr_text(subdiolog.getText().toString().toLowerCase());
                                    models.setEn_text(subdiaog2.getText().toString().toLowerCase());
                                    realm.commitTransaction();
                                    Toast.makeText(getApplication(), "Kayıt edildi",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    //KayitEkle(trEditText.getText().toString(),enEditText.getText().toString());
                                }
                                catch (Exception e)
                                {e.printStackTrace();}
                            }
                        })
                        .setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("MainActivity", "Aborting mission...");
                            }
                        })
                        .show();

            }
        });



        trEditText = (EditText)findViewById(R.id.trEditText);
        enEditText = (EditText)findViewById(R.id.enEditText);
        trcevir = (Button)findViewById(R.id.tcevir);
        encevir=(Button)findViewById(R.id.encevir);
        sil=(Button)findViewById(R.id.sil);



        final ListView listView = (ListView) findViewById(R.id.listview);
        RealmHelper helper=new RealmHelper(realm);
        liste=helper.liste();
        veriadaptoru=new ArrayAdapter(this,R.layout.listele,liste);
        listView.setAdapter(veriadaptoru);





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



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    }








