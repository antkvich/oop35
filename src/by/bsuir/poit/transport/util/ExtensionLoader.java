package by.bsuir.poit.transport.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class ExtensionLoader<T> {

    private final String pluginsPath;
    private final Class<T> typeParameterClass;
    private static final int EXTENSION_LENGTH = 6;

    public ExtensionLoader(String pluginsPath, Class<T> typeParameterClass) {
        this.pluginsPath = pluginsPath;
        this.typeParameterClass = typeParameterClass;
    }

    public List<Class<T>> getExtensionClasses() {
        return getActualExtensions();
    }

    public List<String> getExtensionNames() {
        List<Class<T>> extensionClasses = getActualExtensions();
        return extensionClasses.stream()
                .map(extensionClass -> extensionClass
                        .getName()
                        .replaceAll(".*\\.", ""))
                .collect(Collectors.toList());
    }

    private List<Class<T>> getActualExtensions() {
        List<Class<T>> extensions = new ArrayList<>();
        File[] listOfFiles = fetchFiles();
        if (listOfFiles == null) {
            return extensions;
        }
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                extractClasses(extensions, listOfFile);
            }
        }
        return extensions;
    }

    private void extractClasses(List<Class<T>> extensions, File listOfFile) {
        try {
            Enumeration<JarEntry> e = getJarEntryEnumeration(listOfFile);
            URLClassLoader urlClassLoader = getUrlClassLoader(listOfFile);
            while (e.hasMoreElements()) {
                JarEntry jarEntry = e.nextElement();
                if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
                    continue;
                }
                Class extractedClass = fetchClass(urlClassLoader, jarEntry);
                if (typeParameterClass.isAssignableFrom(extractedClass)) {
                    extensions.add((Class<T>) extractedClass);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Enumeration<JarEntry> getJarEntryEnumeration(File listOfFile) throws IOException {
        JarFile jarFile = new JarFile(listOfFile.getAbsolutePath());
        return jarFile.entries();
    }

    private Class fetchClass(URLClassLoader classLoader, JarEntry jarEntry) throws ClassNotFoundException {
        String className = jarEntry.getName().substring(0, jarEntry.getName().length() - EXTENSION_LENGTH);
        className = className.replace('/', '.');
        return classLoader.loadClass(className);
    }

    private URLClassLoader getUrlClassLoader(File listOfFile) throws MalformedURLException {
        String jarSpec = "jar:file:" + listOfFile.getAbsolutePath() + "!/";
        URL[] urls = {new URL(jarSpec)};
        return URLClassLoader.newInstance(urls);
    }

    private File[] fetchFiles() {
        File folder = new File(pluginsPath);
        return folder.listFiles();
    }
}
