package com.unlockdev.diagnosticodeenfermedades;

class Nodo{
    private Nodo hijoIzq;   //SI al sintoma
    private Nodo hijoDer;   //NO al sintoma
    private String Sintoma; //Guarda nombre del sintoma
     private int marca;     //Guarda si el sintoma es SI o NO


    public Nodo(){
        hijoIzq=null;
        Sintoma = "";
        hijoDer=null;
        marca=0;
    }
    public Nodo(String nomb_sintoma, int marca_sintoma) {
        hijoIzq=null;
        Sintoma = nomb_sintoma;
        hijoDer=null;
        marca=marca_sintoma;
    }

    public int getMarca(){
        return this.marca;
    }

    public void setMarca(int marca_sintoma){
        this.marca=marca_sintoma;
    }
    public String getSintoma() {
        return Sintoma;
    }

    public void setSintoma(String Sintoma) {
        this.Sintoma = Sintoma;
    }
 
    public void setHI(Nodo izq){
        hijoIzq = izq;
    }
    public void setHD(Nodo der) {
        hijoDer = der;
    }
    public Nodo getHI() {
       return hijoIzq;
    }
    public Nodo getHD() {
       return hijoDer;
    }

 }//end class