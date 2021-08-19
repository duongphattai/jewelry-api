package springboot.jewelry.api.util;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Component
public class MapDtoToModel <E, T>{

    @SneakyThrows
    public T map(E dto, T model) {
        Method[] dtoMethods = dto.getClass().getMethods();

        for(Method dtoMethod : dtoMethods) {
            if(!dtoMethod.getName().equals("getClass") && dtoMethod.getName().startsWith("get")) {
                Class<?> propertyType = dtoMethod.getReturnType();
                String setterMethodName = dtoMethod.getName().replaceFirst("get", "set");

                Method modelSetterMethod = ReflectionUtils.findMethod(model.getClass(), setterMethodName, propertyType);
                if(modelSetterMethod != null) {
                    Object dtoValue = dtoMethod.invoke(dto); // getter method
                    modelSetterMethod.invoke(model, dtoValue);
                }
            }
        }
        return model;



//        List<String> dtoGetters = new LinkedList<String>();
//
//        // extract all getters from dto methods
//        for(Method method : dtoMethods) {
//            if(!method.getName().equals("getClass") && method.getName().startsWith("get"))
//                dtoGetters.add(method.getName());
//        }
//
//        // map dto properties to model properties
//        for(String getter : dtoGetters) {
//
//            try {
//                // get dto properties value
//                Object dtoValue = dto.getClass().getMethod(getter).invoke(dto);
//                // parse dto getter to model setter
//                String modelSetter = getter.replaceFirst("get", "set");
//
//                // get properties type
//                Class<?> propertyType = model.getClass().getMethod(modelSetter, dtoValue.getClass()).getParameterTypes()[0];
//
//                // call model's setter to set dtoValue to model
//                model.getClass().getMethod(modelSetter, propertyType).invoke(model, propertyType.cast(dtoValue));
//
//            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
//                    | NoSuchMethodException | SecurityException e) {
//                e.printStackTrace();
//            } catch (NullPointerException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        return model;
    }
}
