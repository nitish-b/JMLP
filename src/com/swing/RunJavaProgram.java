package com.swing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Exception;
import java.lang.Process;
import java.lang.Runtime;
import java.lang.String;
import java.lang.System;
import javax.swing.JOptionPane;

public class RunJavaProgram
{

    public static String printLines(String name, InputStream ins) throws Exception
    {
        String line = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
        String newS = new String();
        while ((line = in.readLine()) != null)
        {
            System.out.println(name + " " + line+"\n\n");

            newS = newS.concat(line);
            newS = newS.concat("\n");
            System.out.println(newS);
        }
        String indented = newS.replaceAll("(?m)^", "\t");
        return indented;
    }

    public static int runProcess(String command) throws Exception
    {
        Process pro = Runtime.getRuntime().exec(command);
        Integer exitVal = pro.waitFor();

        String instr = printLines("StudentSolution/StudentSolution.java stdout:", pro.getInputStream());
        String errstr = printLines("StudentSolution/StudentSolution.java stderr:", pro.getErrorStream());
        if (pro != null && exitVal != 0)
        {
            JOptionPane.showMessageDialog(null, errstr, "Compiler Message", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        else
        {
            return 1;
        }
    }
}