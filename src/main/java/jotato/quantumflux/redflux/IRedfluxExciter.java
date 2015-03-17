package jotato.quantumflux.redflux;


/**
 * Based on King Lemming's CoFH/IEnderEndergyHandler. All Kudos to him
 *
 */
public interface IRedfluxExciter {
	
	/**
	 * 
	 * @return a unique string identifying the owner of this Exciter. Exciters are linked together based on this
	 */
	public String getOwner();
	
	/**
	 * 
	 * @return True if the exciter can receive power. Should return False for self
	 */
	public boolean canReceive();
	
	/**
	 * 
	 * @return whether or not the Exciter can send power
	 */
	public boolean canSend();
	
	/**
	 * 
	 * @param energy
	 * 			Amount to get
	 * @param simulate
	 * 			TRUE if it should be simulated
	 * @return amount of given energy that wasn't consumed; (0 if all was used)
	 */
	public int receiveEnergy(int energy, boolean simulate);
	
	
	/**
	 * 
	 * @param energy
	 * @param simulate
	 * @return amount of energy that was available to give
	 */
	public int requestEnergy(int energy, boolean simulate);

}
