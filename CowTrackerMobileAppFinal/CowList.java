package com.example.cowlogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */

public class CowList extends ListFragment {

    public CowList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cow_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int cow = getArguments().getInt("cow");
        String btnName = MainActivity.pageNames[cow];
        Button rtnBtn = getActivity().findViewById(R.id.rtnBtn);
        rtnBtn.setText("Return to " + btnName);

        Iterator<cowLogs> itr = MainActivity.cowArray.iterator();
        ArrayList<String> sList = new ArrayList<String>();
        sList.clear();
        String[] aList;

        cowLogs aCowLog;

        while(itr.hasNext() == true){
            aCowLog = itr.next();

            if(aCowLog.getCow() == cow){
                sList.add(aCowLog.getAll());
            }
        }
        aList = new String[sList.size()];
        for(int i = 0; i < sList.size(); i++){
            aList[i] = sList.get(i);
        }
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, aList));
    }
}
