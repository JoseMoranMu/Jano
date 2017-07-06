package android.app.jano;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by olgacoll on 5/7/17.
 */

public class HistoricFragment extends Fragment{

    public static final String TAG = "HistoricFragment";

    public HistoricFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_historic, container, false);
        return view;
    }
}
