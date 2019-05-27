package com.opinous.api;

import com.opinous.api.response.OpBaseResponse;
import com.opinous.api.response.OpListResponse;
import com.opinous.api.response.OpStringResponse;
import com.opinous.constants.RestPath;
import com.opinous.request.AuthenticateRequest;
import com.opinous.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(RestPath.AUTH)
public class AuthApi {

    @Autowired
    private AuthService authService;

    @PostMapping(RestPath.GENERATE_TOKEN)
    public OpStringResponse generateToken(@RequestBody final AuthenticateRequest authenticateRequest) {
        return null;
    }

    @GetMapping(RestPath.INVALIDATE_TOKEN)
    public OpBaseResponse invalidateToken(@PathVariable String token) {
        return null;
    }

    @GetMapping(RestPath.ROLES)
    public OpListResponse getRoles(@PathVariable String token) {
        return null;
    }
}
