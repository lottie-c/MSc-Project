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
                    "hello5[generator](10) < hello4[generator](10)"
            })
    
    public static void main(String args[]) {

    }
}
