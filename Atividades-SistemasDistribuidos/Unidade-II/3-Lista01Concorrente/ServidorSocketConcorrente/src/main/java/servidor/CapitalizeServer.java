/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author mathe
 */
public class CapitalizeServer {

    /**
     * Executa o servidor. Quando um cliente se conecta, o servidor gera um novo
     * thread para fazer a manutenção e imediatamente volta a ouvir. Os limites
     * de aplicação o número de threads por meio de um pool de threads (caso
     * contrário, milhões de clientes poderiam faz com que o servidor fique sem
     * recursos ao alocar muitos threads).
     */
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(59898)) {
            System.out.println("O servidor de capitalização está em execução...");
            ExecutorService pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new Capitalizer(listener.accept()));
            }
        }
    }

    private static class Capitalizer implements Runnable {

        private Socket socket;

        Capitalizer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Conectado: " + socket);
            try {
                Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(),
                        true
                );
                while (in.hasNextLine()) {
                    out.println(in.nextLine().toUpperCase());
                }
            } catch (Exception e) {
                System.out.println("Erro:" + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Finalizado: " + socket);
            }
        }
    }
}
