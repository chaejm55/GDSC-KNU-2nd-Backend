package jpabook.jpashop.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class AllOpen

inline fun <reified T> logger(): Logger = LoggerFactory.getLogger(T::class.java)