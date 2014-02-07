package com.mikesachs.ising2dj;

import java.util.Random;
import java.lang.Math;

public class Ising {
    
    // This is the width of the square lattice.
    public int n;
    // This is the interaction strength between neighboring elements.
    public float J;
    // This is the strength of the external field.
    public float h;
    // This is the total size of the Ising lattice. It is n*n.
    public int N;
    // This is the lattice of states.
    private int[] states;
    // The total magnetization of the lattice.
    public int M;
    
    
    Random rand = new Random();
    
    // Constructors
    public Ising(int start_n, float start_J, float start_h) {
        n = start_n;
        J = start_J;
        h = start_h;
        init();
    }
    public Ising() {
        n = 100;
        J = 1.0f;
        h = 0.0f;
        init();
    }
    
    // Initialization common to both constructors.
    private void init() {
        N = n*n;
        states = new int[N];
        M = 0;
        
        // Initialize the lattice to the "high temp" state, i.e. random.
        for ( int i=0; i < states.length; i++) {
            if ( rand.nextInt(2) == 0 )
                states[i] = -1;
            else
                states[i] = 1;
            M += states[i];
        }
    }
    
    public void run(int sweeps, float kBT) {
        for ( int sweep = 0; sweep < sweeps; sweep++ ) {
            for ( int flip_try = 0; flip_try < N; flip_try++ ) {
                try_flip(kBT);
            }
            System.out.println(String.format("%d %f", sweep, m()));
        }
    
    }
    
    public float m() {
        return (float) M/N;
    }
    
    public void do_flip(int flip_index) {
        states[flip_index] *= -1;
        M += 2.0f*states[flip_index];
    }
    
    public void try_flip(float kBT) {
        int try_index = rand.nextInt(N);
        float try_dE = dE(try_index);
        
        if ( try_dE <= 0.0 ) {
            do_flip(try_index);
            //System.out.println(String.format("%f", try_dE));
        } else {
            if ( rand.nextFloat() < Math.exp(-1.0/kBT * try_dE)) {
                do_flip(try_index);
            }
            //System.out.println(String.format("%f %f %f", try_dE, rand.nextFloat(), Math.exp(-1.0/kBT * try_dE)));
        }
    }
    
    public float dE(int try_index) {
        return 2.0f*J*states[try_index]*( states[mod(try_index+1, N)]+states[mod(try_index-1, N)]+states[mod(try_index+n, N)]+states[mod(try_index-n, N)] ) + 2.0f*h*states[try_index];
    }
    
    public int mod(int a, int b) {
        int result = a%b;
        
        if (result < 0)
            return result + b;
        else
            return result;
    }
    
}

