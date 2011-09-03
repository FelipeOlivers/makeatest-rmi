package com.yediat.makeatest.rmi.annotations;

import java.io.IOException;
import java.lang.reflect.Method;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;
import com.yediat.makeatest.rmi.core.MakeATestRMIAssertionError;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

public class PutMockRMIReader implements MakeATestReaderInterface<PutMockRMI> {

	@Override
	public void readAnnotation(PutMockRMI annotation, AnnotationProperties annotationProperties) {
		try {
			// Validations
			if(annotation.fixtureName() == null || annotation.fixtureName().equals(""))
				throw new MakeATestRMIAssertionError("@PutMockRMI annotation: Par�metro fixtureName vazio.");
			
			if(annotation.serverName() == null || annotation.serverName().equals(""))
				throw new MakeATestRMIAssertionError("@PutMockRMI annotation: Par�metro nameServer vazio.");
			
			if(annotation.serverPort() <= 0)
				throw new MakeATestRMIAssertionError("@PutMockRMI annotation: Par�metro portServer inv�lido.");
			
			PutMockRMIProcessor objectProcessor = new PutMockRMIProcessor((Method) annotationProperties.getAnnotated(), annotation.fixtureName(), annotation.serverName(), annotation.serverPort());
			annotationProperties.setProcessor(objectProcessor);	
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}