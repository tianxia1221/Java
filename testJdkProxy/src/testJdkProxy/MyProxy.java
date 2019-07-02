package testJdkProxy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @desc �Լ�ʵ�ֵĴ����࣬���������ֽ����ļ�������̬���ص�JVM��
 * @author gaowenfeng
 * @date 2018/3/30
 */
public class MyProxy {
	/**
	 * ���ɴ������
	 * 
	 * @param loader
	 *            ������������ڼ��ر�����������ļ�
	 * @param interfaces
	 *            ��������Ľӿ�
	 * @param h
	 *            �Զ����InvocationHandler�ӿ�,���ھ����������ִ��
	 * @return ���ر������Ĵ������
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static Object newProxyInstance(MyClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
			throws IllegalArgumentException {
		/**
		 * 1.���ɴ������Դ���� 2.�����ɵ�Դ������������̣�����Ϊ.java�ļ� 3.����Դ���룬������.java�ļ�
		 * 4.��class�ļ��е����ݣ���̬���ص�JVM�� 5.���ر������Ĵ������
		 */
		try {
			// 1.���ɴ������Դ����
			String src = genSesource(interfaces[0]);
			// 2.�����ɵ�Դ������������̣�����Ϊ.java�ļ�
			String path = MyProxy.class.getResource("").getPath();
			File file = new File(path + "$Proxy0.java");

			FileWriter fw = new FileWriter(file);
			fw.write(src);
			fw.close();

			// 3.����Դ���룬������.java�ļ�
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			if(javaCompiler == null){
				 try {
				        Class<?> javacTool = Class.forName("com.sun.tools.javac.api.JavacTool");
				        Method create = javacTool.getMethod("create");
				        javaCompiler = (JavaCompiler) create.invoke(null);
				    } catch (Exception e) {
				        throw new AssertionError(e);
				    }
			}
			StandardJavaFileManager manager = javaCompiler.getStandardFileManager(null, null, null);
			Iterable iterable = manager.getJavaFileObjects(file);

			JavaCompiler.CompilationTask task = javaCompiler.getTask(null, manager, null, null, null, iterable);
			task.call();
			manager.close();

			// 4.��class�ļ��е����ݣ���̬���ص�JVM��
			Class proxyClass = loader.findClass("$Proxy0");

			// 5.���ر������Ĵ������
			Constructor c = proxyClass.getConstructor(InvocationHandler.class);
			return c.newInstance(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/*
	 * ���ɴ������Դ����
	 * 
	 * @return
	 */
	private static String genSesource(Class<?> interfaces) {
		StringBuilder src = new StringBuilder();
		String ln = "\n";
		src.append("package testJdkProxy;").append(ln).append("import java.lang.reflect.Method;").append(ln)
		.append("import java.lang.reflect.InvocationHandler;").append(ln)
				.append("public class $Proxy0 implements ").append(interfaces.getName()).append("{").append(ln)
				.append("private InvocationHandler h;").append(ln).append("public $Proxy0(InvocationHandler h){")
				.append(ln).append("this.h=h;").append(ln).append("}").append(ln);

		for (Method method : interfaces.getMethods()) {
			src.append("public ").append(method.getReturnType()).append(" ").append(method.getName()).append("(String productName) {")
					.append(ln).append("try {").append(ln).append("Method m = ").append(interfaces.getName())
					.append(".class.getMethod(\"").append(method.getName()).append("\" ,Class.forName(\"java.lang.String\"));").append(ln)
					.append("this.h.invoke(this, m, new Object[]{productName});").append(ln).append("}catch (Throwable e){")
					.append(ln).append("e.printStackTrace();").append(ln).append("}").append(ln).append("}").append(ln);
		}
		src.append("}");

		return src.toString();

	}

}