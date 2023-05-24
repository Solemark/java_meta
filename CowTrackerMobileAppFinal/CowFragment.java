package com.example.cowlogs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CowFragment extends Fragment {

    TrackGPS gps;
    public CowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

         gps = new TrackGPS(this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cow_fragment, container, false);

        //Handles cow name
        int cow = getArguments().getInt("cow");
        TextView cowName = view.findViewById(R.id.cowName);
        cowName.setText(MainActivity.pageNames[cow]);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //EditText
        final EditText idText = getActivity().findViewById(R.id.IDText);
        final EditText weightText = getActivity().findViewById(R.id.weightText);
        final EditText ageText = getActivity().findViewById(R.id.ageText);

        //Button
        Button saveLogBtn = getActivity().findViewById(R.id.saveLogBtn);
        Button showLogBtn = getActivity().findViewById(R.id.showLogBtn);

        //Spinner
        String[] conditionArray;
        conditionArray = getResources().getStringArray(R.array.sConditionArray);
        final Spinner spinner = view.findViewById(R.id.conditionSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, conditionArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        saveLogBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int cow = getArguments().getInt("cow");
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
/*
                if(gps.canGetLocation = true){
                    lonL = "" + gps.getLongitude();
                    latL = "" + gps.getLatitude();
                }
*/
                if(idL.isEmpty()){
                    idText.requestFocus();
                    Toast.makeText(getContext(), "Must provide ID!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(weightL.isEmpty()){
                    weightText.requestFocus();
                    Toast.makeText(getContext(), "Must provide Weight!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ageL.isEmpty()){
                    ageText.requestFocus();
                    Toast.makeText(getContext(), "Must provide Age!", Toast.LENGTH_SHORT).show();
                    return;
                }if(conditionL.isEmpty()){
                    Toast.makeText(getContext(), "Invalid Condition!", Toast.LENGTH_SHORT).show();
                    return;
                }

                cowLogs cl = new cowLogs();
                cl.setAll(cow, conditionL, dayL, monthL, yearL, hourL, minuteL, secondL, longitude, latitude, idL, weightL, ageL);
                MainActivity.cowArray.add(cl);

                idText.setText("");
                weightText.setText("");
                ageText.setText("");
                spinner.setSelection(0);
            }
        });
        showLogBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int cow = getArguments().getInt("cow");
                CowList cL = new CowList();
                Bundle args = new Bundle();
                args.putInt("cow", cow);
                cL.setArguments(args);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.cowPlace, cL);
                ft.commit();
            }
        });
    }

}
