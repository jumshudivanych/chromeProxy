package com.sandbox50572;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ChromeProxy implements Runnable {

    private static org.openqa.selenium.chrome.ChromeDriver driver;//TODO static?
    private static String url;//url загружаемои страницы
    public int id;
    private String login;
    private String password;
    public int a;

    public String PROXY;

    //конструктор
    public ChromeProxy(int id, String proxy, String url) {
        this.id = id;// id потока
        this.url = url;
        this.login = login;
        this.password = password;
        this.PROXY = proxy;//TODO в данном случае socks5
    }
    @Override
    public void run() {

        System.out.println("ChromeDriver # " + id);

        //TODO Запуск через прокси
        ChromeOptions option = new ChromeOptions();
        //TODO по протоколу http !!! переделать кидать переменную из coordinator
        //TODO с готовой ссылкой
        //option.addArguments("--proxy-server=http://" + PROXY);
        //TODO подключение прокси socks5
        option.addArguments("--proxy-server=socks5://" + PROXY);
        System.setProperty("webdriver.chrome.driver","C:/idea/chromedriver.exe");
        driver = new org.openqa.selenium.chrome.ChromeDriver(option);
        //раскрытие браузера на весь экран
        //driver.manage().window().maximize();
        //неявное ожидание действует всякий раз при поиске элемента
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Заходим на страницу DuckDuckGo
        driver.get("https://duckduckgo.com");

        //Заходим на страницу
        driver.get(url);

        //инициализация генератора случаиных чисел
        Random random = new Random();
        //получение случайного числа
        a = random.nextInt(10);
        //перевод в секунды
        a = a * 1000;
        //время нахождения на странице
        a = 40000 + a;

        //задержка
         try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //закрытие браузера
        driver.close();
        driver.quit();
        System.out.println("Завершен поток " + id);


        /*
        //TODO ЗАПУСК Chrome
        System.setProperty("webdriver.chrome.driver","C:/idea/chromedriver.exe");
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        driver.manage().window().maximize();
        //неявное ожидание действует всякий раз при поиске элемента
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Заходим на страницу google
        driver.get(url);

        */

    }
}
