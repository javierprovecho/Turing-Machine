package turing.addons;
public class Rule {
    public int q = 1;
    public char e = 'h';
    public int p = 1;
    public char f = 'h';
    public int m = 1;
    public Rule(int v, char w, int x, char y, int z) {
        q = v;
        e = w;
        p = x;
        f = y;
        m = z;
    }
    public Rule(){
        q = 0;
        e = '0';
        p = 0;
        f = '0';
        m = 0;
    }
    public void print(){
    	System.out.print(q+" "+e+" "+p+" "+f+" "+m);
    }
    public void println(){
    	System.out.println(q+" "+e+" "+p+" "+f+" "+m);
    }
}
