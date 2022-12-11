
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author mathe
 */
public class TesteThreadConcorrente1000 {

    /**
     * Executa o servidor. Quando um cliente se conecta, o servidor gera um novo
     * thread para fazer a manutenção e imediatamente volta a ouvir. Os limites
     * de aplicação o número de threads por meio de um pool de threads (caso
     * contrário, milhões de clientes poderiam faz com que o servidor fique sem
     * recursos ao alocar muitos threads).
     */
    public static void main(String[] args) {
        try {
            /**
             * Cria um pool de threads que reutiliza um número fixo de threads
             * operando a partir de uma fila compartilhada ilimitada.
             */
            ExecutorService pool = Executors.newFixedThreadPool(1000);

            int i = 0;
            while (i < 1000) {
                pool.execute(new Servidor02());
                System.out.println("Teste " + i);
                i++;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static class Servidor02 implements Runnable {
        
        @Override
        public void run() {
            System.out.println("teste");
        }
    }
}
