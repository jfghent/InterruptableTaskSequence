/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfghent.interruptabletasksequence;

import com.jfghent.interruptabletask.InterruptableTask;
import com.jfghent.interruptabletimer.InterfaceVoid;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author jon
 */
public class InterruptableTaskSequence implements Runnable {
    private final Boolean cnx;
    private final Boolean paused;
    
    private InterruptableTask currtask;
    private final Queue<InterruptableTask> taskqueue;
    private final InterfaceVoid vi_onfinish;
    
    InterruptableTaskSequence(InterfaceVoid onfinish){
        this.cnx = false;
        this.paused = false;
        this.taskqueue = new LinkedList<>();
        vi_onfinish = onfinish;
    }
    
    @Override
    public void run() {
        runNextTask();
    }

    private void runNextTask() {
        if (taskqueue.peek()!=null){
                currtask = taskqueue.remove();
                currtask.start();
                
        }else
        {
            vi_onfinish.run();
        }
    }
    
    public void add(InterruptableTask task){
        taskqueue.add(task);
    }
    
    public void next(){
        currtask.cancel();
        runNextTask();
    }
    
    public void cancel(){
        currtask.cancel();
    }
    
    public void pause(){
        currtask.pause();
    }    
    
    public void resume1(){
        currtask.resumetask();
    }
    
    public void onfinish(){
        vi_onfinish.run();
    }
}
