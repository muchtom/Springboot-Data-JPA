package com.tatenda.practiseproject.api;

import com.tatenda.practiseproject.DataTransferObject.CourseMaterialRequest;
import com.tatenda.practiseproject.entity.CourseMaterial;
import com.tatenda.practiseproject.service.CourseMaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseMaterial")
public class CourseMaterialController {

    private final CourseMaterialService courseMaterialService;

    public CourseMaterialController(CourseMaterialService courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }
    @PostMapping("/addNewCourseMaterial")
    public void addNewCourseMaterial(@RequestBody CourseMaterialRequest course){
        courseMaterialService.addCourseMaterial(course);
    }
    @PutMapping(path="/updateCourseMaterial/{courseMaterialId}")
    public void updateCourseMaterial(@PathVariable("courseMaterialId") Long courseMaterialId,
                                     @RequestParam(required = false) String courseMaterialUrl){
        courseMaterialService.updateCourseMaterial(courseMaterialId,courseMaterialUrl);

    }
 @DeleteMapping(path="/deleteCourseMaterial/{courseMaterialId}")
    public void deleteCourseMaterial(@PathVariable("courseMaterialId") Long courseMaterialId){
        courseMaterialService.deleteCourseMaterial(courseMaterialId);
    }
    @GetMapping("/getAllCourseMaterial")
    public List<CourseMaterial> getAllCourseMaterial(){
      return   courseMaterialService.getAllCourseMaterial();
    }
}
