package com.pharma_assist.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.annotations.IdGeneratorType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)///this should be work on runtime
@IdGeneratorType(CustomIdGenerator.class)
public @interface GenarateCustomId {

}
