package com.oddskings.utilFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	public static List<String> returnTotalCount(JSONObject json,String outerJsonObject, String innerJsonObject )
	{
		List<String> list = new ArrayList<String>();
		JSONArray array = json.getJSONArray(outerJsonObject);
		for (int i = 0; i < array.length(); i++) 
		{
			list.add(array.getJSONObject(i).getString(innerJsonObject));
//			System.out.println(array.getJSONObject(i).getString("name"));
		}
		return list;
	}

}
