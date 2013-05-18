package com.jdsv650.TicXOToe;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import com.jdsv650.TicXOToe.R;

public class BoardActivity extends Activity implements OnClickListener, OnCheckedChangeListener {

	private int vsComputer;
	private int level = 1;
	private char[][] tictac;
	private int row,col;
	private boolean isPlayerX = true;
	private Random generator = new Random();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent();
		String pcStr = intent.getStringExtra("vComp");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

     	findViewById(R.id.GameOverTextView).setVisibility(View.INVISIBLE);
     	findViewById(R.id.ResetButton).setVisibility(View.INVISIBLE);

		vsComputer = Integer.parseInt(pcStr);
		
		if(vsComputer == 0)
		{
         	findViewById(R.id.radioButton1).setVisibility(View.INVISIBLE);
         	findViewById(R.id.radioButton2).setVisibility(View.INVISIBLE);
         	findViewById(R.id.radioButton3).setVisibility(View.INVISIBLE);
		}
		else
		{
			findViewById(R.id.radioButton1).setVisibility(View.VISIBLE);
         	findViewById(R.id.radioButton2).setVisibility(View.VISIBLE);
         	findViewById(R.id.radioButton3).setVisibility(View.VISIBLE);
		}
		
		
		View b1 = findViewById(R.id.imageButton1);
		b1.setOnClickListener(this);
		
		View b2 = findViewById(R.id.imageButton2);
		b2.setOnClickListener(this);
		
		View b3 = findViewById(R.id.imageButton3);
		b3.setOnClickListener(this);
		
		View b4 = findViewById(R.id.imageButton4);
		b4.setOnClickListener(this);
		
		View b5 = findViewById(R.id.imageButton5);
		b5.setOnClickListener(this);
		
		View b6 = findViewById(R.id.imageButton6);
		b6.setOnClickListener(this);
		
		View b7 = findViewById(R.id.imageButton7);
		b7.setOnClickListener(this);
		
		View b8 = findViewById(R.id.imageButton8);
		b8.setOnClickListener(this);
		
		View b9 = findViewById(R.id.imageButton9);
		b9.setOnClickListener(this);
		
		((RadioGroup)findViewById(R.id.radioGroup)).setOnCheckedChangeListener(this);
					
		initialize();	
	}

	public void disableButtons()
	{
		findViewById(R.id.imageButton1).setEnabled(false);
     	findViewById(R.id.imageButton2).setEnabled(false);
     	findViewById(R.id.imageButton3).setEnabled(false);
     	findViewById(R.id.imageButton4).setEnabled(false);
     	findViewById(R.id.imageButton5).setEnabled(false);
     	findViewById(R.id.imageButton6).setEnabled(false);
     	findViewById(R.id.imageButton7).setEnabled(false);
     	findViewById(R.id.imageButton8).setEnabled(false);
     	findViewById(R.id.imageButton9).setEnabled(false);
	}
	
	public void enableButtons()
	{
		findViewById(R.id.imageButton1).setEnabled(true);
     	findViewById(R.id.imageButton2).setEnabled(true);
     	findViewById(R.id.imageButton3).setEnabled(true);
     	findViewById(R.id.imageButton4).setEnabled(true);
     	findViewById(R.id.imageButton5).setEnabled(true);
     	findViewById(R.id.imageButton6).setEnabled(true);
     	findViewById(R.id.imageButton7).setEnabled(true);
     	findViewById(R.id.imageButton8).setEnabled(true);
     	findViewById(R.id.imageButton9).setEnabled(true);
	}
	
	
	@Override
	public void onClick(View v) {
	
		switch (v.getId()) {	
		    case R.id.imageButton1:
		    	getUserMove(31);
		    	break;
		    case R.id.imageButton2:
		    	getUserMove(32);
		    	break;
		    case R.id.imageButton3:
		    	getUserMove(33);
		    	break;
		    case R.id.imageButton4:
		    	getUserMove(21);
		    	break;
		    case R.id.imageButton5:
		    	getUserMove(22);
		    	break;
		    case R.id.imageButton6:
		    	getUserMove(23);
		    	break;
		    case R.id.imageButton7:
		    	getUserMove(11);
		    	break;
		    case R.id.imageButton8:
		    	getUserMove(12);
		    	break;
		    case R.id.imageButton9:
		    	getUserMove(13);
		    	break;
		}
	}
	
	
	void getUserMove(int num)
	{
	    switch (num) {
	        case 11:
	            row = 0;
	            col = 0;
	            break;
	        case 12:
	            row = 0;
	            col = 1;
	            break;
	        case 13:
	            row = 0;
	            col = 2;
	            break;
	        case 21:
	            row = 1;
	            col = 0;
	            break;
	        case 22:
	            row = 1;
	            col = 1;
	            break;
	        case 23:
	            row = 1;
	            col = 2;
	            break;
	        case 31:
	            row = 2;
	            col = 0;
	            break;
	        case 32:
	            row = 2;
	            col = 1;
	            break;
	        case 33:
	            row = 2;
	            col = 2;
	            break;
	        default:
	            break;
	    }
	    
	    if(tictac[row][col] != '-')
	    {
	        return;
	    }
	    
	    if(isPlayerX && vsComputer == 0) {
	        makeMove(row,col, 'X');
	        tictac[row][col] = 'X';  /* make move for 'X' */
	        if (checkIfWon('X') == 1)  {
	          //  NSLog(@"X wins\n");
	            ShowWinWithButton('X');
	        }
	        else
	            if(isBoardFull())
	            {
	                ShowWinWithButton('T');
	            }
	        isPlayerX = false;
	    }
	    else
	        if(!isPlayerX && vsComputer == 0)
	        {
	            makeMove(row,col, 'O');
	            tictac[row][col] = 'O';  /* make move for 'O' */
	            if (checkIfWon('O') == 1)  {
	             //   NSLog(@"O wins\n");
	                ShowWinWithButton('O');
	            }
	            else
	                if(isBoardFull())
	                {
	                    ShowWinWithButton('T');
	                }
	            isPlayerX = true;
	        }
	        else  // versus computer
	        {
	            makeMove(row,col, 'X');
	            tictac[row][col] = 'X';  /* make move for 'X' */
	        }
	    
	    if(vsComputer == 1 && level == 2)
	    {
	        if (checkIfWon('X') == 1)  {
	          //  NSLog(@"X wins\n");
	            ShowWinWithButton('X');
	        }
	        else
	            if (winPossible('O') == true) {
	             //   NSLog(@"O WINS\n");
	                ShowWinWithButton('O');
	            }
	            else
	                if (winPossible('X') == false) {
	                    /* if opponent does not have to be blocked make move */
	                    computerMove();
	                }
	    }
	    else  if(vsComputer == 1 && level == 0)
	    {
	        if (checkIfWon('X') == 1)  {
	          //  NSLog(@"X wins\n");
	           ShowWinWithButton('X');
	        }
	        else
	            if (checkIfWon('O') == 1) {
	             //   NSLog(@"O WINS\n");
	                ShowWinWithButton('O');
	            }
	            else
	                {
	                    computerMove();
	                    if (checkIfWon('O') == 1) {
	                      //  NSLog(@"O WINS\n");
	                        ShowWinWithButton('O');
	                    }
	                }
	        
	    }
	    else  if(vsComputer == 1 && level == 1) //medium diff
	    {
	        if (checkIfWon('X') == 1)  {
	         //   NSLog(@"X wins\n");
	           ShowWinWithButton('X');
	        }
	        else
	            if (checkIfWon('O') == 1) {
	              //  NSLog(@"O WINS\n");
	                ShowWinWithButton('O');
	            }
	            else
	            {
	                computerMove();
	                if (checkIfWon('O') == 1) {
	                  //  NSLog(@"O WINS\n");
	                    ShowWinWithButton('O');
	                }
	            }
	        
	    }
	    
	}

	 
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		 switch (checkedId) 
		 {
         case R.id.radioButton1:
                level = 0;
                initialize();
                 break;
         case R.id.radioButton2:
        	 	level = 1;
        	 	initialize();
                 break;
         case R.id.radioButton3:
        	 	level = 2;
        	     initialize(); 
                 break;
         }	
	}

	void initialize()
	{
	    tictac= new char[3][3];
	    int i, j;  
	    
	    for (i=0; i<3; i++)   /* set elements = to dash - */
	        for(j=0; j<3; j++)
	            tictac[i][j] = '-';
	    
     	findViewById(R.id.GameOverTextView).setVisibility(View.INVISIBLE);
     	findViewById(R.id.ResetButton).setVisibility(View.INVISIBLE);
	    
	    ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton1);
    	btn1.setImageResource(R.drawable.xobackground);
    	ImageButton btn2 = (ImageButton)findViewById(R.id.imageButton2);
    	btn2.setImageResource(R.drawable.xobackground);
    	ImageButton btn3 = (ImageButton)findViewById(R.id.imageButton3);
    	btn3.setImageResource(R.drawable.xobackground);
    	ImageButton btn4 = (ImageButton)findViewById(R.id.imageButton4);
    	btn4.setImageResource(R.drawable.xobackground);
    	ImageButton btn5 = (ImageButton)findViewById(R.id.imageButton5);
    	btn5.setImageResource(R.drawable.xobackground);
    	ImageButton btn6 = (ImageButton)findViewById(R.id.imageButton6);
    	btn6.setImageResource(R.drawable.xobackground);
    	ImageButton btn7 = (ImageButton)findViewById(R.id.imageButton7);
    	btn7.setImageResource(R.drawable.xobackground);
    	ImageButton btn8 = (ImageButton)findViewById(R.id.imageButton8);
    	btn8.setImageResource(R.drawable.xobackground);
    	ImageButton btn9 = (ImageButton)findViewById(R.id.imageButton9);
    	btn9.setImageResource(R.drawable.xobackground);	
    	
    	enableButtons();
    	isPlayerX = true;
 
	}
	
	
	void computerMove() /* if no win or block make a move */
	{
	    int rowInd, colInd;
	    
	    if(level == 2) {
	        if(tictac[2][1] == 'X' && tictac [1][2] =='X' && tictac[2][2] == '-') {
	            tictac[2][2] = 'O';
	            //2-2
	            ImageButton btn3 = (ImageButton)findViewById(R.id.imageButton3);
	        	btn3.setImageResource(R.drawable.o);	
	        }
	        else if(tictac[1][1] == '-')  {
	            tictac[1][1] = 'O';
	            // 1-1
	            ImageButton btn5 = (ImageButton)findViewById(R.id.imageButton5);
	        	btn5.setImageResource(R.drawable.o);	
	            
	        }
	        else if(tictac[0][0] == '-' && (tictac[0][2] != 'X' || tictac[2][0] != 'X'))  {
	            tictac[0][0] = 'O';
	            // 1-1
	            ImageButton btn7 = (ImageButton)findViewById(R.id.imageButton7);
	        	btn7.setImageResource(R.drawable.o);	
	            
	        }
	        else if (tictac[2][2] == '-' && (tictac[0][2] !='X' || tictac[2][0] !='X'))   {
	            tictac[2][2] = 'O';
	            //2-2
	            ImageButton btn3 = (ImageButton)findViewById(R.id.imageButton3);
	        	btn3.setImageResource(R.drawable.o);	
	        }
	        else if (tictac[2][0] == '-'&& (tictac[0][0] != 'X' || tictac[2][2] != 'X')) {
	            tictac[2][0] = 'O';
	            //2-0
	            ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton1);
	        	btn1.setImageResource(R.drawable.o);	
	            
	        }
	        else if (tictac[0][2] == '-' && (tictac[0][0] !='X' || tictac[2][2] !='X')) {
	            tictac[0][2] = 'O';
	            // 0-2
	            ImageButton btn9 = (ImageButton)findViewById(R.id.imageButton9);
	        	btn9.setImageResource(R.drawable.o);	
	            
	        }
	        else if (tictac[1][0] == '-') {
	            tictac[1][0] = 'O';
	            // 1-0
	            ImageButton btn4 = (ImageButton)findViewById(R.id.imageButton4);
	        	btn4.setImageResource(R.drawable.o);	
	            
	        }
	        else if (tictac[2][1] == '-') {
	            tictac[2][1] = 'O';
	            // drawO(2,1,2);
	            ImageButton btn2 = (ImageButton)findViewById(R.id.imageButton2);
	        	btn2.setImageResource(R.drawable.o);	
	            
	        }
	        else if (tictac[1][2] == '-') {
	            tictac[1][2] = 'O';
	            // drawO(1,2,2);
	            ImageButton btn6 = (ImageButton)findViewById(R.id.imageButton6);
	        	btn6.setImageResource(R.drawable.o);	
	            
	        }
	        else if (tictac[0][1] == '-')  {
	            tictac[0][1] = 'O';
	            // drawO(0,1,2);
	            ImageButton btn8 = (ImageButton)findViewById(R.id.imageButton8);
	        	btn8.setImageResource(R.drawable.o);	
	            
	        }
	        else
	        {
	           // NSLog(@"TIE GAME\n");
	            ShowWinWithButton('T');
	        }
	    }
	    
	    if(level == 0)  // just choose a random space
	    {
	       // NSLog(@"Easy level");
	        
	        if(isBoardFull())
	        {
	            ShowWinWithButton('T');
	            return;
	        }
	         
	        rowInd = generator.nextInt(3);  // 0--2
	        colInd = generator.nextInt(3);
	        
	        while (tictac[rowInd][colInd] != '-')
	        {
	            rowInd = generator.nextInt(3);
	            colInd = generator.nextInt(3);
	        }
	             
	        tictac[rowInd][colInd] = 'O';
	        makeMoveWithRow(rowInd, colInd);
	    }
	    
	    if(level == 1)  // medium level block and take win if avail
	    {
	      //  NSLog(@"Medium level");
	        
	        if(isBoardFull())
	        {
	            ShowWinWithButton('T');
	            return;
	        }
	        
	        
	        if(winPossible('O'))
	        {
	            return;
	        }
	        else if (winPossible('X')) //block?
	        {
	            return;
	        }
	        else // no win or block make random move
	        {
	            rowInd =  generator.nextInt(3);
	            colInd = generator.nextInt(3);
	        
	            while (tictac[rowInd][colInd] != '-')
	            {
	                rowInd = generator.nextInt(3);
	                colInd = generator.nextInt(3);
	            }
	        
	            tictac[rowInd][colInd] = 'O';
	            makeMoveWithRow(rowInd, colInd);
	        }
	    }

	}
	
	
	boolean winPossible(char letter)
	{
	    int row,col;
	    
	    for(row =0; row<3; row++)
	    {
	        for(col=0; col<3; col++)
	        {
	            if (tictac[row][col] == '-')
	            {
	                tictac[row][col] = letter;
	                if(checkIfWon(letter) == 0)
	                    tictac[row][col] = '-';
	                else
	                {
	                    tictac[row][col] = 'O';
	                    // drawO(row,col,2);
	                    //block
						if(row == 0 & col == 0) {
	                    	ImageButton btn7;
	                    	btn7 = (ImageButton)findViewById(R.id.imageButton7);
	    	        		btn7.setImageResource(R.drawable.o);	
						}
	                    if(row == 0 & col == 1)
	                    {
	                    	ImageButton btn8;
	                    	btn8 = (ImageButton)findViewById(R.id.imageButton8);
	    	        		btn8.setImageResource(R.drawable.o);	
	                    }
	                    if(row == 0 & col == 2)
	                    {
	                    	ImageButton btn9;
	                    	btn9 = (ImageButton)findViewById(R.id.imageButton9);
	    	        		btn9.setImageResource(R.drawable.o);	
	                    }	                    
	                    if(row == 1 & col == 0)
	                    {
	                    	ImageButton btn4;
	                    	btn4 = (ImageButton)findViewById(R.id.imageButton4);
	    	        		btn4.setImageResource(R.drawable.o);	
	                    }	                    
	                    if(row == 1 & col == 1) 
	                    {
	                    	ImageButton btn5;
	                    	btn5 = (ImageButton)findViewById(R.id.imageButton5);
	                    	btn5.setImageResource(R.drawable.o);	
	                    }	                    
	                    if(row == 1 & col == 2)
	                    {
	                    	ImageButton btn6;
	                    	btn6 = (ImageButton)findViewById(R.id.imageButton6);
	    	        		btn6.setImageResource(R.drawable.o);	
	                    }	                    
	                    if(row == 2 & col == 0)
	                    {
	                    	ImageButton btn1;
	                    	btn1 = (ImageButton)findViewById(R.id.imageButton1);
	    	        		btn1.setImageResource(R.drawable.o);	
	                    }	                    
	                    if(row == 2 & col == 1)
	                    {
	                    	ImageButton btn2;
	                    	btn2 = (ImageButton)findViewById(R.id.imageButton2);
	    	        		btn2.setImageResource(R.drawable.o);	
	                    }	                    
	                    if(row == 2 & col == 2)
	                    {
	                    	ImageButton btn3;
	                    	btn3 = (ImageButton)findViewById(R.id.imageButton3);
	    	        		btn3.setImageResource(R.drawable.o);	
	                    }
	                    return (true);
	                }
	            }
	        }
	    }
	    
	    return(false);
	}

	boolean isBoardFull()
	{
	    boolean isFull = true;
	    
	    for(int i=0; i<3; i++)
	        for(int j=0; j<3; j++)
	        {
	            if(tictac[i][j] == '-')
	            {
	                isFull = false;
	            }
	        }
	    
	    return isFull;
	}


	void makeMove(int row, int col, char xoChar)
	{
	  if(xoChar == 'O')
	  {
	    switch (row) {
	        case 0:
	            if(col == 0)
	            {
	            	ImageButton btn7 = (ImageButton)findViewById(R.id.imageButton7);
	 	        	btn7.setImageResource(R.drawable.o);
	            }
	            else if(col == 1)
	            {
	            	ImageButton btn8 = (ImageButton)findViewById(R.id.imageButton8);
	 	        	btn8.setImageResource(R.drawable.o);
	 
	            }
	            else if(col == 2)
	            {
	                ImageButton btn9 = (ImageButton)findViewById(R.id.imageButton9);
	 	        	btn9.setImageResource(R.drawable.o);
	                
	            }
	            break;
	        case 1:
	            if(col == 0)
	            {
	            	ImageButton btn4 = (ImageButton)findViewById(R.id.imageButton4);
	 	        	btn4.setImageResource(R.drawable.o);
	            }
	            else if(col == 1)
	            {
	            	 ImageButton btn5 = (ImageButton)findViewById(R.id.imageButton5);
	 	        	btn5.setImageResource(R.drawable.o);
	                
	            }
	            else if(col == 2)
	            {
	            	 ImageButton btn6 = (ImageButton)findViewById(R.id.imageButton6);
	 	        	btn6.setImageResource(R.drawable.o);
	                
	            }
	            break;
	        case 2:
	            if(col == 0)
	            {
	            	 ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton1);
	 	        	btn1.setImageResource(R.drawable.o);
	            }
	            else if(col == 1)
	            {
	            	 ImageButton btn2 = (ImageButton)findViewById(R.id.imageButton2);
	 	        	btn2.setImageResource(R.drawable.o);
	                
	            }
	            else if(col == 2)
	            {
	            	 ImageButton btn3 = (ImageButton)findViewById(R.id.imageButton3);
	 	        	btn3.setImageResource(R.drawable.o);
	                
	            }
	            break;
	        default:
	            break;
	    }
	  }
	  else if (xoChar == 'X')
	  {
		  switch (row) {
	        case 0:
	            if(col == 0)
	            {
	            	ImageButton btn7 = (ImageButton)findViewById(R.id.imageButton7);
	 	        	btn7.setImageResource(R.drawable.x);
	            }
	            else if(col == 1)
	            {
	            	ImageButton btn8 = (ImageButton)findViewById(R.id.imageButton8);
	 	        	btn8.setImageResource(R.drawable.x);
	 
	            }
	            else if(col == 2)
	            {
	                ImageButton btn9 = (ImageButton)findViewById(R.id.imageButton9);
	 	        	btn9.setImageResource(R.drawable.x);
	                
	            }
	            break;
	        case 1:
	            if(col == 0)
	            {
	            	ImageButton btn4 = (ImageButton)findViewById(R.id.imageButton4);
	 	        	btn4.setImageResource(R.drawable.x);
	            }
	            else if(col == 1)
	            {
	            	 ImageButton btn5 = (ImageButton)findViewById(R.id.imageButton5);
	 	        	btn5.setImageResource(R.drawable.x);
	                
	            }
	            else if(col == 2)
	            {
	            	 ImageButton btn6 = (ImageButton)findViewById(R.id.imageButton6);
	 	        	btn6.setImageResource(R.drawable.x);
	                
	            }
	            break;
	        case 2:
	            if(col == 0)
	            {
	            	 ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton1);
	 	        	btn1.setImageResource(R.drawable.x);
	            }
	            else if(col == 1)
	            {
	            	 ImageButton btn2 = (ImageButton)findViewById(R.id.imageButton2);
	 	        	btn2.setImageResource(R.drawable.x);
	                
	            }
	            else if(col == 2)
	            {
	            	 ImageButton btn3 = (ImageButton)findViewById(R.id.imageButton3);
	 	        	btn3.setImageResource(R.drawable.x);
	                
	            }
	            break;
	        default:
	            break;
	    }
	  }
	  
	  
	}

	
	
	
	void makeMoveWithRow(int row, int col)
	{
	    switch (row) {
	        case 0:
	            if(col == 0)
	            {
	            	ImageButton btn7 = (ImageButton)findViewById(R.id.imageButton7);
	 	        	btn7.setImageResource(R.drawable.o);
	            }
	            else if(col == 1)
	            {
	            	ImageButton btn8 = (ImageButton)findViewById(R.id.imageButton8);
	 	        	btn8.setImageResource(R.drawable.o);
	 
	            }
	            else if(col == 2)
	            {
	                ImageButton btn9 = (ImageButton)findViewById(R.id.imageButton9);
	 	        	btn9.setImageResource(R.drawable.o);
	                
	            }
	            break;
	        case 1:
	            if(col == 0)
	            {
	            	ImageButton btn4 = (ImageButton)findViewById(R.id.imageButton4);
	 	        	btn4.setImageResource(R.drawable.o);
	            }
	            else if(col == 1)
	            {
	            	 ImageButton btn5 = (ImageButton)findViewById(R.id.imageButton5);
	 	        	btn5.setImageResource(R.drawable.o);
	                
	            }
	            else if(col == 2)
	            {
	            	 ImageButton btn6 = (ImageButton)findViewById(R.id.imageButton6);
	 	        	btn6.setImageResource(R.drawable.o);
	                
	            }
	            break;
	        case 2:
	            if(col == 0)
	            {
	            	 ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton1);
	 	        	btn1.setImageResource(R.drawable.o);
	            }
	            else if(col == 1)
	            {
	            	 ImageButton btn2 = (ImageButton)findViewById(R.id.imageButton2);
	 	        	btn2.setImageResource(R.drawable.o);
	                
	            }
	            else if(col == 2)
	            {
	            	 ImageButton btn3 = (ImageButton)findViewById(R.id.imageButton3);
	 	        	btn3.setImageResource(R.drawable.o);
	                
	            }
	            break;
	        default:
	            break;
	    }
	}


	int checkIfWon(char letter)
	{
	    int i, j, winner = 0;   /* did someone win ? */
	    
	    for (i=0; i<3; i++)
	        if (tictac[i][0]==letter && tictac[i][1]==letter && tictac[i][2]==letter)
	            winner = 1;
	    
	    for (j=0;j<3;j++)
	        if(tictac[0][j]==letter && tictac[1][j]==letter && tictac[2][j]==letter)
	            winner = 1;
	    
	    if(tictac[0][0]==letter && tictac[1][1]==letter && tictac[2][2]==letter)
	        winner = 1;
	    if(tictac[0][2]==letter && tictac[1][1]==letter && tictac[2][0] == letter)
	        winner = 1;
	    
	    return (winner);
	}

	
	void ShowWin(char letterToWin)
	{
	    if(letterToWin == 'T')
	    {
	    	AlertDialog.Builder builderTie = new AlertDialog.Builder(this);
            builderTie.setMessage("Cat's Game");
            builderTie.setCancelable(true);
            builderTie.setPositiveButton("Reset",
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    initialize();
                }
            });
   
            AlertDialog alertTie = builderTie.create();
            
            alertTie.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlp = alertTie.getWindow().getAttributes();

            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
            wmlp.x = 100;   //x position
            wmlp.y = 50;   //y position
            
            alertTie.show();
            TextView messageView = (TextView)alertTie.findViewById(android.R.id.message);
            messageView.setGravity(Gravity.CENTER);
	    }
	    else
	    {
	    	AlertDialog.Builder builderOver = new AlertDialog.Builder(this);
	    	if(letterToWin == 'O')
	    	{
	    		builderOver.setMessage("O Wins");
	    	}
	    	else
	    		builderOver.setMessage("X Wins");
            builderOver.setCancelable(true);
            builderOver.setPositiveButton("Reset",
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    initialize();
                }
            });
   
            AlertDialog alertOver = builderOver.create();
            
            alertOver.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlp = alertOver.getWindow().getAttributes();

            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
            wmlp.x = 100;   //x position
            wmlp.y = 50;   //y position
            
            alertOver.show();
            TextView messageView = (TextView)alertOver.findViewById(android.R.id.message);
            messageView.setGravity(Gravity.CENTER);
	    }
	}
	
	
	
	void ShowWinWithButton(char letterToWin)
	{
		TextView tv = (TextView)  this.findViewById(R.id.GameOverTextView);
		disableButtons();
	
	    if(letterToWin == 'T')
	    {			
		    tv.setText("Cat's Game");
	    }
	    else
	    {
	    	if(letterToWin == 'O')
	    	{
			    tv.setText("O Wins");

	    	}
	    	else
			    tv.setText("X Wins");
	    }
	    
		findViewById(R.id.GameOverTextView).setVisibility(View.VISIBLE);
     	findViewById(R.id.ResetButton).setVisibility(View.VISIBLE);
	      
	}
	
	public void ResetButtonClicked(View v)
	{
		initialize();
	}
	
}
