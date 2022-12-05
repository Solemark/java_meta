package com.example.cowlogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements ProfileFragment.profileListener{
    int currentPage;
    static ArrayList<cowLogs> cowArray;
    static String[] pageNames = {"Angus", "Hereford", "Brahman", "Shorthorn", "Brangus"};
    String emailOutput, userName, passWord, rpassWord;
    TrackGPS gps;

    @Override
    public void onStart() {
        super.onStart();
        gps = new TrackGPS(this);
    }

    public MainActivity(){
        currentPage = 5;
        cowArray = new ArrayList<cowLogs>();
    }

    @Override
    public void usernameD(String username) {
        userName = username;
    }

    @Override
    public void passwordD(String password) {
        passWord = password;
    }

    @Override
    public void rpasswordD(String rpassword) {
        rpassWord = rpassword;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbDataGet();

        HomeFragment frag = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.cowPlace, frag);
        ft.commit();
    }

    public void dbDataGet(){
        DBAdapter db = new DBAdapter(this);
        try{
            String destPath = "/data/data/" + getPackageName() + "/databases";
            File f = new File(destPath);
            if(!f.exists()){
                f.mkdirs();
                f.createNewFile();
                CopyDB(getBaseContext().getAssets().open("Cow.db"), new FileOutputStream(destPath + "/Cow.db"));
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        db.open();

        Cursor cursor = db.getAllCows();
        cursor.moveToFirst();
        int _id;
        String idS;
        int cow;
        String condition;
        String weight;
        String age;
        int day;
        int month;
        int year;
        int hour;
        int minute;
        int second;
        String lonL;
        String latL;
        while(cursor.moveToNext()) {

            _id = cursor.getInt(0);
            cow = cursor.getInt(1);
            condition = cursor.getString(2);
            weight = cursor.getString(3);
            age = cursor.getString(4);
            day = cursor.getInt(5);
            month = cursor.getInt(6);
            year = cursor.getInt(7);
            hour = cursor.getInt(8);
            minute = cursor.getInt(9);
            second = cursor.getInt(10);
            lonL = cursor.getString(11);
            latL = cursor.getString(12);
            idS = "" + _id;

            cowLogs cl = new cowLogs();
            cl.setAll(cow, condition, day, month, year, hour, minute, second, lonL, latL, idS, weight, age);
            cowArray.add(cl);
        }
        db.close();
    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException{
        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0){
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void sendData(){
        if(userName == null){
            emailOutput = "";
        }
        else{
            emailOutput = userName + "\n";
        }
        for(int i = 0; i < cowArray.size(); i++){
            int c = cowArray.get(i).getCow();
            if(c == 0){
                emailOutput += "Angus ";
            }
            if(c == 1){
                emailOutput += "Hereford ";
            }
            if(c == 2){
                emailOutput += "Brahman ";
            }
            if(c == 3){
                emailOutput += "Shorthorn ";
            }
            if(c == 4){
                emailOutput += "Brangus ";
            }
            emailOutput += cowArray.get(i).getAll() + "\n";
        }

        String[] to = {"testEmail@gmail.com"};
        String[] cc = {"testCC@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("Text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "CowLogs Data From Local Client");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailOutput);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

        DBAdapter db = new DBAdapter(this);
        db.open();
        for(long i = 0; i <= cowArray.size(); i++){
            db.deleteCows(i);
        }
        cowArray.clear();
        db.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_send){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            sendData();
                            Toast.makeText(getApplicationContext(), "Sending stored data\nClearing local data! ", Toast.LENGTH_SHORT).show();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Save entries to DB first?").setPositiveButton("OK", dialogClickListener)
                    .setNegativeButton("CANCEL", dialogClickListener).setTitle("Are you sure? This will delete all entries")
                    .setIcon(R.mipmap.ic_launcher).show();
        }
        else if(id == R.id.action_save){
            Toast.makeText(getApplicationContext(), "Saving data to server!", Toast.LENGTH_SHORT).show();

            DBAdapter db = new DBAdapter(this);
            db.open();

            Iterator<cowLogs> itr = cowArray.iterator();
            cowLogs aCowLog;

            for(long i = 0; i <= cowArray.size(); i++){
                db.deleteCows(i);
            }

            while(itr.hasNext() == true){
                aCowLog = itr.next();
                db.insertCows(aCowLog.getCow(),aCowLog.getCondition(), aCowLog.getWeight(), aCowLog.getAge(), aCowLog.getDay(), aCowLog.getMonth(), aCowLog.getYear(), aCowLog.getHour(), aCowLog.getMinute(), aCowLog.getSecond(), aCowLog.getLongitude(), aCowLog.getLatitude());
            }
            db.close();
        }
        else if(id == R.id.action_profile){
            ProfileFragment pf = new ProfileFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.cowPlace, pf);
            ft.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        currentPage = Integer.valueOf((String)view.getTag());
        showCurrentPage();
    }
    public void onPrevious(View view){
        currentPage = currentPage - 1;
        if(currentPage <= -1){
            currentPage = 4;
        }
        showCurrentPage();
    }
    public void onNext(View view){
        currentPage = currentPage + 1;
        if(currentPage >= 5){
            currentPage = 0;
        }
        showCurrentPage();
    }
    public void onHome(View view){
        currentPage = 5;
        showCurrentPage();
    }
    public void onReturn(View view){
        showCurrentPage();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        final Context context = this;
        final Activity activity = (Activity) context;

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked

                        //EditText
                        EditText idText = activity.findViewById(R.id.IDText);
                        EditText weightText = activity.findViewById(R.id.weightText);
                        EditText ageText = activity.findViewById(R.id.ageText);

                        //Spinner
                        String[] conditionArray;
                        conditionArray = getResources().getStringArray(R.array.sConditionArray);
                        Spinner spinner = activity.findViewById(R.id.conditionSpinner);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, conditionArray);
                        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        spinner.setAdapter(adapter);

                        int cow = currentPage;
                        Calendar c = Calendar.getInstance();
                        int dayL = c.get(Calendar.DAY_OF_MONTH);
                        int monthL = c.get(Calendar.MONTH);
                        int yearL = c.get(Calendar.YEAR);
                        int hourL = c.get(Calendar.HOUR_OF_DAY);
                        int minuteL = c.get(Calendar.MINUTE);
                        int secondL = c.get(Calendar.SECOND);
                        String conditionL = spinner.getSelectedItem().toString();
                        String idL = idText.getText().toString();
                        String weightL = weightText.getText().toString();
                        String ageL = ageText.getText().toString();
                        String longitude = "" + gps.getLongitude();
                        String latitude = "" + gps.getLatitude();

                        if(idL.isEmpty()){
                            idText.requestFocus();
                            Toast.makeText(activity, "Must provide ID!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(weightL.isEmpty()){
                            weightText.requestFocus();
                            Toast.makeText(activity, "Must provide Weight!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(ageL.isEmpty()){
                            ageText.requestFocus();
                            Toast.makeText(activity, "Must provide Age!", Toast.LENGTH_SHORT).show();
                            return;
                        }if(conditionL.isEmpty()){
                        Toast.makeText(activity, "Invalid Condition!", Toast.LENGTH_SHORT).show();
                        return;
                        }

                        cowLogs cl = new cowLogs();
                        cl.setAll(cow, conditionL, dayL, monthL, yearL, hourL, minuteL, secondL, longitude, latitude, idL, weightL, ageL);
                        MainActivity.cowArray.add(cl);

                        Toast.makeText(getApplicationContext(), "Saving data to server!", Toast.LENGTH_SHORT).show();

                        idText.setText("");
                        weightText.setText("");
                        ageText.setText("");
                        spinner.setSelection(0);

                        //MainActivity.super.onBackPressed();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Save entries to DB first?").setPositiveButton("OK", dialogClickListener)
                .setNegativeButton("NO", dialogClickListener).setTitle("Database not saved")
                .setIcon(R.mipmap.ic_launcher).show();
    }

    public void showCurrentPage(){
        if(currentPage == 5){
            HomeFragment hf = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.cowPlace, hf);
            ft.commit();
        }
        else{
            CowFragment cf = new CowFragment();
            //Communicate the cow breed to the fragment
            Bundle args = new Bundle();
            args.putInt("cow", currentPage);
            cf.setArguments(args);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.cowPlace, cf);
            ft.commit();
        }
    }

}
