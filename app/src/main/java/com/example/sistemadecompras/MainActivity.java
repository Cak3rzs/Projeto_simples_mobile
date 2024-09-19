package com.example.sistemadecompras;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Button;
import android.app.AlertDialog;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CheckBox chkarroz;
    private CheckBox chkleite;
    private CheckBox chkcarne;
    private CheckBox chkfeijao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkarroz = findViewById(R.id.chkarroz);
        chkleite = findViewById(R.id.chkleite);
        chkcarne = findViewById(R.id.chkcarne);
        chkfeijao = findViewById(R.id.chkfeijao);
        Button bttotal = findViewById(R.id.bttotal);

        bttotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                double total = 0;
                if (chkarroz.isChecked())
                    total += 35.00;
                if (chkleite.isChecked())
                    total += 5.00;
                if (chkcarne.isChecked())
                    total += 45.50;
                if (chkfeijao.isChecked())
                    total += 10.50;

                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Alerta");
                dialogo.setMessage("O valor de suas compras foi de: R$ " + total);
                dialogo.setNeutralButton("Ok", null);
                dialogo.show();
            }
        });
    }
}
