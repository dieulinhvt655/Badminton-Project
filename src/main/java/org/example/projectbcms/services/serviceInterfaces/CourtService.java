package org.example.projectbcms.services.serviceInterfaces;

import org.example.projectbcms.models.Court;

import java.util.List;

public interface CourtService {

    Court createCourt(Court court);

    Court getCourtById(Long id);

    Court updateCourt(Court court, Long id);

    List<Court> getAllCourts();

    void deleteCourt(Long id);

}
