package com.sandbox50572;

/*
*TODO Потоки стартуют, но не отработав не завершаются
* добавить перебор прокси socks5 анонимный по умолчанию
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Coordinator implements Runnable {


    @Override
    public void run() {
        int id = 1;
        String proxy;
        String url = null;
        Thread threadNext = null;

        try {
            //Считывание с клавиатуры url
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите url :");
            url = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //создание экземпляра класса ProxyList
        ProxyList proxyList = new ProxyList();
        //вызов метода считывания из файла
        proxyList.setProxyList();

        while (true) {

            for (int i=0; i<2; i++) {//количество запускаемых потоков

                // получение элемента из proxyList по id и передачу его
                //в ChromeDriver в качестве proxy
                proxy = proxyList.getProxy(id);
                //TODO Создание и запуск ChromeDriver
                //создание объекта
                //В имя добавить номер потока и потом выводить номер отработавшего потока
                Runnable chromeProxy = new ChromeProxy(id, proxy, url);//TODO URL ФОТКИ http://imagetwist.com/3ha1gv9u7yds/tits.png
                //создание дочернего потока
                threadNext = new Thread(chromeProxy);
                //стартуем новыи поток
                threadNext.start();

                try {
                    threadNext.join();//TODO потоки стартуют по одному и ожидают завершения друг друга!!!
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //TODO !!! потоки стартуют до завершения предидущих настроить
                //TODO параметры браузера по умолчанию
                //TODO Добавить join ( ожидание завершения потока перед началом следующих.)
                //задержка между созданием потоков
                 try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                id++;

            }


        }
    }
}
