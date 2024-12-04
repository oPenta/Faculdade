import java.util.Random;

public class Main {

    public static void main(String[] args) {
        TemperatureSensor temperatureSensor = new TemperatureSensor();
        HumiditySensor humiditySensor = new HumiditySensor();

        Thread temperatureThread = new Thread(temperatureSensor);
        Thread humidityThread = new Thread(humiditySensor);

        temperatureThread.start();
        humidityThread.start();

        try {
            temperatureThread.join();
            humidityThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread principal interrompida: " + e.getMessage());
        }

        System.out.println("Monitoramento de sensores concluído!");
    }
}

class TemperatureSensor implements Runnable {
    private Random random = new Random();

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            int temperature = 20 + random.nextInt(10);
            System.out.println("Sensor de Temperatura: " + temperature + "°C");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Sensor de temperatura interrompido: " + e.getMessage());
            }
        }
    }
}

class HumiditySensor implements Runnable {
    private Random random = new Random();

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            int humidity = 40 + random.nextInt(21);
            System.out.println("Sensor de Umidade: " + humidity + "%");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("Sensor de umidade interrompido: " + e.getMessage());
            }
        }
    }
}
