package javaClass;

import java.io.Serializable;
import java.sql.Date;
/**
 * The {@code Race} class is used to store information about race.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class Race implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idRace;
	private String raceName;
	private int price;
	private String place;
	private int winner;
	private Date date;
	private String ended;
	private String winnerName;
	private int idWinner;
	/**
	 * Class constructor
	 * @param idRace id of a race
	 * @param raceName name of a race
	 * @param price price of a rac
	 * @param place where a race take place
	 * @param winner winner of a race
	 * @param date date of a race
	 */
	public Race(int idRace,String raceName,int price,String place,int winner,Date date){
		this.idRace=idRace;
		this.raceName=raceName;
		this.price=price;
		this.place=place;
		this.winner=winner;
		this.date=date;
	}
	/**
	 * Class constructor
	 * @param idRace id of a race
	 * @param raceName name of a race
	 * @param price price of a rac
	 * @param place where a race take place
	 * @param winner winner of a race
	 * @param date date of a race
	 * @param ended string that show if a race ended or not
	 */
	public Race(int idRace,String raceName,int price,String place,int winner,Date date,String ended){
		this.idRace=idRace;
		this.raceName=raceName;
		this.price=price;
		this.place=place;
		this.winner=winner;
		this.date=date;
		this.ended=ended;
	}
	/**
	 * Class constructor
	 * @param idRace id of a race
	 * @param raceName name of a race
	 * @param price price of a rac
	 * @param place where a race take place
	 * @param date date of a race
	 */
	public Race(int idRace,String raceName,int price,String place,Date date){
		this.idRace=idRace;
		this.raceName=raceName;
		this.price=price;
		this.place=place;
		this.date=date;
	}
	/**
	 * Class constructor
	 * @param idRace id of a race
	 * @param raceName name of a race
	 * @param price price of a rac
	 * @param place where a race take place
	 * @param date date of a race
	 * @param idWinner id of the winner
	 * @param winnerName name of the winner
	 */
	public Race(int idRace,String raceName,int price,String place,Date date,int idWinner,String winnerName){
		this.idRace=idRace;
		this.raceName=raceName;
		this.price=price;
		this.place=place;
		this.date=date;
		this.idWinner=idWinner;
		this.winnerName=winnerName;
	}
	/**
	 * Class constructor
	 */
	public Race() {
		
	}
	/**
	 * @return
	 * It returns the id of a race
	 */
	public int getIdRace() {
		return idRace;
	}
	
	/**
	 * @return
	 * It returns the name of a race
	 */
	public String getRaceName() {
		return raceName;
	}
	
	/**
	 * @return
	 * It returns the price of a race
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * @return
	 * It returns the place of a race
	 */
	public String getPlace() {
		return place;
	}
	
	/**
	 * @return
	 * It returns the winner of a race
	 */
	public int getWinner() {
		return winner;
	}
	
	/**
	 * @return
	 * It returns the date of a race
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @return
	 * It returns a string that say if a race ended or not
	 */
	public String getEnded() {
		return ended;
	}
	
	/**
	 * @return
	 * It returns the name of the person who won the race
	 */
	public String getWinnerName() {
		return winnerName;
	}
	
	/**
	 * @return
	 * It returns the id of a winner
	 */
	public int getIdWinner() {
		return idWinner;
	}

}
