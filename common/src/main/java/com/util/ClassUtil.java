package com.util;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author xiehongwei
 * @date 2021/11/3 1:39 下午
 */
public class ClassUtil {
    static ClassLoader classLoader = ClassUtil.class.getClassLoader();

    /**
     * 扫描包下所有的class
     * @param pack
     * @return
     */
    public static List<Class<?>> findAllClass(String pack) {
        List<Class<?>> result = new ArrayList<>();
        String path = pack.replace(".", "/");
        URL resource = classLoader.getResource(path);
        if ("file".equals(resource.getProtocol())) {
            result.addAll(findClassLocal(pack));
        } else if ("jar".equals(resource.getProtocol())) {
            result.addAll(findClassJar(pack));
        }
        return result;
    }

    @SneakyThrows
    public static void findXml(String pattern) {
        PathMatchingResourcePatternResolver rr = new PathMatchingResourcePatternResolver();
        Resource[] resources = rr.getResources(pattern);

        JAXBContext context = JAXBContext.newInstance(PluginInfo.class);

        for (Resource resource : resources) {
            InputStream is = resource.getURL().openStream();
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.unmarshal(is);
            is.close();
        }
    }

    @SneakyThrows
    private static List<Class<?>> findClassJar(String pack) {
        List<Class<?>> classes = new ArrayList<>();
        String path = pack.replace(".", "/");
        URL resource = classLoader.getResource(path);
        JarURLConnection conn = (JarURLConnection) resource.openConnection();
        JarFile jarFile = conn.getJarFile();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            if (jarEntry.isDirectory()) {
                String dirName = jarEntry.getName().replace(".", "/");
                int i = dirName.lastIndexOf(".");
                if (i > 0) {
                    classes.addAll(findClassJar(dirName.substring(0, i)));
                }
            }
            if (jarEntry.getName().endsWith(".class")) {
                Class<?> clz = classLoader.loadClass(jarEntry.getName().replace(".", "/"));
                classes.add(clz);
            }
        }
        return classes;

    }

    private static Collection<Class<?>> findClassLocal(String pack) {
        return Lists.newArrayList();
    }
}
