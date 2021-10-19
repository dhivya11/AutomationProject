package com.oddskings.utilFiles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

	DateFormat dateFormat = new SimpleDateFormat("dd");
	DateFormat dateFormat1 = new SimpleDateFormat("dd MMM");
	ArrayList<String> list=new ArrayList<String>();

	public int dateToday() {
		Date date = new Date();
		String todate = dateFormat.format(date);
		System.out.println("Date today is "+todate);
		int currentDay = deleteLeadingZerosInDateString(todate);
		return currentDay;
	}

	public int dateLast7thday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		Date todate1 = cal.getTime();
		String fromdate = dateFormat.format(todate1);
		System.out.println("Date 7 days earlier to today is "+fromdate);
		int last7Day = deleteLeadingZerosInDateString(fromdate);
		return last7Day;
	}

	public int deleteLeadingZerosInDateString(String day) {
		String strPattern = "^0+(?!$)";
		String stringNum = day.replaceAll(strPattern, "");
		int num = Integer.parseInt(stringNum);
		return num;
	}
	public ArrayList<String> listOfLast7Days()
	{
		for(int i=0;i>=-7;i--)
        {	
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, i);
        Date todateLoop = cal2.getTime();    
        String xxxx = dateFormat1.format(todateLoop);
        list.add(xxxx);
        }
		return list;
	}
}
