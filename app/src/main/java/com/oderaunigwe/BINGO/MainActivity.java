package com.oderaunigwe.BINGO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Integer> vals = new ArrayList<Integer>();
    private ArrayList<Integer> pickPool = new ArrayList<Integer>();
    private int[][] table = new int [5][5];
    private int pick;
    private int bound;
    private int regularBingo = 0; private int fullHouse = 0; private int xMarks = 0;
    private DrawerLayout drawer;
    MediaPlayer mp;
    MediaPlayer pop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.Regular_Bingo);

        mp = MediaPlayer.create(this,R.raw.win_sound_temp);
        pop = MediaPlayer.create(this,R.raw.pop);

        initArrayMatrixRand();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.Regular_Bingo:
                setButton(false);
                regularBingo = 1;
                fullHouse = 0;
                xMarks = 0;
                break;
            case R.id.Full_House:
                setButton(false);
                fullHouse = 1;
                regularBingo = 0;
                xMarks = 0;
                break;
            case R.id.X_marks:
                setButton(false);
                xMarks = 1;
                regularBingo = 0;
                fullHouse = 0;
                break;
            case R.id.help:
                setButton(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HelpFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openNavDrawer(View view)
    {
        drawer.openDrawer(GravityCompat.START);
    }

    public void onBackPressed()
    {
        mp.stop();
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    public void initArrayMatrix()
    {
        int index = 0;
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                table[i][j] = vals.get(index);
                index++;
            }
        }
    }

    public void initArrayMatrixRand()
    {
        Random rand  = new Random();
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                table[i][j] = rand.nextInt(100);
            }
        }
    }

    public int string_to_int(View view,ArrayList<Integer> vals, int viewID, char  letter)
    {
        //Get the TEXT VIEW
        TextView text =
                view.getRootView().findViewById(viewID);
        //Get value of text view
        //String valString = text.getText().toString();
        text.setTextColor(Color.BLACK);
        String valString = "0";
        Integer val = Integer.parseInt(valString);
        Random random = new Random();
        if(letter == 'b')
        {
            do
            {
                val = random.nextInt(15)+1;
            }while(search_for_duplicates(val) == 1);
        }
        else if(letter == 'i')
        {
            do
            {
                val = random.nextInt(15)+1+15;
            }while(search_for_duplicates(val) == 1);
        }
        else if(letter == 'n')
        {
            do
            {
                val = random.nextInt(15)+1+30;
            }while(search_for_duplicates(val) == 1);
        }
        else if(letter == 'g')
        {
            do
            {
                val = random.nextInt(15)+1+45;
            }while(search_for_duplicates(val) == 1);
        }
        else if(letter == 'o')
        {
            do
            {
                val = random.nextInt(15)+1+60;
            }while(search_for_duplicates(val) == 1);
        }
        vals.add(val);
        text.setText(val.toString());
        return val;
    }
    public void newCard(View view)
    {
        bound = 74;
        setButton(true);
        resetPick(view);
        changeBingoColor(view,R.color.greyedOut);
        fill_pickPool();
        vals.clear();
        char b = 'b', i = 'i', n = 'n', g = 'g', o = 'o';
        string_to_int(view,vals,R.id.b1,b);
        string_to_int(view,vals,R.id.i1,i);
        string_to_int(view,vals,R.id.n1,n);
        string_to_int(view,vals,R.id.g1,g);
        string_to_int(view,vals,R.id.o1,o);
        string_to_int(view,vals,R.id.b2,b);
        string_to_int(view,vals,R.id.i2,i);
        string_to_int(view,vals,R.id.n2,n);
        string_to_int(view,vals,R.id.g2,g);
        string_to_int(view,vals,R.id.o2,o);
        string_to_int(view,vals,R.id.b3,b);
        string_to_int(view,vals,R.id.i3,i);
        string_to_int(view,vals,R.id.n3,n);
        string_to_int(view,vals,R.id.g3,g);
        string_to_int(view,vals,R.id.o3,o);
        string_to_int(view,vals,R.id.b4,b);
        string_to_int(view,vals,R.id.i4,i);
        string_to_int(view,vals,R.id.n4,n);
        string_to_int(view,vals,R.id.g4,g);
        string_to_int(view,vals,R.id.o4,o);
        string_to_int(view,vals,R.id.b5,b);
        string_to_int(view,vals,R.id.i5,i);
        string_to_int(view,vals,R.id.n5,n);
        string_to_int(view,vals,R.id.g5,g);
        string_to_int(view,vals,R.id.o5,o);
        initArrayMatrix();

        freeSpaceCondition(view);
    }

    public int generate_rand()
    {
        Random rand = new Random();
        // pick = rand.nextInt(75)+1;
        return rand.nextInt(bound);
    }
    public void display_pick(View view)
    {
        int index = generate_rand();
        bound--;
        if(bound == 0)
        {
            setButton(false);
        }
        pick = pickPool.get(index);
        pickPool.remove(index);
        //Get the TEXT VIEW
        TextView text =
                view.getRootView().findViewById(R.id.randVal);
        TextView randLet =
                view.getRootView().findViewById(R.id.randLetter);
        //Get value of text view
        String randString = text.getText().toString();
        Integer rand = Integer.parseInt(randString);
        rand = (Integer)pick;
        text.setText(rand.toString());
        if(0 < rand && rand <= 15)
        {
            randLet.setText("B");
        }
        else if(15 < rand && rand <= 30)
        {
            randLet.setText("I");
        }
        else if(30 < rand && rand <= 45)
        {
            randLet.setText("N");
        }
        else if(45 < rand && rand <= 60)
        {
            randLet.setText("G");
        }
        else
        {
            randLet.setText("O");
        }
    }

    public void resetPick(View view)
    {
        TextView text =
                view.getRootView().findViewById(R.id.randVal);
        TextView randLet =
                view.getRootView().findViewById(R.id.randLetter);
        text.setText("0");
        randLet.setText("0");

        TextView freeSpaceText = view.getRootView().findViewById(R.id.n3);
        //TextView freeSpaceTextCPU = view.getRootView().findViewById(R.id.n03);
        if(freeSpaceText.getText() == "FREE\nSPACE")
        {
            freeSpaceText.setTextSize(27);
            //freeSpaceTextCPU.setTextSize(23);
        }
    }

    public int search_for_duplicates(int val)
    {
        int duplicate = 0;
        for(int i = 0; i < vals.size(); i++)
        {
            if(val == vals.get(i))
            {
                duplicate = 1;
                break;
            }
        }
        return duplicate;
    }

    public void click_number(View view)
    {
        int placeholder = 0;
        int id = view.getId();
        TextView text = view.getRootView().findViewById(id);
        String mark_indicator = text.getText().toString();
        if(mark_indicator.equals("X") || mark_indicator.equals("FREE\nSPACE"))
        {
            placeholder++;
        }
        else
        {
            int mark_ind = Integer.parseInt(mark_indicator);
            if(mark_ind == pick)
            {
                pop.start();
                text.setText("X");
                text.setTextColor(Color.RED);
            }

            for(int i = 0; i < 5; i ++)	//Nested for loop searches through each value on the 5x5 matrix
            {
                for(int j = 0; j < 5; j ++)
                {
                    if(table[i][j] == pick)
                    {
                        table[i][j] = 0;	//changes the matching value on card to 0;
                    }
                }

            }

            //After every search, check for wins in every direction
            int winR = checkForWinRows(table,fullHouse);
            int winD = checkForWinDiag(table,xMarks);
            int winC = checkForWinCols(table);
            if (fullHouse == 1) {
                if(winR == 1)
                {
                    setButton(false);
                    changeBingoColor(view,R.color.redBingo);
                    playWinSound();
                }
            }
            else if (xMarks == 1) {
                if(winD == 3)
                {
                    setButton(false);
                    changeBingoColor(view,R.color.redBingo);
                    playWinSound();
                }
            }
            else {
                if(winR == 1 || winC == 2 || winD == 3)
                {
                    setButton(false);
                    changeBingoColor(view,R.color.redBingo);
                    playWinSound();
                }
            }
        }
        }

    public void fill_pickPool()
    {
        pickPool.clear();
        for(int i = 1; i <= 75; i++)
        {
            pickPool.add(i);
        }
    }

    public void setButton(boolean value)
    {
        Button roll = findViewById(R.id.random_number);
        roll.setEnabled(value);
    }

    public void freeSpaceCondition(View view)
    {
        TextView freeSpace = view.getRootView().findViewById(R.id.n3);
        Switch FreeSpace_switch = view.getRootView().findViewById(R.id.free_space_switch);
        if(FreeSpace_switch.isChecked())
        {
            freeSpace.setText("FREE\nSPACE");
            freeSpace.setTextColor(Color.RED);

            if(freeSpace.getTextSize() == 41) {
                freeSpace.setTextSize(40);
            }else   {
                freeSpace.setTextSize(13);
            }

            table[2][2] = 0;
        }

    }

    public void toggle_FreeSpace(View view)
    {
        setButton(false);
    }

    public void playWinSound() {
        mp.start();
    }

    public void changeBingoColor(View view,int colorID) {
        TextView b = view.getRootView().findViewById(R.id.LetterB);
        TextView i = view.getRootView().findViewById(R.id.LetterI);
        TextView n = view.getRootView().findViewById(R.id.LetterN);
        TextView g = view.getRootView().findViewById(R.id.LetterG);
        TextView o = view.getRootView().findViewById(R.id.LetterO);

        b.setTextColor(getResources().getColor(colorID));
        i.setTextColor(getResources().getColor(colorID));
        n.setTextColor(getResources().getColor(colorID));
        g.setTextColor(getResources().getColor(colorID));
        o.setTextColor(getResources().getColor(colorID));
    }

    public static int checkForWinRows(int[][] table, int fullHouse)
    {
        int sumRow1 = 0;
        int sumRow2 = 0;
        int sumRow3 = 0;
        int sumRow4 = 0;
        int sumRow5 = 0;

        for(int i = 0; i < 5; i ++)
        {
            sumRow1 += table[0][i];
            sumRow2 += table[1][i];
            sumRow3 += table[2][i];
            sumRow4 += table[3][i];
            sumRow5 += table[4][i];
        }
        if(fullHouse == 1)
        {
            if(sumRow1 == 0 & sumRow2 == 0 & sumRow3 == 0 & sumRow4 == 0 & sumRow5 == 0)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else {
            if(sumRow1 == 0 || sumRow2 == 0 || sumRow3 == 0 || sumRow4 == 0 || sumRow5 == 0)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int checkForWinCols(int[][] table)
    {
        int sumCol1 = 0;
        int sumCol2 = 0;
        int sumCol3 = 0;
        int sumCol4 = 0;
        int sumCol5 = 0;

        for(int i = 0; i < 5; i ++)
        {
            sumCol1 += table[i][0];
            sumCol2 += table[i][1];
            sumCol3 += table[i][2];
            sumCol4 += table[i][3];
            sumCol5 += table[i][4];
        }

        if(sumCol1 == 0 || sumCol2 == 0 || sumCol3 == 0 || sumCol4 == 0 || sumCol5 == 0)
        {
            return 2;
        }
        else
        {
            return 0;
        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int checkForWinDiag(int[][] table, int xMarks)
    {
        int sumDiag1 = 0;
        int sumDiag2 = 0;

        for(int i = 0; i < 5; i ++)
        {
            sumDiag1 += table[i][i];
        }
        int j = 4;
        for(int i = 0; i < 5; i ++)
        {
            sumDiag2 += table[i][j];
            j --;
        }

        if(xMarks == 1) {
            if(sumDiag1 == 0 & sumDiag2 == 0)
            {
                return 3;
            }
            else
            {
                return 0;
            }
        }
        else {
            if(sumDiag1 == 0 || sumDiag2 == 0)
            {
                return 3;
            }
            else
            {
                return 0;
            }
        }

    }

}
