package com.opinous.validator;

import com.opinous.model.Room;
import com.opinous.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RoomValidator implements Validator  {

    @Autowired
    private RoomService roomService;

    @Override public boolean supports(Class<?> aClass) {
        return Room.class.equals(aClass);
    }

    @Override public void validate(Object o, Errors errors) {
        Room room = (Room) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
    }
}
