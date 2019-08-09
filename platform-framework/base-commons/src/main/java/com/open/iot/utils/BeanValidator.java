package com.open.iot.utils;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.collections4.MapUtils;

/**
 * 
* @ClassName: BeanValidator  
* @Description: hibernate-validator校验工具类  
* @author auth  
* @date 2019年8月9日  
*
 */
public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {
            return Collections.emptyMap();
        } else {
            LinkedHashMap errors = new LinkedHashMap<>();
            Iterator iterator = validateResult.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation violation = (ConstraintViolation)iterator.next();
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    public static Map<String, String> validateList(Collection<?> collection) {
        if(collection == null ){
        	throw new NullPointerException();
        }
        Iterator iterator = collection.iterator();
        Map errors;

        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object, new Class[0]);
        } while (errors.isEmpty());

        return errors;
    }

    public static Map<String, String> validateObject(Object first, Object... objects) {
    	
        if (objects != null && objects.length > 0) {
        	
        	List<Object> list = Arrays.asList(objects);
        	list.add(0, first);
        	
            return validateList( list );
        } else {
            return validate(first, new Class[0]);
        }
    }

	public static void check(Object param) throws IllegalArgumentException {
        Map<String, String> map = BeanValidator.validateObject(param);
        if (MapUtils.isNotEmpty(map)) {
            throw new IllegalArgumentException(map.toString());
        }
    }
	
//	public static void main(String[] args) {
//		
//		SysUser user = new SysUser ();
//		user.setId(1L);
//		BeanValidator.check(user);
//		
//		
//	}
}
