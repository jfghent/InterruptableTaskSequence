/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfghent.interruptabletasksequence;

import com.jfghent.interruptabletask.InterruptableTask;
import com.jfghent.interruptabletimer.InterfaceVoid;
import com.jfghent.interruptabletimermqtt.InterruptableTimerMqtt;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author jon
 */
public class InterruptableTaskSequence implements Runnable {
    private final Boolean cnx;
    private final Boolean paused;
    
    //private InterruptableTask currtask;
    private InterruptableTimerMqtt currtask;
    private final Queue<InterruptableTimerMqtt> taskqueue;
    public final InterfaceVoid vi_onfinish;
    
    public InterruptableTaskSequence(InterfaceVoid _onfinish){
        this.cnx = false;
        this.paused = false;
        this.taskqueue = new LinkedList<>();
        vi_onfinish = _onfinish;
    }
    
    @Override
    public void run() {
        runNextTask();
    }

    public void runNextTask() {
        if (taskqueue.peek()!=null){
                currtask = taskqueue.remove();
                currtask.start();
                
        }else
        {
            vi_onfinish.run();
        }
    }
    
    public void add(InterruptableTimerMqtt task){
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
    
    //public void onfinish(){
    //    vi_onfinish.run();
    //}
    
    public long GetElapsed(){
        return currtask.GetElapsed();
    }
    
    public long GetRemaining(){
        return currtask.GetRemaining();
    }
}
