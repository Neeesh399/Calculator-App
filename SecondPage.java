package Merlino.example.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Merlino.Calculator.R;

import Merlino.Merlino.Calculator.MainActivity;


public class SecondPage extends AppCompatActivity {
    public void openCalculator() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        Button btnCalculator = (Button) findViewById(R.id.btnCalculator);


        btnCalculator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openCalculator();
            }
        });
    }
}
