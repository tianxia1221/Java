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
 * @desc 自己实现的代理类，用来生成字节码文件，并动态加载到JVM中
 * @author gaowenfeng
 * @date 2018/3/30
 */
public class MyProxy {
	/**
	 * 生成代理对象
	 * 
	 * @param loader
	 *            类加载器，用于加载被代理类的类文件
	 * @param interfaces
	 *            被代理类的接口
	 * @param h
	 *            自定义的InvocationHandler接口,用于具体代理方法的执行
	 * @return 返回被代理后的代理对象
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static Object newProxyInstance(MyClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
			throws IllegalArgumentException {
		/**
		 * 1.生成代理类的源代码 2.将生成的源代码输出到磁盘，保存为.java文件 3.编译源代码，并生成.java文件
		 * 4.将class文件中的内容，动态加载到JVM中 5.返回被代理后的代理对象
		 */
		try {
			// 1.生成代理类的源代码
			String src = genSesource(interfaces[0]);
			// 2.将生成的源代码输出到磁盘，保存为.java文件
			String path = MyProxy.class.getResource("").getPath();
			File file = new File(path + "$Proxy0.java");

			FileWriter fw = new FileWriter(file);
			fw.write(src);
			fw.close();

			// 3.编译源代码，并生成.java文件
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

			// 4.将class文件中的内容，动态加载到JVM中
			Class proxyClass = loader.findClass("$Proxy0");

			// 5.返回被代理后的代理对象
			Constructor c = proxyClass.getConstructor(InvocationHandler.class);
			return c.newInstance(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/*
	 * 生成代理类的源代码
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