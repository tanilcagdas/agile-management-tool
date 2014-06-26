package com.agile.interfaces;


public interface ConverterIF<T1 extends ServiceBeanIF, T2 extends DataIF> {

	public T2  convert(T1 service, T2 data);

	public T1 convert(T2 data, T1 service);

}
