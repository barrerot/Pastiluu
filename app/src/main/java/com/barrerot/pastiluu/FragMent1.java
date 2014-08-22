package com.barrerot.pastiluu;

import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.barrerot.pastiluu.util.Communicator;
import com.barrerot.pastiluu.util.ToolPillbox;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragMent1 extends Fragment {

    final String URL_GETLASTTOMAS = "http://80.240.140.210/rest/get_tomas.php?last_items=10";
    private String arry[] = { "Instanyl 200 microgramos", "Dulcolaxo 5mg (1-0-0)", "Pregabalina 25 mg (1-0-0)", "Fortecortin (1-0-0)",
            "Arapaxel 50mg (1-0-0)", "Furosemida 40mg (1-0-0)", "Omeprazol 20mg (1-0-1)", "Paracetamol 1g",
            "Instanyl 200 microgramos", "Pregabalina 75 mg (0-0-1)", "Omeprazol 20mg (1-0-1)", "Paracetamol 1g" };
    ListView listView = null;
    ArrayList<String> tomasIdArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String page = null;

        try {
            page = new Communicator().executeHttpGet(URL_GETLASTTOMAS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject json = null;

        try {
            json = new JSONObject(page);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ToolPillbox tool = new ToolPillbox();
        final ArrayList<String> tomasPastillaArray = tool.getTomasPastillaJson(json);
        tomasIdArray = tool.getTomasIdJson(json);
        final ArrayList<String> tomasDateArray = tool.getTomasDateJson(json);

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        for(int i = 0; i<tomasPastillaArray.size(); i++){
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("name", tomasPastillaArray.get(i));
            datum.put("date", tomasDateArray.get(i));
            data.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter(this.getActivity().getBaseContext(), data, android.R.layout.simple_list_item_2,  new String[] {"name", "date"}, new int[] {android.R.id.text1, android.R.id.text2});

        listView = new ListView(getActivity());
        listView.setAdapter(adapter);

        return listView;
    }
}
