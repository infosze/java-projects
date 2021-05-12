package dao;

import java.util.List;

import entity.Machine;

public interface AutomatDAO {
	
	public List<Machine> getSoldOutProductMachines();

	public List<Machine> getSoldOutCoinMachines();

	public List<Machine> getOfflineMachines();


}
