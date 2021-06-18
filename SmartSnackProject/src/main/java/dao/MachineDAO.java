package dao;

import java.util.List;

import entity.Machine;

public interface MachineDAO {

	public Machine getMachineById(String id);

	public List<Machine> getAllMachines();

	public int addMachine(Machine machine);

	public int editMachine(Machine machine);

	public int deleteMachine(String machineId);

	public List<Machine> getSoldOutProductMachines();

	public List<Machine> getSoldOutCoinMachines();

	public List<Machine> getOfflineMachines();

}
