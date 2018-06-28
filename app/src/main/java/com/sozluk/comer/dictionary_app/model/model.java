package com.sozluk.comer.dictionary_app;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by comer on 20.07.2017.
 */
public class model extends RealmObject {

    public model(String dialogspinner, String dialogspinner2, String tr_text,String en_text) {
        this.dialogspinner = dialogspinner;
        this.dialogspinner2 = dialogspinner2;
        this.tr_text = tr_text;
        this.en_text = en_text;

    }
    public  model()
    {}

    public String getDialogspinner() {
        return dialogspinner;
    }

    public void setDialogspinner(String dialogspinner) {
        this.dialogspinner = dialogspinner;
    }

    public String getDialogspinner2() {
        return dialogspinner2;
    }

    public void setDialogspinner2(String dialogspinner2) {
        this.dialogspinner2 = dialogspinner2;
    }

    private String dialogspinner;
    private String dialogspinner2;
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
