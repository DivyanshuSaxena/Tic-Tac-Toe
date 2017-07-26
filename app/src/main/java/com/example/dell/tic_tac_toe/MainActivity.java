package com.example.dell.tic_tac_toe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int arr[][] = new int[3][3];//Initial array on which moves are to be made
    int temp[][] = new int[3][3];//Temporary array for passing

    void makeTemp(int[][] a, int[][] t) {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                t[i][j] = a[i][j];
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                arr[i][j] = 2;
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int predictMove(int[][] a, int val) {
        int sc = 0, max = -10, min = 10, ex_i = 1, ex_j = 0, flag=0;
        int t[][] = new int[3][3];

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(a[i][j]==2) {
                    //System.out.println("In predictMove() with value "+val);
                    makeTemp(a,t);
                    flag = 1;
                    sc = score(t,i,j,val);
                    if(val==0) {
                        if(sc>=max) {
                            max = sc;
                            ex_i = i;
                            ex_j = j;
                        }
                    }
                    else {
                        if(sc<=min) {
                            min = sc;
                            ex_i = i;
                            ex_j = j;
                        }
                    }
                }
            }
        }
        if (flag==0)
            return -1;
        //System.out.println("This is the best next move, "+(ex_i*10+ex_j)+" for value "+val);
        return (ex_i*10+ex_j);
    }

    int score(int[][] a, int i, int j, int val) {
        a[i][j] = val;
        int t[][] = new int[3][3];
        makeTemp(a,t);
        //System.out.println("In score(), change made at "+i+","+j+" with value "+val);

        int sc = 0, flag = 0;
        for(int l=0;l<3;l++) {
            if(a[l][0]==a[l][1] && a[l][1]==a[l][2] && a[l][0]==1)
                sc = -10;
            else if(a[l][0]==a[l][1] && a[l][1]==a[l][2] && a[l][0]==0)
                sc = 10;
            else if(a[0][l]==a[1][l] && a[1][l]==a[2][l] && a[0][l]==1)
                sc = -10;
            else if(a[0][l]==a[1][l] && a[1][l]==a[2][l] && a[0][l]==0)
                sc = 10;
        }
        if(sc==0) {
            if(a[0][0]==a[1][1] && a[1][1]==a[2][2] && a[1][1]==1)
                sc = -10;
            else if(a[0][0]==a[1][1] && a[1][1]==a[2][2] && a[1][1]==0)
                sc = 10;
            else if(a[0][2]==a[1][1] && a[1][1]==a[2][0] && a[1][1]==1)
                sc = -10;
            else if(a[0][2]==a[1][1] && a[1][1]==a[2][0] && a[1][1]==0)
                sc = 10;
        }
        for(int i1=0; i1<3; i1++) {
            for(int j1=0; j1<3; j1++) {
                if(a[i1][j1]==2)
                    flag = 1;
            }
        }
        if (flag==0) {
            //System.out.println("Array full, returning "+sc);
            return sc;
        }
        if(sc==10 || sc==-10) {
            //System.out.println("Someone has won, returning "+sc);
            return sc;
        }
        else {
            val = (val+1)%2;
            int nextmove = predictMove(t,val);
            //System.out.println(nextmove+" nxtmv");
            return score(t,nextmove/10,nextmove%10,val);
        }
    }

    public void reset(View view) {
        Button nxtbtn0 = (Button) findViewById(R.id.button4);
        Button nxtbtn1 = (Button) findViewById(R.id.button3);
        Button nxtbtn2 = (Button) findViewById(R.id.button2);
        Button nxtbtn3 = (Button) findViewById(R.id.button7);
        Button nxtbtn4 = (Button) findViewById(R.id.button6);
        Button nxtbtn5 = (Button) findViewById(R.id.button5);
        Button nxtbtn6 = (Button) findViewById(R.id.button10);
        Button nxtbtn7 = (Button) findViewById(R.id.button9);
        Button nxtbtn8 = (Button) findViewById(R.id.button8);
        TextView tv = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);

        nxtbtn0.setText("");
        nxtbtn1.setText("");
        nxtbtn2.setText("");
        nxtbtn3.setText("");
        nxtbtn4.setText("");
        nxtbtn5.setText("");
        nxtbtn6.setText("");
        nxtbtn7.setText("");
        nxtbtn8.setText("");
        tv2.setText("");
        tv.setText("Wanna try again? Huh, human?");

        nxtbtn0.setEnabled(true);
        nxtbtn1.setEnabled(true);
        nxtbtn2.setEnabled(true);
        nxtbtn3.setEnabled(true);
        nxtbtn4.setEnabled(true);
        nxtbtn5.setEnabled(true);
        nxtbtn6.setEnabled(true);
        nxtbtn7.setEnabled(true);
        nxtbtn8.setEnabled(true);

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                arr[i][j] = 2;
            }
        }
    }

    public void buttonClick(View view) {
        int ide = view.getId();

        Button button = (Button) findViewById(ide);
        Button nxtbtn0 = (Button) findViewById(R.id.button4);
        Button nxtbtn1 = (Button) findViewById(R.id.button3);
        Button nxtbtn2 = (Button) findViewById(R.id.button2);
        Button nxtbtn3 = (Button) findViewById(R.id.button7);
        Button nxtbtn4 = (Button) findViewById(R.id.button6);
        Button nxtbtn5 = (Button) findViewById(R.id.button5);
        Button nxtbtn6 = (Button) findViewById(R.id.button10);
        Button nxtbtn7 = (Button) findViewById(R.id.button9);
        Button nxtbtn8 = (Button) findViewById(R.id.button8);
        TextView tv = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv.setText("Your move is X.");
        if(button.getText()=="") {
            button.setText("X");
            switch (ide){
                case R.id.button2:
                    arr[0][2]=1;
                    break;
                case R.id.button3:
                    arr[0][1]=1;
                    break;
                case R.id.button4:
                    arr[0][0]=1;
                    break;
                case R.id.button5:
                    arr[1][2]=1;
                    break;
                case R.id.button6:
                    arr[1][1]=1;
                    break;
                case R.id.button7:
                    arr[1][0]=1;
                    break;
                case R.id.button8:
                    arr[2][2]=1;
                    break;
                case R.id.button9:
                    arr[2][1]=1;
                    break;
                case R.id.button10:
                    arr[2][0]=1;
                    break;
                default:
                    break;
            }
            makeTemp(arr,temp);
            int move = predictMove(temp,0);
            if (move!=-1) {
                arr[move / 10][move % 10] = 0;
            }
            switch (move){
                case 0:
                    nxtbtn0.setText("O");
                    break;
                case 1:
                    nxtbtn1.setText("O");
                    break;
                case 2:
                    nxtbtn2.setText("O");
                    break;
                case 10:
                    nxtbtn3.setText("O");
                    break;
                case 11:
                    nxtbtn4.setText("O");
                    break;
                case 12:
                    nxtbtn5.setText("O");
                    break;
                case 20:
                    nxtbtn6.setText("O");
                    break;
                case 21:
                    nxtbtn7.setText("O");
                    break;
                case 22:
                    nxtbtn8.setText("O");
                    break;
                case -1:
                    tv2.setText("Oh! So you drawed. I bet you want to try once more. Click that reset button, human!");
                    break;
                default:
                    break;
            }
            int sc = 0;
            for(int l=0;l<3;l++) {
                if(arr[l][0]==arr[l][1] && arr[l][1]==arr[l][2] && arr[l][0]==1)
                    sc = -10;
                else if(arr[l][0]==arr[l][1] && arr[l][1]==arr[l][2] && arr[l][0]==0)
                    sc = 10;
                else if(arr[0][l]==arr[1][l] && arr[1][l]==arr[2][l] && arr[0][l]==1)
                    sc = -10;
                else if(arr[0][l]==arr[1][l] && arr[1][l]==arr[2][l] && arr[0][l]==0)
                    sc = 10;
            }
            if(sc==0) {
                if(arr[0][0]==arr[1][1] && arr[1][1]==arr[2][2] && arr[1][1]==1)
                    sc = -10;
                else if(arr[0][0]==arr[1][1] && arr[1][1]==arr[2][2] && arr[1][1]==0)
                    sc = 10;
                else if(arr[0][2]==arr[1][1] && arr[1][1]==arr[2][0] && arr[1][1]==1)
                    sc = -10;
                else if(arr[0][2]==arr[1][1] && arr[1][1]==arr[2][0] && arr[1][1]==0)
                    sc = 10;
            }
            if (sc==10) {
                tv2.setText("Yeah human! Accept your defeat. I won!");
                nxtbtn0.setEnabled(false);
                nxtbtn1.setEnabled(false);
                nxtbtn2.setEnabled(false);
                nxtbtn3.setEnabled(false);
                nxtbtn4.setEnabled(false);
                nxtbtn5.setEnabled(false);
                nxtbtn6.setEnabled(false);
                nxtbtn7.setEnabled(false);
                nxtbtn8.setEnabled(false);
            }
            else if (sc==-10) {
                tv2.setText("I don't know how it's possible but human, you've earned your victory!");
            }
        }
        else {
            tv2.setText("Click another box you human!");
        }
    }
}
