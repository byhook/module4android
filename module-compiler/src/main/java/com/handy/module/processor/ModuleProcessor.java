package com.handy.module.processor;

import com.google.auto.service.AutoService;
import com.handy.module.annotation.Module;
import com.handy.module.config.Constants;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author: handy
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes({Constants.ANNOTATION_MODULE_TYPE})
public class ModuleProcessor extends AbstractProcessor {

    private Filer mFiler;

    private Elements mElementUtils;

    private Types mTypeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mTypeUtils = processingEnv.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (CollectionUtils.isNotEmpty(annotations)) {
            Set<? extends Element> routeElements = roundEnv.getElementsAnnotatedWith(Module.class);
            try {
                parseModules(routeElements);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    private void parseModules(Set<? extends Element> routeElements) throws IOException {
        if (CollectionUtils.isNotEmpty(routeElements)) {
            ParameterizedTypeName inputParam = ParameterizedTypeName.get(
                    ClassName.get(Map.class),
                    ClassName.get(Class.class),
                    ClassName.get(Class.class)
            );
            ParameterSpec groupParamSpec = ParameterSpec.builder(inputParam, "mapper").build();
            MethodSpec.Builder loadModuleMapper = MethodSpec.methodBuilder("loadModuleMapper")
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(groupParamSpec);

            for (Element element : routeElements) {
                TypeMirror typeMirror = element.asType();

                DeclaredType classTypeMirror = (DeclaredType) typeMirror;
                TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
                String className = classTypeElement.getQualifiedName().toString();
                String simpleName = classTypeElement.getSimpleName().toString();

                List<TypeMirror> interfaces = (List<TypeMirror>) classTypeElement.getInterfaces();

                for (TypeMirror mirror : interfaces) {
                    loadModuleMapper.addStatement("mapper.put($T.class,$T.class)", mirror, classTypeElement);
                }

                List<TypeMirror> result = (List<TypeMirror>) mTypeUtils.directSupertypes(typeMirror);
                for (TypeMirror object : result) {
                    TypeKind typeKind = object.getKind();
                    boolean isInterface = object.getClass().isInterface();
                    System.out.println(isInterface + "" + typeKind + simpleName);
                }
                Module module = element.getAnnotation(Module.class);
                TypeKind typeKind = typeMirror.getKind();
                System.out.println(typeMirror + "" + module + " " + typeKind);
            }
            String rootFileName = "ModuleMapper";
            JavaFile.builder(Constants.PACKAGE_OF_GENERATE_FILE,
                    TypeSpec.classBuilder(rootFileName)
                            .addModifiers(Modifier.PUBLIC)
//                            .addSuperinterface(ClassName.get(IModulePlugin.class))
                            .addMethod(loadModuleMapper.build())
                            .build()
            ).build().writeTo(mFiler);
        }
    }

}
