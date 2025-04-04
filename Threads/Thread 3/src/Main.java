public class Main {

    public static void main(String[] args) {
        FileDownloaderA fileDownloaderA = new FileDownloaderA();
        FileDownloaderB fileDownloaderB = new FileDownloaderB();

        Thread threadA = new Thread(fileDownloaderA);
        Thread threadB = new Thread(fileDownloaderB);

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            System.out.println("Thread principal interrompida: " + e.getMessage());
        }

        System.out.println("Todos os downloads concluídos!");
    }
}

class FileDownloaderA implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Download do arquivo A: Parte " + i + "/5 baixada");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Download do arquivo A interrompido: " + e.getMessage());
            }
        }
    }
}

class FileDownloaderB implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Download do arquivo B: Parte " + i + "/3 baixada");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Download do arquivo B interrompido: " + e.getMessage());
            }
        }
    }
}
