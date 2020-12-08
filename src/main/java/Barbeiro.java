import java.util.concurrent.BlockingQueue;

public class Barbeiro implements Runnable {
    private final BlockingQueue<Integer> clientes;


    public Barbeiro(BlockingQueue<Integer> clientes) {
        this.clientes = clientes;
    }


    @Override
    public void run() {
        while (true) {
            try {
                atenderCliente();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NullPointerException ignored) {
            }
        }
    }

    private void atenderCliente() throws InterruptedException {
        synchronized (clientes) {
            while (clientes.isEmpty()) {
                System.out.println("*** Sala de Espera Vazia. Barbeiro foi Dormir. ***");
                clientes.wait();
            }

            int clienteId = clientes.poll();
            System.out.println("<-- Cliente n° " + clienteId + " atendido");
            clientes.notifyAll();

            Thread.sleep(500L);
        }
    }

}
