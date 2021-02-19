package com.mauriciofe.github.io.sessao4daniel;

import android.os.Looper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;
import android.util.Log;

import static com.mauriciofe.github.io.sessao4daniel.Constantes.METHOD_GET;

public class MyAssycTask {
    private final static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final static Handler handler = new Handler(Looper.getMainLooper());

    public static void requestApi(String uri, String method, String jsonBody, CallBack<String> callBack) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String result = request(uri, method, jsonBody);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onComplete(result);
                    }
                });
            }
        });
    }

    private static String request(String uri, String method, String jsonBody) {
        BufferedReader reader;
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod(method);
            if (method.equals(METHOD_GET))
                conn.setDoOutput(false);
            else {
                conn.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(jsonBody);
                writer.flush();
            }
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("ERROR", e.getMessage());
            return null;
        }

    }
}
