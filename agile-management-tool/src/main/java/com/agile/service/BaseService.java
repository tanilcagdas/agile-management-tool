package com.agile.service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.IterationData;
import com.agile.beans.servicebean.IterationServiceBean;
import com.agile.interfaces.ConverterIF;
import com.agile.interfaces.DataIF;
import com.agile.interfaces.ServiceBeanIF;

public class BaseService<K extends ServiceBeanIF, V extends DataIF> {

	protected JpaRepository repository;
	protected ConverterIF<K, V> converter;

	public List<K> findAll() {
		List<V> dataList = repository.findAll();
		List<K> serviceBeanList = new ArrayList<>();
		for (V data : dataList) {
			serviceBeanList.add((K) converter.convert(data, createInstance()));
		}
		return serviceBeanList;
	}

	public void delete(K item) {
		repository.delete(converter.convert((K) item, null));

	}

	public K findOne(String name) {
		V data =  (V) repository.findOne(name);
		if (data == null) {
			return null;
		} else {
			return converter.convert(data, null);
		}
	}
	

	@SuppressWarnings("unchecked")
	private K createInstance() {
		K instance = null;
		try {
			instance = (K) ((Class<? extends ServiceBeanIF>) ((ParameterizedType) this.getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return instance;
	}

}
