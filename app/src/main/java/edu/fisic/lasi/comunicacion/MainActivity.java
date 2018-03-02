package edu.fisic.lasi.comunicacion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txt_usuario)  EditText usuario;
    @BindView(R.id.txt_password)  EditText password;
    @BindView(R.id.rg_color)  RadioGroup rgColor;

    private SharedPreferences p;
    private SharedPreferences.Editor edit;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        ((RadioButton) rgColor.getChildAt(0)).setChecked(true);
        p = PreferenceManager.getDefaultSharedPreferences(this);
        if (!p.getString("USUARIO", "").equals("")) {
            intent = new Intent(this, SegundaActivity.class);
            startActivityForResult(intent, 777);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777) {
            if (!p.getString("USUARIO","").equals("")) {
                finish();
            }
        }
    }

    @OnClick(R.id.btn_ingreso)
    public void clickEnBoton(View v) {

        int color;
       if (usuario.getText().toString().equals("Julio") && password.getText().toString().equals("Alfaro")) {

           edit = p.edit();
           intent = new Intent(this, SegundaActivity.class);
             if (((RadioButton)rgColor.getChildAt(0)).isChecked()) {
                 color = Color.rgb(255, 0, 0);
             } else if (((RadioButton)rgColor.getChildAt(1)).isChecked()) {
                 color = Color.rgb(0, 0,255);
             } else {
                 color = Color.rgb(0,255,0);
             }
             edit.putString("USUARIO", usuario.getText().toString());
             edit.putInt("COLOR", color);
             edit.commit();
             intent.putExtra("BK_COLOR", color);
             startActivityForResult(intent, 777);
       } else {
           Toast.makeText(this, "Su usuario y contrasena es invalido", Toast.LENGTH_LONG).show();
       }
    }
}
