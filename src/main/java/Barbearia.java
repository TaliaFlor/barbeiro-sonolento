import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Barbearia {
    private static final int QTD_CADEIRAS = 5;

    public static void main(String[] args) {
        BlockingQueue<Integer> salaDeEspera = new LinkedBlockingQueue<>(QTD_CADEIRAS);

        Thread cliente = new Thread(new Cliente(salaDeEspera), "Cliente");
        Thread barbeiro = new Thread(new Barbeiro(salaDeEspera), "Barbeiro");

        cliente.start();
        barbeiro.start();
    }

}
