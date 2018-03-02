package edu.fisic.lasi.comunicacion;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Milton Ramirez on 26/02/2018.
 */

public class SegundaActivity extends AppCompatActivity {
    @BindView(R.id.lyt_principal)  LinearLayout lytPrincipal;
    SharedPreferences p;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        int color;
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.segunda_layout);
        ButterKnife.bind(this);
        p = PreferenceManager.getDefaultSharedPreferences(this);
        color = getIntent().getIntExtra("BK_COLOR", Color.WHITE);
        if (color == Color.WHITE) {
            color = p.getInt("COLOR", Color.WHITE);
        }
        lytPrincipal.setBackgroundColor(color);
    }

    @OnClick(R.id.btn_logout)
    public void logout(View v) {
        SharedPreferences.Editor edit;
        edit = p.edit();
        edit.putString("USUARIO", "");
        edit.commit();
        finish();
    }
}
