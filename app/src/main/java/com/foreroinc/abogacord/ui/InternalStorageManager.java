package com.foreroinc.abogacord.ui;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InternalStorageManager {

    public static class ListItem {
        private int index;
        private String name;

        public ListItem(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }
    }
    public static void createAndWriteFile(Context context, String filename, String content) {
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(content.getBytes());
            Toast.makeText(context, "Fichero creado correctamente", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(context, "Error al crear el fichero", Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }
    }
    public static String readFile(Context context, String filename) {
        StringBuilder stringBuilder = new StringBuilder();

        try (FileInputStream fis = context.openFileInput(filename);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader bufferedReader = new BufferedReader(isr)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }


    public static List<ListItem> readFile2(Context context, String filename) {
        List<ListItem> list = new ArrayList<>();

        try (FileInputStream fis = context.openFileInput(filename);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader bufferedReader = new BufferedReader(isr)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    int index= Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    ListItem listItem = new ListItem(index, name);
                    list.add(listItem);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
