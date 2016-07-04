package main;

import cz.cuni.mff.spl.SPL;

public class Measurement {

    @SPL(
            generators = {
                    "generator="
                            + "THIS@HEAD:"
                            + "main."
                            + "StringGenerator()#generate()"
            },
            methods = {
                    "hello4="
                            + "THIS@HEAD:"
                            + "main."
                            + "HelloWorld#hello4(String)",
                    "hello5="
                            + "THIS@HEAD:"
                            + "main."
                            + "HelloWorld#hello5(String)"
            },
            formula = {
                    " for (n {100, 1000, 10000})"
                    + "hello5[generator](n) < hello4[generator](n)"
            })
    
    public static void main(String args[]) {

    }
}
