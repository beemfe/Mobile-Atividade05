package br.edu.fateczl.atividade05;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Spinner spQtdNum;
    private RadioGroup radioGroup;
    private RadioButton rbD4;
    private RadioButton rbD6;
    private RadioButton rbD8;
    private RadioButton rbD10;
    private RadioButton rbD12;
    private RadioButton rbD20;
    private RadioButton rbD100;
    private Button btnGerar;
    private TextView tvSaida;
    private TextView tvEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spQtdNum = findViewById(R.id.spQtdNum);
        radioGroup = findViewById(R.id.radioGroup);
        rbD4 = findViewById(R.id.rbD4);
        rbD6 = findViewById(R.id.rbD6);
        rbD8 = findViewById(R.id.rbD8);
        rbD10 = findViewById(R.id.rbD10);
        rbD12 = findViewById(R.id.rbD12);
        rbD20 = findViewById(R.id.rbD20);
        rbD100 = findViewById(R.id.rbD100);
        btnGerar = findViewById(R.id.btnGerar);
        tvSaida = findViewById(R.id.tvSaida);
        tvEntrada = findViewById(R.id.tvEntrada);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.qtd_dados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQtdNum.setAdapter(adapter);

        btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerar();
            }
        });
    }

    private void gerar() {
        int quantidadeDados = Integer.parseInt(spQtdNum.getSelectedItem().toString());

        StringBuilder resultado = new StringBuilder();
        Random random = new Random();

        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);

        if (checkedRadioButton != null) {
            String tipoDado = checkedRadioButton.getText().toString();
            int maximoValorDado = 0;

            switch (tipoDado) {
                case "D4":
                    maximoValorDado = 4;
                    break;
                case "D6":
                    maximoValorDado = 6;
                    break;
                case "D8":
                    maximoValorDado = 8;
                    break;
                case "D10":
                    maximoValorDado = 10;
                    break;
                case "D12":
                    maximoValorDado = 12;
                    break;
                case "D20":
                    maximoValorDado = 20;
                    break;
                case "D100":
                    maximoValorDado = 100;
                    break;
                default:
                    return;
            }

            for (int i = 0; i < quantidadeDados; i++) {
                int valorDado = random.nextInt(maximoValorDado) + 1;
                resultado.append("Dado ").append(i + 1).append(": Face: ").append(valorDado);
                if (i < quantidadeDados - 1) {
                    resultado.append(", ");
                }
            }
        } else {
            return;
        }

        tvSaida.setText(resultado.toString());
    }
}