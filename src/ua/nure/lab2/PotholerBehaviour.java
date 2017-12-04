package ua.nure.lab2;

import jade.core.behaviours.FSMBehaviour;

public abstract class PotholerBehaviour extends FSMBehaviour {

    public static final String STATE_REGISTER = "Register";
    public static final String STATE_PLAY = "Play";

    public static final int REGISTERED = 0;
    public static final int OVER = 1;
    public static final int NOT_REGISTERED = 2;

    abstract void before();

    abstract void after();

}
