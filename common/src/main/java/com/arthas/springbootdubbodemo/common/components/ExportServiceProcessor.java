package com.arthas.springbootdubbodemo.common.components;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * ExportService注解处理器
 * @author : lieying
 * date : 2018/6/27 17:17
 */
@SupportedAnnotationTypes({"com.arthas.springbootdubbodemo.common.components.ExportService"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
//@AutoService(Processor.class)
public class ExportServiceProcessor extends AbstractProcessor {

	private Messager messager;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv){
		super.init(processingEnv);
		this.messager = processingEnv.getMessager();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		return false;
	}
}
