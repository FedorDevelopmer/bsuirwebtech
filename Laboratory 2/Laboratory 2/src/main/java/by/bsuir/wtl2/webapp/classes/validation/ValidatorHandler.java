package by.bsuir.wtl2.webapp.classes.validation;


import by.bsuir.wtl2.webapp.classes.validation.validators.EmailValidator;
import by.bsuir.wtl2.webapp.classes.validation.validators.NameValidator;
import by.bsuir.wtl2.webapp.classes.validation.validators.PhoneNumberValidator;
import by.bsuir.wtl2.webapp.classes.validation.validators.TextValidator;

import java.util.HashMap;
import java.util.Map;

public class ValidatorHandler {
        private static final ValidatorHandler instance = new ValidatorHandler();

        private static Map<String, IValidator> validatorMap;

        public ValidatorHandler(){
            validatorMap = new HashMap<>();
            validatorMap.put("name_validator",new NameValidator());
            validatorMap.put("text_validator",new TextValidator());
            validatorMap.put("email_validator",new EmailValidator());
            validatorMap.put("phone_validator",new PhoneNumberValidator());
        }

        public static ValidatorHandler getInstance(){
            return instance;
        }

        public IValidator getValidatorByName(String name){
            return validatorMap.get(name);
        }

}
