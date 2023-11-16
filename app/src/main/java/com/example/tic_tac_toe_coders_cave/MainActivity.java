package com.example.tic_tac_toe_coders_cave;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tic_tac_toe_coders_cave.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int[]boxPositions ={0,0,0,
                                0,0,0,
                                0,0,0};
    private final List<int[]> winnerPatterns = new ArrayList<>();
    private int playerTurn = 1;
    private int selectedBoxes = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get player names and show in view
        binding.playerOne.setText(getIntent().getStringExtra("PlayerOne"));
        binding.playerTwo.setText(getIntent().getStringExtra("PlayerTwo"));

        binding.playerOneLayout.setBackgroundResource(R.drawable.white_box_black_border);

        //assigning winning patterns
        winnerPatterns.add(new int[] {0,1,2});
        winnerPatterns.add(new int[] {3,4,5});
        winnerPatterns.add(new int[] {6,7,8});
        winnerPatterns.add(new int[] {0,3,6});
        winnerPatterns.add(new int[] {1,4,7});
        winnerPatterns.add(new int[] {2,5,8});
        winnerPatterns.add(new int[] {2,4,6});
        winnerPatterns.add(new int[] {0,4,8});

        binding.restartGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartMatch();
                binding.playerOneLayout.setBackgroundResource(R.drawable.white_box_black_border);
                binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box);
            }
        });

        binding.imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotClicked(0)){
                    // if box is not clicked , it will perform further action/steps
                    performAction((ImageView) view, 0);
                }
            }
        });
        binding.imageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotClicked(1)){
                    performAction((ImageView) view, 1);
                }
            }
        });
        binding.imageThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotClicked(2)){
                    performAction((ImageView) view, 2);
                }
            }
        });
        binding.imageFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotClicked(3)){
                    performAction((ImageView) view, 3);
                }
            }
        });
        binding.imageFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotClicked(4)){
                    performAction((ImageView) view, 4);
                }
            }
        });
        binding.imageSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotClicked(5)){
                    performAction((ImageView) view, 5);
                }
            }
        });
        binding.imageSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotClicked(6)){
                    performAction((ImageView) view, 6);
                }
            }
        });
        binding.imageEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotClicked(7)){
                    performAction((ImageView) view, 7);
                }
            }
        });
        binding.imageNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotClicked(8)){
                    performAction((ImageView) view, 8);
                }
            }
        });

    }


    private boolean isNotClicked(int position) {
        boolean result = false;
        if (boxPositions[position] == 0){
            // if box is previously not clicked, it will return true
            result = true;
        }
        return result;
    }
    private void performAction(ImageView view, int selectedBoxPosition){
        boxPositions[selectedBoxPosition] = playerTurn;
        if (playerTurn == 1){
            // if playerTurn = 1 is selected , it will print "X"
            view.setImageResource(R.drawable.x_icon);

            if (isAnyoneWinner()){
                // then it will check if any winningPattern matches..!
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle("Player "+binding.playerOne.getText().toString()+" won")
                        .setMessage("Play Again..!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                restartMatch();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
//                                startActivity(new Intent(getApplicationContext(), StartActivity.class));
                                getOnBackPressedDispatcher().onBackPressed();
                                finish();
                            }
                        })
                        .show();
            } else if (selectedBoxes == 9) {
                // then it will check if all boxes are selected...!
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle("Match Draw")
                        .setMessage("Play Again..!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                restartMatch();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                getOnBackPressedDispatcher().onBackPressed();
                                finish();
                            }
                        })
                        .show();
            } else {
                // if above both conditions don't match, playerTurn will be changed & number of selectedBoxes will ++
                changePlayerTurn(2);
                selectedBoxes++;
            }

        }else {
            view.setImageResource(R.drawable.o_icon);

            // above same steps goes if playerTurn != 1 (i.e) player 2
            if (isAnyoneWinner()){

                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle("Player "+binding.playerTwo.getText().toString()+" won")
                        .setMessage("Play Again..!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                restartMatch();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                getOnBackPressedDispatcher().onBackPressed();
                                finish();
                            }
                        })
                        .show();
            } else if (selectedBoxes == 9) {
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle("Match Draw")
                        .setMessage("Play Again..!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                restartMatch();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                getOnBackPressedDispatcher().onBackPressed();
                                finish();
                            }
                        })
                        .show();
            } else {
                changePlayerTurn(1);
                selectedBoxes++;
            }
        }
    }

    // will change player turn
    private void changePlayerTurn(int giveTurnToPlayer) {
        playerTurn = giveTurnToPlayer;
        if (playerTurn == 1){
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box_black_border);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box);
        }else {
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box_black_border);
        }
    }

    // will restart match again
    private void restartMatch() {
        boxPositions = new int[] {0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        selectedBoxes = 1;

        binding.imageOne.setImageResource(R.drawable.white_box);
        binding.imageTwo.setImageResource(R.drawable.white_box);
        binding.imageThree.setImageResource(R.drawable.white_box);
        binding.imageFour.setImageResource(R.drawable.white_box);
        binding.imageFive.setImageResource(R.drawable.white_box);
        binding.imageSix.setImageResource(R.drawable.white_box);
        binding.imageSeven.setImageResource(R.drawable.white_box);
        binding.imageEight.setImageResource(R.drawable.white_box);
        binding.imageNine.setImageResource(R.drawable.white_box);

    }

    private boolean isAnyoneWinner() {
        boolean result = false;

        for (int i=0; i<winnerPatterns.size(); i++){
            int[] match = winnerPatterns.get(i);

            if (boxPositions[match[0]]==playerTurn && boxPositions[match[1]]==playerTurn && boxPositions[match[2]]==playerTurn){
                result = true;
            }
        }
        return result;
    }
}