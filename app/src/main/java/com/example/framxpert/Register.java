package com.example.framxpert;

import android.widget.EditText;

public class Register
{
   public String n,e,Pass;
    public String etMobile, etVillage, etLand, etCrop;

    public Register() {
    }

    public Register(String n, String e, String pass, String etMobile, String etVillage, String etLand, String etCrop) {
        this.n = n;
        this.e = e;
        Pass = pass;
        this.etMobile = etMobile;
        this.etVillage = etVillage;
        this.etLand = etLand;
        this.etCrop = etCrop;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getEtMobile() {
        return etMobile;
    }

    public void setEtMobile(String etMobile) {
        this.etMobile = etMobile;
    }

    public String getEtVillage() {
        return etVillage;
    }

    public void setEtVillage(String etVillage) {
        this.etVillage = etVillage;
    }

    public String getEtLand() {
        return etLand;
    }

    public void setEtLand(String etLand) {
        this.etLand = etLand;
    }

    public String getEtCrop() {
        return etCrop;
    }

    public void setEtCrop(String etCrop) {
        this.etCrop = etCrop;
    }
}