package com.sozluk.comer.dictionary_app.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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

import com.sozluk.comer.dictionary_app.R;
import com.sozluk.comer.dictionary_app.database.DatabaseHelper;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



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
                                    com.sozluk.comer.dictionary_app.model models = realm.createObject(com.sozluk.comer.dictionary_app.model.class);
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


        final ListView listView = (ListView) findViewById(R.id.listview);
        DatabaseHelper.RealmHelper helper=new DatabaseHelper.RealmHelper(realm);
        liste=helper.liste();
        veriadaptoru=new ArrayAdapter(this,R.layout.listele,liste);
        listView.setAdapter(veriadaptoru);


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
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this,Cevir.class);
            startActivity(intent);
        }  else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    }








