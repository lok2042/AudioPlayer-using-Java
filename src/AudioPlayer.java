import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AudioPlayer extends JFrame implements ActionListener{

    // Audio
    File file;
    AudioInputStream audioStream;
    Clip clip;

    // Buttons
    JButton playButton;
    JButton pauseButton;
    JButton resetButton;

    // Icons
    ImageIcon greekIcon;
    ImageIcon playIcon;
    ImageIcon pauseIcon;
    ImageIcon resetIcon;

    public AudioPlayer() {
        this.setTitle("Como sócrates - A Song by Bocker | Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 150);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
        this.getContentPane().setBackground(new Color(176,196,222));
        greekIcon = new ImageIcon("Greek.png");
        this.setIconImage(greekIcon.getImage());

        // Sets up audio
        try {
            file = new File("audio.wav");
            audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Sets icons
        playIcon = new ImageIcon("Play.png");
        pauseIcon = new ImageIcon("Pause.png");
        resetIcon = new ImageIcon("Reset.png");

        // Sets up play button
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        playButton.setIcon(playIcon);
        playButton.setFocusable(false);

        // Sets up pause button
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(this);
        pauseButton.setIcon(pauseIcon);
        pauseButton.setFocusable(false);
        pauseButton.setEnabled(false);

        // Sets up reset button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resetButton.setIcon(resetIcon);
        resetButton.setFocusable(false);
        resetButton.setEnabled(false);

        this.add(playButton);
        this.add(pauseButton);
        this.add(resetButton);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            playButton.setEnabled(false);
            pauseButton.setEnabled(true);
            resetButton.setEnabled(true);
            clip.start();
        }
        else if(e.getSource() == pauseButton) {
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
            clip.stop();
        }
        else if(e.getSource() == resetButton) {
            clip.setMicrosecondPosition(0);
        }
    }
}