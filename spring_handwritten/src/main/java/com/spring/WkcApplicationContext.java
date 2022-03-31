package com.spring;


import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 2022/1/6.
 *
 * @author Weikaichen
 */
public class WkcApplicationContext {

    private Class<?> configCalss;

    private static final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMaps = new ConcurrentHashMap<>();

    private static final ArrayList<String> BeanPostProcessorList = new ArrayList<>();

    public WkcApplicationContext(Class<?> appConfigClass) {
        this.configCalss = appConfigClass;

//        解析配置类
//        CompoentScan注解---》扫描路径--》扫描--->创建单例bean
        scan(appConfigClass);

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMaps.entrySet()) {
            String key = entry.getKey();
            BeanDefinition value = entry.getValue();
            if ("singleton".equals(value.getScope())) {
                Object o = createBean(key, value);
                singletonObjects.put(key, o);
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition value) {
        Class<?> clazz = value.getClazz();
        try {
            Object o = clazz.newInstance();
//            依赖注入
            Field[] declaredFields = clazz.getDeclaredFields();

            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    String name = declaredField.getName();
                    Object bean = getBean(name);
                    declaredField.setAccessible(true);
                    declaredField.set(o, bean);
                }
            }
//            aware回调
            if ((o instanceof BeanNameAware)) {
                ((BeanNameAware) o).setBeanName(beanName);
            }

//            BeanPostProcessor
            for (String beanPostProcessor : BeanPostProcessorList) {
                o = ((BeanPostProcessor) getBean(beanPostProcessor)).postProcessBeforeInitialization(o, beanName);

            }

//            初始化
            if ((o instanceof InitializingBean)) {
                ((InitializingBean) o).afterPropertiesSet();
            }

//            BeanPostProcessor
            for (String beanPostProcessor : BeanPostProcessorList) {
                o = ((BeanPostProcessor) getBean(beanPostProcessor)).postProcessAfterInitialization(o, beanName);
            }


            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void scan(Class<?> appConfigClass) {
        if (appConfigClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnno = appConfigClass.getDeclaredAnnotation(ComponentScan.class);
            String value = componentScanAnno.value();
//            扫描
//            BootstrapClassLoader--->jre\rt.jar
//            ExtClassLoader--->jre\lib\ext
//            AppClassLoader--->classpath
            ClassLoader classLoader = WkcApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(value.replace(".", "/"));
            if (resource != null) {
                File file = new File(resource.getPath() == null ? "" : resource.getPath());
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    if (files != null && files.length > 0) {
                        for (File listFile : files) {
                            String substring = listFile.getAbsolutePath().substring(listFile.getAbsolutePath().indexOf("classes\\"), listFile.getAbsolutePath().indexOf(".class")).substring(8);
                            try {
                                Class<?> clazz = classLoader.loadClass(substring.replace("\\", "."));
                                if (clazz.isAnnotationPresent(Component.class)) {
                                    String componentName = clazz.getDeclaredAnnotation(Component.class).value();
                                    //不指定值时自动用类名
                                    if (componentName == null || "".equals(componentName)) {
                                        String name = clazz.getSimpleName();
                                        componentName = (new StringBuilder()).append(Character.toLowerCase(name.charAt(0))).append(name.substring(1)).toString();
                                    }
                                    //BeanPostProcessor
                                    if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                        BeanPostProcessorList.add(componentName);
                                    }
                                    BeanDefinition beanDefinition = new BeanDefinition();
                                    beanDefinition.setClazz(clazz);
                                    if (clazz.isAnnotationPresent(Scope.class)) {
                                        String scopeName = clazz.getDeclaredAnnotation(Scope.class).value();
                                        beanDefinition.setScope(scopeName);
                                    }
                                    beanDefinitionMaps.put(componentName, beanDefinition);
                                }
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public Object getBean(String beanName) {
        if (beanDefinitionMaps.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMaps.get(beanName);
            String scope = beanDefinition.getScope();
            if ("singleton".equals(scope)) {
                return singletonObjects.get(beanName);
            } else if (BeanPostProcessorList.contains(beanName)) {
                try {
                    return beanDefinition.getClazz().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                return createBean(beanName, beanDefinition);
            }
        } else {
            throw new NullPointerException();
        }
        return null;

    }
}
