import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.util.Log;

import com.example.mylibrary.SystemInformation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Stack;
import java.util.Vector;

/**
 * Created by ASUS on 2017/5/25.
 */
@Aspect
public class TraceAspect {
    private static final String METHOD_EXECUTION = "execution(* *..MainActivity.*(..))";
    static Stack<SystemInformation> stack = new Stack<>();
    static Vector<SystemInformation> v = new Vector<>();
    static long start;
    static long end;
    public int count = 0;

    @Pointcut(METHOD_EXECUTION)
    public void methodExecution() {
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Before("methodExecution()")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        SystemInformation SI=new SystemInformation();
        SI.name =joinPoint.getSignature().toString();
        SI.startTime =System.nanoTime();
        SI.getosName();
        stack.push(SI);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @After("methodExecution()")
    public void afterMethodExecution(JoinPoint joinPoint) {
            stack.peek().endTime = System.nanoTime();
            stack.peek().totalTime = stack.peek().endTime - stack.peek().startTime;
            stack.peek().runTime = stack.peek().totalTime - stack.peek().deleteTime;
            stack.peek().getPermission(SystemInformation.ac);
            long t_deleteTime1= System.nanoTime();
            stack.peek().getProcessCpuRate();                 //获取CPU使用率
            long t_deleteTime2= System.nanoTime();
            long t_deleteTime=stack.peek().deleteTime;
            v.add(stack.pop());
        if(!stack.empty()) {
            stack.peek().deleteTime +=t_deleteTime2-t_deleteTime1 +t_deleteTime;
            stack.peek().childFunction += 1;
        }

        String t_name = joinPoint.getSignature().getName();
        if (t_name.equals("onCreate")) {
            v.get(0).getPhysicalMemory(SystemInformation.ac);
            System.out.println("系统可用内存: "+v.get(0).freePhysicalMemory+" 单位: kb");
            System.out.println("相机权限: "+SystemInformation.isCameraUseable());
            System.out.println("发送短信权限: "+v.get(0).hasSEND_SMS_PERMISSION);
            System.out.println("拨打电话权限: "+v.get(0).hasCALL_PHONE);
            System.out.println("读取通信录权限: "+v.get(0).hasREAD_CONTACTS);
            System.out.println("读写系统敏感设置权限: "+v.get(0).hasWRITE_SECURE_SETTINGS);
            int len = v.size();
            for (int i = 0; i < len; i++) {
                System.out.println("方法名: "+v.get(i).name);
                System.out.println( "运行时长: " + v.get(i).runTime + " 子方法数: " + v.get(i).childFunction);
                System.out.println("CPU使用率:"+ v.get(i).cpuRatio);
            }
        }
    }

}
