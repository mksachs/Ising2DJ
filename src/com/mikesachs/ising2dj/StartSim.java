package com.mikesachs.ising2dj;

public class StartSim {
    public static void main(String args[]) {
        Ising sim_model;
        int sweeps;
        float kBT;
        
        /*
        for (String i : args) {
            System.out.println(i);
        }
        */
        
        if ( args.length < 3 ) {
            sim_model = new Ising();
            
            sweeps = Integer.parseInt(args[0]);
            kBT = Float.parseFloat(args[1]);
        } else {
            int n = Integer.parseInt(args[0]);
            float J = Float.parseFloat(args[1]);
            float h = Float.parseFloat(args[2]);
            
            sim_model = new Ising(n, J, h);
            
            sweeps = Integer.parseInt(args[3]);
            kBT = Float.parseFloat(args[4]);
        }
        
        
        System.out.println(String.format("n = %d", sim_model.n));
        System.out.println(String.format("J = %.2f", sim_model.J));
        System.out.println(String.format("h = %.2f", sim_model.h));
        System.out.println(String.format("N = %d", sim_model.N));
        System.out.println(String.format("M = %d", sim_model.M));
        System.out.println(String.format("m = %f", sim_model.m()));
        
        System.out.println(String.format("Sweeps = %d", sweeps));
        System.out.println(String.format("kBT = %f", kBT));
        
        sim_model.run(sweeps, kBT);
        
    }
}