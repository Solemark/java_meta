package com.example.cowlogs;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private profileListener listener;
    EditText unText;
    EditText pwText;
    EditText rpwText;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public interface profileListener{
        void usernameD(String username);
        void passwordD(String password);
        void rpasswordD(String rpassword);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button spBtn = view.findViewById(R.id.spBtn);
        Button cpBtn = view.findViewById(R.id.cpBtn);

        unText = view.findViewById(R.id.unText);
        pwText = view.findViewById(R.id.pwText);
        rpwText = view.findViewById(R.id.rpwText);

        spBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username = unText.getText().toString();
                String password = pwText.getText().toString();
                String rpassword = rpwText.getText().toString();

                if(username.isEmpty()){
                    unText.requestFocus();
                    Toast.makeText(getContext(), "Invalid username!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()){
                    pwText.requestFocus();
                    Toast.makeText(getContext(), "Invalid password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!rpassword.equals(password)){
                    rpwText.requestFocus();
                    Toast.makeText(getContext(), "Repeat password does not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                listener.usernameD(username);
                listener.passwordD(password);
                listener.rpasswordD(rpassword);

                HomeFragment hf = new HomeFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.cowPlace, hf);
                ft.commit();
            }
        });

        cpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                HomeFragment hf = new HomeFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.cowPlace, hf);
                ft.commit();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  profileListener){
            listener = (profileListener) context;
        } else{
            throw new RuntimeException(context.toString() + "\nmust implement profileListener!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
