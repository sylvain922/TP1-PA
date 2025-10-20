import javax.swing.*;
import java.awt.*;

public class UneFenetre extends JFrame {
    private JPanel mobilePanel;
    private int n;
    private Thread[] threads;

    private final int LARG = 800, HAUT = 600; // Dimensions de la fenêtre

    public UneFenetre(int n) {
        this.n = n;
        threads = new Thread[n];

        // Configuration du conteneur des mobiles
        mobilePanel = new JPanel();
        mobilePanel.setLayout(new GridLayout(n, 1)); // Une colonne, n lignes

        // Création et ajout des n mobiles
        for (int i = 0; i < n; i++) {
            // Créer chaque mobile avec une largeur fixe et une hauteur divisée par n
            UnMobile mobile = new UnMobile(LARG, HAUT / n);
            mobilePanel.add(mobile);

            // Créer et démarrer un thread pour chaque mobile
            threads[i] = new Thread(mobile);
            threads[i].start();
        }

        // Ajouter le conteneur des mobiles à la fenêtre
        this.add(mobilePanel);

        // Définir la taille de la fenêtre
        this.setSize(LARG, HAUT);

        // Centrer la fenêtre sur l'écran
        this.setLocationRelativeTo(null);

        // Fermer l'application quand la fenêtre est fermée
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Affichage de la fenêtre
        this.setVisible(true);
    }
}
