package com.company;

/**
 * Created by taozhiheng on 14-11-4.
 */
public class CommandGetUp implements MyCommand{
    private MyReceiver receiver;
    public CommandGetUp(MyReceiver receiver)
    {
        this.receiver=receiver;
    }
    public void change()
    {
        receiver.toGetUp();
    }
}
