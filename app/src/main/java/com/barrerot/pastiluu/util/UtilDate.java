package com.barrerot.pastiluu.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by carlosbarrero
 */
public class UtilDate {
    private String monthsName[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    public List<String> months = new ArrayList<String>();
    public List<Integer> maxDaysMonth = new ArrayList<Integer>();
    public final int firstMonth = 5;
    public final int firstYear = 2013;
    public int nowMonth;
    public int nowYear;

    public UtilDate(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        nowMonth = cal.get(Calendar.MONTH);
        nowYear = cal.get(Calendar.YEAR);

        int daysMonth;
        String aux = "";

        if(this.firstYear == nowYear){
            for(int i = nowMonth; i >= firstMonth; i--){
                aux = monthsName[i] + " " + nowYear;
                months.add(aux);
                cal = new GregorianCalendar(nowYear,i,1);
                daysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                maxDaysMonth.add(daysMonth);
            }
        }
        else if(this.firstYear < nowYear){
            //Itero sobre el año actual
            for(int i = nowMonth; i>=0; i--){
                aux = monthsName[i] + " " + nowYear;
                months.add(aux);
                cal = new GregorianCalendar(nowYear,i,1);
                daysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                maxDaysMonth.add(daysMonth);
            }
            //itero por años hasta llegar al primer año a tratar
            for(int j = nowYear -1; j>this.firstYear; j--){
                //itero por meses (los doce meses)
                for (int i = 11; i>=0; i--){
                    aux = monthsName[i] + " " + j;
                    months.add(aux);
                    cal = new GregorianCalendar(j,i,1);
                    daysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    maxDaysMonth.add(daysMonth);
                }
            }
            //Itero sobre los meses del primer año
            for(int i = 11; i>=this.firstMonth; i--){
                aux = monthsName[i] + " " + this.firstYear;
                months.add(aux);
                cal = new GregorianCalendar(this.firstYear,i,1);
                daysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                maxDaysMonth.add(daysMonth);
            }
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    public List<String> getMonthsArray() {
        return months;
    }

    public int getNumberMonth(String month){
        for(int i=0; i<monthsName.length; i++){
            if(month.equals(monthsName[i]))
                return i + 1;
        }

        return -1;
    }
}
