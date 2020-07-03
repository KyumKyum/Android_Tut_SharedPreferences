package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Constants
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String ASWITCH = "aswitch";

    //Variables for UI
    private TextView textViewShowing;
    private EditText editTextText;
    private Button buttonApply;
    private Button buttonSave;
    private Switch aSwitch;

    //Variables for loading
    private String text;
    private boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewShowing = findViewById(R.id.text_view_showing);
        editTextText = findViewById(R.id.edit_text_text);
        buttonApply = findViewById(R.id.button_apply);
        buttonSave = findViewById(R.id.button_save);
        aSwitch = findViewById(R.id.switch_save);

        loadData();
    }

    public void applyText(View v){
        String text = editTextText.getText().toString();
        textViewShowing.setText(text);
    }

    public void saveText(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        //MODE_PRIVATE : no other apps can change this preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Using editor we save variables.

        editor.putString(TEXT,textViewShowing.getText().toString());
        editor.putBoolean(ASWITCH,aSwitch.isChecked());

        editor.apply(); //Saved

        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData (){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT,"");
        switchOnOff = sharedPreferences.getBoolean(ASWITCH,false);
        //First param: Key value, Second param: Default Value
        updateViews();
    }

    public void updateViews() {
        textViewShowing.setText(text);
        aSwitch.setChecked(switchOnOff);
    }
}
