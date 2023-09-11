package com.paul.billing_system.controller;

import com.paul.billing_system.dto.DepartmentDTO;
import com.paul.billing_system.entity.Department;
import com.paul.billing_system.service.DepartmentServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentServices departmentServices;

    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    @PostMapping("/addDepartment/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody DepartmentDTO departmentDTO,@PathVariable Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        DepartmentDTO departmentDTO1 = DepartmentDTO.form(departmentServices.save(id,departmentDTO));
        return new ResponseEntity<>(departmentDTO1, HttpStatus.OK);
    }
    @GetMapping("/getAllDepartment")
    public ResponseEntity<?> getAllDepartment(){
        List<Department> departments = departmentServices.getAllDepartmant();
        List<DepartmentDTO> departmentDTOList = departments.stream().map(DepartmentDTO::form).toList();
        return new ResponseEntity<>(departmentDTOList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id){
        DepartmentDTO departmentDTO = DepartmentDTO.form(departmentServices.getDepartmentById(id));
        return new ResponseEntity<>(departmentDTO,HttpStatus.OK);
    }

    @PutMapping("/updateDepartment/{id}")
    public ResponseEntity<?> updateDepartment(@Valid @PathVariable Long id, @RequestBody DepartmentDTO departmentDTO,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        DepartmentDTO departmentDTO1 = DepartmentDTO.form(departmentServices.updateDepartment(departmentDTO,id));
        return new ResponseEntity<>(departmentDTO1,HttpStatus.OK);
    }
}
