package com.example.mylibrary;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by ASUS on 2017/5/24.
 */
public class SystemInformation extends AppCompatActivity {

    public static Activity ac;

    public long startTime;     //开始时间
    public long endTime;       //结束时间
    public long totalTime;     //方法总的运行时间
    public long runTime;       //除去内部调用方法运行时间
    public String name;        //方法名
    public int childFunction;  //里面含有几个函数
    public long deleteTime;    //调用的函数的时间
    public long freePhysicalMemory; //剩余物理内存
    public String osName;
    public float cpuRatio;    //CPU占有率
    int totalThread;          //线程总数


    public boolean hasSEND_SMS_PERMISSION;             //发送短信权限
    public boolean hasCALL_PHONE;                       //拨打电话权限
    public boolean hasREAD_CONTACTS;                   //读取通讯录权限
    public boolean hasWRITE_SECURE_SETTINGS;          //读写系统敏感设置权限

    int kb = 1024;


    private static final File versionFile = new File("/proc/version");
    public  static final String SEND_SMS_PERMISSION="android.permission.SEND_SMS";
    public static  final String CALL_PHONE ="android.permission.CALL_PHONE";
    public  static final String READ_CONTACTS="android.permission.READ_CONTACTS";
    public  static final String WRITE_SECURE_SETTINGS="android.permission.WRITE_SECURE_SETTINGS";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public SystemInformation() {
        deleteTime = 0;
        childFunction = 0;
        freePhysicalMemory = 0;
    }


    public  void getPhysicalMemory(Context c) {
        ActivityManager activityManager = (ActivityManager) c.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        freePhysicalMemory = info.availMem / kb;

    }

    public void getosName()
    {
        osName = System.getProperty("os.name");
    }

    public void getThread() {
        ThreadGroup parentThread;
        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread.getParent() != null; parentThread = parentThread.getParent()) ;
        this.totalThread = parentThread.activeCount();
    }


    public void getProcessCpuRate() {                              //获取CPU占用率

        StringBuilder tv = new StringBuilder();
        int rate = 0;

        try {
            String Result;
            Process p;
            p = Runtime.getRuntime().exec("top -n 1");

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((Result = br.readLine()) != null) {
                if (Result.trim().length() < 1) {
                    continue;
                } else {
                    String[] CPUusr = Result.split("%");
                    tv.append("USER:" + CPUusr[0] + "\n");
                    String[] CPUusage = CPUusr[0].split("User");
                    String[] SYSusage = CPUusr[1].split("System");
                    tv.append("CPU:" + CPUusage[1].trim() + " length:" + CPUusage[1].trim().length() + "\n");
                    tv.append("SYS:" + SYSusage[1].trim() + " length:" + SYSusage[1].trim().length() + "\n");
                    rate = Integer.parseInt(CPUusage[1].trim()) + Integer.parseInt(SYSusage[1].trim());
                    break;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        cpuRatio= rate;
    }

    public static boolean isCameraUseable() {                         //是否获得camera权限

        boolean canUse =true;
        Camera mCamera =null;
        try{
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        }catch(Exception e) {
            canUse =false;
        }
        if(mCamera !=null) {
            mCamera.release();

        }
        return canUse;
    }

    public void getPermission(Context c)
    {
        if(c.checkCallingOrSelfPermission(SEND_SMS_PERMISSION)== PackageManager.PERMISSION_GRANTED)
            hasSEND_SMS_PERMISSION=true;
        else
            hasSEND_SMS_PERMISSION=false;

        if(c.checkCallingOrSelfPermission(CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
            hasCALL_PHONE=true;
        else
            hasCALL_PHONE=false;

        if(c.checkCallingOrSelfPermission(READ_CONTACTS)== PackageManager.PERMISSION_GRANTED)
            hasREAD_CONTACTS=true;
        else
            hasREAD_CONTACTS=false;

        if(c.checkCallingOrSelfPermission(WRITE_SECURE_SETTINGS)== PackageManager.PERMISSION_GRANTED)
            hasWRITE_SECURE_SETTINGS=true;
        else
            hasWRITE_SECURE_SETTINGS=false;

    }


}

//<uses-permission android:name="android.permission.CALL_PHONE"/>
//<uses-permission android:name="android.permission.READ_CONTACTS"/>
//<uses-permission android:name="android.permission.SEND_SMS"/>
