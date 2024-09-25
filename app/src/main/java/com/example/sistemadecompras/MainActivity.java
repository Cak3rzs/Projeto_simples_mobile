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
    private Button btpagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkarroz = findViewById(R.id.chkarroz);
        chkleite = findViewById(R.id.chkleite);
        chkcarne = findViewById(R.id.chkcarne);
        chkfeijao = findViewById(R.id.chkfeijao);
        Button bttotal = findViewById(R.id.bttotal);
        btpagamento = findViewById(R.id.btpagamento);

        bttotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                double total = calcularTotal();
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Alerta");
                dialogo.setMessage("O valor de suas compras foi de: R$ " + total + "  escolha sua forma de pagamento abaixo:");
                dialogo.setNeutralButton("Ok", (dialog, which) -> {
                    if (total > 0) {
                        btpagamento.setVisibility(View.VISIBLE); // Show payment button
                    }
                });
                dialogo.show();
            }
        });

        btpagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                double total = calcularTotal();

                if (total > 0) {
                    String[] opcoesPagamento = {"Cartão de Crédito", "Cartão de Débito", "Dinheiro", "Boleto", "Pix"};
                    AlertDialog.Builder dialogoPagamento = new AlertDialog.Builder(MainActivity.this);
                    dialogoPagamento.setTitle("Escolha um método de pagamento")
                            .setItems(opcoesPagamento, (dialog, which) -> {
                                String metodo = opcoesPagamento[which];
                                finalizarPagamento(metodo, total);
                            });
                    dialogoPagamento.show();
                } else {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setMessage("Por favor, selecione um produto antes de pagar.")
                            .setNeutralButton("Ok", null)
                            .show();
                }
            }
        });
    }

    private double calcularTotal() {
        double total = 0;
        if (chkarroz.isChecked()) total += 35.00;
        if (chkleite.isChecked()) total += 5.00;
        if (chkcarne.isChecked()) total += 45.50;
        if (chkfeijao.isChecked()) total += 10.50;
        return total;
    }

    private void finalizarPagamento(String metodo, double total) {
        AlertDialog.Builder dialogoConfirmacao = new AlertDialog.Builder(MainActivity.this);
        dialogoConfirmacao.setMessage("Você escolheu " + metodo + ". Total: R$ " + total + ". Pagamento realizado com sucesso!")
                .setNeutralButton("Ok", null)
                .show();
    }
}
