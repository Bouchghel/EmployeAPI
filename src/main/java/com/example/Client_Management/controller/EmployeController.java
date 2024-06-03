package com.example.Client_Management.controller;

import com.example.Client_Management.entity.Employe;
import com.example.Client_Management.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        Employe employe = employeService.getEmployeById(id);
        return ResponseEntity.ok(employe);
    }

    @GetMapping("/{id}/file/{attributeName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id, @PathVariable String attributeName) {
        Employe employe = employeService.getEmployeById(id);
        Map<String, Object> attributes = employe.getAttributes();
        if (attributes != null && attributes.containsKey(attributeName)) {
            String fileBase64 = (String) attributes.get(attributeName);
            byte[] fileBytes = Base64.getDecoder().decode(fileBase64);
            ByteArrayResource resource = new ByteArrayResource(fileBytes);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + attributeName)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(fileBytes.length)
                    .body(resource);
        }
        return ResponseEntity.notFound().build();
    }
}
