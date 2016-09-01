package classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author:Shawn.Xu
 * Date:2016/9/1
 * Description:
 */
public class MyClassLoader extends  ClassLoader {
    private String classPath;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        MyClassLoader fileClsLoader = new MyClassLoader();
        fileClsLoader.setClassPath("E:\\j2ee_proj\\skythink\\WebContent\\WEB-INF\\classes\\");
        Class cls = fileClsLoader.loadClass("com.cmw.entity.sys.AccordionEntity");
        Object obj = cls.newInstance();
        Method[] mthds = cls.getMethods();
        for(Method mthd : mthds){
            String methodName = mthd.getName();
            System.out.println("mthd.name="+methodName);
        }
        System.out.println("obj.class="+obj.getClass().getName());
        System.out.println("obj.class="+cls.getClassLoader().toString());
        System.out.println("obj.class="+cls.getClassLoader().getParent().toString());
    }

    public MyClassLoader() {
        super();
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * 根据类名字符串从指定的目录查找类，并返回类对象
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = null;
        try {
            classData = loadClassData(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.defineClass(name, classData, 0, classData.length);
    }

    /**
     * 根据类名字符串加载类 byte 数据流
     * @param name   类名字符串  例如： com.cmw.entity.SysEntity
     * @return   返回类文件 byte 流数据
     * @throws IOException
     */
    private byte[] loadClassData(String name) throws IOException{
        File file = getFile(name);
        FileInputStream fis = new FileInputStream(file);
        byte[] arrData = new byte[(int)file.length()];
        fis.read(arrData);
        return arrData;
    }

    /**
     * 根据类名字符串返回一个 File 对象
     * @param name   类名字符串
     * @return    File 对象
     * @throws FileNotFoundException
     */
    private File getFile(String name) throws FileNotFoundException {
        File dir = new File(classPath);
        if(!dir.exists()) throw new FileNotFoundException(classPath+" 目录不存在！");
        String _classPath = classPath.replaceAll("[\\\\]", "/");
        int offset = _classPath.lastIndexOf("/");
        name = name.replaceAll("[.]", "/");
        if(offset != -1 && offset < _classPath.length()-1){
            _classPath += "/";
        }
        _classPath += name +".class";
        dir = new File(_classPath);
        if(!dir.exists()) throw new FileNotFoundException(dir+" 不存在！");
        return dir;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
}
