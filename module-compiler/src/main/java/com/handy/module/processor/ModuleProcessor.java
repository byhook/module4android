package com.handy.module.processor;

import com.google.auto.service.AutoService;
import com.handy.module.config.Constants;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

/**
 * @author: handy
 * @date: 2020-06-09
 * @description:
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes({Constants.ANNOTATION_MODULE_TYPE})
public class ModuleProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }

}
