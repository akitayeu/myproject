package com.samsolutions.kitayeu.myproject.controllers.REST;

import com.samsolutions.kitayeu.myproject.dtos.RoleDto;
import com.samsolutions.kitayeu.myproject.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/page={page}")
    public ResponseEntity<List<RoleDto>> readAllRoles(@PathVariable(name = "page") int page) {
        final List<RoleDto> roleDtoList = roleService.getAllRole(page);
        return roleDtoList != null && !roleDtoList.isEmpty()
                ? new ResponseEntity<>(roleDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable(name = "id") int id) {
        final RoleDto roleDto = roleService.getById(id);
        return roleDto != null
                ? new ResponseEntity<>(roleDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable(name = "id") int id) {
        final boolean deleted = roleService.deleteRole(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateRole(@RequestBody RoleDto roleDto, @PathVariable(name = "id") int id) {
        final boolean updated = roleService.updateRole(roleDto, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RoleDto roleDto) {
        final RoleDto createdRole = roleService.createRole(roleDto);
        return createdRole != null
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
