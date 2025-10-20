import java.awt.*;
import javax.swing.*;

public class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps=50, sonCote=40;
    boolean movingRight = true;

    UnMobile(int telleLargeur, int telleHauteur) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    public void run() {
        for(sonDebDessin= 0; sonDebDessin < saLargeur - sonPas; sonDebDessin += sonPas) {
            repaint();
            try{
                Thread.sleep(sonTemps);
            }
            catch (InterruptedException telleExcp) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        for(sonDebDessin= saLargeur - sonCote; sonDebDessin >= 0; sonDebDessin -= sonPas) {
            repaint();
            try{
                Thread.sleep(sonTemps);
            }
            catch (InterruptedException telleExcp) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void paintComponent(Graphics telCG) {
        super.paintComponent(telCG);
        telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
    }
}