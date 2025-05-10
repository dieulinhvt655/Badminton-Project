package org.example.projectbcms.controllers;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.models.Court;
import org.example.projectbcms.services.serviceInterfaces.CourtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courts")
@RequiredArgsConstructor
public class CourtController {

    private final CourtService courtService;

    @PostMapping("/tao-san-cau")
    public Court createCourt(@RequestBody Court newCourt) {
        return courtService.createCourt(newCourt);
    }

    @PutMapping("/cap-nhat-san-cau/{id}")
    public Court updateCourt( @RequestBody Court newCourt, @PathVariable Long id) {
        return courtService.updateCourt(newCourt, id);
    }

    @GetMapping("/hien-thi-thong-tin-san-cau/{id}")
    public Court getCourt(@PathVariable Long id) {
        return courtService.getCourtById(id);
    }

    @GetMapping("/hien-thi-thong-tin-tat-ca-san-cau")
    public List<Court> getCourts() {
        return courtService.getAllCourts();
    }

    @DeleteMapping("/xoa-san-cau/{id}")
    public void deleteCourt(@PathVariable Long id) {
        courtService.deleteCourt(id);
    }

}
