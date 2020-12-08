import java.util.concurrent.BlockingQueue;

public class Cliente implements Runnable {
    private final BlockingQueue<Integer> fila;

    private int clienteId;


    public Cliente(BlockingQueue<Integer> fila) {
        this.fila = fila;
        this.clienteId = 0;
    }


    @Override
    public void run() {
        while (true) {
            try {
                clienteId++;
                entrarNaFila();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void entrarNaFila() throws InterruptedException {
        synchronized (fila) {
            while (fila.remainingCapacity() == 0) {
                System.out.println("*** Sala de Espera Cheia. Cliente n° " + clienteId + " foi embora ***");
                fila.wait();
                clienteId++;
            }

            fila.offer(clienteId);
            System.out.println("--> Cliente n° " + clienteId + " está a espera");
            fila.notifyAll();

            Thread.sleep(450L);
        }
    }

}
