//通过线程的联合来模拟顾客等待蛋糕师制作蛋糕的事件
//
//两个子线程
//一customer线程 模拟顾客
//二cakeMaker线程，模拟蛋糕师
//
//顾客（customer线程）到蛋糕店完成取蛋糕的任务，必须要联合蛋糕师（cakerMaker线程））z只有等待蛋糕师吧蛋糕制作完毕，然后才能取走蛋糕，从而完成取蛋糕的任务
//
//customer线程要联合cakeMaker线程  cakeMaker.join()

public class ThreadJoinDemo{
    public  static void main(String args[]){
        ThreadJoin a = new ThreadJoin();
        Thread customer = new Thread(a);
        Thread cakeMaker = new Thread(a);
        customer.setName("顾客");
        cakeMaker.setName("蛋糕师");
        a.setJoinThread(cakeMaker);
        customer.start();
    }
}
class ThreadJoin implements Runnable{
    Cake cake;
    Thread joinThread;
    public void setJoinThread(Thread t){
        joinThread = t;
    }
    public void run(){
        if(Thread.currentThread().getName().equals("顾客"))
            System.out.println(Thread.currentThread().getName()
                    +"等待" + joinThread.getName()+"制作生日蛋糕");
        try{
            joinThread.start();
            joinThread.join();  //当前线程开始等待joinThread结束
        }catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName()
                    + "买了" + cake.name + "价格 " + cake.price);

        }
        if(Thread.currentThread() == joinThread){
            System.out.println(joinThread.getName()+
                    "开始制作生日蛋糕，请等一下");
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){}
            cake = new Cake("生日蛋糕",288);
            System.out.println(joinThread.getName()+
                    "已经吧蛋糕制作完毕");
        }
    }
    class Cake{//内部类
        int price;
        String name;
        Cake(String name, int price){
            this.name = name;
            this.price = price;
        }}
}
