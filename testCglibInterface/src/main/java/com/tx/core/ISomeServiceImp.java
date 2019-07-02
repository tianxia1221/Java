package com.tx.core;

/**
 * 目标类
 * 
 * @author 浪丶荡
 *
 */
public class ISomeServiceImp implements ISomeService {
	@Override
	public String Litigate() {
		return "自己打官司，输了";
	}

	@Override
	public String eat() {
		return "自己吃饭";
	}

}