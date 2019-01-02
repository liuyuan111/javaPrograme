import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class JProgressBarDemo{
    public static void main(String args[]){
        BarWin bw = new BarWin();
    }
}

class BarWin extends JFrame implements Runnable{
    JProgressBar pbar1, pbar2;
    Thread thread1, thread2;
    JTextField text1,text2;
    int number =50;

    BarWin(){
        pbar1 = new JProgressBar(0,number);
        pbar2 = new JProgressBar(0,number);
        pbar1.setBorderPainted(true);
        pbar2.setBorderPainted(true);
        text1 = new JTextField(10);
        text2 = new JTextField(10);
        thread1 = new Thread(this);
        thread2 = new Thread(this);
        Box boxV1 = Box.createVerticalBox();
        boxV1.add(pbar1);
        boxV1.add(Box.createHorizontalStrut(8));
        boxV1.add(pbar2);
        Box boxV2 = Box.createVerticalBox();
        boxV2.add(text1);
        boxV2.add(Box.createHorizontalStrut(8));
        boxV2.add(text2);
        Box baseBox = Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(Box.createHorizontalStrut(8));
        baseBox.add(boxV2);
        setLayout(new FlowLayout());
        add(baseBox);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10,10,600,300);
        setVisible(true);
        thread1.start();
        thread2.start();
    }


    public void run() {
        if (Thread.currentThread() == thread1) {
            for (int i = 1; i <= number; i++) {
                text1.setText("第" + i + "项" + f(i));
                pbar1.setValue(i);
                try {
                    thread1.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
        if (Thread.currentThread() == thread2) {
            long a1 = 1, a2 = 1, a = a1;
            int i = 1;
            while (i <= number) {
                if (i < 3) {
                    a = a1 + a2;
                    a1 = a2;
                    a2 = a;
                }
                text2.setText("第" + i + "项" + a);
                pbar2.setValue(i);
                i++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
}
    long f(int n){
        long c=0;
        if(n==1||n==2)
            c=1;
        else if (n>1)
            c = f(n-1)+f(n-2);
        return c;
    }
}



