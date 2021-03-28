package mx.ucol.threads;

import  java.util.Scanner;

public class App {
    private final static int MAX = 5_000_000;

    // TODO
    // Create a nested class that uses the countPrimes() method and implements Runnable
    // HINT: You can use System.currentTimeMillis() to capture the current time of the system

    public static void main(String args[]) throws InterruptedException {
        Scanner leer = new Scanner(System.in);
        /*
        Utilice Scanner para poder generar un input
        a traves de consola para leer la cantidad de
        Threads que se tienen
         */
        System.out.print("How many Threads?");
        int numberOfThreads = leer.nextInt();

        if (args.length > 0) {
            try {
                numberOfThreads = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument " + args[0] + " must be an integer.");
                System.exit(1);
            }
        }

        if (numberOfThreads == 0) {
            System.out.println("Argument must be more than zero.");
            System.exit(1);
        }

        // TODO
        // Create threads (HINT: You can store threads in arrays)
        // HINT: You can store threads in arrays
        PrimeThreads Pr = new PrimeThreads();
        Thread ThreadArray[] = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++){
            ThreadArray[i] = new Thread(Pr);
            ThreadArray[i].start();
        }

   }

    // This methods counts the number of primes in the range min to max
    private static int countPrimes(int min, int max) {
        int count = 0;
        for (int i = min; i <= max; i++) {
            if (isPrime(i)) {
                count++;
            }
        }

        return count;
    }

    private static boolean isPrime(int x) {
        assert x > 1;
        int top = (int)Math.sqrt(x);

        for (int i = 2; i <= top; i++) {
            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }

    static class PrimeThreads extends Thread implements Runnable{
        public void run(){
            /*
            Se inicia un contador para poder marcar el timepo
             */
            long init = System.currentTimeMillis();

            //Se le pone el 2 como minimo debido a que si le dejo el 0 y el 1,
            //Esos los cuenta como primos y dea un resultado de 2 primos más
            int primesNum = countPrimes(2, MAX);
            long end = System.currentTimeMillis();
            /*
            Aqui lo que se tiene que realizar es una simple operación para poder obtener
            los segundos exactos, tomamos el momento donde termina de contar
            Se lo restamos a cuando incio el conteo y se divido entre 1000
             */
            double secs = (end - init )/1000.0;
            /*
            Ya aquí solo es el acomodo para que muestre los threads exactos en los cuales se esta leyendo
            y en cuanto tiempo los leyo
             */
            System.out.println(Thread.currentThread().getName() + " counted " + primesNum + " primes in " + secs + " seconds.");
        }
    }
}