package com.agm.issuemanagement.api;

import com.agm.issuemanagement.dto.ProjectDto;
import com.agm.issuemanagement.entity.Project;
import com.agm.issuemanagement.service.impl.ProjectServiceImpl;
import com.agm.issuemanagement.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/versioning")
@Api(value = ApiPaths.ProjectCtrl.CTRL, description = "Project APIs")
@CrossOrigin
public class ProjectVersionApi {
    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @GetMapping(value = "/{id}", params = "version=1")
    @ApiOperation(value = "Get By Id Operation V1")
    public ResponseEntity<ProjectDto> getByIdV1(@PathVariable(value = "id", required = true) Long id) {
        ProjectDto projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping(value = "/{id}", params = "version=2")
    @ApiOperation(value = "Get By Id Operation V2")
    public ResponseEntity<ProjectDto> getByIdV2(@PathVariable(value = "id", required = true) Long id) {
        ProjectDto projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

}
