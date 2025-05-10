package org.example.projectbcms.services;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.models.Court;
import org.example.projectbcms.repositories.CourtRepository;
import org.example.projectbcms.services.serviceInterfaces.CourtService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourtImpl implements CourtService {

    private final CourtRepository courtRepository;

    @Override
    public Court createCourt(Court court) {
        Court newCourt = new Court();
        newCourt.setName(court.getName());
        newCourt.setPricePerHour(court.getPricePerHour());
        newCourt.setCourtStatus(court.getCourtStatus());

        courtRepository.save(newCourt);

        return newCourt;
    }

    @Override
    public Court getCourtById(Long id){
        Optional<Court> court = courtRepository.findById(id);
        return court.orElseThrow();
    }

    @Override
    public Court updateCourt(Court updateCourt, Long id) {
        Court courtPresent = getCourtById(id);
        courtPresent.setName(updateCourt.getName());
        courtPresent.setCourtStatus(String.valueOf(updateCourt.getPricePerHour()));
        courtPresent.setCourtStatus(String.valueOf(updateCourt.getCourtStatus()));

        return courtRepository.save(courtPresent);
    }

    @Override
    public List<Court> getAllCourts() {
       List<Court> courts = courtRepository.findAll();
       return courts;
    }

    @Override
    public void deleteCourt(Long id) {
        courtRepository.deleteById(id);
        System.out.println("xoa thanh cong!");
    }
}
