package com.sandbox50572;

/**
 * многопоточный proxy chrome driver
 * TODO добавить перебор прокси листа потоками (sincronized)
 */
public class App 
{
    public static void main( String[] args )
    {


        //TODO создание экземпляра класса для работы с базой данных
        //WorkToSqlite workToSqlite = new WorkToSqlite();
        //вызов метода
        //workToSqlite.work();

        //создание объекта coordinator
        Runnable coordinator1 = new Coordinator();
        //создание потока
        Thread threadCoordinator = new Thread(coordinator1);
        //стартуем поток coordinator
        threadCoordinator.start();

        System.out.println( "Метод main завершен." );
    }
}
