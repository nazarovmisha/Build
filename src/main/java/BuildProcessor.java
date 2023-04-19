import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class BuildProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementAnnotatedWith = roundEnv.getElementsAnnotatedWith(BuilderField.class);

        Map<Boolean, List<Element>> annotatedMethods = elementAnnotatedWith.stream()
                .collect(Collectors.partitioningBy(element -> ((ExecutableType) element.asType())
                        .getParameterTypes().size() == 1 && element.getSimpleName().toString().startsWith("set")));

        List<Element> setters = annotatedMethods.get(true);

        String className = ((TypeElement) setters.get(0).getEnclosingElement()).getQualifiedName().toString();

        Map<String, String> setterMap = setters.stream().collect(Collectors.toMap(
                setter -> setter.getSimpleName().toString(), setter ->
                        ((ExecutableType) setter.asType()).getParameterTypes().get(0).toString()));
      try{
          createBuilderClass()
      }


        return false;
    }
}
