package com.example.projektpo.oldentities;

import org.springframework.data.repository.CrudRepository;

public interface TravelRepository extends CrudRepository<Travel, Integer> {
    Travel findById(int id);

//    @Query("SELECT travel.id FROM Travel travel WHERE travel.id = userId")
//    Travel findAllByUserId(int userId);

}
