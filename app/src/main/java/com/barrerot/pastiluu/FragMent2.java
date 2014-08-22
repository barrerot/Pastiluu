package com.barrerot.pastiluu;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.barrerot.pastiluu.util.Communicator;
import com.barrerot.pastiluu.util.ToolPillbox;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragMent2 extends Fragment {

    final String URL_GETPASTILLAS = "http://pastillero.buluu.es/rest/get_pastillas.php";
    final String URL_ADDTOMAS = "http://pastillero.buluu.es/rest/addToma.php?pid=";
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
            page = new Communicator().executeHttpGet(URL_GETPASTILLAS);
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

        final ArrayList<String> pastillasNameArray = tool.getPastillasJson(json);
        final ArrayList<Integer> pastillasIdArray = tool.getPastillaIdsJson(json);

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        for(int i = 0; i<pastillasIdArray.size(); i++){
            Map<String, String> datum = new HashMap<String, String>(1);
            datum.put("name", pastillasNameArray.get(i));
            data.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter(this.getActivity().getBaseContext(), data, android.R.layout.simple_list_item_1,  new String[] {"name"}, new int[] {android.R.id.text1});

        final Activity act = getActivity();

        listView = new ListView(getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                Toast.makeText(act.getBaseContext(), "Toma nueva apuntada", Toast.LENGTH_SHORT).show();
                                                addToma(pastillasIdArray.get(position));
                                            }
                                        }
        );
        return listView;
    }

    public boolean addToma(int pastillaid){
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet();

        Log.e("Pastillas:", "pastillasid: " + pastillaid);

        URI uri = null;
        try {
            uri = new URI(URL_ADDTOMAS + pastillaid);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        httpGet.setURI(uri);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
