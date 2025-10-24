import java.awt.*;
import javax.swing.*;

public class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps = 50, sonCote = 40;

    private static final Semaphore semaphore = new Semaphore(2);

    UnMobile(int telleLargeur, int telleHauteur) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    @Override
    public void run() {
        int unTiers = saLargeur / 3;
        int deuxTiers = 2 * saLargeur / 3;

        try {
            // 1ère boucle : de 0 à 1/3
            for (sonDebDessin = 0; sonDebDessin < unTiers; sonDebDessin += sonPas) {
                repaint();
                Thread.sleep(sonTemps);
            }

            // 2ème boucle : de 1/3 à 2/3
            semaphore.syncWait();
            for (sonDebDessin = unTiers; sonDebDessin < deuxTiers; sonDebDessin += sonPas) {
                repaint();
                Thread.sleep(sonTemps);
            }
            semaphore.syncSignal();

            // 3ème boucle : de 2/3 à 3/3
            for (sonDebDessin = deuxTiers; sonDebDessin < saLargeur - sonCote; sonDebDessin += sonPas) {
                repaint();
                Thread.sleep(sonTemps);
            }

            // 4ème boucle : de 3/3 à 2/3
            for (sonDebDessin = saLargeur - sonCote; sonDebDessin >= deuxTiers; sonDebDessin -= sonPas) {
                repaint();
                Thread.sleep(sonTemps);
            }

            // 5ème boucle : de 2/3 à 1/3
            semaphore.syncWait();
            for (sonDebDessin = deuxTiers; sonDebDessin >= unTiers; sonDebDessin -= sonPas) {
                repaint();
                Thread.sleep(sonTemps);
            }
            semaphore.syncSignal();

            // 6ème boucle : de 1/3 à 0
            for (sonDebDessin = unTiers; sonDebDessin >= 0; sonDebDessin -= sonPas) {
                repaint();
                Thread.sleep(sonTemps);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected void paintComponent(Graphics telCG) {
        super.paintComponent(telCG);
        telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
    }
}
