package android.app.jano;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Created by olgacoll on 5/7/17.
 */

public class ConfigFragment extends Fragment{

    public static final String TAG = "ConfigFragment";

    public ConfigFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_config, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        Button b = (Button) view.findViewById(R.id.buttonAlert);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog d = createDialog();
                d.show();
            }
        });
        ListView g = (ListView) view.findViewById(R.id.ListView);
        g.setHorizontalScrollBarEnabled(true);
        g.setAdapter(new ImageAdapter(this.getActivity()));

    }
    public Dialog createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.test_layout, null));
        // Add action buttons
        return builder.create();
    }
}