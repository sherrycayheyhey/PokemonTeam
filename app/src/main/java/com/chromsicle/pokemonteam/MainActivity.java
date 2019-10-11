package com.chromsicle.pokemonteam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView teamText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.chromsicle.pokemonteam", Context.MODE_PRIVATE);
        teamText = findViewById(R.id.teamTextView);

        String team = sharedPreferences.getString("team", "oops");

        //*******************ALERT!*******************
        if (team.equals("oops")) {
            //this runs if a team hasn't been selected yet
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Team Selection")
                    .setMessage("Which Pokemon team are you on?")
                    .setPositiveButton("Mystic", new DialogInterface.OnClickListener() { //do something when clicked
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setTeam("mystic");
                        }
                    })
                    .setNeutralButton("Valor", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setTeam("valor");
                        }
                    })
                    .setNegativeButton("Instinct", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setTeam("instinct");
                        }
                    })
                    .show();
        } else {
            //this runs if the team has already been selected, whatever's coming out of shared preferences
            setTeam(team);
        }
    }

    //*******************MENU*******************
    //this makes the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //get a MenuInflater then inflate it
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //this is called when a menu item is selected by the user
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        //check the menu item id to see what to do with it
        switch (item.getItemId()) {
            case R.id.mystic:
                setTeam("mystic");
                return true;
            case R.id.valor:
                setTeam("valor");
                return true;
            case R.id.instinct:
                setTeam("instinct");
                return true;
            default:
                return false;

        }
    }

    public void setTeam (String team) {
        //put in the team the user selected
        sharedPreferences.edit().putString("team", team).apply();

    //update the TextView
    if(team.equals("mystic")){
        teamText.setText("Team Color: Blue\nTeam Bird: Articuno\nTeam Leader: Blanche");
    } else if (team.equals("valor")){
        teamText.setText("Team Color: Red\nTeam Bird: Moltres\nTeam Leader: Candela");
    } else if (team.equals("instinct")){
        teamText.setText("Team Color: Yellow\nTeam Bird: Zapdos\nTeam Leader: Spark");
    }

    }
}
