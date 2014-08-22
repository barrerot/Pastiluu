package com.barrerot.pastiluu.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by carlosbarrero
 */
public class ToolPillbox {

    public ArrayList getPastillasJson(JSONObject json){

        ArrayList<String> array = new ArrayList<String>();

        try{
            JSONArray jsonMainArr = json.getJSONArray("pastillas");
            for(int i = 0; i < jsonMainArr.length(); i++){
                JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
                String name = childJSONObject.getString("nombre");
                array.add(name);
            }
        }
        catch (JSONException e){
        }

        return array;
    }

    public ArrayList getPastillaIdsJson(JSONObject json){

        ArrayList<Integer> array = new ArrayList<Integer>();

        try{
            JSONArray jsonMainArr = json.getJSONArray("pastillas");
            for(int i = 0; i < jsonMainArr.length(); i++){
                JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
                int id = childJSONObject.getInt("pastillaid");
                array.add(id);
            }
        }
        catch (JSONException e){
        }

        return array;
    }

    public ArrayList getTomasPastillaJson(JSONObject json){

        ArrayList<String> array = new ArrayList<String>();

        try{
            JSONArray jsonMainArr = json.getJSONArray("tomas");
            for(int i = 0; i < jsonMainArr.length(); i++){
                JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
                String name = childJSONObject.getString("pastilla");
                array.add(name);
            }
        }
        catch (JSONException e){
        }

        return array;
    }

    public ArrayList getTomasIdJson(JSONObject json){

        ArrayList<String> array = new ArrayList<String>();

        try{
            JSONArray jsonMainArr = json.getJSONArray("tomas");
            for(int i = 0; i < jsonMainArr.length(); i++){
                JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
                String name = childJSONObject.getString("tomaid");
                array.add(name);
            }
        }
        catch (JSONException e){
        }

        return array;
    }

    public ArrayList getTomasDateJson(JSONObject json){

        ArrayList<String> array = new ArrayList<String>();

        try{
            JSONArray jsonMainArr = json.getJSONArray("tomas");
            for(int i = 0; i < jsonMainArr.length(); i++){
                JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
                String date = childJSONObject.getString("date");
                String time = childJSONObject.getString("time");
                array.add(date + " - " + time);
            }
        }
        catch (JSONException e){
        }

        return array;
    }

}
