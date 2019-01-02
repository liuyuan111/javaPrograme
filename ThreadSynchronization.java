//线程同步程序实例
//他们拥有一个账本，都可以使用saveOrTake(int amount)方法
//对本账本进行访问：会计使用saveOrTake(int amount)方法时
//像账本上写入 存钱记录，出纳使用saveOrTake(int amount)方法时
//像账本写入取钱记录，因此，当会计正在使用saveOrTake(int amount)时，出纳被禁止使用
//
//程序要保证其中一个线程使用saveOrTake(int amount)时，另一个线程将必须等待，saveOrTake(int amount)方法应当是一个synchronized
public class ThreadSynchronization{
    public static void main(String args[]){
        Bank bank = new Bank();
        bank.setMoney(200);
        System.out.println("目前账上有" + bank.money + "万");
        Thread accountant, cashier; //会计，出纳两个线程
        accountant  = new Thread(bank);
        cashier = new Thread(bank);
        accountant.setName("会计");
        cashier.setName("出纳");
        accountant.start();
        cashier.start();
    }
}
class Bank implements Runnable {
    int money = 200;

    public void setMoney(int n) {
        money = n;
    }

    public void run() {
        if (Thread.currentThread().getName().equals("会计"))
            saveOrTake(300);
        else if (Thread.currentThread().getName().equals("出纳"))
            saveOrTake(150);
    }


    public synchronized void saveOrTake(int amount) {//存取方法
        if (Thread.currentThread().getName().equals("会计")) {
            for (int i = 1; i <= 3; i++) {
                money = money + amount / 3;//每存入amount/3稍歇一下
                System.out.println(Thread.currentThread().getName() +
                        "存入" + amount / 3 + "账上有" + money + "万， 休息一会再存");
                try {
                    Thread.sleep(1000);//这时出纳仍不能使用saveOrTake方法
                } catch (InterruptedException e) {
                }
            }
        } else if (Thread.currentThread().getName().equals("出纳")) {
            for (int i = 1; i <= 3; i++) {
                money = money - amount / 3;//每次取amount/3， 稍歇一下
                System.out.println(Thread.currentThread().getName() + "取出" + amount / 3 +
                        "账上有" + money + "万， 休息一会再取");
                try {
                    Thread.sleep(1000);//此时会计不能使用saveOrTake方法
                } catch (InterruptedException e) {
                }
            }
        }
    }
}