package com.sozluk.comer.dictionary_app;

import android.widget.EditText;

import com.sozluk.comer.dictionary_app.model;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by comer on 21.07.2017.
 */
public class RealmHelper {
Realm realm;
public RealmHelper(Realm realm)
{
    this.realm=realm;
}

//Write
public void save(final model models)
{
    realm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {
            model m=realm.copyToRealm(models);

        }




    });

}

//Read
    public ArrayList<String> liste()
    {
        ArrayList<String>veriadaptoru=new ArrayList<>();
        RealmResults<model> models=realm.where(model.class).findAll();

        for (model s:models)
        {
            veriadaptoru.add(s.getDialogspinner() + " " +s.getTr_text() + " " + s.getDialogspinner2() + " " + s.getEn_text());
        }
        return veriadaptoru;
    }

    public boolean checkUser(String email) {

        RealmResults<model> realmObjects = realm.where(model.class).findAll();
        for (model myRealmObject : realmObjects) {
            if (email.equals(myRealmObject.getEn_text()))
            {
                return true;
            }
        }
        return false;
    }


}
