package com.sandbox50572;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProxyList {

    ArrayList<String> proxyList;



    public void setProxyList() {

        proxyList = new ArrayList<>();
        try {
            //считывание данных из файла
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\prox\\proxy.txt"), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) !=null) {
                proxyList.add(line);//добавление в ArrayList

                //вывод на экран
                //System.out.println(line);
            }
            System.out.println("proxyList содержит " + proxyList.size() + " элементов.");//вывод количества элементов ArrayList
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO тут добавить элементы из файла в ArrayList
    }
     public String getProxy(int id) {
        //String proxy = null;//TODO тут взять элемент с нужным id из ArrayList
         String proxy;
         proxy = proxyList.get(id);
         return proxy;
     }



}
