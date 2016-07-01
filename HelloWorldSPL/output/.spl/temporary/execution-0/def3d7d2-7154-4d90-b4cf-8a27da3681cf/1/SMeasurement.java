// This file was generated by SPL Measurement Code Generator 
// #ce1fe94e|local|THIS@/Users/lottiecarruthers/Documents/Masters/Project/HelloWorldSPL/output/.spl/temporary/build-0/sourceCode/revision-0-20160701142639:main.HelloWorld#hello5(String)[THIS@/Users/lottiecarruthers/Documents/Masters/Project/HelloWorldSPL/output/.spl/temporary/build-0/sourceCode/revision-0-20160701142639:main.StringGenerator#generate()](10)


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Vector;
import java.util.jar.Manifest;
import java.util.List;
import java.util.LinkedList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class SMeasurement {

    private static final String OUT_FILE = "out";
    private static final String ERR_FILE = "err";

    private static final String IDENTIFICATION        = "#ce1fe94e|local|THIS@/Users/lottiecarruthers/Documents/Masters/Project/HelloWorldSPL/output/.spl/temporary/build-0/sourceCode/revision-0-20160701142639:main.HelloWorld#hello5(String)[THIS@/Users/lottiecarruthers/Documents/Masters/Project/HelloWorldSPL/output/.spl/temporary/build-0/sourceCode/revision-0-20160701142639:main.StringGenerator#generate()](10)";
    private static final String PROPERTY_DATE         = "#date=";
    private static final String PROPERTY_WARMUP_COUNT = "#warmup=";
    private static final String PROPERTY_SAMPLE_COUNT = "#count=";
    private static final String MARK_SAMPLES_BEGIN    = "#begin";
    private static final String MARK_SAMPLES_END      = "#end";
    private static final String RESULT_FILE_NAME      = "result.dat";
    
    private static final String DATE_FORMAT_PATTERN   = "dd-MMM-yyyy HH:mm:ss";

    private static int WARMUP_CYCLES = 1000;
    private static int WARMUP_TIME = 5;   
    private static int MEASUREMENT_CYCLES = 2000; 
    private static int MEASUREMENT_TIME = 10; 
    
    public static void main(String[] args) 
            throws Throwable {
        new SMeasurement(args);
    }

    private static List<URL> listJars(File file) 
            throws Throwable {      
            
        List<URL> jars = new LinkedList<>();
        
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".jar")) {
                jars.add(f.toURI().toURL());
            }
        }

        return jars;
    }

    private void checkConfiguration()
            throws Exception {
        if (WARMUP_CYCLES == -1 && WARMUP_TIME == -1) {
            WARMUP_CYCLES = 1000;
            WARMUP_TIME = 5;
        }

        if (MEASUREMENT_CYCLES == -1 && MEASUREMENT_TIME == -1) {
            MEASUREMENT_CYCLES = 2000;
            MEASUREMENT_TIME = 10;
        }
    }
    
    private void redirectStdToLogs() 
            throws IOException {
        File out = new File(OUT_FILE);
        File err = new File(ERR_FILE);

        out.createNewFile();
        err.createNewFile();

        PrintStream outputStream = new PrintStream(new FileOutputStream(out));
        PrintStream errorStream = new PrintStream(new FileOutputStream(err));
        System.setOut(outputStream);
        System.setErr(errorStream);
    }    

    public SMeasurement(String[] args) 
            throws Throwable {
        redirectStdToLogs();
            
        checkConfiguration();

        URL url = this.getClass().getClassLoader().getResource("SMeasurement.class");
        File file = new File(url.toURI().getPath()).getAbsoluteFile().getParentFile();

        List<URL> generatorClasspaths = new LinkedList<>();
        List<URL> methodClasspaths = new LinkedList<>();

        for (File f : file.listFiles()) {
            if (f.isDirectory() && f.getName().startsWith("generator")) {
                    generatorClasspaths.add(f.toURI().toURL());
                    generatorClasspaths.addAll(listJars(f));
            }
            if (f.isDirectory() && f.getName().startsWith("method")) {
                    methodClasspaths.add(f.toURI().toURL());
                    methodClasspaths.addAll(listJars(f));
            }
        }

        URL[] generatorURLs = generatorClasspaths.toArray(new URL[generatorClasspaths.size()]);
        URL[] methodURLs = methodClasspaths.toArray(new URL[methodClasspaths.size()]);

        ClassLoader parentClassLoader = Thread.currentThread().getContextClassLoader();

        URLClassLoader generatorClassLoader = new URLClassLoader(
                generatorURLs,
                parentClassLoader);

        URLClassLoader methodClassLoader = new URLClassLoader(
                methodURLs,
                parentClassLoader);

        Class<?> generatorProviderClass = generatorClassLoader.loadClass("CSGenerator");
        Object generatorProvider = generatorProviderClass.newInstance();

        Class<?> methodProviderClass = methodClassLoader.loadClass("CSMethod");
        Object methodProvider = methodProviderClass.newInstance();

        Vector<Long> times = new Vector<Long>(MEASUREMENT_CYCLES);

        // warmup
        long warmupCyclesSpent = 0;
        long warmupTimeStart = System.currentTimeMillis()/1000;

        while (true) {
            if (WARMUP_CYCLES != -1 && warmupCyclesSpent >= WARMUP_CYCLES) {
                break;
            }
            long warmupTimeSpent = (System.currentTimeMillis()/1000) - warmupTimeStart;
            if (WARMUP_TIME != -1 && warmupTimeSpent >= WARMUP_TIME) {
                break;
            }

            Thread.currentThread().setContextClassLoader(generatorClassLoader);
            Iterable<Object[]> generator = ((ISGenerator)generatorProvider).newInstance();

            Thread.currentThread().setContextClassLoader(methodClassLoader);
            ISMethod methodObject = ((ISMethod)methodProvider);
            methodObject.newInstance();

            for (Object[] arguments : generator) {
                methodObject.call(arguments);
            }

            warmupCyclesSpent++;
        }

        //measurement
        long measurementCyclesSpent = 0;
        long measurementTimeStart = System.currentTimeMillis()/1000;        
        
        while (true) {
            if (MEASUREMENT_CYCLES != -1 && measurementCyclesSpent >= MEASUREMENT_CYCLES) {
                break;
            }
            long measurementTimeSpent = (System.currentTimeMillis()/1000) - measurementTimeStart;
            if (MEASUREMENT_TIME != -1 && measurementTimeSpent >= MEASUREMENT_TIME) {
                break;
            }

            Thread.currentThread().setContextClassLoader(generatorClassLoader);
            Iterable<Object[]> generator = ((ISGenerator)generatorProvider).newInstance();

            Thread.currentThread().setContextClassLoader(methodClassLoader);
            ISMethod methodObject = ((ISMethod) methodProvider);
            methodObject.newInstance();

            Thread.yield();

            long start;

                            ThreadMXBean thMxB = ManagementFactory.getThreadMXBean();
                start = thMxB.getCurrentThreadCpuTime();
                        
            for (Object[] arguments : generator) {
                methodObject.call(arguments);
            }

            long end;

                            end = thMxB.getCurrentThreadCpuTime();
                           

            // first sample is usually pretty bad
            // even for long warm up
            if (measurementCyclesSpent != 0) {
                times.add(end - start);
            } else {
                MEASUREMENT_CYCLES++;
            }

            measurementCyclesSpent++;
        }        
        
        File outputFile = new File(RESULT_FILE_NAME);
        
        try (
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                PrintStream output = new PrintStream(outputStream)) {

            output.println(IDENTIFICATION);

            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);
            Date now = Calendar.getInstance().getTime();
            output.println(PROPERTY_DATE + formatter.format(now));

            output.println(PROPERTY_WARMUP_COUNT + String.valueOf(warmupCyclesSpent));

            output.println(PROPERTY_SAMPLE_COUNT + times.size());

            output.println(MARK_SAMPLES_BEGIN);
            for (Long time : times) {
                output.println(time);
            }

            output.println(MARK_SAMPLES_END);
        }
    }

    public class LoudClassLoader extends URLClassLoader {

        public LoudClassLoader(URL[] urls) {
            super(urls);
        }

        public LoudClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }

        @Override
        public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            System.out.printf("loadClass [%s]\n", name);
            return super.loadClass(name, resolve);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            System.out.printf("findClass [%s]\n", name);
            return super.findClass(name);
        }

        @Override
        protected Package definePackage(String name, Manifest man, URL url) throws IllegalArgumentException {
            System.out.printf("definePackage [%s]\n", name);
            return super.definePackage(name, man, url);
        }

        @Override
        protected Package definePackage(String name, String specTitle, String specVersion, String specVendor, String implTitle, String implVersion,
                String implVendor, URL sealBase) throws IllegalArgumentException {
            System.out.printf("definePackage [%s]\n", name);
            return super.definePackage(name, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor, sealBase);
        }
    }
}

