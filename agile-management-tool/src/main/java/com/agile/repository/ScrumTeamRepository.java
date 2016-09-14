package com.agile.repository;


import java.util.List;

import com.agile.beans.ProductOwnerUserData;
import com.agile.beans.ScrumTeamData;


public interface ScrumTeamRepository extends BaseRepository<ScrumTeamData, String> {
	
	List<ScrumTeamData> findByProductOwner(ProductOwnerUserData user); 
}

