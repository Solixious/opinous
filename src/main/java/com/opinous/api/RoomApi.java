package com.opinous.api;

import com.opinous.api.response.OpBaseResponse;
import com.opinous.constants.RestPath;
import com.opinous.request.CreateRoomRequest;
import com.opinous.exception.RoomOverloadedException;
import com.opinous.model.Room;
import com.opinous.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestPath.ROOM)
@Slf4j
public class RoomApi {

    public static final String INSUFFICIENT_ALIAS_TO_CREATE_A_ROOM = "Insufficient alias to create a room.";

    @Autowired
    private RoomService roomService;

    @PostMapping(RestPath.NEW)
    public OpBaseResponse newRoom(@RequestBody final CreateRoomRequest createRoomRequest) {
        try {
            final Room room = new Room();
            BeanUtils.copyProperties(createRoomRequest, room);
            roomService.createRoom(room);
            return new OpBaseResponse();
        } catch (RoomOverloadedException e) {
            log.error(INSUFFICIENT_ALIAS_TO_CREATE_A_ROOM);
            return new OpBaseResponse(false, INSUFFICIENT_ALIAS_TO_CREATE_A_ROOM);
        }
    }
}
