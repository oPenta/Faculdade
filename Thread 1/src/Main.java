public class Main {

    public static void main(String[] args) {
        EmailSender emailSender = new EmailSender();
        ReportProcessor reportProcessor = new ReportProcessor();

        emailSender.start();
        reportProcessor.start();

        try {
            emailSender.join();
            reportProcessor.join();
        } catch (InterruptedException e) {
            System.out.println("Thread principal interrompida: " + e.getMessage());
        }

        System.out.println("Todas as tarefas foram concluídas.");
    }
}

class EmailSender extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Enviando e-mail " + i + "/5...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Envio de e-mail interrompido: " + e.getMessage());
            }
        }
    }
}

class ReportProcessor extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Processando relatório " + i + "/3...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Processamento de relatório interrompido: " + e.getMessage());
            }
        }
    }
}
