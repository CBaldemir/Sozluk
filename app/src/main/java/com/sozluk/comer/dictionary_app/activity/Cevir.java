package com.sozluk.comer.dictionary_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sozluk.comer.dictionary_app.R;
import com.sozluk.comer.dictionary_app.model;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class Cevir extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText trEditText,enEditText;
    Button trcevir,encevir,sil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cevir);
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
        sil=(Button)findViewById(R.id.sil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        trcevir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    RealmResults<model> realmObjects = realm.where(com.sozluk.comer.dictionary_app.model.class).findAll();
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

                    RealmResults<com.sozluk.comer.dictionary_app.model> realmObjects = realm.where(com.sozluk.comer.dictionary_app.model.class).findAll();
                    for (com.sozluk.comer.dictionary_app.model myRealmObject : realmObjects) {
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

                    RealmResults<com.sozluk.comer.dictionary_app.model> realmObjects = realm.where(com.sozluk.comer.dictionary_app.model.class).equalTo("en_text",enEditText.getText().toString().toLowerCase()).findAll();
                    for (com.sozluk.comer.dictionary_app.model myRealmObject : realmObjects) {
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
        getMenuInflater().inflate(R.menu.cevir, menu);
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
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this,Cevir.class);
            startActivity(intent);
        }  else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
