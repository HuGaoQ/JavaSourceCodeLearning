package com.bruis.learnsb.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author LuoHaiYang
 */
@Component
public class MyPerson implements FactoryBean<Person> {

    @Override
    public Person getObject() {
        return new Person();
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }
}
