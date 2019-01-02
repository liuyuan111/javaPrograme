class ClassRoom implements Runnable//该类用来模拟教室
{
    Thread student, teacher;
    ClassRoom(){
        teacher = new Thread(this);//创建teacher线程
        student = new Thread(this);
        teacher.setName("即老师");
        student.setName("张三");
    }
    public void run(){
        if(Thread.currentThread() == student){
            try{
                System.out.println("student.getName()" + "正在睡觉不听课");
                Thread.sleep(1000*60*60);
            }catch(InterruptedException e){
                System.out.println(student.getName() + "被老师叫醒了");
            }
            System.out.println(student.getName() + "开始听课");
        }
        else if(Thread.currentThread() == teacher){
            for (int i = 1; i <= 3; i++){
                System.out.println("上课");
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){}
            }
            student.interrupted();//吵醒中断student
        }
    }
}


public class TestInterrupted{
    public static void main(String args[]){
        ClassRoom room = new ClassRoom();
        room.student.start();
        room.teacher.start();
    }
}
