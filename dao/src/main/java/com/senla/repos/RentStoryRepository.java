package com.senla.repos;
import com.senla.entity.RentStory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface RentStoryRepository extends CommonRepository<RentStory> {


}
