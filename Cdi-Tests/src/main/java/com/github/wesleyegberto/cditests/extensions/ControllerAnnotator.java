package com.github.wesleyegberto.cditests.extensions;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

/**
 * Each class with the sufix "Controller" will be annotated with @Controller. 
 */
public class ControllerAnnotator implements Extension {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void annotates(@Observes ProcessAnnotatedType pat) {
		AnnotatedType annotatedType = pat.getAnnotatedType();
		Class type = annotatedType.getJavaClass();
		
		// verify if it has Controller in its name
		if(type.getSimpleName().endsWith("Controller")) {
			pat.setAnnotatedType(new AnnotatedTypeControllerWrapper(annotatedType));
			System.out.println("[CDI Extension] Controller found: " + type.getSimpleName());
		}
	}
}
