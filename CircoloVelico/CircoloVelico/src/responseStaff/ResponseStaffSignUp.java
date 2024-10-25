package responseStaff;


import java.io.Serializable;

/**
 * The {@code ResponseStaffSignUp} class is a Response class and it used to send a response from the server to the client.
 * This Response class is used to send this kind of request:
 * 1)Send an object that contains the id of the staff member who Signed in<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class ResponseStaffSignUp implements Serializable 
{
  private static final long serialVersionUID = 1L;
  private final String username ;
  private final String password ;
  private final int req;
  private final int id;
  /**
   * Class constructor
   * @param username username of the user who signed in
   * @param password password of the user who signed in
   * @param req an integer value that distinguish which request client has sent to the server.
   * @param id id of the user who signed in
   */
  public ResponseStaffSignUp(final String username,final String password,final int req,int id)
  {
    this.username=username;
    this.password=password;
    this.req=req;
    this.id=id;
  }

  /**
 * @return
 * it return the username of a staff member
 */
public String getUsername()
  {
    return this.username;
  }
  /**
 * @return
 *  it return the password of a staff member
 */
public String getPassword()
  {
    return this.password;
  }
  /**
 * @return
 * it return an integer value
 */
public int getReq() {
	  return this.req;
  }
  /**
 * @return
 * it return the id of a staff member
 */
public int getId() {
	  return this.id;
  }
}
