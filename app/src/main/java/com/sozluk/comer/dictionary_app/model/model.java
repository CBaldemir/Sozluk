package com.sozluk.comer.dictionary_app;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by comer on 20.07.2017.
 */
public class model extends RealmObject {


    private String tr_text;
    private String en_text;




    public String getTr_text() {
        return tr_text;
    }

    public void setTr_text(String tr_text) {
        this.tr_text = tr_text;
    }

    public String getEn_text() {
        return en_text;
    }

    public void setEn_text(String en_text) {
        this.en_text = en_text;
    }


}
