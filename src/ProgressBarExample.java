import javax.swing.*;
import java.awt.*;

class ProgressBarExample extends JFrame implements Runnable{

    JLabel label;
    ImageIcon image;
    JProgressBar bar;
    private JLabel pic;

    public ProgressBarExample(){
    	//setAlwaysOnTop(true);
        bar = new JProgressBar();
        bar.setValue(0);
        bar.setBounds(50,420,768,15);
        bar.setStringPainted(true);
        bar.setFont(new Font("MV Boli",Font.BOLD,8));
        bar.setForeground(Color.blue);
        bar.setBackground(Color.CYAN);


        label = new JLabel();
        label.setLocation(150, 0);
        label.setSize(875,489);
        //label.setIcon(new ImageIcon(ProgressBarExample.class.getResource("/JavaCrud/src/images/image.jpg")));
        label.setIcon(new ImageIcon(JavaCrud.class.getResource("/images/image.jpg")));
        label.add(bar);

        getContentPane().setLayout(null);

        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(ProgressBarExample.class.getResource("Desktop//image.jpg")));

        this.setSize(850,489);
        getContentPane().add(label);
                
        pic = new JLabel("");
        pic.setBounds(0, 0, 875, 489);
        getContentPane().add(pic);
        this.setLocationRelativeTo(null);
        this.setFocusable(false);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        run();
    }
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	fill();
    }
    
    public void fill() {
        int counter =0;

        while(counter<=100) {

            bar.setValue(counter);
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            counter +=1;
            if (counter == 99){
                
            	new JavaCrud().frame.setVisible(true);
                //nextpage.setVisible(true);
            }
            if(counter == 100) {
            	this.dispose();
            }
        }
    }
    public static void main(String[] args) {
    	ProgressBarExample obj = new ProgressBarExample();
	}
}