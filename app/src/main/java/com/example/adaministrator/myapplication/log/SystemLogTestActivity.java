package com.example.adaministrator.myapplication.log;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.adaministrator.myapplication.R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Adaministrator on 2017/11/5.
 */

public class SystemLogTestActivity extends AppCompatActivity {

    TextView mCpuInfo;
    TextView mMemInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_log_test);

        mCpuInfo = (TextView) findViewById(R.id.tv_cpu_info);
        mMemInfo = (TextView) findViewById(R.id.tv_mem_info);

        String[] args = {"/system/bin/cat proc/stat", "/system/bin/cat proc/cpuinfo"};
        String cpuInfo = getInfoFromProc(args);
        Log.i("cpuInfo", cpuInfo);
        mCpuInfo.setText(cpuInfo);

        String[] args1 = {"/system/bin/cat proc/meminfo"};
        String memInfo = getInfoFromProc(args1);
        Log.i("memInfo", memInfo);
        mMemInfo.setText(memInfo);
    }

    public String getCPURateDesc() {
        String path = "/proc/stat";//系统CPU信息文件
        long totalJiffies[] = new long[2];
        long totalIdle[] = new long[2];
        int firstCPUNum = 0;//设置这个参数，这要是防止两次读取文件获知的CPU数量不同，导致不能计算。这里统一以第一次的CPU数量为基准
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        Pattern pattern = Pattern.compile("[0-9]+");
        for (int i = 0; i < 2; i++) {// 控制读取次数
            totalJiffies[i] = 0;
            totalIdle[i] = 0;
            try {
                fileReader = new FileReader(path);
                bufferedReader = new BufferedReader(fileReader, 8192);
                int currentCPUNum = 0;
                String str;
                while ((str = bufferedReader.readLine()) != null && (i == 0 || currentCPUNum < firstCPUNum)) {
                    if (str.toLowerCase().startsWith("cpu")) {
                        currentCPUNum++;
                        int index = 0;
                        Matcher matcher = pattern.matcher(str);
                        while (matcher.find()) {
                            try {
                                long tempJiffies = Long.parseLong(matcher.group(0).trim());

                                StringBuilder temp = new StringBuilder(mCpuInfo.getText().toString());
                                temp.append(" | ").append(tempJiffies);
                                mCpuInfo.setText(temp);

                                totalJiffies[i] += tempJiffies;
                                if (index == 3) {//空闲时间为该行第4条栏目
                                    totalIdle[i] += tempJiffies;
                                }
                                index++;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (i == 0) {
                        firstCPUNum = currentCPUNum;
                        try {//暂停50毫秒，等待系统更新信息。
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        double rate = -1;
        if (totalJiffies[0] > 0 && totalJiffies[1] > 0 && totalJiffies[0] != totalJiffies[1]) {
            rate = 1.0 * ((totalJiffies[1] - totalIdle[1]) - (totalJiffies[0] - totalIdle[0])) / (totalJiffies[1] -
                    totalJiffies[0]);
        }

        return String.format("cpu:%.2f", rate);
    }

    private String getInfoFromProc(String[] args) {
        ShellUtils.CommandResult result = ShellUtils.execCmd(args, false, true);
        return result.errorMsg + "\n" + result.successMsg;
        //        return TextUtils.isEmpty(result.successMsg) ? result.errorMsg : result.successMsg;

        //        ProcessBuilder cmd;
        //        String result = "";
        //        try {
        //            String[] args = {"/system/bin/cat", "/proc/stat"};
        //            cmd = new ProcessBuilder(args);
        //            Process process = cmd.start();
        //            InputStream in = process.getInputStream();
        //            byte[] re = new byte[1024];// 1Kb的缓冲空间
        //            while (in.read(re) != -1) {
        //                System.out.println(new String(re));
        //                result = result + new String(re);
        //                //                return result.toString();
        //            }
        //            in.close();
        //        } catch (Exception ex) {
        //            ex.printStackTrace();
        //        }
        //        return result;

    }
}
