package testJdkProxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

    private String baseDir;

    public MyClassLoader(){
        this.baseDir = MyClassLoader.class.getResource("").getPath();
    }

    /**
     * ͨ�������Ƽ������ֽ����ļ���JVM��
     * @param name ����
     * @return ���Class����
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // ��ȡ����
        String className = MyClassLoader.class.getPackage().getName()+"."+name;
        if(null == baseDir) {
            throw new ClassNotFoundException();
        }

        // ��ȡ���ļ�
        File file = new File(baseDir,name+".class");
        if(!file.exists()){
            throw new ClassNotFoundException();
        }

        // �����ļ�ת��Ϊ�ֽ�����
        try(
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }

            // ���ø��෽������classʵ��
            return defineClass(className,out.toByteArray(),0,out.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}