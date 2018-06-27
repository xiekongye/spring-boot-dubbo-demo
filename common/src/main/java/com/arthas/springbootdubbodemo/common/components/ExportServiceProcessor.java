package com.arthas.springbootdubbodemo.common.components;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Iterator;
import java.util.Set;

/**
 * ExportService注解处理器
 * @author : lieying
 * date : 2018/6/27 17:17
 */
@SupportedAnnotationTypes({"com.arthas.springbootdubbodemo.common.components.ExportService"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class ExportServiceProcessor extends AbstractProcessor {

	private Messager messager;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv){
		super.init(processingEnv);
		this.messager = processingEnv.getMessager();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		this.messager.printMessage(Diagnostic.Kind.NOTE,"执行ExportService注解处理器......");

		Iterator<? extends TypeElement> iterator = annotations.iterator();
		while (iterator.hasNext()){
			TypeElement element = iterator.next();
			Class clazz = element.getClass();
			messager.printMessage(Diagnostic.Kind.NOTE,clazz.toString());
			messager.printMessage(Diagnostic.Kind.NOTE,String.format("注解名：%s",element.getSimpleName()));
			Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(element);

			Iterator<? extends Element> elemIterator = elements.iterator();
			while (elemIterator.hasNext()){
				Element elem = elemIterator.next();
				messager.printMessage(Diagnostic.Kind.NOTE,elem.getSimpleName());

			}
		}

		return false;
	}
}
