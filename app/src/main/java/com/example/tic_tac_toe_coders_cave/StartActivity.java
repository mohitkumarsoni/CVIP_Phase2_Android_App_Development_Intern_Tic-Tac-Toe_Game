package com.example.tic_tac_toe_coders_cave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tic_tac_toe_coders_cave.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {
    ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String playerOne = binding.playerOneName.getText().toString().trim();
                String playerTwo = binding.playerTwoName.getText().toString().trim();

                if (playerOne.isEmpty() ){
                    binding.playerOneName.setError("Enter Name !");
                }else if (playerTwo.isEmpty() ){
                    binding.playerTwoName.setError("Enter Name !");
                } else if ( !playerOne.isEmpty() && !playerTwo.isEmpty() ) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("PlayerOne", playerOne);
                    intent.putExtra("PlayerTwo", playerTwo);
                    startActivity(intent);
                }
            }
        });
    }
}