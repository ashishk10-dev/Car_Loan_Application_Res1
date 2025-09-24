package com.cm.service;


import java.util.List;

import com.cm.model.CustomerDetails;
import com.cm.model.SanctionLetter;

public interface SanctionServiceI {

	public CustomerDetails generateSactionId(int customerID, SanctionLetter sanctionLetter);


	


}
